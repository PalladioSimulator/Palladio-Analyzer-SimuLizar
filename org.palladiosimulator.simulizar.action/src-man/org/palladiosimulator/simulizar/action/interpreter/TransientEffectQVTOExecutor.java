package org.palladiosimulator.simulizar.action.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.instance.InstancePackage;
import org.palladiosimulator.simulizar.action.mapping.Mapping;
import org.palladiosimulator.simulizar.action.mapping.MappingPackage;
import org.palladiosimulator.simulizar.reconfiguration.qvto.AbstractQVTOExecutor;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.TransformationCache;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.TransformationData;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.TransformationParameterInformation;

/**
 * Implementation of the {@link AbstractQVTOExecutor} suitable for executing {@link AdaptationStep
 * AdaptationSteps}, i.e., transient effects.
 * 
 * @author Florian Rosenthal
 *
 */
class TransientEffectQVTOExecutor extends AbstractQVTOExecutor {

    private final List<ModelExtent> currentPureOutParams;
    private final URI adaptationStepUri;
    private final URI controllerCompletionUri;
    private final URI preconditionUri;

    private static final EPackage MAPPING_EPACKAGE = MappingPackage.Literals.MAPPING.getEPackage();
    private static final EPackage REPOSITORY_EPACKAGE = RepositoryPackage.Literals.REPOSITORY.getEPackage();
    private static final EPackage ACTION_EPACKAGE = CorePackage.Literals.ACTION.getEPackage();
    private static final EPackage ROLE_SET_EPACKAGE = InstancePackage.Literals.ROLE_SET.getEPackage();

    protected TransientEffectQVTOExecutor(QVToModelCache availableModels, AdaptationStep adaptationStep) {
        super(new TransformationCache(), Objects.requireNonNull(availableModels));

        this.adaptationStepUri = URI.createURI(Objects.requireNonNull(adaptationStep.getAdaptationStepURI()));
        this.controllerCompletionUri = URI.createURI(adaptationStep.getControllerCompletionURI());
        this.preconditionUri = URI.createURI(adaptationStep.getPreconditionURI());
        this.currentPureOutParams = new ArrayList<>();

        getAvailableTransformations().store(this.adaptationStepUri, this.controllerCompletionUri, this.preconditionUri);
        getAvailableModels().storeModel(adaptationStep);

        validateControllerCompletion();
        validateAdaptationStep();
        validatePrecondition();
    }

    private void validatePrecondition() {
        TransformationData preconditionData = getAvailableTransformations().get(this.preconditionUri);

        byte flags = 0;
        for (TransformationParameterInformation inParam : preconditionData.getInParameters()) {
            EPackage paramType = inParam.getParameterType();
            if (paramType.equals(ACTION_EPACKAGE)) {
                flags |= 1;
            } else if (paramType.equals(ROLE_SET_EPACKAGE)) {
                flags |= 2;
            }
        }
        // combination of the two yields 3
        // independent of the frequency of occurrence of each of which
        if (flags != 3) {
            throw new RuntimeException("Precondition " + this.preconditionUri
                    + " must have at least 'in'/'inout' parameters of type " + ACTION_EPACKAGE.getNsURI() + " and "
                    + ROLE_SET_EPACKAGE.getNsURI());
        }
    }

    private void validateControllerCompletion() {
        TransformationData controllerCompletionData = getAvailableTransformations().get(this.controllerCompletionUri);

        Collection<TransformationParameterInformation> outParams = controllerCompletionData.getPureOutParameters();
        if (outParams.size() != 1 || !outParams.iterator().next().getParameterType().equals(MAPPING_EPACKAGE)) {
            throw new RuntimeException("Controller completion " + this.controllerCompletionUri
                    + " must have exactly one 'out' parameter of type " + MAPPING_EPACKAGE.getNsURI());
        }
        Iterable<TransformationParameterInformation> inParams = controllerCompletionData.getInParameters();
        byte flags = 0;
        for (TransformationParameterInformation inParam : inParams) {
            EPackage paramType = inParam.getParameterType();
            if (paramType.equals(REPOSITORY_EPACKAGE)) {
                flags |= 1;
            } else if (paramType.equals(ACTION_EPACKAGE)) {
                flags |= 2;
            } else if (paramType.equals(ROLE_SET_EPACKAGE)) {
                flags |= 4;
            }
            // combination of all three yields 7: (1 | 2) | 4 = 1 | (2 | 4) = (1 | 4) | 2 = 7
            // independent of the frequency of occurrence of each of which
        }
        if (flags != 7) {
            throw new RuntimeException("Controller completion " + this.controllerCompletionUri
                    + " must have at least 'in'/'inout' parameters of type " + REPOSITORY_EPACKAGE.getNsURI() + ", "
                    + ACTION_EPACKAGE.getNsURI() + " and " + ROLE_SET_EPACKAGE.getNsURI());
        }
    }

    private void validateAdaptationStep() {
        TransformationData adaptationStepData = getAvailableTransformations().get(this.adaptationStepUri);

        Iterable<TransformationParameterInformation> inParams = adaptationStepData.getInParameters();
        byte flags = 0;
        for (TransformationParameterInformation inParam : inParams) {
            EPackage paramType = inParam.getParameterType();
            if (paramType.equals(MAPPING_EPACKAGE)) {
                flags |= 1;
            } else if (paramType.equals(ROLE_SET_EPACKAGE)) {
                flags |= 2;
            }
            // combination of the two yields 3
            // independent of the frequency of occurrence of each of which
        }
        if (flags != 3) {
            throw new RuntimeException("AdaptationStep " + this.adaptationStepUri
                    + " must have at least 'in'/'inout' parameters of type " + MAPPING_EPACKAGE.getNsURI() + " and "
                    + ROLE_SET_EPACKAGE.getNsURI());
        }
    }

    final boolean executePrecondition() {
        return executeTransformation(this.preconditionUri);
    }

    final boolean executeAdaptationStep() {
        return executeTransformation(this.adaptationStepUri);
    }

    final Mapping executeControllerCompletion(Repository controllerCompletionRepository) {
        EObject repoInCache = null;
        if (getAvailableModels().containsModelOfType(REPOSITORY_EPACKAGE)) {
            // save
            repoInCache = getAvailableModels().getModelByType(REPOSITORY_EPACKAGE);
        }
        getAvailableModels().storeModel(controllerCompletionRepository);
        boolean result = executeTransformation(this.controllerCompletionUri);
        if (repoInCache != null) {
            // restore
            getAvailableModels().storeModel(repoInCache);
        }
        if (result) {
            EObject resultObj = getAvailableModels().getModelByType(MAPPING_EPACKAGE);
            if (resultObj != null) {
                return (Mapping) resultObj;
            }
        }
        return null;
    }

    @Override
    protected boolean handleExecutionResult(ExecutionDiagnostic executionResult) {
        boolean result = super.handleExecutionResult(executionResult);
        if (result) {
            for (ModelExtent outParam : this.currentPureOutParams) {
                EObject resultModel = outParam.getContents().get(0);
                getAvailableModels().storeModel(resultModel);
            }
        }
        return result;
    }

    @Override
    protected ModelExtent[] setupModelExtents(TransformationData data) {
        this.currentPureOutParams.clear();
        ModelExtent[] result = super.setupModelExtents(data);
        for (TransformationParameterInformation outParam : data.getPureOutParameters()) {
            this.currentPureOutParams.add(result[outParam.getParameterIndex()]);
        }
        return result;
    }
}

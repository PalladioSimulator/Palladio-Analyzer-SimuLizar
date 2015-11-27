package org.palladiosimulator.simulizar.action.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

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
        TransformationData preconditionData = getAvailableTransformations().get(this.preconditionUri).get();

        Collection<TransformationParameterInformation> inParams = preconditionData.getInParameters();
        if (inParams.parallelStream().noneMatch(paramTypePredicate(ACTION_EPACKAGE))
                && inParams.parallelStream().noneMatch(paramTypePredicate(ROLE_SET_EPACKAGE))) {
            throw new RuntimeException(
                    "Precondition " + this.preconditionUri + " must have at least 'in'/'inout' parameters of type "
                            + ACTION_EPACKAGE.getNsURI() + " and " + ROLE_SET_EPACKAGE.getNsURI());
        }
    }

    private void validateControllerCompletion() {
        TransformationData controllerCompletionData = getAvailableTransformations().get(this.controllerCompletionUri)
                .get();

        Collection<TransformationParameterInformation> outParams = controllerCompletionData.getPureOutParameters();
        if (outParams.size() != 1 || !outParams.iterator().next().getParameterType().equals(MAPPING_EPACKAGE)) {
            throw new RuntimeException("Controller completion " + this.controllerCompletionUri
                    + " must have exactly one 'out' parameter of type " + MAPPING_EPACKAGE.getNsURI());
        }
        Collection<TransformationParameterInformation> inParams = controllerCompletionData.getInParameters();
        if (inParams.parallelStream().noneMatch(paramTypePredicate(REPOSITORY_EPACKAGE))
                && inParams.parallelStream().noneMatch(paramTypePredicate(ACTION_EPACKAGE))
                && inParams.parallelStream().noneMatch(paramTypePredicate(ROLE_SET_EPACKAGE))) {
            throw new RuntimeException("Controller completion " + this.controllerCompletionUri
                    + " must have at least 'in'/'inout' parameters of type " + REPOSITORY_EPACKAGE.getNsURI() + ", "
                    + ACTION_EPACKAGE.getNsURI() + " and " + ROLE_SET_EPACKAGE.getNsURI());
        }
    }

    private void validateAdaptationStep() {
        TransformationData adaptationStepData = getAvailableTransformations().get(this.adaptationStepUri).get();

        Collection<TransformationParameterInformation> inParams = adaptationStepData.getInParameters();
        if (inParams.parallelStream().noneMatch(paramTypePredicate(MAPPING_EPACKAGE))
                && inParams.parallelStream().noneMatch(paramTypePredicate(ROLE_SET_EPACKAGE))) {
            throw new RuntimeException(
                    "AdaptationStep " + this.adaptationStepUri + " must have at least 'in'/'inout' parameters of type "
                            + MAPPING_EPACKAGE.getNsURI() + " and " + ROLE_SET_EPACKAGE.getNsURI());
        }
    }

    private static Predicate<? super TransformationParameterInformation> paramTypePredicate(EPackage desiredType) {
        return param -> param.getParameterType().equals(desiredType);
    }

    final boolean executePrecondition() {
        return executeTransformation(this.preconditionUri);
    }

    final boolean executeAdaptationStep() {
        return executeTransformation(this.adaptationStepUri);
    }

    final Optional<Mapping> executeControllerCompletion(Repository controllerCompletionRepository) {
        // cache repo, if present
        Optional<EObject> cachedRepo = getAvailableModels().getModelByType(REPOSITORY_EPACKAGE);
        getAvailableModels().storeModel(controllerCompletionRepository);
        boolean result = executeTransformation(this.controllerCompletionUri);
        // restore if necessary
        cachedRepo.ifPresent(repoInCache -> getAvailableModels().storeModel(repoInCache));
        if (result) {
            return getAvailableModels().getModelByType(MAPPING_EPACKAGE).map(obj -> (Mapping) obj);
        }
        return Optional.empty();
    }

    @Override
    protected boolean handleExecutionResult(ExecutionDiagnostic executionResult) {
        boolean result = super.handleExecutionResult(executionResult);
        if (result) {
            this.currentPureOutParams.stream().map(ModelExtent::getContents).filter(contents -> !contents.isEmpty())
                    .forEach(contents -> getAvailableModels().storeModel(contents.get(0)));
        }
        return result;
    }

    @Override
    protected ModelExtent[] setupModelExtents(TransformationData data) {
        this.currentPureOutParams.clear();
        ModelExtent[] result = super.setupModelExtents(data);
        data.getPureOutParameters()
                .forEach(outParam -> this.currentPureOutParams.add(result[outParam.getParameterIndex()]));
        return result;
    }
}

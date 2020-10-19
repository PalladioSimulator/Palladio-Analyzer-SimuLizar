package org.palladiosimulator.simulizar.action.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.simulizar.action.context.ExecutionContext;
import org.palladiosimulator.simulizar.action.core.EnactAdaptationStep;
import org.palladiosimulator.simulizar.action.core.GuardedTransition;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingStep;
import org.palladiosimulator.simulizar.action.core.StateTransformingStep;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.action.mapping.Mapping;
import org.palladiosimulator.simulizar.action.mapping.MappingPackage;
import org.palladiosimulator.simulizar.reconfiguration.qvto.AbstractQVTOExecutor;
import org.palladiosimulator.simulizar.reconfiguration.qvto.QvtoModelTransformation;
import org.palladiosimulator.simulizar.reconfiguration.qvto.TransformationParameterInformation;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.ModelTransformationCache;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;

/**
 * Implementation of the {@link AbstractQVTOExecutor} suitable for executing {@link AdaptationStep
 * AdaptationSteps}, i.e., transient effects.
 * 
 * @author Florian Rosenthal
 *
 */
class TransientEffectQVTOExecutor extends AbstractQVTOExecutor {

	private static final Logger LOGGER = Logger.getLogger(TransientEffectQVTOExecutor.class);
	
    private static final EPackage MAPPING_EPACKAGE = MappingPackage.Literals.MAPPING.getEPackage();
    private static final EPackage REPOSITORY_EPACKAGE = RepositoryPackage.Literals.REPOSITORY.getEPackage();

    private final Collection<ModelExtent> currentPureOutParams;
    
    protected TransientEffectQVTOExecutor(ModelTransformationCache transformationCache, QVToModelCache availableModels) {
        super(transformationCache, Objects.requireNonNull(availableModels));
        this.currentPureOutParams = new ArrayList<>();

    }

    Optional<Mapping> executeControllerCompletion(Repository controllerCompletionRepository,
            String controllerCompletionPath, IResourceTableManager resourceTableManager) {
        // cache repo, if present
        Collection<EObject> cachedRepo = this.getModelsByType(REPOSITORY_EPACKAGE);
        this.storeModel(controllerCompletionRepository);
        URI controllerCompletionUri = URI.createURI(controllerCompletionPath);
        QvtoModelTransformation controllerCompletion = this.getTransformationByUri(controllerCompletionUri).get();
        boolean result = this.executeTransformation(controllerCompletion, resourceTableManager);
        // restore if necessary
        cachedRepo.forEach(repoInCache -> this.storeModel(repoInCache));
        if (result) {
            return this.getModelByType(MAPPING_EPACKAGE).map(obj -> (Mapping) obj);
        }
        return Optional.empty();
    }

    boolean executeGuardedTransition(GuardedTransition guardedTransition, IResourceTableManager resourceTableManager) {
    	URI conditionUri = URI.createURI(guardedTransition.getConditionURI());
		QvtoModelTransformation condition = this.getTransformationByUri(conditionUri).get();
		ExecutionDiagnostic result = this.executeTransformationInternal(Objects.requireNonNull(condition), resourceTableManager);
		return handleExecutionResultForGuardedTransition(guardedTransition, result);
    }

    private void storeModel(EObject model) {
        getAvailableModels().storeModel(model);
    }

    private void prepareTransformation(String transformationUri) {
        assert transformationUri != null;

        URI uri = URI.createURI(Objects.requireNonNull(transformationUri));
        if (!getAvailableTransformations().contains(uri)) {
            getAvailableTransformations().store(uri);
        }
    }

    void addTransformationParameters(RoleSet roleSet, ExecutionContext context) {
    	 storeModel(roleSet);
         storeModel(context);
    }
    
    void enableForTransformationExecution(EnactAdaptationStep enactAdaptationStep) {
        storeModel(Objects.requireNonNull(enactAdaptationStep));
        prepareTransformation(enactAdaptationStep.getAdaptationStepURI());
    }

    void enableForTransformationExecution(ResourceDemandingStep resourceDemandingStep) {
        storeModel(Objects.requireNonNull(resourceDemandingStep));
        prepareTransformation(resourceDemandingStep.getControllerCompletionURI());
    }

    void enableForTransformationExecution(StateTransformingStep stateTransformingStep) {
        storeModel(Objects.requireNonNull(stateTransformingStep));
    }

    void enableForTransformationExecution(GuardedTransition guardedTransition) {
        prepareTransformation(Objects.requireNonNull(guardedTransition).getConditionURI());
    }

    Optional<QvtoModelTransformation> getTransformationByUri(URI transformationId) {
        return getAvailableTransformations().get(Objects.requireNonNull(transformationId));
    }

    Collection<EObject> getModelsByType(EPackage modelType) {
        return getAvailableModels().getModelsByType(Objects.requireNonNull(modelType));
    }

    Optional<EObject> getModelByType(EPackage modelType) {
        Collection<EObject> modelsOfType = this.getModelsByType(Objects.requireNonNull(modelType));
        return modelsOfType.isEmpty() ? Optional.empty() : Optional.of(modelsOfType.iterator().next());
    }

    @Override
    protected boolean handleExecutionResult(ExecutionDiagnostic executionResult) {
        boolean result = super.handleExecutionResult(executionResult);
        if (result) {
            this.currentPureOutParams.stream().map(ModelExtent::getContents).filter(contents -> !contents.isEmpty())
                    .map(contents -> contents.get(0)).forEach(getAvailableModels()::storeModel);
        }
        return result;
    }
    
    protected boolean handleExecutionResultForGuardedTransition(GuardedTransition guardedTransition, ExecutionDiagnostic executionResult) {
    	if (executionResult.getCode() == ExecutionDiagnostic.FATAL_ASSERTION) {
    		LOGGER.info("Guard Condition of \"" + guardedTransition.getEntityName() + "\" evaluated to false. The transition is not taken.");
    		return false;
    	} else {
    		return this.handleExecutionResult(executionResult);
    	}
    	
    }

    @Override
    protected ModelExtent[] setupModelExtents(QvtoModelTransformation transformation) {
        this.currentPureOutParams.clear();
        ModelExtent[] result = super.setupModelExtents(transformation);
        transformation.getPureOutParameters().stream().mapToInt(TransformationParameterInformation::getParameterIndex)
                .mapToObj(index -> result[index]).forEach(this.currentPureOutParams::add);
        return result;
    }
}

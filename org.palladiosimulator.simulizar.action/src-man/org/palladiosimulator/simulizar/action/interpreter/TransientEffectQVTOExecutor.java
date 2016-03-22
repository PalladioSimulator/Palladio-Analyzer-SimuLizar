package org.palladiosimulator.simulizar.action.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.simulizar.action.mapping.Mapping;
import org.palladiosimulator.simulizar.action.mapping.MappingPackage;
import org.palladiosimulator.simulizar.reconfiguration.qvto.AbstractQVTOExecutor;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;
import org.palladiosimulator.simulizar.reconfigurationrule.qvto.QvtoModelTransformation;
import org.palladiosimulator.simulizar.reconfigurationrule.qvto.TransformationParameterInformation;

/**
 * Implementation of the {@link AbstractQVTOExecutor} suitable for executing {@link AdaptationStep
 * AdaptationSteps}, i.e., transient effects.
 * 
 * @author Florian Rosenthal
 *
 */
class TransientEffectQVTOExecutor extends AbstractQVTOExecutor {

    private static final EPackage MAPPING_EPACKAGE = MappingPackage.Literals.MAPPING.getEPackage();
    private static final EPackage REPOSITORY_EPACKAGE = RepositoryPackage.Literals.REPOSITORY.getEPackage();

    private final Collection<ModelExtent> currentPureOutParams;

    protected TransientEffectQVTOExecutor(QVToModelCache availableModels) {
        super(Objects.requireNonNull(availableModels));
        this.currentPureOutParams = new ArrayList<>();
    }

    Optional<Mapping> executeControllerCompletion(Repository controllerCompletionRepository,
            QvtoModelTransformation controllerCompletion) {
        // cache repo, if present
        Collection<EObject> cachedRepo = this.getModelsByType(REPOSITORY_EPACKAGE);
        this.storeModel(controllerCompletionRepository);
        boolean result = this.executeTransformation(controllerCompletion);
        // restore if necessary
        cachedRepo.forEach(repoInCache -> this.storeModel(repoInCache));
        if (result) {
            return this.getModelByType(MAPPING_EPACKAGE).map(obj -> (Mapping) obj);
        }
        return Optional.empty();
    }

    private void storeModel(EObject model) {
        getAvailableModels().storeModel(model);
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

    @Override
    protected ModelExtent[] setupModelExtents(QvtoModelTransformation data) {
        this.currentPureOutParams.clear();
        ModelExtent[] result = super.setupModelExtents(data);
        data.getPureOutParameters().stream().mapToInt(TransformationParameterInformation::getParameterIndex)
                .mapToObj(index -> result[index]).forEach(this.currentPureOutParams::add);
        return result;
    }
}

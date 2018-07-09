package org.palladiosimulator.simulizar.action.interpreter;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.EnactAdaptationStep;
import org.palladiosimulator.simulizar.action.core.GuardedTransition;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingStep;
import org.palladiosimulator.simulizar.action.instance.InstancePackage;
import org.palladiosimulator.simulizar.action.mapping.MappingPackage;
import org.palladiosimulator.simulizar.reconfiguration.qvto.QvtoModelTransformation;
import org.palladiosimulator.simulizar.reconfiguration.qvto.TransformationParameterInformation;

public class TransientEffectQVTOExecutorUtil {

    private static final EPackage MAPPING_EPACKAGE = MappingPackage.Literals.MAPPING.getEPackage();
    private static final EPackage REPOSITORY_EPACKAGE = RepositoryPackage.Literals.REPOSITORY.getEPackage();
    private static final EPackage ADAPTATION_BEHAVIOR_EPACKAGE = CorePackage.Literals.ADAPTATION_BEHAVIOR.getEPackage();
    private static final EPackage ROLE_SET_EPACKAGE = InstancePackage.Literals.ROLE_SET.getEPackage();

    private static Predicate<? super TransformationParameterInformation> paramTypePredicate(EPackage desiredType) {
        return param -> param.getParameterType().equals(desiredType);
    }

    static void validateGuardedTransition(TransientEffectQVTOExecutor executor, GuardedTransition guardedTransition) {
        URI conditionUri = URI.createURI(guardedTransition.getConditionURI());
        Optional<QvtoModelTransformation> preconditionData = executor.getTransformationByUri(conditionUri);

        Collection<TransformationParameterInformation> inParams = preconditionData
                .map(QvtoModelTransformation::getInParameters)
                .orElseThrow(() -> new RuntimeException("Condition transformation not available!"));
        if (inParams.parallelStream().noneMatch(paramTypePredicate(ADAPTATION_BEHAVIOR_EPACKAGE))
                && inParams.parallelStream().noneMatch(paramTypePredicate(ROLE_SET_EPACKAGE))) {
            throw new RuntimeException(
                    "Condition/Guard " + conditionUri + " must have at least 'in'/'inout' parameters of type"
                            + ADAPTATION_BEHAVIOR_EPACKAGE.getNsURI() + " and " + ROLE_SET_EPACKAGE.getNsURI());
        }

    }

    static void validateResourceDemandingStep(TransientEffectQVTOExecutor executor,
            ResourceDemandingStep resourceDemandingStep) {
        URI controllerCompletionUri = URI.createURI(resourceDemandingStep.getControllerCompletionURI());
        Optional<QvtoModelTransformation> controllerCompletionData = executor
                .getTransformationByUri(controllerCompletionUri);

        Collection<TransformationParameterInformation> outParams = controllerCompletionData
                .orElseThrow(() -> new RuntimeException("Controller completion transformation not available!"))
                .getPureOutParameters();
        if (outParams.size() != 1 || !outParams.iterator().next().getParameterType().equals(MAPPING_EPACKAGE)) {
            throw new RuntimeException("Controller completion " + controllerCompletionUri
                    + " must have exactly one 'out' parameter of type " + MAPPING_EPACKAGE.getNsURI());
        }
        Collection<TransformationParameterInformation> inParams = controllerCompletionData.get().getInParameters();
        if (inParams.parallelStream().noneMatch(paramTypePredicate(REPOSITORY_EPACKAGE))
                && inParams.parallelStream().noneMatch(paramTypePredicate(ADAPTATION_BEHAVIOR_EPACKAGE))
                && inParams.parallelStream().noneMatch(paramTypePredicate(ROLE_SET_EPACKAGE))) {
            throw new RuntimeException("Controller completion " + controllerCompletionUri
                    + " must have at least 'in'/'inout' parameters of type " + REPOSITORY_EPACKAGE.getNsURI() + ", "
                    + ADAPTATION_BEHAVIOR_EPACKAGE.getNsURI() + " and " + ROLE_SET_EPACKAGE.getNsURI());
        }
    }

    static void validateEnactAdaptationStep(TransientEffectQVTOExecutor executor,
            EnactAdaptationStep enactAdaptationStep) {
        URI adaptationStepUri = URI.createURI(Objects.requireNonNull(enactAdaptationStep.getAdaptationStepURI()));
        Optional<QvtoModelTransformation> adaptationStepData = executor.getTransformationByUri(adaptationStepUri);

        Collection<TransformationParameterInformation> inParams = adaptationStepData
                .orElseThrow(() -> new RuntimeException("Adaptation step transformation not available!"))
                .getInParameters();
        if (inParams.parallelStream().noneMatch(paramTypePredicate(MAPPING_EPACKAGE))
                && inParams.parallelStream().noneMatch(paramTypePredicate(ROLE_SET_EPACKAGE))) {
            throw new RuntimeException(
                    "AdaptationStep " + adaptationStepUri + " must have at least 'in'/'inout' parameters of type "
                            + MAPPING_EPACKAGE.getNsURI() + " and " + ROLE_SET_EPACKAGE.getNsURI());
        }
    }
}

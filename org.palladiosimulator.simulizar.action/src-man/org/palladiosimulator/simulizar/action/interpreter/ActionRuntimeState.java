package org.palladiosimulator.simulizar.action.interpreter;

import java.util.Objects;

import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection;
import org.palladiosimulator.simulizar.action.parameter.ParameterFactory;
import org.palladiosimulator.simulizar.runtimestate.IRuntimeStateAccessor;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public class ActionRuntimeState implements IRuntimeStateAccessor {
    private static SimuLizarRuntimeState state;
    private static IModelAccess modelAccess;

    private static final ControllerCallInputVariableUsageCollection EMPTY_VARIABLE_USAGE_COLLECTION = ParameterFactory.eINSTANCE
            .createControllerCallInputVariableUsageCollection();

    /**
     * Initializes a new instance of the {@link TransientEffectInterpreter} class with the given
     * parameters.
     * 
     * @param set
     *            The {@link RoleSet} that shall be used by the interpreter.
     * @param repository
     *            The {@link AdaptationBehaviorRepository} that contains all available adaptations.
     * @return A new instance of the {@link TransientEffectInterpreter} class.
     * @throws NullPointerException
     *             In case any of the passed objects is {@code null}.
     */
    public static TransientEffectInterpreter createTransientEffectInterpreter(RoleSet set,
            AdaptationBehaviorRepository repository) {
        return createTransientEffectInterpreter(set, EMPTY_VARIABLE_USAGE_COLLECTION, repository);
    }

    /**
     * Initializes a new instance of the {@link TransientEffectInterpreter} class with the given
     * parameters.
     * 
     * @param set
     *            The {@link RoleSet} that shall be used by the interpreter.
     * @param controllerCallVariabelUsages
     *            The {@link ControllerCallInputVariableUsageCollection} to be used by the
     *            interpreter for executing {@link ControllerCall}s.
     * @param repository
     *            The {@link AdaptationBehaviorRepository} that contains all available adaptations.
     * @return A new instance of the {@link TransientEffectInterpreter} class.
     * @throws NullPointerException
     *             In case any of the passed objects is {@code null}.
     */
    public static TransientEffectInterpreter createTransientEffectInterpreter(RoleSet set,
            ControllerCallInputVariableUsageCollection controllerCallVariabelUsages,
            AdaptationBehaviorRepository repository) {

        return new TransientEffectInterpreter(state, Objects.requireNonNull(set),
                Objects.requireNonNull(controllerCallVariabelUsages), Objects.requireNonNull(repository), modelAccess);
    }

    /**
     * Injects the {@link SimuLizarRuntimeState} which is then used by the
     * {@link #createTransientEffectInterpreter(RoleSet, AdaptationBehaviorRepository)} methods to
     * equip the interpreters.
     * 
     * @param The
     *            {@link SimuLizarRuntimeState} to inject.
     * @throws NullPointerException
     *             In case the given instance is {@code null}.
     */
    @Override
    public void setRuntimeStateModel(SimuLizarRuntimeState passedState) {
        ActionRuntimeState.state = Objects.requireNonNull(passedState);
        ActionRuntimeState.modelAccess = passedState.getModelAccess();
    }
}

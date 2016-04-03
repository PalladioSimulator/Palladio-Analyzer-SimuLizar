package org.palladiosimulator.simulizar.action.interpreter;

import java.util.Objects;
import java.util.Optional;

import org.palladiosimulator.simulizar.action.context.ExecutionContext;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection;
import org.palladiosimulator.simulizar.action.parameter.ParameterFactory;
import org.palladiosimulator.simulizar.runtimestate.IRuntimeStateAccessor;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeStateAbstract;

public class ActionRuntimeState implements IRuntimeStateAccessor {
    private static SimuLizarRuntimeStateAbstract state;

    private static final ControllerCallInputVariableUsageCollection EMPTY_VARIABLE_USAGE_COLLECTION = ParameterFactory.eINSTANCE
            .createControllerCallInputVariableUsageCollection();

    /**
     * Gets a {@link TransientEffectInterpreterBuilder} which is suitable to construct an
     * {@link TransientEffectInterpreter} with the given arguments.
     * 
     * @param roleSet
     *            The {@link RoleSet} to be used by the interpreter.
     * @param repository
     *            The {@link AdaptationBehaviorRepository} which contains all available adaptations
     *            to be executed.
     * @return An {@link TransientEffectInterpreterBuilder} instance to construct the interpreter.
     * @throws NullPointerException
     *             In case either argument is {@code null}.
     */
    public static TransientEffectInterpreterBuilder getInterpreterBuilder(RoleSet roleSet,
            AdaptationBehaviorRepository repository) {
        return new TransientEffectInterpreterBuilder(roleSet, repository);
    }

    /**
     * Injects the {@link SimuLizarRuntimeState} which is then used by
     * {@link #TransientEffectInterpreterBuilder} instances to equip themselves for interpreter
     * creation.
     * 
     * @param The
     *            {@link SimuLizarRuntimeState} to inject.
     * @throws NullPointerException
     *             In case the given instance is {@code null}.
     */
    @Override
    public void setRuntimeStateModel(SimuLizarRuntimeStateAbstract passedState) {
        ActionRuntimeState.state = Objects.requireNonNull(passedState);
    }

    /**
     * Implementation of the well-known <i>Builder Pattern</i> for a facilitated construction of
     * {@link TransientEffectInterpreter}s with different configurations.
     * 
     * @author Florian Rosenthal
     *
     */
    public static class TransientEffectInterpreterBuilder {

        private final SimuLizarRuntimeStateAbstract state = ActionRuntimeState.state;
        private final RoleSet roleSet;
        private final AdaptationBehaviorRepository repository;

        private ExecutionContext context = null;
        private ControllerCallInputVariableUsageCollection controllerCallVariableUsages = EMPTY_VARIABLE_USAGE_COLLECTION;
        private boolean isAsync = false;

        private TransientEffectInterpreterBuilder(RoleSet roleSet, AdaptationBehaviorRepository repository) {
            this.roleSet = Objects.requireNonNull(roleSet);
            this.repository = Objects.requireNonNull(repository);
        }

        /**
         * Equips the current builder instance to create an interpreter for asynchronous
         * interpretation.
         * 
         * @return The current (yet modified) instance.
         */
        public TransientEffectInterpreterBuilder isAsync() {
            this.isAsync = true;
            return this;
        }

        public TransientEffectInterpreterBuilder isAsync(ExecutionContext context) {
            return isAsync().addExecutionContext(Objects.requireNonNull(context));
        }

        /**
         * Equips the current builder instance with a collection of input variable usages that shall
         * be used by the interpreter to be constructed.
         * 
         * @param controllerCallVariabelUsages
         *            The {@link ControllerCallInputVariableUsageCollection} to be used by the
         *            interpreter for executing {@link ControllerCall}s.
         * 
         * @return The current (yet modified) instance.
         * @throws NullPointerException
         *             In case {@code ControllerCallInputVariableUsages == null}.
         */
        public TransientEffectInterpreterBuilder addControllerCallVariableUsages(
                ControllerCallInputVariableUsageCollection controllerCallVariabelUsages) {
            this.controllerCallVariableUsages = Objects.requireNonNull(controllerCallVariabelUsages);
            return this;
        }

        public TransientEffectInterpreterBuilder addExecutionContext(ExecutionContext executionContext) {
            this.context = Objects.requireNonNull(executionContext);
            return this;
        }

        /**
         * Constructs an appropriate {@link TransientEffectInterpreter} by using the current builder
         * configuration.
         * 
         * @return A newly created {@link TransientEffectInterpreter} instance.
         */
        public TransientEffectInterpreter build() {
            return new TransientEffectInterpreter(this.state, this.roleSet, this.controllerCallVariableUsages,
                    this.repository, this.isAsync, Optional.ofNullable(this.context));
        }
    }
}

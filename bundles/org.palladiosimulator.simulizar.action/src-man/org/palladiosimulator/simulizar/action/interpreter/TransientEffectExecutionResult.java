package org.palladiosimulator.simulizar.action.interpreter;

import java.util.Optional;

import org.omg.PortableInterceptor.SUCCESSFUL;
import org.palladiosimulator.simulizar.action.context.ExecutionContext;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;

/**
 * Class to represent the result of the execution of transient effects, i.e.,
 * {@link AdaptationBehavior}s. Instances of this class are created by the
 * {@link TransientEffectInterpreter} in the course of its
 * {@link TransientEffectInterpreter#caseAdaptationBehavior(AdaptationBehavior)} method. Note that
 * in case of an asynchronous execution of the behavior, the meaningfulness is limited since the
 * execution is only triggered. Hence, in such a case the corresponding result only contains
 * information on this.
 * 
 * @author Florian Rosenthal
 *
 */
public final class TransientEffectExecutionResult {

    private final EventResult executionResult;
    private final Optional<ExecutionContext> executionContext;

    TransientEffectExecutionResult(EventResult result, ExecutionContext context) {
        this.executionResult = result;
        this.executionContext = Optional.ofNullable(context);
    }

    /**
     * Gets the execution result.
     * 
     * @return The result of the execution of the behavior, in terms of a {@link EventResult}
     *         constant.<br>
     *         If this instance is associated with an asynchronous behavior execution,
     *         {@link SUCCESSFUL} is returned in any case.
     */
    public EventResult getExecutionResult() {
        return this.executionResult;
    }

    /**
     * Gets the execution context that was used to execute the behavior. This is mainly of interest
     * in case of an asynchronous execution.
     * 
     * @return The {@link ExecutionContext} that was used to execute the behavior.
     */
    public Optional<ExecutionContext> getContext() {
        return this.executionContext;
    }

    /**
     * Gets the execution result in terms of a boolean flag. This is only meaningful in case of a
     * synchronous behavior execution.
     * 
     * @return {@code true} in case of success, else {@code false}.
     */
    public boolean getExecutionResultAsBoolean() {
        return this.executionResult == EventResult.SUCCESS ? true : false;
    }
}

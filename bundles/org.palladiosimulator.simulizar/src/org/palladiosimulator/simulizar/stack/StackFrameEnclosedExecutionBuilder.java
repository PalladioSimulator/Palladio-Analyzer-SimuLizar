package org.palladiosimulator.simulizar.stack;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.palladiosimulator.pcm.parameter.VariableUsage;

import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * This interface allows to specify interpreter executions in nested parameter / result stack frame
 * scopes.
 * 
 * @author Sebastian Krach
 *
 * @param <T>
 *            the concrete builder type.
 */
public interface StackFrameEnclosedExecutionBuilder<T extends StackFrameEnclosedExecutionBuilder<T>> {

    /**
     * Provide variable usages which should be evaluated before the execution, determining which
     * variables should be transferred from the current parameter stack frame to the new parameter
     * stack frame.
     * 
     * @param variables
     *            the list of variable usages which are evaluated based on the result stack frame.
     */
    T evaluateInput(Iterable<VariableUsage> variables);

    /**
     * Provide additional callbacks which are able to add / modify the newly created parameter stack
     * frame.
     * 
     * @param enhancer
     *            the callback taking the newly created parameter stack frame.
     */
    T enhanceInput(Consumer<SimulatedStackframe<Object>> enhancer);

    /**
     * Enable result stack frame processing. This is done implicitly when
     * {@link #evaluateReturns(Iterable)} or {@link #enhanceReturns(Consumer)} is used.
     */
    T processReturnFrame();

    /**
     * Provide variable usages which should be evaluated after the execution, determining which
     * variable should be transferred from the result stack to the enclosing parameter stack.
     * 
     * @param variables
     *            the list of variable usages which are evaluated based on the result stack frame.
     */
    T evaluateReturns(Iterable<VariableUsage> variables);

    /**
     * Provide additional callbacks which are able to process the resulting return value stack frame
     * after the execution.
     * 
     * @param enhancer
     *            the callback taking the resulting return stack frame.
     */
    T enhanceReturns(Consumer<SimulatedStackframe<Object>> enhancer);

    /**
     * Sets, whether the parent of the newly created stack frame should be the current stack frame.
     * All values in the parent stack frame are resolvable through the child, if the child does not
     * contain its own value for the respective key.
     * 
     * @param connected,
     *            true if the parent relationship should be established
     * 
     */
    T setConnectedToParent(boolean connected);

    /**
     * Executes the provided runnable after evaluating all input variables. After the execution the
     * return variables are processed before returning control.
     * 
     * @param runnable
     *            the actual interpretation routine.
     */
    void execute(Runnable runnable);

    /**
     * Executes the provided runnable after evaluating all input variables. After the execution the
     * return variables are processed before returning control. In contrast to
     * {@link #execute(Runnable)} this closure returns a value which is passed through after return
     * variable processing.
     * 
     * @param <R>
     *            the result type
     * @param closure
     *            the actual closure providing a return value
     * @return the value provided by the closure
     */
    <R> R execute(Supplier<R> closure);

}

package org.palladiosimulator.simulizar.stack;

import java.util.Optional;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.stack.processors.ImplicitParameterPostProcessor;

import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * This interface allows to implement extensions which can influence nested stack frame creation, in
 * order to support to automatically provide values on the parameter / result stack. An examplary
 * implementation can be found in {@link ImplicitParameterPostProcessor}.
 * 
 * @author Sebastian Krach
 *
 */
public interface StackFramePrePostProcessor {
    /**
     * Called before the execution in the nested scope is started
     * 
     * @param context
     *            the context of the current interpretation
     * @param childFrame
     *            the newly created stack frame
     */
    default void onChildFrameCreate(InterpreterDefaultContext context, SimulatedStackframe<Object> childFrame) {
    }

    /**
     * Called before the execution, only if return variable processing is enabled for this frame.
     * 
     * @param context
     *            the context of the current interpretation
     * @param resultFrame
     *            the newly created result frame
     */
    default void onResultFrameCreate(InterpreterDefaultContext context, SimulatedStackframe<Object> resultFrame) {
    }

    /**
     * Called before the execution but after the input variable usages are evaluated and stored to
     * the new child frame.
     * 
     * @param context
     *            the context of the current interpretation
     * @param childFrame
     *            the child frame containing the evaluated input variable usages
     * @param resultFrame
     *            the result frame, present only if return value processing is enabled for this
     *            frame
     */
    default void onInputParameterEvaluateDone(InterpreterDefaultContext context, SimulatedStackframe<Object> childFrame,
            Optional<SimulatedStackframe<Object>> resultFrame) {
    }

    /**
     * Called right after the execution in the nested scope finished. Return variable usages are NOT
     * yet evaluated.
     * 
     * @param context
     *            the context of the current interpretation
     * @param childFrame
     *            the child frame containing the evaluated input variable usages
     * @param resultFrame
     *            the result frame, present only if return value processing is enabled for this
     *            frame
     */
    default void onPostExecute(InterpreterDefaultContext context, SimulatedStackframe<Object> childFrame,
            Optional<SimulatedStackframe<Object>> resultFrame) {
    }

    /**
     * Called after the execution in the nested scope finished. The nested parameter stack frame has
     * already been dropped. The result frame has also already been removed from the result frame
     * stack. The return variable usages are NOT yet evaluated. This event is only called if return
     * variable usage processing is activated.
     * 
     * @param context
     *            the context of the current interpretation
     * @param resultFrame
     *            the result frame
     */
    default void onBeforeResultFrameParameterEvaluate(InterpreterDefaultContext context,
            SimulatedStackframe<Object> resultFrame) {
    }

    /**
     * Called after the execution in the nested scope finished. The nested parameter stack frame has
     * already been dropped. The result frame has also already been removed from the result frame
     * stack. The return variable usages have just been evaluated. This event is only called if
     * return variable usage processing is activated.
     * 
     * @param context
     *            the context of the current interpretation
     * @param resultFrame
     *            the result frame
     */
    default void onResultFrameParameterEvaluateDone(InterpreterDefaultContext context,
            SimulatedStackframe<Object> resultFrame) {
    }

    /**
     * Called right after all newly created frames have been removed from the stack, just before the
     * control is handed back to the interpreter.
     * 
     * @param context
     *            the context of the current interpretation
     * @param childFrame
     *            the frame containing the input parameters (not on the stack anymore)
     * @param resultFrame
     *            the result frame of the execution (not on the result stack anymore), only if
     *            result variable usage processing is enabled.
     */
    default void onStackRestored(InterpreterDefaultContext context, SimulatedStackframe<Object> childFrame,
            Optional<SimulatedStackframe<Object>> resultFrame) {
    }

}

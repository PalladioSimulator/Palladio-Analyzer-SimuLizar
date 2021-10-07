package org.palladiosimulator.simulizar.stack.processors;

import java.util.Optional;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.ConstantsContainer;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.stack.StackFramePrePostProcessor;

import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

public class ImplicitParameterPostProcessor implements StackFramePrePostProcessor {
    @Inject
    public ImplicitParameterPostProcessor() {}
    
    @Override
    public void onChildFrameCreate(InterpreterDefaultContext context,
            de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe<Object> childFrame) {
        for (var value: context.getStack().currentStackFrame().getContents()) {
            if (value.getKey().startsWith(ConstantsContainer.IMPLICIT_IN_PARAMETER_PREFIX) || 
                    value.getKey().startsWith(ConstantsContainer.IMPLICIT_INOUT_PARAMETER_PREFIX)) {
                childFrame.addValue(value.getKey(), value.getValue());
            }
        }
    }

    @Override
    public void onPostExecute(InterpreterDefaultContext context, SimulatedStackframe<Object> childFrame,
            Optional<SimulatedStackframe<Object>> resultFrame) {
        for (var value: childFrame.getContents()) {
            if ((value.getKey().startsWith(ConstantsContainer.IMPLICIT_INOUT_PARAMETER_PREFIX) ||
                    value.getKey().startsWith(ConstantsContainer.IMPLICIT_OUT_PARAMETER_PREFIX)) &&
                    !context.getCurrentResultFrame().hasValue(value.getKey())) {
                context.getCurrentResultFrame().addValue(value.getKey(), value.getValue());
            }
        }
    }
    
    @Override
    public void onBeforeResultFrameParameterEvaluate(InterpreterDefaultContext context,
            SimulatedStackframe<Object> resultFrame) {
        for (var value: resultFrame.getContents()) {
            if (value.getKey().startsWith(ConstantsContainer.IMPLICIT_INOUT_PARAMETER_PREFIX) ||
                    value.getKey().startsWith(ConstantsContainer.IMPLICIT_OUT_PARAMETER_PREFIX)) {
                context.getStack().currentStackFrame().addValue(value.getKey(), value.getValue());
            }
        }
    }

}

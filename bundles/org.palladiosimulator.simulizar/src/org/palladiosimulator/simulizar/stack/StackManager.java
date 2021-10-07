package org.palladiosimulator.simulizar.stack;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.inject.Inject;

import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.scopes.SimulatedThreadScope;
import org.palladiosimulator.simulizar.utils.SimulatedStackHelper;

import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

@SimulatedThreadScope
/**
 * The StackMananger manages interpreter executions in nested parameter / result stack scopes. It
 * replaces deprecated utility methods of {@link SimulatedStackHelper}.
 * 
 * Use {@link #inChildFrame(InterpreterDefaultContext)} to create a new
 * {@link StackFrameEnclosedExecutionBuilder}. See documentation of
 * {@link StackFrameEnclosedExecutionBuilder}.
 * 
 * 
 * @author Sebastian Krach
 *
 */
public class StackManager {
    class StackFrameEnclosedExecutionBuilderImpl
            implements StackFrameEnclosedExecutionBuilder<StackFrameEnclosedExecutionBuilderImpl> {
        final InterpreterDefaultContext context;
        SimulatedStackframe<Object> parent;
        boolean processOutput = false;
        Consumer<SimulatedStackframe<Object>> outputProcessor = null;
        Consumer<SimulatedStackframe<Object>> inputProcessor = null;

        public StackFrameEnclosedExecutionBuilderImpl(InterpreterDefaultContext context) {
            this.context = context;
            this.parent = context.getStack().currentStackFrame();
        }

        @Override
        public <R> R execute(Supplier<R> closure) {
            var stack = context.getStack();

            var frame = parent != null ? new SimulatedStackframe<>(parent) : new SimulatedStackframe<>();
            var resultFrame = processOutput ? Optional.of(new SimulatedStackframe<>())
                    : Optional.<SimulatedStackframe<Object>> empty();

            processors.forEach(proc -> proc.onChildFrameCreate(context, frame));

            if (processOutput) {
                var latestResultFrame = resultFrame.get();
                processors.forEach(proc -> proc.onResultFrameCreate(context, latestResultFrame));
                context.getResultFrameStack()
                    .push(latestResultFrame);
            }

            if (inputProcessor != null) {
                inputProcessor.accept(frame);
            }
            processors.forEach(proc -> proc.onInputParameterEvaluateDone(context, frame, resultFrame));

            stack.pushStackFrame(frame);

            var res = closure.get();

            processors.forEach(proc -> proc.onPostExecute(context, frame, resultFrame));

            stack.removeStackFrame();

            if (processOutput) {
                var latestResultFrame = context.getResultFrameStack().pop();
                processors.forEach(proc -> proc.onBeforeResultFrameParameterEvaluate(context, latestResultFrame));
                if (outputProcessor != null) {
                    outputProcessor.accept(latestResultFrame);
                }
                processors.forEach(proc -> proc.onResultFrameParameterEvaluateDone(context, latestResultFrame));
            }

            processors.forEach(proc -> proc.onStackRestored(context, frame, resultFrame));
            return res;
        }

        @Override
        public void execute(Runnable runnable) {
            this.execute(() -> {
                runnable.run();
                return null;
            });
        }

        @Override
        public StackFrameEnclosedExecutionBuilderImpl enhanceInput(Consumer<SimulatedStackframe<Object>> enhancer) {
            if (inputProcessor == null) {
                inputProcessor = enhancer;
            } else {
                inputProcessor = inputProcessor.andThen(enhancer);
            }
            return this;
        }

        @Override
        public StackFrameEnclosedExecutionBuilderImpl evaluateInput(Iterable<VariableUsage> variables) {
            this.enhanceInput(childFrame -> {
                SimulatedStackHelper.addParameterToStackFrame(context.getStack()
                    .currentStackFrame(), variables, childFrame);
            });
            return this;
        }

        public StackFrameEnclosedExecutionBuilderImpl setConnectedToParent(boolean connected) {
            parent = connected ? context.getStack()
                .currentStackFrame() : null;
            return this;
        }

        @Override
        public StackFrameEnclosedExecutionBuilderImpl processReturnFrame() {
            processOutput = true;
            return this;
        }

        @Override
        public StackFrameEnclosedExecutionBuilderImpl evaluateReturns(Iterable<VariableUsage> variables) {
            this.enhanceReturns(result -> {
                SimulatedStackHelper.addParameterToStackFrame(result, variables, context.getStack()
                    .currentStackFrame());
            });
            return this;
        }

        @Override
        public StackFrameEnclosedExecutionBuilderImpl enhanceReturns(Consumer<SimulatedStackframe<Object>> enhancer) {
            processOutput = true;
            if (outputProcessor == null) {
                outputProcessor = enhancer;
            } else {
                outputProcessor = outputProcessor.andThen(enhancer);
            }
            return this;
        }
    }

    private final Set<StackFramePrePostProcessor> processors;

    @Inject
    public StackManager(Set<StackFramePrePostProcessor> processors) {
        this.processors = processors;
    }

    /**
     * Entry point to the execution of interpreter logic in a nested stack frame scope.
     * 
     * @return the execution builder. See {@link StackFrameEnclosedExecutionBuilder} for more
     *         details.
     */
    public StackFrameEnclosedExecutionBuilder<?> inChildFrame(InterpreterDefaultContext context) {
        return new StackFrameEnclosedExecutionBuilderImpl(context);
    }
}

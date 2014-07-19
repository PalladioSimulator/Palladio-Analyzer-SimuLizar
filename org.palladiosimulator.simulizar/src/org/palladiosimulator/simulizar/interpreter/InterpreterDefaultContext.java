package org.palladiosimulator.simulizar.interpreter;

import java.util.Stack;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.runtimestate.SimuComRuntimeState;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.simucomframework.Context;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStack;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * Default context for the pcm interpreter.
 * 
 * @author Joachim Meyer
 * 
 */
public class InterpreterDefaultContext extends Context {

    /**
    * 
    */
    private static final long serialVersionUID = -5027373777424401211L;

    protected static final Logger LOG = Logger.getLogger(InterpreterDefaultContext.class.getName());

    private final Stack<AssemblyContext> assemblyContextStack = new Stack<AssemblyContext>();

    private final SimuComRuntimeState runtimeState;

    public InterpreterDefaultContext(final SimuComRuntimeState simulizarModel) {
        super(simulizarModel.getModel());
        this.stack = new SimulatedStack<Object>();
        this.runtimeState = simulizarModel;
    }

    /**
     * Constrcutor
     * 
     * @param simuComModel
     *            the SimuCom model.
     * @param simProcess
     *            the sim process of this context, means the process in which this context is used
     */
    public InterpreterDefaultContext(final SimuComRuntimeState simulizarModel, final SimuComSimProcess simProcess) {
        this(simulizarModel);
        this.setSimProcess(simProcess);
    }

    public InterpreterDefaultContext(
            final Context context,
            final SimuComRuntimeState runtimeState,
            final boolean copyStack) {
        super(context.getModel());
        this.setEvaluationMode(context.getEvaluationMode());
        this.setSimProcess(context.getThread());
        this.stack = new SimulatedStack<Object>();
        this.runtimeState = runtimeState;
        if (copyStack && context.getStack().size() > 0) {
            this.stack.pushStackFrame(context.getStack().currentStackFrame().copyFrame());
        } else {
            this.stack.pushStackFrame(new SimulatedStackframe<Object>());
        }
    }

    /**
     * Create interpreter default context from the given default context (model, sim process and
     * stack are set according to the given default context). The contents of the stack will be
     * copied.
     * 
     * @param context
     *            the default context from which the new default context should be created.
     */
    public InterpreterDefaultContext(final InterpreterDefaultContext context) {
        this(context, context.getRuntimeState(), true);
    }

    public SimuComRuntimeState getRuntimeState() {
        return this.runtimeState;
    }

    /**
     * @see org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext#initialiseAssemblyContextLookup()
     */
    @Override
    protected void initialiseAssemblyContextLookup() {
        // Template method which is only needed in generative SimuCom
    }

    public Stack<AssemblyContext> getAssemblyContextStack() {
        return this.assemblyContextStack;
    }
}

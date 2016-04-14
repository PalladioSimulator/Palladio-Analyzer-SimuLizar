package org.palladiosimulator.simulizar.interpreter;

import java.util.Stack;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeStateAbstract;

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

    private final Stack<AssemblyContext> assemblyContextStack = new Stack<AssemblyContext>();

    private final SimuLizarRuntimeStateAbstract runtimeState;

    private final IModelAccess modelAccess;

    public InterpreterDefaultContext(final SimuLizarRuntimeStateAbstract simulizarModel) {
        super(simulizarModel.getModel());
        this.stack = new SimulatedStack<Object>();
        this.runtimeState = simulizarModel;
        this.modelAccess = this.runtimeState.getModelAccess();
    }

    InterpreterDefaultContext(final Context context, final SimuLizarRuntimeStateAbstract runtimeState,
            final boolean copyStack) {
        super(context.getModel());
        this.modelAccess = runtimeState.getModelAccess().clone();
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
     * @param thread
     */
    public InterpreterDefaultContext(final InterpreterDefaultContext context, final SimuComSimProcess thread) {
        this(context, context.getRuntimeState(), true);
        this.setSimProcess(thread);
    }

    public SimuLizarRuntimeStateAbstract getRuntimeState() {
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

    public IModelAccess getModelAccess() {
        return this.modelAccess;
    }
}

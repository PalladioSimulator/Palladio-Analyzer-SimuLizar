package org.palladiosimulator.simulizar.interpreter;

import java.util.Stack;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.simucomframework.Context;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStack;

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

    protected static final Logger logger = Logger.getLogger(InterpreterDefaultContext.class.getName());

    private final Stack<AssemblyContext> assemblyContextStack = new Stack<AssemblyContext>();

    private final EventNotificationHelper eventHelper;

    /**
     * Create interpreter default context from the given default context (model, sim process and
     * stack are set according to the given default context). The contents of the stack will be
     * copied.
     * 
     * @param context
     *            the default context from which the new default context should be created.
     */
    public InterpreterDefaultContext(final InterpreterDefaultContext context) {
        super(context.getModel());
        this.setEvaluationMode(context.getEvaluationMode());
        this.setSimProcess(context.getThread());
        this.stack = new SimulatedStack<Object>();
        this.eventHelper = context.eventHelper;
        if (context.getStack().size() > 0) {
            this.stack.pushStackFrame(context.getStack().currentStackFrame().copyFrame());
        }
    }

    /**
     * Constrcutor
     * 
     * @param simuComModel
     *            the SimuCom model.
     * @param simProcess
     *            the sim process of this context, means the process in which this context is used
     */
    public InterpreterDefaultContext(final SimuComModel simuComModel, final SimuComSimProcess simProcess) {
        this(simuComModel);
        this.setSimProcess(simProcess);
    }

    public InterpreterDefaultContext(final SimuComModel simuComModel) {
        super(simuComModel);
        this.stack = new SimulatedStack<Object>();
        this.eventHelper = new EventNotificationHelper();
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

    public EventNotificationHelper getEventNotificationHelper() {
        return this.eventHelper;
    }
}

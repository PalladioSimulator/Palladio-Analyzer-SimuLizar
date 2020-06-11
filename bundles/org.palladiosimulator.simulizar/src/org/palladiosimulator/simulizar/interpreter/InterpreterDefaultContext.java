package org.palladiosimulator.simulizar.interpreter;

import java.util.Stack;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.simucomframework.Context;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
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

    private final PCMPartitionManager pcmPartitionManager;

    private PCMResourceSetPartition localPCMModelCopy;

    private IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> assemblyAllocationLookup;

    InterpreterDefaultContext(final PCMPartitionManager pcm, SimuComModel myModel,
            IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> assemblyAllocationLookup) {
        super(myModel);
        this.stack = new SimulatedStack<Object>();
        this.pcmPartitionManager = pcm;
        this.localPCMModelCopy = this.pcmPartitionManager.getLocalPCMModel();
        this.assemblyAllocationLookup = assemblyAllocationLookup;
    }

    InterpreterDefaultContext(final Context context, final boolean copyStack, 
            final PCMResourceSetPartition pcmLocalCopy, final PCMPartitionManager pcm) {
        super(context.getModel());
        this.assemblyAllocationLookup = context.getAssemblyAllocationLookup();
        this.pcmPartitionManager = pcm.makeSnapshot();
        this.localPCMModelCopy = pcmLocalCopy;
        this.setEvaluationMode(context.getEvaluationMode());
        this.setSimProcess(context.getThread());
        this.stack = new SimulatedStack<Object>();
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
        this(context, true, context.getPCMPartitionManager().getLocalPCMModel(), context.getPCMPartitionManager());
        this.setSimProcess(thread);
    }

    
    public Stack<AssemblyContext> getAssemblyContextStack() {
        return this.assemblyContextStack;
    }

    public PCMPartitionManager getPCMPartitionManager() {
        return this.pcmPartitionManager;
    }
    
    public PCMResourceSetPartition getLocalPCMModelAtContextCreation() {
        return this.localPCMModelCopy;
    }

    @Override
    public IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> getAssemblyAllocationLookup() {
        return this.assemblyAllocationLookup;
    };
}

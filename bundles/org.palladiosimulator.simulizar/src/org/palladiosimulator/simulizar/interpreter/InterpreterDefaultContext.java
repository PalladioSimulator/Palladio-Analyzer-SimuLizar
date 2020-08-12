package org.palladiosimulator.simulizar.interpreter;

import java.util.Optional;
import java.util.Stack;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.simulizar.reliability.FailureStackFrame;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.simucomframework.Context;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
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

    private final AbstractSimuLizarRuntimeState runtimeState;

    private final PCMPartitionManager pcmPartitionManager;

	private PCMResourceSetPartition localPCMModelCopy;
	
	private Stack<FailureStackFrame<?>> failureStack;

    private IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> assemblyAllocationLookup;

    public InterpreterDefaultContext(final AbstractSimuLizarRuntimeState simulizarModel, 
            IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> assemblyAllocationLookup) {
        super(simulizarModel.getModel());
        this.stack = new SimulatedStack<Object>();
        this.runtimeState = simulizarModel;

        this.pcmPartitionManager = this.runtimeState.getPCMPartitionManager();
        this.localPCMModelCopy = this.pcmPartitionManager.getLocalPCMModel();
        this.failureStack = new Stack<>();
        this.assemblyAllocationLookup = assemblyAllocationLookup;
    }

    InterpreterDefaultContext(final Context context, final AbstractSimuLizarRuntimeState runtimeState,
            final boolean copyStack, final PCMResourceSetPartition pcmLocalCopy) {
        super(context.getModel());
        this.assemblyAllocationLookup = context.getAssemblyAllocationLookup();
        this.pcmPartitionManager = runtimeState.getPCMPartitionManager().makeSnapshot();
        this.localPCMModelCopy = pcmLocalCopy;
        this.setEvaluationMode(context.getEvaluationMode());
        this.setSimProcess(context.getThread());
        this.stack = new SimulatedStack<Object>();
        this.runtimeState = runtimeState;
        this.failureStack = new Stack<>();
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
        this(context, context.getRuntimeState(), true, context.getPCMPartitionManager().getLocalPCMModel());
        this.setSimProcess(thread);
    }

    public AbstractSimuLizarRuntimeState getRuntimeState() {
        return this.runtimeState;
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

    
    public void raiseFailure(FailureStackFrame<?> failure) {
    	failureStack.push(failure);
    }
    
    public Optional<FailureStackFrame<?>> peekFailure() {
    	if(failureStack.isEmpty()) {
    		return Optional.empty();
    	} else {
        	return Optional.of(failureStack.peek());    		
    	}
    }
    
    public Optional<FailureStackFrame<?>> popFailure() {
    	if(failureStack.isEmpty()) {
    		return Optional.empty();
    	} else {
        	return Optional.of(failureStack.pop());    		
    	}
    }
    
    public boolean hasFailureOccurred() {
    	return !failureStack.isEmpty();
    }

}

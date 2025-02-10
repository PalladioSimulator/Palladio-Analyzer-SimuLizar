package org.palladiosimulator.simulizar.interpreter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import jakarta.inject.Inject;
import jakarta.inject.Qualifier;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.simulizar.runtimestate.FQComponentID;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
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
     * This annotation is used as a {@link Qualifier} to identify an
     * {@link InterpreterDefaultContext} as the root context of a simulation.
     * 
     * The annotation should be used to signal to the employed dependency injection framework that a
     * parameter of type {@link InterpreterDefaultContext} of an {@link Inject}-annotated
     * constructor should be supplied with the simulation root context.
     */
    @Qualifier
    @Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MainContext {
    }

    /**
     * This annotation is used as a {@link Qualifier} to identify an
     * {@link InterpreterDefaultContext} as the parent of a context of a simulation.
     * 
     * The annotation should be used to signal to the employed dependency injection framework that a
     * parameter of type {@link InterpreterDefaultContext} of an {@link Inject}-annotated
     * constructor should be supplied with the current parent context.
     */
    @Qualifier
    @Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ParentContext {
    }

    private static final long serialVersionUID = -5027373777424401211L;

    private final Stack<AssemblyContext> assemblyContextStack = new Stack<AssemblyContext>();
    private final Deque<SimulatedStackframe<Object>> resultFrameStack = new LinkedList<>();
    private final PCMResourceSetPartition localPCMModelCopy;
    private final IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> assemblyAllocationLookup;

    protected InterpreterDefaultContext(
            final SimuComModel simuComModel, 
            PCMResourceSetPartition localPartition,
            IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> assemblyAllocationLookup,
            IResourceTableManager resourceTableManager,
            SimulatedStack<Object> stack) {
        super(simuComModel, resourceTableManager);
        this.stack = stack;
        this.localPCMModelCopy = localPartition;
        this.assemblyAllocationLookup = assemblyAllocationLookup;      
    }

    public Stack<AssemblyContext> getAssemblyContextStack() {
        return this.assemblyContextStack;
    }

    /**
     * Returns the local pcm model which was valid once the context was created.
     * 
     * @deprecated use {@link #getLocalPCMModel()}, which was renamed, as the context which
     *             should be used for interpretation, is not necessarily reflected by the model at
     *             context creation (e. g. in case of the root context)
     */
    @Deprecated
    public PCMResourceSetPartition getLocalPCMModelAtContextCreation() {
        return this.getLocalPCMModel();
    }
    

    /**
     * Returns the local pcm model, which should be used for interpretation in the current and child contexts.
     */
    public PCMResourceSetPartition getLocalPCMModel() {
        return localPCMModelCopy;
    }
    
    @Override
    public IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> getAssemblyAllocationLookup() {
        return this.assemblyAllocationLookup;
    };

    public FQComponentID computeFQComponentID() {
        return new FQComponentID(this.computeAssemblyContextPath());
    }

    private List<AssemblyContext> computeAssemblyContextPath() {
        final Stack<AssemblyContext> stack = getAssemblyContextStack();
        final ArrayList<AssemblyContext> result = new ArrayList<AssemblyContext>(stack.size() - 1);
        for (int i = 1; i < stack.size(); i++) {
            result.add(stack.get(i));
        }
        return result;
    }

    public Deque<SimulatedStackframe<Object>> getResultFrameStack() {
        return resultFrameStack;
    }

    public SimulatedStackframe<Object> getCurrentResultFrame() {
        return resultFrameStack.peek();
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
    public static InterpreterDefaultContext createChildContext(InterpreterDefaultContext parentContext, SimuComSimProcess simProcess) {
        var stackCopy = new SimulatedStack<Object>();
        if (parentContext.getStack()
            .size() > 0) {
            stackCopy.pushStackFrame(parentContext.getStack()
                .currentStackFrame()
                .copyFrame());
        } else {
            stackCopy.pushStackFrame(new SimulatedStackframe<Object>());
        }

        var context = new InterpreterDefaultContext(
                parentContext.getModel(),
                parentContext.localPCMModelCopy,
                parentContext.assemblyAllocationLookup,
                parentContext.getResourceTableManager(),
                stackCopy);

        context.assemblyContextStack.addAll(parentContext.getAssemblyContextStack());
        
        if (simProcess != null) context.setSimProcess(simProcess);
        context.setEvaluationMode(parentContext.getEvaluationMode());
        
        return context;
    }
}

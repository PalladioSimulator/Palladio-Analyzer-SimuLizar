package org.palladiosimulator.simulizar.interpreter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.inject.Inject;
import javax.inject.Qualifier;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.runtimestate.FQComponentID;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.Context;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
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
    
    
    /**
    *
    */
    private static final long serialVersionUID = -5027373777424401211L;

    private final Stack<AssemblyContext> assemblyContextStack = new Stack<AssemblyContext>();

    private final PCMPartitionManager pcmPartitionManager;

    private PCMResourceSetPartition localPCMModelCopy;

    private final IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> assemblyAllocationLookup;

    public InterpreterDefaultContext(final SimuComModel simuComModel, PCMPartitionManager partitionManager,
            IAssemblyAllocationLookup<EntityReference<ResourceContainer>> assemblyAllocationLookup,
            ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> simRCAccess,
            IResourceTableManager resourceTableManager) {
        super(simuComModel, resourceTableManager);
        this.stack = new SimulatedStack<Object>();
        this.pcmPartitionManager = partitionManager;
        this.localPCMModelCopy = this.pcmPartitionManager.getLocalPCMModel();
        this.assemblyAllocationLookup = id -> simRCAccess
            .getSimulatedEntity(assemblyAllocationLookup.getAllocatedEntity(id)
                .getId());
    }

    InterpreterDefaultContext(final Context context, PCMPartitionManager partitionManager, final boolean copyStack,
            final PCMResourceSetPartition pcmLocalCopy) {
        super(context.getModel(), context.getResourceTableManager());
        this.assemblyAllocationLookup = context.getAssemblyAllocationLookup();
        this.pcmPartitionManager = partitionManager.makeSnapshot();
        this.localPCMModelCopy = pcmLocalCopy;
        this.setEvaluationMode(context.getEvaluationMode());
        this.setSimProcess(context.getThread());
        this.stack = new SimulatedStack<Object>();
        if (copyStack && context.getStack()
            .size() > 0) {
            this.stack.pushStackFrame(context.getStack()
                .currentStackFrame()
                .copyFrame());
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
        this(context, context.getPCMPartitionManager(), true, context.getPCMPartitionManager()
            .getLocalPCMModel());
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
    
}

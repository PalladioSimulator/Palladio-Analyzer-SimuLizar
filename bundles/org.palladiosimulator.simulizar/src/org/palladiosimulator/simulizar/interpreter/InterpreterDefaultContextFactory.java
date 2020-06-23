package org.palladiosimulator.simulizar.interpreter;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.simucomframework.Context;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;

/**
 * Factory for InterpreterDefaultContext
 * @author Jens Manig
 *
 */
public enum InterpreterDefaultContextFactory implements InterpreterContextFactory{
    Factory;
    @Override
    public InterpreterDefaultContext create(final PCMPartitionManager pcm, 
            SimuComModel myModel, IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> assemblyAllocationLookup) {
        return new InterpreterDefaultContext(pcm, myModel,  assemblyAllocationLookup);
    }
    @Override
    public InterpreterDefaultContext create(final Context context, 
            final boolean copyStack, final PCMResourceSetPartition pcmLocalCopy, final PCMPartitionManager pcm) {
        return new InterpreterDefaultContext(context, copyStack, pcmLocalCopy, pcm);
    }
    @Override
    public InterpreterDefaultContext create(final InterpreterDefaultContext context, final SimuComSimProcess thread) {
        return new InterpreterDefaultContext(context, thread);
    }
}

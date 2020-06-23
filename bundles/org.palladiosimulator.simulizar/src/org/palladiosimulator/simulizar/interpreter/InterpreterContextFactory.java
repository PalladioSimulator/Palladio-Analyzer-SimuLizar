package org.palladiosimulator.simulizar.interpreter;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
/**
 * Factory interface for InterpreterDefaultContextFactory
 * @author Jens Manig
 */
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.simucomframework.Context;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;

public interface InterpreterContextFactory {
    public InterpreterDefaultContext create(final PCMPartitionManager pcm,
            SimuComModel myModel, IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> assemblyAllocationLookup);

    public  InterpreterDefaultContext create(final Context context, 
            final boolean copyStack, final PCMResourceSetPartition pcmLocalCopy, final PCMPartitionManager pcm);
    
    public  InterpreterDefaultContext create(final InterpreterDefaultContext context, final SimuComSimProcess thread); 
}
package org.palladiosimulator.simulizar.interpreter;

import de.uka.ipd.sdq.simucomframework.Context;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;

public interface InterpreterContextFactory {
    public InterpreterDefaultContext create(
            SimuComModel myModel, IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> assemblyAllocationLookup);

    public  InterpreterDefaultContext create(final Context context, final boolean copyStack);
    
    public  InterpreterDefaultContext create(final InterpreterDefaultContext context, final SimuComSimProcess thread); 
}
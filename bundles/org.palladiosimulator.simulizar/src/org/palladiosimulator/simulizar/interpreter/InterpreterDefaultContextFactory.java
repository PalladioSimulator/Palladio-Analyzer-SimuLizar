package org.palladiosimulator.simulizar.interpreter;

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
    public InterpreterDefaultContext create( 
            SimuComModel myModel, IAssemblyAllocationLookup<AbstractSimulatedResourceContainer> assemblyAllocationLookup) {
        return new InterpreterDefaultContext(myModel,  assemblyAllocationLookup);
    }
    @Override
    public InterpreterDefaultContext create(final Context context, 
            final boolean copyStack) {
        return new InterpreterDefaultContext(context, copyStack);
    }
    @Override
    public InterpreterDefaultContext create(final InterpreterDefaultContext context, final SimuComSimProcess thread) {
        return new InterpreterDefaultContext(context, thread);
    }
}

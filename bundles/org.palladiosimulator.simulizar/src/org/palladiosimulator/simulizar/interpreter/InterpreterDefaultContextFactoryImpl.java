package org.palladiosimulator.simulizar.interpreter;

import de.uka.ipd.sdq.simucomframework.Context;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

/**
 * Factory implementation for InterpreterDefaultContext
 * @author Jens Manig
 *
 */
public class InterpreterDefaultContextFactoryImpl implements InterpreterDefaultContextFactory{
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

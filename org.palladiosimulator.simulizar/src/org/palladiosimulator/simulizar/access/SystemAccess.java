package org.palladiosimulator.simulizar.access;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.utils.PCMModels;

import de.uka.ipd.sdq.pcm.system.System;

/**
 * Access class for system model.
 * 
 * @author Joachim Meyer
 */
public class SystemAccess extends AbstractPCMModelAccess<System> {
    /**
     * Constructor
     * 
     * @param context
     *            the interpreter default context.
     * @param modelHelper
     *            the model helper.
     */
    public SystemAccess(final InterpreterDefaultContext context, final ModelHelper modelHelper) {
        super(context, modelHelper);
    }

    @Override
    protected System getSpecificModel(final PCMModels models) {
        return models.getSystem();
    }
}

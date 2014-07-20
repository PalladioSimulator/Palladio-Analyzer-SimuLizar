package org.palladiosimulator.simulizar.access;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

import de.uka.ipd.sdq.pcm.allocation.Allocation;

/**
 * Access class for allocation model.
 * 
 * @author Joachim Meyer
 */
public class AllocationAccess extends AbstractPCMModelAccess<Allocation> {

    /**
     * Constructor
     * 
     * @param context
     *            the interpreter default context.
     * @param modelHelper
     *            the model helper.
     */
    public AllocationAccess(final InterpreterDefaultContext context, final ModelHelper modelHelper) {
        super(context, modelHelper);
    }

    @Override
    protected Allocation getSpecificModel(final PCMModels models) {
        return models.getAllocation();
    }

}

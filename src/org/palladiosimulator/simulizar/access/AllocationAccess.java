package org.palladiosimulator.simulizar.access;

import org.palladiosimulator.simulizar.exceptions.PCMModelAccessException;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.utils.PCMModels;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;

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

    /**
     * Gets the allocation context of the given assembly context.
     * 
     * @param assemblyContext
     *            the assembly context.
     * @return the allocation context.
     */
    public AllocationContext getAllocationContext(final AssemblyContext assemblyContext) {
        for (final AllocationContext allocationContext : this.getModel().getAllocationContexts_Allocation()) {
            if (allocationContext.getAssemblyContext_AllocationContext().getId().equals(assemblyContext.getId())) {
                return allocationContext;
            }
        }
        throw new PCMModelAccessException("No AllocationContext found for AssemblyContext: " + assemblyContext);
    }

    @Override
    protected Allocation getSpecificModel(final PCMModels models) {
        return models.getAllocation();
    }

}

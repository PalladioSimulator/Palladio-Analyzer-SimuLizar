package de.upb.pcm.interpreter.access;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.allocation.AllocationContext;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.upb.pcm.interpreter.exceptions.PCMModelAccessException;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.utils.PCMModels;

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
	public AllocationAccess(final InterpreterDefaultContext context,
			final ModelHelper modelHelper) {
		super(context, modelHelper);
	}

	/**
	 * Gets the allocation context of the given assembly context.
	 * 
	 * @param assemblyContext
	 *            the assembly context.
	 * @return the allocation context.
	 */
	public AllocationContext getAllocationContext(
			final AssemblyContext assemblyContext) {
		for (final AllocationContext allocationContext : this.getModel()
				.getAllocationContexts_Allocation()) {
			if (allocationContext.getAssemblyContext_AllocationContext()
					.equals(assemblyContext)) {
				return allocationContext;
			}
		}
		throw new PCMModelAccessException(
				"No AllocationContext found for AssemblyContext: "
						+ assemblyContext);
	}

	@Override
	protected Allocation getSpecificModel(PCMModels models) {
		return models.getAllocation();
	}

}

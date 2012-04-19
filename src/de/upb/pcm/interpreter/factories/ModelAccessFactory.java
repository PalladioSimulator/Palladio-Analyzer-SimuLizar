package de.upb.pcm.interpreter.factories;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.upb.pcm.interpreter.access.AllocationAccess;
import de.upb.pcm.interpreter.access.PMSAccess;
import de.upb.pcm.interpreter.access.PRMAccess;
import de.upb.pcm.interpreter.access.RepositoryAccess;
import de.upb.pcm.interpreter.access.SystemAccess;
import de.upb.pcm.interpreter.access.UsageModelAccess;
import de.upb.pcm.interpreter.interfaces.IModelAccessFactory;
import de.upb.pcm.interpreter.interpreter.RDSeffInterpreter;
import de.upb.pcm.interpreter.interpreter.RepositoryInterpreter;
import de.upb.pcm.interpreter.interpreter.UsageModelUsageScenarioInterpreter;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.utils.ModelHelper;

/**
 * Factory for pcm and pms model accesses and pcm model interpreters.
 * 
 * @author Joachim Meyer
 * 
 */
public class ModelAccessFactory implements IModelAccessFactory {
	private final ModelHelper modelHelper;

	/**
	 * Constructor
	 * 
	 * @param modelHelper
	 *            the model helper.
	 */
	public ModelAccessFactory(final ModelHelper modelHelper) {
		super();
		this.modelHelper = modelHelper;
	}

	@Override
	public AllocationAccess getAllocationAccess(
			final InterpreterDefaultContext context) {
		return new AllocationAccess(context, getModelHelper());
	}

	@Override
	public SystemAccess getSystemAccess(final InterpreterDefaultContext context) {
		return new SystemAccess(context, getModelHelper());
	}

	/**
	 * 
	 * @see de.upb.pcm.interpreter.interfaces.IModelAccessFactory#getPCMModelAccess(int,
	 *      de.upb.pcm.interpreter.simulation.InterpreterDefaultContext)
	 */
	@Override
	public UsageModelAccess getUsageModelAccess(
			final InterpreterDefaultContext context) {
		return new UsageModelAccess(context, getModelHelper());
	}

	@Override
	public RDSeffInterpreter getRDSEFFInterpreter(final InterpreterDefaultContext context, final AssemblyContext assemblyContext) {
		if (context == null)
			throw new IllegalArgumentException("Interpreter context must not be null");
		return new RDSeffInterpreter(this, context, assemblyContext);
	}

	@Override
	public RepositoryInterpreter getRepositoryInterpreter(final InterpreterDefaultContext context) {
		if (context == null)
			throw new IllegalArgumentException("Interpreter context must not be null");
		return new RepositoryInterpreter(this, context);
	}

	/**
	 * 
	 * @see de.upb.pcm.interpreter.interfaces.IModelAccessFactory#getPCMModelInterpreter(int,
	 *      de.upb.pcm.interpreter.simulation.InterpreterDefaultContext,
	 *      de.uka.ipd.sdq.pcm.core.composition.AssemblyContext)
	 */
	@Override
	public UsageModelUsageScenarioInterpreter getUsageModelScenarioInterpreter(final InterpreterDefaultContext context) {
		if (context == null)
			throw new IllegalArgumentException("Interpreter context must not be null");
		return new UsageModelUsageScenarioInterpreter(this,context);
	}

	/**
	 * 
	 * @see de.upb.pcm.interpreter.interfaces.IModelAccessFactory#getPMSModelAccess()
	 */
	@Override
	public PMSAccess getPMSModelAccess() {
		return new PMSAccess(getModelHelper());
	}

	@Override
	public PRMAccess getPRMModelAccess() {
		return new PRMAccess(getModelHelper());
	}
	
	/**
	 * @return the modelHelper.
	 */
	private ModelHelper getModelHelper() {
		return this.modelHelper;
	}

	@Override
	public RepositoryAccess getRepositoryAccess(
			InterpreterDefaultContext context) {
		return new RepositoryAccess(context, getModelHelper());
	}

}

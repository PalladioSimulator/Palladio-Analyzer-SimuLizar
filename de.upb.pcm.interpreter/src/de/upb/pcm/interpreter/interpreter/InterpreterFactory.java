package de.upb.pcm.interpreter.interpreter;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.upb.pcm.interpreter.access.IModelAccessFactory;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;

public class InterpreterFactory implements IInterpreterFactory {
	private final IModelAccessFactory modelAccessFactory;
	
	public InterpreterFactory(IModelAccessFactory modelAccessFactory) {
		super();
		this.modelAccessFactory = modelAccessFactory;
	}
	
	@Override
	public RDSeffInterpreter getRDSEFFInterpreter(final InterpreterDefaultContext context, final AssemblyContext assemblyContext) {
		if (context == null)
			throw new IllegalArgumentException("Interpreter context must not be null");
		return new RDSeffInterpreter(this,modelAccessFactory, context, assemblyContext);
	}

	@Override
	public RepositoryInterpreter getRepositoryInterpreter(final InterpreterDefaultContext context) {
		if (context == null)
			throw new IllegalArgumentException("Interpreter context must not be null");
		return new RepositoryInterpreter(this,modelAccessFactory, context);
	}

	/**
	 * 
	 * @see de.upb.pcm.interpreter.access.IModelAccessFactory#getPCMModelInterpreter(int,
	 *      de.upb.pcm.interpreter.simulation.InterpreterDefaultContext,
	 *      de.uka.ipd.sdq.pcm.core.composition.AssemblyContext)
	 */
	@Override
	public UsageModelUsageScenarioInterpreter getUsageModelScenarioInterpreter(final InterpreterDefaultContext context) {
		if (context == null)
			throw new IllegalArgumentException("Interpreter context must not be null");
		return new UsageModelUsageScenarioInterpreter(this,modelAccessFactory,context);
	}
}

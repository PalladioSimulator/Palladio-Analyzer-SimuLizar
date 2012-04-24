package de.upb.pcm.interpreter.switches;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyConnector;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.Connector;
import de.uka.ipd.sdq.pcm.core.composition.RequiredDelegationConnector;
import de.uka.ipd.sdq.pcm.core.composition.util.CompositionSwitch;
import de.uka.ipd.sdq.pcm.repository.OperationSignature;
import de.uka.ipd.sdq.pcm.repository.RequiredRole;
import de.upb.pcm.interpreter.exceptions.PCMModelInterpreterException;
import de.upb.pcm.interpreter.interpreter.IInterpreterFactory;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;

public class ComposedStructureInnerSwitch<T> extends CompositionSwitch<T> {
	protected static final Logger logger = Logger
			.getLogger(ProvidedDelegationSwitch.class.getName());

	private final InterpreterDefaultContext context;
	private final IInterpreterFactory interpreterFactory;
	private final OperationSignature operationSignature;
	private final RequiredRole requiredRole;

	/**
	 * Constructor
	 * 
	 * @param modelInterpreter
	 *            the corresponding pcm model interpreter holding this switch..
	 */
	public ComposedStructureInnerSwitch(
			final InterpreterDefaultContext context,
			final IInterpreterFactory interpreterFactory,
			final OperationSignature operationSignature, 
			final RequiredRole requiredRole) {
		super();
		this.context = context;
		this.interpreterFactory = interpreterFactory;
		this.operationSignature = operationSignature;
		this.requiredRole = requiredRole;
	}

	@Override
	public T caseAssemblyConnector(AssemblyConnector assemblyConnector) {
		RepositoryComponentSwitch<T> repositoryComponentSwitch = 
				new RepositoryComponentSwitch<T>(
						context, 
						interpreterFactory, 
						assemblyConnector.getProvidingAssemblyContext_AssemblyConnector(), 
						this.operationSignature, 
						assemblyConnector.getProvidedRole_AssemblyConnector());
		repositoryComponentSwitch.doSwitch(assemblyConnector.getProvidedRole_AssemblyConnector());
		return super.caseAssemblyConnector(assemblyConnector);
	}
	
	@Override
	public T caseRequiredDelegationConnector(RequiredDelegationConnector requiredDelegationConnector) {
		AssemblyContext parentContext = this.context.getAssemblyContextStack().pop();
		ComposedStructureInnerSwitch<T> composedStructureInnerSwitch = 
				new ComposedStructureInnerSwitch<T>(
						context, 
						interpreterFactory, 
						operationSignature, 
						requiredDelegationConnector.getOuterRequiredRole_RequiredDelegationConnector());
		composedStructureInnerSwitch.doSwitch(parentContext);
		this.context.getAssemblyContextStack().push(parentContext);
		return super.caseRequiredDelegationConnector(requiredDelegationConnector);
	}

	@Override
	public T caseAssemblyContext(AssemblyContext assemblyContext) {
		Connector connector = getConnectedConnector(assemblyContext, requiredRole);
		this.doSwitch(connector);
		return super.caseAssemblyContext(assemblyContext);
	}
	
	

	/**
	 * Determines the assembly connector which is connected with the required
	 * role.
	 * 
	 * @param requiredRole
	 *            the required role.
	 * @return the determined assembly connector, null otherwise.
	 */
	private static Connector getConnectedConnector(
			final AssemblyContext myContext,
			final RequiredRole requiredRole) {
		if (myContext == null)
			throw new IllegalArgumentException("Assembly context must not be null");
		if (requiredRole == null)
			throw new IllegalArgumentException("Required role must not be null");
		final CompositionSwitch<Connector> connectorSelector = new CompositionSwitch<Connector>(){

			@Override
			public Connector caseRequiredDelegationConnector(RequiredDelegationConnector delegationConnector) {
				if (delegationConnector.getAssemblyContext_RequiredDelegationConnector() == myContext &&
					delegationConnector.getInnerRequiredRole_RequiredDelegationConnector() == requiredRole)
					return delegationConnector;
				return null;
			}

			@Override
			public Connector caseAssemblyConnector(AssemblyConnector assemblyConnector) {
				if (assemblyConnector.getRequiringAssemblyContext_AssemblyConnector() == myContext && 
					assemblyConnector.getRequiredRole_AssemblyConnector() == requiredRole)
					return assemblyConnector;
				return null;
			} 
			
		};
		for (final Connector connector : myContext.getParentStructure__AssemblyContext().getConnectors__ComposedStructure()) {
			Connector result = connectorSelector.doSwitch(connector);
			if (result != null)
				return result;
		}
		throw new PCMModelInterpreterException("Found unbound provided role. PCM model is invalid.");
	}	
}

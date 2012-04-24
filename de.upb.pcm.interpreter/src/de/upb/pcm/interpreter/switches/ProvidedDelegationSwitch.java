package de.upb.pcm.interpreter.switches;


import org.apache.log4j.Logger;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.ProvidedDelegationConnector;
import de.uka.ipd.sdq.pcm.core.composition.util.CompositionSwitch;
import de.uka.ipd.sdq.pcm.repository.OperationSignature;
import de.uka.ipd.sdq.pcm.repository.ProvidedRole;
import de.upb.pcm.interpreter.interpreter.IInterpreterFactory;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;


/**
 * Switch for Repository Model
 * 
 * @author Joachim Meyer
 * 
 * @param <T> return type of switch methods.
 */
public class ProvidedDelegationSwitch<T> 
	extends CompositionSwitch<T> 
{
   protected static final Logger logger = Logger.getLogger(ProvidedDelegationSwitch.class.getName());

   private final InterpreterDefaultContext context;
   private final IInterpreterFactory interpreterFactory;
   private final OperationSignature operationSignature;
   private final ProvidedRole providedRole;
   
   /**
    * Constructor
    * 
    * @param modelInterpreter the corresponding pcm model interpreter holding this switch..
    */
   public ProvidedDelegationSwitch(final InterpreterDefaultContext context,
		   final IInterpreterFactory interpreterFactory,
		   OperationSignature operationSignature,
		   ProvidedRole providedRole)
   {
      super();
      this.context = context;
      this.interpreterFactory = interpreterFactory;
      this.operationSignature = operationSignature;
      this.providedRole = providedRole;
   }

	@Override
	public T caseProvidedDelegationConnector(ProvidedDelegationConnector delegationConnector) {
		RepositoryComponentSwitch<T> repositoryComponentSwitch = 
				new RepositoryComponentSwitch<T>(context, 
						interpreterFactory, 
						delegationConnector.getAssemblyContext_ProvidedDelegationConnector(), 
						operationSignature, 
						delegationConnector.getInnerProvidedRole_ProvidedDelegationConnector());
		repositoryComponentSwitch.doSwitch(delegationConnector.getInnerProvidedRole_ProvidedDelegationConnector());
		return super.caseProvidedDelegationConnector(delegationConnector);
	}
}

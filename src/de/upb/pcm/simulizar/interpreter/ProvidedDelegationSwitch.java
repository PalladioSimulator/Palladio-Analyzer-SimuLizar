package de.upb.pcm.simulizar.interpreter;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.pcm.core.composition.ProvidedDelegationConnector;
import de.uka.ipd.sdq.pcm.core.composition.util.CompositionSwitch;
import de.uka.ipd.sdq.pcm.repository.OperationSignature;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;
import de.upb.pcm.simulizar.access.IModelAccessFactory;

/**
 * Switch for Repository Model
 * 
 * @author Joachim Meyer
 * 
 * @param <T> return type of switch methods.
 */
class ProvidedDelegationSwitch
	extends CompositionSwitch<SimulatedStackframe<Object>> 
{
   protected static final Logger logger = Logger.getLogger(ProvidedDelegationSwitch.class.getName());

   private final InterpreterDefaultContext context;
   private final IModelAccessFactory modelAccessFactory;
   private final OperationSignature operationSignature;
   
   /**
    * Constructor
    * 
    * @param modelInterpreter the corresponding pcm model interpreter holding this switch..
    */
   public ProvidedDelegationSwitch(final InterpreterDefaultContext context,
		   final IModelAccessFactory interpreterFactory,
		   OperationSignature operationSignature)
   {
      super();
      this.context = context;
      this.modelAccessFactory = interpreterFactory;
      this.operationSignature = operationSignature;
   }

	@Override
	public SimulatedStackframe<Object> caseProvidedDelegationConnector(ProvidedDelegationConnector delegationConnector) {
		RepositoryComponentSwitch repositoryComponentSwitch = 
				new RepositoryComponentSwitch(context, 
						modelAccessFactory, 
						delegationConnector.getAssemblyContext_ProvidedDelegationConnector(), 
						operationSignature, 
						delegationConnector.getInnerProvidedRole_ProvidedDelegationConnector());
		return repositoryComponentSwitch.doSwitch(delegationConnector.getInnerProvidedRole_ProvidedDelegationConnector());
	}
}

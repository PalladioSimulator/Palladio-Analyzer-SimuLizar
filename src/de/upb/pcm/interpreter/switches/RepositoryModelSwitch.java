package de.upb.pcm.interpreter.switches;


import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyConnector;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.ProvidedDelegationConnector;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.OperationSignature;
import de.uka.ipd.sdq.pcm.repository.ProvidedRole;
import de.uka.ipd.sdq.pcm.repository.RequiredRole;
import de.uka.ipd.sdq.pcm.repository.util.RepositorySwitch;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingSEFF;
import de.uka.ipd.sdq.pcm.seff.ServiceEffectSpecification;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;
import de.upb.pcm.interpreter.access.IModelAccessFactory;
import de.upb.pcm.interpreter.access.SystemAccess;
import de.upb.pcm.interpreter.exceptions.PCMModelInterpreterException;
import de.upb.pcm.interpreter.interpreter.IInterpreterFactory;
import de.upb.pcm.interpreter.interpreter.RDSeffInterpreter;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.simulation.InterpreterSimulatedStack;
import de.upb.pcm.interpreter.utils.InterpreterLogger;


/**
 * Switch for Repository Model
 * 
 * @author Joachim Meyer
 * 
 * @param <T> return type of switch methods.
 */
public class RepositoryModelSwitch<T> extends RepositorySwitch<T> implements IPCMModelSwitch<T>
{

   /**
    * TODO: WTF???
    */
   private static final int NUMBER_OF_STACKFRAMES = 4;

   protected static final Logger logger = Logger.getLogger(RepositoryModelSwitch.class.getName());

   private AssemblyContext calledAssemblyContext;
   private final InterpreterDefaultContext context;
   private final IInterpreterFactory interpreterFactory;
   private final OperationSignature operationSignature;
   private final SystemAccess systemAccess;

   /**
    * Constructor
    * 
    * @param modelInterpreter the corresponding pcm model interpreter holding this switch..
    */
   public RepositoryModelSwitch(final InterpreterDefaultContext context,
		   final IInterpreterFactory interpreterFactory,
		   final IModelAccessFactory modelAccessFactory,
		   OperationSignature operationSignature)
   {
      super();
      this.context = context;
      this.interpreterFactory = interpreterFactory;
      this.systemAccess = modelAccessFactory.getSystemAccess(context);
      this.operationSignature = operationSignature;
   }


   /**
    * @see de.uka.ipd.sdq.pcm.repository.util.RepositorySwitch#caseBasicComponent(de.uka.ipd.sdq.pcm.repository.BasicComponent)
    */
   @Override
   public T caseBasicComponent(final BasicComponent object)
   {
      // create new stack frame for component parameters
      final InterpreterSimulatedStack stack = context.getStack();
      final SimulatedStackframe<Object> componentParameterStackFrame = stack.createAndPushNewStackFrame(
            object.getComponentParameterUsage_ImplementationComponentType(), stack.currentStackFrame());

      // create new stack frame for assembly context component parameters
      stack.createAndPushNewStackFrame(calledAssemblyContext.getConfigParameterUsages__AssemblyContext(),
            componentParameterStackFrame);

      // get seffs for call
      final List<ServiceEffectSpecification> calledSeffs = getSeffsForCall(
            object.getServiceEffectSpecifications__BasicComponent(), this.operationSignature);

      interpretSeffs(calledSeffs);

      /*
       * Remove created stack frame (including stack frame created for the results of an external
       * call in RDSEFF, done in RDSEFF Interpreter).
       */

      for (int i = 0; i < NUMBER_OF_STACKFRAMES; i++)
      {
         InterpreterLogger.debug(logger, "Remove stack frame: " + stack.currentStackFrame());
         stack.removeStackFrame();
      }

      return super.caseBasicComponent(object);


   }


   /**
    * @see de.uka.ipd.sdq.pcm.repository.util.RepositorySwitch#caseProvidedRole(de.uka.ipd.sdq.pcm.repository.ProvidedRole)
    */
   @Override
   public T caseProvidedRole(final ProvidedRole object)
   {
     final ProvidedDelegationConnector connectedProvidedDelegationConnector = systemAccess
            .getConnectedProvidedDelegationConnector(object);

      // determine the called assembly context
      calledAssemblyContext = connectedProvidedDelegationConnector.getAssemblyContext_ProvidedDelegationConnector();


      this.doSwitch(calledAssemblyContext.getEncapsulatedComponent__AssemblyContext());

      return super.caseProvidedRole(object);
   }


   /**
    * @see de.uka.ipd.sdq.pcm.repository.util.RepositorySwitch#caseRequiredRole(de.uka.ipd.sdq.pcm.repository.RequiredRole)
    */
   @Override
   public T caseRequiredRole(final RequiredRole object)
   {
      final AssemblyConnector assemblyConnector = systemAccess.getConnectedAssemblyConnector(object);

      // determine the called assembly context
      calledAssemblyContext = assemblyConnector.getProvidingAssemblyContext_AssemblyConnector();


      this.doSwitch(calledAssemblyContext.getEncapsulatedComponent__AssemblyContext());
      return super.caseRequiredRole(object);
   }

   /**
    * Return the seffs (of different types) from a list of seffs which are affected by the call.
    * Different types are currently not supported by the meta model.
    * 
    * @param serviceEffectSpecifications a list of service effect specifications.
    * @param operationSignature the operation signature.
    * @return a list of seffs corresponding to the operation signature.
    */
   private List<ServiceEffectSpecification> getSeffsForCall(
         final EList<ServiceEffectSpecification> serviceEffectSpecifications,
         final OperationSignature operationSignature)
   {
      final List<ServiceEffectSpecification> seffs = new Vector<ServiceEffectSpecification>();

      for (final ServiceEffectSpecification serviceEffectSpecification : serviceEffectSpecifications)
      {
         if (serviceEffectSpecification.getDescribedService__SEFF().equals(operationSignature))
         {
            seffs.add(serviceEffectSpecification);
         }
      }
      return seffs;
   }


   /**
    * Interpret the given Seffs.
    * 
    * @param calledSeffs a list of seffs.
    */
   private void interpretSeffs(final List<ServiceEffectSpecification> calledSeffs)
   {
      /*
       * we assume exactly one seff per call, the meta model also allows no seffs, but we omit that
       * in this interpreter
       */
      if (calledSeffs.size() != 1)
      {
         throw new PCMModelInterpreterException("Only exactly one SEFF is currently supported.");
      }
      if (!(calledSeffs.get(0) instanceof ResourceDemandingSEFF))
      {
         throw new PCMModelInterpreterException("Only ResourceDemandingSEFFs are currently supported.");
      }
      else
      {
         for (final ServiceEffectSpecification serviceEffectSpecification : calledSeffs)
         {

            final RDSeffInterpreter rdSeffInterpreter = interpreterFactory.getRDSEFFInterpreter(
                  context, calledAssemblyContext);

            // interpret called seff
            rdSeffInterpreter.interpret(serviceEffectSpecification);
         }
      }
   }

}

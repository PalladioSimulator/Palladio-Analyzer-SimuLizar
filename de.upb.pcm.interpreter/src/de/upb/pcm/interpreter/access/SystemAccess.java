package de.upb.pcm.interpreter.access;


import de.uka.ipd.sdq.pcm.core.composition.AssemblyConnector;
import de.uka.ipd.sdq.pcm.core.composition.ProvidedDelegationConnector;
import de.uka.ipd.sdq.pcm.repository.ProvidedRole;
import de.uka.ipd.sdq.pcm.repository.RequiredRole;
import de.uka.ipd.sdq.pcm.system.System;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.utils.ModelHelper;


/**
 * Access class for system model.
 * 
 * @author Joachim Meyer
 */
public class SystemAccess extends AbstractPCMModelAccess
{


   /**
    * Constructor
    * 
    * @param context the interpreter default context.
    * @param modelHelper the model helper.
    */
   public SystemAccess(final InterpreterDefaultContext context, final ModelHelper modelHelper)
   {
      super(context, modelHelper);
   }


   /**
    * Determines the assembly connector which is connected with the required role.
    * 
    * @param requiredRole the required role.
    * @return the determined assembly connector, null otherwise.
    */
   public AssemblyConnector getConnectedAssemblyConnector(final RequiredRole requiredRole)
   {
      for (final AssemblyConnector assemblyConnector : getModel().getAssemblyConnectors_ComposedStructure())
      {
         if (assemblyConnector.getRequiredRole_AssemblyConnector().equals(requiredRole))
         {
            return assemblyConnector;
         }
      }
      return null;
   }


   /**
    * Determines the provided delegation connector which is connected with the provided role.
    * 
    * @param providedRole the provided role.
    * @return the determined provided delegation connector, null otherwise.
    */
   public ProvidedDelegationConnector getConnectedProvidedDelegationConnector(final ProvidedRole providedRole)
   {
      for (final ProvidedDelegationConnector providedDelegationConnector : getModel()
            .getProvidedDelegationConnectors_ComposedStructure())
      {
         if (providedDelegationConnector.getOuterProvidedRole_ProvidedDelegationConnector().equals(providedRole))
         {
            return providedDelegationConnector;
         }
      }
      return null;
   }


   /**
    * @return the system model.
    */
   @Override
   public System getModel()
   {

      if (this.getSimProcess() != null)
      {
         return getModelHelper().getLocalPCMModels(this.getSimProcess()).getSystem();
      }
      return getModelHelper().getGlobalPCMModels().getSystem();
   }

}

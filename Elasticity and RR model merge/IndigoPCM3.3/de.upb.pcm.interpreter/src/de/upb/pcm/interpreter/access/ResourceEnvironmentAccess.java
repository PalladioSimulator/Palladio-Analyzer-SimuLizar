package de.upb.pcm.interpreter.access;


import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.utils.ModelHelper;


/**
 * Access class for resource environment model.
 * 
 * @author Joachim Meyer
 */
public class ResourceEnvironmentAccess extends AbstractPCMModelAccess
{

   /**
    * Constructor
    * 
    * @param context the interpreter default context.
    * @param modelHelper the model helper.
    */
   public ResourceEnvironmentAccess(final InterpreterDefaultContext context, final ModelHelper modelHelper)
   {
      super(context, modelHelper);
   }


   /**
    * @return the resource environment model.
    */
   @Override
   public ResourceEnvironment getModel()
   {
      if (this.getSimProcess() != null)
      {
         return getModelHelper().getLocalPCMModels(this.getSimProcess()).getResourceEnvironment();
      }
      return getModelHelper().getGlobalPCMModels().getResourceEnvironment();
   }


}

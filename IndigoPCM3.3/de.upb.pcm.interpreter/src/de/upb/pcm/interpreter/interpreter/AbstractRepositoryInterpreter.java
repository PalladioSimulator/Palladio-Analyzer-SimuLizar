/**
 * 
 */
package de.upb.pcm.interpreter.interpreter;


import de.uka.ipd.sdq.pcm.repository.Repository;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.utils.ModelHelper;


/**
 * Abstract interpreter base class for repository and rdseff interpreter.
 * 
 * @author Joachim Meyer
 */
public abstract class AbstractRepositoryInterpreter extends AbstractPCMModelInterpreter
{


   /**
    * Constructor
    * 
    * @param contex the interpreter default context for the pcm model interpreter, may be null.
    * @param modelHelper the model helper.
    */
   public AbstractRepositoryInterpreter(final InterpreterDefaultContext context, final ModelHelper modelHelper)
   {
      super(context, modelHelper);
   }


   /**
    * @see de.upb.pcm.interpreter.access.AbstractPCMModelAccess#getModel()
    */
   @Override
   public Repository getModel()
   {
      if (this.getSimProcess() != null)
      {
         return getModelHelper().getLocalPCMModels(this.getSimProcess()).getRepository();
      }
      return getModelHelper().getGlobalPCMModels().getRepository();
   }

}

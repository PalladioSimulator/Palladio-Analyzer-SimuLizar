package de.upb.pcm.interpreter.access;


import de.upb.pcm.interpreter.utils.ModelHelper;
import de.upb.pcm.prm.PRMModel;


/**
 * Access class for pms model.
 * 
 * @author Joachim Meyer
 */
public class PRMAccess extends AbstractModelAccess<PRMModel>
{
   /**
    * Constructor
    * 
    * @param modelHelper the model helper.
    */
   public PRMAccess(final ModelHelper modelHelper)
   {
      super(modelHelper);
   }

   /**
    * @return the prm model.
    */
   @Override
   public PRMModel getModel()
   {
      return getModelHelper().getGlobalPRMModel();
   }
}

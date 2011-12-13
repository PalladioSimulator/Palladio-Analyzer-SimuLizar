/**
 * 
 */
package de.upb.pcm.interpreter.access;


import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import de.upb.pcm.interpreter.utils.ModelHelper;


/**
 * Abstract base class for pms model access classes.
 * 
 * @author Joachim Meyer
 * 
 */
public abstract class AbstractPMSModelAccess
{

   protected static final Logger logger = Logger.getLogger(AbstractPMSModelAccess.class.getName());


   private final ModelHelper modelHelper;


   /**
    * Constructor
    * 
    * @param modelHelper the model helper.
    */
   public AbstractPMSModelAccess(final ModelHelper modelHelper)
   {
      this.modelHelper = modelHelper;

   }


   /**
    * Gets the model to be accessed by this class. If sim process is set, this method return a local
    * copy of the corresponding model, otherwise the global model.
    * 
    * @return returns the model to be accessed by this class.
    */
   public abstract EObject getModel();


   /**
    * @return returns the modelHelper.
    */
   public ModelHelper getModelHelper()
   {
      return this.modelHelper;
   }


}

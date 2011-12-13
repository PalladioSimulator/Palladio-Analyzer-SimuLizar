package de.upb.pcm.interpreter.access;


import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.simucomframework.abstractSimEngine.SimProcess;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.utils.ModelHelper;


/**
 * Abstract base class for model access classes.
 * 
 * @author Joachim Meyer
 * 
 */
public abstract class AbstractPCMModelAccess
{

   protected static final Logger logger = Logger.getLogger(AbstractPCMModelAccess.class.getName());

   private InterpreterDefaultContext context;

   private final ModelHelper modelHelper;


   /**
    * Constructor
    * 
    * @param context the interpreter default context for this model access class
    * @param modelHelper the model helper
    */
   public AbstractPCMModelAccess(final InterpreterDefaultContext context, final ModelHelper modelHelper)
   {
      super();

      this.modelHelper = modelHelper;
      this.context = context;
   }


   /**
    * @return returns the context, may be null
    */
   public InterpreterDefaultContext getContext()
   {
      return this.context;
   }


   /**
    * Gets the model to be accessed by this class. If sim process is set, this method return a local
    * copy of the corresponding model, otherwise the global model.
    * 
    * @return returns the model to be accessed by this class.
    */
   protected abstract EObject getModel();


   /**
    * @return returns the modelHelper.
    */
   public ModelHelper getModelHelper()
   {
      return this.modelHelper;
   }


   /**
    * Gets the sim process for this model access class. Not every model access class is executed
    * within a sim process. In this case, methods return null.
    * 
    * @return returns the simProcess, may be null
    */
   public SimProcess getSimProcess()
   {
      if (this.context != null)
      {
         return this.context.getThread();
      }
      return null;
   }


   public void setContext(final InterpreterDefaultContext context)
   {
      this.context = context;
   }


}

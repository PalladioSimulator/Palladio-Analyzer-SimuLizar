package de.upb.pcm.interpreter.interpreter;


import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.pcm.repository.OperationProvidedRole;
import de.uka.ipd.sdq.pcm.repository.OperationRequiredRole;
import de.uka.ipd.sdq.pcm.repository.OperationSignature;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.upb.pcm.interpreter.exceptions.PCMModelLoadException;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.switches.RepositoryModelSwitch;
import de.upb.pcm.interpreter.utils.InterpreterLogger;
import de.upb.pcm.interpreter.utils.ModelHelper;


/**
 * 
 * Interpreter for repository model.
 * 
 * @author Joachim Meyer
 */
public class RepositoryInterpreter extends AbstractRepositoryInterpreter
{

   /**
    * the operation signature which is passed by a calling rdseff interpreter.
    */
   private OperationSignature operationSignature;


   /**
    * Constructor
    * 
    * @param contex the interpreter default context for the pcm model interpreter, may be null.
    * @param modelHelper the model helper.
    */
   public RepositoryInterpreter(final InterpreterDefaultContext context, final ProbeSpecContext probeSpecContext, final ModelHelper modelHelper)
   {
      super(context, probeSpecContext, modelHelper);
   }


   /**
    * @see de.upb.pcm.interpreter.interpreter.AbstractPCMModelInterpreter#getModelSwitch()
    */
   @Override
   protected RepositoryModelSwitch<Object> getModelSwitch()
   {

      return new RepositoryModelSwitch<Object>(this);
   }


   /**
    * Gets the operation signature which is passed by a calling RDSEFF interpreter in the
    * startInterpretation method (optional element). Used to determine a RDSEFF for a call action.
    * 
    * @return returns the operationSignature.
    */
   public final OperationSignature getOperationSignature()
   {
      return this.operationSignature;
   }


   /**
    * @see de.upb.pcm.interpreter.interpreter.AbstractPCMModelInterpreter#startInterpretation(org.eclipse.emf.ecore.EObject,
    *      de.upb.pcm.interpreter.interfaces.IPCMModelSwitch)
    */
   @Override
   protected void startInterpretation(final EObject startElement, final Object... o)
   {
      InterpreterLogger.debug(logger, "Start interpretation of Repository: " + getModel());
      if (!(startElement instanceof OperationProvidedRole || startElement instanceof OperationRequiredRole || o[0] instanceof OperationSignature))
      {
         throw new PCMModelLoadException(
               "startElement must be of type OperationProvidedRole or OperationRequiredRole. Optional element must be of type OperationSignature.");
      }

      operationSignature = (OperationSignature) o[0];

      getModelSwitch().doSwitch(startElement);
      InterpreterLogger.debug(logger, "Finished interpretation of Repository: " + getModel());
   }


}

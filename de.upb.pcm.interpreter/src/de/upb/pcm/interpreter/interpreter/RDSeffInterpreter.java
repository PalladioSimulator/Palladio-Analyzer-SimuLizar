package de.upb.pcm.interpreter.interpreter;


import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.seff.ServiceEffectSpecification;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.upb.pcm.interpreter.exceptions.PCMModelLoadException;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.switches.RDSeffSwitch;
import de.upb.pcm.interpreter.utils.InterpreterLogger;
import de.upb.pcm.interpreter.utils.ModelHelper;


/**
 * 
 * Interpreter for RDSEFFs.
 * 
 * @author Joachim Meyer
 */
public class RDSeffInterpreter extends AbstractSeffInterpreter
{


   /**
    * Constructor
    * 
    * @param contex the interpreter default context for the pcm model interpreter, may be null.
    * @param modelHelper the model helper.
    * @param assemblyContext the assembly context of the corresponding component.
    */
   public RDSeffInterpreter(final InterpreterDefaultContext context, 
		 final ProbeSpecContext probeSpecContext,  
		 final ModelHelper modelHelper,
         final AssemblyContext assemblyContext)
   {
      super(context, probeSpecContext, modelHelper, assemblyContext);
   }


   /**
    * @see de.upb.pcm.interpreter.interpreter.AbstractPCMModelInterpreter#getModelSwitch()
    */
   @Override
   protected RDSeffSwitch<Object> getModelSwitch()
   {
      return new RDSeffSwitch<Object>(this, getAssemblyContext());
   }


   /**
    * @see de.upb.pcm.interpreter.interpreter.AbstractPCMModelInterpreter#startInterpretation(org.eclipse.emf.ecore.EObject,
    *      de.upb.pcm.interpreter.interfaces.IPCMModelSwitch)
    */
   @Override
   protected void startInterpretation(final EObject startElement, final Object... o)
   {
      InterpreterLogger.debug(logger, "Start interpretation of RDSeff: " + getModel());
      if (!(startElement instanceof ServiceEffectSpecification))
      {
         throw new PCMModelLoadException("startElement must be of type ServiceEffectSpecification.");
      }

      getModelSwitch().doSwitch(startElement);

      /*
       * add result stack frame from RDSEFF on current stack, so that it can be used in a calling
       * rdseff interpreter
       */
      getContext().getStack().pushStackFrame(getModelSwitch().getTemporaryResultStackFrame());

      InterpreterLogger.debug(logger, "Pushed result stack frame on stack: "
            + getModelSwitch().getTemporaryResultStackFrame());

      InterpreterLogger.debug(logger, "Finished interpretation of RDSeff: " + getModel());


   }
}

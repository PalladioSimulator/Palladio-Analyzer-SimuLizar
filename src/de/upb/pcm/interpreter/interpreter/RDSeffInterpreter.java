package de.upb.pcm.interpreter.interpreter;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour;
import de.uka.ipd.sdq.pcm.seff.ServiceEffectSpecification;
import de.upb.pcm.interpreter.access.AbstractPCMModelAccess;
import de.upb.pcm.interpreter.access.IModelAccessFactory;
import de.upb.pcm.interpreter.exceptions.PCMModelLoadException;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.switches.RDSeffSwitch;
import de.upb.pcm.interpreter.utils.InterpreterLogger;

/**
 * 
 * Interpreter for RDSEFFs.
 * 
 * @author Joachim Meyer
 */
public class RDSeffInterpreter extends AbstractPCMModelInterpreter<ResourceDemandingBehaviour,Repository>
{
   /**
	    * The assembly context of the corresponding component.
	    */
	private final AssemblyContext assemblyContext;

	/**
    * Constructor
    * 
    * @param contex the interpreter default context for the pcm model interpreter, may be null.
    * @param modelHelper the model helper.
    * @param assemblyContext the assembly context of the corresponding component.
    */
   public RDSeffInterpreter(
		 final IInterpreterFactory interpreterFactory,
		 final IModelAccessFactory modelAccessFactory,
		 final InterpreterDefaultContext context,
         final AssemblyContext assemblyContext)
   {
      super(interpreterFactory,modelAccessFactory,context);
      this.assemblyContext = assemblyContext;
   }

   /**
    * @see de.upb.pcm.interpreter.interpreter.AbstractPCMModelInterpreter#startInterpretation(org.eclipse.emf.ecore.EObject,
    *      de.upb.pcm.interpreter.interfaces.IPCMModelSwitch)
    */
   @Override
   public void interpret(final ResourceDemandingBehaviour startElement, final Object... o)
   {
      InterpreterLogger.debug(logger, "Start interpretation of RDSeff: " + startElement);
      if (!(startElement instanceof ServiceEffectSpecification))
      {
         throw new PCMModelLoadException("startElement must be of type ServiceEffectSpecification.");
      }

      getModelSwitch().doSwitch(startElement);

      /*
       * add result stack frame from RDSEFF on current stack, so that it can be used in a calling
       * rdseff interpreter
       */
      getModelAccess().getContext().getStack().pushStackFrame(getModelSwitch().getTemporaryResultStackFrame());

      InterpreterLogger.debug(logger, "Pushed result stack frame on stack: "
            + getModelSwitch().getTemporaryResultStackFrame());

      InterpreterLogger.debug(logger, "Finished interpretation of RDSeff: " + startElement);
   }

	@Override
	protected AbstractPCMModelAccess<Repository> createModelAccess(
			IModelAccessFactory modelAccessFactory,
			InterpreterDefaultContext context) {
		return modelAccessFactory.getRepositoryAccess(context);
	}

	@Override
	protected RDSeffSwitch<Object> getModelSwitch() {
		return new RDSeffSwitch<Object>(this.context, interpreterFactory, this.modelAccessFactory, this.getAssemblyContext(), this.pcmInterpreterProbeSpecUtil);
	}

	/**
	    * @return the assemblyContext of the corresponding component.
	    */
	protected AssemblyContext getAssemblyContext() {
	      return this.assemblyContext;
	   }
}

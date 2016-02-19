package de.upb.pcm.interpreter.interfaces;


import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.upb.pcm.interpreter.access.AbstractPCMModelAccess;
import de.upb.pcm.interpreter.access.AbstractPMSModelAccess;
import de.upb.pcm.interpreter.interpreter.AbstractPCMModelInterpreter;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;


/**
 * Model access factory interface for creating pcm and prm model access, as well as pcm model
 * interpreter.
 * 
 * @author Joachim Meyer
 * 
 */
public interface IModelAccessFactory
{


   /**
    * Usage model interpreter for usage scenario.
    */
   public final int USAGEMODEL_USAGESCENARIO_INTERPRETER = 0;

   /**
    * Repository model interpreter.
    */
   public final int REPOSITORY_INTERPRETER = 1;

   /**
    * Resource Demanding Service Effect Specification interpreter.
    */
   public final int RDSEFF_INTERPRETER = 2;

   /**
    * System access.
    */
   public final int SYSTEM_ACCESS = 3;

   /**
    * Resource environment access.
    */
   public final int RESOURCE_ENVIRONMENT_ACCESS = 4;

   /**
    * Allocation access.
    */
   public final int ALLOCATION_ACCESS = 5;

   /**
    * Usage model access.
    */
   public final int USAGE_MODEL_ACCESS = 6;


   /**
    * Gets PCM model access specified by model access type.
    * 
    * @param modelAccessType the model access Type, e.g. IModelAccessFactory.USAGE_MODEL_ACCESS.
    * @param context the interpreter default context for the pcm model access, may be null.
    * @return the pcm model access.
    */
   AbstractPCMModelAccess getPCMModelAccess(int modelAccessType, InterpreterDefaultContext context);


   /**
    * Gets PCM Model interpreter specified by interpreter type.
    * 
    * @param interpreterType the interpreter Type, e.g. IModelAccessFactory.RDSEFF_INTERPRETER.
    * @param context the interpreter default context for the pcm model interpreter, may be null.
    * @param assemblyContext the assembly context. Only necessary for
    *           IModelAccessFactory.RDSEFF_INTERPRETER to specify the assembly context of the
    *           corresponding component.
    * @return the pcm model interpreter.
    */
   AbstractPCMModelInterpreter getPCMModelInterpreter(int interpreterType, InterpreterDefaultContext context,
         AssemblyContext assemblyContext, ProbeSpecContext probeSpecContext);


   /**
    * Gets PMS model access.
    * 
    * @return the pms model access.
    */
   AbstractPMSModelAccess getPMSModelAccess();


}

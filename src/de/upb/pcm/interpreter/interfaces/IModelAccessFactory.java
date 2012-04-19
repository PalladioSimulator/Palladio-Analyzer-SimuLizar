package de.upb.pcm.interpreter.interfaces;


import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.upb.pcm.interpreter.access.AllocationAccess;
import de.upb.pcm.interpreter.access.PMSAccess;
import de.upb.pcm.interpreter.access.PRMAccess;
import de.upb.pcm.interpreter.access.RepositoryAccess;
import de.upb.pcm.interpreter.access.SystemAccess;
import de.upb.pcm.interpreter.access.UsageModelAccess;
import de.upb.pcm.interpreter.interpreter.RDSeffInterpreter;
import de.upb.pcm.interpreter.interpreter.RepositoryInterpreter;
import de.upb.pcm.interpreter.interpreter.UsageModelUsageScenarioInterpreter;
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
    * Gets PCM model access specified by model access type.
    * 
    * @param modelAccessType the model access Type, e.g. IModelAccessFactory.USAGE_MODEL_ACCESS.
    * @param context the interpreter default context for the pcm model access, may be null.
    * @return the pcm model access.
    */
   UsageModelAccess getUsageModelAccess(InterpreterDefaultContext context);
   AllocationAccess getAllocationAccess(InterpreterDefaultContext context);
   SystemAccess     getSystemAccess(InterpreterDefaultContext context);
   RepositoryAccess getRepositoryAccess(InterpreterDefaultContext context);
 
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

	public UsageModelUsageScenarioInterpreter getUsageModelScenarioInterpreter(InterpreterDefaultContext contextê);
	public RDSeffInterpreter getRDSEFFInterpreter(InterpreterDefaultContext context, AssemblyContext assemblyContext);
	public RepositoryInterpreter getRepositoryInterpreter(InterpreterDefaultContext context);
	
   /**
    * Gets PMS model access.
    * 
    * @return the pms model access.
    */
   PMSAccess getPMSModelAccess();
   PRMAccess getPRMModelAccess();
}

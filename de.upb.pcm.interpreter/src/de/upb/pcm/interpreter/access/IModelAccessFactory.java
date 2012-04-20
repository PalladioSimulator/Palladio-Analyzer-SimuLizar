package de.upb.pcm.interpreter.access;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
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
   UsageModelAccess getUsageModelAccess(InterpreterDefaultContext context);

   AllocationAccess getAllocationAccess(InterpreterDefaultContext context);
   
   SystemAccess     getSystemAccess(InterpreterDefaultContext context);
   
   RepositoryAccess getRepositoryAccess(InterpreterDefaultContext context);
   
   GlobalPCMAccess getGlobalPCMAccess();

 
	public UsageModelUsageScenarioInterpreter getUsageModelScenarioInterpreter(InterpreterDefaultContext context);
	public RDSeffInterpreter getRDSEFFInterpreter(InterpreterDefaultContext context, AssemblyContext assemblyContext);
	public RepositoryInterpreter getRepositoryInterpreter(InterpreterDefaultContext context);
	
   /**
    * Gets PMS model access.
    * 
    * @return the pms model access.
    */
   PMSAccess getPMSModelAccess();

   PRMAccess getPRMModelAccess();
   
   SDAccess getSDAccess();
}

package de.upb.pcm.interpreter.launcher.jobs;


import de.uka.ipd.sdq.workflow.OrderPreservingBlackboardCompositeJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.pcm.configurations.AbstractPCMWorkflowRunConfiguration;
import de.uka.ipd.sdq.workflow.pcm.jobs.PreparePCMBlackboardPartionJob;


/**
 * Composite Job for preparing Blackboard and loading PCM Models into it.
 * 
 * @author Joachim Meyer
 * 
 */
public class LoadPCMModelsIntoBlackboardInterpreterJob extends OrderPreservingBlackboardCompositeJob<MDSDBlackboard>
{


   /**
    * @param config
    */
   public LoadPCMModelsIntoBlackboardInterpreterJob(final AbstractPCMWorkflowRunConfiguration config)
   {

      this.add(new PreparePCMBlackboardPartionJob());
      this.add(new LoadPCMModelsInterpreterJob(config));
   }


}

package de.upb.pcm.interpreter.launcher.jobs;


import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.probespec.framework.ISampleBlackboard;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.SampleBlackboard;
import de.uka.ipd.sdq.simucomframework.DiscardInvalidMeasurementsBlackboardDecorator;
import de.uka.ipd.sdq.simucomframework.ExperimentRunner;
import de.uka.ipd.sdq.simucomframework.SimuComGarbageCollector;
import de.uka.ipd.sdq.simucomframework.calculator.CalculatorFactory;
import de.uka.ipd.sdq.simucomframework.calculator.SetupPipesAndFiltersStrategy;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.SimuComProbeStrategyRegistry;
import de.uka.ipd.sdq.simucomframework.simucomstatus.SimuComStatus;
import de.uka.ipd.sdq.simucomframework.simucomstatus.SimucomstatusFactory;
import de.uka.ipd.sdq.workflow.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.exceptions.JobFailedException;
import de.uka.ipd.sdq.workflow.exceptions.RollbackFailedException;
import de.uka.ipd.sdq.workflow.exceptions.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.upb.pcm.interpreter.access.UsageModelAccess;
import de.upb.pcm.interpreter.interfaces.IModelAccessFactory;
import de.upb.pcm.interpreter.ssj.SSJSimEngineFactory;
import de.upb.pcm.interpreter.utils.InterpreterLogger;
import de.upb.pcm.interpreter.utils.ModelHelper;
import de.upb.pcm.prm.PrmFactory;


/**
 * Job starting the pcm interpretation.
 * 
 * @author Joachim Meyer
 * 
 */
public class PCMStartInterpretationJob implements IBlackboardInteractingJob<MDSDBlackboard>
{

   private static final Logger logger = Logger.getLogger(PCMStartInterpretationJob.class.getName());

   private MDSDBlackboard blackboard;

   private SimuComStatus simuComStatus;

   private final SimuComWorkflowConfiguration configuration;


   /**
    * Constructor
    * 
    * @param configuration the SimuCom workflow configuration.
    */
   public PCMStartInterpretationJob(final SimuComWorkflowConfiguration configuration)
   {
      this.configuration = configuration;


   }


   /**
    * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
    */
   @Override
   public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException
   {


      InterpreterLogger.info(logger, "Start job: " + this);
      final SimuComModel simuComModel = new SimuComModel(getConfiguration().getSimuComConfiguration(),
            getSimuComStatus(), new SSJSimEngineFactory(), false);

      setupSampleBlackboard(simuComModel);

      // factories and loaders
      final ModelHelper modelHelper = new ModelHelper(getBlackboard(), simuComModel,
            PrmFactory.eINSTANCE.createPRMModel());

      // create usage model access
      final UsageModelAccess usageModelAccess = (UsageModelAccess) modelHelper.getModelAccessFactory()
            .getPCMModelAccess(IModelAccessFactory.USAGE_MODEL_ACCESS, null);

      getSimuComStatus().setCurrentSimulationTime(0);

      modelHelper.getSimuComModel().setUsageScenarios(usageModelAccess.getWorkloadDrivers());

      InterpreterLogger.debug(logger, "Start usage scenario for each simulated user");
      final double simRealTimeNano = ExperimentRunner.run(modelHelper.getSimuComModel(), modelHelper.getSimuComModel()
            .getConfig().getSimuTime());
      final double simRealTimeSeconds = simRealTimeNano / Math.pow(10, 9);
      InterpreterLogger.debug(logger, "Finished UsageModel. Interpretation took " + simRealTimeSeconds
            + " real time seconds");

      ProbeSpecContext.instance().finish();
      InterpreterLogger.info(logger, "finished job: " + this);


   }


   /**
    * @return returns the blackboard.
    */
   private MDSDBlackboard getBlackboard()
   {
      return this.blackboard;
   }


   /**
    * @return returns the configuration.
    */
   private SimuComWorkflowConfiguration getConfiguration()
   {
      return this.configuration;
   }


   /**
    * @see de.uka.ipd.sdq.workflow.IJob#getName()
    */
   @Override
   public String getName()
   {
      return "Perform PCM interpreter";
   }


   /**
    * Gets the SimuCom status, creates one if none exists.
    * 
    * @return the SimuCom status.
    */
   public SimuComStatus getSimuComStatus()
   {
      if (this.simuComStatus == null)
      {
         this.simuComStatus = SimucomstatusFactory.eINSTANCE.createSimuComStatus();
         this.simuComStatus.setProcessStatus(SimucomstatusFactory.eINSTANCE.createSimulatedProcesses());
         this.simuComStatus.setResourceStatus(SimucomstatusFactory.eINSTANCE.createSimulatedResources());

      }
      return this.simuComStatus;
   }


   /**
    * @see de.uka.ipd.sdq.workflow.IJob#rollback(org.eclipse.core.runtime.IProgressMonitor)
    */
   @Override
   public void rollback(final IProgressMonitor monitor) throws RollbackFailedException
   {


   }


   /**
    * @see de.uka.ipd.sdq.workflow.IBlackboardInteractingJob#setBlackboard(de.uka.ipd.sdq.workflow.Blackboard)
    */
   @Override
   public void setBlackboard(final MDSDBlackboard blackboard)
   {
      this.blackboard = blackboard;

   }


   /**
    * Sets SampleBlackboard instead of concurrency sample blackboard.
    * 
    * @param simuComModel the SimuCom model.
    */
   private void setupSampleBlackboard(final SimuComModel simuComModel)
   {
      final ISampleBlackboard sampleBlackboard = new DiscardInvalidMeasurementsBlackboardDecorator(
            new SampleBlackboard(), simuComModel.getSimulationControl());
      ProbeSpecContext.instance().configure(sampleBlackboard, new SimuComGarbageCollector(sampleBlackboard),
            new SimuComProbeStrategyRegistry(),
            new CalculatorFactory(sampleBlackboard, simuComModel, new SetupPipesAndFiltersStrategy(simuComModel)));
   }


}

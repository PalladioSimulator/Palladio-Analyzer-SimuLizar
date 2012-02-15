package de.upb.pcm.interpreter.access;


import de.uka.ipd.sdq.pcm.usagemodel.ClosedWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.OpenWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.pcm.usagemodel.Workload;
import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;
import de.uka.ipd.sdq.simucomframework.usage.IUserFactory;
import de.uka.ipd.sdq.simucomframework.usage.IWorkloadDriver;
import de.upb.pcm.interpreter.factories.ClosedWorkloadScenarioUserFactory;
import de.upb.pcm.interpreter.factories.OpenWorkloadScenarioUserFactory;
import de.upb.pcm.interpreter.interfaces.IModelAccessFactory;
import de.upb.pcm.interpreter.interpreter.AbstractPCMModelInterpreter;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.utils.InterpreterLogger;
import de.upb.pcm.interpreter.utils.ModelHelper;


/**
 * Access class for usage model.
 * 
 * Note: This class is actually not a model access class, it needs to be an interpreter, to avoid
 * "instance of" for the workloads.
 * 
 * @author Joachim Meyer
 */
public class UsageModelAccess extends AbstractPCMModelAccess
{

   /**
    * Constructor
    * 
    * @param context the interpreter default context.
    * @param modelHelper the model helper.
    */
   public UsageModelAccess(final InterpreterDefaultContext context, final ModelHelper modelHelper)
   {
      super(context, modelHelper);
   }


   private IWorkloadDriver getClosedWorkloadDriver(final Workload workload)
   {
      InterpreterLogger.debug(logger, "Create workload driver for ClosedWorkload: " + workload);
      final ClosedWorkload closedWorkload = (ClosedWorkload) workload;


      final IUserFactory userFactory = new ClosedWorkloadScenarioUserFactory(this, closedWorkload
            .getThinkTime_ClosedWorkload().getSpecification())
      {
         @Override
         public IScenarioRunner createScenarioRunner()
         {
            // create scenario interpreter
            final AbstractPCMModelInterpreter scenarioInterpreter = getModelHelper().getModelAccessFactory()
                  .getPCMModelInterpreter(IModelAccessFactory.USAGEMODEL_USAGESCENARIO_INTERPRETER, null, null);
            return (IScenarioRunner) scenarioInterpreter;

         }
      };
      // create workload driver by using given factory
      return new de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload(userFactory, closedWorkload.getPopulation(),closedWorkload.getUsageScenario_Workload().getId());
   }


   /**
    * @return global usage model.
    */
   @Override
   protected UsageModel getModel()
   {
      return getModelHelper().getGlobalPCMModels().getUsageModel();
   }


   private IWorkloadDriver getOpenWorkloadDriver(final Workload workload)
   {
      InterpreterLogger.debug(logger, "Create workload driver for OpenWorkload: " + workload);
      final OpenWorkload openWorkload = (OpenWorkload) workload;

      final IUserFactory userFactory = new OpenWorkloadScenarioUserFactory(this)
      {
         @Override
         public IScenarioRunner createScenarioRunner()
         {
            final AbstractPCMModelInterpreter scenarioInterpreter = getModelHelper().getModelAccessFactory()
                  .getPCMModelInterpreter(IModelAccessFactory.USAGEMODEL_USAGESCENARIO_INTERPRETER, null, null);
            return (IScenarioRunner) scenarioInterpreter;
         }
      };

      // create workload driver by using given factory
      return new de.uka.ipd.sdq.simucomframework.usage.OpenWorkload(getModelHelper().getSimuComModel(), userFactory,
            openWorkload.getInterArrivalTime_OpenWorkload().getSpecification(),openWorkload.getUsageScenario_Workload().getId());
   }


   private IWorkloadDriver getWorkloadDriver(final UsageScenario usageScenario)
   {

      // get workload of scenario
      final Workload workload = usageScenario.getWorkload_UsageScenario();

      // determine if workload is open or closed
      if (workload instanceof ClosedWorkload)
      {
         return getClosedWorkloadDriver(workload);

      }
      else if (workload instanceof OpenWorkload)
      {
         return getOpenWorkloadDriver(workload);
      }
      else
      {
         return null;
      }

   }


   /**
    * Gets workload drivers for the usage scenarios in the usage model. Currently only one usage
    * scenario is supported.
    * 
    * @return a list currently containing only one workload driver.
    */
   public IWorkloadDriver[] getWorkloadDrivers()
   {
      // TODO only one usage scenario supported
      return new IWorkloadDriver[] { getWorkloadDriver(getModel().getUsageScenario_UsageModel().get(0)) };
   }
}

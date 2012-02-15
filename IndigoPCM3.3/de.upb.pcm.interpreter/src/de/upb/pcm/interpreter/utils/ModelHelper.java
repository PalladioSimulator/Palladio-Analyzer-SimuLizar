package de.upb.pcm.interpreter.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.storydriven.modeling.activities.Activity;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;
import de.upb.pcm.interpreter.factories.ModelAccessFactory;
import de.upb.pcm.interpreter.interfaces.IModelAccessFactory;
import de.upb.pcm.interpreter.launcher.jobs.LoadPMSModelIntoBlackboardJob;
import de.upb.pcm.interpreter.launcher.jobs.LoadSDMModelsIntoBlackboardJob;
import de.upb.pcm.interpreter.launcher.partitions.PMSResourceSetPartition;
import de.upb.pcm.interpreter.launcher.partitions.SDMResourceSetPartition;
import de.upb.pcm.interpreter.sdinterpreter.SDExecutor;
import de.upb.pcm.pms.PMSModel;
import de.upb.pcm.prm.PRMModel;


/**
 * 
 * Helper to access the pcm model (global and local), the prm model, the pms model and all sdm
 * models.
 * 
 * @author Joachim Meyer
 */

public class ModelHelper
{

   protected static final Logger logger = Logger.getLogger(ModelHelper.class.getName());

   private final Map<SimuComSimProcess, PCMModels> modelCopies;

   private final Map<SimuComSimProcess, Long> sessionIds;

   private final MDSDBlackboard blackboard;


   private final IModelAccessFactory modelReaderFactory;

   private final SimuComModel simuComModel;

   private final ResourceSyncer resourceSyncer;

   private final PRMModel prmModel;

   private SDExecutor sdExecutor;


   /**
    * Constructor
    * 
    * @param blackboard the blackboard holding the models.
    * @param simuComModel the SimuCom model.
    * @param prmModel the prm model.
    */
   public ModelHelper(final MDSDBlackboard blackboard, final SimuComModel simuComModel, final PRMModel prmModel)
   {
      super();
      this.modelCopies = new HashMap<SimuComSimProcess, PCMModels>();
      this.sessionIds = new HashMap<SimuComSimProcess, Long>();
      this.blackboard = blackboard;

      this.modelReaderFactory = new ModelAccessFactory(this);
      this.simuComModel = simuComModel;
      this.resourceSyncer = new ResourceSyncer(simuComModel, this);

      this.prmModel = prmModel;


      /*
       * Sync Resources from global pcm model with simucom model for the first time, models are
       * already loaded into the blackboard by the workflow engine
       */
      getResourceSyncer().syncResourceEnvironment();
   }


   /**
    * 
    * @return a copy of the global pcm models.
    */
   private PCMModels copyGlobalPCMModels()
   {
      // add to list
      final PCMModels globalPCMModels = getGlobalPCMModels();

      final List<EObject> globalPCMModelsList = new ArrayList<EObject>();
      globalPCMModelsList.add(globalPCMModels.getAllocation());
      globalPCMModelsList.add(globalPCMModels.getRepository());
      globalPCMModelsList.add(globalPCMModels.getResourceEnvironment());
      globalPCMModelsList.add(globalPCMModels.getSystem());
      globalPCMModelsList.add(globalPCMModels.getUsageModel());

      final List<EObject> modelCopyCollection = (ArrayList<EObject>) EcoreUtil.copyAll(globalPCMModelsList);

      // add to PCMCopy
      final PCMModels pcmCopy = new PCMModels();
      pcmCopy.setAllocation((Allocation) modelCopyCollection.get(0));
      pcmCopy.setRepository((Repository) modelCopyCollection.get(1));
      pcmCopy.setResourceEnvironment((ResourceEnvironment) modelCopyCollection.get(2));
      pcmCopy.setSystem((de.uka.ipd.sdq.pcm.system.System) modelCopyCollection.get(3));
      pcmCopy.setUsageModel((UsageModel) modelCopyCollection.get(4));

      return pcmCopy;
   }


   /**
    * @return returns the blackboard.
    */
   private MDSDBlackboard getBlackboard()
   {
      return this.blackboard;
   }


   /**
    * 
    * @return the global pcm models.
    */
   public PCMModels getGlobalPCMModels()
   {
      final PCMResourceSetPartition pcmResourceSetPartition = getPCMResourceSetPartition();

      // add to PCMCopy
      final PCMModels pcmCopy = new PCMModels();
      pcmCopy.setAllocation(pcmResourceSetPartition.getAllocation());
      pcmCopy.setRepository(pcmResourceSetPartition.getRepositories().get(1));
      pcmCopy.setResourceEnvironment(pcmResourceSetPartition.getResourceEnvironment());
      pcmCopy.setSystem(pcmResourceSetPartition.getSystem());
      pcmCopy.setUsageModel(pcmResourceSetPartition.getUsageModel());

      return pcmCopy;
   }


   /**
    * 
    * @return the global pms model.
    */
   public PMSModel getGlobalPMSModel()
   {
      final PMSResourceSetPartition pcmResourceSetPartition = getPMSResourceSetPartition();

      return pcmResourceSetPartition.getPMSModel();

   }


   /**
    * 
    * @return the global prm model.
    */
   public PRMModel getGlobalPRMModel()
   {
      return this.prmModel;

   }


   /**
    * 
    * Returns local pcm models for the given sim process. If none exists a local copy will be
    * created. If simulated user repeats interaction with the system within the sim process, a fresh
    * copy will created.
    * 
    * @param simuComSimProcess the sim process.
    * @return the local pcm models for the sim process.
    */
   public PCMModels getLocalPCMModels(final SimuComSimProcess simuComSimProcess)
   {
      if (getModelCopies().get(simuComSimProcess) == null ||
    		  getSessionIds().get(simuComSimProcess) < simuComSimProcess.getCurrentSessionId())
      {
         getModelCopies().put(simuComSimProcess, copyGlobalPCMModels());
         getSessionIds().put(simuComSimProcess, simuComSimProcess.getCurrentSessionId());
         InterpreterLogger.debug(logger, "Created pcm model copy for sim process: " + simuComSimProcess);
      }
      return this.modelCopies.get(simuComSimProcess);
   }


   /**
    * @return returns the modelReaderFactory.
    */
   public IModelAccessFactory getModelAccessFactory()
   {
      return this.modelReaderFactory;
   }


   /**
    * @return returns the modelCopies.
    */
   private Map<SimuComSimProcess, PCMModels> getModelCopies()
   {
      return this.modelCopies;
   }


   private PCMResourceSetPartition getPCMResourceSetPartition()
   {
      final PCMResourceSetPartition pcmResourceSetPartition = (PCMResourceSetPartition) getBlackboard().getPartition(
            LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);
      return pcmResourceSetPartition;
   }


   private PMSResourceSetPartition getPMSResourceSetPartition()
   {
      final PMSResourceSetPartition pmsResourceSetPartition = (PMSResourceSetPartition) getBlackboard().getPartition(
            LoadPMSModelIntoBlackboardJob.PMS_MODEL_PARTITION_ID);
      return pmsResourceSetPartition;
   }


   /**
    * @return returns the resourceSyncer.
    */
   private ResourceSyncer getResourceSyncer()
   {
      return this.resourceSyncer;
   }


   /**
    * @return returns the sdExecutor.
    */
   public SDExecutor getSDExecutor()
   {
      if (this.sdExecutor == null)
      {
         this.sdExecutor = new SDExecutor(this);
      }
      return this.sdExecutor;
   }


   /**
    * 
    * @return a list of the sdm models.
    */
   public List<Activity> getSDMModels()
   {
      final SDMResourceSetPartition sdmResourceSetPartition = getSDMResourceSetPartition();

      return sdmResourceSetPartition.getActivities();

   }


   private SDMResourceSetPartition getSDMResourceSetPartition()
   {
      final SDMResourceSetPartition sdmResourceSetPartition = (SDMResourceSetPartition) getBlackboard().getPartition(
            LoadSDMModelsIntoBlackboardJob.SDM_MODEL_PARTITION_ID);
      return sdmResourceSetPartition;
   }


   /**
    * @return returns the sessionIds.
    */
   private Map<SimuComSimProcess, Long> getSessionIds()
   {
      return this.sessionIds;
   }


   /**
    * @return returns the simuComModel.
    */
   public SimuComModel getSimuComModel()
   {
      return this.simuComModel;
   }


   /**
    * Sync resource environment in SimuCom from global pcm models.
    * 
    * @return the global pcm models.
    */
   public PCMModels syncResourceEnvironment()
   {
      InterpreterLogger.debug(logger, "Refresh global pcm models");

      getResourceSyncer().syncResourceEnvironment();

      return getGlobalPCMModels();
   }


   /**
    * Checks whether sdm models exists, without using any classes from sd interpreter.
    * 
    * @return true if yes, otherwise false;
    */
   public boolean sdmModelsExists()
   {
      return getSDMResourceSetPartition().getResourceSet().getResources().size() > 0;
   }


   /**
    * Checks whether pms model exists.
    * 
    * @return true if yes, otherwise false;
    */
   public boolean pmsModelExists()
   {
      return getPMSResourceSetPartition().getResourceSet().getResources().size() > 0;
   }


}

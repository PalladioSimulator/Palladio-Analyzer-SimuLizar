package de.upb.pcm.interpreter.access.internal;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.storydriven.storydiagrams.activities.Activity;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;
import de.upb.pcm.interpreter.launcher.jobs.LoadPMSModelIntoBlackboardJob;
import de.upb.pcm.interpreter.launcher.jobs.LoadSDMModelsIntoBlackboardJob;
import de.upb.pcm.interpreter.launcher.partitions.PMSResourceSetPartition;
import de.upb.pcm.interpreter.launcher.partitions.SDMResourceSetPartition;
import de.upb.pcm.interpreter.utils.InterpreterLogger;
import de.upb.pcm.interpreter.utils.PCMModels;
import de.upb.pcm.pms.PMSModel;
import de.upb.pcm.prm.PRMModel;
import de.upb.pcm.prm.PrmFactory;


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

   private final PRMModel prmModel;

   /**
    * Constructor
    * 
    * @param blackboard the blackboard holding the models.
    * @param simuComModel the SimuCom model.
    * @param prmModel the prm model.
    */
   public ModelHelper(final MDSDBlackboard blackboard)
   {
      super();
      this.modelCopies = new HashMap<SimuComSimProcess, PCMModels>();
      this.sessionIds = new HashMap<SimuComSimProcess, Long>();
      this.blackboard = blackboard;
      this.prmModel = PrmFactory.eINSTANCE.createPRMModel();
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
      final PCMModels pcmCopy = new PCMModels(
    	      (Repository) modelCopyCollection.get(1),
    	      (de.uka.ipd.sdq.pcm.system.System) modelCopyCollection.get(3),
    	      (ResourceEnvironment) modelCopyCollection.get(2),
    	      (Allocation) modelCopyCollection.get(0),
    	      (UsageModel) modelCopyCollection.get(4));

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
      final PCMModels pcmCopy = new PCMModels(
    		  pcmResourceSetPartition.getRepositories().get(1),
    		  pcmResourceSetPartition.getSystem(),
    		  pcmResourceSetPartition.getResourceEnvironment(),
    		  pcmResourceSetPartition.getAllocation(),pcmResourceSetPartition.getUsageModel());

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

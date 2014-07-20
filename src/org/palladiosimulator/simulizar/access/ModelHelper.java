package org.palladiosimulator.simulizar.access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.simulizar.launcher.jobs.LoadPMSModelIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.LoadSDMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.partitions.PMSResourceSetPartition;
import org.palladiosimulator.simulizar.launcher.partitions.SDMResourceSetPartition;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmFactory;
import org.storydriven.storydiagrams.activities.Activity;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;

/**
 * 
 * Helper to access the pcm model (global and local), the prm model, the pms model and all SD
 * models.
 * 
 * @author Joachim Meyer, Steffen Becker
 */
public class ModelHelper {

    protected static final Logger LOG = Logger.getLogger(ModelHelper.class.getName());

    /**
     * TODO FIXME This cache is a severe memory leak
     */
    private final Map<SimuComSimProcess, PCMModels> modelCopies;

    private final PCMResourceSetPartition pcmPartition;
    private final PMSResourceSetPartition pmsPartition;
    private final SDMResourceSetPartition sdmPartition;
    private final PRMModel prmModel;

    /**
     * Constructor
     * 
     * @param blackboard
     *            the workflow engine's blackboard holding all models.
     */
    public ModelHelper(final MDSDBlackboard blackboard) {
        super();
        this.modelCopies = new HashMap<SimuComSimProcess, PCMModels>();
        this.prmModel = PrmFactory.eINSTANCE.createPRMModel();
        this.pcmPartition = getResourceSetPartition(blackboard, LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);
        this.sdmPartition = getResourceSetPartition(blackboard, LoadSDMModelsIntoBlackboardJob.SDM_MODEL_PARTITION_ID);
        this.pmsPartition = getResourceSetPartition(blackboard, LoadPMSModelIntoBlackboardJob.PMS_MODEL_PARTITION_ID);
    }

    /**
     * 
     * @return the global pcm models.
     */
    public PCMModels getGlobalPCMModels() {
        return new PCMModels(pcmPartition.getAllocation(), pcmPartition.getUsageModel());
    }

    /**
     * 
     * @return the global pms model.
     */
    public PMSModel getGlobalPMSModel() {
        return pmsPartition.getPMSModel();
    }

    /**
     * 
     * @return the global prm model.
     */
    public PRMModel getGlobalPRMModel() {
        return this.prmModel;
    }

    /**
     * Returns local pcm models for the given sim process. If none exists a local copy will be
     * created. If simulated user repeats interaction with the system within the sim process, a
     * fresh copy will created.
     * 
     * @param simuComSimProcess
     *            the sim process.
     * @return the local pcm models for the sim process.
     */
    public PCMModels getLocalPCMModels(final SimuComSimProcess simuComSimProcess) {
        if (!this.modelCopies.containsKey(simuComSimProcess)) {
            this.modelCopies.put(simuComSimProcess, this.copyGlobalPCMModels());
            if (LOG.isDebugEnabled()) {
                LOG.debug("Created pcm model copy for sim process: " + simuComSimProcess);
            }
        }
        return this.modelCopies.get(simuComSimProcess);
    }

    /**
     * 
     * @return a list of the sdm models.
     */
    public List<Activity> getSDMModels() {
        return sdmPartition.getActivities();
    }

    /**
     * Checks whether sdm models exists, without using any classes from sd interpreter.
     * 
     * @return true if yes, otherwise false;
     */
    public boolean sdmModelsExists() {
        return sdmPartition.getResourceSet().getResources().size() > 0;
    }

    /**
     * Checks whether pms model exists.
     * 
     * @return true if yes, otherwise false;
     */
    public boolean pmsModelExists() {
        return pmsPartition.getResourceSet().getResources().size() > 0;
    }

    @SuppressWarnings("unchecked")
    private <T extends ResourceSetPartition> T getResourceSetPartition(
            final MDSDBlackboard blackboard,
            final String id) {
        return (T) blackboard.getPartition(id);
    }

    /**
     * 
     * @return a copy of the global pcm models.
     */
    private PCMModels copyGlobalPCMModels() {
        // add to list
        final PCMModels globalPCMModels = this.getGlobalPCMModels();

        final List<EObject> globalPCMModelsList = new ArrayList<EObject>();
        globalPCMModelsList.add(globalPCMModels.getAllocation());
        globalPCMModelsList.add(globalPCMModels.getUsageModel());

        final List<EObject> modelCopyCollection = (ArrayList<EObject>) EcoreUtil.copyAll(globalPCMModelsList);

        // add to PCMCopy
        final PCMModels pcmCopy = new PCMModels((Allocation) modelCopyCollection.get(0),
                (UsageModel) modelCopyCollection.get(1));

        return pcmCopy;
    }

    public PCMResourceSetPartition getPCMResourceSetPartition() {
        return this.pcmPartition;
    }

}

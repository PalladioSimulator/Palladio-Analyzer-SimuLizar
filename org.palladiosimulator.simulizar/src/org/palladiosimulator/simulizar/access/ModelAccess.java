package org.palladiosimulator.simulizar.access;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.commons.emfutils.EMFCopyHelper;
import org.palladiosimulator.simulizar.launcher.jobs.LoadMonitorRepositoryModelIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.LoadSDMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.LoadUEModelIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.partitions.PMSResourceSetPartition;
import org.palladiosimulator.simulizar.launcher.partitions.SDMResourceSetPartition;
import org.palladiosimulator.simulizar.launcher.partitions.UEResourceSetPartition;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmFactory;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationListener;
import org.scaledl.usageevolution.UsageEvolution;
import org.storydriven.storydiagrams.activities.Activity;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.jobs.LoadPCMModelsIntoBlackboardJob;

/**
 * Helper to access the PCM model (global and local), the prm model, the pms model, the usage
 * evolution model and all SD models.
 * 
 * @author Joachim Meyer, Steffen Becker, Erlend Stav
 */
public class ModelAccess implements IModelAccess, IReconfigurationListener {

    private static final Logger LOGGER = Logger.getLogger(ModelAccess.class.getName());

    private final Map<SimuComSimProcess, PCMResourceSetPartition> modelCopies = new HashMap<SimuComSimProcess, PCMResourceSetPartition>();
    private final PCMResourceSetPartition pcmPartition;
    private PCMResourceSetPartition currentPCMCopy;
    private final PMSResourceSetPartition pmsPartition;
    private final SDMResourceSetPartition sdmPartition;
    private final UEResourceSetPartition uePartititon;
    private final PRMModel prmModel;

    /**
     * Constructor
     * 
     * @param blackboard
     *            the workflow engine's blackboard holding all models.
     */
    public ModelAccess(final MDSDBlackboard blackboard) {
        super();
        this.prmModel = PrmFactory.eINSTANCE.createPRMModel();
        this.pcmPartition = getResourceSetPartition(blackboard, LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);
        this.sdmPartition = getResourceSetPartition(blackboard, LoadSDMModelsIntoBlackboardJob.SDM_MODEL_PARTITION_ID);
        this.pmsPartition = getResourceSetPartition(blackboard, LoadMonitorRepositoryModelIntoBlackboardJob.MONITOR_REPOSITORY_MODEL_PARTITION_ID);
        this.uePartititon = getResourceSetPartition(blackboard, LoadUEModelIntoBlackboardJob.UE_MODEL_PARTITION_ID);
        this.currentPCMCopy = copyPCMPartition();
    }

    private ModelAccess(final ModelAccess copy) {
        super();
        this.prmModel = copy.prmModel;
        this.pcmPartition = copy.pcmPartition;
        this.sdmPartition = copy.sdmPartition;
        this.pmsPartition = copy.pmsPartition;
        this.uePartititon = copy.uePartititon;
        this.currentPCMCopy = copy.currentPCMCopy;
    }

    @Override
    public IModelAccess clone() {
        return new ModelAccess(this);
    }

    @Override
    public PCMResourceSetPartition getLocalPCMModel() {
        return this.currentPCMCopy;
    }

    /**
     * @return a copy of the global PCM modelling partition
     */
    private PCMResourceSetPartition copyPCMPartition() {
        PCMResourceSetPartition newPartition = new PCMResourceSetPartition();
        List<EObject> modelCopy = EMFCopyHelper.deepCopyToEObjectList(pcmPartition.getResourceSet());
        for (int i = 0; i < modelCopy.size(); i++) {
            Resource resource = newPartition.getResourceSet().createResource(URI.createFileURI("/temp" + i));
            resource.getContents().add(modelCopy.get(i));
        }
        return newPartition;
    }

    @Override
    public PCMResourceSetPartition getGlobalPCMModel() {
        return this.pcmPartition;
    }

    /**
     * 
     * @return the global pms model.
     */
    @Override
    public MonitorRepository getMonitorRepositoryModel() {
        return pmsPartition.getMonitorRepositoryModel();
    }

    /**
     * 
     * @return the global prm model.
     */
    @Override
    public PRMModel getPRMModel() {
        return this.prmModel;
    }

    /**
     * 
     * @return the global usage evolution model, or null if no such model is available
     */
    @Override
    public UsageEvolution getUsageEvolutionModel() {
        if (this.uePartititon == null)
            return null;
        else
            return this.uePartititon.getUsageEvolution();
    }

    /**
     * 
     * @return a list of the sdm models.
     */
    @Override
    public List<Activity> getStoryDiagrams() {
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
    private <T extends ResourceSetPartition> T getResourceSetPartition(final MDSDBlackboard blackboard, final String id) {
        return (T) blackboard.getPartition(id);
    }

    @Override
    public void reconfigurationExecuted(final Collection<Notification> modelChanges) {
        LOGGER.debug("Reconfiguration(s) have been exectuted, taking a new copy of the global PCM for new simulation threads");
        this.currentPCMCopy = copyPCMPartition();
    }

}

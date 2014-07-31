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
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.launcher.jobs.LoadPMSModelIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.LoadSDMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.partitions.PMSResourceSetPartition;
import org.palladiosimulator.simulizar.launcher.partitions.SDMResourceSetPartition;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmFactory;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationListener;
import org.storydriven.storydiagrams.activities.Activity;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimProcess;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimProcessListener;
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
public class ModelAccess implements IModelAccess, IReconfigurationListener {

    protected static final Logger LOG = Logger.getLogger(ModelAccess.class.getName());

    private final Map<SimuComSimProcess, PCMResourceSetPartition> modelCopies = new HashMap<SimuComSimProcess, PCMResourceSetPartition>();
    private final PCMResourceSetPartition pcmPartition;
    private final PCMResourceSetPartition currentPCMCopy;
    private final PMSResourceSetPartition pmsPartition;
    private final SDMResourceSetPartition sdmPartition;
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
        this.pmsPartition = getResourceSetPartition(blackboard, LoadPMSModelIntoBlackboardJob.PMS_MODEL_PARTITION_ID);
        this.currentPCMCopy = copyPCMPartition();
    }

    @Override
    public PCMResourceSetPartition getLocalPCMModel(final InterpreterDefaultContext context) {
        if (context.getThread() != null) {
            SimuComSimProcess process = context.getThread();
            process.addProcessListener(new ISimProcessListener() {
                @Override
                public void notifySuspending(ISimProcess process) {
                }

                @Override
                public void notifyResuming(ISimProcess process) {
                }

                @Override
                public void notifyTerminated(ISimProcess process) {
                    LOG.debug("Terminating process detected, cleaning up its model copy");
                    process.removeProcessListener(this);
                    modelCopies.remove(process);
                }
            });
            if (!modelCopies.containsKey(process)) {
                modelCopies.put(process, this.currentPCMCopy);
            }
            return modelCopies.get(process);
        } else {
            return this.pcmPartition;
        }
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
    public PMSModel getPMSModel() {
        return pmsPartition.getPMSModel();
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
    private <T extends ResourceSetPartition> T getResourceSetPartition(
            final MDSDBlackboard blackboard,
            final String id) {
        return (T) blackboard.getPartition(id);
    }

    @Override
    public void reconfigurationExecuted(Collection<Notification> modelChanges) {
        LOG.debug("Reconfiguration(s) have been exectuted, taking a new copy of the global PCM for new simulation threads");
        copyPCMPartition();
    }

}

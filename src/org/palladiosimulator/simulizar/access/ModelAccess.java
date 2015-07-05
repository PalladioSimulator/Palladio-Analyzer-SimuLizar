package org.palladiosimulator.simulizar.access;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.commons.emfutils.EMFCopyHelper;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationListener;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionPackage;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * Helper to access the PCM model (global and local), the RuntimeMeasurement model, the Monitor
 * Repository model, the usage evolution model and all SD models.
 * 
 * @author Joachim Meyer, Steffen Becker, Erlend Stav, Sebastian Lehrig
 */
public class ModelAccess implements IModelAccess, IReconfigurationListener {

    private static final Logger LOGGER = Logger.getLogger(ModelAccess.class.getName());

    private final PCMResourceSetPartition pcmPartition;
    private PCMResourceSetPartition currentPCMCopy;
    private final RuntimeMeasurementModel runtimeMeasurementModel;
    private final MDSDBlackboard blackboard;

    /**
     * Constructor
     * 
     * @param blackboard
     *            the workflow engine's blackboard holding all models.
     */
    public ModelAccess(final MDSDBlackboard blackboard) {
        super();
        this.blackboard = blackboard;
        this.runtimeMeasurementModel = RuntimeMeasurementFactory.eINSTANCE.createRuntimeMeasurementModel();
        this.pcmPartition = getResourceSetPartition(blackboard, LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);
        this.currentPCMCopy = copyPCMPartition();
    }

    private ModelAccess(final ModelAccess copy) {
        super();
        this.blackboard = copy.blackboard;
        this.runtimeMeasurementModel = copy.runtimeMeasurementModel;
        this.pcmPartition = copy.pcmPartition;
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
        final PCMResourceSetPartition newPartition = new PCMResourceSetPartition();
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
     * @return the global Monitor Repository model.
     */
    @Override
    public MonitorRepository getMonitorRepositoryModel() {
        try {
            LOGGER.debug("Retrieving Monitor Repository model from blackboard partition");
            List<MonitorRepository> result = this.pcmPartition.getElement(MonitorRepositoryPackage.eINSTANCE
                    .getMonitorRepository());
            return result.get(0);
        } catch (Exception e) {
            LOGGER.info("No Monitor Repository model found, so no simulation data will be taken.");
            return null;
        }
    }

    /**
     * @return return the usage evolution element
     */
    public ServiceLevelObjectiveRepository getServiceLevelObjectiveRepositoryModel() {
        try {
            LOGGER.debug("Retrieving Service Level Objective repository from blackboard partition");
            List<ServiceLevelObjectiveRepository> result = this.pcmPartition
                    .getElement(ServicelevelObjectivePackage.eINSTANCE.getServiceLevelObjectiveRepository());
            return result.get(0);
        } catch (Exception e) {
            LOGGER.info("No Service Level Objectives found.");
            return null;
        }
    }

    /**
     * 
     * @return the global RuntimeMeasurement model.
     */
    @Override
    public RuntimeMeasurementModel getRuntimeMeasurementModel() {
        return this.runtimeMeasurementModel;
    }

    /**
     * 
     * @return the global usage evolution model, or null if no such model is available
     */
    @Override
    public UsageEvolution getUsageEvolutionModel() {
        try {
            LOGGER.debug("Retrieving Usage Evolution model from blackboard partition");
            List<UsageEvolution> result = this.pcmPartition.getElement(UsageevolutionPackage.eINSTANCE
                    .getUsageEvolution());
            return result.get(0);
        } catch (Exception e) {
            LOGGER.info("No Usage Evolution model found, so evolution will not be simulated.");
            return null;
        }
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

    @Override
    public void beginReconfigurationEvent(BeginReconfigurationEvent event) {
        // Nothing to do
    }

    @Override
    public void endReconfigurationEvent(EndReconfigurationEvent event) {
        // Nothing to do
    }

    @Override
    public MDSDBlackboard getBlackboard() {
        return this.blackboard;
    }

}

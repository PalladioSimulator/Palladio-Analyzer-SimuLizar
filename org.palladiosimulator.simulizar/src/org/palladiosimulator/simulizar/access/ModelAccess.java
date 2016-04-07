package org.palladiosimulator.simulizar.access;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.commons.emfutils.EMFCopyHelper;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.pcm.PcmPackage;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionPackage;

import de.uka.ipd.sdq.stoex.StoexPackage;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * Helper to access the PCM model (global and local), the RuntimeMeasurement model, the Monitor
 * Repository model, the usage evolution model and all SD models.
 *
 * @author Joachim Meyer, Steffen Becker, Erlend Stav, Sebastian Lehrig
 */
public class ModelAccess implements IModelAccess {

    private static final Logger LOGGER = Logger.getLogger(ModelAccess.class.getName());

    private final PCMResourceSetPartition pcmPartition;
    private final RuntimeMeasurementModel runtimeMeasurementModel;
    private final MDSDBlackboard blackboard;
    private boolean isObservingPcmChanges = false;
    private PCMResourceSetPartition currentPCMCopy;
    private boolean modelsDirty = false;


    
    /**
     * A log listener which logs all changes in the global PCM model.
     */
    private final Adapter globalPCMChangeListener = new EContentAdapter() {

        @Override
        public void notifyChanged(final Notification notification) {
            super.notifyChanged(notification);
            // The models should not be marked as a new local copy if only individual values, and
            // not references (i.e. wiring) has changed.
            if (isPCMModel(notification.getFeature())
                    && notification.getEventType() != Notification.REMOVING_ADAPTER) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Detected change in global PCM model. Changed object: " + notification.getNotifier());
                    LOGGER.debug(notification.toString());
                }
                ModelAccess.this.modelsDirty = true;
            }
        }

        private boolean isPCMModel(Object feature) {
            if(EcorePackage.eINSTANCE.getEObject().isInstance(feature)
                    && EcorePackage.eINSTANCE.getEStructuralFeature().isInstance(feature)){
                EStructuralFeature eStructFeature = (EStructuralFeature) feature;
                EClass containingClass = eStructFeature.getEContainingClass();
                EObject rootContainer = EcoreUtil.getRootContainer(containingClass);
                
                if(PcmPackage.eINSTANCE == rootContainer || StoexPackage.eINSTANCE == rootContainer) {
                    return true;
                }
            }
            return false;
        }

    };

    /**
     * Constructor
     *
     * @param blackboard
     *            the workflow engine's blackboard holding all models.
     */
    public ModelAccess(final MDSDBlackboard blackboard) {
        this.blackboard = blackboard;
        this.runtimeMeasurementModel = RuntimeMeasurementFactory.eINSTANCE.createRuntimeMeasurementModel();
        this.pcmPartition = this.getResourceSetPartition(blackboard,
                LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);
        this.currentPCMCopy = this.copyPCMPartition();
    }

    private ModelAccess(final ModelAccess copy) {
        this.blackboard = copy.blackboard;
        this.runtimeMeasurementModel = copy.runtimeMeasurementModel;
        this.pcmPartition = copy.pcmPartition;
        // make sure that model is consistent before creating copy.
        copy.checkAndHandleDeferredChanges();
        this.currentPCMCopy = copy.currentPCMCopy;
    }

    @Override
    public IModelAccess clone() {
        return new ModelAccess(this);
    }

    @Override
    public PCMResourceSetPartition getLocalPCMModel() {
        checkAndHandleDeferredChanges();
        return this.currentPCMCopy;
    }

    private void checkAndHandleDeferredChanges() {
        if (this.modelsDirty) {
            ModelAccess.this.currentPCMCopy = ModelAccess.this.copyPCMPartition();
            modelsDirty = false;
        }
    }

    /**
     * Calling this method has the current instance observe the {@link PCMResourceSetPartition} in
     * order to keep track of model changes.
     *
     * @see #getGlobalPCMModel()
     * @see #stopObservingPcmChanges()
     */
    public void startObservingPcmChanges() {
        if (!this.isObservingPcmChanges) {
            final List<Adapter> adapters = this.pcmPartition.getResourceSet().eAdapters();
            if (!adapters.contains(this.globalPCMChangeListener)) {
                adapters.add(this.globalPCMChangeListener);
            }
            this.isObservingPcmChanges = true;
        }
    }

    /**
     * Calling this method has the current instance stop observing the
     * {@link PCMResourceSetPartition}.
     *
     * @see #getGlobalPCMModel()
     * @see #startObservingPcmChanges()
     */
    public void stopObservingPcmChanges() {
        if (this.isObservingPcmChanges) {
            this.pcmPartition.getResourceSet().eAdapters().remove(this.globalPCMChangeListener);
            this.isObservingPcmChanges = false;
        }
    }

    /**
     * @return a copy of the global PCM modelling partition
     */
    // FIXME @Igor: Return the access level back to private when ModelAccessUseOriginalReferences is not needed anymore.
    protected PCMResourceSetPartition copyPCMPartition() {
        LOGGER.debug("Take a new copy of the global PCM for new simulation threads");
        final PCMResourceSetPartition newPartition = new PCMResourceSetPartition();
        final List<EObject> modelCopy = EMFCopyHelper.deepCopyToEObjectList(this.pcmPartition.getResourceSet());
        for (int i = 0; i < modelCopy.size(); i++) {
            final Resource resource = newPartition.getResourceSet().createResource(URI.createFileURI("/temp" + i));
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
            final List<MonitorRepository> result = this.pcmPartition
                    .getElement(MonitorRepositoryPackage.eINSTANCE.getMonitorRepository());
            return result.get(0);
        } catch (final Exception e) {
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
            final List<ServiceLevelObjectiveRepository> result = this.pcmPartition
                    .getElement(ServicelevelObjectivePackage.eINSTANCE.getServiceLevelObjectiveRepository());
            return result.get(0);
        } catch (final Exception e) {
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
            final List<UsageEvolution> result = this.pcmPartition
                    .getElement(UsageevolutionPackage.eINSTANCE.getUsageEvolution());
            return result.get(0);
        } catch (final Exception e) {
            LOGGER.info("No Usage Evolution model found, so evolution will not be simulated.");
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends ResourceSetPartition> T getResourceSetPartition(final MDSDBlackboard blackboard,
            final String id) {
        return (T) blackboard.getPartition(id);
    }

    @Override
    public MDSDBlackboard getBlackboard() {
        return this.blackboard;
    }

}

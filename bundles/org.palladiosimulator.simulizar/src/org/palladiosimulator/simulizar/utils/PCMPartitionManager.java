package org.palladiosimulator.simulizar.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Qualifier;

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
import org.palladiosimulator.analyzer.workflow.ConstantsContainer;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.commons.emfutils.EMFCopyHelper;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.pcm.PcmPackage;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.stoex.StoexPackage;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * The class manages all the models that are required during simulation. This includes querying for
 * specific models as well as observing and handling model changes.
 * 
 * @author scheerer
 *
 */
public class PCMPartitionManager {
    /**
     * The Global annotation should be used to reference to the global PCMResourceSet partition for
     * constructor injection.
     * 
     * The global partition should always be used to apply persistent changes to the simulation
     * model, i. e. by reconfigurations. Any model interpretation (i. e. visitor-based interpreters)
     * should not use the global partition, but use the local copy, which guarantees a stable view.
     * 
     */
    @Qualifier
    @Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Global {
    }
    
    /**
     * The Local annotation should be used to reference to the PCMResourceSet partition of the
     * current interpretation scope for constructor injection.
     * 
     * The local partition should always be used for model interpretation (i. e. visitor-based
     * interpreters).
     */
    @Qualifier
    @Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Local {
    }

    private static final Logger LOGGER = Logger.getLogger(PCMPartitionManager.class.getName());
    
    private final static String RM_MODEL_FILE_EXTENSION = ".runtimemeasurement";

    private final PCMResourceSetPartition globalPartition;
    private final MDSDBlackboard blackboard;
    private boolean isObservingPcmChanges = false;
    private PCMResourceSetPartition currentPartition;
    private boolean modelsDirty = false;

    /**
     * A log listener which logs all changes in the global PCM model.
     */
    private final Adapter globalPCMChangeListener = new EContentAdapter() {

        @Override
        public void notifyChanged(final Notification notification) {
            super.notifyChanged(notification);
            // The models should not be marked as a new local copy if only individual
            // values, and
            // not references (i.e. wiring) has changed.
            if (isPCMModel(notification.getFeature()) && notification.getEventType() != Notification.REMOVING_ADAPTER) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Detected change in global PCM model. Changed object: " + notification.getNotifier());
                    LOGGER.debug(notification.toString());
                }
                PCMPartitionManager.this.modelsDirty = true;
            }
        }

        private boolean isPCMModel(Object feature) {
            if (EcorePackage.eINSTANCE.getEObject()
                .isInstance(feature)
                    && EcorePackage.eINSTANCE.getEStructuralFeature()
                        .isInstance(feature)) {
                EStructuralFeature eStructFeature = (EStructuralFeature) feature;
                EClass containingClass = eStructFeature.getEContainingClass();
                EObject rootContainer = EcoreUtil.getRootContainer(containingClass);

                if (PcmPackage.eINSTANCE == rootContainer || StoexPackage.eINSTANCE == rootContainer) {
                    return true;
                }
            }
            return false;
        }

    };

    /**
     * The constructor initializes the blackboard, which is the primary source to manage, makes
     * copies of the current PCM related mode, which may be changed by reconfigurations. Moreover, a
     * runtime measurement model is created and temporarily persisted (this is necessary for keeping
     * it in the blackboard) until simulation is done.
     * 
     * @param blackboard
     *            The workflow engine's blackboard holding all models.
     * @param config
     *            SimuLizar workflow configuration object.
     */
    public PCMPartitionManager(final MDSDBlackboard blackboard, final SimuLizarWorkflowConfiguration config) {
        this.blackboard = blackboard;
        this.globalPartition = (PCMResourceSetPartition) blackboard.getPartition(ConstantsContainer.DEFAULT_PCM_INSTANCE_PARTITION_ID);
        if (globalPartition == null) {
            throw new IllegalStateException("The provided blackboard does not contain the required PCM partition");
        }
        this.currentPartition = this.copyPCMPartition();
    }

    public void initialize() {
        Optional<EObject> result = this.globalPartition.getElement(MonitorRepositoryPackage.Literals.MONITOR_REPOSITORY).stream().findAny();
        if (result.isPresent()) {
            var uri = result.get().eResource().getURI().appendFileExtension(RM_MODEL_FILE_EXTENSION);
            Resource resource = this.globalPartition.getResourceSet().createResource(uri);
            resource.getContents().add(RuntimeMeasurementFactory.eINSTANCE.createRuntimeMeasurementModel());
        } else {
            LOGGER.error("No monitor repository set in global partition.");
        }
    }

    /**
     * @return the global PCM modeling partition. The global PCM model is the primary model under
     *         change, e.g., whenever a reconfiguration is triggered, and is observed during
     *         simulation.
     */
    public PCMResourceSetPartition getGlobalPCMModel() {
        return this.globalPartition;
    }

    /**
     * @return a copy of the global PCM modeling partition. The local PCM model represents an
     *         up-to-date snapshot of the global PCM model that captures the latest changes made in
     *         the global PCM model.
     */
    public PCMResourceSetPartition getLocalPCMModel() {
        checkAndHandleDeferredChanges();
        return this.currentPartition;
    }

    private void checkAndHandleDeferredChanges() {
        if (this.modelsDirty) {
            this.currentPartition = this.copyPCMPartition();
            this.modelsDirty = false;
        }
    }

    /**
     * @return a copy of the global PCM modeling partition
     */
    private PCMResourceSetPartition copyPCMPartition() {
        LOGGER.debug("Take a new copy of the global PCM for new simulation threads");
        final PCMResourceSetPartition newPartition = new PCMResourceSetPartition();
        final List<EObject> modelCopy = EMFCopyHelper.deepCopyToEObjectList(this.globalPartition.getResourceSet());
        for (int i = 0; i < modelCopy.size(); i++) {
            final Resource resource = newPartition.getResourceSet()
                .createResource(URI.createFileURI("/temp" + i));
            resource.getContents()
                .add(modelCopy.get(i));
        }
        return newPartition;
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
            filterResourcesToListen().forEach(resource -> addListenerTo(resource));
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
            filterResourcesToListen().forEach(resource -> removeListenerFrom(resource));
            this.isObservingPcmChanges = false;
        }
    }

    private List<Resource> filterResourcesToListen() {
        Stream<Resource> resources = this.globalPartition.getResourceSet()
            .getResources()
            .stream();
        return resources.filter(resourcesToListen())
            .collect(Collectors.toList());
    }

    private Predicate<Resource> resourcesToListen() {
        return r -> {
            if (r.getContents()
                .isEmpty()) {
                return false;
            }
            return r.getContents()
                .get(0) instanceof RuntimeMeasurementModel ? false : true;
        };
    }

    private void addListenerTo(Resource resource) {
        final List<Adapter> adapters = resource.eAdapters();
        if (adapters.contains(this.globalPCMChangeListener) == false) {
            adapters.add(this.globalPCMChangeListener);
        }
    }

    private void removeListenerFrom(Resource resource) {
        resource.eAdapters()
            .remove(this.globalPCMChangeListener);
    }

    /**
     * Enables to query the blackboard for a specific model that has been stored.
     * 
     * @param targetType
     *            Corresponds to the EClass of the target model to be searched for.
     * @return the model to search for or null if the model was not found.
     */
    public <T extends EObject> T findModel(EClass targetType) {
        List<T> result = this.globalPartition.getElement(targetType);
        if (result.isEmpty()) {
            LOGGER.info(String.format("No model with target type %s was found.", targetType));
            return null;
        }
        return result.get(0);
    }

    /**
     * @return the blackboard that contains the global PCM partition.
     */
    public MDSDBlackboard getBlackboard() {
        return this.blackboard;
    }

}

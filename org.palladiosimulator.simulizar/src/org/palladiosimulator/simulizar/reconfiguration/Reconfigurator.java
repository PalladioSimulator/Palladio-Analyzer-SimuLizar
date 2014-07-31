package org.palladiosimulator.simulizar.reconfiguration;

import java.util.Collection;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.ResourceContainerMeasurement;
import org.palladiosimulator.simulizar.prm.util.PrmSwitch;

import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;

/**
 * Class whose objects will listen on changes in the PCM@Runtime and trigger reconfigurations
 * respectively.
 * 
 * @author snowball
 *
 */
public class Reconfigurator extends AbstractObservable<IReconfigurationListener> {

    /**
     * This class' internal LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(Reconfigurator.class);

    private final Collection<Notification> modelChanges = new LinkedList<Notification>();

    /**
     * Change listener, which will convert selected changes in the PRM instance into reconfiguration
     * checks.
     */
    private final Adapter prmListener = new EContentAdapter() {

        @Override
        public void notifyChanged(final Notification notification) {
            super.notifyChanged(notification);
            modelChanges.clear();
            Reconfigurator.this.checkAndExecuteReconfigurations(notification);
            if (modelChanges.size() > 0) {
                Reconfigurator.this.getEventDispatcher().reconfigurationExecuted(modelChanges);
            }
        }

    };

    /**
     * A log listener which logs all changes in the global PCM model.
     */
    private final Adapter globalPCMChangeListener = new EContentAdapter() {

        @Override
        public void notifyChanged(final Notification notification) {
            super.notifyChanged(notification);
            if (notification.getEventType() != Notification.REMOVING_ADAPTER) {
                modelChanges.add(notification);
                LOGGER.debug("Detected change in global PCM model. Changed object: " + notification.getNotifier());
                LOGGER.debug(notification.toString());
            }
        }

    };

    /**
     * Access interface to the global PCM@Runtime model.
     */
    private final PCMResourceSetPartition pcmResourceSetPartition;

    /**
     * Access interface to the PRM runtime model.
     */
    private final PRMModel prmModel;

    /**
     * Set of all registered reconfigurators, i.e., objects that can change the PCM@Runtime.
     */
    private final IReconfigurator[] reconfigurators;

    /**
     * Constructor.
     * 
     * @param modelAccessFactory
     *            Access factory for model access interfaces.
     * @param reconfigurators
     *            Set of reconfigurators which will be triggered as soon as new, interesting
     *            monitoring data arrives.
     */
    public Reconfigurator(final IModelAccess modelAccessFactory, final IReconfigurator[] reconfigurators) {
        super();
        this.pcmResourceSetPartition = modelAccessFactory.getGlobalPCMModel();
        this.prmModel = modelAccessFactory.getPRMModel();
        this.reconfigurators = reconfigurators;
    }

    /**
     * Setup all listeners to listen for their respective model changes.
     */
    public void startListening() {
        if (LOGGER.isInfoEnabled()) {
            pcmResourceSetPartition.getResourceSet().eAdapters().add(this.globalPCMChangeListener);
        }
        this.prmModel.eAdapters().add(this.prmListener);
    }

    /**
     * Detach all model listeners.
     */
    public void stopListening() {
        this.prmModel.eAdapters().remove(this.prmListener);
        if (LOGGER.isInfoEnabled()) {
            pcmResourceSetPartition.getResourceSet().eAdapters().remove(this.globalPCMChangeListener);
        }
    }

    /**
     * Method which is called on a change in the PRM. All reconfigurators are informed and can check
     * for potential reconfigurations.
     * 
     * @param notification
     *            The notification event, which describes a change in the PRM.
     */
    protected void checkAndExecuteReconfigurations(final Notification notification) {
        final EObject monitoredElement = this.getMonitoredElement(notification);

        // Value changed, reconfigure!
        if (isNotificationNewMeasurement(monitoredElement)) {
            for (final IReconfigurator reconfigurator : this.reconfigurators) {
                if (reconfigurator.checkAndExecute(monitoredElement)) {
                    LOGGER.info("Successfully executed reconfiguration.");
                }
            }
        }
    }

    /**
     * @param monitoredElement
     * @return
     */
    private boolean isNotificationNewMeasurement(final EObject monitoredElement) {
        return monitoredElement != null;
    }

    /**
     * Visitor singleton which is used to query the monitored PCM object from a PRM notification
     * (which is a change in a {@link PCMModelElementMeasurement}).
     */
    private static final PrmSwitch<EObject> MONITORED_ELEMENT_RETRIEVER = new PrmSwitch<EObject>() {

        @Override
        public EObject casePCMModelElementMeasurement(final PCMModelElementMeasurement object) {
            return object.getPcmModelElement();
        }

        @Override
        public EObject caseResourceContainerMeasurement(final ResourceContainerMeasurement object) {
            return object.getPcmModelElement();
        }

    };

    /**
     * Retrieve the monitored PCM element from the PRM change event.
     * 
     * @param notification
     *            The PRM change event.
     * @return The PCM element whose monitoring triggered the change event.
     */
    protected EObject getMonitoredElement(final Notification notification) {
        switch (notification.getEventType()) {
        case Notification.ADD:
            return MONITORED_ELEMENT_RETRIEVER.doSwitch((EObject) notification.getNewValue());
        case Notification.REMOVE:
            return null;
        case Notification.REMOVING_ADAPTER:
            return null;
        default:
            LOGGER.warn("Unsupported PRM Notification: " + notification);
            return null;
        }
    }

}

package org.palladiosimulator.simulizar.reconfiguration;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.util.RuntimeMeasurementSwitch;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

/**
 * Class whose objects will listen on changes in the PCM@Runtime and trigger reconfigurations
 * respectively.
 * 
 * @author Steffen Becker
 * @author Matthias Becker
 * @author Sebastian Lehrig
 * @author Florian Rosenthal
 *
 */
public class Reconfigurator extends
        AbstractObservable<IReconfigurationListener> {

    /**
     * This class' internal LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(Reconfigurator.class);

    /**
     * After executing a reconfiguration, current model changes within the PCM blackboard partition
     * are tracked here.
     */
    private final Collection<Notification> modelChanges = new LinkedList<Notification>();

    /**
     * Change listener, which will convert selected changes in the RuntimeMeasurement instance into
     * reconfiguration checks.
     */
    private final Adapter runtimeMeasurementListener = new EContentAdapter() {

        @Override
        public void notifyChanged(final Notification notification) {
            super.notifyChanged(notification);
            /*
             * TODO FIXME Christian this needs to be refactored and moved as this is now triggered
             * within the processes.
             */
            modelChanges.clear();
            Reconfigurator.this.checkAndExecuteReconfigurations(notification);
            // if (modelChanges.size() > 0) {
            // Reconfigurator.this.getEventDispatcher().reconfigurationExecuted(modelChanges);
            // }
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
                if (Reconfigurator.this.getReconfigurationProcess() != null) {
                    Reconfigurator.this.getReconfigurationProcess()
                            .appendReconfigurationNotification(notification);
                }
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Detected change in global PCM model. Changed object: "
                            + notification.getNotifier());
                    LOGGER.debug(notification.toString());
                }
            }
        }

    };

    /**
     * Access interface to the global PCM@Runtime model.
     */
    private final PCMResourceSetPartition pcmResourceSetPartition;

    /**
     * Access interface to the RuntimeMeasurement model.
     */
    private final RuntimeMeasurementModel runtimeMeasurementModel;

    /**
     * Set of all registered reconfigurators, i.e., objects that can change the PCM@Runtime.
     */
    private final List<IReconfigurator> reconfigurators;

    private final SimuComModel model;

    private ReconfigurationProcess reconfigurationProcess = null;

    /**
     * Constructor.
     * 
     * @param model
     * 
     * @param modelAccessFactory
     *            Access factory for model access interfaces.
     * @param reconfigurators
     *            Set of reconfigurators which will be triggered as soon as new, interesting
     *            monitoring data arrives.
     */
    public Reconfigurator(SimuComModel model,
            final IModelAccess modelAccessFactory,
            final ISimulationControl simulationcontrol,
            final List<IReconfigurator> reconfigurators) {
        super();
        this.model = model;
        this.pcmResourceSetPartition = modelAccessFactory.getGlobalPCMModel();
        this.runtimeMeasurementModel = modelAccessFactory
                .getRuntimeMeasurementModel();
        this.reconfigurators = reconfigurators;
    }

    /**
     * Setup all listeners to listen for their respective model changes.
     */
    public void startListening() {
        pcmResourceSetPartition.getResourceSet().eAdapters()
                .add(this.globalPCMChangeListener);
        this.runtimeMeasurementModel.eAdapters().add(
                this.runtimeMeasurementListener);
    }

    /**
     * Detach all model listeners.
     */
    public void stopListening() {
        this.runtimeMeasurementModel.eAdapters().remove(
                this.runtimeMeasurementListener);
        pcmResourceSetPartition.getResourceSet().eAdapters()
                .remove(this.globalPCMChangeListener);
    }

    /**
     * Method which is called on a change in the RuntimeMeasurement. All reconfigurators are
     * informed and can check for potential reconfigurations.
     * 
     * @param notification
     *            The notification event, which describes a change in the RuntimeMeasurement model.
     */
    protected void checkAndExecuteReconfigurations(
            final Notification notification) {
        final EObject monitoredElement = this.getMonitoredElement(notification);

        // Value changed, reconfiguration is triggered. Reconfiguration only
        // executes if the
        // previous reconfiguration process is finished. This could be done on a
        // more fine-granular
        // level (one thread per executor).
        if (isNotificationNewMeasurement(monitoredElement)
                && (reconfigurationProcess == null || reconfigurationProcess
                        .isFinished())) {
            // StB: I do not like this code line. It would be better to have a _single_,
            // final reconfiguration process attached to this object. This single
            // process could have a startProcess(monitoredElement) method to start
            // and schedule the process...
            reconfigurationProcess = new ReconfigurationProcess(model,
                    "Reconfiguration Process", this.reconfigurators,
                    monitoredElement, this.getEventDispatcher());
            reconfigurationProcess.scheduleAt(0);
        }
    }

    /**
     * Checks whether the monitored element is not null
     * 
     * @param monitoredElement
     * @return true if the monitored element is not null
     */
    private boolean isNotificationNewMeasurement(final EObject monitoredElement) {
        return monitoredElement != null;
    }

    /**
     * Visitor singleton which is used to query the monitored PCM object from a RuntimeMeasurement
     * notification (which is a change in a {@link RuntimeMeasurement}).
     */
    private static final RuntimeMeasurementSwitch<MeasuringPoint> MONITORED_ELEMENT_RETRIEVER = new RuntimeMeasurementSwitch<MeasuringPoint>() {

        @Override
        public MeasuringPoint caseRuntimeMeasurement(
                final RuntimeMeasurement object) {
            return object.getMeasuringPoint();
        };

    };

    void fireReconfigurationEvent(
            EndReconfigurationEvent endReconfigurationEvent) {
        this.getEventDispatcher().endReconfigurationEvent(
                endReconfigurationEvent);
    }

    void fireReconfigurationEvent(
            BeginReconfigurationEvent beginReconfigurationEvent) {
        this.getEventDispatcher().beginReconfigurationEvent(
                beginReconfigurationEvent);
    }

    /**
     * Retrieve the monitored PCM element from the RuntimeMeasurement change event.
     * 
     * @param notification
     *            The RuntimeMeasurment change event.
     * @return The PCM element whose monitoring triggered the change event.
     */
    protected EObject getMonitoredElement(final Notification notification) {
        switch (notification.getEventType()) {
        case Notification.ADD:
            return MONITORED_ELEMENT_RETRIEVER.doSwitch((EObject) notification
                    .getNewValue());
        case Notification.REMOVE:
            return null;
        case Notification.REMOVING_ADAPTER:
            return null;
        case Notification.SET:
            // in this case, one feature such as value of notifier
            // PcmModelElementMeasurement could
            // have been set/changed
            return MONITORED_ELEMENT_RETRIEVER.doSwitch((EObject) notification
                    .getNotifier());
        default:
            LOGGER.warn("Unsupported RuntimeMeasurement Notification: "
                    + notification);
            return null;
        }
    }

    public ReconfigurationProcess getReconfigurationProcess() {
        return reconfigurationProcess;
    }

}

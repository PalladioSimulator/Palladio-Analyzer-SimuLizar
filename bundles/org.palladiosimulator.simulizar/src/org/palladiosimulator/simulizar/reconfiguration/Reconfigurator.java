package org.palladiosimulator.simulizar.reconfiguration;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;
import org.palladiosimulator.runtimemeasurement.util.RuntimeMeasurementSwitch;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationTimeProvider;

/**
 * Class whose objects will listen on changes in the PCM@Runtime (i.e., they
 * track changes in the {@link RuntimeMeasurementModel}) and trigger
 * reconfigurations respectively.
 *
 * @author Steffen Becker
 * @author Matthias Becker
 * @author Sebastian Lehrig
 * @author Florian Rosenthal
 *
 */
public class Reconfigurator extends AbstractObservable<IReconfigurationListener> implements IModelObserver {

	/**
	 * This class' internal LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(Reconfigurator.class);

	/**
	 * Change listener, which will convert selected changes in the
	 * RuntimeMeasurement instance into reconfiguration checks.
	 */
	private final Adapter runtimeMeasurementListener = new EContentAdapter() {

		@Override
		public void notifyChanged(final Notification notification) {
			super.notifyChanged(notification);
			Reconfigurator.this.checkAndExecuteReconfigurations(notification);
		}
	};

	/**
	 * Access interface to the RuntimeMeasurement model.
	 */
	private RuntimeMeasurementModel runtimeMeasurementModel;

	// will be initialized lazily, once the first reconfiguration is to be
	// executed
	private ReconfigurationProcess reconfigurationProcess;

	private double lastReconfigurationTime = 0;

    private final ISimulationTimeProvider simTimeProvider;

    private final ReconfigurationProcessFactory processFactory;

    private final PCMResourceSetPartition partition;

	/**
	 * Constructor.
	 *
	 * @param model
	 *
	 * @param modelAccessFactory
	 *            Access factory for model access interfaces.
	 * @param reconfigurators
	 *            Set of reconfigurators which will be triggered as soon as new,
	 *            interesting monitoring data arrives.
	 */
    public Reconfigurator(@Global PCMResourceSetPartition partition,
	        ReconfigurationProcessFactory processFactory,
			ISimulationTimeProvider simTimeProvider) {
		this.partition = partition;
        this.processFactory = processFactory;
        this.simTimeProvider = simTimeProvider;
	}

    @Override
    public void initialize() {
        var models = partition.getElement(RuntimeMeasurementPackage.eINSTANCE.getRuntimeMeasurementModel());
        if (models.size() != 1) {
            if (models.size() > 1) {
                throw new IllegalStateException("There is more than one Runtime Measurements Model in the global PCM Partition. This is not supported.");
            } else {
                LOGGER.warn("No runtime measurements model found. Deactivating the reconfigurator");
            }
        } else {
            runtimeMeasurementModel = (RuntimeMeasurementModel) models.get(0);
            runtimeMeasurementModel.eAdapters().add(this.runtimeMeasurementListener);
            this.getEventDispatcher().initialize();
        }
    }

    @Override
    public void unregister() {
        if (this.runtimeMeasurementModel != null) {
            this.runtimeMeasurementModel.eAdapters().remove(this.runtimeMeasurementListener);
        }
        // this also requires that the reconfiguration process be terminated
        if (this.reconfigurationProcess != null) {
            this.reconfigurationProcess.requestTermination();
        }
        
        removeAllObserver();
    }

	/**
	 * Method which is called on a change in the RuntimeMeasurement. All
	 * reconfigurators are informed and can check for potential
	 * reconfigurations.
	 *
	 * @param notification
	 *            The notification event, which describes a change in the
	 *            RuntimeMeasurement model.
	 */
	protected void checkAndExecuteReconfigurations(final Notification notification) {
		final EObject monitoredElement = this.getMonitoredElement(notification);

		// Value changed, reconfiguration is triggered. Reconfiguration only
		// executes if the
		// previous reconfiguration is finished. This could be done on a
		// more fine-granular
		// level (one thread per executor).
		if (this.isNotificationNewMeasurement(monitoredElement)
				&& simTimeProvider.getCurrentSimulationTime() > this.lastReconfigurationTime
				&& (this.reconfigurationProcess == null || !this.reconfigurationProcess.isScheduled())) {
			if (this.reconfigurationProcess == null) {
				this.reconfigurationProcess = processFactory.create();
			}
			this.reconfigurationProcess.executeReconfigurations(this.runtimeMeasurementModel);
			this.lastReconfigurationTime = simTimeProvider.getCurrentSimulationTime();
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
	 * Visitor singleton which is used to query the monitored PCM object from a
	 * RuntimeMeasurement notification (which is a change in a
	 * {@link RuntimeMeasurement}).
	 */
	private static final RuntimeMeasurementSwitch<MeasuringPoint> MONITORED_ELEMENT_RETRIEVER = new RuntimeMeasurementSwitch<MeasuringPoint>() {

		@Override
		public MeasuringPoint caseRuntimeMeasurement(final RuntimeMeasurement object) {
			return object.getMeasuringPoint();
		};

	};

	void fireReconfigurationEvent(final EndReconfigurationEvent endReconfigurationEvent) {
		this.getEventDispatcher().endReconfigurationEvent(endReconfigurationEvent);
	}

	void fireReconfigurationEvent(final BeginReconfigurationEvent beginReconfigurationEvent) {
		this.getEventDispatcher().beginReconfigurationEvent(beginReconfigurationEvent);
	}

	void fireReconfigurationEvent(final ReconfigurationExecutedEvent reconfigurationExecutedEvent) {
		this.getEventDispatcher().reconfigurationExecuted(reconfigurationExecutedEvent);
	}

	/**
	 * Retrieve the monitored PCM element from the RuntimeMeasurement change
	 * event.
	 *
	 * @param notification
	 *            The RuntimeMeasurment change event.
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
		case Notification.SET:
			// in this case, one feature such as value of notifier
			// PcmModelElementMeasurement could
			// have been set/changed
			return MONITORED_ELEMENT_RETRIEVER.doSwitch((EObject) notification.getNotifier());
		default:
			LOGGER.warn("Unsupported RuntimeMeasurement Notification: " + notification);
			return null;
		}
	}

	/**
	 * Gets the current reconfiguration process.
	 *
	 * @return The current {@link ReconfigurationProcess}.
	 */
	public ReconfigurationProcess getReconfigurationProcess() {
		return this.reconfigurationProcess;
	}

}

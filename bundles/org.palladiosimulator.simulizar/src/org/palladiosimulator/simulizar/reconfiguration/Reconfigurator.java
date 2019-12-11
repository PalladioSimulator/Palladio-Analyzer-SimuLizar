package org.palladiosimulator.simulizar.reconfiguration;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.util.RuntimeMeasurementSwitch;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

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
public class Reconfigurator extends AbstractObservable<IReconfigurationListener> {

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
	private final RuntimeMeasurementModel runtimeMeasurementModel;

	/**
	 * Set of all registered reconfigurators, i.e., objects that can change the
	 * PCM@Runtime.
	 */
	private final List<IReconfigurationEngine> reconfiguratorEngines;

	private final List<AbstractReconfigurationLoader> reconfigurationLoaders;

	private final SimuComModel model;

	// will be initialized lazily, once the first reconfiguration is to be
	// executed
	private ReconfigurationProcess reconfigurationProcess;

	private double lastReconfigurationTime = 0;

	private SimuLizarWorkflowConfiguration configuration;

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
	public Reconfigurator(final SimuComModel model, final RuntimeMeasurementModel rmModel,
			final ISimulationControl simulationcontrol, final List<IReconfigurationEngine> reconfigurators,
			SimuLizarWorkflowConfiguration configuration) {
		super();
		this.model = model;
		this.runtimeMeasurementModel = rmModel;
		this.reconfiguratorEngines = reconfigurators;
		this.configuration = configuration;

		this.reconfigurationLoaders = ExtensionHelper.getExecutableExtensions(
				SimulizarConstants.RECONFIGURATION_LOADER_EXTENSION_POINT_ID,
				SimulizarConstants.RECONFIGURATION_LOADER_EXTENSION_POINT_LOADER_ATTRIBUTE);
		reconfigurationLoaders.forEach(loader -> loader.load(configuration));
	}

	/**
	 * Setup all listeners to listen for their respective model changes.
	 */
	public void startListening() {
		this.runtimeMeasurementModel.eAdapters().add(this.runtimeMeasurementListener);
	}

	/**
	 * Detach all model listeners and request termination of reconfiguration
	 * process.
	 */
	public void cleanUp() {
		this.runtimeMeasurementModel.eAdapters().remove(this.runtimeMeasurementListener);
		// this also requires that the reconfiguration process be terminated
		if (this.reconfigurationProcess != null) {
			this.reconfigurationProcess.requestTermination();

		}
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
				&& this.model.getSimulationControl().getCurrentSimulationTime() > this.lastReconfigurationTime
				&& (this.reconfigurationProcess == null || !this.reconfigurationProcess.isScheduled())) {
			if (this.reconfigurationProcess == null) {
				this.reconfigurationProcess = new ReconfigurationProcess(this.model, this.reconfiguratorEngines, this);
			}
			this.reconfigurationProcess.executeReconfigurations(this.runtimeMeasurementModel);
			this.lastReconfigurationTime = this.model.getSimulationControl().getCurrentSimulationTime();
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

	public SimuLizarWorkflowConfiguration getConfiguration() {
		return configuration;
	}

	public List<AbstractReconfigurationLoader> getReconfigurationLoaders() {
		return reconfigurationLoaders;
	}
	
	

}

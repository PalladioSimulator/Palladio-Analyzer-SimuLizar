package org.palladiosimulator.simulizar.reconfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

/**
 * {@link SimuComSimProcess} implementation which is responsible for executing
 * reconfigurations during Simulizar runs.
 * 
 * @author Florian Rosenthal
 * @see Reconfigurator
 * @see IReconfigurator
 */
public class ReconfigurationProcess extends SimuComSimProcess {

	private EObject monitoredElement;
	private final Iterable<IReconfigurator> reconfigurators;
	private final ISimulationControl simControl;
	private final List<Notification> currentReconfigNotifications;
	private final Reconfigurator reconfigurator;
	// volatile is sufficient as flag is only set once
	private volatile boolean terminationRequested = false;

	/**
	 * Initializes a new instance of the {@link ReconfigurationProcess} class.
	 * 
	 * @param model
	 *            The {@link SimuComModel} that is in used during the current
	 *            simulation run.
	 * @param reconfigurators
	 *            An {@link Iterable} containing all {@link IReconfigurator}
	 *            that shall be used.
	 * @param reconfigurator
	 *            The {@link Reconfigurator} instance that manages and triggers
	 *            reconfigurations.
	 */
	protected ReconfigurationProcess(SimuComModel model,
			Iterable<IReconfigurator> reconfigurators,
			Reconfigurator reconfigurator) {
		super(model, "Reconfiguration Process");
		this.reconfigurators = Objects.requireNonNull(reconfigurators,
				"reconfigurators must not be null");
		this.reconfigurator = Objects.requireNonNull(reconfigurator,
				"reconfigurator must not be null");
		this.simControl = Objects.requireNonNull(model,
				"Passed SimuComModel must not be null").getSimulationControl();
		this.currentReconfigNotifications = new ArrayList<>();
	}

	/**
	 * Appends the given notification about model change due to a
	 * reconfiguration that takes place.
	 * 
	 * @param notification
	 *            A {@link Notification} that describe model changes due to
	 *            reconfiguration.
	 */
	public void appendReconfigurationNotification(Notification notification) {
		if (notification != null) {
			this.currentReconfigNotifications.add(notification);
		}
	}

	private void fireBeginReconfigurationEvent(BeginReconfigurationEvent event) {
		this.reconfigurator.fireReconfigurationEvent(event);
	}

	private void fireEndReconfigurationEvent(EndReconfigurationEvent event) {
		this.reconfigurator.fireReconfigurationEvent(event);
	}

	private void fireReconfigurationExecutedEvent(
			BeginReconfigurationEvent beginEvent,
			EndReconfigurationEvent endEvent) {
		this.reconfigurator
				.fireReconfigurationEvent(new ReconfigurationExecutedEvent(
						beginEvent, endEvent, this.currentReconfigNotifications));
	}

	private void clearNotifications() {
		this.currentReconfigNotifications.clear();
	}

	@Override
	public void activate() {
		throw new UnsupportedOperationException(
				"The reconfiguration process is not supposed to be activated manually."
						+ "Use executeReconfigurations(EObject) instead.");
	}

	@Override
	public void reschedule(double d) {
		throw new UnsupportedOperationException(
				"The reconfiguration process is not supposed to be scheduled manually."
						+ "Use executeReconfigurations(EObject) instead.");
	}

	/**
	 * Executes all reconfigurations that are provided by the attached
	 * {@link IReconfigurator}s.
	 * 
	 * @param monitoredElement
	 *            The {@link EObject} which is the reconfiguration target.
	 */
	void executeReconfigurations(EObject monitoredElement) {
		this.monitoredElement = Objects.requireNonNull(monitoredElement,
				"Monitored element must not be null.");
		if (this.isScheduled()) {
			throw new IllegalStateException(
					"Reconfigurations are already taking place.");
		}
		if (this.isFinished()) {
			throw new IllegalStateException(
					"Reconfiguration process has already terminated.");
		}
		scheduleAt(0);
	}

	/**
	 * Indicates whether termination has been requested by a call to
	 * {@link #requestTermination()}. <br>
	 * Note, that the process may still be running, even if this method returns
	 * {@code true}.
	 * 
	 * @return {@code true} if termination has been requested, otherwise
	 *         {@code false}.
	 */
	boolean isTerminationRequested() {
		return this.terminationRequested;
	}

	/**
	 * Requests that the process shall terminate properly.
	 */
	void requestTermination() {
		if (!terminationRequested) {
			// this ensures that flag is only set once
			this.terminationRequested = true;
		}
	}

	@Override
	protected void internalLifeCycle() {
		// execute reconfigurations until termination requested
		while (!isTerminationRequested()) {
			for (IReconfigurator reconfigurator : this.reconfigurators) {
				BeginReconfigurationEvent beginReconfigurationEvent = new BeginReconfigurationEvent(
						this.simControl.getCurrentSimulationTime());
				fireBeginReconfigurationEvent(beginReconfigurationEvent);
				boolean reconfigResult = reconfigurator
						.checkAndExecute(monitoredElement);
				EndReconfigurationEvent endReconfigurationEvent = new EndReconfigurationEvent(
						EventResult.fromBoolean(reconfigResult),
						this.simControl.getCurrentSimulationTime());
				fireEndReconfigurationEvent(endReconfigurationEvent);
				if (reconfigResult) {
					LOGGER.debug("Successfully executed reconfiguration.");
					fireReconfigurationExecutedEvent(beginReconfigurationEvent,
							endReconfigurationEvent);
				}
				clearNotifications();
				this.passivate();
			}
		}
	}
}

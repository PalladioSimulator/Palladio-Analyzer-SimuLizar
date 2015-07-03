package org.palladiosimulator.simulizar.reconfiguration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

public class ReconfigurationProcess extends SimuComSimProcess {

	private final EObject monitoredElement;
	private final IReconfigurationListener reconfigurationDispatcher;
	private final Iterable<IReconfigurator> reconfigurators;
	private final ISimulationControl simControl;
	private final List<Notification> currentReconfigNotifications;

	protected ReconfigurationProcess(SimuComModel model, String name,
			Iterable<IReconfigurator> reconfigurators,
			EObject monitoredElement,
			IReconfigurationListener reconfigurationDispatcher) {
		super(model, name);
		this.reconfigurators = reconfigurators;
		this.monitoredElement = monitoredElement;
		this.reconfigurationDispatcher = reconfigurationDispatcher;
		this.simControl = model.getSimulationControl();
		this.currentReconfigNotifications = new ArrayList<>();
	}

	public void appendReconfigurationNotification(Notification notification) {
		if (notification != null) {
			this.currentReconfigNotifications.add(notification);
		}
	}

	private void fireBeginReconfigurationEvent(BeginReconfigurationEvent event) {
		this.reconfigurationDispatcher.beginReconfigurationEvent(event);
	}

	private void fireEndReconfigurationEvent(EndReconfigurationEvent event) {
		this.reconfigurationDispatcher.endReconfigurationEvent(event);
	}

	private void fireReconfigurationExecutedEvent(
			BeginReconfigurationEvent beginEvent,
			EndReconfigurationEvent endEvent) {
		this.reconfigurationDispatcher
				.reconfigurationExecuted(new ReconfigurationExecutedEvent(
						beginEvent, endEvent, this.currentReconfigNotifications));
	}

	private void clearNotifications() {
		this.currentReconfigNotifications.clear();
	}

	@Override
	protected void internalLifeCycle() {
		for (IReconfigurator reconfigurator : reconfigurators) {
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
		}
	}
}

package org.palladiosimulator.simulizar.interpreter.listener;

import org.eclipse.emf.common.notify.Notification;

public class ReconfigurationExecutedEvent {

	private final BeginReconfigurationEvent beginReconfEvent;
	private final EndReconfigurationEvent endReconfEvent;
	private final Iterable<Notification> modelChanges;

	public ReconfigurationExecutedEvent(
			BeginReconfigurationEvent beginReconfigurationEvent,
			EndReconfigurationEvent endReconfigurationEvent,
			Iterable<Notification> modelChanges) {
		if (beginReconfigurationEvent == null
				|| endReconfigurationEvent == null || modelChanges == null) {
			throw new IllegalArgumentException(
					"None of the parameters must be null.");
		}
		this.beginReconfEvent = beginReconfigurationEvent;
		this.endReconfEvent = endReconfigurationEvent;
		this.modelChanges = modelChanges;
	}

	public EventResult getReconfigurationResult() {
		return this.endReconfEvent.getReconfigurationEventResult();
	}

	public double getFinishTime() {
		return this.endReconfEvent.getPassageTime();
	}

	public double getStartTime() {
		return this.beginReconfEvent.getPassageTime();
	}

	public double getDuration() {
		return this.getFinishTime() - this.getStartTime();
	}

	public Iterable<Notification> getModelChanges() {
		return this.modelChanges;
	}
}

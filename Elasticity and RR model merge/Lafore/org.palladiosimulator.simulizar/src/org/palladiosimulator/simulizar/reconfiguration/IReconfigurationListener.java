package org.palladiosimulator.simulizar.reconfiguration;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;

public interface IReconfigurationListener {
	public void reconfigurationExecuted(Collection<Notification> modelChanges);

	public void beginReconfigurationEvent(BeginReconfigurationEvent event);

	public void endReconfigurationEvent(EndReconfigurationEvent event);
}

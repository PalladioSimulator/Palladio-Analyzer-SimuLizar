package org.palladiosimulator.simulizar.reconfiguration;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationEvent;

public interface IReconfigurationListener {
    public void reconfigurationExecuted(Collection<Notification> modelChanges);

    public void beginReconfigurationEvent(ReconfigurationEvent event);

    public void endReconfigurationEvent(ReconfigurationEvent event);
}

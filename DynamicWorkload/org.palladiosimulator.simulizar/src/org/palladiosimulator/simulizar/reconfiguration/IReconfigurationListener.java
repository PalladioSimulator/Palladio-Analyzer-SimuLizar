package org.palladiosimulator.simulizar.reconfiguration;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

public interface IReconfigurationListener {
    public void reconfigurationExecuted(Collection<Notification> modelChanges);
}

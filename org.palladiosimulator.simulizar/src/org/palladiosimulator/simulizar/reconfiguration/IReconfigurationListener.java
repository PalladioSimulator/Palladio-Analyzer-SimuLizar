package org.palladiosimulator.simulizar.reconfiguration;

import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;

public interface IReconfigurationListener {

    public void reconfigurationExecuted(ReconfigurationExecutedEvent reconfExecutedEvent);

    public void beginReconfigurationEvent(BeginReconfigurationEvent event);

    public void endReconfigurationEvent(EndReconfigurationEvent event);
}

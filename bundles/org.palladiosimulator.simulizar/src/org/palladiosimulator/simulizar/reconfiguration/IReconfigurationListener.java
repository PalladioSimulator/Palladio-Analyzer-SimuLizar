package org.palladiosimulator.simulizar.reconfiguration;

import org.palladiosimulator.simulizar.di.core.extension.Extension;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;

public interface IReconfigurationListener extends Extension {
    
    default void initialize() {
        
    }

    default void reconfigurationExecuted(ReconfigurationExecutedEvent reconfExecutedEvent) {
        
    }

    default void beginReconfigurationEvent(BeginReconfigurationEvent event) {
        
    }

    default void endReconfigurationEvent(EndReconfigurationEvent event) {
        
    }
}

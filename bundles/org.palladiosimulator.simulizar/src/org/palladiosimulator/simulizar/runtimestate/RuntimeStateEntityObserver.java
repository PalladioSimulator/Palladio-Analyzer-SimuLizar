package org.palladiosimulator.simulizar.runtimestate;

import org.palladiosimulator.simulizar.di.base.extension.Extension;

public interface RuntimeStateEntityObserver extends Extension {

    default void initialize() {
        
    }
    
    default void cleanup() {
        
    }
    
}

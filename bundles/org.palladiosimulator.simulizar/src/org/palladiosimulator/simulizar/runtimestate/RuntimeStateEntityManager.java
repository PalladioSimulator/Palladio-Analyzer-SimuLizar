package org.palladiosimulator.simulizar.runtimestate;

import org.palladiosimulator.simulizar.di.extension.Extension;

public interface RuntimeStateEntityManager extends Extension {
    
    default void initialize() {
        
    }
    
    default void cleanup() {
        
    }
    
    @Override
    default Class<? extends Extension> getExtensionType() {
        return RuntimeStateEntityManager.class;
    }

}

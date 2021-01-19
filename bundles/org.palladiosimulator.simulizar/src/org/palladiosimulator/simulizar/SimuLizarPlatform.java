package org.palladiosimulator.simulizar;

import org.palladiosimulator.simulizar.component.core.DaggerSimuLizarPlatformComponent;
import org.palladiosimulator.simulizar.component.core.SimuLizarPlatformComponent;

public enum SimuLizarPlatform {
    
    INSTANCE(DaggerSimuLizarPlatformComponent.factory().create());
    
    private SimuLizarPlatform(SimuLizarPlatformComponent component) {
        this.component = component;
    }
    
    private final SimuLizarPlatformComponent component;
    
    public SimuLizarPlatformComponent getComponent() {
        return component;
    }
    
    public static SimuLizarPlatformComponent getPlatformComponent() {
        return INSTANCE.getComponent();
    }

}

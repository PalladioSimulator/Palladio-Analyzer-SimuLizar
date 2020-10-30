package org.palladiosimulator.simulizar;

import javax.inject.Singleton;

import org.palladiosimulator.simulizar.modules.DefaultQUALModule;
import org.palladiosimulator.simulizar.modules.EclipseIDEPreferencesModule;
import org.palladiosimulator.simulizar.modules.SimuLizarCoreAggregateModule;

import dagger.Component;

@Component(modules = { SimuLizarCoreAggregateModule.class, DefaultQUALModule.class, EclipseIDEPreferencesModule.class })
@Singleton
public interface SimuLizarComponent extends SimuLizarCoreComponent {
    
    @Component.Builder
    interface Builder extends SimuLizarCoreComponent.Builder {}

}

package org.palladiosimulator.simulizar.test.commons.util;

import javax.inject.Singleton;

import org.palladiosimulator.simulizar.SimuLizarCoreComponent;
import org.palladiosimulator.simulizar.modules.DefaultQUALModule;
import org.palladiosimulator.simulizar.modules.SimuLizarCoreAggregateModule;

import dagger.Component;

@Component(modules = { SimuLizarCoreAggregateModule.class, DefaultQUALModule.class, TestPreferencesModule.class })
@Singleton
public interface SimuLizarTestComponent extends SimuLizarCoreComponent {

    @Component.Builder
    interface Builder extends SimuLizarCoreComponent.Builder {
    }

}

package org.palladiosimulator.simulizar.elasticity;

import javax.inject.Singleton;

import org.palladiosimulator.simulizar.SimuLizarCoreComponent;
import org.palladiosimulator.simulizar.elasticity.modules.ElasticityQUALModule;
import org.palladiosimulator.simulizar.modules.EclipseIDEPreferencesModule;
import org.palladiosimulator.simulizar.modules.SimuLizarCoreAggregateModule;
import org.palladiosimulator.simulizar.modules.EclipseSimuLizarExtensionModule;

import dagger.Component;

@Component(modules = { SimuLizarCoreAggregateModule.class, ElasticityQUALModule.class,
        EclipseIDEPreferencesModule.class, EclipseSimuLizarExtensionModule.class })
@Singleton
public interface SimuLizarElasticityComponent extends SimuLizarCoreComponent {

    @Component.Builder
    interface Builder extends SimuLizarCoreComponent.Builder {
    }

}

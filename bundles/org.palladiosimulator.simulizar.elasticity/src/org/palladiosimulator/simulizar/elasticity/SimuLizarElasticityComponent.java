package org.palladiosimulator.simulizar.elasticity;

import javax.inject.Singleton;

import org.palladiosimulator.simulizar.SimuLizarCoreComponent;
import org.palladiosimulator.simulizar.elasticity.modules.ElasticityModule;
import org.palladiosimulator.simulizar.modules.EclipseIDEPreferencesModule;
import org.palladiosimulator.simulizar.modules.SimuLizarCoreAggregateModule;

import dagger.Component;

@Component(modules = { SimuLizarCoreAggregateModule.class, EclipseIDEPreferencesModule.class, ElasticityModule.class })
@Singleton
public interface SimuLizarElasticityComponent extends SimuLizarCoreComponent {
    
    @Component.Builder
    interface Builder extends SimuLizarCoreComponent.Builder {}

}

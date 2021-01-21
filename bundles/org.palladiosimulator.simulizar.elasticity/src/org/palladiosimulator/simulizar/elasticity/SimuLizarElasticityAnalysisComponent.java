package org.palladiosimulator.simulizar.elasticity;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.elasticity.modules.ElasticityRuntimeModule;

import dagger.Component;

@Component(modules = { ElasticityRuntimeModule.class })
public interface SimuLizarElasticityAnalysisComponent extends SimuLizarRuntimeComponent {
    
    @Component.Factory
    interface Factory extends SimuLizarRuntimeComponent.Factory {}

}

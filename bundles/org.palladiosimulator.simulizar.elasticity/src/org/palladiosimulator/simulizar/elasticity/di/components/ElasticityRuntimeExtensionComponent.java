package org.palladiosimulator.simulizar.elasticity.di.components;

import java.util.Set;

import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.extension.Extension;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionContribution;
import org.palladiosimulator.simulizar.elasticity.aggregator.AggregatorAttachingRuntimeStateObserver;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityObserver;
import org.palladiosimulator.simulizar.scopes.RuntimeExtensionScope;

import dagger.Component;

@Component(dependencies = {ElasticityAnalysisComponent.class, QUALComponent.class})
@RuntimeExtensionScope
public interface ElasticityRuntimeExtensionComponent extends ExtensionComponent<ElasticityRuntimeExtensionComponent> {
    AggregatorAttachingRuntimeStateObserver stateObserver();
    
    @Override
    default Set<ExtensionContribution<? extends Extension, ElasticityRuntimeExtensionComponent>> contributions() {
        return contribute(RuntimeStateEntityObserver.class, ElasticityRuntimeExtensionComponent::stateObserver);
    }
    
    @Component.Factory
    interface Factory extends ExtensionComponent.Factory {
        ElasticityRuntimeExtensionComponent create(ElasticityAnalysisComponent analysisComponent, QUALComponent qualComponent);
    }
}

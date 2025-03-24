package org.palladiosimulator.simulizar.elasticity.di.components;

import org.palladiosimulator.simulizar.di.component.dependency.QUALComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.scopes.RuntimeExtensionScope;
import org.palladiosimulator.simulizar.elasticity.aggregator.AggregatorAttachingRuntimeStateObserver;

import dagger.Component;

@Component(dependencies = {ElasticityAnalysisComponent.class, QUALComponent.class})
@RuntimeExtensionScope
public interface ElasticityRuntimeExtensionComponent extends ExtensionComponent {
    AggregatorAttachingRuntimeStateObserver stateObserver();

    
    @Component.Factory
    interface Factory extends ExtensionComponent.Factory {
        ElasticityRuntimeExtensionComponent create(ElasticityAnalysisComponent analysisComponent, QUALComponent qualComponent);
    }
}

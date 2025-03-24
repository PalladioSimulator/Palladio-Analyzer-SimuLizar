package org.palladiosimulator.simulizar.elasticity.di.components;

import org.palladiosimulator.simulizar.core.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.di.modules.stateless.configuration.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.di.scopes.AnalysisRootScope;
import org.palladiosimulator.simulizar.elasticity.aggregator.ReconfigurationTimeAggregatorWithConfidence;
import org.palladiosimulator.simulizar.elasticity.di.modules.ElasticityAnalysisModule;
import org.palladiosimulator.simulizar.elasticity.jobs.SimuLizarElasticityAnalysisCompositeJob;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = { ElasticityAnalysisModule.class, SimuLizarConfigurationModule.class })
@AnalysisRootScope
public interface ElasticityAnalysisComponent {
    
    SimuLizarElasticityAnalysisCompositeJob rootJob();
    
    ReconfigurationTimeAggregatorWithConfidence aggregatorWithConfidence();
    
    @Component.Factory
    interface Factory {
        ElasticityAnalysisComponent create(@BindsInstance SimuLizarWorkflowConfiguration configuration);
    }

}

package org.palladiosimulator.simulizar.elasticity;

import org.palladiosimulator.simulizar.elasticity.jobs.SimuLizarElasticityAnalysisCompositeJob;
import org.palladiosimulator.simulizar.elasticity.modules.ElasticityRootModule;
import org.palladiosimulator.simulizar.elasticity.scope.ElasticityAnalysisScope;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = { ElasticityRootModule.class })
@ElasticityAnalysisScope
public interface SimuLizarElasticityComponent {
    
    SimuLizarElasticityAnalysisCompositeJob rootJob();
    
    @Component.Factory
    public interface Factory {
        SimuLizarElasticityComponent create(@BindsInstance SimuLizarWorkflowConfiguration configuration);
    }

}

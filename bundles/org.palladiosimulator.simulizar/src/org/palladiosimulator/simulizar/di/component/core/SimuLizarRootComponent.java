package org.palladiosimulator.simulizar.di.component.core;

import org.palladiosimulator.simulizar.di.modules.component.core.SimuLizarRootModule;
import org.palladiosimulator.simulizar.di.modules.component.extensions.ExtensionComponentsModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.ComponentFactoriesModule;
import org.palladiosimulator.simulizar.di.modules.stateless.mdsd.MDSDBlackboardProvidingModule;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.scopes.AnalysisRootScope;

import dagger.BindsInstance;
import dagger.Component;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@Component(modules = SimuLizarRootModule.class)
@AnalysisRootScope
public interface SimuLizarRootComponent {
    IJob rootJob();

    MDSDBlackboard blackboard();

    SimuLizarWorkflowConfiguration config();

    @Component.Factory
    public abstract class Factory {
        public SimuLizarRootComponent create(SimuLizarWorkflowConfiguration configuration) {
            return create(configuration, new ComponentFactoriesModule(), new ExtensionComponentsModule(),
                    new MDSDBlackboardProvidingModule());
        }
        
        public SimuLizarRootComponent create(SimuLizarWorkflowConfiguration configuration,
                ExtensionComponentsModule extensionModule) {
            return create(configuration, new ComponentFactoriesModule(),
                    extensionModule, new MDSDBlackboardProvidingModule());
        }

        public abstract SimuLizarRootComponent create(@BindsInstance SimuLizarWorkflowConfiguration configuration,
                ComponentFactoriesModule factoriesModule, ExtensionComponentsModule extensionModule,
                MDSDBlackboardProvidingModule blackBoardModule);
    }

}

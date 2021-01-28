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

@Component(modules = { SimuLizarRootModule.class })
@AnalysisRootScope
public interface SimuLizarRootComponent {
    IJob rootJob();

    MDSDBlackboard blackboard();

    SimuLizarWorkflowConfiguration config();

    @Component.Factory
    public static interface Factory {
        default ComponentFactoriesModule defaultComponentFactoriesModule() {
            return new ComponentFactoriesModule();
        }
        
        default ExtensionComponentsModule defaultExtensionComponentsModule() {
            return new ExtensionComponentsModule();
        }
        
        default MDSDBlackboardProvidingModule defaultMDSDBlackboardProvidingModule() {
            return new MDSDBlackboardProvidingModule();
        }
        
        default SimuLizarRootComponent create(SimuLizarWorkflowConfiguration configuration) {
            return create(configuration, defaultComponentFactoriesModule(), defaultExtensionComponentsModule(),
                    defaultMDSDBlackboardProvidingModule());
        }
        
        default SimuLizarRootComponent create(SimuLizarWorkflowConfiguration configuration,
                ExtensionComponentsModule extensionModule) {
            return create(configuration, defaultComponentFactoriesModule(),
                    extensionModule, new MDSDBlackboardProvidingModule());
        }

        SimuLizarRootComponent create(@BindsInstance SimuLizarWorkflowConfiguration configuration,
                ComponentFactoriesModule factoriesModule, ExtensionComponentsModule extensionModule,
                MDSDBlackboardProvidingModule blackBoardModule);
    }

}

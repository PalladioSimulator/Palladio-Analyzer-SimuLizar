package org.palladiosimulator.simulizar.test.commons.di.components;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.interfaces.AnalysisRuntimeComponent;
import org.palladiosimulator.simulizar.di.modules.component.core.SimuLizarRootModule;
import org.palladiosimulator.simulizar.di.modules.component.extensions.ExtensionComponentsModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.RootComponentFactoriesModule;
import org.palladiosimulator.simulizar.di.modules.stateless.mdsd.MDSDBlackboardProvidingModule;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.scopes.AnalysisRootScope;
import org.palladiosimulator.simulizar.test.commons.di.components.TestSimuLizarRootComponent.TestRootModule;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;

@Component(modules = TestRootModule.class)
@AnalysisRootScope
public interface TestSimuLizarRootComponent extends SimuLizarRootComponent {
    
    AnalysisRuntimeComponent.Factory runtimeComponentFactory();

    @Component.Factory
    public static interface Factory extends SimuLizarRootComponent.Factory {
        
        @Override
        public abstract TestSimuLizarRootComponent create(@BindsInstance SimuLizarWorkflowConfiguration configuration,
                RootComponentFactoriesModule factoriesModule, ExtensionComponentsModule extensionModule,
                MDSDBlackboardProvidingModule blackBoardModule);
        
    }
    
    @dagger.Module(includes = SimuLizarRootModule.class)
    public static interface TestRootModule {
        @Binds
        SimuLizarRootComponent bindRootComponent(TestSimuLizarRootComponent impl);
    }

}

package org.palladiosimulator.simulizar.test.commons.di.components;

import javax.inject.Named;

import org.palladiosimulator.simulizar.core.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.di.base.scopes.AnalysisRootScope;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.interfaces.AnalysisRuntimeComponent;
import org.palladiosimulator.simulizar.di.modules.component.core.SimuLizarRootModule;
import org.palladiosimulator.simulizar.di.modules.component.extensions.ExtensionComponentsModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.RootComponentFactoriesModule;
import org.palladiosimulator.simulizar.di.modules.stateless.mdsd.MDSDBlackboardProvidingModule;
import org.palladiosimulator.simulizar.launcher.jobs.LoadSimuLizarModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.SimuLizarPrepareBlackboardJob;
import org.palladiosimulator.simulizar.test.commons.di.components.TestSimuLizarRootComponent.TestRootModule;
import org.palladiosimulator.simulizar.test.commons.di.overrides.TestSimuLizarLoadModelsJob;
import org.palladiosimulator.simulizar.test.commons.di.overrides.TestSimuLizarPrepareBlackboardJob;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;

@Component(modules = TestRootModule.class)
@AnalysisRootScope
public interface TestSimuLizarRootComponent extends SimuLizarRootComponent {

    AnalysisRuntimeComponent.Factory runtimeComponentFactory();

    @Component.Factory
    public static interface Factory extends SimuLizarRootComponent.Factory {

        default SimuLizarRootComponent create(SimuLizarWorkflowConfiguration configuration,
                RootComponentFactoriesModule factoriesModule, ExtensionComponentsModule extensionModule,
                MDSDBlackboardProvidingModule blackBoardModule) {
            return create(configuration, factoriesModule, extensionModule, blackBoardModule,
                    new TestConfigurationModule());
        }

        public abstract TestSimuLizarRootComponent create(@BindsInstance SimuLizarWorkflowConfiguration configuration,
                RootComponentFactoriesModule factoriesModule, ExtensionComponentsModule extensionModule,
                MDSDBlackboardProvidingModule blackBoardModule, TestConfigurationModule configModule);

    }

    @dagger.Module(includes = { SimuLizarRootModule.class, TestConfigurationModule.class })
    public static interface TestRootModule {
        @Binds
        SimuLizarRootComponent bindRootComponent(TestSimuLizarRootComponent impl);

        @Binds
        SimuLizarPrepareBlackboardJob bindPrepareBlackboardJob(TestSimuLizarPrepareBlackboardJob impl);

        @Binds
        LoadSimuLizarModelsIntoBlackboardJob bindPrepareLoadModelJob(TestSimuLizarLoadModelsJob impl);
    }

    @Module
    public static class TestConfigurationModule {
        public static final String ACTIVATE_MODEL_LOADING = "activate_model_loading";

        @Provides
        @Named(ACTIVATE_MODEL_LOADING)
        public boolean activateModelLoading() {
            return true;
        }
    }

}

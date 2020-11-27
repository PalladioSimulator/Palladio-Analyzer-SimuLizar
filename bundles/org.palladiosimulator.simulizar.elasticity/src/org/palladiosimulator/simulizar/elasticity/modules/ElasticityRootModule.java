package org.palladiosimulator.simulizar.elasticity.modules;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Optional;

import javax.inject.Qualifier;

import org.palladiosimulator.simulizar.DaggerSimuLizarRootComponent;
import org.palladiosimulator.simulizar.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.elasticity.DaggerRegisterCalculatorListenerExtension;
import org.palladiosimulator.simulizar.elasticity.ElasticityAnalysisExtension;
import org.palladiosimulator.simulizar.elasticity.RegisterCalculatorListenerExtension;
import org.palladiosimulator.simulizar.elasticity.aggregator.ReconfigurationTimeAggregatorWithConfidence;
import org.palladiosimulator.simulizar.elasticity.scope.ElasticityAnalysisScope;
import org.palladiosimulator.simulizar.modules.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.modules.custom.CustomSimuLizarExtensionsProvidingModule;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.statistics.StaticBatchAlgorithm;
import de.uka.ipd.sdq.statistics.estimation.SampleMeanEstimator;

@Module
public interface ElasticityRootModule {
    @Qualifier
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface NumberOfRunsLimit {
    }

    @Provides
    @NumberOfRunsLimit
    static int provideNumberOfRunsLimit() {
        return 50;
    }

    @Provides
    @ElasticityAnalysisScope
    static ReconfigurationTimeAggregatorWithConfidence bindAggregatorWithConfidence(
            SimuLizarWorkflowConfiguration config) {
        // [Krach] This is ugly but necessary due to the mixed up configuration hierarchy.
        var simComConfig = Optional.ofNullable(config.getSimulationConfiguration())
            .filter(SimuComConfig.class::isInstance)
            .map(SimuComConfig.class::cast)
            .orElseThrow(() -> new IllegalStateException(
                    "SimuLizar expects the simulation configuration to be of type SimuComConfig."));

        return new ReconfigurationTimeAggregatorWithConfidence(new StaticBatchAlgorithm(5, 5),
                new SampleMeanEstimator(), simComConfig.getConfidenceLevel() / 100.0,
                simComConfig.getConfidenceHalfWidth() / 100.0);
    }
    
    @Provides
    static RegisterCalculatorListenerExtension.Factory provideListenerFactory() {
        return DaggerRegisterCalculatorListenerExtension.factory();
    }

    @Provides
    static SimuLizarRootComponent provideRootComponent(SimuLizarWorkflowConfiguration configuration,
            ElasticityAnalysisExtension extension) {
        return DaggerSimuLizarRootComponent.builder()
            .simuLizarConfigurationModule(new SimuLizarConfigurationModule(configuration))
            .customSimuLizarExtensionsProvidingModule(CustomSimuLizarExtensionsProvidingModule.of(extension))
            .build();
    }
}

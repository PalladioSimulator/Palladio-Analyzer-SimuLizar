package org.palladiosimulator.simulizar.elasticity.di.modules;

import org.palladiosimulator.simulizar.di.modules.component.core.SimuLizarRuntimeModule;
import org.palladiosimulator.simulizar.di.scopes.AnalysisRootScope;
import org.palladiosimulator.simulizar.elasticity.aggregator.ReconfigurationTimeAggregatorWithConfidence;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simucomframework.core.SimuComConfig;
import de.uka.ipd.sdq.statistics.StaticBatchAlgorithm;
import de.uka.ipd.sdq.statistics.estimation.SampleMeanEstimator;

@Module(includes = {SimuLizarRuntimeModule.class})
public interface ElasticityAnalysisModule {

    @AnalysisRootScope
    @Provides
    static ReconfigurationTimeAggregatorWithConfidence provideAggregator(SimuComConfig configuration) {
        return new ReconfigurationTimeAggregatorWithConfidence(
                new StaticBatchAlgorithm(5, 5),
                new SampleMeanEstimator(), 
                configuration.getConfidenceLevel() / 100.0, // Confidence is specified in percent
                configuration.getConfidenceHalfWidth() / 100.0);
    }
    
}

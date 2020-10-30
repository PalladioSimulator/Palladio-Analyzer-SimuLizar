package org.palladiosimulator.simulizar.elasticity.modules;

import org.palladiosimulator.simulizar.elasticity.jobs.RunElasticityAnalysisJob.ProbeFrameworkListenerForElasticity;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public interface ElasticityModule {
    
    @Binds @IntoSet IInterpreterListener bindElasticityListener(ProbeFrameworkListenerForElasticity impl);

}

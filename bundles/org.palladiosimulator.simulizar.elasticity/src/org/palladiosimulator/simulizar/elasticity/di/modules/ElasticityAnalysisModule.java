package org.palladiosimulator.simulizar.elasticity.di.modules;

import org.palladiosimulator.simulizar.di.modules.component.core.SimuLizarRuntimeModule;
import org.palladiosimulator.simulizar.elasticity.jobs.RunElasticityAnalysisJob.ProbeFrameworkListenerForElasticity;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module(includes = {SimuLizarRuntimeModule.class})
public interface ElasticityAnalysisModule {
    
    @Binds @IntoSet IInterpreterListener bindElasticityListener(ProbeFrameworkListenerForElasticity impl);

}

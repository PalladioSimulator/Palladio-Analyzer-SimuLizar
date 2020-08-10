package org.palladiosimulator.simulizar.elasticity.jobs;

import javax.inject.Singleton;

import org.palladiosimulator.simulizar.elasticity.jobs.RunElasticityAnalysisJob.SimuLizarRuntimeStateElasticity;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;

import com.google.inject.AbstractModule;

public class SimuLizarRuntimeStateElasticityModule extends AbstractModule{
	@Override
	protected void configure() {
		bind(AbstractSimuLizarRuntimeState.class).to(SimuLizarRuntimeStateElasticity.class).in(Singleton.class);
	}
}

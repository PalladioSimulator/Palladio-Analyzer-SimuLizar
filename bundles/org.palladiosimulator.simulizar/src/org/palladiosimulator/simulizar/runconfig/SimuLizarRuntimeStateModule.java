package org.palladiosimulator.simulizar.runconfig;

import javax.inject.Singleton;

import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

import com.google.inject.AbstractModule;
/**
 * Module for binding AbstractRuntimeState
 * @author Jens Manig
 *
 */
public class SimuLizarRuntimeStateModule extends AbstractModule{
	@Override
	protected void configure() {
		bind(AbstractSimuLizarRuntimeState.class).to(SimuLizarRuntimeState.class).in(Singleton.class);
	}
}

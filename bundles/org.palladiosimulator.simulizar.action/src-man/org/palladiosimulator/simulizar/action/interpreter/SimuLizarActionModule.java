package org.palladiosimulator.simulizar.action.interpreter;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
/**
 * Module for dependency injection for RuntimeState
 * @author Jens Manig
 *
 */
public class SimuLizarActionModule extends AbstractModule{


	
	public SimuLizarActionModule() {
	}
		
	
	@Override
	protected void configure() {
		install(new FactoryModuleBuilder()
				.build(TransientEffectInterpreterBuilderFactory.class));
		install(new FactoryModuleBuilder()
				.build(TransientEffectInterpreterFactory.class));
	}
}

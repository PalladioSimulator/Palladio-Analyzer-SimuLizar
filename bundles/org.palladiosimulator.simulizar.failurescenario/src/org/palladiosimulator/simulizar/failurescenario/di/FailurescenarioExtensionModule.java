package org.palladiosimulator.simulizar.failurescenario.di;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.scopes.RuntimeExtensionScope;

import dagger.Binds;
import dagger.Module;

@Module
public interface FailurescenarioExtensionModule {
	@Binds
    @RuntimeExtensionScope
	InterpreterDefaultContext bindContext(InterpreterDefaultContext impl);
}

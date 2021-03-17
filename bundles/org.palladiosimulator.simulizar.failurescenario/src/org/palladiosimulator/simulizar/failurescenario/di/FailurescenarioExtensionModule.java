package org.palladiosimulator.simulizar.failurescenario.di;

import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultMerger;
import org.palladiosimulator.simulizar.interpreter.result.impl.BasicInterpreterResultMerger;
import org.palladiosimulator.simulizar.scopes.RuntimeExtensionScope;

import dagger.Binds;
import dagger.Module;

@Module
public interface FailurescenarioExtensionModule {
	@Binds
    @RuntimeExtensionScope
    InterpreterResultMerger bindResultMerger(BasicInterpreterResultMerger impl);
}

package org.palladiosimulator.simulizar.interpreter;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface UsageScenarioSwitchFactory {
    UsageScenarioSwitch create(final InterpreterDefaultContext context);
}

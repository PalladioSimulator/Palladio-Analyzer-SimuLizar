package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;

public interface ScenarioRunnerFactory {
	public ScenarioRunnerImpl create(final UsageScenario scenario);
}

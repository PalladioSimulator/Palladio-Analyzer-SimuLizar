package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;
/**
 * Factory interface for ScenarioRunnerImpl with assisted inject
 * @author Jens Manig
 *
 */
public interface ScenarioRunnerFactory {
	public ScenarioRunnerImpl create(final UsageScenario scenario);
}

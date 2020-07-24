package org.palladiosimulator.simulizar.interpreter;
/**
 * Factory interface for UsageScenarioSwitch with assisted inject
 * @author Jens Manig
 *
 */
public interface UsageScenarioSwitchFactory {
	 public UsageScenarioSwitch<Object> create(final InterpreterDefaultContext context);
}

package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

public interface SimulatedUsageModelsFactory {
	 SimulatedUsageModels create(final InterpreterDefaultContext rootContext);
}

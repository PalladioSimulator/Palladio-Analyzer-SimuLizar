package org.palladiosimulator.simulizar.failurescenario.interpreter;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.failurescenario.interpreter.strategies.FailureBehaviorChangingStrategy;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEventFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.SimpleEventBasedSimEntity;

public class FailureBehaviorChangingSimulationEntity extends SimpleEventBasedSimEntity {

	private static final Logger LOGGER = Logger.getLogger(FailureBehaviorChangingSimulationEntity.class);

	private final FailureBehaviorChangingStrategy fbChangingStrategy;

	protected FailureBehaviorChangingSimulationEntity(final ISimEventFactory eventFactory, final double firstOccurrence,
			final FailureBehaviorChangingStrategy fbChangingStrategy) {
		super(eventFactory, "FailureBehaviorChangingSimulationEntity");
		this.activate(firstOccurrence);
		this.fbChangingStrategy = fbChangingStrategy;
	}

	public double getSimulationTimeOfNextEventTrigger() {
		return this.getNextOccurence().get();
	}

	public void stopScheduling() {
		this.unschedule();
	}

	@Override
	protected Optional<Double> entityRoutine() {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Trigger for entity " + this.getClass().getName() + " occurred.");
		}
		triggerInternal();
		return Optional.empty();
	}

	protected void triggerInternal() {
		if (this.fbChangingStrategy.isValid()) {
			this.fbChangingStrategy.execute();
		}
	}
}

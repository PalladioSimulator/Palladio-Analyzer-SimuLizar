package org.palladiosimulator.simulizar.failurescenario.interpreter;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.interpreter.adapter.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.adapter.PreInterpretationBehaviorContainer;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEventFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.SimpleEventBasedSimEntity;

public class FailureOccurenceSimulationEntity extends SimpleEventBasedSimEntity {

	private static final Logger LOGGER = Logger.getLogger(FailureOccurenceSimulationEntity.class);

	private final PreInterpretationBehaviorContainer pIBContainer;
	private final PreInterpretationBehavior behavior;

	protected FailureOccurenceSimulationEntity(final ISimEventFactory eventFactory, final double firstOccurrence,
			PreInterpretationBehaviorContainer pIBContainer, PreInterpretationBehavior behavior) {
		super(eventFactory, "FailureOccurenceSimulationEntity");
		this.activate(firstOccurrence);
		this.pIBContainer = pIBContainer;
		this.behavior = behavior;
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
		this.pIBContainer.addBehavior(behavior);
	}
}

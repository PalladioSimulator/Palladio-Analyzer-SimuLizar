package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;

import com.google.inject.assistedinject.Assisted;

public interface LoopingUsageEvolverFactory {
	
	public LoopingUsageEvolver create(@Assisted("firstOccurrence") final double firstOccurrence, @Assisted("delay") final double delay, final UsageScenario evolvedScenario,
			@Assisted("timeOffset") double simulationTimeOffset);
}

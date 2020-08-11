package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;

import com.google.inject.assistedinject.Assisted;
/**
 * Factory interface for LoopingUsageEvolver with assisted injects
 * @author Jens Manig
 *
 */
public interface LoopingUsageEvolverFactory {
	
	public LoopingUsageEvolver create(@Assisted("firstOccurrence") final double firstOccurrence, @Assisted("delay") final double delay, final UsageScenario evolvedScenario,
			@Assisted("timeOffset") double simulationTimeOffset);
}

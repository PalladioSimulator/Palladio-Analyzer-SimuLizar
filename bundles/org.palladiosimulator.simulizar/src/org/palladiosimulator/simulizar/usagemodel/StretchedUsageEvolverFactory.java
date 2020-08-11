package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;

import com.google.inject.assistedinject.Assisted;
/**
 * Factory interface for StrechedUsageEvolverFactory with assisted inject
 * @author Jens Manig
 *
 */
public interface StretchedUsageEvolverFactory {
	 public StretchedUsageEvolver create(@Assisted("firstOccurrence") final double firstOccurrence, @Assisted("delay") final double delay, final UsageScenario evolvedScenario);
}

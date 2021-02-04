package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.entity.EntityReference;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface LoopingUsageEvolverFactory {
    LoopingUsageEvolver create(final double firstOccurrence, final double delay, double simulationTimeOffset,
            final EntityReference<UsageScenario> evolvedScenario);
}

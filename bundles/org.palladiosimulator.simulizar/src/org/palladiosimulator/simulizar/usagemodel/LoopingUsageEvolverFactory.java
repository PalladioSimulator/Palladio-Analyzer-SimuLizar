package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.core.entity.EntityReference;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface LoopingUsageEvolverFactory {
    LoopingUsageEvolver create(@Assisted("firstOccurrence") final double firstOccurrence,
            @Assisted("delay") final double delay, @Assisted("simulationTimeOffset") double simulationTimeOffset,
            final EntityReference<UsageScenario> evolvedScenario);
}

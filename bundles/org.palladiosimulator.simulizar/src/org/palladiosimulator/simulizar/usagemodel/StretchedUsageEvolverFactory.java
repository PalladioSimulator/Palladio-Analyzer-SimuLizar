package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.entity.EntityReference;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface StretchedUsageEvolverFactory {
    StretchedUsageEvolver create(final double firstOccurrence, final double delay,
            final EntityReference<UsageScenario> evolvedScenario);
}

package org.palladiosimulator.simulizar.runtimestate;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;

public class SimulatedCompositeComponentInstance extends SimulatedComponentInstance {

    @AssistedInject
    public SimulatedCompositeComponentInstance(@Assisted final String fqId) {
        super(fqId);
    }

}

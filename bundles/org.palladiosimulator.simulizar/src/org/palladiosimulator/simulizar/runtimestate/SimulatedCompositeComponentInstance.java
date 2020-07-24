package org.palladiosimulator.simulizar.runtimestate;

import javax.inject.Inject;

import com.google.inject.assistedinject.Assisted;

public class SimulatedCompositeComponentInstance extends SimulatedComponentInstance {
	@Inject
    public SimulatedCompositeComponentInstance(@Assisted final String fqId) {
        super(fqId);
    }

}

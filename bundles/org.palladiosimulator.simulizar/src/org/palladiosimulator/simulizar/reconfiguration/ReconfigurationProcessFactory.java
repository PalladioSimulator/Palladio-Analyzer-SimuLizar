package org.palladiosimulator.simulizar.reconfiguration;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface ReconfigurationProcessFactory {
    
    ReconfigurationProcess create();

}

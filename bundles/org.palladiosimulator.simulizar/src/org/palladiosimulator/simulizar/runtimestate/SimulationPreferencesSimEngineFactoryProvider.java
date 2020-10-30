package org.palladiosimulator.simulizar.runtimestate;

import javax.inject.Inject;
import javax.inject.Provider;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.preferences.SimulationPreferencesHelper;

public class SimulationPreferencesSimEngineFactoryProvider implements Provider<ISimEngineFactory> {

    @Inject
    public SimulationPreferencesSimEngineFactoryProvider() {
    }
    
    @Override
    public ISimEngineFactory get() {
        // load factory for the preferred simulation engine
        final ISimEngineFactory factory = SimulationPreferencesHelper.getPreferredSimulationEngine();
        if (factory == null) {
            throw new RuntimeException("There is no simulation engine available. Install at least one engine.");
        }
        return factory;
    }

}

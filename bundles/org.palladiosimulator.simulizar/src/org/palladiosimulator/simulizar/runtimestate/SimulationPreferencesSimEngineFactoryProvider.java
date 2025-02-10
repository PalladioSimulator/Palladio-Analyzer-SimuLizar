package org.palladiosimulator.simulizar.runtimestate;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.preferences.SimulationPreferencesHelper;

/**
 * This implementation provides a simulation engine factory based on a setting in the Preferences
 * Dialog within Eclipse IDE.
 */
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

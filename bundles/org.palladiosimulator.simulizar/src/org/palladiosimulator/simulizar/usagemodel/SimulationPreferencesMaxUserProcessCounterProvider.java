package org.palladiosimulator.simulizar.usagemodel;

import javax.inject.Inject;
import javax.inject.Provider;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;
import de.uka.ipd.sdq.simulation.preferences.SimulationPreferencesHelper;

public class SimulationPreferencesMaxUserProcessCounterProvider implements Provider<IUserProcessCountMonitor>  {

    private final ISimulationControl simulation;
    
    @Inject
    public SimulationPreferencesMaxUserProcessCounterProvider(ISimulationControl simulation) {
        this.simulation = simulation;
    }

    @Override
    public UserProcessCountMonitor get() {
        int maxProcessCount = SimulationPreferencesHelper.getMaximumUserProcessesCount();
        return new UserProcessCountMonitor(simulation, maxProcessCount);
    }
}

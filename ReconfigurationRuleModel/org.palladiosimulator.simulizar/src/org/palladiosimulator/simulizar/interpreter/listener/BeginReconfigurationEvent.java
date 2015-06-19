package org.palladiosimulator.simulizar.interpreter.listener;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

public class BeginReconfigurationEvent extends ReconfigurationEvent {

    public BeginReconfigurationEvent(double simulationTime) {
        super(EventType.BEGIN, simulationTime);
    }

    public BeginReconfigurationEvent(ISimulationControl simulationControl) {
        this(simulationControl.getCurrentSimulationTime());
    }

}

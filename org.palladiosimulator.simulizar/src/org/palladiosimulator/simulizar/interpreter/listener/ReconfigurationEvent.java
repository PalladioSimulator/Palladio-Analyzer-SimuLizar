package org.palladiosimulator.simulizar.interpreter.listener;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

public abstract class ReconfigurationEvent {

    private final double passageTime;
    private final EventType eventType;

    public ReconfigurationEvent(final EventType eventType, final ISimulationControl simulationcontrol) {
        super();
        this.eventType = eventType;
        this.passageTime = simulationcontrol.getCurrentSimulationTime();
    }

    public ReconfigurationEvent(final EventType eventType, final Double simulationTime) {
        super();
        this.eventType = eventType;
        this.passageTime = simulationTime;
    }

    /**
     * @return the passageTime
     */
    public double getPassageTime() {
        return this.passageTime;
    }

    public EventType getEventType() {
        return this.eventType;
    }

}

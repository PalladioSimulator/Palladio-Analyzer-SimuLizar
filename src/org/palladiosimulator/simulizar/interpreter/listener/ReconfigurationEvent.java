package org.palladiosimulator.simulizar.interpreter.listener;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class ReconfigurationEvent {

    private final double passageTime;
    private final EventType eventType;
    private final SimuComModel simuComModel;

    public ReconfigurationEvent(final EventType eventType, final SimuComModel simuComModel) {
        super();
        this.simuComModel = simuComModel;
        this.eventType = eventType;
        this.passageTime = simuComModel.getSimulationControl().getCurrentSimulationTime();
    }

    /**
     * @return the passageTime
     */
    public double getPassageTime() {
        return this.passageTime;
    }

    /**
     * @return the thread
     */
    public SimuComModel getModel() {
        return this.simuComModel;
    }

    public EventType getEventType() {
        return this.eventType;
    }

}

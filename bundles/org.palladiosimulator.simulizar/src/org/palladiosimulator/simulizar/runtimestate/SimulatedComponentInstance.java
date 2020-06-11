package org.palladiosimulator.simulizar.runtimestate;

public abstract class SimulatedComponentInstance {

    private final String myID;

    protected SimulatedComponentInstance(final String fqID) {
        super();
        this.myID = fqID;
    }

    public String getFqn() {
        return this.myID;
    }

    /**
     * Called upon shut down of the simulation in order to notify component instances of
     * the necessity to clean up simulation runtime state (e.g. passive resources)
     */
    public void cleanUp() {
    	
    }
}

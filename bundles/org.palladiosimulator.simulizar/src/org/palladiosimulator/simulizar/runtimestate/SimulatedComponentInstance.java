package org.palladiosimulator.simulizar.runtimestate;

public abstract class SimulatedComponentInstance {

    private final String myID;
    private AbstractSimuLizarRuntimeState runtimeState;

    protected SimulatedComponentInstance(final AbstractSimuLizarRuntimeState runtimeState, final String fqID) {
        super();
        this.runtimeState = runtimeState;
        this.myID = fqID;
    }

    public String getFqn() {
        return this.myID;
    }

    /**
     * @return the runtimeState
     */
    protected final AbstractSimuLizarRuntimeState getRuntimeState() {
        return this.runtimeState;
    }
    
    /**
     * Called upon shut down of the simulation in order to notify component instances of
     * the necessity to clean up simulation runtime state (e.g. passive resources)
     */
    public void cleanUp() {
    	
    }
}

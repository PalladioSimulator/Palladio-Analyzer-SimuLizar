package org.palladiosimulator.simulizar.runtimestate;

import java.util.List;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;

public abstract class SimulatedComponentInstance {

    private final FQComponentID myID;
    private AbstractSimuLizarRuntimeState runtimeState;

    /**
     * @param myID
     */
    protected SimulatedComponentInstance(final List<AssemblyContext> assemblyContextPath) {
        super();
        this.myID = new FQComponentID(assemblyContextPath);
    }

    protected SimulatedComponentInstance(final AbstractSimuLizarRuntimeState runtimeState, final FQComponentID fqID) {
        super();
        this.runtimeState = runtimeState;
        this.myID = fqID;
    }

    public FQComponentID getFqn() {
        return this.myID;
    }

    /**
     * @return the runtimeState
     */
    protected final AbstractSimuLizarRuntimeState getRuntimeState() {
        return this.runtimeState;
    }
}

package org.palladiosimulator.simulizar.runtimestate;

import java.util.List;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;

public abstract class SimulatedComponentInstance {

    private final FQComponentID myID;
    private SimuLizarRuntimeStateAbstract runtimeState;

    /**
     * @param myID
     */
    protected SimulatedComponentInstance(final List<AssemblyContext> assemblyContextPath) {
        super();
        this.myID = new FQComponentID(assemblyContextPath);
    }

    protected SimulatedComponentInstance(final SimuLizarRuntimeStateAbstract runtimeState, final FQComponentID fqID) {
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
    protected final SimuLizarRuntimeStateAbstract getRuntimeState() {
        return this.runtimeState;
    }
}

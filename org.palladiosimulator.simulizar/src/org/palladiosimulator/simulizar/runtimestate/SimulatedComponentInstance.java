package org.palladiosimulator.simulizar.runtimestate;

import java.util.List;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;

public abstract class SimulatedComponentInstance {

    private final FQComponentID myID;
    private SimuComRuntimeState runtimeState;

    /**
     * @param myID
     */
    protected SimulatedComponentInstance(final List<AssemblyContext> assemblyContextPath) {
        super();
        this.myID = new FQComponentID(assemblyContextPath);
    }

    protected SimulatedComponentInstance(final SimuComRuntimeState runtimeState, final FQComponentID fqID) {
        super();
        this.runtimeState = runtimeState;
        this.myID = fqID;
    }

    public FQComponentID getFqn() {
        return myID;
    }

    /**
     * @return the runtimeState
     */
    protected final SimuComRuntimeState getRuntimeState() {
        return runtimeState;
    }
}

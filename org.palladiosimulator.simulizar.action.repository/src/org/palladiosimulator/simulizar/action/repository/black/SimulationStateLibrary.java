package org.palladiosimulator.simulizar.action.repository.black;

import java.util.Optional;

import org.eclipse.m2m.qvt.oml.blackbox.java.Operation;
import org.eclipse.m2m.qvt.oml.blackbox.java.Operation.Kind;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeStateAbstract;

public class SimulationStateLibrary {

    private static Optional<SimuLizarRuntimeStateAbstract> state = Optional.empty();

    public SimulationStateLibrary() {
    }

    /**
     * Gets the current simulation (in {@code s}).
     * 
     * @return The current simulation time, in terms of a nonnegative double value.
     * @throws RuntimeException
     *             In case the simulation time could not be obtained.
     */
    @Operation(kind = Kind.HELPER)
    public static double getSimulationTime() {
        return state.map(s -> s.getModel().getSimulationControl().getCurrentSimulationTime()).orElseThrow(
                () -> new RuntimeException("Could not query simulation time. Maybe runtime state has not been set!"));
    }

    static void injectRuntimeStateModel(SimuLizarRuntimeStateAbstract state) {
        SimulationStateLibrary.state = Optional.ofNullable(state);
    }
}

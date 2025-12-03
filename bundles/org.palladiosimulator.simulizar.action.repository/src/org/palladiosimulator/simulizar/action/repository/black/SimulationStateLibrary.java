package org.palladiosimulator.simulizar.action.repository.black;

import java.util.Optional;
import java.util.Random;

import org.eclipse.m2m.qvt.oml.blackbox.java.Operation;
import org.eclipse.m2m.qvt.oml.blackbox.java.Operation.Kind;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public class SimulationStateLibrary {

    private static Optional<SimuLizarRuntimeState> state = Optional.empty();

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
    
    /**
     * Returns random value in range.
     * No random number functionality in QVTO.
     * 
     * @param  min : int inclusive
     * @param  max : int exclusive
     * @return Random int in range [min, max[ where min is inclusive, max exclusive.
     */
    @Operation(kind = Kind.HELPER)
    public static int getRandomNumber(int min, int max) {
    	Random random = new Random();
    	if (min == max) return min;
        return random.ints(min, max)
          .findFirst()
          .getAsInt();
    }

    static void injectRuntimeStateModel(SimuLizarRuntimeState state) {
        SimulationStateLibrary.state = Optional.ofNullable(state);
    }
}

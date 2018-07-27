package org.palladiosimulator.simulizar.runtimestate;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * This class delegates the decision if the simulation should be cancelled to a 
 * provided function. Its purpose is to decouple the SimulizarRuntimeState from
 * eclipse specific process logic.
 * 
 * @author Sebastian Krach
 */
public class SimulationCancelationDelegate  {
    private Supplier<Boolean> canceledProvider;

    public SimulationCancelationDelegate(Supplier<Boolean> cancelledProvider) {
        Objects.requireNonNull(cancelledProvider);
        this.canceledProvider = cancelledProvider;        
    }
    
    public boolean isCanceled() {
        return canceledProvider.get();
    }
}

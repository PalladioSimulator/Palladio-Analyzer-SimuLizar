package org.palladiosimulator.simulizar.runtimestate;

/**
 * This class delegates the decision if the simulation should be cancelled to a provided function.
 * Its purpose is to decouple the SimulizarRuntimeState from eclipse specific process logic.
 * 
 * @author Sebastian Krach
 */
@FunctionalInterface
public interface SimulationCancelationDelegate {

    public boolean isCanceled();
    
}

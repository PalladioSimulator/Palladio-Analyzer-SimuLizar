package org.palladiosimulator.simulizar.runtimestate;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.interpreter.adapter.PreInterpretationBehaviorAdapter;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultMerger;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;

/**
 * Manager to exchange behavior for the interpretation of certain simulation entities between
 * different interpreters. For the id of every simulated entity one adapter is mapped to store
 * PreInterpretationBehavior.
 * 
 * @author Jonas Lehmann
 *
 */

@SimulationRuntimeScope
public class PreInterpretationBehaviorManager implements RuntimeStateEntityManager {

    private final Map<String, PreInterpretationBehaviorAdapter> adapterIdStorage = new HashMap<>();
    private final InterpreterResultMerger merger;

    @Inject
    public PreInterpretationBehaviorManager(InterpreterResultMerger merger) {
        this.merger = merger;
    }

    /**
     * Returns an adapter for the simulated entity with the given id. Creates a new Adapter if there
     * is not one already. Behaviors can then be added on the adapter to be executed later in the
     * interpreter.
     * 
     * @return the adapter for the simulated entity with the given id
     */
    public PreInterpretationBehaviorAdapter getAdapterForEntity(String id) {
        this.adapterIdStorage.putIfAbsent(id, new PreInterpretationBehaviorAdapter(this.merger));
        return adapterIdStorage.get(id);
    }

    /**
     * Checks if an adapter has already been registered for this entity.
     * 
     * @param id the ID of the simulated entity
     * @return true if an adapter exists for the specified entity
     */
    public boolean hasAdapterForEntityAlreadyBeenCreated(String id) {
        return this.adapterIdStorage.containsKey(id);
    }

    @Override
    public void cleanup() {
        adapterIdStorage.clear();
    }
}

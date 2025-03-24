package org.palladiosimulator.simulizar.runtimestate;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.emf.ecore.EClass;
import org.palladiosimulator.simulizar.di.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehaviorContainer;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultMerger;

/**
 * Manager to exchange behavior for the interpretation of certain simulation entities between
 * different interpreters. For the id of every simulated entity one container is mapped to store
 * PreInterpretationBehaviors.
 * 
 * @author Jonas Lehmann
 *
 */

@SimulationRuntimeScope
public class PreInterpretationBehaviorManager implements RuntimeStateEntityManager {

    private final Map<String, PreInterpretationBehaviorContainer> containerIdStorage = new HashMap<>();
    private final Map<EClass, PreInterpretationBehaviorContainer> containerTypeStorage = new HashMap<>();
    private final InterpreterResultMerger merger;

    @Inject
    public PreInterpretationBehaviorManager(InterpreterResultMerger merger) {
        this.merger = merger;
    }

    /**
     * Returns a container for the simulated entity with the given id. Creates a new Container if
     * there is not one already. Behaviors can then be added into the container to be executed later
     * in the interpreter.
     * 
     * @return the container for the simulated entity with the given id
     */
    public PreInterpretationBehaviorContainer getContainerForEntity(String id) {
        this.containerIdStorage.putIfAbsent(id, new PreInterpretationBehaviorContainer(this.merger));
        return containerIdStorage.get(id);
    }

    /**
     * Returns a container for the simulated entity with the given id. Creates a new Container if
     * there is not one already. Behaviors can then be added into the container to be executed later
     * in the interpreter.
     * 
     * @param object
     *            the type of EObject for which the lookup should be done
     * @return the container for the simulated entity with the given id
     */
    public PreInterpretationBehaviorContainer getContainerForEntity(EClass object) {
        this.containerTypeStorage.putIfAbsent(object, new PreInterpretationBehaviorContainer(this.merger));
        return containerTypeStorage.get(object);
    }

    /**
     * Checks if a container has already been registered for this entitys id.
     * 
     * @param id
     *            the ID of the simulated entity
     * @return true if a container exists for the specified entity
     */
    public boolean hasContainerAlreadyBeenRegisteredForEntity(String id) {
        return this.containerIdStorage.containsKey(id);
    }

    /**
     * Checks if a container has already been registered for this type of EObject.
     * 
     * @param object
     *            the type of EObject for which the lookup should be done
     * @return true if a container exists for the specified entity
     */
    public boolean hasContainerAlreadyBeenRegisteredForEntity(EClass object) {
        return this.containerTypeStorage.containsKey(object);
    }

    @Override
    public void cleanup() {
        containerIdStorage.clear();
    }
}

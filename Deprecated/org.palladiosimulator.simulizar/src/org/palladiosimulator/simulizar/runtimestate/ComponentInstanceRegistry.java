package org.palladiosimulator.simulizar.runtimestate;

import java.util.HashMap;
import java.util.Map;

public class ComponentInstanceRegistry {

    private final Map<FQComponentID, SimulatedComponentInstance> componentInstances;

    /**
     * 
     */
    protected ComponentInstanceRegistry() {
        super();
        this.componentInstances = new HashMap<FQComponentID, SimulatedComponentInstance>();
    }

    public void addComponentInstance(final SimulatedComponentInstance instance) {
        if (instance == null) {
            throw new IllegalArgumentException("Null not allowed as component instance");
        }
        if (componentInstances.containsKey(instance.getFqn())) {
            throw new IllegalArgumentException("Component Instance already exists");
        }
        componentInstances.put(instance.getFqn(), instance);
    }

    public void removeComponentInstance(final SimulatedComponentInstance instance) {
        if (instance == null) {
            throw new IllegalArgumentException("Null not allowed as component instance");
        }
        if (!componentInstances.containsKey(instance.getFqn())) {
            throw new IllegalArgumentException("Component Instance must exists");
        }
        componentInstances.remove(instance.getFqn());
    }

    public boolean hasComponentInstance(final FQComponentID id) {
        if (id == null) {
            throw new IllegalArgumentException("Null not allowed as component instance");
        }
        return componentInstances.containsKey(id);
    }

    public SimulatedComponentInstance getComponentInstance(final FQComponentID id) {
        if (id == null) {
            throw new IllegalArgumentException("Null not allowed as component instance");
        }
        if (!componentInstances.containsKey(id)) {
            throw new IllegalArgumentException("Component Instance must exists");
        }
        return componentInstances.get(id);
    }
}

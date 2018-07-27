package org.palladiosimulator.simulizar.runtimestate;

import java.util.HashMap;
import java.util.Map;

public class ComponentInstanceRegistry {

    private final Map<String, SimulatedComponentInstance> componentInstances;

    /**
     *
     */
    protected ComponentInstanceRegistry() {
        super();
        this.componentInstances = new HashMap<String, SimulatedComponentInstance>();
    }

    public void addComponentInstance(final SimulatedComponentInstance instance) {
        if (instance == null) {
            throw new IllegalArgumentException("Null not allowed as component instance");
        }
        if (this.componentInstances.containsKey(instance.getFqn())) {
            throw new IllegalArgumentException("Component Instance already exists");
        }
        this.componentInstances.put(instance.getFqn(), instance);
    }

    public void removeComponentInstance(final SimulatedComponentInstance instance) {
        if (instance == null) {
            throw new IllegalArgumentException("Null not allowed as component instance");
        }
        if (!this.componentInstances.containsKey(instance.getFqn())) {
            throw new IllegalArgumentException("Component Instance must exists");
        }
        this.componentInstances.remove(instance.getFqn());
    }

    public boolean hasComponentInstance(final FQComponentID id) {
        if (id == null) {
            throw new IllegalArgumentException("Null not allowed as component instance");
        }
        return this.componentInstances.containsKey(id.getFQIDString());
    }

    public SimulatedComponentInstance getComponentInstance(final FQComponentID id) {
        if (id == null) {
            throw new IllegalArgumentException("Null not allowed as component instance");
        }
        String fqid = id.getFQIDString();
        if (!this.componentInstances.containsKey(fqid)) {
            throw new IllegalArgumentException("Component Instance must exists");
        }
        return this.componentInstances.get(fqid);
    }
    
    public void cleanUpInstancesAndRegistry() {
    	this.componentInstances.values().forEach(SimulatedComponentInstance::cleanUp);
    	this.componentInstances.clear();
    }
}

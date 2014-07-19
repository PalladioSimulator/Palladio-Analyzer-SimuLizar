package org.palladiosimulator.simulizar.runtimestate;

import org.palladiosimulator.simulizar.interpreter.EventNotificationHelper;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

/**
 * This class provides access to all simulation and simulizar related objects. This includes access
 * to the original SimuComModel (containing the simulated resources, simulated processes, etc.), to
 * simulizars central simulator event distribution object, and to simulated component instances
 * (e.g. to access their current state of passive ressources, etc.).
 * 
 * Per simulation run, there should be exactly one instance of this class and all of its manged
 * information objects.
 * 
 * @author Steffen Becker
 *
 */
public class SimuComRuntimeState {

    private final SimuComModel model;
    private final EventNotificationHelper eventHelper;
    private final ComponentInstanceRegistry componentInstanceRegistry;

    /**
     * @param model
     */
    public SimuComRuntimeState(SimuComModel model) {
        super();
        this.model = model;
        this.eventHelper = new EventNotificationHelper();
        this.componentInstanceRegistry = new ComponentInstanceRegistry();
    }

    /**
     * @return the model
     */
    public final SimuComModel getModel() {
        return model;
    }

    public EventNotificationHelper getEventNotificationHelper() {
        return this.eventHelper;
    }

    /**
     * @return the componentInstanceRegistry
     */
    public final ComponentInstanceRegistry getComponentInstanceRegistry() {
        return componentInstanceRegistry;
    }
}

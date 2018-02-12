package org.palladiosimulator.simulizar.interpreter.listener;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.simucomframework.Context;


public class ModelElementPassedEvent<T extends EObject> extends SimulationEvent {

    private final T modelElement;
    private final EventType eventType;
    
    public ModelElementPassedEvent(final T modelElement, final EventType eventType, final Context context) {
        super(context.getThread());
        this.modelElement = modelElement;
        this.eventType = eventType;
    }

    /**
     * @return the modelElement
     */
    public T getModelElement() {
        return this.modelElement;
    }

    public EventType getEventType() {
        return this.eventType;
    }
}

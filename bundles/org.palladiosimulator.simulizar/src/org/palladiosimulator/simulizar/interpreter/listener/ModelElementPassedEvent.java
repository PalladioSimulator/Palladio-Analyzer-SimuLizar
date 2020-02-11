package org.palladiosimulator.simulizar.interpreter.listener;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.simucomframework.Context;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

public class ModelElementPassedEvent<T extends EObject> {

    private final T modelElement;
    private final double passageTime;
    private final EventType eventType;
    private final Context context;
    
    public ModelElementPassedEvent(final T modelElement, final EventType eventType, final Context context) {
        super();
        this.modelElement = modelElement;
        this.context = context;
        this.eventType = eventType;
        this.passageTime = context.getThread().getModel().getSimulationControl().getCurrentSimulationTime();
    }

    /**
     * @return the modelElement
     */
    public T getModelElement() {
        return this.modelElement;
    }

    /**
     * @return the passageTime
     */
    public double getPassageTime() {
        return this.passageTime;
    }

    /**
     *  @return the context
     */
    public Context getContext() {
    	return this.context;
    }
    
    /**
     * @return the thread
     */
    public SimuComSimProcess getThread() {
        return this.context.getThread();
    }

    public EventType getEventType() {
        return this.eventType;
    }
}

package de.upb.pcm.simulizar.interpreter.listener;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

public class ModelElementPassedEvent<T extends EObject> {

	private final T modelElement;
	private final double passageTime;
	private final EventType eventType;
	private final SimuComSimProcess thread;

	public ModelElementPassedEvent(final T modelElement, final EventType eventType, final SimuComSimProcess thread)
	{
		super();
		this.modelElement = modelElement;
		this.thread = thread;
		this.eventType = eventType;
		this.passageTime = thread.getModel().getSimulationControl().getCurrentSimulationTime();
	}

	/**
	 * @return the modelElement
	 */
	public T getModelElement() {
		return modelElement;
	}

	/**
	 * @return the passageTime
	 */
	public double getPassageTime() {
		return passageTime;
	}

	/**
	 * @return the thread
	 */
	public SimuComSimProcess getThread() {
		return thread;
	}

	public EventType getEventType() {
		return eventType;
	}
}

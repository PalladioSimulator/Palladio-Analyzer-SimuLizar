package org.palladiosimulator.simulizar.interpreter.listener;

public interface IEventListener<T extends SimulationEvent> {

	void eventOccurred(T event);
	
	Class<? extends T> getEventType();
	
}

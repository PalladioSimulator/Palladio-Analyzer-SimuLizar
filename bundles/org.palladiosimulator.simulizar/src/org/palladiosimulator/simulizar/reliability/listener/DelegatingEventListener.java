package org.palladiosimulator.simulizar.reliability.listener;

import java.util.function.Consumer;

import org.palladiosimulator.simulizar.interpreter.listener.IEventListener;
import org.palladiosimulator.simulizar.interpreter.listener.SimulationEvent;

public class DelegatingEventListener<T extends SimulationEvent> implements IEventListener<T> {

	private Consumer<T> delegate;
	private Class<? extends T> eventType;
	
	
	
	public DelegatingEventListener(Class<? extends T> eventType, Consumer<T> listenerFunction) {
		super();
		this.delegate = listenerFunction;
		this.eventType = eventType;
	}

	@Override
	public void eventOccurred(T event) {
		delegate.accept(event);
	}

	@Override
	public Class<? extends T> getEventType() {
		return eventType;
	}

}

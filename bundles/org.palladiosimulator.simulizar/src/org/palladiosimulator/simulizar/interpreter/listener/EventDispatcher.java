package org.palladiosimulator.simulizar.interpreter.listener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.palladiosimulator.commons.designpatterns.AbstractObservable;

public class EventDispatcher extends AbstractObservable<IEventListener<?>>{
	
	/**
	 * Maps concrete event types to the corresponding subset of listeners
	 */
	private Map<Class<?>,Set<IEventListener<?>>> observersCache;
	
	public EventDispatcher() {
		observersCache = new HashMap<Class<?>, Set<IEventListener<?>>>();
	}
	
	@Override
	public void addObserver(IEventListener<?> observer) {
		super.addObserver(observer);
		observersCache.forEach((evClass,subset) -> {
			if(observer.getEventType().isAssignableFrom(evClass)) {
				subset.add(observer);
			}
		}); 
	}

	@Override
	public void removeAllObserver() {
		observersCache.clear();
	}

	@Override
	public void removeObserver(IEventListener<?> observer) {
		super.removeObserver(observer);
		observersCache.forEach((evClass,subset) -> subset.remove(observer)); 
	}

	@SuppressWarnings("unchecked") //Cast is safe through reflection type checking
	public void fireEvent(SimulationEvent event) {
		Class<? extends SimulationEvent> evClass = event.getClass();
		Set<IEventListener<?>> listeners = observersCache.computeIfAbsent(evClass, (cl) -> {
			return getObservers().stream()
			.filter((o) -> o.getEventType().isAssignableFrom(cl))
			.collect(Collectors.toCollection(HashSet::new));
		});
		listeners.forEach( lis -> ((IEventListener<SimulationEvent>) lis).eventOccurred(event)); 
	}
	
}

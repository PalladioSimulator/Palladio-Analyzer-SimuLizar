package org.palladiosimulator.simulizar.interpreter.listener;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.simucomframework.Context;

public interface ModelElementPassedEventFactory {
	 public <T extends EObject>ModelElementPassedEvent<T> create(final T modelElement, final EventType eventType, final Context context);
}

package org.palladiosimulator.simulizar.interpreter.listener;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.simucomframework.Context;

public class ModelElementPassedEventFactoryImpl implements ModelElementPassedEventFactory{

	@Override
	public <T extends EObject>ModelElementPassedEvent<T> create(final T modelElement,final EventType eventType, final Context context) {
		return new ModelElementPassedEvent<>(modelElement, eventType, context);
	}

}

package org.palladiosimulator.simulizar.lafore.eurema.main;

import de.mdelab.eurema.interpreter.EuremaInterpreter;
import de.mdelab.eurema.interpreter.EuremaInterpreterFactory;
import de.mdelab.eurema.interpreter.EventQueue;
import eurema.EuremaFactory;

public class StartEuremaInterpreter {

	public static void main(String[] args) {
		EuremaInterpreter interpreter = EuremaInterpreterFactory.getInstance();
		EventQueue q = interpreter
				.execute("../euremaInstance/Lafore.eurema");

		eurema.Event startingEvent = EuremaFactory.eINSTANCE.createEvent();
		startingEvent.setName("StartMape");
		eurema.EventType startingEventType = EuremaFactory.eINSTANCE.createEventType();
		startingEventType.setType("StartMapeEvent");
		startingEvent.setType(startingEventType);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// ============================================================

		System.out.println("==============================");
		System.out.println();
		System.out.println("Added StartMape event.");
		System.out.println();
		System.out.println("==============================");

		q.add(startingEvent);


	}

}

package de.mdelab.eurema.test.dummyselfrepair.main;

import de.mdelab.eurema.interpreter.EuremaInterpreter;
import de.mdelab.eurema.interpreter.EuremaInterpreterFactory;
import de.mdelab.eurema.interpreter.EventQueue;
import eurema.EuremaFactory;

public class RunEuremaInterpreter {

	public static void main(String[] args) {

		EuremaInterpreter interpreter = EuremaInterpreterFactory.getInstance();
		EventQueue q = interpreter
				.execute("../EUREMA-Examples/model/dummy-self-repair/eurema-models/Self-repair-Architecture.eurema");

		eurema.Event rtException = EuremaFactory.eINSTANCE.createEvent();
		rtException.setName("");
		eurema.EventType rtExceptionType = EuremaFactory.eINSTANCE
				.createEventType();
		rtExceptionType.setType("RtException");
		rtException.setType(rtExceptionType);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// ============================================================

		System.out.println("==============================");
		System.out.println();
		System.out.println("Added 1st event.");
		System.out.println();
		System.out.println("==============================");

		q.add(rtException);

		// ============================================================

		try {
			// Trigger has a period of 10s.
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// ============================================================

		System.out.println("==============================");
		System.out.println();
		System.out.println("Added 2nd event.");
		System.out.println();
		System.out.println("==============================");

		q.add(rtException);

		// ============================================================

		try {
			// Trigger has a period of 10s.
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// ============================================================

		System.out.println("==============================");
		System.out.println();
		System.out.println("Added 3rd event.");
		System.out.println();
		System.out.println("==============================");

		q.add(rtException);

		// ============================================================
		
		System.out.println("==============================");
		System.out.println();
		System.out.println("Added 4th, 5th, and 6th event.");
		System.out.println();
		System.out.println("==============================");
		
		q.add(rtException);
		q.add(rtException);
		q.add(rtException);
		
		// ============================================================
	}

}

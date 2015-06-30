package org.palladiosimulator.simulizar.lafore.eurema.interpreter;

public class SimuLizarEuremaInterpreterFactory {

	/**
	 * Singleton reference to the EUREMA interpreter.
	 */
	private final static SimuLizarEuremaInterpreter euremaInterpreter = new SimuLizarEuremaInterpreterImpl();

	/**
	 * Private constructor.
	 */
	private SimuLizarEuremaInterpreterFactory() {

	}

	/**
	 * Returns an EUREMA interpreter instance.
	 * 
	 * @return a EUREMA interpreter instance.
	 */
	public static SimuLizarEuremaInterpreter getInstance() {
		return euremaInterpreter;
	}
}

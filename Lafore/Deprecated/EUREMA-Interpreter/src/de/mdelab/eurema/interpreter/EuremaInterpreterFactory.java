package de.mdelab.eurema.interpreter;

/**
 * Factory to obtain an instance of the EUREMA interpreter.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public class EuremaInterpreterFactory {

	/**
	 * Singleton reference to the EUREMA interpreter.
	 */
	private final static EuremaInterpreter euremaInterpreter =
			new EuremaInterpreterImpl();

	/**
	 * Private constructor.
	 */
	private EuremaInterpreterFactory() {

	}

	/**
	 * Returns an EUREMA interpreter instance.
	 * 
	 * @return a EUREMA interpreter instance.
	 */
	public static EuremaInterpreter getInstance() {
		return euremaInterpreter;
	}

}

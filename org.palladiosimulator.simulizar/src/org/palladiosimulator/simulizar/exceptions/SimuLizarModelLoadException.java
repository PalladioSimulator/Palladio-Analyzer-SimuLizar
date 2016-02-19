package org.palladiosimulator.simulizar.exceptions;

/**
 * Class for exceptions while loading models necessary for SimuLizar
 * 
 * @author Matthias Becker
 * 
 */
public class SimuLizarModelLoadException extends RuntimeException {

	/**
	* 
	*/
	private static final long serialVersionUID = 894825899470628660L;

	/**
	 * @param message
	 *            the exception message.
	 */
	public SimuLizarModelLoadException(final String message) {
		super(message);
	}

}

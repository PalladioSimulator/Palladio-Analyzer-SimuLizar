package de.mdelab.eurema.interpreter.models;

/**
 * Runtime exception if a severe failure occurs in the EUREMA model repository.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public class ModelRepositoryException extends RuntimeException {

	/**
	 * Serial version uid.
	 */
	private static final long serialVersionUID = 3264381667990491574L;

	/**
	 * Constructor.
	 * 
	 * @param msg
	 *            failure message.
	 */
	public ModelRepositoryException(String msg) {
		super(msg);
	}

	/**
	 * Constructor.
	 * 
	 * @param msg
	 *            failure message.
	 * @param cause
	 *            the cause for raising this exception.
	 */
	public ModelRepositoryException(String msg, Throwable cause) {
		super(msg, cause);
	}

}

package de.mdelab.eurema.interpreter;

/**
 * Runtime exception thrown by the EUREMA interpreter if a severe failure occurs
 * in executing an EUREMA model.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public class EuremaInterpreterException extends RuntimeException {

	/**
	 * Serial version uid.
	 */
	private static final long serialVersionUID = -4417108595588465268L;

	/**
	 * Constructor.
	 * 
	 * @param msg
	 *            failure message.
	 */
	public EuremaInterpreterException(String msg) {
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
	public EuremaInterpreterException(String msg, Throwable cause) {
		super(msg, cause);
	}

}

package de.mdelab.eurema.interpreter.execution.consistency;

/**
 * Runtime exception thrown if a failures occurs in managing consistency such as
 * non-reentrance or quiescence when executing an EUREMA model.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class QuiescenceManagmentException extends RuntimeException {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -118753489713464431L;

	/**
	 * Constructor.
	 * 
	 * @param msg
	 *            failure message.
	 */
	public QuiescenceManagmentException(String msg) {
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
	public QuiescenceManagmentException(String msg, Throwable cause) {
		super(msg, cause);
	}
}

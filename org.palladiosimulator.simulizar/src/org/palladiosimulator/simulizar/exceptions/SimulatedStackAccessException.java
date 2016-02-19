package org.palladiosimulator.simulizar.exceptions;

/**
 * Class for exceptions while accessing a simulated stack
 *
 * @author Joachim Meyer
 *
 */
public class SimulatedStackAccessException extends RuntimeException {

    /**
    *
    */
    private static final long serialVersionUID = -6239562275785227769L;

    /**
     * @param message
     *            the exception message.
     */
    public SimulatedStackAccessException(final String message) {
        super(message);

    }

}

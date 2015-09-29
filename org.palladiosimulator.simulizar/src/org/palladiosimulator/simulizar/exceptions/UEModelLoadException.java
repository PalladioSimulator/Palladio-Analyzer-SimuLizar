package org.palladiosimulator.simulizar.exceptions;

/**
 * Class for exceptions while loading a usage evolution model
 *
 * @author Erlend Stav
 *
 */
public class UEModelLoadException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -4464127374781785095L;

    /**
    *
    */

    /**
     * @param message
     *            the exception message.
     */
    public UEModelLoadException(final String message) {
        super(message);
    }

}

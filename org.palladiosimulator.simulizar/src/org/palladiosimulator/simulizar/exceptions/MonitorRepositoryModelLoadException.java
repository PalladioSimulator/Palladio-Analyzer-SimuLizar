package org.palladiosimulator.simulizar.exceptions;

/**
 * Class for exceptions while loading a Monitor Repository model
 *
 * @author Joachim Meyer
 *
 */
public class MonitorRepositoryModelLoadException extends RuntimeException {

    /**
    *
    */
    private static final long serialVersionUID = 894825899470628660L;

    /**
     * @param message
     *            the exception message.
     */
    public MonitorRepositoryModelLoadException(final String message) {
        super(message);
    }

}

package org.palladiosimulator.simulizar.exceptions;

/**
 * Class for exceptions while loading a pms model
 * 
 * @author Joachim Meyer
 * 
 */
public class PMSModelLoadException extends RuntimeException {

    /**
    * 
    */
    private static final long serialVersionUID = 894825899470628660L;

    /**
     * @param message
     *            the exception message.
     */
    public PMSModelLoadException(final String message) {
        super(message);
    }

}

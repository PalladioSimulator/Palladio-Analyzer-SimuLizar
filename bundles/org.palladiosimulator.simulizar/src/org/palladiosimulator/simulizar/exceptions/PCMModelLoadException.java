package org.palladiosimulator.simulizar.exceptions;

/**
 * Class for exceptions while loading a pcm model
 *
 * @author Joachim Meyer
 *
 */
public class PCMModelLoadException extends RuntimeException {

    /**
    *
    */
    private static final long serialVersionUID = 8553325364453147702L;

    /**
     * @param message
     *            the exception message.
     */
    public PCMModelLoadException(final String message) {
        super(message);

    }

}

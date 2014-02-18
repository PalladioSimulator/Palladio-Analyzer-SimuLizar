package org.palladiosimulator.simulizar.exceptions;

import de.mdelab.sdm.interpreter.core.SDMException;

/**
 * Class for exceptions while using a model access class.
 * 
 * @author Joachim Meyer
 * 
 */
public class PCMModelAccessException extends RuntimeException {

    /**
    * 
    */
    private static final long serialVersionUID = -739076690568932833L;

    /**
     * @param message
     *            the exception message.
     */
    public PCMModelAccessException(final String message) {
        super(message);

    }

    public PCMModelAccessException(String msg, SDMException e) {
        super(msg,e);
    }

}

package de.upb.pcm.simulizar.utils;

import org.apache.log4j.Logger;

/**
 * Logger for the pcm interpreter.
 * 
 * @author Joachim Meyer
 * 
 */
public class InterpreterLogger {

    public static void debug(final Logger logger, final String message) {
        if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }

    public static void info(final Logger logger, final String message) {
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }

}

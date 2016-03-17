package org.palladiosimulator.simulizar.reconfiguration.qvto;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.m2m.qvt.oml.util.Log;

/**
 * QVTo Reconfigurator Logging Class
 * 
 * @author Matthias Becker, Sebastian Krach
 *
 */
public class QVTOReconfigurationLogger implements Log {

    private static Logger logger;
    
    private static Level DEFAULT_LOG_LEVEL = Level.DEBUG;

    /**
     * 
     * @param clazz
     *            class for the LOGGER
     */
    public QVTOReconfigurationLogger(Class<?> clazz) {
        QVTOReconfigurationLogger.logger = Logger.getLogger(clazz);
    }

    @Override
    public void log(String message) {
        logger.log(DEFAULT_LOG_LEVEL, message);
    }

    @Override
    public void log(int logLevel, String message) {
        Level level = Level.toLevel(logLevel, DEFAULT_LOG_LEVEL);
        logger.log(level, message);
    }

    @Override
    public void log(String message, Object parameter) {
        logger.log(DEFAULT_LOG_LEVEL, String.format(message, parameter));
    }

    @Override
    public void log(int logLevel, String message, Object parameter) {
        Level level = Level.toLevel(logLevel, DEFAULT_LOG_LEVEL);
        logger.log(level, String.format(message, parameter));
    }

}

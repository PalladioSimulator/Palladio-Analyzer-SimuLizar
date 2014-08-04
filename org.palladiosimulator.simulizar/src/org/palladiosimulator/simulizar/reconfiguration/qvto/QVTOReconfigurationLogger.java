package org.palladiosimulator.simulizar.reconfiguration.qvto;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.m2m.qvt.oml.util.Log;

/**
 * 
 * @author Matthias
 *
 */
public class QVTOReconfigurationLogger implements Log {

    private static Logger logger;

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
        logger.log(Level.INFO, message);

    }

    @Override
    public void log(int arg0, String arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void log(String arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void log(int arg0, String arg1, Object arg2) {
        // TODO Auto-generated method stub

    }

}

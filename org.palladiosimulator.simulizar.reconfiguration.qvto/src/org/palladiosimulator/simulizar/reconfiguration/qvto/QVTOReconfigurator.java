/**
 * 
 */
package org.palladiosimulator.simulizar.reconfiguration.qvto;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.eclipseutils.FileHelper;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.TransformationCache;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

/**
 * A reconfigurator implementation which relies on QVTo to do the reconfiguration. The QVTo rules
 * both check their reconfiguration precondition and perform the actual reconfiguration.
 * 
 * @author Matthias Becker
 *
 */
public class QVTOReconfigurator extends AbstractReconfigurator implements IReconfigurator {

    /**
     * This class' internal LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(QVTOReconfigurator.class);
    private static final String QVTO_FILE_EXTENSION = ".qvto";

    /**
     * QVTO Interpreter used internally to interpret the SDs.
     */
    private QVTOExecutor qvtoExecutor;

    /**
     * QVTO Reconfigurator default constructor.
     * 
     * @param modelAccessFactory
     *            ModelAccessFactory giving access to PCM and PRM models
     * @param configuration
     *            Simulation configuration
     */
    public QVTOReconfigurator() {
        super();
    }

    /**
     * QVTO Reconfigurator constructor.
     * 
     * @param modelAccessFactory
     *            ModelAccessFactory giving access to PCM and PRM models
     * @param configuration
     *            Simulation configuration
     */
    public QVTOReconfigurator(final IModelAccess modelAccessFactory, final SimuLizarWorkflowConfiguration configuration) {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.palladiosimulator.simulizar.reconfiguration.IReconfigurator#checkAndExecute(org.eclipse
     * .emf.ecore.EObject)
     */
    @Override
    public boolean checkAndExecute(EObject monitoredElement) {
        LOGGER.debug("Checking reconfiguration rules due to PRM change");
        final boolean result = this.getQVTOExecutor().executeRules(monitoredElement);
        LOGGER.debug(result ? "Reconfigured system by a matching rule"
                : "No reconfiguration rule was executed, all conditions were false");
        return result;
    }

    private QVTOExecutor getQVTOExecutor() {
        if (this.qvtoExecutor == null) {
            TransformationCache transformationCache = new TransformationCache(
                    getQvtoFiles(this.configuration.getReconfigurationRulesFolder()));
            this.qvtoExecutor = new QVTOExecutor(transformationCache, new QVToModelCache(this.modelAccessFactory));
        }
        return this.qvtoExecutor;
    }

    /**
     * Gets the QVTO files within the specified path.
     * 
     * @param path
     *            Path to reconfiguration rules.
     * @return The QVTO files within the given path. Returns an empty array in case no files are
     *         found.
     */
    private static URI[] getQvtoFiles(final String path) {
        assert path != null;
        if (path.equals("")) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No path to QVTo rules given.");
            }
            return new URI[0];
        }

        final URI[] uris = FileHelper.getURIs(path, QVTO_FILE_EXTENSION);

        if (uris.length == 0) {
            LOGGER.info("No QVTo rules found, QVTo reconfigurations disabled.");
        }

        return uris;
    }
}

/**
 * 
 */
package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.eclipseutils.FileHelper;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.ModelTransformation;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;
import org.palladiosimulator.simulizar.reconfigurationrule.qvto.ModelTransformationCache;
import org.palladiosimulator.simulizar.reconfigurationrule.qvto.QvtoModelTransformation;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

/**
 * A reconfigurator implementation which relies on QVTo to do the
 * reconfiguration. The QVTo rules both check their reconfiguration precondition
 * and perform the actual reconfiguration.
 * 
 * @author Matthias Becker
 *
 */
public class QVTOReconfigurator extends AbstractReconfigurator {

	/**
	 * This class' internal LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(QVTOReconfigurator.class);
	private static final String QVTO_FILE_EXTENSION = ".qvto";

	/**
	 * QVTO Interpreter used internally to interpret the SDs.
	 */
	private QVTOExecutor qvtoExecutor;
	private SimuLizarWorkflowConfiguration configuration;

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
	public QVTOReconfigurator(final IModelAccess modelAccessFactory, SimuLizarWorkflowConfiguration configuration) {
		super();
		this.modelAccessFactory = modelAccessFactory;
		this.configuration = configuration;
	}

	private QVTOExecutor getQVTOExecutor() {
		if (this.qvtoExecutor == null) {
			ModelTransformationCache transformationCache = new ModelTransformationCache(
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

	@Override
	public boolean runCheck(EList<org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation<?>> checks, EObject monitoredElement) {
		LOGGER.debug("Checking reconfiguration rules due to PRM change");
		ArrayList<QvtoModelTransformation> transformations = new ArrayList<QvtoModelTransformation>();
		for (org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation<?> check : checks) {
			try {
				transformations.add((QvtoModelTransformation) check);
			} catch (ClassCastException e) {
				LOGGER.debug("Not a QVTO model transformation.");
			}
		}
		boolean result = getQVTOExecutor().executeTransformations(transformations);
		LOGGER.debug(result ? "Reconfigured system by a matching rule"
				: "No reconfiguration rule was executed, all conditions were false");
		return result;
	}

	@Override
	public boolean runExecute(EList<org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation<?>> actions, EObject monitoredElement) {
		LOGGER.debug("Checking reconfiguration rules due to PRM change");
		ArrayList<QvtoModelTransformation> transformations = new ArrayList<QvtoModelTransformation>();
		for (org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation<?> action : actions) {
			try {
				transformations.add((QvtoModelTransformation) action);
			} catch (ClassCastException e) {
				LOGGER.debug("Not a QVTO model transformation.");
			}
		}
		boolean result = getQVTOExecutor().executeTransformations(transformations);
		LOGGER.debug(result ? "Reconfigured system by a matching rule"
				: "No reconfiguration rule was executed, all conditions were false");
		return result;
	}

	@Override
	public void setReconfigurator(Reconfigurator reconfigurator) {
		// TODO Auto-generated method stub

	}
}

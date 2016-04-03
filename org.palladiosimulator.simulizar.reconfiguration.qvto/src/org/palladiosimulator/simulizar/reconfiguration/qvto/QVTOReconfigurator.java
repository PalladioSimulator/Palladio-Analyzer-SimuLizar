/**
 * 
 */
package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.ModelTransformationCache;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;
import org.palladiosimulator.simulizar.reconfigurationrule.qvto.QvtoModelTransformation;

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
	public QVTOReconfigurator(final IModelAccess modelAccessFactory) {
		super();
		this.modelAccessFactory = modelAccessFactory;
	}

	private QVTOExecutor getQVTOExecutor() {
		if (this.qvtoExecutor == null) {
			this.qvtoExecutor = new QVTOExecutor(new ModelTransformationCache(), new QVToModelCache(this.modelAccessFactory));
		}
		return this.qvtoExecutor;
	}

	@Override
	public boolean runCheck(EList<? extends ModelTransformation<?>> checks, EObject monitoredElement) {
		LOGGER.debug("Checking reconfiguration rules due to PRM change");
		ArrayList<QvtoModelTransformation> transformations = new ArrayList<QvtoModelTransformation>();
		for (ModelTransformation<? extends Object> check : checks) {
			try {
				if (check instanceof QvtoModelTransformation) {
					transformations.add((QvtoModelTransformation) check);
				}
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
	public boolean runExecute(EList<? extends ModelTransformation<?>> actions, EObject monitoredElement) {
		LOGGER.debug("Checking reconfiguration rules due to PRM change");
		ArrayList<QvtoModelTransformation> transformations = new ArrayList<QvtoModelTransformation>();
		for (ModelTransformation<? extends Object> action : actions) {
			try {
				if (action instanceof QvtoModelTransformation) {
					transformations.add((QvtoModelTransformation) action);
				}
			} catch (ClassCastException e) {
				LOGGER.debug("Not a QVTO model transformation.");
			}
		}
		boolean result = getQVTOExecutor().executeTransformations(transformations);
		LOGGER.debug(result ? "Reconfigured system by a matching rule"
				: "No reconfiguration rule was executed, all conditions were false");
		return result;
	}
}

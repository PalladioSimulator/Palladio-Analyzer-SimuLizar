/**
 * 
 */
package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.ModelTransformationCache;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVToModelCache;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;

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
	 */
	public QVTOReconfigurator() {
        super();
    }
	
	private QVTOExecutor getQVTOExecutor() {
		if (this.qvtoExecutor == null) {
			this.qvtoExecutor = new QVTOExecutor(new ModelTransformationCache(), new QVToModelCache(this.pcmPartitionManager));
		}
		return this.qvtoExecutor;
	}

	@Override
	public boolean runCheck(EList<? extends ModelTransformation<? extends Object>> checks, EObject monitoredElement, IResourceTableManager resourceTableManager) {
		return this.runExecute(checks, monitoredElement, resourceTableManager);
	}

	@Override
	public boolean runExecute(EList<? extends ModelTransformation<? extends Object>> actions, EObject monitoredElement
	        , IResourceTableManager resourceTableManager) {
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
		boolean result = getQVTOExecutor().executeTransformations(transformations, resourceTableManager);
		LOGGER.debug(result ? "Reconfigured system by a matching rule"
				: "No reconfiguration rule was executed, all conditions were false");
		return result;
	}
}

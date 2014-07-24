/**
 * 
 */
package org.palladiosimulator.simulizar.reconfiguration.qvto;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.storydiagrams.SDReconfigurator;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

/**
 * A reconfigurator implementation which relies on QVTo to do the reconfiguration. The QVTo rules
 * both check their reconfiguration precondition and perform the actual reconfiguration.
 * 
 * @author Matthias Becker
 *
 */
public class QVTOReconfigurator implements IReconfigurator {

    /**
     * This class' internal LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SDReconfigurator.class);

    /**
     * QVTO Interpreter used internally to interpret the SDs.
     */
    private final QVTOExecutor qvtoExecutor;

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
        this.qvtoExecutor = new QVTOExecutor(modelAccessFactory, configuration);
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
        final boolean result = this.qvtoExecutor.executeRules(monitoredElement);
        LOGGER.debug(result ? "Reconfigured system by a matching rule"
                : "No reconfiguration rule was executed, all conditions were false");
        return result;
    }

}

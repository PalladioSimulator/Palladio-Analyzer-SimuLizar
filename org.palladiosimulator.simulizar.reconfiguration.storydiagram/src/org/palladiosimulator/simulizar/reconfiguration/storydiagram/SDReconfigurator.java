package org.palladiosimulator.simulizar.reconfiguration.storydiagram;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurator;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

/**
 * A reconfigurator implementation which relies on story diagrams to do the reconfiguration. The
 * story diagrams both check their reconfiguration precondition and perform the actual
 * reconfiguration.
 * 
 * @author snowball
 *
 */
public class SDReconfigurator implements IReconfigurator {

    /**
     * This class' internal LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SDReconfigurator.class);

    /**
     * Access interface to access all loaded SDs.
     */
    private IModelAccess modelAccess;

    /**
     * SD Interpreter used internally to interpret the SDs.
     */
    private SDExecutor sdExecutor;

    /**
     * SD Reconfigurator constructor.
     * 
     * @param modelAccessFactory
     *            Model access factory used to access the SDs.
     */
    public SDReconfigurator(final IModelAccess modelAccessFactory) {
        super();
        this.modelAccess = modelAccessFactory;
        this.sdExecutor = new SDExecutor(modelAccessFactory);
    }

    @Override
    public boolean checkAndExecute(final EObject monitoredElement) {
        if (!this.modelAccess.getStoryDiagrams().isEmpty()) {
            LOGGER.debug("Checking reconfiguration rules due to RuntimeMeasurement change");
            final boolean result = this.sdExecutor.executeActivities(monitoredElement);
            LOGGER.debug(result ? "Reconfigured system by a matching rule"
                    : "No reconfiguration rule was executed, all conditions were false");
            return result;
        } else {
            return false;
        }
    }

    private SDExecutor getSDExecutor() {
        if (this.sdExecutor == null) {
            this.sdExecutor = new SDExecutor(this.modelAccess);
        }
        return this.sdExecutor;
    }

    @Override
    public void setModelAccess(IModelAccess modelAccess) {
        this.modelAccess = modelAccess;
    }

    @Override
    public void setConfiguration(SimuLizarWorkflowConfiguration configuration) {
        // Nothing to do
    }
}

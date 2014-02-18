package org.palladiosimulator.simulizar.reconfiguration.storydiagrams;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.access.IModelAccessFactory;
import org.palladiosimulator.simulizar.access.SDAccess;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurator;

/**
 * A reconfigurator implementation which relies on story diagrams to do the reconfiguration. The
 * story diagrams both check their reconfiguration precondition and perform the actual reconfiguration.
 * 
 * @author snowball
 *
 */
public class SDReconfigurator implements IReconfigurator {

    /**
     * This class' internal logger. 
     */
    private static final Logger LOGGER = Logger.getLogger(SDReconfigurator.class);

    /**
     * Access interface to access all loaded SDs.
     */
    private final SDAccess sdAccess;

    /**
     * SD Interpreter used internally to interpret the SDs. 
     */
    private final SDExecutor sdExecutor;

    /**
     * SD Reconfigurator constructor.
     * @param modelAccessFactory Model access factory used to access the SDs.
     */
    public SDReconfigurator(final IModelAccessFactory modelAccessFactory) {
        super();
        this.sdAccess = modelAccessFactory.getSDAccess();
        this.sdExecutor = new SDExecutor(modelAccessFactory);
    }

    @Override
    public void checkAndExecute(final EObject monitoredElement) {
        if (this.sdAccess.sdModelsExist()) {
            LOGGER.debug("Checking reconfiguration rules due to PRM change");
            final boolean result = this.sdExecutor.executeActivities(monitoredElement);
            LOGGER.debug(result ? "Reconfigured system by a matching rule"
                    : "No reconfiguration rule was executed, all conditions were false");
        }
    }
}

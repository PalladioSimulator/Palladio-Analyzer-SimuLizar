/**
 * 
 */
package org.palladiosimulator.simulizar.reconfiguration.qvto;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.access.IModelAccessFactory;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.storydiagrams.SDReconfigurator;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * @author Matthias
 *
 */
public class QVTOReconfigurator implements IReconfigurator {
	
    /**
     * This class' internal logger. 
     */
    private static final Logger LOGGER = Logger.getLogger(SDReconfigurator.class);
	
    /**
     * SD Interpreter used internally to interpret the SDs. 
     */
    private final QVTOExecutor qvtoExecutor;
    
    /**
     * SD Reconfigurator constructor.
     * @param modelAccessFactory Model access factory used to access the SDs.
     */
    public QVTOReconfigurator(final IModelAccessFactory modelAccessFactory, SimuComWorkflowConfiguration configuration, MDSDBlackboard blackboard) {
        super();
        this.qvtoExecutor = new QVTOExecutor(modelAccessFactory, configuration, blackboard);
    }


	/* (non-Javadoc)
	 * @see org.palladiosimulator.simulizar.reconfiguration.IReconfigurator#checkAndExecute(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public void checkAndExecute(EObject monitoredElement) {
        LOGGER.debug("Checking reconfiguration rules due to PRM change");
        final boolean result = this.qvtoExecutor.executeRules(monitoredElement);
        LOGGER.debug(result ? "Reconfigured system by a matching rule"
                : "No reconfiguration rule was executed, all conditions were false");
	}

}

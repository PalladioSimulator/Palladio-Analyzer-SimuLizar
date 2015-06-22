package org.palladiosimulator.simulizar.reconfiguration.storydiagram;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationEngine;
import org.palladiosimulator.simulizar.reconfiguration.ModelTransformation;
import org.palladiosimulator.simulizar.reconfiguration.storydiagram.modelaccess.StoryDiagramModelAccess;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.storydriven.storydiagrams.activities.Activity;

/**
 * A reconfigurator implementation which relies on story diagrams to do the reconfiguration. The
 * story diagrams both check their reconfiguration precondition and perform the actual
 * reconfiguration.
 * 
 * @author snowball
 *
 */
public class SDReconfigurator extends AbstractReconfigurator implements IReconfigurationEngine {

    /**
     * This class' internal LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SDReconfigurator.class);

    /**
     * Access interface to access all loaded SDs.
     */
    private StoryDiagramModelAccess modelAccess;

    /**
     * SD Interpreter used internally to interpret the SDs.
     */
    private SDExecutor sdExecutor;

    private SimuLizarWorkflowConfiguration configuration;

    /**
     * Story Diagram Reconfigurator default constructor.
     * 
     * @param modelAccessFactory
     *            Model access factory used to access the SDs.
     */
    public SDReconfigurator() {
        super();
    }

    @Override
    public boolean runCheck(EList<ModelTransformation<?>> checks, final EObject monitoredElement) {
   
    	ArrayList<Activity> activities = new ArrayList<Activity>();
    	for(ModelTransformation<?> check : checks){
    		try {
    			ModelTransformation<Activity> sdModelTransformation = (ModelTransformation<Activity>)check;
    			activities.add(sdModelTransformation.getModelTransformation());
    		} catch (ClassCastException e){
    			LOGGER.debug("Not a Storydiagram model transformation.");
    		}
    	}
    	
        return executeActivities(monitoredElement, activities);
    }

	private boolean executeActivities(final EObject monitoredElement, ArrayList<Activity> activities) {
		if (!activities.isEmpty()) {
            LOGGER.debug("Checking reconfiguration rules due to RuntimeMeasurement change");
            final boolean result = this.getSDExecutor(activities).executeActivities(monitoredElement);
            LOGGER.debug(result ? "Reconfigured system by a matching rule"
                    : "No reconfiguration rule was executed, all conditions were false");
            return result;
        } else {
            return false;
        }
	}
    
	@Override
	public boolean runExecute(EList<ModelTransformation<?>> actions, EObject monitoredElement) {
    	ArrayList<Activity> activities = new ArrayList<Activity>();
    	for(ModelTransformation<?> action : actions){
    		try {
    			ModelTransformation<Activity> sdModelTransformation = (ModelTransformation<Activity>)action;
    			activities.add(sdModelTransformation.getModelTransformation());
    		} catch (ClassCastException e){
    			LOGGER.debug("Not a Storydiagram model transformation.");
    		}
    	}
    	
        return executeActivities(monitoredElement, activities);
	}

    private SDExecutor getSDExecutor(List<Activity> actvities) {
        if (this.sdExecutor == null) {
            this.sdExecutor = new SDExecutor(this.modelAccess, actvities);
        }
        return this.sdExecutor;
    }

    @Override
    public void setModelAccess(IModelAccess modelAccess) {
        this.modelAccess = new StoryDiagramModelAccess(modelAccess, this.configuration);
    }

    @Override
    public void setConfiguration(SimuLizarWorkflowConfiguration configuration) {
        this.configuration = configuration;
    }
}

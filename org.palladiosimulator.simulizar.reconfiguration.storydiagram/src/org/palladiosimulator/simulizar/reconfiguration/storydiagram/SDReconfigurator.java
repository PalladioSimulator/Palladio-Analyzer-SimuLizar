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
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
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
     * SD Interpreter used internally to interpret the SDs.
     */
    private SDExecutor sdExecutor;

    /**
     * Story Diagram Reconfigurator default constructor.
     * 
     * @param modelAccessFactory
     *            Model access factory used to access the SDs.
     */
    public SDReconfigurator() {
        super();
    }
    
    /**
     * Story Diagram Reconfigurator default constructor.
     * 
     * @param modelAccessFactory
     *            Model access factory used to access the SDs.
     */
    public SDReconfigurator(IModelAccess modelAccess) {
        super();
        this.modelAccessFactory = modelAccess;
    }

    @SuppressWarnings("unchecked")
	@Override
    public boolean runCheck(EList<org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation<?>> checks, final EObject monitoredElement) {
   
    	ArrayList<Activity> activities = new ArrayList<Activity>();
    	for(org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation<?> check : checks){
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
            LOGGER.info("Checking reconfiguration rules due to RuntimeMeasurement change");
            final boolean result = this.getSDExecutor(activities).executeActivities(monitoredElement);
            LOGGER.info(result ? "Reconfigured system by a matching rule"
                    : "No reconfiguration rule was executed, all conditions were false");
            return result;
        } else {
            return false;
        }
	}
    
	@SuppressWarnings("unchecked")
	@Override
	public boolean runExecute(EList<org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation<?>> actions, EObject monitoredElement) {
    	ArrayList<Activity> activities = new ArrayList<Activity>();
    	LOGGER.info("Executing Story Diagram Model Transformation.");
    	for(org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation<?> action : actions){
    		try {
    			ModelTransformation<Activity> sdModelTransformation = (ModelTransformation<Activity>)action;
    			activities.add(sdModelTransformation.getModelTransformation());
    		} catch (ClassCastException e){
    			LOGGER.info("Not a Storydiagram model transformation.");
    		}
    	}
    	
        return executeActivities(monitoredElement, activities);
	}

    private SDExecutor getSDExecutor(List<Activity> actvities) {
        if (this.sdExecutor == null) {
            this.sdExecutor = new SDExecutor(this.modelAccessFactory, actvities);
        }
        return this.sdExecutor;
    }

	@Override
	public void setReconfigurator(Reconfigurator reconfigurator) {
		// TODO Auto-generated method stub
		
	}
}

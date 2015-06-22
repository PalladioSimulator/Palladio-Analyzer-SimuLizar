package org.palladiosimulator.simulizar.reconfiguration.henshin;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Unit;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.ModelTransformation;
import org.palladiosimulator.simulizar.reconfiguration.henshin.modelaccess.HenshinModelAccess;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

public class HenshinReconfigurator extends AbstractReconfigurator {

    private HenshinModelAccess modelAccess;
    private SimuLizarWorkflowConfiguration configuration;

    /**
     * This class' internal LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HenshinReconfigurator.class);

    @Override
    public void setModelAccess(final IModelAccess modelAccess) {
        this.modelAccess = new HenshinModelAccess(modelAccess, this.configuration);
    }

    @Override
    public void setConfiguration(final SimuLizarWorkflowConfiguration configuration) {
        this.configuration = configuration;
    }
    
    /**
     * @param app
     * @param resourceSet
     * @param module
     * @param saveResult
     */
    private boolean executeUnit(UnitApplication app, Unit unit) {
      
        // Load the measurement model into an EGraph
        LOGGER.info("Called Henshin reconfiguration engine.");
        EGraph graph = new EGraphImpl(this.modelAccess.getRuntimeMeasurementModel());
        graph.add(this.modelAccess.getGlobalPCMModel().getAllocation());

        app.setEGraph(graph);

        // Set parameters for rule and execute...
        app.setUnit(unit);

        if (app.execute(null)) {
            LOGGER.debug("Successfully executed Henshin rule.");
            return true;
        } else {
            LOGGER.debug("Executing Henshin rule failed.");
            return false;

        }

    }

    public boolean executeUnits(List<Unit> units, EObject measuringPoint) {

        // Create an engine and a rule application:
        Engine engine = new EngineImpl();
        UnitApplication app = new UnitApplicationImpl(engine);

        boolean result = false;
        if (!units.isEmpty()) {
            for (final Unit unit : units) {
            	boolean execution = executeUnit(app, unit);
               result |= execution;
               LOGGER.debug("Executing Henshin rule <" + unit.getName() + "> " + (execution? "succeded": "failed"));
            }
        }

        return result;
    }

	@Override
	public boolean runCheck(EList<ModelTransformation<?>> checks, EObject monitoredElement) {
    	ArrayList<Unit> units = new ArrayList<Unit>();
    	for(ModelTransformation<?> check : checks){
    		try {
    			ModelTransformation<Unit> henshinModelTransformation = (ModelTransformation<Unit>)check;
    			units.add(henshinModelTransformation.getModelTransformation());
    		} catch (ClassCastException e){
    			LOGGER.debug("Not a Henshin model transformation check.");
    		}
    	}
		return executeUnits(units, monitoredElement);
	}

	@Override
	public boolean runExecute(EList<ModelTransformation<?>> actions, EObject monitoredElement) {
    	ArrayList<Unit> modules = new ArrayList<Unit>();
    	for(ModelTransformation<?> action : actions){
    		try {
    			ModelTransformation<Unit> henshinModelTransformation = (ModelTransformation<Unit>)action;
    			modules.add(henshinModelTransformation.getModelTransformation());
    		} catch (ClassCastException e){
    			LOGGER.debug("Not a Henshin model transformation action.");
    		}
    	}
		return executeUnits(modules, monitoredElement);
	}

}

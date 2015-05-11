package org.palladiosimulator.simulizar.reconfiguration.henshin;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Module;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.henshin.modelaccess.HenshinModelAccess;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

public class HenshinReconfigurator implements IReconfigurator {

    private HenshinModelAccess modelAccess;
    private List<Module> modules;
    private SimuLizarWorkflowConfiguration configuration;

    /**
     * This class' internal LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HenshinReconfigurator.class);

    @Override
    public void setModelAccess(final IModelAccess modelAccess) {
        this.modelAccess = new HenshinModelAccess(modelAccess, this.configuration);
        this.modules = this.modelAccess.getHenshinRules();
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
    private boolean executeReconfiguration(UnitApplication app, Module module) {
        // Load the measurement model into an EGraph
        LOGGER.info("Called Henshin reconfiguration engine.");
        EGraph graph = new EGraphImpl(this.modelAccess.getGlobalPCMModel().getAllocation());

        app.setEGraph(graph);

        // Set parameters for rule and execute...
        app.setUnit(module.getUnit("execute"));

        if (app.execute(null)) {
            LOGGER.debug("Successfully executed Henshin rule.");
            return true;
        } else {
            LOGGER.debug("Executing Henshin rule failed.");
            return false;

        }

    }

    /**
     * @param app
     * @param resourceSet
     * @param module
     */
    private boolean analyzeReconfiguration(UnitApplication app, Module module) {
        // Load the example model into an EGraph:
        EGraph graph = new EGraphImpl(this.modelAccess.getRuntimeMeasurementModel());
        app.setEGraph(graph);

        // Execute analyze step of rule
        app.setUnit(module.getUnit("analyze"));

        if (app.execute(null)) {
            LOGGER.debug("Found matching Henshin rule.");
            return true;
        } else {
            LOGGER.debug("No matching Henshin rule found.");
            return false;
        }
    }

    @Override
    public boolean checkAndExecute(EObject measuringPoint) {

        // Create an engine and a rule application:
        Engine engine = new EngineImpl();
        UnitApplication app = new UnitApplicationImpl(engine);

        boolean result = false;
        if (!this.modules.isEmpty()) {
            for (final Module module : modules) {
                if (analyzeReconfiguration(app, module)) {
                    result |= executeReconfiguration(app, module);
                }
            }
        }
        return result;
    }

}

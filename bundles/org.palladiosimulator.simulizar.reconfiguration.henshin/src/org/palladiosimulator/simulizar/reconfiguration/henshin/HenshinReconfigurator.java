package org.palladiosimulator.simulizar.reconfiguration.henshin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Module;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;
import org.palladiosimulator.simulizar.core.reconfiguration.AbstractReconfigurator;
import org.palladiosimulator.simulizar.core.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;

public class HenshinReconfigurator extends AbstractReconfigurator {

    /**
     * Henshin reconfigurator default constructor.
     */
    public HenshinReconfigurator() {
        super();
    }

    /**
     * This class' internal LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(HenshinReconfigurator.class);

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
        EGraph graph = new EGraphImpl(this.pcmPartitionManager.getGlobalPCMModel()
            .getAllocation());

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
        RuntimeMeasurementModel rmModel = this.pcmPartitionManager
            .findModel(RuntimeMeasurementPackage.eINSTANCE.getRuntimeMeasurementModel());
        EGraph graph = new EGraphImpl(rmModel);
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
    public boolean runCheck(List<? extends ModelTransformation<? extends Object>> checks, EObject monitoredElement,
            IResourceTableManager resourceTableManager, Map<String, Object> configParams) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean runExecute(List<? extends ModelTransformation<? extends Object>> actions, EObject monitoredElement,
            IResourceTableManager resourceTableManager) {
        Map<String, Object> configParams = Collections.emptyMap();
        return runExecute(actions, monitoredElement, resourceTableManager, configParams);
    }

    @Override
    public boolean runExecute(List<? extends ModelTransformation<? extends Object>> actions, EObject monitoredElement,
            IResourceTableManager resourceTableManager, Map<String, Object> configParams) {
        List<HenshinModelTransformation> transformations = new ArrayList<>();
        LOGGER.info("Executing Story Diagram Model Transformation.");
        for (ModelTransformation<? extends Object> action : actions) {
            try {
                if (action instanceof HenshinModelTransformation) {
                    HenshinModelTransformation henshinModelTransformation = (HenshinModelTransformation) action;
                    transformations.add(henshinModelTransformation);
                }
            } catch (ClassCastException e) {
                LOGGER.info("Not a Storydiagram model transformation.");
            }
        }

        return executeTransformations(transformations);
    }

    private boolean executeTransformations(List<HenshinModelTransformation> transformations) {
        Engine engine = new EngineImpl();
        UnitApplication app = new UnitApplicationImpl(engine);
        boolean result = false;
        for (final HenshinModelTransformation transformation : transformations) {
            if (analyzeReconfiguration(app, transformation.getModelTransformation())) {
                result |= executeReconfiguration(app, transformation.getModelTransformation());
            }
        }
        return result;
    }

}

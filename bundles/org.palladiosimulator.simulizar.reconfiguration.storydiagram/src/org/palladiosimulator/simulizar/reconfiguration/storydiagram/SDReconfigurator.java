package org.palladiosimulator.simulizar.reconfiguration.storydiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurator;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;

/**
 * A reconfigurator implementation which relies on story diagrams to do the reconfiguration. The
 * story diagrams both check their reconfiguration precondition and perform the actual
 * reconfiguration.
 * 
 * @author snowball
 *
 */
public class SDReconfigurator extends AbstractReconfigurator {

    /**
     * This class' internal LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(SDReconfigurator.class);

    /**
     * SD Interpreter used internally to interpret the SDs.
     */
    private SDExecutor sdExecutor;

    /**
     * Story diagram reconfigurator default constructor.
     */
    public SDReconfigurator() {
        super();
    }

    @Override
    public boolean runCheck(List<? extends ModelTransformation<? extends Object>> checks,
            final EObject monitoredElement, IResourceTableManager resourceTableManager,
            Map<String, Object> configParams) {

        ArrayList<SDModelTransformation> transformations = new ArrayList<>();
        for (ModelTransformation<? extends Object> check : checks) {
            try {
                if (check instanceof SDModelTransformation) {
                    SDModelTransformation sdModelTransformation = (SDModelTransformation) check;
                    transformations.add(sdModelTransformation);
                }
            } catch (ClassCastException e) {
                LOGGER.debug("Not a Storydiagram model transformation.");
            }
        }

        return executeTransformations(monitoredElement, transformations);
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
        ArrayList<SDModelTransformation> activities = new ArrayList<>();
        LOGGER.info("Executing Story Diagram Model Transformation.");
        for (ModelTransformation<? extends Object> action : actions) {
            try {
                if (action instanceof SDModelTransformation) {
                    SDModelTransformation sdModelTransformation = (SDModelTransformation) action;
                    activities.add(sdModelTransformation);
                }
            } catch (ClassCastException e) {
                LOGGER.info("Not a Storydiagram model transformation.");
            }
        }

        return executeTransformations(monitoredElement, activities);
    }

    private boolean executeTransformations(final EObject monitoredElement,
            ArrayList<SDModelTransformation> transformations) {
        if (!transformations.isEmpty()) {
            LOGGER.info("Checking reconfiguration rules due to RuntimeMeasurement change");
            final boolean result = this.getSDExecutor()
                .executeTransformations(transformations, monitoredElement);
            LOGGER.info(result ? "Reconfigured system by a matching rule"
                    : "No reconfiguration rule was executed, all conditions were false");
            return result;
        } else {
            return false;
        }
    }

    private SDExecutor getSDExecutor() {
        if (this.sdExecutor == null) {
            this.sdExecutor = new SDExecutor(this.pcmPartitionManager);
        }
        return this.sdExecutor;
    }
}

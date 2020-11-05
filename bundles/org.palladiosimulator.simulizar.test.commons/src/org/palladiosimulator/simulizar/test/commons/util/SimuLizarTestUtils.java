package org.palladiosimulator.simulizar.test.commons.util;

import java.util.HashMap;
import java.util.Map;

import org.palladiosimulator.simulizar.launcher.SimulizarConstants;

import de.uka.ipd.sdq.simucomframework.SimuComConfig;

public final class SimuLizarTestUtils {
    public static final Map<String, Object> createDefaultSimulationProperties() {
        final Map<String, Object> properties = new HashMap<>();
    
        properties.put(SimuComConfig.SIMULATE_LINKING_RESOURCES, false);
        properties.put(SimuComConfig.SIMULATE_THROUGHPUT_OF_LINKING_RESOURCES, true);
        properties.put(SimuComConfig.USE_FIXED_SEED, false);
        properties.put(SimuComConfig.PERSISTENCE_RECORDER_NAME,
                org.palladiosimulator.recorderframework.edp2.Activator.EDP2_ID);
        properties.put(SimuComConfig.SIMULATOR_ID, "de.uka.ipd.sdq.codegen.simucontroller.simulizar");
        properties.put(SimuComConfig.EXPERIMENT_RUN, SimuComConfig.DEFAULT_EXPERIMENT_RUN);
        properties.put(SimuComConfig.SIMULATION_TIME, "2000");
        properties.put(SimuComConfig.MAXIMUM_MEASUREMENT_COUNT, SimuComConfig.DEFAULT_MAXIMUM_MEASUREMENT_COUNT);
        properties.put(SimuComConfig.VARIATION_ID, SimuComConfig.DEFAULT_VARIATION_NAME);
        properties.put(SimulizarConstants.RECONFIGURATION_RULES_FOLDER,
                SimulizarConstants.DEFAULT_RECONFIGURATION_RULES_FOLDER);
        properties.put(SimuComConfig.VERBOSE_LOGGING, false);
        properties.put("de.uka.ipd.sdq.workflowengine.debuglevel", "1"); // Log level DEBUG
    
        return properties;
    }
}

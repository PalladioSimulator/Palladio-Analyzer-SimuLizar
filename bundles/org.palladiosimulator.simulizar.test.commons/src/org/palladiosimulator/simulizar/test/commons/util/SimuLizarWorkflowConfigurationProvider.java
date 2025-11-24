package org.palladiosimulator.simulizar.test.commons.util;

import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;
import static org.junit.platform.commons.support.AnnotationSupport.findRepeatableAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.edp2.models.Repository.Repository;
import org.palladiosimulator.simulizar.core.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.test.commons.annotation.SetConfigProperty;
import org.palladiosimulator.simulizar.test.commons.annotation.SimulationConfig;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestExtensionCommons;

import de.uka.ipd.sdq.simucomframework.core.SimuComConfig;
import de.uka.ipd.sdq.simulation.core.AbstractSimulationConfig;
import de.uka.ipd.sdq.workflow.configuration.IJobConfiguration;

public class SimuLizarWorkflowConfigurationProvider implements Supplier<IJobConfiguration> {
    final Repository edp2Repository;
    final SimulationConfig annotation;
    final List<SetConfigProperty> additionalConfig;

    public SimuLizarWorkflowConfigurationProvider(ExtensionContext context) {
        annotation = findAnnotation(context.getElement(), SimulationConfig.class)
            .orElseThrow(() -> new IllegalArgumentException(
                    "This provider expects a SimulationConfig annotation to be (meta-)present"));
        additionalConfig = findRepeatableAnnotations(context.getElement(), SetConfigProperty.class);
        edp2Repository = SimuLizarTestExtensionCommons.getObjectFromStore(context, Repository.class)
            .orElseThrow(() -> new IllegalArgumentException(
                    "No EDP2 repository initialized. Please make sure to annotate your test accordingly."));
    }

    @Override
    public SimuLizarWorkflowConfiguration get() {
        var properties = SimuLizarTestUtils.createDefaultSimulationProperties();
        properties.put("EDP2RepositoryID", edp2Repository.getId());
        properties.put(SimuComConfig.SIMULATE_FAILURES, annotation.simulateReliability());
        properties.put(SimuComConfig.SIMULATE_LINKING_RESOURCES, annotation.simulateLinkingResource());
        properties.put(SimuComConfig.SIMULATE_THROUGHPUT_OF_LINKING_RESOURCES, annotation.simulateLinkThroughput());
        properties.put(AbstractSimulationConfig.MAXIMUM_MEASUREMENT_COUNT, annotation.maxMeasurements());
        properties.put(AbstractSimulationConfig.SIMULATION_TIME, annotation.maxSimTime());
        additionalConfig.forEach(prop -> properties.put(prop.id(), prop.value()));
        var simulizarConfiguration = new SimuLizarWorkflowConfiguration(properties);
        simulizarConfiguration.setSimuComConfiguration(new SimuComConfig(properties, false, null));
        
        Optional.ofNullable(properties.get(SimulizarConstants.RECONFIGURATION_RULES_FOLDER))
            .ifPresent(o -> simulizarConfiguration.setReconfigurationRulesFolder(o.toString()));
        
        return simulizarConfiguration;
    }

}

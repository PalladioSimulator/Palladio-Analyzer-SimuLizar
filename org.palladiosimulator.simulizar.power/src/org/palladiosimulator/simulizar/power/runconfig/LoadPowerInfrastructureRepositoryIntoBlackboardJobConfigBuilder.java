package org.palladiosimulator.simulizar.power.runconfig;

import java.util.Map;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionConfigurationBuilder;

public class LoadPowerInfrastructureRepositoryIntoBlackboardJobConfigBuilder extends
        AbstractWorkflowExtensionConfigurationBuilder {

    @Override
    public AbstractExtensionJobConfiguration buildConfiguration(Map<String, Object> properties) {
        if (properties == null || !properties.containsKey(LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig.INFRASTRUCTURE_MODEL_FILE)) {
            throw new IllegalArgumentException("Given properties map must contain entry for key "
                    + LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig.INFRASTRUCTURE_MODEL_FILE + ".");
        }
        String path = String.valueOf(properties.get(LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig.INFRASTRUCTURE_MODEL_FILE));
        return new LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig(path);
    }
}

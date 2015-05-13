package org.palladiosimulator.simulizar.power.runconfig;

import java.util.Map;

import org.palladiosimulator.simulizar.power.jobs.LoadPowerInfrastructureRepositoryIntoBlackboardJob;
import org.palladiosimulator.simulizar.power.ui.configuration.PowerInfrastructureRepositoryFileInputHandler;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionConfigurationBuilder;

/**
 * Class to build the configuration for the {@link LoadPowerInfrastructureRepositoryIntoBlackboardJob}.
 * @see LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig
 * @author Florian Rosenthal
 *
 */
public class LoadPowerInfrastructureRepositoryIntoBlackboardJobConfigBuilder extends
        AbstractWorkflowExtensionConfigurationBuilder {

    /**
     * {@inheritDoc}
     * @throws  IllegalArgumentException In case the passed properties map does not contain an entry for the
     * {@link PowerInfrastructureRepositoryFileInputHandler#INFRASTRUCTURE_MODEL_FILE} key.
     */
    @Override
    public AbstractExtensionJobConfiguration buildConfiguration(Map<String, Object> properties) {
        if (properties == null || !properties.containsKey(PowerInfrastructureRepositoryFileInputHandler.INFRASTRUCTURE_MODEL_FILE)) {
            throw new IllegalArgumentException("Given properties map must contain entry for key "
                    + PowerInfrastructureRepositoryFileInputHandler.INFRASTRUCTURE_MODEL_FILE + ".");
        }
        String path = String.valueOf(properties.get(PowerInfrastructureRepositoryFileInputHandler.INFRASTRUCTURE_MODEL_FILE));
        return new LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig(path);
    }
}

package org.palladiosimulator.simulizar.power.runconfig;

import java.util.Map;

import org.palladiosimulator.simulizar.power.jobs.LoadPowerInfrastructureRepositoryIntoBlackboardJob;
import org.palladiosimulator.simulizar.power.ui.configuration.PowerInfrastructureRepositoryFileInputConfigBuilder;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionConfigurationBuilder;

/**
 * Class to build the configuration for the
 * {@link LoadPowerInfrastructureRepositoryIntoBlackboardJob}.
 * 
 * @see LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig
 * @author Florian Rosenthal
 *
 */
public class LoadPowerInfrastructureRepositoryIntoBlackboardJobConfigBuilder extends
        AbstractWorkflowExtensionConfigurationBuilder {

    /**
     * {@inheritDoc}
     * 
     * @throws IllegalArgumentException
     *             In case the passed properties map does not contain an entry for the
     *             {@link PowerInfrastructureRepositoryFileInputHandler#INFRASTRUCTURE_MODEL_FILE}
     *             key.
     */
    @Override
    public AbstractExtensionJobConfiguration buildConfiguration(Map<String, Object> properties) {
        if (properties == null
                || !properties
                        .containsKey(PowerInfrastructureRepositoryFileInputConfigBuilder.INFRASTRUCTURE_MODEL_FILE)) {
            throw new IllegalArgumentException("Given properties map must contain entry for key "
                    + PowerInfrastructureRepositoryFileInputConfigBuilder.INFRASTRUCTURE_MODEL_FILE + ".");
        }
        String path = String.valueOf(properties
                .get(PowerInfrastructureRepositoryFileInputConfigBuilder.INFRASTRUCTURE_MODEL_FILE));
        return new LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig(path);
    }
}

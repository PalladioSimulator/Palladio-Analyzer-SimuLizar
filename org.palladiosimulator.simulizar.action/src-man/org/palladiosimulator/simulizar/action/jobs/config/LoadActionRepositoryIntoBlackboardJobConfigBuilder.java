package org.palladiosimulator.simulizar.action.jobs.config;

import java.util.Map;

import org.palladiosimulator.simulizar.action.jobs.LoadActionRepositoryIntoBlackBoardJob;
import org.palladiosimulator.simulizar.action.ui.configuration.ActionRepositoryFileInputConfigBuilder;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionConfigurationBuilder;

/**
 * Class to build the configuration for the {@link LoadActionRepositoryIntoBlackBoardJob}.
 * 
 * @see LoadActionRepositoryIntoBlackBoardJob
 * @author Florian Rosenthal
 *
 */
public class LoadActionRepositoryIntoBlackboardJobConfigBuilder extends AbstractWorkflowExtensionConfigurationBuilder {
    /**
     * {@inheritDoc}
     * 
     * @throws IllegalArgumentException
     *             In case the passed properties map does not contain an entry for the
     *             {@link ActionRepositoryFileInputConfigBuilder#ACTION_MODEL_FILE} key.
     */
    @Override
    public AbstractExtensionJobConfiguration buildConfiguration(Map<String, Object> properties) {
        if (properties == null || !properties.containsKey(ActionRepositoryFileInputConfigBuilder.ACTION_MODEL_FILE)) {
            throw new IllegalArgumentException("Given properties map must contain entry for key "
                    + ActionRepositoryFileInputConfigBuilder.ACTION_MODEL_FILE + ".");
        }
        String path = String.valueOf(properties.get(ActionRepositoryFileInputConfigBuilder.ACTION_MODEL_FILE));
        return new LoadActionRepositoryIntoBlackboardJobConfig(path);
    }
}

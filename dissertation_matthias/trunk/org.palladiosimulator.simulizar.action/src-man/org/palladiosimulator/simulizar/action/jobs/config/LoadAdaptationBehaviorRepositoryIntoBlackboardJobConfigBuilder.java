package org.palladiosimulator.simulizar.action.jobs.config;

import java.util.Map;

import org.palladiosimulator.simulizar.action.jobs.LoadAdaptationBehaviorRepositoryIntoBlackBoardJob;
import org.palladiosimulator.simulizar.action.ui.configuration.AdaptationBehaviorRepositoryFileInputConfigBuilder;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionConfigurationBuilder;

/**
 * Class to build the configuration for the {@link LoadAdaptationBehaviorRepositoryIntoBlackBoardJob}.
 * 
 * @see LoadAdaptationBehaviorRepositoryIntoBlackBoardJob
 * @author Florian Rosenthal
 *
 */
public class LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfigBuilder extends AbstractWorkflowExtensionConfigurationBuilder {
    /**
     * {@inheritDoc}
     * 
     * @throws IllegalArgumentException
     *             In case the passed properties map does not contain an entry for the
     *             {@link AdaptationBehaviorRepositoryFileInputConfigBuilder#ACTION_MODEL_FILE} key.
     */
    @Override
    public AbstractExtensionJobConfiguration buildConfiguration(Map<String, Object> properties) {
        if (properties == null || !properties.containsKey(AdaptationBehaviorRepositoryFileInputConfigBuilder.ACTION_MODEL_FILE)) {
            throw new IllegalArgumentException("Given properties map must contain entry for key "
                    + AdaptationBehaviorRepositoryFileInputConfigBuilder.ACTION_MODEL_FILE + ".");
        }
        String path = String.valueOf(properties.get(AdaptationBehaviorRepositoryFileInputConfigBuilder.ACTION_MODEL_FILE));
        return new LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig(path);
    }
}

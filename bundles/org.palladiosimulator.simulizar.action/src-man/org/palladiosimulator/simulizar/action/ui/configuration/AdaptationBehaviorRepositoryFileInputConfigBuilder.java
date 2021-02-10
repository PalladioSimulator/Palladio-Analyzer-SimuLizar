package org.palladiosimulator.simulizar.action.ui.configuration;

import java.util.HashMap;
import java.util.Map;

import org.palladiosimulator.simulizar.action.jobs.config.LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig;
import org.palladiosimulator.simulizar.ui.configuration.extensions.AbstractExtensionFileInputConfigurationBuilder;
import org.palladiosimulator.simulizar.ui.configuration.extensions.ExtensionFileInputConfiguration;
import org.palladiosimulator.simulizar.ui.configuration.extensions.ExtensionInputType;

public class AdaptationBehaviorRepositoryFileInputConfigBuilder extends AbstractExtensionFileInputConfigurationBuilder {

    /**
     * This constant holds the repository key for the Action model file.
     */
    public static final String ACTION_MODEL_FILE = "actionModelFile";
    private static final String[] ACTION_MODEL_FILE_EXTENSIONS = new String[] { "*.actions" };
    private static final String GROUP_LABEL = "Optional: Action Model File (For Transient Effect Analyses)";
    private static final String DIALOG_TITLE = "Select Action Model File";

    @Override
    public ExtensionFileInputConfiguration buildConfiguration() {
        Map<String, Object> properties = new HashMap<>(6);

        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.CONFIG_ATTRIBUTE_NAME_KEY,
                ACTION_MODEL_FILE);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.FILE_RESTRICTIONS_KEY,
                ACTION_MODEL_FILE_EXTENSIONS);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.DEFAULT_URI_KEY,
                LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig.DEFAULT_ADAPTATION_BEHAVIOR_REPOSITORY_PATH);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.DIALOG_TITLE_KEY, DIALOG_TITLE);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.GROUP_LABEL_KEY, GROUP_LABEL);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.INPUT_TYPE_KEY, ExtensionInputType.FILE);

        return new ExtensionFileInputConfiguration(properties);
    }
    
    public static LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig createConfig(Map<String, Object> properties) {
        if (properties == null || !properties.containsKey(AdaptationBehaviorRepositoryFileInputConfigBuilder.ACTION_MODEL_FILE)) {
            throw new IllegalArgumentException("Given properties map must contain entry for key "
                    + AdaptationBehaviorRepositoryFileInputConfigBuilder.ACTION_MODEL_FILE + ".");
        }
        String path = String.valueOf(properties.get(AdaptationBehaviorRepositoryFileInputConfigBuilder.ACTION_MODEL_FILE));
        return new LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig(path);
    }

}

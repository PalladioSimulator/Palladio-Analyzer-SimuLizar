package org.palladiosimulator.simulizar.failurescenario.ui.config;

import java.util.HashMap;
import java.util.Map;

import org.palladiosimulator.simulizar.failurescenario.jobs.config.LoadFailurescenarioExtensionIntoBlackboardJobConfig;
import org.palladiosimulator.simulizar.ui.configuration.extensions.AbstractExtensionFileInputConfigurationBuilder;
import org.palladiosimulator.simulizar.ui.configuration.extensions.ExtensionFileInputConfiguration;
import org.palladiosimulator.simulizar.ui.configuration.extensions.ExtensionInputType;

public class FailurescenarioExtensionFileInputConfigurationBuilder
		extends AbstractExtensionFileInputConfigurationBuilder {

	
	/**
     * This constant holds the repository key for the Failure model file.
     */
    public static final String FAILURE_MODEL_FILE = "failureModelFile";
    private static final String[] FAILURE_MODEL_FILE_EXTENSIONS = new String[] { "*.failurescenario" };
    private static final String GROUP_LABEL = "Optional: Failure Model File (For Failurescenario Analyses)";
    private static final String DIALOG_TITLE = "Select Failure Model File";

    
	public FailurescenarioExtensionFileInputConfigurationBuilder() {
	}
	
	

	@Override
	public ExtensionFileInputConfiguration buildConfiguration() {
		Map<String, Object> properties = new HashMap<>(6);

        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.CONFIG_ATTRIBUTE_NAME_KEY,
        		FAILURE_MODEL_FILE);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.FILE_RESTRICTIONS_KEY,
        		FAILURE_MODEL_FILE_EXTENSIONS);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.DEFAULT_URI_KEY,
        		LoadFailurescenarioExtensionIntoBlackboardJobConfig.DEFAULT_FAILURE_MODEL_PATH);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.DIALOG_TITLE_KEY, DIALOG_TITLE);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.GROUP_LABEL_KEY, GROUP_LABEL);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.INPUT_TYPE_KEY, ExtensionInputType.FILE);

        return new ExtensionFileInputConfiguration(properties);
	}
	
	public static LoadFailurescenarioExtensionIntoBlackboardJobConfig createConfig(Map<String, Object> properties) {
        if (properties == null || !properties.containsKey(FailurescenarioExtensionFileInputConfigurationBuilder.FAILURE_MODEL_FILE)) {
            throw new IllegalArgumentException("Given properties map must contain entry for key "
                    + FailurescenarioExtensionFileInputConfigurationBuilder.FAILURE_MODEL_FILE + ".");
        }
        String path = String.valueOf(properties.get(FailurescenarioExtensionFileInputConfigurationBuilder.FAILURE_MODEL_FILE));
        return new LoadFailurescenarioExtensionIntoBlackboardJobConfig(path);
    }

}

package org.palladiosimulator.simulizar.power.ui.configuration;

import java.util.HashMap;
import java.util.Map;

import org.palladiosimulator.simulizar.ui.configuration.extensions.AbstractExtensionFileInputConfigurationBuilder;
import org.palladiosimulator.simulizar.ui.configuration.extensions.ExtensionFileInputConfiguration;
import org.palladiosimulator.simulizar.ui.configuration.extensions.ExtensionInputType;

import de.fzi.power.infrastructure.PowerInfrastructureRepository;

/**
 * Implementation of the {@link AbstractExtensionFileInputConfigurationBuilder} to exploit the
 * {@code org.palladiosimulator.simulizar.ui.configuration.fileinput} extension point. This allows
 * the user to specify {@link PowerInfrastructureRepository} model file that is included in the
 * Simulizar launch configuration.
 * 
 * @author Florian Rosenthal
 *
 */
public class PowerInfrastructureRepositoryFileInputConfigBuilder extends AbstractExtensionFileInputConfigurationBuilder {

    /**
     * This constant holds the run configuration key for the infrastructure model file.
     */
    public static final String INFRASTRUCTURE_MODEL_FILE = "infrastructureModelFile";
    private static final String[] INFRASTRUCTURE_MODEL_FILE_EXTENSIONS = new String[] { "*.infrastructure" };
    private static final String GROUP_LABEL = "Optional: Infrastructure Model File (For Power Analyses)";
    private static final String DIALOG_TITLE = "Select Infrastructure Model File";

    @Override
    public ExtensionFileInputConfiguration buildConfiguration() {
        Map<String, Object> properties = new HashMap<>(6);

        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.CONFIG_ATTRIBUTE_NAME_KEY,
                INFRASTRUCTURE_MODEL_FILE);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.FILE_RESTRICTIONS_KEY,
                INFRASTRUCTURE_MODEL_FILE_EXTENSIONS);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.DEFAULT_URI_KEY, "");
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.DIALOG_TITLE_KEY, DIALOG_TITLE);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.GROUP_LABEL_KEY, GROUP_LABEL);
        properties.put(ExtensionFileInputConfiguration.DefaultPropertyKeys.INPUT_TYPE_KEY, ExtensionInputType.FILE);

        return new ExtensionFileInputConfiguration(properties);
    }

}

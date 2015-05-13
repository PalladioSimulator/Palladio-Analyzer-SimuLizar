package org.palladiosimulator.simulizar.power.ui.configuration;

import org.palladiosimulator.simulizar.ui.configuration.extensions.AbstractExtensionFileInputHandler;

import de.fzi.power.infrastructure.PowerInfrastructureRepository;

/**
 * Implementation of the {@link AbstractExtensionFileInputHandler} to exploit the
 * {@code org.palladiosimulator.simulizar.ui.configuration.fileinput} extension point.
 * This allows the user to specify {@link PowerInfrastructureRepository} model file that is included
 * in the Simulizar launch configuration.
 * @author Florian Rosenthal
 *
 */
public class PowerInfrastructureRepositoryFileInputHandler extends AbstractExtensionFileInputHandler {

    /**
     * This constant holds the run configuration key for the infrastructure model file.
     */
    public static final String INFRASTRUCTURE_MODEL_FILE = "infrastructureModelFile";
    private static final String[] INFRASTRUCTURE_MODEL_FILE_EXTENSIONS = new String[] { "*.infrastructure" };
    private static final String GROUP_LABEL = "Optional: Infrastructure Model File (For Power Analyses)";
    private static final String DIALOG_TITLE = "Select Infrastructure Model File";
    
    @Override
    public String getDialogTitle() {
        return DIALOG_TITLE;
    }

    @Override
    public String getGroupLabel() {
        return GROUP_LABEL;
    }

    @Override
    public String[] getFileExtensionRestrictions() {
        return INFRASTRUCTURE_MODEL_FILE_EXTENSIONS;
    }
    
    @Override
    public String getConfigurationAttributeName() {
        return INFRASTRUCTURE_MODEL_FILE;
    }

}

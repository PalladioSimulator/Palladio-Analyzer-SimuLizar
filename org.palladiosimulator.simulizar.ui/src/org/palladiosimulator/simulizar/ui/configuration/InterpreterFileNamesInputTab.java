package org.palladiosimulator.simulizar.ui.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.ui.configuration.extensions.AbstractExtensionFileInputConfigurationBuilder;
import org.palladiosimulator.simulizar.ui.configuration.extensions.ExtensionFileInputConfiguration;
import org.palladiosimulator.simulizar.ui.configuration.extensions.ExtensionFileInputConfiguration.DefaultPropertyKeys;
import org.palladiosimulator.simulizar.ui.configuration.extensions.ExtensionInputType;

import de.uka.ipd.sdq.workflow.launchconfig.LaunchConfigPlugin;
import de.uka.ipd.sdq.workflow.launchconfig.tabs.TabHelper;
import de.uka.ipd.sdq.workflow.pcm.runconfig.ProtocomFileNamesInputTab;

/**
 * File name input tab for SimuLizar. Uses middleware and eventmiddle ware input fields for Monitor
 * Repository models and SDM models.
 */
public class InterpreterFileNamesInputTab extends ProtocomFileNamesInputTab {

    private static final String EXTENSION_POINT_ID = "org.palladiosimulator.simulizar.ui.configuration.fileinput";
    private static final String EXTENSION_POINT_ATTRIBUTE = "fileInputConfigurationBuilder";

    // input fields
    /** Text field for path to Monitor Repository file. */
    protected Text monitorRepositoryFile;
    /** Text field for path to reconfiguration rules folder. */
    protected Text reconfigurationRulesFolder;
    /** Text field for path to usage evolution file. */
    protected Text usageEvolutionFile;
    /** Text field for service level objectives file. */
    protected Text serviceLevelObjectivesFile;

    private final Map<ExtensionFileInputConfiguration, Text> extensionFileFolderInputTexts;

    public InterpreterFileNamesInputTab() {
        this.extensionFileFolderInputTexts = new HashMap<ExtensionFileInputConfiguration, Text>();
    }

    /**
     * @see de.uka.ipd.sdq.workflow.launchconfig.tabs.FileNamesInputTab#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(final Composite parent) {
        super.createControl(parent);
        this.extensionFileFolderInputTexts.clear();
        /**
         * Create Monitor Repository file section
         */
        monitorRepositoryFile = new Text(container, SWT.SINGLE | SWT.BORDER);
        TabHelper.createFileInputSection(container, modifyListener, "Optional: Monitor Repository File",
                SimulizarConstants.MONITORING_SPECIFICATION_FILE_EXTENSION, monitorRepositoryFile,
                "Select Monitor Repository File", getShell(), SimulizarConstants.DEFAULT_MONITOR_REPOSITORY_FILE);

        /**
         * Create reconfiguration rules folder section
         */
        reconfigurationRulesFolder = new Text(container, SWT.SINGLE | SWT.BORDER);
        TabHelper.createFolderInputSection(container, modifyListener, "Optional: Reconfiguration Rules folder",
                reconfigurationRulesFolder, "Select Reconfiguration Rules Folder", getShell(),
                SimulizarConstants.DEFAULT_RECONFIGURATION_RULES_FOLDER);

        /**
         * Create service level objective repository section
         */
        serviceLevelObjectivesFile = new Text(container, SWT.SINGLE | SWT.BORDER);
        TabHelper.createFileInputSection(container, modifyListener, "Optional: Service Level Objectives File",
                SimulizarConstants.SERVICELEVELOBJECTIVEREPOSITORY_FILE_EXTENSION, serviceLevelObjectivesFile,
                "Select Service Level Objective Repository File", getShell(),
                SimulizarConstants.SERVICELEVELOBJECTIVEREPOSITORY_FILE);

        /**
         * Create UsageEvolution file section
         */
        usageEvolutionFile = new Text(container, SWT.SINGLE | SWT.BORDER);
        TabHelper.createFileInputSection(container, modifyListener, "Optional: Usage Evolution File",
                SimulizarConstants.USAGEEVOLUTION_FILE_EXTENSION, usageEvolutionFile, "Select Usage Evolution File",
                getShell(), SimulizarConstants.DEFAULT_USAGEEVOLUTION_FILE);

        createInputSectionsForExtensions();

    }

    private void createInputSectionsForExtensions() {
        Iterable<AbstractExtensionFileInputConfigurationBuilder> extensions = ExtensionHelper.getExecutableExtensions(
                EXTENSION_POINT_ID, EXTENSION_POINT_ATTRIBUTE);
        for (AbstractExtensionFileInputConfigurationBuilder extension : extensions) {
            ExtensionFileInputConfiguration config = extension.buildConfiguration();
            Text inputText = new Text(container, SWT.SINGLE | SWT.BORDER);
            this.extensionFileFolderInputTexts.put(config, inputText);
            String groupLabel = config.getPropertyByKey(DefaultPropertyKeys.GROUP_LABEL_KEY, String.class);
            String dialogTitle = config.getPropertyByKey(DefaultPropertyKeys.DIALOG_TITLE_KEY, String.class);
            String defaultUri = config.getPropertyByKey(DefaultPropertyKeys.DEFAULT_URI_KEY, String.class);
            ExtensionInputType inputType = config.getPropertyByKey(DefaultPropertyKeys.INPUT_TYPE_KEY,
                    DefaultPropertyKeys.EXPECTED_INPUT_TYPE_DATA_TYPE);
            if (inputType == ExtensionInputType.FILE) {
                String[] fileExtensionRestrictions = config.getPropertyByKey(DefaultPropertyKeys.FILE_RESTRICTIONS_KEY,
                        DefaultPropertyKeys.EXPECTED_FILE_RESTRICTIONS_DATA_TYPE);
                TabHelper.createFileInputSection(container, modifyListener, groupLabel, fileExtensionRestrictions,
                        inputText, dialogTitle, getShell(), defaultUri);
            } else {
                TabHelper.createFolderInputSection(container, modifyListener, groupLabel, monitorRepositoryFile,
                        dialogTitle, getShell(), defaultUri);
            }

        }
    }

    private void setTextFromConfigAttribute(Text textWidget, ILaunchConfiguration config, String attributeName,
            String defaultValue) {
        try {
            textWidget.setText(config.getAttribute(attributeName, defaultValue));
        } catch (CoreException e) {
            LaunchConfigPlugin.errorLogger(getName(), attributeName, e.getMessage());
        }
    }

    /**
     * @see de.uka.ipd.sdq.workflow.launchconfig.tabs.FileNamesInputTab#initializeFrom(org.eclipse.debug.core.ILaunchConfiguration)
     */
    @Override
    public void initializeFrom(final ILaunchConfiguration configuration) {
        super.initializeFrom(configuration);
        setTextFromConfigAttribute(this.monitorRepositoryFile, configuration,
                SimulizarConstants.MONITOR_REPOSITORY_FILE, SimulizarConstants.DEFAULT_MONITOR_REPOSITORY_FILE);
        setTextFromConfigAttribute(this.reconfigurationRulesFolder, configuration,
                SimulizarConstants.RECONFIGURATION_RULES_FOLDER,
                SimulizarConstants.DEFAULT_RECONFIGURATION_RULES_FOLDER);
        setTextFromConfigAttribute(this.serviceLevelObjectivesFile, configuration,
                SimulizarConstants.SERVICELEVELOBJECTIVEREPOSITORY_FILE,
                SimulizarConstants.DEFAULT_SERVICELEVELOBJECTIVE_FILE);
        setTextFromConfigAttribute(this.usageEvolutionFile, configuration, SimulizarConstants.USAGEEVOLUTION_FILE,
                SimulizarConstants.DEFAULT_USAGEEVOLUTION_FILE);
        for (Entry<ExtensionFileInputConfiguration, Text> entry : this.extensionFileFolderInputTexts.entrySet()) {
            String defaultUri = entry.getKey().getPropertyByKey(DefaultPropertyKeys.DEFAULT_URI_KEY, String.class);
            String configAttributeName = entry.getKey().getPropertyByKey(DefaultPropertyKeys.CONFIG_ATTRIBUTE_NAME_KEY,
                    String.class);
            setTextFromConfigAttribute(entry.getValue(), configuration, configAttributeName, defaultUri);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#performApply(org.eclipse.debug.core.
     * ILaunchConfigurationWorkingCopy)
     */
    @Override
    public void performApply(final ILaunchConfigurationWorkingCopy configuration) {
        super.performApply(configuration);
        configuration.setAttribute(SimulizarConstants.MONITOR_REPOSITORY_FILE, monitorRepositoryFile.getText());
        configuration.setAttribute(SimulizarConstants.RECONFIGURATION_RULES_FOLDER,
                reconfigurationRulesFolder.getText());
        configuration.setAttribute(SimulizarConstants.SERVICELEVELOBJECTIVEREPOSITORY_FILE,
                serviceLevelObjectivesFile.getText());
        configuration.setAttribute(SimulizarConstants.USAGEEVOLUTION_FILE, usageEvolutionFile.getText());
        for (Entry<ExtensionFileInputConfiguration, Text> entry : this.extensionFileFolderInputTexts.entrySet()) {
            String configAttributeName = entry.getKey().getPropertyByKey(DefaultPropertyKeys.CONFIG_ATTRIBUTE_NAME_KEY,
                    String.class);
            configuration.setAttribute(configAttributeName, entry.getValue().getText());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.debug.ui.ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.
     * ILaunchConfigurationWorkingCopy)
     */
    @Override
    public void setDefaults(final ILaunchConfigurationWorkingCopy configuration) {
        super.setDefaults(configuration);
        configuration.setAttribute(SimulizarConstants.MONITOR_REPOSITORY_FILE,
                SimulizarConstants.DEFAULT_MONITOR_REPOSITORY_FILE);
        configuration.setAttribute(SimulizarConstants.RECONFIGURATION_RULES_FOLDER,
                SimulizarConstants.DEFAULT_RECONFIGURATION_RULES_FOLDER);
        configuration.setAttribute(SimulizarConstants.SERVICELEVELOBJECTIVEREPOSITORY_FILE,
                SimulizarConstants.DEFAULT_SERVICELEVELOBJECTIVE_FILE);
        configuration.setAttribute(SimulizarConstants.USAGEEVOLUTION_FILE,
                SimulizarConstants.DEFAULT_USAGEEVOLUTION_FILE);

        for (ExtensionFileInputConfiguration extensionConfig : this.extensionFileFolderInputTexts.keySet()) {
            String defaultUri = extensionConfig.getPropertyByKey(DefaultPropertyKeys.DEFAULT_URI_KEY, String.class);
            String configAttributeName = extensionConfig.getPropertyByKey(DefaultPropertyKeys.CONFIG_ATTRIBUTE_NAME_KEY,
                    String.class);
            configuration.setAttribute(configAttributeName, defaultUri);
        }
    }

    /**
     * @see de.uka.ipd.sdq.workflow.launchconfig.tabs.FileNamesInputTab#isValid(org.eclipse.debug.core.ILaunchConfiguration)
     */
    @Override
    public boolean isValid(final ILaunchConfiguration launchConfig) {
        return true;
    }
}

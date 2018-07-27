package org.palladiosimulator.simulizar.ui.wizards;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

public class ExampleWizardZnnCom extends Wizard implements INewWizard, IExecutableExtension {

    private WizardNewProjectCreationPage _pageOne;
    private IConfigurationElement _configurationElement;
    private static final String ZNN_COM_PATH = "/resources/archive/simulizar_example_znn_com.zip";
    public static String ALLOCATION_FILE_ATTRIBUTE_KEY="allocationFile";
	public static String LAUNCH_CONFIGURATION="SimuLizar ZNN.com.launch";
	public static String LAUNCH_CONFIGURATION_WITH_EXTENSION="SimuLizar ZNN.com.launch";
	public static String LOAD_BALANCER_EXAMPLE_URI="org.palladiosimulator.simulizar.examples.znncom";
	public static String PMS_FILE_ATTRIBUTE_KEY="pmsFile";
	public static String RECONFIGURATION_RULES_FOLDER_ATTRIBUTE_KEY="reconfigurationRulesFolder";
	public static String MONITOR_REPOSITORY_FILE_ATTRIBUTE_KEY="monitorRepositoryFile";
	public static String USAGE_FILE_ATTRIBUTE_KEY="usageFile";
	public static String WIZARD_PAGE_TITLE="SimuLizar example project - Znn.com";
	public static String EXAMPLE_DESCRIPTION="SimuLizar example project.";

    public ExampleWizardZnnCom() {
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean performFinish() {
        String name = _pageOne.getProjectName();
        URI location = null;
        if (!_pageOne.useDefaults()) {
            location = _pageOne.getLocationURI();
        } // else location == null

        IProject project = ExampleWizardSupport.createProject(name, location, ZNN_COM_PATH);

        BasicNewProjectResourceWizard.updatePerspective(_configurationElement);

        try {
			ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();

			// Adapting the SimuLizar-LoadBalancer launch configuration
			ILaunchConfiguration readOnlyLaunchConfiguration = manager.getLaunchConfiguration(project.getFile(LAUNCH_CONFIGURATION_WITH_EXTENSION));
			ILaunchConfigurationWorkingCopy writableLaunchConfiguration = readOnlyLaunchConfiguration.copy(LAUNCH_CONFIGURATION);
			
			List<String> keysOfAttributesToChange = Arrays.asList(ALLOCATION_FILE_ATTRIBUTE_KEY, 
																  PMS_FILE_ATTRIBUTE_KEY, 
																  RECONFIGURATION_RULES_FOLDER_ATTRIBUTE_KEY, 
																  USAGE_FILE_ATTRIBUTE_KEY,
																  MONITOR_REPOSITORY_FILE_ATTRIBUTE_KEY);			
			ExampleWizardSupport.modifyLaunchConfigurationAttributeValues(keysOfAttributesToChange, 
													 LOAD_BALANCER_EXAMPLE_URI, 
													 project.getName(), 
													 readOnlyLaunchConfiguration, 
													 writableLaunchConfiguration);
        }catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return true;
    }

    @Override
    public void addPages() {
        super.addPages();

        _pageOne = new WizardNewProjectCreationPage(WIZARD_PAGE_TITLE);
        _pageOne.setTitle(WIZARD_PAGE_TITLE);
        _pageOne.setDescription(EXAMPLE_DESCRIPTION);

        addPage(_pageOne);
    }

    @Override
    public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
            throws CoreException {
        _configurationElement = config;

    }

}

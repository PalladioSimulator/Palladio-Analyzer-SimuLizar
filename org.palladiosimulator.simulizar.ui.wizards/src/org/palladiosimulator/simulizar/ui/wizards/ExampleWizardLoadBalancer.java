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

public class ExampleWizardLoadBalancer extends Wizard implements INewWizard, IExecutableExtension {

	private WizardNewProjectCreationPage _pageOne;
	private IConfigurationElement _configurationElement;
	private static final String LOAD_BALANCER_PATH = "/resources/archive/simulizar_example_load_balancer.zip";
	public static String ALLOCATION_FILE_ATTRIBUTE_KEY="allocationFile";
	public static String CONFIDENCE_MODEL_ELEMENT_URI_ATTRIBUTE_KEY="confidenceModelElementURI";
	public static String LAUNCH_CONFIGURAION_2="SimuLizar Load Balancer";
	public static String LAUNCH_CONFIGURAION_2_WITH_EXTENSION="SimuLizar Load Balancer.launch";
	public static String LAUNCH_CONFIGURAION_3="SimuLizar Scalability Load Balancer";
	public static String LAUNCH_CONFIGURAION_3_WITH_EXTENSION="SimuLizar Scalability Load Balancer.launch";
	public static String LAUNCH_CONFIGURATION_1="SimuLizar-LoadBalancer";
	public static String LAUNCH_CONFIGURATION_1_WITH_EXTENSION="SimuLizar-LoadBalancer.launch";
	public static String LOAD_BALANCER_EXAMPLE_URI="org.palladiosimulator.simulizar.examples.loadbalancer";
	public static String MONITOR_REPOSITORY_FILE_ATTRIBUTE_KEY="monitorRepositoryFile";
	public static String PMS_FILE_ATTRIBUTE_KEY="pmsFile";
	public static String RECONFIGURATION_RULES_FOLDER_ATTRIBUTE_KEY="reconfigurationRulesFolder";
	public static String SLO_FILE_ATTRIBUTE_KEY="serviceLevelObjectiveRepositoryFile";
	public static String USAGE_FILE_ATTRIBUTE_KEY="usageFile";
	public static String WIZARD_PAGE_TITLE="SimuLizar example project - Load balancer";
	public static String EXAMPLE_DESCRIPTION="SimuLizar example project.";

	public ExampleWizardLoadBalancer() {
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
		}

		IProject project = ExampleWizardSupport.createProject(name, location, LOAD_BALANCER_PATH);

		BasicNewProjectResourceWizard.updatePerspective(_configurationElement);

		try {
			ILaunchManager manager = DebugPlugin.getDefault().getLaunchManager();

			// Adapting the SimuLizar-LoadBalancer launch configuration
			ILaunchConfiguration readOnlyLaunchConfiguration = manager.getLaunchConfiguration(project.getFile(LAUNCH_CONFIGURATION_1_WITH_EXTENSION));
			ILaunchConfigurationWorkingCopy writableLaunchConfiguration = readOnlyLaunchConfiguration.copy(LAUNCH_CONFIGURATION_1);
			
			List<String> keysOfAttributesToChange = Arrays.asList(ALLOCATION_FILE_ATTRIBUTE_KEY, 
																  PMS_FILE_ATTRIBUTE_KEY, 
																  RECONFIGURATION_RULES_FOLDER_ATTRIBUTE_KEY, 
																  USAGE_FILE_ATTRIBUTE_KEY, 
																  MONITOR_REPOSITORY_FILE_ATTRIBUTE_KEY, 
																  SLO_FILE_ATTRIBUTE_KEY, 
																  CONFIDENCE_MODEL_ELEMENT_URI_ATTRIBUTE_KEY);			
			ExampleWizardSupport.modifyLaunchConfigurationAttributeValues(keysOfAttributesToChange, 
													 LOAD_BALANCER_EXAMPLE_URI, 
													 project.getName(), 
													 readOnlyLaunchConfiguration, 
													 writableLaunchConfiguration);
			
			// Adapting the SimuLizar Load Balancer launch
			// configuration
			readOnlyLaunchConfiguration = manager.getLaunchConfiguration(project.getFile(LAUNCH_CONFIGURAION_2_WITH_EXTENSION));
			writableLaunchConfiguration = readOnlyLaunchConfiguration.copy(LAUNCH_CONFIGURAION_2);
			
			keysOfAttributesToChange = Arrays.asList(ALLOCATION_FILE_ATTRIBUTE_KEY, 
													 RECONFIGURATION_RULES_FOLDER_ATTRIBUTE_KEY, 
													 USAGE_FILE_ATTRIBUTE_KEY, 
													 MONITOR_REPOSITORY_FILE_ATTRIBUTE_KEY, 
													 SLO_FILE_ATTRIBUTE_KEY, 
													 CONFIDENCE_MODEL_ELEMENT_URI_ATTRIBUTE_KEY);
			ExampleWizardSupport.modifyLaunchConfigurationAttributeValues(keysOfAttributesToChange, 
													 LOAD_BALANCER_EXAMPLE_URI, 
													 project.getName(), 
													 readOnlyLaunchConfiguration, 
													 writableLaunchConfiguration); //$NON-NLS-1$
			
			// Adapting the SimuLizar Scalability Load Balancer launch
			// configuration - commented out because the project requires the dependency to 
			// org.palladiosimulator.simulizar.reconfiguration.storydiagram, which is not installed with SimuLizar at the moment (02.09.2015).
			/* readOnlyLaunchConfiguration = manager.getLaunchConfiguration(project.getFile(LAUNCH_CONFIGURAION_3_WITH_EXTENSION));
			writableLaunchConfiguration = readOnlyLaunchConfiguration.copy(LAUNCH_CONFIGURAION_3);
			
			keysOfAttributesToChange = Arrays.asList(ALLOCATION_FILE_ATTRIBUTE_KEY, RECONFIGURATION_RULES_FOLDER_ATTRIBUTE_KEY, USAGE_FILE_ATTRIBUTE_KEY, MONITOR_REPOSITORY_FILE_ATTRIBUTE_KEY, SLO_FILE_ATTRIBUTE_KEY);
			modifyLaunchConfigurationAttributeValues(keysOfAttributesToChange, LOAD_BALANCER_EXAMPLE_URI, project.getName(), readOnlyLaunchConfiguration, writableLaunchConfiguration);*/
		} catch (CoreException e) {
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
		_pageOne.setDescription(EXAMPLE_DESCRIPTION); //$NON-NLS-1$

		addPage(_pageOne);
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException {
		_configurationElement = config;

	}

}

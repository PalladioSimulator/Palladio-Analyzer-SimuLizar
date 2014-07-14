package org.palladiosimulator.simulizar.ui.wizards;

import java.net.URI;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

public class ExampleWizardZnnCom extends Wizard implements INewWizard, IExecutableExtension {

	private WizardNewProjectCreationPage _pageOne;
	private IConfigurationElement _configurationElement;
	private static final String ZNN_COM_PATH="/resources/archive/simulizar_example_znn_com.zip";
	
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
	 
	    ExampleWizardSupport.createProject(name, location, ZNN_COM_PATH);
	 
	    BasicNewProjectResourceWizard.updatePerspective(_configurationElement);
	    
	    return true;
	}
	
	@Override
	public void addPages() {
	    super.addPages();
	 
	    _pageOne = new WizardNewProjectCreationPage("SimuLizar example project - Znn.com");
	    _pageOne.setTitle("SimuLizar example project - Znn.com");
	    _pageOne.setDescription("SimuLizar example project.");
	 
	    addPage(_pageOne);
	}

	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		_configurationElement = config;
		
	}

}

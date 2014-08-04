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

public class ExampleWizardLoadBalancer extends Wizard implements INewWizard, IExecutableExtension {

    private WizardNewProjectCreationPage _pageOne;
    private IConfigurationElement _configurationElement;
    private static final String LOAD_BALANCER_PATH = "/resources/archive/simulizar_example_load_balancer.zip";

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

        ExampleWizardSupport.createProject(name, location, LOAD_BALANCER_PATH);

        BasicNewProjectResourceWizard.updatePerspective(_configurationElement);

        return true;
    }

    @Override
    public void addPages() {
        super.addPages();

        _pageOne = new WizardNewProjectCreationPage("SimuLizar example project - Load balancer");
        _pageOne.setTitle("SimuLizar example project - Load balancer");
        _pageOne.setDescription("SimuLizar example project.");

        addPage(_pageOne);
    }

    @Override
    public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
            throws CoreException {
        _configurationElement = config;

    }

}

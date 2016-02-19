package org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment.pages;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment.DynamicEnvironmentModelCreationWizard;
import org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment.editors.UsageModelSelectionEditor;

import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;

/**
 * Class for the page used for selecting the usage model
 * @author Vinay Akkasetty Gopal
 */
public class SelectUsageModelPage extends WizardPage {
	
	private UsageModelSelectionEditor modelEditor = null;
	private UsageModel model = null;
	IStructuredSelection selection = null;
	
	private Resource resource = null;
	
	public SelectUsageModelPage(String pageName, IStructuredSelection selection) 
	{
		super(pageName);
		this.selection = selection;
		this.setPageComplete(false);
		this.setTitle("Dynamic Environment Model");
		this.setDescription("Select a Usage Model");
	}
	
	/**
	 * Creates the specific controls for the wizard page
	 * @param parent
	 */
	@Override
	public void createControl(Composite parent) {
		// top level group
		Composite topLevel = new Composite(parent, SWT.NONE);
		topLevel.setLayout(new GridLayout());
		topLevel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
				| GridData.HORIZONTAL_ALIGN_FILL));
		topLevel.setFont(parent.getFont());
		
		this.setControl(topLevel);
		
		this.modelEditor = new UsageModelSelectionEditor("modelEditor", "Usage Model", topLevel);
		this.modelEditor.setPropertyChangeListener(new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				SelectUsageModelPage.this.loadUsageModel();
				SelectUsageModelPage.this.setPageComplete(true);				
			}});
		
		
		if(selection.getFirstElement() instanceof IFile)
		{
			IFile initial = (IFile) selection.getFirstElement();
			if("usagemodel".equals(initial.getFileExtension()))
					this.modelEditor.setStringValue(initial.getFullPath().toString());
		}		
		
	}
	
	/**
	 * Returns true when all the required information on the current page is entered
	 * and the wizard can go to next page
	 */
	@Override
	public boolean canFlipToNextPage()
	{
		return super.canFlipToNextPage() && !this.modelEditor.getStringValue().isEmpty();
	}
	
	private void loadUsageModel() {	
		String filename = this.modelEditor.getStringValue();
		
		if(!filename.endsWith(".usagemodel"))
			return;
		
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("usagemodel", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		// Get the resource
		resource = resSet.getResource(URI.createURI(filename), true);

		if(resource.getContents().isEmpty())
			return;
		
		if(!(resource.getContents().get(0) instanceof UsageModel))
			return;
		
		// get the root-element and the components-category
		this.model = (UsageModel) resource.getContents().get(0);
				
		if(this.getWizard() instanceof DynamicEnvironmentModelCreationWizard)
		{
			String name = resource.getURI().trimFileExtension().lastSegment();
			((DynamicEnvironmentModelCreationWizard)this.getWizard()).setUsageModel(this.model, name);
		}
		
		
	}
	
	/**
	 * Returns the resource
	 * @return Resource
	 */
	public Resource getResource() {
		return this.resource;
	}
	
	/**
	 * Returns the model
	 * @return RootNode
	 */
	public UsageModel getModel() {
		return this.model;
	}
	
	/**
	 * Returns the editor value
	 * @return String
	 */
	public String getModelFile() {
		return this.modelEditor.getStringValue();
	}
}

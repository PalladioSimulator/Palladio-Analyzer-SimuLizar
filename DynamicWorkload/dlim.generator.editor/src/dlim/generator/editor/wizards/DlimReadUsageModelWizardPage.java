package dlim.generator.editor.wizards;

import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelPackage;
import de.uka.ipd.sdq.workflow.launchconfig.tabs.TabHelper;
import de.uka.ipd.sdq.workflow.pcm.ConstantsContainer;


/**
 * Page for providing PCM UsageModel file for modeling work and load variations
 * @author Vinay Akkasetty Gopal
 */

public class DlimReadUsageModelWizardPage extends WizardPage {
	
	private Text filePathText;
	private String filePath = "";
	
	protected Text textUsage;
	
	protected ResourceSet rs = new ResourceSetImpl();
	protected ModifyListener modifyListener;
	
	protected DlimReadUsageModelWizardPage(String pageName) {
		super(pageName);
	}
	
	/**
	 * Fills the control area. Provides a plot canvas for DLIM instance visualization.
	 * The interactive area must be filled by children implementations.
	 */
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent,SWT.NONE);
		GridLayout compositeLayout = new GridLayout(1,true);
		composite.setLayout(compositeLayout);
		{
			GridData data = new GridData();
			data.widthHint = parent.getSize().y;
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = true;
			data.verticalAlignment = GridData.FILL;
			data.grabExcessVerticalSpace = true;
			composite.setLayoutData(data);
		}
		Composite interactiveArea = new Composite(composite,SWT.NONE);
		interactiveArea.setLayout(new GridLayout(1,true));
		{
			GridData data = new GridData();
			//data.heightHint = 400;
			data.horizontalAlignment = GridData.FILL;
			data.grabExcessHorizontalSpace = false;
			data.grabExcessVerticalSpace = false;
			interactiveArea.setLayoutData(data);
		}
		
		fillInteractiveArea(interactiveArea);
		
		setPageComplete(validatePage());
		setControl(composite);
	}
	
	protected void fillInteractiveArea(Composite interactiveArea) {
		GridData parentData = new GridData();
		parentData.horizontalAlignment = GridData.FILL;
		interactiveArea.setLayoutData(parentData);
		
		Composite parent = new Composite(interactiveArea, SWT.NONE);
		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.marginWidth = 5;
		gridLayout.marginHeight = 5;
		gridLayout.verticalSpacing = 2;
		gridLayout.horizontalSpacing = 0;
		parent.setLayout(gridLayout);
		
		Composite fileSelectionComposite = new Composite(interactiveArea, SWT.NONE);
		GridLayout formSelectionLayout = new GridLayout(3,false);
		formSelectionLayout.marginWidth = 5;
		formSelectionLayout.marginHeight = 5;
		formSelectionLayout.verticalSpacing = 0;
		formSelectionLayout.horizontalSpacing = 0;
		fileSelectionComposite.setLayout(formSelectionLayout);
		createFileSelectionField(fileSelectionComposite);
	}
	
	private void createFileSelectionField(Composite fileSelectionComposite) {
		modifyListener = new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
		};
		filePathText = new Text(fileSelectionComposite, SWT.SINGLE | SWT.BORDER);
		TabHelper.createFileInputSection(fileSelectionComposite, modifyListener, "Usage Model File", ConstantsContainer.USAGEMODEL_EXTENSION, filePathText, "Select Usage Model File", getShell(), ConstantsContainer.DEFAULT_USAGE_FILE);
		addValidationListener(filePathText);
		/*Label parameterFieldLabel = new Label(fileSelectionComposite, SWT.NONE);
		parameterFieldLabel.setText("Usage Model file: ");
		GridData parameterFieldData = new GridData();
		parameterFieldData.grabExcessHorizontalSpace = false;
		parameterFieldData.horizontalAlignment = SWT.BEGINNING;
		parameterFieldData.widthHint = 300;
		filePathText = new Text(fileSelectionComposite, SWT.BORDER);
		filePathText.setText(filePath);
		filePathText.setLayoutData(parameterFieldData);
		addValidationListener(filePathText);
		Button fileDialogButton = new Button(fileSelectionComposite,SWT.PUSH);
		fileDialogButton.setText("Browse");
		fileDialogButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSelection(e);
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				handleSelection(e);
			}
			private void handleSelection(SelectionEvent e) {
				FileDialog dialog = new FileDialog(getShell(),SWT.OPEN);
				String[] filterNames = {"Usage Model file"};
				String[] filterExtensions = new String [] {"*.usagemodel"};
				dialog.setFilterNames(filterNames);
				dialog.setFilterExtensions(filterExtensions);
				dialog.setText("Select Usage Model File");
				String newPath = dialog.open();
				if (newPath != null && !newPath.isEmpty()) {
					filePathText.setText(newPath);
				}
			}
		});*/
	}
	
	/**
	 * Add this listener to any text area. It will trigger the validatePage() method.
	 * @param text
	 */
	protected void addValidationListener(Text text) {
		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
			
		});
	}
	
	protected boolean validatePage() {

		filePath = filePathText.getText();
		if (!filePath.isEmpty()) {			
			final EClass targetType = UsagemodelPackage.eINSTANCE.getUsageModel();
			
			Resource r = rs.getResource(URI.createURI(filePath), true);
			
			if (r != null && r.getContents().size() > 0 && targetType.isSuperTypeOf(r.getContents().get(0).eClass())) {
				CustomDlimModelWizard wizard = (CustomDlimModelWizard)getWizard();
		    	setMessage(getDescription());
		        wizard.setUsageModel((UsageModel) r.getContents().get(0));
		    }		   
			else {
		    	throw new RuntimeException("Failed to retrieve Usage model element " + targetType.getName());
		    } 
		}
		return true;
	}

}

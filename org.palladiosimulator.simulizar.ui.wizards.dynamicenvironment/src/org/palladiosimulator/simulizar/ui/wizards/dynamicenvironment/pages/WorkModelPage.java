package org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment.pages;

import org.apache.log4j.Logger;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment.DynamicEnvironmentModelCreationWizard;

import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import dlim.WorkLoadSequence;

public class WorkModelPage extends WizardPage {
	
	private UsageModel usageModel;
	
	private Label parameterFieldLabel = null;
	
	public WorkModelPage(String pageName, WorkLoadSequence rootWLSequence) {
		super(pageName);
	}
	
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
		parameterFieldLabel = new Label(composite, SWT.NONE);

//		setPageComplete(validatePage());
		setControl(composite);
	}

	public void setUsageModel(UsageModel usageModel) {
		this.usageModel = usageModel;
		if (this.usageModel != null) {
			for (UsageScenario us : this.usageModel.getUsageScenario_UsageModel()) {
				System.out.println("Work Page: " + us.getEntityName());
				
				parameterFieldLabel.setText(us.getEntityName());
			}
		}
	}

}

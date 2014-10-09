package dlim.generator.editor.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.workflow.launchconfig.tabs.TabHelper;
import de.uka.ipd.sdq.workflow.pcm.ConstantsContainer;
import dlim.WorkLoadSequence;

public class DlimWorkModelPage extends WizardPage{
	
	private UsageModel usageModel;
	
	protected DlimWorkModelPage(String pageName, WorkLoadSequence rootWLSequence) {
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
		
		if (this.usageModel != null) {
			for (UsageScenario us : this.usageModel.getUsageScenario_UsageModel()) {
				Label parameterFieldLabel = new Label(composite, SWT.NONE);
				parameterFieldLabel.setText(us.getEntityName());
			}
		}
		
//		setPageComplete(validatePage());
		setControl(composite);
	}

	public void setUsageModel(UsageModel usageModel) {
		this.usageModel = usageModel;
	}

}

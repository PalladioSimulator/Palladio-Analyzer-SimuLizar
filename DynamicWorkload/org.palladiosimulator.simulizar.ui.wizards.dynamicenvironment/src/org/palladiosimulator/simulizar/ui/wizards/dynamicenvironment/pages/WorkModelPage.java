package org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment.pages;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment.editingsupport.PropertyValueEditingSupport;

import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import dlim.DlimFactory;
import dlim.DlimPackage;
import dlim.Sequence;
import dlim.TimeDependentWorkFunctionContainer;
import dlim.WorkLoadSequence;

public class WorkModelPage extends WizardPage {
	
	private DlimPackage dlimPackage = DlimPackage.eINSTANCE;
	private DlimFactory dlimFactory = dlimPackage.getDlimFactory();
	private WorkLoadSequence rootWLSequence;
	private UsageModel usageModel;
	
	private Composite composite = null;
	
	private Text text_1;
	private TableViewer workAttributesTableViewer = null;
	private TableViewerColumn workColumn = null;
	private TableViewerColumn startTimeColumn = null;
	private TableViewerColumn durationColumn = null;
	private TableViewerColumn synchronizeColumn = null;
	private TableViewerColumn loadNameColumn = null;
	private TableViewerColumn loadFunctionColumn = null;
	private TableViewerColumn mutualLoadColumn = null;
	
	
//	private Label parameterFieldLabel = null;
	
	public WorkModelPage(String pageName, WorkLoadSequence rootWLSequence) {
		super(pageName);
		this.rootWLSequence = rootWLSequence;
		setDescription("For each usage scenario (work) specified in the usage model, here you can provide values to work attributes like start time, duration etc. And provide name to the load acting on work and the type of the load function. If no values are provided are provided, a dynamic workload model with default values will be created.");
		setTitle("Model Work Parameters");
	}
	
	@Override
	public void createControl(Composite parent) {
		this.composite = new Composite(parent,SWT.NONE);
//		GridLayout compositeLayout = new GridLayout(2,true);
//		composite.setLayout(compositeLayout);
//		{
//			GridData data = new GridData();
//			data.widthHint = parent.getSize().y;
//			data.horizontalAlignment = GridData.FILL;
//			data.grabExcessHorizontalSpace = true;
//			data.verticalAlignment = GridData.FILL;
//			data.grabExcessVerticalSpace = true;
//			composite.setLayoutData(data);
//		}
//		parameterFieldLabel = new Label(composite, SWT.NONE);

//		setPageComplete(validatePage());
		setControl(composite);
		
		Label lblTotalDuration = new Label(composite, SWT.NONE);
		lblTotalDuration.setBounds(10, 10, 87, 15);
		lblTotalDuration.setText("Total Duration:");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(94, 10, 87, 21);
		
//		Group grpWork = new Group(composite, SWT.NONE);
//		grpWork.setText("grp 1");
//		grpWork.setBounds(10, 41, 585, 149);
//		
//		Label lblStartTime = new Label(grpWork, SWT.NONE);
//		lblStartTime.setBounds(10, 24, 66, 15);
//		lblStartTime.setText("Start Time:");
//		
//		Text text_2 = new Text(grpWork, SWT.BORDER);
//		text_2.setBounds(109, 21, 76, 21);
//		
//		Label lblDuration = new Label(grpWork, SWT.NONE);
//		lblDuration.setBounds(10, 60, 55, 15);
//		lblDuration.setText("Duration:");
//		
//		Text text_3 = new Text(grpWork, SWT.BORDER);
//		text_3.setBounds(109, 57, 76, 21);
//		
//		Label lblLoadSequenceName = new Label(grpWork, SWT.NONE);
//		lblLoadSequenceName.setBounds(286, 24, 66, 15);
//		lblLoadSequenceName.setText("Load Name:");
//		
//		Text text_4 = new Text(grpWork, SWT.BORDER);
//		text_4.setBounds(360, 21, 195, 21);
//		
//		Label lblMutualLoad = new Label(grpWork, SWT.NONE);
//		lblMutualLoad.setBounds(272, 60, 90, 15);
//		lblMutualLoad.setText("Load Function:");
//		
//		Combo combo = new Combo(grpWork, SWT.NONE);
//		combo.setBounds(360, 57, 195, 23);
//		
//		Label lblTimeSynachronizedWith = new Label(grpWork, SWT.NONE);
//		lblTimeSynachronizedWith.setBounds(10, 99, 101, 15);
//		lblTimeSynachronizedWith.setText("Synchronize with:");
//		
//		Combo combo_2 = new Combo(grpWork, SWT.NONE);
//		combo_2.setBounds(109, 96, 145, 23);
//		
//		Label lblMutualLoad_1 = new Label(grpWork, SWT.NONE);
//		lblMutualLoad_1.setBounds(276, 99, 76, 15);
//		lblMutualLoad_1.setText("Mutual Load:");
//		
//		Combo combo_3 = new Combo(grpWork, SWT.NONE);
//		combo_3.setBounds(360, 96, 195, 23);
		
		this.workAttributesTableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		Table workAttributesTable = this.workAttributesTableViewer.getTable();
		workAttributesTable.setBounds(10, 62, 709, 327);
		workAttributesTable.setHeaderVisible(true);
		workAttributesTable.setLinesVisible(true);
		setCellEditor(workAttributesTable, 1);
		setCellEditor(workAttributesTable, 2);
		setCellEditor(workAttributesTable, 3);
		setCellEditor(workAttributesTable, 4);
		setCellEditor(workAttributesTable, 5);
		setCellEditor(workAttributesTable, 6);
		
		workColumn = new TableViewerColumn(workAttributesTableViewer, SWT.NONE);
		workColumn.getColumn().setWidth(100);
		workColumn.getColumn().setText("Work");
		workColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TimeDependentWorkFunctionContainer tdwfc = (TimeDependentWorkFunctionContainer) element;
				return tdwfc.getWork().getEntityName();
			}
			@Override
			public Color getBackground(Object element) {
				return new Color(Display.getDefault(), new RGB(242, 242, 242));
			}
		});
		
		startTimeColumn = new TableViewerColumn(workAttributesTableViewer, SWT.NONE);
		startTimeColumn.getColumn().setWidth(100);
		startTimeColumn.getColumn().setText("Start Time");
		startTimeColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TimeDependentWorkFunctionContainer tdwfc = (TimeDependentWorkFunctionContainer) element;
				String startTime = tdwfc.getWorkStartTime()+"";
				return startTime;
			}
		});
		
		durationColumn = new TableViewerColumn(workAttributesTableViewer, SWT.NONE);
		durationColumn.getColumn().setWidth(100);
		durationColumn.getColumn().setText("Duration");
		durationColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TimeDependentWorkFunctionContainer tdwfc = (TimeDependentWorkFunctionContainer) element;
				String duration = tdwfc.getWorkDuration()+"";
				return duration;
			}
		});
		
		synchronizeColumn = new TableViewerColumn(workAttributesTableViewer, SWT.NONE);
		synchronizeColumn.getColumn().setWidth(100);
		synchronizeColumn.getColumn().setText("Synchronize With");
		synchronizeColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TimeDependentWorkFunctionContainer tdwfc = (TimeDependentWorkFunctionContainer) element;
				String cName = "";
				if (tdwfc.getTimeSynchronization() != null)
					cName = tdwfc.getTimeSynchronization().getWork().getEntityName();
				return cName;
			}
		});
		  
		
		loadNameColumn = new TableViewerColumn(workAttributesTableViewer, SWT.NONE);
		loadNameColumn.getColumn().setWidth(100);
		loadNameColumn.getColumn().setText("Load Name");
		loadNameColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TimeDependentWorkFunctionContainer tdwfc = (TimeDependentWorkFunctionContainer) element;
				return tdwfc.getLoadSequence().getName();
			}
		});
		
		loadFunctionColumn = new TableViewerColumn(workAttributesTableViewer, SWT.NONE);
		loadFunctionColumn.getColumn().setWidth(100);
		loadFunctionColumn.getColumn().setText("Load Function");
		loadFunctionColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TimeDependentWorkFunctionContainer tdwfc = (TimeDependentWorkFunctionContainer) element;
				return tdwfc.getLoadSequence().getName();
			}
		});
		
		mutualLoadColumn = new TableViewerColumn(workAttributesTableViewer, SWT.NONE);
		mutualLoadColumn.getColumn().setWidth(100);
		mutualLoadColumn.getColumn().setText("Mutual Load");
		mutualLoadColumn.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				TimeDependentWorkFunctionContainer tdwfc = (TimeDependentWorkFunctionContainer) element;
				String cName = "";
				if (tdwfc.getTimeSynchronization() != null)
					cName = tdwfc.getTimeSynchronization().getWork().getEntityName();
				return cName;
			}
		});
		
		
	}

	public void setUsageModel(UsageModel usageModel) {
		this.usageModel = usageModel;
		if (this.usageModel != null) {
			for (UsageScenario us : this.usageModel.getUsageScenario_UsageModel()) {
				System.out.println("Work Page: " + us.getEntityName());
//				this.getControl().getParent().layout(true,true)
//				Label parameterFieldLabel = new Label(composite, SWT.NONE);
//				parameterFieldLabel.setText(us.getEntityName());
				
				TimeDependentWorkFunctionContainer tdwfContainer = dlimFactory.createTimeDependentWorkFunctionContainer();
				tdwfContainer.setName(us.getEntityName());
				tdwfContainer.setWork(us);
				Sequence rootLoadObject = dlimFactory.createSequence();
				tdwfContainer.setLoadSequence(rootLoadObject);
				rootWLSequence.getWorkFunctionContainers().add(tdwfContainer);
				
				this.workAttributesTableViewer.add(tdwfContainer);
			}
			
//			synchronizeColumn.setEditingSupport(new PropertyValueEditingSupport(synchronizeColumn.getViewer(), this.rootWLSequence));
//			mutualLoadColumn.setEditingSupport(new PropertyValueEditingSupport(mutualLoadColumn.getViewer(), this.rootWLSequence));
		}
	}
	
	public void setCellEditor(final Table table, int cloumn) {
	    final TableEditor editor = new TableEditor(table);
	    // The editor must have the same size as the cell and must
	    // not be any smaller than 50 pixels.
	    editor.horizontalAlignment = SWT.LEFT;
	    editor.grabHorizontal = true;
	    editor.minimumWidth = 100;
	    // editing the second column
	    final int EDITABLECOLUMN = cloumn;
	    

	    table.addSelectionListener(new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        // Clean up any previous editor control
	        Control oldEditor = editor.getEditor();
	        if (oldEditor != null)
	          oldEditor.dispose();

	        // Identify the selected row
	        final TableItem item = (TableItem) e.item;
	        if (item == null)
	          return;

	        // The control that will be the editor must be a child of the
	        // Table
	        Text newEditor = new Text(table, SWT.NONE);
	        newEditor.setText(item.getText(EDITABLECOLUMN));
       
	        newEditor.addModifyListener(new ModifyListener() {
	          public void modifyText(ModifyEvent me) {
	            Text text = (Text) editor.getEditor();
	            editor.getItem()
	                .setText(EDITABLECOLUMN, text.getText());
	            if(EDITABLECOLUMN == 1)
	            	((TimeDependentWorkFunctionContainer)item.getData()).setWorkStartTime(Double.parseDouble(text.getText()));
	            if(EDITABLECOLUMN == 2)
	            	((TimeDependentWorkFunctionContainer)item.getData()).setWorkDuration(Double.parseDouble(text.getText()));
	            if(EDITABLECOLUMN == 4)
	            	((TimeDependentWorkFunctionContainer)item.getData()).getLoadSequence().setName(text.getText());
//	            if(EDITABLECOLUMN == 5)
	            	
	          }
	        });
	        newEditor.selectAll();
	        newEditor.setFocus();
	        editor.setEditor(newEditor, item, EDITABLECOLUMN);
	      }
	    });
	}
          
}

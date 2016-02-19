package org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment.editingsupport;

import java.util.Arrays;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import dlim.TimeDependentWorkFunctionContainer;
import dlim.WorkLoadSequence;

public class PropertyValueEditingSupport extends EditingSupport{
	
	private String[] valueArray = null;  
    private Composite parent; 
    private ComboBoxViewerCellEditor combobox_editor;  
    private WorkLoadSequence wlSequence;
    private ColumnViewer viewer = null;
    
    public PropertyValueEditingSupport(ColumnViewer viewer, WorkLoadSequence wlSequence) {  
        super(viewer);
        this.viewer = viewer;
        this.wlSequence = wlSequence;
        this.valueArray = new String[wlSequence.getWorkFunctionContainers().size()];
        parent =((TableViewer) viewer).getTable(); 
        System.out.println("Sequence length: "+wlSequence.getWorkFunctionContainers().size());
//        combobox_editor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(), SWT.READ_ONLY|SWT.DROP_DOWN);
        int i = 0;
        for (TimeDependentWorkFunctionContainer tdwfc : wlSequence.getWorkFunctionContainers()) {
        	valueArray[i] = tdwfc.getWork().getEntityName();
        	i++;
        }
        System.out.println("Array: "+Arrays.toString(valueArray));
        combobox_editor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(), SWT.READ_ONLY|SWT.DROP_DOWN);
//        combobox_editor.setItems(valueArray);
        combobox_editor.setContentProvider(new ArrayContentProvider());
        combobox_editor.setInput(valueArray);
    }  
  
    @Override  
    protected boolean canEdit(Object element) {  
        return true;  
    }  
  
    @Override  
    protected CellEditor getCellEditor(Object element) { 	
    	return combobox_editor;  
    }  
  
    @Override  
    protected Object getValue(Object element) {
    	TimeDependentWorkFunctionContainer tdwfc = (TimeDependentWorkFunctionContainer)element;	
//		return this.wlSequence.getWorkFunctionContainers().indexOf(tdwfc);
    	System.out.println("getValue: "+tdwfc.getName());
    	return tdwfc.getTimeSynchronization().getWork().getEntityName();
//    	return null;
    	    
    }  
  
    @Override  
    protected void setValue(Object element, Object value) {  
        // Get the current service that's been selected  
        // if it's something else, value is an index in the service property values set  
    	TimeDependentWorkFunctionContainer tdwfc = (TimeDependentWorkFunctionContainer)element;
//    	tdwfc.setTimeSynchronization((TimeDependentWorkFunctionContainer) value);
    	System.out.println("element: "+tdwfc.getName());
    	System.out.println("value: "+value);
//    	tdwfc.setTimeSynchronization((TimeDependentWorkFunctionContainer)value);
    	this.viewer.update(tdwfc, null);
    }  
}

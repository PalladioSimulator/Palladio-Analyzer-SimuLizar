package org.palladiosimulator.simulizar.ui.wizards.dynamicenvironment.editingsupport;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import dlim.DlimFactory;
import dlim.DlimPackage;
import dlim.Function;
import dlim.Sequence;
import dlim.TimeDependentWorkFunctionContainer;

public class LoadFunctionEditingSupport extends EditingSupport {
	
//	private String[] valueArray = {"Linear Trend", "Logarithmic Trend", "Exponential Trend", "Sin Trend", "Absolute Sin", "Absolute Value Function", "Uniform Noise", "Normal Noise", "Burst"};
	public static enum Value {LinearTrend, LogarithmicTrend, ExponentialTrend, SinTrend, AbsoluteSin, AbsoluteValueFunction, UniformNoise, NormalNoise, Burst};
	private ComboBoxViewerCellEditor combobox_editor;
	private ColumnViewer viewer = null;
	
	private DlimPackage dlimPackage = DlimPackage.eINSTANCE;
	private DlimFactory dlimFactory = dlimPackage.getDlimFactory();
	
	public LoadFunctionEditingSupport(ColumnViewer viewer) {
		 super(viewer);
	     this.viewer = viewer;
	     combobox_editor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl());
	     combobox_editor.setContentProvider(new ArrayContentProvider());
	     combobox_editor.setInput(Value.values());
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
	  	return null;	    
	}
	
	@Override  
    protected void setValue(Object element, Object value) {
		if (element instanceof TimeDependentWorkFunctionContainer && value instanceof Value) {
	    	TimeDependentWorkFunctionContainer tdwfc = (TimeDependentWorkFunctionContainer)element;
	    	System.out.println("element: "+tdwfc.getName());
	    	System.out.println("value: "+value);
	    	Value v = (Value)value;
	    	Function function = null;
	    	if (v.equals(Value.LinearTrend)) {
	    		function = dlimFactory.createLinearTrend(); 		
	    	}
	    	tdwfc.getLoadSequence().getSequenceFunctionContainers().get(0).setFunction(function);
	    	this.viewer.update(tdwfc, null);
		}
    }
}

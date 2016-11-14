package org.palladiosimulator.simulizar.ui.perspective.filter;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;


public class MeasuringPointProfileApplicationFileFilter extends ViewerFilter {

	public MeasuringPointProfileApplicationFileFilter() {
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(element instanceof IFile){
			IFile f = (IFile)element;
			if(f.getName().endsWith(".mpProfile.pa.xmi")){
				return false;
			}
		}
		return true;
	}

}

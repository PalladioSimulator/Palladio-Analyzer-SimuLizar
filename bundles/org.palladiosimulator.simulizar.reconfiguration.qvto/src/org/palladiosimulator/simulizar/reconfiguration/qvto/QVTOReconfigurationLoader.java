package org.palladiosimulator.simulizar.reconfiguration.qvto;

import javax.inject.Inject;

import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.di.core.scopes.RuntimeExtensionScope;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurationLoader;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.ModelTransformationCache;

@RuntimeExtensionScope
public class QVTOReconfigurationLoader extends AbstractReconfigurationLoader {

	private static final String QVTO_FILE_EXTENSION = ".qvto";
	
	@Inject
	public QVTOReconfigurationLoader() {
    }

	@Override
	protected String getTransformationFileExtension() {
		return QVTO_FILE_EXTENSION;
	}

	@Override
	protected void setTransformations(URI[] transURIs) {
		new ModelTransformationCache(transURIs).getAll().forEach(t -> this.transformations.add(t));
	}
}

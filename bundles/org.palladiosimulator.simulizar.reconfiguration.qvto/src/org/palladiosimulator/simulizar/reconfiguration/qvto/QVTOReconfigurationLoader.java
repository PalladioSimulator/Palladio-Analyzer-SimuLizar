package org.palladiosimulator.simulizar.reconfiguration.qvto;

import jakarta.inject.Inject;

import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurationLoader;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.ModelTransformationCache;
import org.palladiosimulator.simulizar.scopes.RuntimeExtensionScope;

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

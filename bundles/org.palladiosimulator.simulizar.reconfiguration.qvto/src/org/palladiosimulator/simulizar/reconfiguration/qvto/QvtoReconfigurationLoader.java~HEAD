package org.palladiosimulator.simulizar.reconfiguration.qvto;

import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurationLoader;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.ModelTransformationCache;

public class QvtoReconfigurationLoader extends AbstractReconfigurationLoader {

	private static final String QVTO_FILE_EXTENSION = ".qvto";

	@Override
	protected String getTransformationFileExtension() {
		return QVTO_FILE_EXTENSION;
	}

	@Override
	protected void setTransformations(URI[] transURIs) {
		new ModelTransformationCache(transURIs).getAll().forEach(t -> this.transformations.add(t));
	}
}

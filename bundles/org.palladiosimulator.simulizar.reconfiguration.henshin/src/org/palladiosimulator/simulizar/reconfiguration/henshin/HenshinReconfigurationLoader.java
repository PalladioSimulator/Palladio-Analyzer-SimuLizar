package org.palladiosimulator.simulizar.reconfiguration.henshin;

import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.henshin.model.Module;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurationLoader;

public class HenshinReconfigurationLoader extends AbstractReconfigurationLoader {

	private static final String HENSHIN_FILE_EXTENSION = ".henshin";
	
	@Override
	protected String getTransformationFileExtension() {
		return HENSHIN_FILE_EXTENSION;
	}

	@Override
	protected void setTransformations(URI[] transURIs) {
		for (URI each : transURIs) {
			Optional<HenshinModelTransformation> trans = toHenshinModelTransformation(each);
			if (trans.isPresent()) {
				this.transformations.add(trans.get());
			}
		}
	}
	
	private Optional<HenshinModelTransformation> toHenshinModelTransformation(URI henshinURI) {
		ResourceSet rsHelper = new ResourceSetImpl();
		for (EObject each : rsHelper.getResource(henshinURI, true).getContents()) {
			if (each instanceof Module) {
				return Optional.of(new HenshinModelTransformation((Module) each));
			}
		}
		return Optional.empty();
	}
}

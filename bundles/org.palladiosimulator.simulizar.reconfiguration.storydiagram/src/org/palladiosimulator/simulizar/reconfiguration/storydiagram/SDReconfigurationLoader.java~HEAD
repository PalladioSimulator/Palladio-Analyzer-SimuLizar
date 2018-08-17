package org.palladiosimulator.simulizar.reconfiguration.storydiagram;

import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurationLoader;
import org.storydriven.storydiagrams.activities.Activity;

public class SDReconfigurationLoader extends AbstractReconfigurationLoader {

	public static final String STORYDIAGRAMS_FILE_EXTENSION = ".sdm";

	@Override
	protected String getTransformationFileExtension() {
		return STORYDIAGRAMS_FILE_EXTENSION;
	}

	@Override
	protected void setTransformations(URI[] transURIs) {
		for (URI each : transURIs) {
			toSDModelTransformation(each).ifPresent(trans -> this.transformations.add(trans));
		}
	}

	private Optional<SDModelTransformation> toSDModelTransformation(URI sdURI) {
		ResourceSet rsHelper = new ResourceSetImpl();
		Resource sdResource = rsHelper.getResource(sdURI, true);
		if (sdResource.getContents().isEmpty()) {
			return Optional.empty();
		}
		Activity activity = (Activity) sdResource.getContents().get(0);
		return Optional.of(new SDModelTransformation(activity));
	}
}

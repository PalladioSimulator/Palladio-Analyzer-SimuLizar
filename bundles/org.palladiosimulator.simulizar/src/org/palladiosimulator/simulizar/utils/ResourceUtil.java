package org.palladiosimulator.simulizar.utils;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.palladiosimulator.runtimemeasurement.util.RuntimeMeasurementResourceFactoryImpl;

public class ResourceUtil {
	
	private final static String RM_MODEL_FILE_EXTENSION = "runtimemeasurement";
	private final static String DEFAULT_FILE_NAME = "tmp";
	
	public static Resource createRuntimeMeasurementModelResource(String storingLocation) {
		return createResourceOf(RM_MODEL_FILE_EXTENSION, new RuntimeMeasurementResourceFactoryImpl(), storingLocation);
	}
	
	public static Resource createResourceOf(String fileExt, ResourceFactoryImpl resourceFactory, String storingLocation) {
		ResourceSet rsHelper = new ResourceSetImpl();
		rsHelper.getResourceFactoryRegistry()
		 		.getExtensionToFactoryMap()
		 		.put(fileExt, resourceFactory);
		 
		URI rmModelUri = URI.createURI(construct(fileExt, storingLocation));
		return rsHelper.createResource(rmModelUri); 
	}
	
	private static String construct(String fileExt, String storingLocation) {
		storingLocation = removeLastSegment(storingLocation);
		return String.format("%1s%2s.%3s", storingLocation, DEFAULT_FILE_NAME, fileExt);
	}

	private static String removeLastSegment(String storingLocation) {
		String last = URI.createFileURI(storingLocation).lastSegment();
		return storingLocation.replace(last, "");
	}
}

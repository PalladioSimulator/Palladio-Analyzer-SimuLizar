package org.palladiosimulator.simulizar.utils;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.palladiosimulator.runtimemeasurement.util.RuntimeMeasurementResourceFactoryImpl;

/**
 * Util class to create resources.
 * 
 * @author scheerer
 *
 */
public class ResourceUtil {

	private static final Logger LOGGER = Logger.getLogger(ResourceUtil.class.getName());

	private final static String RM_MODEL_FILE_EXTENSION = ".runtimemeasurement";
	private final static String DEFAULT_FILE_NAME = "tmp";

	/**
	 * Creates a runtime measurement model based on the specified path.
	 * 
	 * @param storingLocation
	 * @see #createResourceOf(String, ResourceFactoryImpl, String)
	 * @return @see #createResourceOf(String, ResourceFactoryImpl, String)
	 */
	public static Resource createRuntimeMeasurementModelResource(String storingLocation) {
		return createResourceOf(RM_MODEL_FILE_EXTENSION, new RuntimeMeasurementResourceFactoryImpl(), storingLocation);
	}

	/**
	 * Creates a resource of a model based on the specified path, file extension and
	 * resource factory.
	 * 
	 * @param fileExt         file extension of the created resource
	 * @param resourceFactory factory required to create the resource
	 * @param storingLocation specifies the storing location of the runtime
	 *                        measurement model.
	 * @return the created resource of the runtime measurement model.
	 */
	public static Resource createResourceOf(String fileExt, ResourceFactoryImpl resourceFactory,
			String storingLocation) {
		ResourceSet rsHelper = new ResourceSetImpl();
		rsHelper.getResourceFactoryRegistry().getExtensionToFactoryMap().put(fileExt, resourceFactory);

		if (isNotValid(storingLocation)) {
			return rsHelper.createResource(defaultFileURI());
		}

		URI rmModelUri = URI.createURI(construct(fileExt, storingLocation));
		if (rmModelUri.isPlatformResource() == false || storingLocation.isEmpty()) {
			rmModelUri = defaultFileURI();
		}
		return rsHelper.createResource(rmModelUri);
	}

	private static boolean isNotValid(String storingLocation) {
		return storingLocation == null || storingLocation.isEmpty();
	}

	private static String construct(String fileExt, String storingLocation) {
		storingLocation = removeLastSegment(storingLocation);
		return String.format("%1s%2s.%3s", storingLocation, DEFAULT_FILE_NAME, fileExt);
	}

	private static String removeLastSegment(String storingLocation) {
		String last = URI.createFileURI(storingLocation).lastSegment();
		return storingLocation.replace(last, "");
	}

	private static URI defaultFileURI() {
		File tmp = null;
		try {
			tmp = File.createTempFile(DEFAULT_FILE_NAME, RM_MODEL_FILE_EXTENSION);
		} catch (IOException ex) {
			LOGGER.info(String.format("The temporary file for the runtime measurement model could not be created: %s",
					ex.getMessage()));
		}
		return URI.createFileURI(tmp.getAbsolutePath());
	}
}

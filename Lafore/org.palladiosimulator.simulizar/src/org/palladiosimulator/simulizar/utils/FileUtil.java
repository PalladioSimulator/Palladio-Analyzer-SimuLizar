package org.palladiosimulator.simulizar.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.osgi.framework.Bundle;

public class FileUtil {
	/**
	 * Get the reconfiguration rule folder.
	 * 
	 * FIXME: Remove this and load reconfiguration rules into a blackboard.
	 * 
	 * @param path
	 *            String to the reconfiguration rules
	 * @return folder of the QVTo reconfiguration rules
	 */
	public static File getFolder(final String path) {
		// add file protocol only if necessary
		String filePath = path;
		File folder = null;
		if (!path.startsWith("platform:")) {
			filePath = "file:///" + filePath;

			URI folderURI = URI.createURI(filePath);
			folder = new File(folderURI.toFileString());
		} else {
			try {
				URL pathURL = FileLocator.resolve(new URL(path));
				String folderString = pathURL.toExternalForm().replace("file:", "");
				folder = new File(folderString);
			} catch (IOException e) {
				// LOGGER.warn("No QVTo rules found, QVTo reconfigurations
				// disabled.", e);
			}
		}
		return folder;
	}

	/**
	 * Get the reconfiguration rule files.
	 * 
	 * FIXME: Remove this and load reconfiguration rules into a blackboard.
	 * 
	 * @param folder
	 *            Filepath to the reconfiguration rules
	 * @return folder of the QVTo reconfiguration rules
	 */
	public static File[] getFiles(final File folder, final String fileExtension) {
		if (folder == null || !folder.exists()) {
			// LOGGER.warn("Folder " + folder +
			// " does not exist. No reconfiguration rules will be loaded.");
			return new File[0];
		}
		final File[] files = folder.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(final File dir, final String name) {
				return name.endsWith(fileExtension);
			}
		});
		return files;
	}

	/**
	 * Method which returns the absolute file path for a bundle name and a
	 * relative path
	 * 
	 * @param bundleName
	 *            The plugin ID where the file is located
	 * @param relativePath
	 *            The relative path in the plugin
	 * 
	 * @return absolute file path
	 */
	public static String getAbsoluteFilename(String bundleName, String relativePath) {
		String absoluteFilename = "";
		URI platformPluginURI = URI.createPlatformPluginURI(bundleName + '/' + relativePath, true);
		absoluteFilename = platformPluginURI.toFileString();

		Bundle bundle = Platform.getBundle(bundleName);
		URL base = bundle.getEntry(relativePath);

		try {
			absoluteFilename = FileLocator.toFileURL(base).toString();
			if (absoluteFilename.startsWith("file:/")) {
				absoluteFilename = absoluteFilename.substring(6);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return absoluteFilename;
	}

}

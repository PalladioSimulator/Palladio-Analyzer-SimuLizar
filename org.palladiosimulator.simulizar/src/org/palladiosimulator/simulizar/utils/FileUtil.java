package org.palladiosimulator.simulizar.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.commons.eclipseutils.FileHelper;

public class FileUtil {

	protected static final Logger LOGGER = Logger.getLogger(FileUtil.class.getName());
    private static final String QVTO_FILE_EXTENSION = ".qvto";
    
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

            final URI folderURI = URI.createURI(filePath);
            folder = new File(folderURI.toFileString());
        } else {
            try {
                final URL pathURL = FileLocator.resolve(new URL(path));
                final String folderString = pathURL.toExternalForm().replace("file:", "");
                folder = new File(folderString);
            } catch (final IOException e) {
                // LOGGER.warn("No QVTo rules found, QVTo reconfigurations disabled.", e);
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
     * Gets the QVTO files within the specified path.
     * 
     * @param path
     *            Path to reconfiguration rules.
     * @return The QVTO files within the given path. Returns an empty array in case no files are
     *         found.
     */
    public static URI[] getQvtoFiles(final String path) {
        assert path != null;
        if (path.equals("")) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No path to QVTo rules given.");
            }
            return new URI[0];
        }

        final URI[] uris = FileHelper.getURIs(path, QVTO_FILE_EXTENSION);

        if (uris.length == 0) {
            LOGGER.info("No QVTo rules found, QVTo reconfigurations disabled.");
        }

        return uris;
    }
}

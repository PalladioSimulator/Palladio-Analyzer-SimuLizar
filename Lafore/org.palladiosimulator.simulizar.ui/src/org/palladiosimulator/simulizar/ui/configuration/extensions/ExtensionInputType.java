package org.palladiosimulator.simulizar.ui.configuration.extensions;

import org.palladiosimulator.simulizar.ui.configuration.InterpreterFileNamesInputTab;

/**
 * Enum to indicate what kind of contribution to the {@link InterpreterFileNamesInputTab} is intended:<br>
 * <ul>
 * <li>File input</li>
 * <li>Folder input</li>
 * </ul>
 * @author Florian Rosenthal
 *
 */
public enum ExtensionInputType {
    /**
     * File input is intended.
     */
    FILE,
    /**
     * Folder input is intended.
     */
    FOLDER;
}

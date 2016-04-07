package org.palladiosimulator.simulizar.ui.configuration.extensions;

import org.palladiosimulator.simulizar.ui.configuration.InterpreterFileNamesInputTab;
import org.palladiosimulator.simulizar.ui.configuration.extensions.ExtensionFileInputConfiguration.DefaultPropertyKeys;

/**
 * Base class for all factories that wish to create {@link ExtensionFileInputConfiguration}s.
 * 
 * @author Florian Rosenthal
 *
 */
public abstract class AbstractExtensionFileInputConfigurationBuilder {

    /**
     * Creates a client-specific {@link ExtensionFileInputConfiguration} that contains all the
     * properties required to contribute to the {@link InterpreterFileNamesInputTab} by exploiting
     * the {@code org.palladiosimulator.simulizar.ui.configuration.fileinput} extension point.
     * 
     * @return A {@link ExtensionFileInputConfiguration} that contains at least the properties
     *         specified by the keys in {@link DefaultPropertyKeys}.
     */
    public abstract ExtensionFileInputConfiguration buildConfiguration();
}

package org.palladiosimulator.simulizar.ui.configuration.extensions;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.ui.configuration.InterpreterFileNamesInputTab;


/**
 * Clients that wish to contribute to a Simulizar launch configuration in terms of additional files/folders 
 * that are meant to be publicly accessible, must extend this class. 
 * Furthermore, they have to make use of the corresponding extension point <i>org.palladiosimulator.simulizar.ui.configuration.fileinput</i>.
 * When a Simulizar launch configuration is displayed in Eclipse, the properties of this class are used to 
 * add a new {@link Group} with an input field to the {@link InterpreterFileNamesInputTab}.
 * @author Florian Rosenthal
 *
 */
public abstract class AbstractExtensionFileInputHandler {

    protected static final String[] DEFAULT_FILE_EXTENSION_RESTRICTIONS = new String[0];
    protected static final String DEFAULT_FILE_URI = "";
    
    /**
     * Gets the title of the corresponding input field in the {@link InterpreterFileNamesInputTab}.
     * @return The title of the corresponding input field.
     */
    public abstract String getDialogTitle();
    
    /**
     * Gets the label of the {@link Group} in the {@link InterpreterFileNamesInputTab}
     * which contains the input field.
     * @return
     * @see #getDialogTitle()
     */
    public abstract String getGroupLabel();
    
    /**
     * In case a file is to be selected by the user, this method can be overriden
     * be clients to specify what files are allowed, e.g., "*.txt" files. 
     * @return An array whose elements denote file extension restrictions.<br>
     * The default implementation returns an empty String array.
     */
    public String[] getFileExtensionRestrictions() {
        return DEFAULT_FILE_EXTENSION_RESTRICTIONS;
    }
    
    /**
     * Clients can override this method when they want to define a default {@link URI}
     * which is used in case the user does not choose any.
     * @return The String representation of the default URI.<br>
     * The default implementation returns an empty String.
     */
    public String getDefaultFileUri() {
        return DEFAULT_FILE_URI;
    }
    
    /**
     * Client can override this method in order to examine the validity of a given {@link ILaunchConfiguration}
     * from their point of view. For instance, they could check whether the file/folder added to the configuration is valid.
     * @param configuration The current {@link ILaunchConfiguration}.
     * @return {@code true} in case the given configuration is valid, {@code false} otherwise.<br>
     * The default implementation always returns {@code true}.
     * @see #getConfigurationAttributeName()
     */
    public boolean isValid(ILaunchConfiguration configuration) {
        return true;
    }
    
    public abstract String getConfigurationAttributeName();
    
    /**
     * Callback method which is triggered after {@link InterpreterFileNamesInputTab#performApply(ILaunchConfigurationWorkingCopy)} has done its work.<br>
     * Clients may want to implement this method in order to do some sanity checks.
     * Note, that the corresponding configuration attribute has already been set (by the tab) to the value chosen by the user.<br>
     * The default implementation does nothing.
     * @param launchConfig A {@link ILaunchConfigurationWorkingCopy}, that is, and editable copy of the current launch configuration.
     */
    public void afterPerformApply(ILaunchConfigurationWorkingCopy currentLaunchConfig) {
    }

    /**
     * Callback method which is triggered after {@link InterpreterFileNamesInputTab#setDefaults(ILaunchConfigurationWorkingCopy)} has done its work.<br>
     * Clients may want to implement this method in order to do some sanity checks.
     * Note, that the corresponding configuration attribute has already been set (by the tab) to the default value.<br>
     * The default implementation does nothing.
     * @param launchConfig A {@link ILaunchConfigurationWorkingCopy}, that is, and editable copy of the current launch configuration.
     */
    public void afterSetDefaults(ILaunchConfiguration launchConfig) {
    }
}

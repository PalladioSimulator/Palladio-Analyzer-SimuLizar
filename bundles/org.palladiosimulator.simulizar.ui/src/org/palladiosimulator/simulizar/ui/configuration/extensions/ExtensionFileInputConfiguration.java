package org.palladiosimulator.simulizar.ui.configuration.extensions;

import java.util.Map;

import org.palladiosimulator.commons.datastructureutils.MapHelper;
import org.palladiosimulator.simulizar.ui.configuration.InterpreterFileNamesInputTab;

import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionConfigurationBuilder;

/**
 * Configuration required to be created by clients that wish to exploit
 * the {@code org.palladiosimulator.simulizar.ui.configuration.fileinput} extension point.
 * At least, values for the properties specified by the keys in {@link DefaultPropertyKeys}
 * have to be present.
 * @author Florian Rosenthal
 * @see AbstractWorkflowExtensionConfigurationBuilder
 */
public class ExtensionFileInputConfiguration {

    private final Map<String, Object> properties;

    public ExtensionFileInputConfiguration(Map<String, Object> properties) {
        if (properties == null) {
            throw new IllegalArgumentException("Given properties map must not be null.");
        }
        this.properties = properties;
    }

    public <T> T getPropertyByKey(String key, Class<T> expectedDataType) {
        return MapHelper.getValue(this.properties, key, expectedDataType);
    }

    public boolean isPropertySet(String propertyKey) {
        return this.properties.containsKey(propertyKey);
    }
    
    /**
     * Class that contains keys for the properties that at least have to be specified.
     * @author Florian Rosenthal
     *
     */
    public static class DefaultPropertyKeys {

        /**
         * Key used to specify a collection of allowed file extensions in terms of a {@code String[]}.
         * Each array element shall consist allowed extension (e.g., "*.txt").<br>
         * This property is only required for {@link ExtensionInputType#FILE}. 
         */
        public static final String FILE_RESTRICTIONS_KEY = "fileRestrictions";
        /**
         * Key used to specify a default model file of folder location.
         * If non is present, the empty string is to be passed.
         */
        public static final String DEFAULT_URI_KEY = "defaultUri";
        /**
         * Key used to specify a the title of the dialog that will be created on the {@link InterpreterFileNamesInputTab}.
         */
        public static final String DIALOG_TITLE_KEY = "dialogTitle";
        /**
         * Key used to specify a the label of the dialog group that will be created on the {@link InterpreterFileNamesInputTab}.
         */
        public static final String GROUP_LABEL_KEY = "groupLabel";
        /**
         * Key to specify whether a file or folder is to be selected by the user, denoted in terms
         * of the {@link ExtensionInputType} enum.
         */
        public static final String INPUT_TYPE_KEY = "inputType";
        /**
         * Key for the property to specify the name of the (Simulizar) launch configuration attribute that will
         * contain the path of the selected file (or folder).
         */
        public static final String CONFIG_ATTRIBUTE_NAME_KEY = "configAttribute";
        
        public static final Class<String[]> EXPECTED_FILE_RESTRICTIONS_DATA_TYPE = String[].class;
        public static final Class<ExtensionInputType> EXPECTED_INPUT_TYPE_DATA_TYPE = ExtensionInputType.class;
        
        private DefaultPropertyKeys() {
            //not supposed to be instantiated
        }
    }
}

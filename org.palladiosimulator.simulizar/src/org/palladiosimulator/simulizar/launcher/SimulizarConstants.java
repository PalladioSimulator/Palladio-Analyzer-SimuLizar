package org.palladiosimulator.simulizar.launcher;

public class SimulizarConstants {

    // Model filenames configuration identifiers
    public static final String PMS_FILE = "pmsFile";
    public static final String RECONFIGURATION_RULES_FOLDER = "reconfigurationRulesFolder";

    /*
     * Set the file extensions which the dialogs will use as selection filter and for file name
     * validation.
     */
    public static final String[] MONITORING_SPECIFICATION_FILE_EXTENSION = new String[] {
        "*.pms"
    };
    public static final String[] RECONFIGURATION_RULES_FILE_EXTENSION = new String[] {
            "*.sdm", ".qvto"
    };

    // Default values
    /** Default URI of the pms file. */
    public static final String DEFAULT_PMS_FILE = "";
    /** Default URI of the folder containing the sdm file. */
    public static final String DEFAULT_RECONFIGURATION_RULES_FOLDER = "";

}

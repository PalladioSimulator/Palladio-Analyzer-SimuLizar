package org.palladiosimulator.simulizar.launcher;

public class SimulizarConstants {

    // Model filenames configuration identifiers
    public static final String MONITOR_REPOSITORY_FILE = "monitorRepositoryFile";
    public static final String RECONFIGURATION_RULES_FOLDER = "reconfigurationRulesFolder";
    public static final String USAGEEVOLUTION_FILE = "usageEvolutionFile";
    public static final String SERVICELEVELOBJECTIVEREPOSITORY_FILE = "serviceLevelObjectiveRepositoryFile";
    public static final String INFRASTRUCTURE_MODEL_FILE = "infrastructureModelFile";

    /*
     * Set the file extensions which the dialogs will use as selection filter and for file name
     * validation.
     */
    public static final String[] MONITORING_SPECIFICATION_FILE_EXTENSION = new String[] { "*.monitorrepository" };
    public static final String[] RECONFIGURATION_RULES_FILE_EXTENSION = new String[] { "*.sdm", ".qvto", ".henshin" };
    public static final String[] USAGEEVOLUTION_FILE_EXTENSION = new String[] { "*.usageevolution" };
    public static final String[] SERVICELEVELOBJECTIVEREPOSITORY_FILE_EXTENSION = new String[] { "*.slo" };
    public static final String[] INFRASTRUCTURE_MODEL_FILE_EXTENSIONS = new String[] { "*.infrastructure" };

    // Default values
    /** Default URI of the Monitor Repository file. */
    public static final String DEFAULT_MONITOR_REPOSITORY_FILE = "";
    /** Default URI of the folder containing the reconfiguration rule files. */
    public static final String DEFAULT_RECONFIGURATION_RULES_FOLDER = "";
    /** Default URI of the usage evolution file. */
    public static final String DEFAULT_USAGEEVOLUTION_FILE = "";
    public static final String DEFAULT_INFRASTRUCTURE_MODEL_FILE = "";
    public static final String DEFAULT_SERVICELEVELOBJECTIVE_FILE = "";

}

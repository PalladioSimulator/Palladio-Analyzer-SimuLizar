package org.palladiosimulator.simulizar.launcher;

public class SimulizarConstants {

    // for the extension points
    public static final String MODEL_LOAD_EXTENSION_POINT_ID = "org.palladiosimulator.simulizar.launcher.modelload";
    public static final String MODEL_LOAD_EXTENSION_POINT_JOB_ATTRIBUTE = "loadJob";
    public static final String MODEL_LOAD_EXTENSION_POINT_JOB_CONFIG_BUILDER_ATTRIBUTE = "loadJobConfigBuilder";
    public static final String MODEL_LOAD_EXTENSION_POINT_BLACKBOARD_PARTITION_ID_ATTRIBUTE = "blackboardPartitionId";

    /**
     * Id of the extendible Runtime State observer point
     */
    public static final String RUNTIME_STATE_ACCESS_EXTENSION_POINT_ID = "org.palladiosimulator.simulizar.runtimestate.runtimestateaccessor";
    public static final String RUNTIME_STATE_ACCESS_EXTENSION_POINT_ACCESSOR_ATTRIBUTE = "accessor";

    // Model filenames configuration identifiers
    public static final String MONITOR_REPOSITORY_FILE = "monitorRepositoryFile";
    public static final String RECONFIGURATION_RULES_FOLDER = "reconfigurationRulesFolder";
    public static final String USAGEEVOLUTION_FILE = "usageEvolutionFile";
    public static final String SERVICELEVELOBJECTIVEREPOSITORY_FILE = "serviceLevelObjectiveRepositoryFile";

    /**
     * Id of the extension point used for reconfiguration engines
     */
    public static final String RECONFIGURATION_ENGINE_EXTENSION_POINT_ID = "org.palladiosimulator.simulizar.reconfigurationengine";
    public static final String RECONFIGURATION_ENGINE_EXTENSION_POINT_ENGINE_ATTRIBUTE = "reconfigurationEngine";

    /**
     * Id of the extension point used for adapting SimuLizar configurations
     */
    public static final String CONFIGURATOR_EXTENSION_POINT_ID = "org.palladiosimulator.simulizar.configurator";
    public static final String CONFIGURATOR_EXTENSION_POINT_ATTRIBUTE = "configurator";

    /*
     * Set the file extensions which the dialogs will use as selection filter and for file name
     * validation.
     */
    public static final String[] MONITORING_SPECIFICATION_FILE_EXTENSION = new String[] { "*.monitorrepository" };
    public static final String[] RECONFIGURATION_RULES_FILE_EXTENSION = new String[] { "*.sdm", ".qvto", ".henshin" };
    public static final String[] USAGEEVOLUTION_FILE_EXTENSION = new String[] { "*.usageevolution" };
    public static final String[] SERVICELEVELOBJECTIVEREPOSITORY_FILE_EXTENSION = new String[] { "*.slo" };

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

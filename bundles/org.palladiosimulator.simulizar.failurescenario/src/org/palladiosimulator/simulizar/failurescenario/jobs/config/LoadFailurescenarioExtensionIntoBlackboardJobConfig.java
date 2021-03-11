package org.palladiosimulator.simulizar.failurescenario.jobs.config;

public class LoadFailurescenarioExtensionIntoBlackboardJobConfig {
	private String failureModelPath;

    public static final String DEFAULT_FAILURE_MODEL_PATH = "";

    /**
     * Initializes a new instance of the
     * {@link LoadFailurescenarioExtensionIntoBlackboardJobConfig} class with the given
     * parameter.
     * 
     * @param failureModelPath
     *            A string containing the path to the Failure model to load.
     */
    public LoadFailurescenarioExtensionIntoBlackboardJobConfig(String failureModelPath) {
        if (failureModelPath == null || failureModelPath == "") {
            throw new IllegalArgumentException("Given path must not be null.");
        }
        this.failureModelPath = failureModelPath;
    }

    /**
     * Gets the path to the Failure model to load.
     * 
     * @return A string containing the path to the Failure model to load.
     */
    public String getFailureModelPath() {
        return this.failureModelPath;
    }
}

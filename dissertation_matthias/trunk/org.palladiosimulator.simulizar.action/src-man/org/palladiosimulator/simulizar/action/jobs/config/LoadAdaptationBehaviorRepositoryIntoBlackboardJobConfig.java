package org.palladiosimulator.simulizar.action.jobs.config;

import org.palladiosimulator.simulizar.action.jobs.LoadAdaptationBehaviorRepositoryIntoBlackBoardJob;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;

/**
 * This class is the configuration for the {@link LoadAdaptationBehaviorRepositoryIntoBlackBoardJob}
 * .
 * 
 * @see LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfigBuilder
 * @author Christian Stier, Florian Rosenthal
 *
 */
public class LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig extends AbstractExtensionJobConfiguration {

    private String adaptationBehaviorRepositoryPath;

    public static final String DEFAULT_ADAPTATION_BEHAVIOR_REPOSITORY_PATH = "platform:/plugin/org.palladiosimulator.simulizar.action.repository/model/repository.actions";

    /**
     * Initializes a new instance of the
     * {@link LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig} class with the given
     * parameter.
     * 
     * @param actionRepositoryPath
     *            A string containing the path to the Action model to load.
     */
    public LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig(String adaptationBehaviorRepositoryPath) {
        if (adaptationBehaviorRepositoryPath == null) {
            throw new IllegalArgumentException("Given path must not be null.");
        }
        this.adaptationBehaviorRepositoryPath = adaptationBehaviorRepositoryPath;
    }

    /**
     * Gets the path to the Action model to load.
     * 
     * @return A string containing the path to the Action model to load.
     */
    public String getAdaptationBehaviorRepositoryPath() {
        return this.adaptationBehaviorRepositoryPath;
    }

    @Override
    public String getErrorMessage() {
        return "Invalid Action Configuration!";
    }

    /**
     * {@inheritDoc}<br>
     * In this case, the path to the power infrastructure repository model is set to the
     * out-of-the-box supported Adaptation Behavior Repository path.
     */
    @Override
    public void setDefaults() {
        this.adaptationBehaviorRepositoryPath = DEFAULT_ADAPTATION_BEHAVIOR_REPOSITORY_PATH;
    }

}

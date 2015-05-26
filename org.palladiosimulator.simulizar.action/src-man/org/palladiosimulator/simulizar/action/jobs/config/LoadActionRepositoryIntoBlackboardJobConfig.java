package org.palladiosimulator.simulizar.action.jobs.config;

import org.palladiosimulator.simulizar.action.jobs.LoadActionRepositoryIntoBlackBoardJob;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;

/**
 * This class is the configuration for the {@link LoadActionRepositoryIntoBlackBoardJob}.
 * @see LoadActionRepositoryIntoBlackboardJobConfigBuilder
 * @author Christian Stier, Florian Rosenthal
 *
 */
public class LoadActionRepositoryIntoBlackboardJobConfig extends AbstractExtensionJobConfiguration {

    
    private String actionRepositoryPath;
    
    public static String DEFAULT_ACTION_REPOSITORY_PATH = "platform:/resource/org.palladiosimulator.simulizar.action.repository/model/repository.actions";
    
    /**
     * Initializes a new instance of the {@link LoadActionRepositoryIntoBlackboardJobConfig} class
     * with the given parameter.
     * @param actionRepositoryPath A string containing the path to the Action model to load. 
     */
    public LoadActionRepositoryIntoBlackboardJobConfig(String actionRepositoryPath) {
        if (actionRepositoryPath == null) {
            throw new IllegalArgumentException("Given path must not be null.");
        }
        this.actionRepositoryPath = actionRepositoryPath;
    }
    
    /**
     * Gets the path to the Action model to load.
     * @return A string containing the path to the Action model to load.
     */
    public String getActionRepositoryPath() {
        return this.actionRepositoryPath;
    }
    
    
    @Override
    public String getErrorMessage() {
       return "Invalid Action Configuration!";
    }

    /**
     * {@inheritDoc}<br>
     * In this case, the path to the power infrastructure repository model is set to the out-of-the-box
     * supported Action Repository path.
     */
    @Override
    public void setDefaults() {
        this.actionRepositoryPath = DEFAULT_ACTION_REPOSITORY_PATH;
    }
    
}

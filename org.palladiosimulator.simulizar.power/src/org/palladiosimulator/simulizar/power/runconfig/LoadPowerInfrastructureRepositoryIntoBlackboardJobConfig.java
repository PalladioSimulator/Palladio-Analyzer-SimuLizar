package org.palladiosimulator.simulizar.power.runconfig;

import org.palladiosimulator.simulizar.power.jobs.LoadPowerInfrastructureRepositoryIntoBlackboardJob;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;

/**
 * This class is the configuration for the {@link LoadPowerInfrastructureRepositoryIntoBlackboardJob}.
 * @see LoadPowerInfrastructureRepositoryIntoBlackboardJobConfigBuilder
 * @author Florian Rosenthal
 *
 */
public class LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig extends AbstractExtensionJobConfiguration {

    private String infrastructureRepositoryPath;
    
    /**
     * Initializes a new instance of the {@link LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig} class
     * with the given parameter.
     * @param infrastructureRepositoryPath A string containing the path to the power infrastructure repository model to load. 
     */
    public LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig(String infrastructureRepositoryPath) {
        if (infrastructureRepositoryPath == null) {
            throw new IllegalArgumentException("Given path must not be null.");
        }
        this.infrastructureRepositoryPath = infrastructureRepositoryPath;
    }
    
    /**
     * Gets the path to the power infrastructure repository model to load.
     * @return A string containing the path to the power infrastructure repository model to load.
     */
    public String getInfrastructureRepositoryPath() {
        return this.infrastructureRepositoryPath;
    }
    
    @Override
    public String getErrorMessage() {
       return "Invalid Power Infrastructure Configuration!";
    }

    /**
     * {@inheritDoc}<br>
     * In this case, the path to the power infrastructure repository model is set to the empty string.
     */
    @Override
    public void setDefaults() {
        this.infrastructureRepositoryPath = "";

    }

}

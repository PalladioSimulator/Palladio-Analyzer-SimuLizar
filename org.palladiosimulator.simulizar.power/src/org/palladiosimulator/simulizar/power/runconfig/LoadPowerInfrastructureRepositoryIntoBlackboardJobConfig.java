package org.palladiosimulator.simulizar.power.runconfig;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;

public class LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig extends AbstractExtensionJobConfiguration {

    public static final String INFRASTRUCTURE_MODEL_FILE = "infrastructureModelFile";
    
    private String infrastructureRepositoryPath;
    
    public LoadPowerInfrastructureRepositoryIntoBlackboardJobConfig(String infrastructureRepositoryPath) {
        if (infrastructureRepositoryPath == null) {
            throw new IllegalArgumentException("Given path must not be null.");
        }
        this.infrastructureRepositoryPath = infrastructureRepositoryPath;
    }
    
    public String getInfrastructureRepositoryPath() {
        return this.infrastructureRepositoryPath;
    }
    
    @Override
    public String getErrorMessage() {
       return "";
    }

    @Override
    public void setDefaults() {
        this.infrastructureRepositoryPath = "";

    }

}

package org.palladiosimulator.simulizar.runconfig;

import java.util.Map;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;

public class SimuLizarWorkflowConfiguration extends SimuComWorkflowConfiguration {

    protected String monitorRepositoryFile;
    protected String reconfigurationRulesFolder;
    protected String usageEvolutionFile;
    protected String serviceLevelObjectivesFile;

    public SimuLizarWorkflowConfiguration(Map<String, Object> attributes) {
        super(attributes);
    }

    public String getMonitorRepositoryFile() {
        return monitorRepositoryFile;
    }

    public void setMonitorRepositoryFile(String monitorRepositoryFile) {
        this.monitorRepositoryFile = monitorRepositoryFile;
    }

    public String getReconfigurationRulesFolder() {
        return reconfigurationRulesFolder;
    }

    public void setReconfigurationRulesFolder(String reconfigurationRulesFolder) {
        this.reconfigurationRulesFolder = reconfigurationRulesFolder;
    }

    public String getUsageEvolutionFile() {
        return usageEvolutionFile;
    }

    public void setUsageEvolutionFile(String usageEvolutionFile) {
        this.usageEvolutionFile = usageEvolutionFile;
    }

    public String getServiceLevelObjectivesFile() {
        return serviceLevelObjectivesFile;
    }

    public void setServiceLevelObjectivesFile(String serviceLevelObjectivesFile) {
        this.serviceLevelObjectivesFile = serviceLevelObjectivesFile;
    }

    @Override
    public void setDefaults() {
        throw new RuntimeException("Not implemented. No defaults defined.");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SimuLizarWorkflowConfiguration config = (SimuLizarWorkflowConfiguration) super.clone();
        config.monitorRepositoryFile = this.monitorRepositoryFile;
        config.reconfigurationRulesFolder = this.reconfigurationRulesFolder;
        config.usageEvolutionFile = this.usageEvolutionFile;
        return config;
    }

}

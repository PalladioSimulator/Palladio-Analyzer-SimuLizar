package org.palladiosimulator.simulizar.runconfig;

import java.util.Map;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;

public class SimuLizarWorkflowConfiguration extends SimuComWorkflowConfiguration {

    /** Logger for this class. */
    private static final Logger LOGGER = Logger.getLogger(SimuLizarWorkflowConfiguration.class);

    protected String monitorRepositoryFile;
    protected String reconfigurationRulesFolder;
    protected String usageEvolutionFile;
    protected String serviceLevelObjectivesFile;

    public SimuLizarWorkflowConfiguration(final Map<String, Object> attributes) {
        super(attributes);
    }

    public String getMonitorRepositoryFile() {
        return this.monitorRepositoryFile;
    }

    public void setMonitorRepositoryFile(final String monitorRepositoryFile) {
        this.monitorRepositoryFile = monitorRepositoryFile;
    }

    public String getReconfigurationRulesFolder() {
        return this.reconfigurationRulesFolder;
    }

    public void setReconfigurationRulesFolder(final String reconfigurationRulesFolder) {
        this.reconfigurationRulesFolder = reconfigurationRulesFolder;
    }

    public String getUsageEvolutionFile() {
        return this.usageEvolutionFile;
    }

    public void setUsageEvolutionFile(final String usageEvolutionFile) {
        this.usageEvolutionFile = usageEvolutionFile;
    }

    public String getServiceLevelObjectivesFile() {
        return this.serviceLevelObjectivesFile;
    }

    public void setServiceLevelObjectivesFile(final String serviceLevelObjectivesFile) {
        this.serviceLevelObjectivesFile = serviceLevelObjectivesFile;
    }

    @Override
    public void setDefaults() {
        throw new RuntimeException("Not implemented. No defaults defined.");
    }

    @Override
    protected SimuLizarWorkflowConfiguration clone() throws CloneNotSupportedException {
        final SimuLizarWorkflowConfiguration config = (SimuLizarWorkflowConfiguration) super.clone();
        config.monitorRepositoryFile = this.monitorRepositoryFile;
        config.reconfigurationRulesFolder = this.reconfigurationRulesFolder;
        config.usageEvolutionFile = this.usageEvolutionFile;
        return config;
    }

    @Override
    public SimuLizarWorkflowConfiguration getClone() {
        SimuLizarWorkflowConfiguration config;
        try {
            config = this.clone();
        } catch (final CloneNotSupportedException e) {
            LOGGER.fatal("Could not clone configuration.", e);
            config = null;
        }
        return config;
    }

    public String getTemporaryDataLocation() {
        return this.temporaryDataLocation;
    }
}

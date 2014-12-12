package org.palladiosimulator.simulizar.runconfig;

import java.util.Map;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;

public class SimuLizarWorkflowConfiguration extends SimuComWorkflowConfiguration {

    protected String pmsFile;
    protected String reconfigurationRulesFolder;

    public SimuLizarWorkflowConfiguration(Map<String, Object> attributes) {
        super(attributes);
    }

    public String getPmsFile() {
        return pmsFile;
    }

    public void setPmsFile(String pmsFile) {
        this.pmsFile = pmsFile;
    }

    public String getReconfigurationRulesFolder() {
        return reconfigurationRulesFolder;
    }

    public void setReconfigurationRulesFolder(String reconfigurationRulesFolder) {
        this.reconfigurationRulesFolder = reconfigurationRulesFolder;
    }

    @Override
    public void setDefaults() {
        throw new RuntimeException("Not implemented. No defaults defined.");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SimuLizarWorkflowConfiguration config = (SimuLizarWorkflowConfiguration) super.clone();
        config.pmsFile = this.pmsFile;
        config.reconfigurationRulesFolder = this.reconfigurationRulesFolder;
        return config;
    }

}

package org.palladiosimulator.simulizar.test.commons.di.overrides;

import java.util.Set;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Provider;

import org.palladiosimulator.simulizar.launcher.jobs.PartitionContribution;
import org.palladiosimulator.simulizar.launcher.jobs.SimuLizarPrepareBlackboardJob;
import org.palladiosimulator.simulizar.test.commons.di.components.TestSimuLizarRootComponent.TestConfigurationModule;

public class TestSimuLizarPrepareBlackboardJob extends SimuLizarPrepareBlackboardJob {

    @Inject
    public TestSimuLizarPrepareBlackboardJob(Provider<Set<PartitionContribution>> partitionContributionExtensions,
            @Named(TestConfigurationModule.ACTIVATE_MODEL_LOADING) boolean activateLoading) {
        super(partitionContributionExtensions);
        
        if (activateLoading) {
            super.addStandardJobs();
        }
    }
    
    @Override
    protected void addStandardJobs() {
        // Intentionally left black, as default model partition is potentially initialized by
        // testing framework
    }

}

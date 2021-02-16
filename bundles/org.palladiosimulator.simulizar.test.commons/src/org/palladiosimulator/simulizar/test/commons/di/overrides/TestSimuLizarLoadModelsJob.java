package org.palladiosimulator.simulizar.test.commons.di.overrides;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import org.palladiosimulator.simulizar.launcher.jobs.ModelContribution;
import org.palladiosimulator.simulizar.launcher.jobs.LoadSimuLizarModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.test.commons.di.components.TestSimuLizarRootComponent.TestConfigurationModule;

public class TestSimuLizarLoadModelsJob extends LoadSimuLizarModelsIntoBlackboardJob {

    @Inject
    public TestSimuLizarLoadModelsJob(SimuLizarWorkflowConfiguration configuration,
            Provider<Set<ModelContribution>> modelContributionExtensions,
            @Named(TestConfigurationModule.ACTIVATE_MODEL_LOADING) boolean activateLoading) {
        super(configuration, modelContributionExtensions);

        if (activateLoading) {
            super.addStandardJobs(configuration);
        }
    }

    @Override
    protected void addStandardJobs(SimuLizarWorkflowConfiguration configuration) {
        // Intentionally left black, as default model partition is potentially initialized by
        // testing framework
    }

}

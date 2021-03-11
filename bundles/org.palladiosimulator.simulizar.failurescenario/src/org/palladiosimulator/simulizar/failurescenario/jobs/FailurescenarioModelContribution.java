package org.palladiosimulator.simulizar.failurescenario.jobs;

import javax.inject.Inject;

import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.failurescenario.jobs.config.LoadFailurescenarioExtensionIntoBlackboardJobConfig;
import org.palladiosimulator.simulizar.launcher.jobs.ModelContribution;

public class FailurescenarioModelContribution implements ModelContribution {

    
    private final LoadFailurescenarioExtensionIntoBlackboardJobConfig config;

    @Inject
    public FailurescenarioModelContribution(LoadFailurescenarioExtensionIntoBlackboardJobConfig config) {
        this.config = config;
    }
	
	@Override
	public void loadModel(Facade delegate) {
		delegate.loadModel(URI.createURI(config.getFailureModelPath()));
	}

}

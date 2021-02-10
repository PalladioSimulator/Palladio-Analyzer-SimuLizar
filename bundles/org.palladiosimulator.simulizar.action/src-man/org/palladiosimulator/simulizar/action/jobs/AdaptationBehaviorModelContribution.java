package org.palladiosimulator.simulizar.action.jobs;

import javax.inject.Inject;

import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.jobs.config.LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig;
import org.palladiosimulator.simulizar.launcher.jobs.ModelContribution;

public class AdaptationBehaviorModelContribution implements ModelContribution {

    /**
     * Constant which holds the id of the {@link AdaptationBehaviorRepository} within the
     * blackboard, as defined in the plugin.xml (in the corresponding extension section).
     */
    public static final String ADAPTATION_BEHAVIOR__REPOSITORY_MODEL_PARTITION_ID = "org.palladiosimulator.simulizar.action";

    private final LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig config;

    @Inject
    public AdaptationBehaviorModelContribution(LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig config) {
        this.config = config;
    }

    @Override
    public void loadModel(Facade delegate) {
        delegate.loadModel(URI.createURI(config.getAdaptationBehaviorRepositoryPath()),
                ADAPTATION_BEHAVIOR__REPOSITORY_MODEL_PARTITION_ID);

    }

}

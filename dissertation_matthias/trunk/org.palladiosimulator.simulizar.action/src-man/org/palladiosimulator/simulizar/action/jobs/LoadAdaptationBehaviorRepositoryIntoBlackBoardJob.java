package org.palladiosimulator.simulizar.action.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.jobs.config.LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig;
import org.palladiosimulator.simulizar.action.jobs.config.LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfigBuilder;
import org.palladiosimulator.simulizar.action.partitions.AdaptationBehaviorRepositoryResourceSetPartition;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * This class is an {@link AbstractWorkflowExtensionJob} implementation to load a
 * {@link AdaptationBehaviorRepository} model (which is specified by an URI) into an
 * {@link MDSDBlackboard} instance.
 * 
 * @see LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig
 * @see LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfigBuilder
 * @author Christian Stier, Florian Rosenthal
 *
 */
public class LoadAdaptationBehaviorRepositoryIntoBlackBoardJob extends AbstractWorkflowExtensionJob<MDSDBlackboard> {
    /**
     * Constant which holds the id of the {@link AdaptationBehaviorRepository} within the
     * blackboard, as defined in the plugin.xml (in the corresponding extension section).
     */
    private static final String ADAPTATION_BEHAVIOR__REPOSITORY_MODEL_PARTITION_ID = "org.palladiosimulator.simulizar.action";

    private String path;

    /**
     * Initializes a new instance of the {@link LoadAdaptationBehaviorRepositoryIntoBlackBoardJob}
     * class.
     */
    public LoadAdaptationBehaviorRepositoryIntoBlackBoardJob() {

    }

    /**
     * {@inheritDoc}<br>
     * In this case, the passed configuration must be an
     * {@link LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig} which contains the path of
     * the {@link AdaptationBehaviorRepository} that will be loaded by this job.
     * 
     * @throws IllegalArgumentException
     *             In case the given configuration is not a
     *             {@code LoadActionRepositoryIntoBlackboardJobConfig}.
     */
    @Override
    public void setJobConfiguration(AbstractExtensionJobConfiguration configuration) throws IllegalArgumentException {
        if (!(configuration instanceof LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig)) {
            throw new IllegalArgumentException(
                    "Given configuration must be of type " + "'LoadActionRepositoryIntoBlackboardJobConfig'.");
        }
        this.path = ((LoadAdaptationBehaviorRepositoryIntoBlackboardJobConfig) configuration)
                .getAdaptationBehaviorRepositoryPath();
        super.setJobConfiguration(configuration);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IllegalArgumentException
     *             In case the given blackboard is {@code null}.
     */
    @Override
    public void setBlackboard(MDSDBlackboard blackboard) throws IllegalArgumentException {
        if (blackboard == null) {
            throw new IllegalArgumentException("Blackboard to set must not be null.");
        }
        super.setBlackboard(blackboard);
    }

    @Override
    public MDSDBlackboard getBlackboard() {
        return this.myBlackboard;
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
    }

    /**
     * {@inheritDoc}<br>
     * In this implementation, the action repository model is loaded into the blackboard. Therefore,
     * an {@link AdaptationBehaviorRepositoryResourceSetPartition} is created and added to the
     * blackboard.
     * 
     * @see LoadAdaptationBehaviorRepositoryIntoBlackBoardJob#ADAPTATION_BEHAVIOR__REPOSITORY_MODEL_PARTITION_ID
     */
    @Override
    public void execute(IProgressMonitor arg0) throws JobFailedException, UserCanceledException {
        if (this.myBlackboard == null) {
            throw new IllegalStateException("Blackboard has been not set beforehand!");
        }
        AdaptationBehaviorRepositoryResourceSetPartition partition = new AdaptationBehaviorRepositoryResourceSetPartition();
        if (!this.path.equals("")) {
            partition.loadModel(URI.createURI(!this.path.startsWith("platform:") ? "file:///" + this.path : this.path));

        }
        this.myBlackboard.addPartition(ADAPTATION_BEHAVIOR__REPOSITORY_MODEL_PARTITION_ID, partition);
        // now resolve all cross references from current resource to PCM
        partition.resolveAllProxies();
    }

    @Override
    public String getName() {
        return "Perform Adaptation Behavior Repository Load";
    }
}

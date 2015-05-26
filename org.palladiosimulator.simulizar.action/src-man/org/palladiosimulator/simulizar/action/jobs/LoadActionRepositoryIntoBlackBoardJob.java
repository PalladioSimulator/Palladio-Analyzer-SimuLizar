package org.palladiosimulator.simulizar.action.jobs;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.action.core.ActionRepository;
import org.palladiosimulator.simulizar.action.jobs.config.LoadActionRepositoryIntoBlackboardJobConfig;
import org.palladiosimulator.simulizar.action.jobs.config.LoadActionRepositoryIntoBlackboardJobConfigBuilder;
import org.palladiosimulator.simulizar.action.partitions.ActionRepositoryResourceSetPartition;

import de.uka.ipd.sdq.workflow.extension.AbstractExtensionJobConfiguration;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * This class is an {@link AbstractWorkflowExtensionJob} implementation to load a 
 * {@link ActionRepository} model (which is specified by an URI) into an {@link MDSDBlackboard} instance.
 * @see LoadActionRepositoryIntoBlackboardJobConfig
 * @see LoadActionRepositoryIntoBlackboardJobConfigBuilder
 * @author Christian Stier, Florian Rosenthal
 *
 */
public class LoadActionRepositoryIntoBlackBoardJob extends AbstractWorkflowExtensionJob<MDSDBlackboard> {
    /**
     * Constant which holds the id of the {@link ActionRepository} within the blackboard, 
     * as defined in the plugin.xml (in the corresponding extension section).
     */
    private static final String ACTION_REPOSITORY_MODEL_PARTITION_ID = "org.palladiosimulator.simulizar.action";
    
    private String path;
    
    /**
    * Initializes a new instance of the {@link LoadActionRepositoryIntoBlackboardJob} class.
    */
    public LoadActionRepositoryIntoBlackBoardJob() {
        
    }
    
    /**
     * {@inheritDoc}<br>
     * In this case, the passed configuration must be an {@link LoadActionRepositoryIntoBlackboardJobConfig}
     * which contains the path of the {@link ActionRepository} that will be loaded by this job.
     * @throws IllegalArgumentException In case the given configuration is not a 
     * {@code LoadActionRepositoryIntoBlackboardJobConfig}.
     */
    @Override
    public void setJobConfiguration(AbstractExtensionJobConfiguration configuration) throws IllegalArgumentException {
        if (!(configuration instanceof LoadActionRepositoryIntoBlackboardJobConfig)) {
            throw new IllegalArgumentException("Given configuration must be of type "
                    + "'LoadActionRepositoryIntoBlackboardJobConfig'.");
        }
        this.path = ((LoadActionRepositoryIntoBlackboardJobConfig) configuration).getActionRepositoryPath();
        super.setJobConfiguration(configuration);
    }
    
    /**
     * {@inheritDoc}
     * @throws IllegalArgumentException In case the given blackboard is {@code null}.
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
     * In this implementation, the action repository model is loaded into the blackboard.
     * Therefore, an {@link ActionRepositoryResourceSetPartition} is created and added to the blackboard.
     * @see LoadActionRepositoryIntoBlackBoardJob#ACTION_REPOSITORY_MODEL_PARTITION_ID
     */
    @Override
    public void execute(IProgressMonitor arg0) throws JobFailedException, UserCanceledException {
        if (this.myBlackboard == null) {
            throw new IllegalStateException("Blackboard has been not set beforehand!");
        }
        ActionRepositoryResourceSetPartition partition = new ActionRepositoryResourceSetPartition();
        if (!this.path.equals("")) {
            partition.loadModel(URI.createURI(!this.path.startsWith("platform:") ? "file:///" + this.path : this.path));

        }
        this.myBlackboard.addPartition(ACTION_REPOSITORY_MODEL_PARTITION_ID, partition);
        // now resolve all cross references from current resource to PCM
        partition.resolveAllProxies();
    }

    @Override
    public String getName() {
        return "Perform Action Repository Load";
    }
}

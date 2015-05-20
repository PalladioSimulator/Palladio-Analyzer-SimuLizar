package org.palladiosimulator.simulizar.reconfiguration.storydiagrams.exploration.jobs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.simulizar.reconfiguration.storydiagrams.exploration.SDMReconfigurationSpaceExplorer;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class SDMReconfigurationSpaceExplorationJob implements IBlackboardInteractingJob<MDSDBlackboard> {

    private static final Logger LOGGER = Logger.getLogger(SDMReconfigurationSpaceExplorationJob.class.getName());

    // private final SDMReconfigurationSpaceExplorer explorer;

    private final SimuLizarWorkflowConfiguration configuration;

    private MDSDBlackboard blackboard;

    public SDMReconfigurationSpaceExplorationJob(final SimuLizarWorkflowConfiguration configuration) {
        super();
        this.configuration = configuration;
    }

    @Override
    public void cleanup(IProgressMonitor arg0) throws CleanupFailedException {
        // TODO Auto-generated method stub

    }

    @Override
    public void execute(IProgressMonitor arg0) throws JobFailedException, UserCanceledException {
        try {
            SDMReconfigurationSpaceExplorer explorer = new SDMReconfigurationSpaceExplorer(this.configuration,
                    this.blackboard);
            explorer.computeReconfigurationSpace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setBlackboard(MDSDBlackboard blackboard) {
        this.blackboard = blackboard;
    }

}

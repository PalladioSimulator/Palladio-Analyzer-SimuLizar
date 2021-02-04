package org.palladiosimulator.simulizar.launcher.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class ResolveModelPartitionsJob extends AbstractBlackboardInteractingJob<MDSDBlackboard> {

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        myBlackboard.getPartitionIds().forEach(id -> {
            EcoreUtil.resolveAll(myBlackboard.getPartition(id).getResourceSet());
        });
    }

    @Override
    public void cleanup(IProgressMonitor monitor) throws CleanupFailedException {
        // Nothing to do here
    }

    @Override
    public String getName() {
        return "Resolve Partitions on Blackboard";
    }

}

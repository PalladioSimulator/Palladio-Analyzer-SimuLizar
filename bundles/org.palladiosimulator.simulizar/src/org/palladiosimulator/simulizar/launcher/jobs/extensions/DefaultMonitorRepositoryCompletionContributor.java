package org.palladiosimulator.simulizar.launcher.jobs.extensions;

import javax.inject.Inject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.analyzer.workflow.core.ConstantsContainer;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.simulizar.launcher.jobs.ModelCompletionJobContributor;

import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class DefaultMonitorRepositoryCompletionContributor extends AbstractBlackboardInteractingJob<MDSDBlackboard>
        implements ModelCompletionJobContributor, Comparable<IJob> {
    
    private final MDSDBlackboard blackboard;

    @Inject
    public DefaultMonitorRepositoryCompletionContributor(MDSDBlackboard blackboard) {
        this.blackboard = blackboard;
    }
    
    @Override
    public void contribute(Facade delegate) {
        // If there is not a monitor repository already, contribute this job
        if(blackboard.hasPartition(ConstantsContainer.DEFAULT_PCM_INSTANCE_PARTITION_ID)
                && blackboard.getPartition(ConstantsContainer.DEFAULT_PCM_INSTANCE_PARTITION_ID).getElement(
                        MonitorRepositoryPackage.Literals.MONITOR_REPOSITORY).isEmpty()) {
            delegate.contribute(this);
        }
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        var resSet = blackboard.getPartition(ConstantsContainer.DEFAULT_PCM_INSTANCE_PARTITION_ID).getResourceSet();

        var measuringPointRepo = DefaultMeasuringPointRepositoryFactory.createDefaultRepository(resSet);
        resSet.createResource(URI.createURI("model-gen/generated.measuringpoint")).getContents().add(measuringPointRepo);
        resSet.createResource(URI.createURI("model-gen/generated.monitorrepository"))
            .getContents().add(DefaultMonitorRepositoryFactory.createDefaultMonitorRepository(measuringPointRepo));
    }


    @Override
    public void cleanup(IProgressMonitor monitor) throws CleanupFailedException {
        // Nothing to clean up
    }

    @Override
    public String getName() {
        return "Default Monitor Repository Completion";
    }

    @Override
    public int compareTo(IJob o) {
        // This job should run last, to also take completed components into account
        return 1;
    }

    

}

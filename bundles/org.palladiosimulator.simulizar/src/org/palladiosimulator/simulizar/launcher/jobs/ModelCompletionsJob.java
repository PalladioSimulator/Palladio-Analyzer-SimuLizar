package org.palladiosimulator.simulizar.launcher.jobs;

import java.util.Comparator;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;

import org.eclipse.core.runtime.IProgressMonitor;

import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * This job executes all model completion contributions in order.
 * 
 * @author Sebastian Krach
 *
 */
public class ModelCompletionsJob extends SequentialBlackboardInteractingJob<MDSDBlackboard>
        implements IBlackboardInteractingJob<MDSDBlackboard>, ModelCompletionJobContributor.Facade,
        Comparator<IJob> {

    private final Provider<Set<ModelCompletionJobContributor>> modelCompletionJobs;

    /**
     * Constructor
     *
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    @Inject
    public ModelCompletionsJob(Provider<Set<ModelCompletionJobContributor>> modelCompletionJobs) {
        super(false);
        this.modelCompletionJobs = modelCompletionJobs;
    }
    
    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        modelCompletionJobs.get().forEach(contributor -> contributor.contribute(this));
        this.myJobs.sort(this);
        super.execute(monitor);
    }

    @Override
    public void contribute(IBlackboardInteractingJob<MDSDBlackboard> contribution) {
        this.add(contribution);
    }

    @SuppressWarnings("unchecked")
    @Override
    public int compare(IJob o1, IJob o2) {
        int o1Result = 0;
        int o2Result = 0;
        if (o1 instanceof Comparable) {
            o1Result = ((Comparable<IJob>)o1).compareTo(o2);
        }
        if (o2 instanceof Comparable) {
            o2Result = (-1) * ((Comparable<IJob>)o2).compareTo(o1);
        }
        if (o1Result != 0 && o2Result != 0 && o1Result != o2Result) {
            throw new IllegalStateException(
                    String.format("Both jobs <%s> and <%s> request contradictory ordering.", o1.getName(), o2.getName()));
        }
        return o1Result != 0 ? o1Result : o2Result;
    }
    
    @Override
    public String getName() {
        return "Model Completions Composite Job";
    }

}

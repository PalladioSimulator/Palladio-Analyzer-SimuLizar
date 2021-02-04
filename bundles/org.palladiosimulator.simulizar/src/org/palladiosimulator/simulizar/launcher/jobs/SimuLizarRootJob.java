package org.palladiosimulator.simulizar.launcher.jobs;

import javax.inject.Inject;
import javax.inject.Provider;

import org.palladiosimulator.simulizar.di.component.interfaces.AnalysisRuntimeComponent;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.BlackboardAwareJobProxy;
import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Composite job loading pcm and Monitor Repository model, as well as all sdm models and usage
 * evolution model and starting pcm interpretation.
 *
 * @author Joachim Meyer
 *
 */
public class SimuLizarRootJob extends SequentialBlackboardInteractingJob<MDSDBlackboard>
        implements IBlackboardInteractingJob<MDSDBlackboard> {

    /**
     * Constructor
     *
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    @Inject
    public SimuLizarRootJob(final SimuLizarWorkflowConfiguration configuration,
            MDSDBlackboard blackboard,
            Provider<LoadSimuLizarModelsIntoBlackboardJob> modelLoadJob,
            Provider<ModelCompletionsJob> modelCompletionsJob,
            AnalysisRuntimeComponent.Factory runtimeComponentFactory) {
        super(false);
        setBlackboard(blackboard);
        
        this.addJob(new BlackboardAwareJobProxy<>("Load models into blackboard", modelLoadJob::get));
        this.addJob(new ResolveModelPartitionsJob());
        this.addJob(new BlackboardAwareJobProxy<>("Run registered model completions", modelCompletionsJob::get));
        this.addJob(new BlackboardAwareJobProxy<>("Run simulizar runtime", () -> runtimeComponentFactory.create().runtimeJob()));
        

        if (configuration.getServiceLevelObjectivesFile() != null
                && !(configuration.getServiceLevelObjectivesFile().isBlank())) {
            addEvaluateResultsJob(configuration);
        }
    }

    protected void addEvaluateResultsJob(final SimuLizarWorkflowConfiguration configuration) {
        this.addJob(new EvaluateResultsJob(configuration));
    }
    
    

}

package org.palladiosimulator.simulizar.launcher.jobs;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.component.core.AnalysisRuntimeComponent;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

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
public class PCMInterpreterRootCompositeJob extends SequentialBlackboardInteractingJob<MDSDBlackboard>
        implements IBlackboardInteractingJob<MDSDBlackboard> {

    /**
     * Constructor
     *
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    @Inject
    public PCMInterpreterRootCompositeJob(final SimuLizarWorkflowConfiguration configuration,
            MDSDBlackboard blackboard,
            LoadSimuLizarModelsIntoBlackboardJob modelLoadJob,
            AnalysisRuntimeComponent.Factory runtimeComponentFactory) {
        super(false);
        setBlackboard(blackboard);
        
        this.addJob(modelLoadJob);        
        this.addJob(runtimeComponentFactory.create().runtimeJob());

        if (configuration.getServiceLevelObjectivesFile() != null
                && !(configuration.getServiceLevelObjectivesFile().isBlank())) {
            addEvaluateResultsJob(configuration);
        }
    }

    protected void addLoadLinkingResourceSimulationModelsJob(SimuLizarWorkflowConfiguration configuration) {
        
    }

    protected void addEvaluateResultsJob(final SimuLizarWorkflowConfiguration configuration) {
        this.addJob(new EvaluateResultsJob(configuration));
    }
    
    

}

package org.palladiosimulator.simulizar.launcher.jobs;

import org.palladiosimulator.analyzer.workflow.ConstantsContainer;
import org.palladiosimulator.analyzer.workflow.jobs.CreateBlackboardPartitionJob;
import org.palladiosimulator.analyzer.workflow.jobs.LoadModelIntoBlackboardJob;
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
    public PCMInterpreterRootCompositeJob(final SimuLizarWorkflowConfiguration configuration) {
        super(false);

        // Always begin with an empty Blackboard;
        this.setBlackboard(new MDSDBlackboard());

        addLoadSimuLizarModelsJob(configuration);
        
        if (configuration.getSimulateLinkingResources()) {
            addLoadLinkingResourceSimulationModelsJob(configuration);
            this.add(new SimuLizarApplyConnectorCompletionsJob(configuration));
        }

        addRunInterpretationJob(configuration);

        if (configuration.getServiceLevelObjectivesFile() != null
                && !(configuration.getServiceLevelObjectivesFile().isBlank())) {
            addEvaluateResultsJob(configuration);
        }
    }

    protected void addLoadLinkingResourceSimulationModelsJob(SimuLizarWorkflowConfiguration configuration) {
        this.add(new CreateBlackboardPartitionJob(ConstantsContainer.RMI_MIDDLEWARE_REPOSITORY_PARTITION_ID));
        LoadModelIntoBlackboardJob.parseUriAndAddModelLoadJob(configuration.getRMIMiddlewareFile(),
                ConstantsContainer.RMI_MIDDLEWARE_REPOSITORY_PARTITION_ID, this);
    }

    protected void addLoadSimuLizarModelsJob(final SimuLizarWorkflowConfiguration configuration) {
        this.add(new LoadSimuLizarModelsIntoBlackboardJob(configuration));
    }
    
    protected void addRunInterpretationJob(final SimuLizarWorkflowConfiguration configuration) {
        this.addJob(new PCMStartInterpretationJob(configuration));
    }
    
    protected void addEvaluateResultsJob(final SimuLizarWorkflowConfiguration configuration) {
        this.addJob(new EvaluateResultsJob(configuration));
    }
    
    

}

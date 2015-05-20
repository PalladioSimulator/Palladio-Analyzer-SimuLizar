package org.palladiosimulator.simulizar.reconfiguration.storydiagrams.exploration.jobs;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.launcher.jobs.LoadSimuLizarModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.reconfiguration.storydiagram.jobs.LoadSDMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.IBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Composite job loading pcm and Monitor Repository model, as well as all sdm models and usage
 * evolution model and starting pcm interpretation.
 * 
 * @author Matthias Becker
 * 
 */
public class SimuLizarReconfigurationExplorationCompositeJob extends SequentialBlackboardInteractingJob<MDSDBlackboard>
        implements IBlackboardInteractingJob<MDSDBlackboard> {

    private static final Logger LOGGER = Logger.getLogger(SimuLizarReconfigurationExplorationCompositeJob.class
            .getName());

    /**
     * Constructor
     * 
     * @param configuration
     *            SimuLizar/PCM workflow configuration.
     */
    public SimuLizarReconfigurationExplorationCompositeJob(final SimuLizarWorkflowConfiguration configuration) {
        super(false);

        this.addJob(new LoadSimuLizarModelsIntoBlackboardJob(configuration, false));

        this.addJob(new LoadSDMModelsIntoBlackboardJob(configuration));

        this.addJob(new SDMReconfigurationSpaceExplorationJob(configuration));

    }

}

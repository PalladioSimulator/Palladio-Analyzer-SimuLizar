package org.palladiosimulator.simulizar.reconfiguration.storydiagram.modelaccess;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.storydiagram.jobs.LoadSDMModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.scaledl.usageevolution.UsageEvolution;
import org.storydriven.storydiagrams.activities.Activity;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * Helper to access all models and specifically the StoryDiagram models from the Story Diagram
 * reconfiguration engine.
 * 
 * @author Matthias Becker
 *
 */
public class StoryDiagramModelAccess implements IModelAccess {

    private static final Logger LOGGER = Logger.getLogger(StoryDiagramModelAccess.class);

    private final SDMResourceSetPartition sdmPartition;
    private final IModelAccess modelAccess;

    public StoryDiagramModelAccess(IModelAccess modelAccess, final SimuLizarWorkflowConfiguration configuration) {
        this.modelAccess = modelAccess;
        IProgressMonitor monitor = new NullProgressMonitor();
        try {
            LoadSDMModelsIntoBlackboardJob loadSDMModelsJob = new LoadSDMModelsIntoBlackboardJob(configuration);
            loadSDMModelsJob.setBlackboard(this.modelAccess.getBlackboard());
            loadSDMModelsJob.execute(monitor);
        } catch (JobFailedException e) {
            LOGGER.error("Failed loading StoryDiagram reconfiguration rules into Blackboard.", e);
        } catch (UserCanceledException e) {
            LOGGER.error("Loading StoryDiagram reconfiguration rules into Blackboard was aborted.", e);
        }
        this.sdmPartition = getResourceSetPartition(modelAccess.getBlackboard(),
                LoadSDMModelsIntoBlackboardJob.SDM_MODEL_PARTITION_ID);
    }

    /**
     * 
     * @return a list of the sdm models.
     */
    public List<Activity> getStoryDiagrams() {
        if (!(sdmPartition == null)) {
            return sdmPartition.getActivities();
        } else {
            return new LinkedList<Activity>();
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends ResourceSetPartition> T getResourceSetPartition(final MDSDBlackboard blackboard, final String id) {
        return (T) blackboard.getPartition(id);
    }

    /**
     * Checks whether sdm models exists, without using any classes from sd interpreter.
     * 
     * @return true if yes, otherwise false;
     */
    public boolean sdmModelsExists() {
        return sdmPartition.getResourceSet().getResources().size() > 0;
    }

    @Override
    public PCMResourceSetPartition getLocalPCMModel() {
        return modelAccess.getLocalPCMModel();
    }

    @Override
    public PCMResourceSetPartition getGlobalPCMModel() {
        return modelAccess.getGlobalPCMModel();
    }

    @Override
    public MonitorRepository getMonitorRepositoryModel() {
        return modelAccess.getMonitorRepositoryModel();
    }

    @Override
    public RuntimeMeasurementModel getRuntimeMeasurementModel() {
        return modelAccess.getRuntimeMeasurementModel();
    }

    @Override
    public UsageEvolution getUsageEvolutionModel() {
        return modelAccess.getUsageEvolutionModel();
    }

    @Override
    public IModelAccess clone() {
        return modelAccess.clone();
    }

    @Override
    public MDSDBlackboard getBlackboard() {
        return modelAccess.getBlackboard();
    }
}

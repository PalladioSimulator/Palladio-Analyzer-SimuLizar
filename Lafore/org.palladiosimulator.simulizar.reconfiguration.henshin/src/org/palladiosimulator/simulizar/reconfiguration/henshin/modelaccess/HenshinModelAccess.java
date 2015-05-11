package org.palladiosimulator.simulizar.reconfiguration.henshin.modelaccess;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.henshin.model.Module;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.henshin.jobs.LoadHenshinModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.scaledl.usageevolution.UsageEvolution;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;

/**
 * Helper to access all models and specifically the StoryDiagram models from the Story Diagram
 * reconfiguration engine.
 * 
 * @author Matthias Becker
 *
 */
public class HenshinModelAccess implements IModelAccess {

    private static final Logger LOGGER = Logger.getLogger(HenshinModelAccess.class);

    private final HenshinResourceSetPartition henshinPartition;
    private final IModelAccess modelAccess;

    public HenshinModelAccess(IModelAccess modelAccess, final SimuLizarWorkflowConfiguration configuration) {
        this.modelAccess = modelAccess;
        IProgressMonitor monitor = new NullProgressMonitor();
        try {
            new LoadHenshinModelsIntoBlackboardJob(configuration, modelAccess.getBlackboard()).execute(monitor);
        } catch (JobFailedException e) {
            LOGGER.error("Failed loading StoryDiagram reconfiguration rules into Blackboard.", e);
        } catch (UserCanceledException e) {
            LOGGER.error("Loading StoryDiagram reconfiguration rules into Blackboard was aborted.", e);
        }
        this.henshinPartition = getResourceSetPartition(modelAccess.getBlackboard(),
                LoadHenshinModelsIntoBlackboardJob.HENSHIN_MODEL_PARTITION_ID);
    }

    /**
     * 
     * @return a list of the henshin reconfiguration rules.
     */
    public List<Module> getHenshinRules() {
        if (!(henshinPartition == null)) {
            return henshinPartition.getModules();
        } else {
            return new LinkedList<Module>();
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
        return henshinPartition.getResourceSet().getResources().size() > 0;
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

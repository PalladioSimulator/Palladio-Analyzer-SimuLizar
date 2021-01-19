package org.palladiosimulator.simulizar.launcher.jobs;

import java.util.Set;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.simulizar.interpreter.EventNotificationHelper;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.usagemodel.SimulatedUsageModels;
import org.palladiosimulator.simulizar.usagemodel.UsageEvolverFacade;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionPackage;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.workflow.jobs.AbstractBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class InitializeInterpretationJob extends AbstractBlackboardInteractingJob<MDSDBlackboard> {
    
    private static final Logger LOGGER = Logger.getLogger(InitializeInterpretationJob.class);
    private final Reconfigurator reconfigurator;
    private final Set<IInterpreterListener> interpreterListeners;
    private final PCMPartitionManager pcmPartitionManager;
    private final EventNotificationHelper eventHelper;
    private final UsageEvolverFacade usageEvolverFacade;
    private final SimuComModel simuComModel;
    private final SimulatedUsageModels simulatedUsageModels;
    private final Set<IModelObserver> modelObservers;
    
    @Inject
    public InitializeInterpretationJob(final PCMPartitionManager pcmPartitionManager, 
            final SimuComModel simuComModel,
            final EventNotificationHelper eventHelper, 
            final SimulatedUsageModels simulatedUsageModels, 
            final Reconfigurator reconfigurator,
            final UsageEvolverFacade usageEvolverFacade, 
            final Set<IModelObserver> modelObservers,
            final Set<IInterpreterListener> interpreterListeners) {
        this.pcmPartitionManager = pcmPartitionManager;
        this.simuComModel = simuComModel;
        this.eventHelper = eventHelper;
        this.simulatedUsageModels = simulatedUsageModels;
        this.reconfigurator = reconfigurator;
        this.usageEvolverFacade = usageEvolverFacade;
        this.modelObservers = modelObservers;
        this.interpreterListeners = interpreterListeners;
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        initializeModelObservers();
        initializeWorkloadDrivers();
        initializeUsageEvolver();
        
        simuComModel.getSimulationControl().addStopCondition(monitor::isCanceled);
        
        reconfigurator.startListening();
        interpreterListeners.forEach(this.eventHelper::addObserver);
        
        pcmPartitionManager.startObservingPcmChanges();
    }
    
    private void initializeWorkloadDrivers() {
        LOGGER.debug("Initialise simucom framework's workload drivers");
        this.simuComModel.setUsageScenarios(simulatedUsageModels.createWorkloadDrivers());
    }

    private void initializeModelObservers() {
        LOGGER.debug(
                "Initialize model observers, e.g., to keep simucom framework objects in sync with global PCM model");
        modelObservers.forEach(m -> m.initialize());
    }

    private void initializeUsageEvolver() {
        UsageEvolution ueModel = pcmPartitionManager
                .findModel(UsageevolutionPackage.eINSTANCE.getUsageEvolution());
        if (ueModel != null) {
            LOGGER.debug("Start the code to evolve the usage model over time");

            usageEvolverFacade.start();
        }
    }

    @Override
    public void cleanup(IProgressMonitor monitor) throws CleanupFailedException {
        // Nothing to clean up
    }

    @Override
    public String getName() {
       return InitializeInterpretationJob.class.getName();
    }

}

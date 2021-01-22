package org.palladiosimulator.simulizar.launcher.jobs;

import java.util.Set;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.probeframework.ProbeFrameworkContext;
import org.palladiosimulator.recorderframework.config.IRecorderConfigurationFactory;
import org.palladiosimulator.simulizar.interpreter.EventDispatcher;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.reconfiguration.AbstractReconfigurationLoader;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityManager;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityObserver;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

public class SimuLizarRuntimeJob extends SequentialJob {
    
    private static final Logger LOGGER = Logger.getLogger(SimuLizarRuntimeJob.class);
    
    private final PCMPartitionManager pcmPartitionManager;
    private final EventDispatcher eventHelper;
    private final Set<IModelObserver> modelObservers;
    private final Set<IInterpreterListener> interpreterListeners;
    private final Set<AbstractReconfigurationLoader> reconfigurationLoaders;
    private final SimuLizarWorkflowConfiguration configuration;

    private final Set<RuntimeStateEntityManager> entityManagers;

    private final Set<RuntimeStateEntityObserver> entityObservers;
    
    
    @Inject
    public SimuLizarRuntimeJob(
            final SimuLizarWorkflowConfiguration configuration,
            final PCMPartitionManager pcmPartitionManager,
            final EventDispatcher eventHelper,  
            final Set<IModelObserver> modelObservers,
            final Set<IInterpreterListener> interpreterListeners,
            final RunInterpreterJob interpreterJob,
            final ProbeFrameworkContext probeFrameworkContext,
            final IRecorderConfigurationFactory recorderConfigurationFactory,
            final Set<AbstractReconfigurationLoader> reconfigurationLoaders,
            final Set<RuntimeStateEntityManager> entityManagers,
            final Set<RuntimeStateEntityObserver> entityObservers) {
        this.configuration = configuration;
        this.pcmPartitionManager = pcmPartitionManager;
        this.eventHelper = eventHelper;
        this.interpreterListeners = interpreterListeners;
        this.modelObservers = modelObservers;
        this.reconfigurationLoaders = reconfigurationLoaders;
        this.entityManagers = entityManagers;
        this.entityObservers = entityObservers;
        
        this.addJob(interpreterJob);
    }

    @Override
    public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
        pcmPartitionManager.initialize();
        
        LOGGER.debug("Load reconfigurations");
        reconfigurationLoaders.forEach(loader -> loader.load(configuration));

        LOGGER.debug("Initialize managers of internal simulation state");
        entityManagers.forEach(RuntimeStateEntityManager::initialize);
        
        LOGGER.debug("Initialize observers of internal simulation state");
        entityObservers.forEach(RuntimeStateEntityObserver::initialize);

        LOGGER.debug("Initialize model observers, e.g., to runtime state objects in sync with global PCM model");
        modelObservers.forEach(m -> m.initialize());

        LOGGER.debug("Initialize interpreter listeners.");
        interpreterListeners.forEach(this.eventHelper::addObserver);
        interpreterListeners.forEach(IInterpreterListener::initialize);

        pcmPartitionManager.startObservingPcmChanges();

        super.execute(monitor);
    }

    @Override
    public void cleanup(IProgressMonitor monitor) throws CleanupFailedException {
        LOGGER.debug("Deregister all listeners and execute cleanup code");
        eventHelper.removeAllListener();
        pcmPartitionManager.stopObservingPcmChanges();
        interpreterListeners.forEach(IInterpreterListener::cleanup);
        modelObservers.forEach(IModelObserver::unregister);
        entityManagers.forEach(RuntimeStateEntityManager::cleanup);
    }

    @Override
    public String getName() {
       return SimuLizarRuntimeJob.class.getName();
    }

}

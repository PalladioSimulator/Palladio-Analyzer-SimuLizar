package org.palladiosimulator.simulizar.runtimestate;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

import org.palladiosimulator.probeframework.ProbeFrameworkContext;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.simucomstatus.SimuComStatus;
import de.uka.ipd.sdq.simucomframework.simucomstatus.SimucomstatusFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;

public class SimuComModelFactory implements Provider<SimuComModel> {

    private SimuComConfig configuration;
    private IResourceTableManager resourceTableManager;
    private ISimEngineFactory simEngineFactory;
    private ProbeFrameworkContext probeFrameworkContext;
    
    @Inject
    SimuComModelFactory(final SimuComConfig configuration, IResourceTableManager resourceTableManager, ISimEngineFactory simEngineFactory,
            ProbeFrameworkContext probeFrameworkContext) {
        this.configuration = configuration;
        this.resourceTableManager = resourceTableManager;
        this.simEngineFactory = simEngineFactory;
        this.probeFrameworkContext = probeFrameworkContext;       
    }

    @Override
    public SimuComModel get() {
        // Status model to store the runtime state of the simulator
        final SimuComStatus simuComStatus = createSimuComStatus();

        final SimuComModel simuComModel = new SimuComModel(configuration, simuComStatus,
                simEngineFactory, false, probeFrameworkContext, resourceTableManager);

        simuComModel.getSimulationStatus().setCurrentSimulationTime(0);

        return simuComModel;
    }
    
    /**
     * Gets the SimuCom status, creates one if none exists.
     *
     * @return the SimuCom status.
     */
    private static SimuComStatus createSimuComStatus() {
        final SimuComStatus simuComStatus = SimucomstatusFactory.eINSTANCE.createSimuComStatus();

        simuComStatus.setProcessStatus(SimucomstatusFactory.eINSTANCE.createSimulatedProcesses());
        simuComStatus.setResourceStatus(SimucomstatusFactory.eINSTANCE.createSimulatedResources());

        return simuComStatus;
    }
}

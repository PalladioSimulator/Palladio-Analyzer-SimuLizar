package org.palladiosimulator.simulizar.runtimestate;

import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.simucomstatus.SimuComStatus;
import de.uka.ipd.sdq.simucomframework.simucomstatus.SimucomstatusFactory;
import de.uka.ipd.sdq.simulation.AbstractSimulationConfig;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.preferences.SimulationPreferencesHelper;

final class SimuComModelFactory {

    static SimuComModel createSimuComModel(final SimuLizarWorkflowConfiguration configuration) {
        final SimuComModel simuComModel = initialiseSimuComModel(configuration);
        return simuComModel;
    }

    private static SimuComModel initialiseSimuComModel(final SimuLizarWorkflowConfiguration configuration) {
        // Configuration options for the simulation engine
        final AbstractSimulationConfig simulationConfiguration = configuration.getSimulationConfiguration();

        // Status model to store the runtime state of the simulator
        final SimuComStatus simuComStatus = createSimuComStatus();

        // Factory used to create the simulation engine used in the simulation,
        // e.g., SSJ engine or Desmo-J engine
        final ISimEngineFactory simEngineFactory = getSimEngineFactory();

        final SimuComModel simuComModel = new SimuComModel((SimuComConfig) simulationConfiguration, simuComStatus,
                simEngineFactory, false);

        simuComModel.getSimulationStatus().setCurrentSimulationTime(0);

        return simuComModel;
    }

    private static ISimEngineFactory getSimEngineFactory() {
        // load factory for the preferred simulation engine
        final ISimEngineFactory factory = SimulationPreferencesHelper.getPreferredSimulationEngine();
        if (factory == null) {
            throw new RuntimeException("There is no simulation engine available. Install at least one engine.");
        }
        return factory;
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

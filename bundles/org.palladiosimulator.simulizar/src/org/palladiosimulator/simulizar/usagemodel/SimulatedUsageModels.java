package org.palladiosimulator.simulizar.usagemodel;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;
import org.palladiosimulator.pcm.usagemodel.Workload;
import org.palladiosimulator.pcm.usagemodel.util.UsagemodelSwitch;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import com.google.inject.Inject;

import de.uka.ipd.sdq.simucomframework.usage.ICancellableWorkloadDriver;
import de.uka.ipd.sdq.simucomframework.usage.IWorkloadDriver;

public class SimulatedUsageModels {

    private final InterpreterDefaultContext rootContext;
    private final Map<ClosedWorkload, de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload> closedWorkloads = new HashMap<ClosedWorkload, de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload>();
    private final Map<OpenWorkload, de.uka.ipd.sdq.simucomframework.usage.OpenWorkload> openWorkloads = new HashMap<OpenWorkload, de.uka.ipd.sdq.simucomframework.usage.OpenWorkload>();
    private final PCMPartitionManager pcmPartitionManager;
    private final ScenarioRunnerFactory scenarioRunnerFactory;
    private final WorkloadDriverFactory<de.uka.ipd.sdq.simucomframework.usage.OpenWorkload> openWorkloadDriverFactory;
    private final WorkloadDriverFactory<de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload> closedWorkloadDriverFactory;


    @Inject
    public SimulatedUsageModels(final InterpreterDefaultContext rootContext, final PCMPartitionManager pcmPartitionManager,
    		ScenarioRunnerFactory scenarioRunnerFactory, WorkloadDriverFactory<de.uka.ipd.sdq.simucomframework.usage.OpenWorkload> openWorkloadDriverFactory,
    		 WorkloadDriverFactory<de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload> closedWorkloadDriverFactory) {
        super();
        this.rootContext = rootContext;
        this.pcmPartitionManager = pcmPartitionManager;
		this.scenarioRunnerFactory = scenarioRunnerFactory;
		this.openWorkloadDriverFactory = openWorkloadDriverFactory;
		this.closedWorkloadDriverFactory = closedWorkloadDriverFactory;
    }

    /**
     * Gets workload drivers for the usage scenarios in the usage model
     *
     * @return a list of workload drivers
     */
    public IWorkloadDriver[] createWorkloadDrivers() {
        final EList<UsageScenario> usageScenarios = this.pcmPartitionManager.getGlobalPCMModel()
                .getUsageModel().getUsageScenario_UsageModel();
        final IWorkloadDriver[] workloads = new IWorkloadDriver[usageScenarios.size()];
        for (int i = 0; i < usageScenarios.size(); i++) {
            workloads[i] = this.createAndAddWorkloadDriver(usageScenarios.get(i));
        }
        return workloads;
    }
    
    public IWorkloadDriver createAndAddWorkloadDriver(final UsageScenario usageScenario) {
        // get workload of scenario
        final Workload workload = usageScenario.getWorkload_UsageScenario();

        // determine if workload is open or closed
        if (workload.eClass() == UsagemodelPackage.eINSTANCE.getClosedWorkload()) {
            final de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload driver = this.closedWorkloadDriverFactory.createWorkloadDriver(rootContext,
            		workload, usageScenario, scenarioRunnerFactory);
            this.closedWorkloads.put((ClosedWorkload) workload, driver);
            return driver;
        } else if (workload.eClass() == UsagemodelPackage.eINSTANCE.getOpenWorkload()) {
            final de.uka.ipd.sdq.simucomframework.usage.OpenWorkload driver = this.openWorkloadDriverFactory.createWorkloadDriver(rootContext,
            		workload, usageScenario, scenarioRunnerFactory);
            this.openWorkloads.put((OpenWorkload) workload, driver);
            return driver;
        } else {
            throw new UnsupportedOperationException("Unsupported Workload Found");
        }
    }

    public ICancellableWorkloadDriver getWorkloadDriver(final Workload workload) {
        return (new UsagemodelSwitch<ICancellableWorkloadDriver>() {
            @Override
            public ICancellableWorkloadDriver caseClosedWorkload(ClosedWorkload object) {
                return SimulatedUsageModels.this.getClosedWorkloadDriver(object);
            }
            
            @Override
            public ICancellableWorkloadDriver caseOpenWorkload(OpenWorkload object) {
                return SimulatedUsageModels.this.getOpenWorkloadDriver(object);
            }
        }).doSwitch(workload);
    }
    
    public de.uka.ipd.sdq.simucomframework.usage.OpenWorkload getOpenWorkloadDriver(final OpenWorkload openWorkload) {
        return this.openWorkloads.get(openWorkload);
    }

    public de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload getClosedWorkloadDriver(
            final ClosedWorkload closedWorkload) {
        return this.closedWorkloads.get(closedWorkload);
    }
}

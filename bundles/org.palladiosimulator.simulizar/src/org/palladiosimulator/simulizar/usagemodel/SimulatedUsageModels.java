package org.palladiosimulator.simulizar.usagemodel;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;
import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;
import org.palladiosimulator.pcm.usagemodel.Workload;
import org.palladiosimulator.pcm.usagemodel.util.UsagemodelSwitch;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.UsageScenarioSwitch;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.usage.ClosedWorkloadUserFactory;
import de.uka.ipd.sdq.simucomframework.usage.ICancellableWorkloadDriver;
import de.uka.ipd.sdq.simucomframework.usage.IClosedWorkloadUserFactory;
import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;
import de.uka.ipd.sdq.simucomframework.usage.IUserFactory;
import de.uka.ipd.sdq.simucomframework.usage.IWorkloadDriver;
import de.uka.ipd.sdq.simucomframework.usage.OpenWorkloadUserFactory;

public class SimulatedUsageModels {

    private static final Logger LOGGER = Logger.getLogger(SimulatedUsageModels.class);
    private final InterpreterDefaultContext rootContext;
    private final Map<ClosedWorkload, de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload> closedWorkloads = new HashMap<ClosedWorkload, de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload>();
    private final Map<OpenWorkload, de.uka.ipd.sdq.simucomframework.usage.OpenWorkload> openWorkloads = new HashMap<OpenWorkload, de.uka.ipd.sdq.simucomframework.usage.OpenWorkload>();

    private final IResourceTableManager resourceTableManager;
    
    public SimulatedUsageModels(final InterpreterDefaultContext rootContext, IResourceTableManager resourceTableManager) {
        super();
        this.rootContext = rootContext;
        this.resourceTableManager = resourceTableManager;
    }

    /**
     * Gets workload drivers for the usage scenarios in the usage model
     *
     * @return a list of workload drivers
     */
    public IWorkloadDriver[] createWorkloadDrivers() {
        final EList<UsageScenario> usageScenarios = this.rootContext.getPCMPartitionManager().getGlobalPCMModel()
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
            final de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload driver = this.createClosedWorkloadDriver(workload,
                    usageScenario);
            this.closedWorkloads.put((ClosedWorkload) workload, driver);
            return driver;
        } else if (workload.eClass() == UsagemodelPackage.eINSTANCE.getOpenWorkload()) {
            final de.uka.ipd.sdq.simucomframework.usage.OpenWorkload driver = this.createOpenWorkloadDriver(workload,
                    usageScenario);
            this.openWorkloads.put((OpenWorkload) workload, driver);
            return driver;
        } else {
            throw new UnsupportedOperationException("Unsupported Workload Found");
        }
    }

    private de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload createClosedWorkloadDriver(final Workload workload,
            final UsageScenario usageScenario) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Create workload driver for ClosedWorkload: " + workload);
        }
        final ClosedWorkload closedWorkload = (ClosedWorkload) workload;

        final IClosedWorkloadUserFactory userFactory = new ClosedWorkloadUserFactory(this.rootContext.getModel(),
                closedWorkload.getThinkTime_ClosedWorkload().getSpecification(), usageScenario, resourceTableManager) {

            @Override
            public IScenarioRunner createScenarioRunner() {
                return SimulatedUsageModels.this.getScenarioRunner(usageScenario, resourceTableManager);
            }
        };

        // create workload driver by using given factory
        return new de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload(userFactory, closedWorkload.getPopulation());
    }

    private de.uka.ipd.sdq.simucomframework.usage.OpenWorkload createOpenWorkloadDriver(final Workload workload,
            final UsageScenario usageScenario) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Create workload driver for OpenWorkload: " + workload);
        }
        final OpenWorkload openWorkload = (OpenWorkload) workload;

        final IUserFactory userFactory = new OpenWorkloadUserFactory(this.rootContext.getModel(), usageScenario, resourceTableManager) {

            @Override
            public IScenarioRunner createScenarioRunner() {
                return SimulatedUsageModels.this.getScenarioRunner(usageScenario, resourceTableManager);
            }
        };

        // create workload driver by using given factory
        return new de.uka.ipd.sdq.simucomframework.usage.OpenWorkload(this.rootContext.getModel(), userFactory,
                openWorkload.getInterArrivalTime_OpenWorkload().getSpecification(), resourceTableManager);
    }

    private IScenarioRunner getScenarioRunner(final UsageScenario scenario, IResourceTableManager resourceTableManager) {
        return new IScenarioRunner() {

            @Override
            public void scenarioRunner(final SimuComSimProcess thread) {
                final InterpreterDefaultContext newContext = new InterpreterDefaultContext(
                        SimulatedUsageModels.this.rootContext, thread);
                final UsageModel usageModel = newContext.getPCMPartitionManager().getLocalPCMModel().getUsageModel();
                
                // If the UsageScenario is not contained in the UsageModel (e.g. it has
                // been removed after the workload scheduled the new user, and before the 
                // user starts execution) simply exit without processing the scenario.
                usageModel.getUsageScenario_UsageModel().stream()
                	.filter(sc -> sc.getId().equals(scenario.getId()))
                	.findAny().ifPresent(sc -> {
                	    new UsageScenarioSwitch<Object>(newContext, resourceTableManager).doSwitch(sc);
                	});
            }
        };
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

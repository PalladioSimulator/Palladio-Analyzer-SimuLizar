package org.palladiosimulator.simulizar.usagemodel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.log4j.Logger;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;
import org.palladiosimulator.pcm.usagemodel.Workload;
import org.palladiosimulator.pcm.usagemodel.util.UsagemodelSwitch;
import org.palladiosimulator.simulizar.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.usage.ClosedWorkloadUserFactory;
import de.uka.ipd.sdq.simucomframework.usage.ICancellableWorkloadDriver;
import de.uka.ipd.sdq.simucomframework.usage.IClosedWorkloadUserFactory;
import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;
import de.uka.ipd.sdq.simucomframework.usage.IUserFactory;
import de.uka.ipd.sdq.simucomframework.usage.IWorkloadDriver;
import de.uka.ipd.sdq.simucomframework.usage.OpenWorkloadUserFactory;

public class SimulatedUsageModels {

    private static final Logger LOGGER = Logger.getLogger(SimulatedUsageModels.class);
    private final Map<ClosedWorkload, de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload> closedWorkloads = new HashMap<ClosedWorkload, de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload>();
    private final Map<OpenWorkload, de.uka.ipd.sdq.simucomframework.usage.OpenWorkload> openWorkloads = new HashMap<OpenWorkload, de.uka.ipd.sdq.simucomframework.usage.OpenWorkload>();

    private final IResourceTableManager resourceTableManager;
    private final SimuComModel simucomModel;
    private final EntityReferenceFactory<UsageScenario> usageScenarioReferenceFactory;
    private final IScenarioRunnerFactory<Entity> scenarioRunnerFactory;
    
    
    @Inject
    public SimulatedUsageModels(@MainContext Provider<InterpreterDefaultContext> rootContextProvider, 
            @Global PCMResourceSetPartition globalPartition, SimuComModel simucomModel, IResourceTableManager resourceTableManager, 
            EntityReferenceFactory<UsageScenario> usageScenarioReferenceFactory,
            IScenarioRunnerFactory<Entity> scenarioRunnerFactory) {
        super();
        this.simucomModel = simucomModel;
        this.resourceTableManager = resourceTableManager;
        this.usageScenarioReferenceFactory = usageScenarioReferenceFactory;
        this.scenarioRunnerFactory = scenarioRunnerFactory;
    }


    public IWorkloadDriver createAndAddWorkloadDriver(final UsageScenario usageScenario) {
        // get workload of scenario
        final Workload workload = usageScenario.getWorkload_UsageScenario();

        // determine if workload is open or closed
        if (workload.eClass() == UsagemodelPackage.eINSTANCE.getClosedWorkload()) {
            final de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload driver = this.createClosedWorkloadDriver(workload,
                    usageScenario);
            this.closedWorkloads.put((ClosedWorkload) workload, driver);
            this.simucomModel.getUsageScenarios().add(driver);
            return driver;
        } else if (workload.eClass() == UsagemodelPackage.eINSTANCE.getOpenWorkload()) {
            final de.uka.ipd.sdq.simucomframework.usage.OpenWorkload driver = this.createOpenWorkloadDriver(workload,
                    usageScenario);
            this.openWorkloads.put((OpenWorkload) workload, driver);
            this.simucomModel.getUsageScenarios().add(driver);
            return driver;
        } else {
            throw new UnsupportedOperationException("Unsupported Workload Found");
        }
    }
    
    public void cancelAndUnregisterWorkloadDriver(Workload workload) {
        var workloadDriver = (new UsagemodelSwitch<ICancellableWorkloadDriver>() {
            public ICancellableWorkloadDriver caseOpenWorkload(OpenWorkload object) {
                return SimulatedUsageModels.this.openWorkloads.remove(object);
            };

            public ICancellableWorkloadDriver caseClosedWorkload(ClosedWorkload object) {
                return SimulatedUsageModels.this.closedWorkloads.remove(object);
            };
        }).doSwitch(workload);
        if (workloadDriver != null) {
            workloadDriver.cancel();
            SimulatedUsageModels.this.simucomModel.getUsageScenarios()
                .remove(workloadDriver);
        }
    }

    private de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload createClosedWorkloadDriver(final Workload workload,
            final UsageScenario usageScenario) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Create workload driver for ClosedWorkload: " + workload);
        }
        final ClosedWorkload closedWorkload = (ClosedWorkload) workload;

        final IClosedWorkloadUserFactory userFactory = new ClosedWorkloadUserFactory(simucomModel,
                closedWorkload.getThinkTime_ClosedWorkload().getSpecification(), usageScenario, resourceTableManager) {

            @Override
            public IScenarioRunner createScenarioRunner() {
                return scenarioRunnerFactory.createScenarioRunner(usageScenarioReferenceFactory.createCached(usageScenario));
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

        final IUserFactory userFactory = new OpenWorkloadUserFactory(simucomModel, usageScenario, resourceTableManager) {

            @Override
            public IScenarioRunner createScenarioRunner() {
                return scenarioRunnerFactory.createScenarioRunner(usageScenarioReferenceFactory.createCached(usageScenario));
            }
        };

        // create workload driver by using given factory
        return new de.uka.ipd.sdq.simucomframework.usage.OpenWorkload(simucomModel, userFactory,
                openWorkload.getInterArrivalTime_OpenWorkload().getSpecification(), resourceTableManager);
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

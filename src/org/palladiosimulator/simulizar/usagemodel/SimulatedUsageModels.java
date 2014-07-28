package org.palladiosimulator.simulizar.usagemodel;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.UsageScenarioSwitch;

import de.uka.ipd.sdq.pcm.usagemodel.ClosedWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.OpenWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelPackage;
import de.uka.ipd.sdq.pcm.usagemodel.Workload;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.usage.ClosedWorkloadUserFactory;
import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;
import de.uka.ipd.sdq.simucomframework.usage.IUserFactory;
import de.uka.ipd.sdq.simucomframework.usage.IWorkloadDriver;
import de.uka.ipd.sdq.simucomframework.usage.OpenWorkloadUserFactory;

public class SimulatedUsageModels {

    private static final Logger LOG = Logger.getLogger(SimulatedUsageModels.class);
    private final InterpreterDefaultContext context;
    private final Map<ClosedWorkload, de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload> closedWorkloads = new HashMap<ClosedWorkload, de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload>();
    private final Map<OpenWorkload, de.uka.ipd.sdq.simucomframework.usage.OpenWorkload> openWorkloads = new HashMap<OpenWorkload, de.uka.ipd.sdq.simucomframework.usage.OpenWorkload>();

    public SimulatedUsageModels(final InterpreterDefaultContext mainContext) {
        super();
        this.context = mainContext;
    }

    /**
     * Gets workload drivers for the usage scenarios in the usage model
     * 
     * @return a list of workload drivers
     */
    public IWorkloadDriver[] getWorkloadDrivers(
            final UsageModel model,
            final IModelAccess interpreterFactory) {
        final EList<UsageScenario> usageScenarios = model.getUsageScenario_UsageModel();
        final IWorkloadDriver[] workloads = new IWorkloadDriver[usageScenarios.size()];
        for (int i = 0; i < usageScenarios.size(); i++) {
            workloads[i] = getWorkloadDriver(usageScenarios.get(i), interpreterFactory);
        }
        return workloads;
    }

    private IWorkloadDriver getWorkloadDriver(
            final UsageScenario usageScenario,
            final IModelAccess interpreterFactory) {
        // get workload of scenario
        final Workload workload = usageScenario.getWorkload_UsageScenario();

        // determine if workload is open or closed
        if (workload.eClass().getClassifierID() == UsagemodelPackage.CLOSED_WORKLOAD) {
            final de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload driver = getClosedWorkloadDriver(workload,
                    usageScenario, interpreterFactory);
            closedWorkloads.put((ClosedWorkload) workload, driver);
            return driver;
        }

        if (workload.eClass().getClassifierID() == UsagemodelPackage.OPEN_WORKLOAD) {
            final de.uka.ipd.sdq.simucomframework.usage.OpenWorkload driver = getOpenWorkloadDriver(workload,
                    usageScenario, interpreterFactory);
            openWorkloads.put((OpenWorkload) workload, driver);
            return driver;
        }

        throw new UnsupportedOperationException("Unsupported Workload Found");
    }

    private de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload getClosedWorkloadDriver(
            final Workload workload,
            final UsageScenario usageScenario,
            final IModelAccess interpreterFactory) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Create workload driver for ClosedWorkload: " + workload);
        }
        final ClosedWorkload closedWorkload = (ClosedWorkload) workload;

        final IUserFactory userFactory = new ClosedWorkloadUserFactory(this.context.getModel(), closedWorkload
                .getThinkTime_ClosedWorkload().getSpecification(), usageScenario) {
            @Override
            public IScenarioRunner createScenarioRunner() {
                return getScenarioRunner(interpreterFactory, usageScenario);
            }
        };

        // create workload driver by using given factory
        return new de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload(userFactory, closedWorkload.getPopulation());
    }

    private de.uka.ipd.sdq.simucomframework.usage.OpenWorkload getOpenWorkloadDriver(final Workload workload,
            final UsageScenario usageScenario,
            final IModelAccess interpreterFactory) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Create workload driver for OpenWorkload: " + workload);
        }
        final OpenWorkload openWorkload = (OpenWorkload) workload;

        final IUserFactory userFactory = new OpenWorkloadUserFactory(this.context.getModel(), usageScenario) {
            @Override
            public IScenarioRunner createScenarioRunner() {
                return getScenarioRunner(interpreterFactory, usageScenario);
            }
        };

        // create workload driver by using given factory
        return new de.uka.ipd.sdq.simucomframework.usage.OpenWorkload(
                this.context.getModel(),
                userFactory,
                openWorkload.getInterArrivalTime_OpenWorkload().getSpecification());
    }

    private IScenarioRunner getScenarioRunner(final IModelAccess modelAccessFactory,
            final UsageScenario scenario) {
        return new IScenarioRunner() {

            @Override
            public void scenarioRunner(final SimuComSimProcess thread) {
                final InterpreterDefaultContext myContext = new InterpreterDefaultContext(context);
                myContext.setSimProcess(thread);
                final UsageScenarioSwitch<Object> interpreter = new UsageScenarioSwitch<Object>(myContext,
                        modelAccessFactory);
                interpreter.doSwitch(scenario);
            }
        };
    }

    public de.uka.ipd.sdq.simucomframework.usage.OpenWorkload getOpenWorkloadDriver(OpenWorkload openWorkload) {
        return openWorkloads.get(openWorkload);
    }

    public de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload getClosedWorkloadDriver(ClosedWorkload closedWorkload) {
        return closedWorkloads.get(closedWorkload);
    }
}

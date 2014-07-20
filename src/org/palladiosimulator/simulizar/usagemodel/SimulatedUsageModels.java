package org.palladiosimulator.simulizar.usagemodel;

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

    private final Logger LOG = Logger.getLogger(SimulatedUsageModels.class);
    private final InterpreterDefaultContext context;

    public SimulatedUsageModels(final InterpreterDefaultContext mainContext) {
        super();
        this.context = mainContext;
    }

    /**
     * Gets workload drivers for the usage scenarios in the usage model
     * 
     * @return a list of workload drivers
     */
    public IWorkloadDriver[] getWorkloadDrivers(final UsageModel model, final IModelAccess interpreterFactory) {
        final EList<UsageScenario> usageScenarios = model.getUsageScenario_UsageModel();
        final IWorkloadDriver[] workloads = new IWorkloadDriver[usageScenarios.size()];
        for (int i = 0; i < usageScenarios.size(); i++) {
            workloads[i] = getWorkloadDriver(usageScenarios.get(i), interpreterFactory);
        }
        return workloads;
    }

    private IWorkloadDriver getWorkloadDriver(final UsageScenario usageScenario,
            final IModelAccess interpreterFactory) {
        // get workload of scenario
        final Workload workload = usageScenario.getWorkload_UsageScenario();

        // determine if workload is open or closed
        if (workload.eClass().getClassifierID() == UsagemodelPackage.CLOSED_WORKLOAD) {
            return getClosedWorkloadDriver(workload, usageScenario, interpreterFactory);
        }

        if (workload.eClass().getClassifierID() == UsagemodelPackage.OPEN_WORKLOAD) {
            return getOpenWorkloadDriver(workload, usageScenario, interpreterFactory);
        }

        throw new UnsupportedOperationException("Unsupported Workload Found");
    }

    private IWorkloadDriver getClosedWorkloadDriver(final Workload workload, final UsageScenario usageScenario,
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

    private IWorkloadDriver getOpenWorkloadDriver(final Workload workload, final UsageScenario usageScenario,
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
        return new de.uka.ipd.sdq.simucomframework.usage.OpenWorkload(this.context.getModel(), userFactory,
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

}

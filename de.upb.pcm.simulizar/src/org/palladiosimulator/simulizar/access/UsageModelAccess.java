package org.palladiosimulator.simulizar.access;

import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.UsageScenarioSwitch;
import org.palladiosimulator.simulizar.utils.PCMModels;

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

/**
 * Access class for usage model.
 * 
 * Note: This class is actually not a model access class, it needs to be an interpreter, to avoid
 * "instance of" for the workloads.
 * 
 * @author Joachim Meyer
 */
public class UsageModelAccess extends AbstractPCMModelAccess<UsageModel> {
    /**
     * Constructor.
     * 
     * @param context
     *            the interpreter default context.
     * @param modelHelper
     *            the model helper.
     */
    public UsageModelAccess(final InterpreterDefaultContext context, final ModelHelper modelHelper) {
        super(context, modelHelper);
    }

    /**
     * Gets workload drivers for the usage scenarios in the usage model
     * 
     * @return a list of workload drivers
     */
    public IWorkloadDriver[] getWorkloadDrivers(final IModelAccessFactory interpreterFactory) {
        final EList<UsageScenario> usageScenarios = this.getModel().getUsageScenario_UsageModel();
        final IWorkloadDriver[] workloads = new IWorkloadDriver[usageScenarios.size()];
        for (int i = 0; i < usageScenarios.size(); i++) {
            workloads[i] = this.getWorkloadDriver(usageScenarios.get(i), interpreterFactory);
        }
        return workloads;
    }

    @Override
    protected UsageModel getSpecificModel(final PCMModels models) {
        return models.getUsageModel();
    }

    private IWorkloadDriver getWorkloadDriver(final UsageScenario usageScenario,
            final IModelAccessFactory interpreterFactory) {
        // get workload of scenario
        final Workload workload = usageScenario.getWorkload_UsageScenario();

        // determine if workload is open or closed
        if (workload.eClass().getClassifierID() == UsagemodelPackage.CLOSED_WORKLOAD) {
            return this.getClosedWorkloadDriver(workload, usageScenario, interpreterFactory);
        }

        if (workload.eClass().getClassifierID() == UsagemodelPackage.OPEN_WORKLOAD) {
            return this.getOpenWorkloadDriver(workload, usageScenario, interpreterFactory);
        }

        throw new UnsupportedOperationException("Unsupported Workload Found");
    }

    private IWorkloadDriver getClosedWorkloadDriver(final Workload workload, final UsageScenario usageScenario,
            final IModelAccessFactory interpreterFactory) {
        if (logger.isDebugEnabled()) {
            logger.debug("Create workload driver for ClosedWorkload: " + workload);
        }
        final ClosedWorkload closedWorkload = (ClosedWorkload) workload;

        final IUserFactory userFactory = new ClosedWorkloadUserFactory(this.context.getModel(), closedWorkload
                .getThinkTime_ClosedWorkload().getSpecification()) {
            @Override
            public IScenarioRunner createScenarioRunner() {
                return UsageModelAccess.this.getScenarioRunner(interpreterFactory, usageScenario);
            }
        };

        // create workload driver by using given factory
        return new de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload(userFactory, closedWorkload.getPopulation(),
                closedWorkload.getUsageScenario_Workload().getId());
    }

    private IWorkloadDriver getOpenWorkloadDriver(final Workload workload, final UsageScenario usageScenario,
            final IModelAccessFactory interpreterFactory) {
        if (logger.isDebugEnabled()) {
            logger.debug("Create workload driver for OpenWorkload: " + workload);
        }
        final OpenWorkload openWorkload = (OpenWorkload) workload;

        final IUserFactory userFactory = new OpenWorkloadUserFactory(this.context.getModel()) {
            @Override
            public IScenarioRunner createScenarioRunner() {
                return UsageModelAccess.this.getScenarioRunner(interpreterFactory, usageScenario);
            }
        };

        // create workload driver by using given factory
        return new de.uka.ipd.sdq.simucomframework.usage.OpenWorkload(this.context.getModel(), userFactory,
                openWorkload.getInterArrivalTime_OpenWorkload().getSpecification(), openWorkload
                        .getUsageScenario_Workload().getId());
    }

    protected IScenarioRunner getScenarioRunner(final IModelAccessFactory modelAccessFactory,
            final UsageScenario scenario) {
        return new IScenarioRunner() {

            @Override
            public void scenarioRunner(final SimuComSimProcess thread) {
                final InterpreterDefaultContext myContext = new InterpreterDefaultContext(UsageModelAccess.this.context);
                myContext.setSimProcess(thread);
                final UsageScenarioSwitch<Object> interpreter = new UsageScenarioSwitch<Object>(myContext,
                        modelAccessFactory);
                interpreter.doSwitch(scenario);
            }
        };
    }
}

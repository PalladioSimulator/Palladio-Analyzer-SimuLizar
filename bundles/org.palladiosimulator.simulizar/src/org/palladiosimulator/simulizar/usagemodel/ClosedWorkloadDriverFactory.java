package org.palladiosimulator.simulizar.usagemodel;

import org.apache.log4j.Logger;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.Workload;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

import de.uka.ipd.sdq.simucomframework.usage.ClosedWorkloadUserFactory;
import de.uka.ipd.sdq.simucomframework.usage.IClosedWorkloadUserFactory;
import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;
/**
 * Implementation of WorkloadDriverFactory for closedworkloaddrivers
 * @author Jens Manig
 *
 */
public class ClosedWorkloadDriverFactory implements WorkloadDriverFactory<de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload> {
	private static final Logger LOGGER = Logger.getLogger(SimulatedUsageModels.class);
	
	@Override
	public de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload createWorkloadDriver(final InterpreterDefaultContext rootContext, final Workload workload,
            final UsageScenario usageScenario, ScenarioRunnerFactory scenarioRunnerFactory) {
		if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Create workload driver for ClosedWorkload: " + workload);
        }
        final ClosedWorkload closedWorkload = (ClosedWorkload) workload;

        final IClosedWorkloadUserFactory userFactory = new ClosedWorkloadUserFactory(rootContext.getModel(),
                closedWorkload.getThinkTime_ClosedWorkload().getSpecification(), usageScenario) {

            @Override
            public IScenarioRunner createScenarioRunner() {
                return scenarioRunnerFactory.create(usageScenario);
            }
        };

        // create workload driver by using given factory
        return new  de.uka.ipd.sdq.simucomframework.usage.ClosedWorkload(userFactory, closedWorkload.getPopulation());
	}


}
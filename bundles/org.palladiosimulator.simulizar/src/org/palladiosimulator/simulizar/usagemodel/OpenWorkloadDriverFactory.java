package org.palladiosimulator.simulizar.usagemodel;

import org.apache.log4j.Logger;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.Workload;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;
import de.uka.ipd.sdq.simucomframework.usage.IUserFactory;
import de.uka.ipd.sdq.simucomframework.usage.OpenWorkloadUserFactory;
/**
 * Implementation of WorkloadDriverFactory for openworkloaddrivers
 *
 * @author Jens Manig
 *
 */
public class OpenWorkloadDriverFactory implements WorkloadDriverFactory<de.uka.ipd.sdq.simucomframework.usage.OpenWorkload> {
	private static final Logger LOGGER = Logger.getLogger(SimulatedUsageModels.class);
	
	@Override
	public de.uka.ipd.sdq.simucomframework.usage.OpenWorkload createWorkloadDriver(InterpreterDefaultContext rootContext, Workload workload,
			UsageScenario usageScenario, ScenarioRunnerFactory scenarioRunnerFactory) {
		 if (LOGGER.isDebugEnabled()) {
	         LOGGER.debug("Create workload driver for OpenWorkload: " + workload);
	     }
	     final OpenWorkload openWorkload = (OpenWorkload) workload;

	     final IUserFactory userFactory = new OpenWorkloadUserFactory(rootContext.getModel(), usageScenario) {

	         @Override
	         public IScenarioRunner createScenarioRunner() {
	             return scenarioRunnerFactory.create(usageScenario);
	         }
	     };

	     // create workload driver by using given factory
	     return new de.uka.ipd.sdq.simucomframework.usage.OpenWorkload(rootContext.getModel(), userFactory,
	             openWorkload.getInterArrivalTime_OpenWorkload().getSpecification());
	}

}
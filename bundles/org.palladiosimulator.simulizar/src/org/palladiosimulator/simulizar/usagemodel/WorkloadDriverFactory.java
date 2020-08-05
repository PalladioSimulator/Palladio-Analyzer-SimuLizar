package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.Workload;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

import de.uka.ipd.sdq.simucomframework.usage.IWorkloadDriver;
/**
 * Factory for creation of workloaddrivers
 * @author Jens Manig
 *
 */
public interface WorkloadDriverFactory<WorkloadDriver extends IWorkloadDriver> {
	 
	 public WorkloadDriver createWorkloadDriver(final InterpreterDefaultContext rootContext, final Workload workload,
	            final UsageScenario usageScenario, ScenarioRunnerFactory scenarioRunnerFactory);
	
}

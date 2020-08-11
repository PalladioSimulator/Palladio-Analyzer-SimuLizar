package org.palladiosimulator.simulizar.usagemodel;

import javax.inject.Inject;

import org.palladiosimulator.pcm.usagemodel.UsageModel;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContextFactory;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.UsageScenarioSwitchFactory;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import com.google.inject.assistedinject.Assisted;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;
/**
 * Implementation of IScenarionRunner
 * @author Jens Manig
 *
 */
public class ScenarioRunnerImpl implements IScenarioRunner{
	
	private final UsageScenario scenario;
	private final InterpreterDefaultContext rootContext;
	private final PCMPartitionManager pcmPartitionManager;
    private final InterpreterDefaultContextFactory contextFactory;
    private final UsageScenarioSwitchFactory usageSwitch;

	@Inject
	public ScenarioRunnerImpl(@Assisted UsageScenario scenario, InterpreterDefaultContext rootContext, final PCMPartitionManager pcmPartitionManager,
		InterpreterDefaultContextFactory contextFactory, UsageScenarioSwitchFactory usageSwitch) {
		this.scenario = scenario;
		this.rootContext = rootContext;
		this.pcmPartitionManager = pcmPartitionManager;
		this.contextFactory = contextFactory;
		this.usageSwitch = usageSwitch;
	}

	@Override
	 public void scenarioRunner(final SimuComSimProcess thread) {
        final InterpreterDefaultContext newContext = this.contextFactory.create(
                this.rootContext, thread);
        final UsageModel usageModel = this.pcmPartitionManager.getLocalPCMModel().getUsageModel();
        
        // If the UsageScenario is not contained in the UsageModel (e.g. it has
        // been removed after the workload scheduled the new user, and before the 
        // user starts execution) simply exit without processing the scenario.
        usageModel.getUsageScenario_UsageModel().stream()
        	.filter(sc -> sc.getId().equals(this.scenario.getId()))
        	.findAny().ifPresent(sc -> {
        	    usageSwitch.create(newContext).doSwitch(sc);
        	});
    }

}

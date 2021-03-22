package org.palladiosimulator.simulizar.failurescenario.interpreter;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.failuremodel.failurescenario.FailureScenario;
import org.palladiosimulator.failuremodel.failurescenario.FailureScenarioRepository;
import org.palladiosimulator.failuremodel.failurescenario.FailurescenarioPackage;
import org.palladiosimulator.simulizar.failurescenario.interpreter.provider.FailureBehaviorChangesProviderSwitch;
import org.palladiosimulator.simulizar.failurescenario.interpreter.provider.ReferenceResolverSwitch;
import org.palladiosimulator.simulizar.failurescenario.interpreter.provider.ScheduledResourceProviderSwitch;
import org.palladiosimulator.simulizar.modelobserver.AllocationLookupSyncer;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.runtimestate.PreInterpretationBehaviorManager;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEventFactory;

public class FailurescenarioObserver implements IModelObserver {

	private static final Logger LOGGER = Logger.getLogger(AllocationLookupSyncer.class);

	private PCMResourceSetPartition globalPartition;
	private FailureScenarioRepository fsRepository;
	private ISimEventFactory simEventFactory;
	private final PreInterpretationBehaviorManager pibManager;
	private final ScheduledResourceProviderSwitch resourceProvider;
	// private final InterpreterDefaultContext context;

	@Inject
	public FailurescenarioObserver(// final InterpreterDefaultContext context,
			ISimEventFactory simEventFactory, @Global PCMResourceSetPartition globalPartition,
			PreInterpretationBehaviorManager pibManager, ResourceRegistry resourceRegistry) {
		// this.context = context;
		this.simEventFactory = simEventFactory;
		this.globalPartition = globalPartition;
		this.pibManager = pibManager;
		this.resourceProvider = new ScheduledResourceProviderSwitch(resourceRegistry);
		this.fsRepository = null;
	}

	@Override
	public void initialize() {
		List<EObject> failureScenarioResources = globalPartition
				.getElement(FailurescenarioPackage.eINSTANCE.getFailureScenarioRepository());
		if (!failureScenarioResources.isEmpty()) {
			LOGGER.info("Start loading failurescenarios");
			fsRepository = (FailureScenarioRepository) failureScenarioResources.get(0);
			fsRepository.getFailurescenarios().forEach(fs -> this.registerOccurences(fs));
		}
	}

	private void registerOccurences(FailureScenario fs) {
		// Only FS with the attribute executionEnabled == true are interpreted.
		if (!fs.getExecutionEnabled()) {
			return;
		}

		ReferenceResolverSwitch referenceResolver = new ReferenceResolverSwitch();
		FailureBehaviorChangesProviderSwitch fbChangesProvider = new FailureBehaviorChangesProviderSwitch();

		fs.getOccurences().forEach(o -> {

			List<FailureBehaviorChangeDTO> fbChangeDTOs = fbChangesProvider.doSwitch(o.getFailure());

			for (FailureBehaviorChangeDTO dto : fbChangeDTOs) {
				// check if failuretype is already supported and a DataTransferObject for its
				// strategy exists
				// && try to allocate context of the strategy
				if (dto != null
						&& dto.getStrategy().allocateContext(referenceResolver, resourceProvider, pibManager, o)) {

					double simulationPointInTime = Double.parseDouble(o.getPointInTime().getSpecification());
					// create simulation event for failure behavior change
					new FailureBehaviorChangingSimulationEntity(simEventFactory,
							simulationPointInTime + dto.getRelativePointInTime(), dto.getStrategy());
				}
			}
		});
	}

}

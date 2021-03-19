package org.palladiosimulator.simulizar.failurescenario.interpreter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.failuremodel.failurescenario.FailureScenario;
import org.palladiosimulator.failuremodel.failurescenario.FailureScenarioRepository;
import org.palladiosimulator.failuremodel.failurescenario.FailurescenarioPackage;
import org.palladiosimulator.simulizar.failurescenario.interpreter.preinterpretation.ReferenceResolverSwitch;
import org.palladiosimulator.simulizar.modelobserver.AllocationLookupSyncer;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.runtimestate.PreInterpretationBehaviorManager;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEventFactory;

public class FailurescenarioObserver implements IModelObserver {

	private static final Logger LOGGER = Logger.getLogger(AllocationLookupSyncer.class);

	private PCMResourceSetPartition globalPartition;
	private FailureScenarioRepository fsRepository;
	private ISimEventFactory simEventFactory;
	private final PreInterpretationBehaviorManager pibManager;
	// private final ISimulatedModelEntityAccess<LinkingResource,
	// SimulatedLinkingResource> linkingResourceAccess;

	@Inject
	public FailurescenarioObserver(ISimEventFactory simEventFactory, @Global PCMResourceSetPartition globalPartition,
			PreInterpretationBehaviorManager pibManager) {
		this.simEventFactory = simEventFactory;
		this.globalPartition = globalPartition;
		this.pibManager = pibManager;
		this.fsRepository = null;

//		var link = linkingResourceAccess.getSimulatedEntity(l.getId());
//      if(link != null) {
//          ((SimulatedLinkingResource) link).getUnderlyingResource().getId();
//      }
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
		FailureBehaviorChangesProvider fbChangesProvider = new FailureBehaviorChangesProvider();

		List<FailureBehaviorChangingSimulationEntity> failureSimEntities = new ArrayList<FailureBehaviorChangingSimulationEntity>();

		fs.getOccurences().forEach(o -> {
			
			List<FailureBehaviorChangeDTO> fbChangeDTOs = fbChangesProvider.doSwitch(o.getFailure());

			for (FailureBehaviorChangeDTO dto : fbChangeDTOs) {
				// check if failuretype is already supported
				// && try to allocate context of the strategy
				if (dto != null && dto.getStrategy().allocateContext(referenceResolver, pibManager, o)) {

					double simulationPointInTime = Double.parseDouble(o.getPointInTime().getSpecification());
					failureSimEntities.add(new FailureBehaviorChangingSimulationEntity(simEventFactory,
							simulationPointInTime + dto.getRelativePointInTime(), dto.getStrategy()));
				}
			}

//			// get the failure behavior
//			InterpretationBehaviorProvider ibProvider = new InterpretationBehaviorProvider();
//			PreInterpretationBehavior b = ibProvider.doSwitch(o.getFailure());
//			// get the reference where the adapter should be added
//			String id = referenceResolver.doSwitch(o.getOrigin());
//
//			// if failuretype is already supported create event/simulationEntity
//			if (b != null && id != null) {
//				// get the global container of the failure-attached-simEntity and add an
//				// occurence
//				// during simulation.
//				// when it is triggered it adds the PreInterpretationBehavior to the container.
//				PreInterpretationBehaviorContainer pibcontainer = pibManager.getContainerForEntity(id);
//				failureSimEntities.add(new FailureBehaviorChangingSimulationEntity(simEventFactory,
//						Double.parseDouble(o.getPointInTime().getSpecification()), pibcontainer, b));
//			}
		});
	}

}

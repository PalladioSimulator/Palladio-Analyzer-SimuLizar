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
import org.palladiosimulator.simulizar.failurescenario.interpreter.adapter.InterpretationBehaviorProvider;
import org.palladiosimulator.simulizar.failurescenario.interpreter.adapter.ReferenceResolverSwitch;
import org.palladiosimulator.simulizar.interpreter.adapter.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.adapter.PreInterpretationBehaviorAdapter;
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

	@Inject
	public FailurescenarioObserver(ISimEventFactory simEventFactory, @Global PCMResourceSetPartition globalPartition,
			PreInterpretationBehaviorManager pibManager) {
		this.simEventFactory = simEventFactory;
		this.globalPartition = globalPartition;
		this.pibManager = pibManager;
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

		InterpretationBehaviorProvider ibProvider = new InterpretationBehaviorProvider();
		ReferenceResolverSwitch referenceResolver = new ReferenceResolverSwitch();

		List<FailureOccurenceSimulationEntity> failureSimEntities = new ArrayList<FailureOccurenceSimulationEntity>();

		fs.getOccurences().forEach(o -> {
			// get the failure behavior
			PreInterpretationBehavior b = ibProvider.doSwitch(o.getFailure());
			// get the reference where the adapter should be added
			String id = referenceResolver.doSwitch(o.getOrigin());

			// if failuretype is already supported create event/simulationEntity
			if (b != null && id != null) {
				// get the global adapter of the failure-attached-simEntity and add an occurence
				// during simulation.
				// when it is triggered it adds the PreInterpretationBehavior to the adapter.
				PreInterpretationBehaviorAdapter adapter = pibManager.getAdapterForEntity(id);
				failureSimEntities.add(new FailureOccurenceSimulationEntity(simEventFactory,
						Double.parseDouble(o.getPointInTime().getSpecification()), adapter, b));
			}
		});
	}

}

package org.palladiosimulator.simulizar.failurescenario.interpreter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.failuremodel.failurescenario.FailureScenario;
import org.palladiosimulator.failuremodel.failurescenario.FailureScenarioRepository;
import org.palladiosimulator.failuremodel.failurescenario.FailurescenarioPackage;
import org.palladiosimulator.failuremodel.failurescenario.Occurence;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.Repository;
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.AbstractBranchTransition;
import org.palladiosimulator.pcm.seff.BranchAction;
import org.palladiosimulator.pcm.seff.InternalAction;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.SeffPackage;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.failurescenario.interpreter.adapter.InterpretationBehaviorProvider;
import org.palladiosimulator.simulizar.failurescenario.interpreter.adapter.ReferenceResolverSwitch;
import org.palladiosimulator.simulizar.failurescenario.interpreter.failuremanager.FailureManager;
import org.palladiosimulator.simulizar.interpreter.adapter.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.adapter.PreInterpretationBehaviorAdapter;
import org.palladiosimulator.simulizar.modelobserver.AllocationLookupSyncer;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.usagemodel.LoopingUsageEvolver;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEventFactory;

public class FailurescenarioObserver implements IModelObserver {

	private static final Logger LOGGER = Logger.getLogger(AllocationLookupSyncer.class);

	private PCMResourceSetPartition globalPartition;
	private FailureScenarioRepository fsRepository;
	private ISimEventFactory simEventFactory;
	private ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> simEntityAccessor;

	@Inject
	public FailurescenarioObserver(
			ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> simEntityAccessor,
			ISimEventFactory simEventFactory, @Global PCMResourceSetPartition globalPartition) {
		this.simEventFactory = simEventFactory;
		this.globalPartition = globalPartition;
		this.simEntityAccessor = simEntityAccessor;
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
		} else {
			LOGGER.error("No failure scenarios found");
		}
	}

	private void registerOccurences(FailureScenario fs) {

		// ------------------------------debug
		List<EObject> repos = globalPartition.getElement(RepositoryPackage.eINSTANCE.getRepository());
		List<String> ids = new ArrayList<String>();

		Repository repo = (Repository) repos.get(1);
		EList<RepositoryComponent> repoComponents = repo.getComponents__Repository();
		repos.forEach(r -> ((Repository) r).getComponents__Repository().forEach(c -> ids.add(c.getId())));

		BasicComponent bc = (BasicComponent) repoComponents.get(0);
		ResourceDemandingSEFF rdseff = (ResourceDemandingSEFF) bc.getServiceEffectSpecifications__BasicComponent()
				.get(0);
		EList<AbstractAction> actions = rdseff.getSteps_Behaviour();
		actions.forEach(a -> ids.add(a.getId()));

		BranchAction ba = ((BranchAction) actions.get(2));
		EList<AbstractBranchTransition> abstractBranchTransitions = ba.getBranches_Branch();
		AbstractAction action = abstractBranchTransitions.get(0).getBranchBehaviour_BranchTransition()
				.getSteps_Behaviour().get(2);
		// -----------------------------debug

		InterpretationBehaviorProvider ibProvider = new InterpretationBehaviorProvider();
		ReferenceResolverSwitch referenceResolver = new ReferenceResolverSwitch();

		List<FailureOccurenceSimulationEntity> failureSimEntities = new ArrayList<FailureOccurenceSimulationEntity>();

		fs.getOccurences().forEach(o -> {
			// get the failure behavior
			PreInterpretationBehavior b = ibProvider.doSwitch(o.getFailure());
			// get the reference where the adapter should be added
			Notifier n = referenceResolver.doSwitch(o.getOrigin());
			AbstractSimulatedResourceContainer simentity = simEntityAccessor.getSimulatedEntity("_rl6MwCHbEd62GabW1zGSBw");
			// if failuretype is already supported create event/simulationEntity
			if (b != null && n != null) {
				failureSimEntities.add(new FailureOccurenceSimulationEntity(simEventFactory,
						Double.parseDouble(o.getPointInTime().getSpecification()),
						new PreInterpretationBehaviorAdapter(b, n)));
			}
		});
	}

}

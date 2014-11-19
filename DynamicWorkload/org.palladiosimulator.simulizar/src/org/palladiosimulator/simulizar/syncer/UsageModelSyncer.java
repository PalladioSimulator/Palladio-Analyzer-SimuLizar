package org.palladiosimulator.simulizar.syncer;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

import de.uka.ipd.sdq.pcm.core.CorePackage;
import de.uka.ipd.sdq.pcm.core.PCMRandomVariable;
import de.uka.ipd.sdq.pcm.parameter.VariableCharacterisation;
import de.uka.ipd.sdq.pcm.parameter.VariableUsage;
import de.uka.ipd.sdq.pcm.usagemodel.AbstractUserAction;
import de.uka.ipd.sdq.pcm.usagemodel.ClosedWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.OpenWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelPackage;
import de.uka.ipd.sdq.pcm.usagemodel.Workload;
import de.uka.ipd.sdq.stoex.StoexPackage;

public class UsageModelSyncer extends AbstractSyncer<UsageModel> implements IModelSyncer {

	private static final Logger LOGGER = Logger.getLogger(UsageModelSyncer.class);

	public UsageModelSyncer(final SimuLizarRuntimeState runtimeModel) {
		super(runtimeModel, runtimeModel.getModelAccess().getGlobalPCMModel().getUsageModel());
	}

	@Override
	public void initializeSyncer() {
	}

	@Override
	protected void synchronizeSimulationEntities(final Notification notification) {
		LOGGER.debug("Usage model changed... Resync needed: " + notification.getEventType());
		switch (notification.getEventType()) {
		case Notification.REMOVING_ADAPTER:
		case Notification.RESOLVE:
			break;
		case Notification.REMOVE:
			LOGGER.debug("Case REMOVE");
			break;
		case Notification.SET:
			LOGGER.debug("Case SET");
			if (UsagemodelPackage.eINSTANCE.getClosedWorkload().isInstance(notification.getNotifier())) {
				syncClosedWorkload(notification);
			} else if (CorePackage.eINSTANCE.getPCMRandomVariable().isInstance(notification.getNotifier()) && ((EObject) notification.getNotifier()).eContainer() instanceof OpenWorkload
					&& notification.getFeature() == StoexPackage.eINSTANCE.getRandomVariable_Specification()) {
				syncOpenWorkload(notification);
			} else if (CorePackage.eINSTANCE.getPCMRandomVariable().isInstance(notification.getNotifier()) && ((EObject) notification.getNotifier()).eContainer() instanceof VariableCharacterisation
					&& notification.getFeature() == StoexPackage.eINSTANCE.getRandomVariable_Specification()) {
				syncInputParameterCharacterization(notification);
			} else {
				LOGGER.error("Usage model changed...But no resync strategy is known. Simulation results most likely are wrong.");
			}
			break;
		case Notification.ADD:
			LOGGER.debug("Case ADD");
			break;
		default:
			LOGGER.error("Usage model changed...But no resync strategy is known. Simulation results most likely are wrong.");
			break;
		}
	}

	/**
	 * @param notification
	 */
	private void syncInputParameterCharacterization(Notification notification) {
		VariableCharacterisation notifiedVariableCharacterisation = ((PCMRandomVariable) notification.getNotifier()).getVariableCharacterisation_Specification();
		VariableUsage notifiedVariableUsage = notifiedVariableCharacterisation.getVariableUsage_VariableCharacterisation();
		EntryLevelSystemCall notifiedEntryLevelSystemCall = notifiedVariableUsage.getEntryLevelSystemCall_InputParameterUsage();
		UsageScenario notifiedUsageScenario = notifiedEntryLevelSystemCall.getScenarioBehaviour_AbstractUserAction().getUsageScenario_SenarioBehaviour();
		UsageModel runtimeUsageModel = this.runtimeModel.getModelAccess().getGlobalPCMModel().getUsageModel();
		for (UsageScenario us : runtimeUsageModel.getUsageScenario_UsageModel()) {
			LOGGER.info("Here 3");
			if (us.getId().equalsIgnoreCase(notifiedUsageScenario.getId())) {
				for (AbstractUserAction aua : us.getScenarioBehaviour_UsageScenario().getActions_ScenarioBehaviour()) {
					LOGGER.info("Here 2");
					if (aua instanceof EntryLevelSystemCall) {
						if (((EntryLevelSystemCall) aua).getId().equalsIgnoreCase(notifiedEntryLevelSystemCall.getId())) {
							for (VariableUsage vu : ((EntryLevelSystemCall) aua).getInputParameterUsages_EntryLevelSystemCall()) {
								LOGGER.info("Here 1");
								if (vu.getNamedReference__VariableUsage().getReferenceName().equalsIgnoreCase(notifiedVariableUsage.getNamedReference__VariableUsage().getReferenceName())) {
									LOGGER.info("Setting VariableUsage: " + vu.getNamedReference__VariableUsage().getReferenceName() + " with a new changed value = "
											+ notification.getNewStringValue());
									for (VariableCharacterisation vc : vu.getVariableCharacterisation_VariableUsage()) {
										LOGGER.info("Here 0");
										if (vc.getType().compareTo(notifiedVariableCharacterisation.getType()) == 0) {
											vu.getVariableCharacterisation_VariableUsage().get(0).getSpecification_VariableCharacterisation().setSpecification(notification.getNewStringValue());

										}
									}
								}

							}
						}
					}

				}
			}

		}
	}

	// private void
	// inputParameterCharacterizationChange(VariableCharacterisation
	// variableCharacterisation, String newStringValue) {
	// LOGGER.info("Setting input parameter varial characterization to " +
	// newStringValue);
	// //
	// this.runtimeModel.getUsageModels().getOpenWorkloadDriver((OpenWorkload)
	// // workload).setInterarrivalTime(newStringValue);
	// }

	/**
	 * @param notification
	 */
	private void syncClosedWorkload(final Notification notification) {
		final ClosedWorkload workload = (ClosedWorkload) notification.getNotifier();
		closedWorkloadPopulationChanged(workload, notification.getNewIntValue());
	}

	/**
	 * @param notification
	 */
	private void syncOpenWorkload(final Notification notification) {
		final OpenWorkload workload = (OpenWorkload) ((EObject) notification.getNotifier()).eContainer();
		openWorkloadInterarrivalChange(workload, notification.getNewStringValue());
	}

	private void openWorkloadInterarrivalChange(final Workload workload, final String newInterarrivalTime) {
		LOGGER.debug("Setting open workload interarrival time to " + newInterarrivalTime);
		this.runtimeModel.getUsageModels().getOpenWorkloadDriver((OpenWorkload) workload).setInterarrivalTime(newInterarrivalTime);
	}

	private void closedWorkloadPopulationChanged(final Workload workload, final int newPopulation) {
		LOGGER.debug("Setting closed workload population to " + newPopulation);
		this.runtimeModel.getUsageModels().getClosedWorkloadDriver((ClosedWorkload) workload).setPopulation(newPopulation);
	}
}

package org.palladiosimulator.simulizar.modelobserver;

import jakarta.inject.Inject;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.core.CorePackage;
import org.palladiosimulator.pcm.parameter.ParameterPackage;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;
import org.palladiosimulator.pcm.usagemodel.Workload;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.usagemodel.SimulatedUsageModels;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import de.uka.ipd.sdq.simucomframework.usage.IWorkloadDriver;
import de.uka.ipd.sdq.stoex.StoexPackage;

@SimulationRuntimeScope
public class UsageModelSyncer extends AbstractUsageModelObserver {

    private static final Logger LOGGER = Logger.getLogger(UsageModelSyncer.class);
    private final SimulatedUsageModels usageModels;

    @Inject
    public UsageModelSyncer(@Global PCMResourceSetPartition globalPCMInstance, SimulatedUsageModels usageModels) {
        super(globalPCMInstance);
        this.usageModels = usageModels;
    }

    @Override
    public void initialize() {
        super.initialize();
        globalPCMInstance.getUsageModel()
            .getUsageScenario_UsageModel()
            .forEach(us -> {
                usageModels.createAndAddWorkloadDriver(us);
            });
    }

    @Override
    protected void add(Notification notification) {
        if (UsagemodelPackage.eINSTANCE.getUsageModel()
            .isInstance(notification.getNotifier())
                && UsagemodelPackage.eINSTANCE.getUsageModel_UsageScenario_UsageModel()
                    .equals(notification.getFeature())) {
            this.syncUsageScenarioAddition(notification);
        } else {
            LOGGER.error(
                    "Usage model changed...But no resync strategy is known. Simulation results most likely are wrong.");
        }
    }

    @Override
    protected void remove(Notification notification) {
        if (UsagemodelPackage.eINSTANCE.getUsageModel()
            .isInstance(notification.getNotifier())
                && UsagemodelPackage.eINSTANCE.getUsageModel_UsageScenario_UsageModel()
                    .equals(notification.getFeature())) {
            this.syncUsageScenarioRemoval(notification);
        } else {
            LOGGER.error(
                    "Usage model changed...But no resync strategy is known. Simulation results most likely are wrong.");
        }
    }

    @Override
    protected void set(final Notification notification) {
        if (UsagemodelPackage.eINSTANCE.getClosedWorkload()
            .isInstance(notification.getNotifier())
                && notification.getFeature() == UsagemodelPackage.eINSTANCE.getClosedWorkload_Population()) {
            this.syncClosedWorkloadPopulationChange(notification);
        } else if (CorePackage.eINSTANCE.getPCMRandomVariable()
            .isInstance(notification.getNotifier())
                && ((EObject) notification.getNotifier()).eContainer() instanceof ClosedWorkload
                && notification.getFeature() == StoexPackage.eINSTANCE.getRandomVariable_Specification()) {
            this.syncClosedWorkloadThinkTimeChange(notification);
        } else if (CorePackage.eINSTANCE.getPCMRandomVariable()
            .isInstance(notification.getNotifier())
                && ((EObject) notification.getNotifier()).eContainer() instanceof OpenWorkload
                && notification.getFeature() == StoexPackage.eINSTANCE.getRandomVariable_Specification()) {
            this.syncOpenWorkloadInterarrivalTimeChange(notification);
        } else if (CorePackage.eINSTANCE.getPCMRandomVariable()
            .isInstance(notification.getNotifier())
                && ParameterPackage.eINSTANCE.getVariableCharacterisation()
                    .isInstance(((EObject) notification.getNotifier()).eContainer())) {
            /*
             * Nothing needs to happen in this case as the new variable char. is used for the next
             * user
             */
        } else if (UsagemodelPackage.eINSTANCE.getUsageScenario()
            .isInstance(notification.getNotifier())
                && notification.getFeature() == UsagemodelPackage.eINSTANCE.getUsageScenario_UsageModel_UsageScenario()
                && notification.getOldValue() != null && notification.getNewValue() == null) {
            /*
             * Do nothing as the change is handled as a remove on the container
             */
        } else {
            LOGGER.error(
                    "Usage model changed...But no resync strategy is known. Simulation results most likely are wrong.");
        }
    }

    private void syncClosedWorkloadPopulationChange(final Notification notification) {
        final ClosedWorkload workload = (ClosedWorkload) notification.getNotifier();
        this.closedWorkloadPopulationChange(workload, notification.getNewIntValue());
    }

    private void syncClosedWorkloadThinkTimeChange(final Notification notification) {
        final ClosedWorkload workload = (ClosedWorkload) ((EObject) notification.getNotifier()).eContainer();
        this.closedWorkloadThinkTimeChange(workload, notification.getNewStringValue());
    }

    private void syncOpenWorkloadInterarrivalTimeChange(final Notification notification) {
        final OpenWorkload workload = (OpenWorkload) ((EObject) notification.getNotifier()).eContainer();
        this.openWorkloadInterarrivalTimeChange(workload, notification.getNewStringValue());
    }

    private void openWorkloadInterarrivalTimeChange(final Workload workload, final String newInterarrivalTime) {
        LOGGER.debug("Setting open workload interarrival time to " + newInterarrivalTime);
        usageModels.getOpenWorkloadDriver((OpenWorkload) workload)
            .setInterarrivalTime(newInterarrivalTime);
    }

    private void closedWorkloadPopulationChange(final Workload workload, final int newPopulation) {
        LOGGER.debug("Setting closed workload population to " + newPopulation);
        usageModels.getClosedWorkloadDriver((ClosedWorkload) workload)
            .setPopulation(newPopulation);
    }

    private void closedWorkloadThinkTimeChange(final Workload workload, final String newThinkTime) {
        LOGGER.debug("Setting closed workload think time to " + newThinkTime);
        usageModels.getClosedWorkloadDriver((ClosedWorkload) workload)
            .setThinkTime(newThinkTime);
    }

    private void syncUsageScenarioAddition(Notification notification) {
        LOGGER.debug("Initializing execution of new usage scenario");
        IWorkloadDriver newDriver = usageModels.createAndAddWorkloadDriver((UsageScenario) notification.getNewValue());
        newDriver.run();
        LOGGER.debug("Execution of new usage scenario started");
    }

    private void syncUsageScenarioRemoval(Notification notification) {
        LOGGER.debug("Stopping execution of specific usage scenario");
        usageModels.cancelAndUnregisterWorkloadDriver(
                ((UsageScenario) notification.getOldValue()).getWorkload_UsageScenario());
        LOGGER.debug("Execution of new usage scenario started");
    }
}

package org.palladiosimulator.simulizar.syncer;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.core.CorePackage;
import org.palladiosimulator.pcm.parameter.ParameterPackage;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;
import org.palladiosimulator.pcm.usagemodel.Workload;

import de.uka.ipd.sdq.stoex.StoexPackage;

public class UsageModelSyncer extends AbstractUsageModelObserver {

    private static final Logger LOGGER = Logger.getLogger(UsageModelSyncer.class);

    public UsageModelSyncer() {
        super();
    }

    @Override
    protected void set(final Notification notification) {
        if (UsagemodelPackage.eINSTANCE.getClosedWorkload().isInstance(notification.getNotifier())) {
            this.syncClosedWorkload(notification);
        } else if (CorePackage.eINSTANCE.getPCMRandomVariable().isInstance(notification.getNotifier())
                && ((EObject) notification.getNotifier()).eContainer() instanceof OpenWorkload
                && notification.getFeature() == StoexPackage.eINSTANCE.getRandomVariable_Specification()) {
            this.syncOpenWorkload(notification);
        } else if (CorePackage.eINSTANCE.getPCMRandomVariable().isInstance(notification.getNotifier())
                && ParameterPackage.eINSTANCE.getVariableCharacterisation()
                        .isInstance(((EObject) notification.getNotifier()).eContainer())) {
            /*
             * Nothing needs to happen in this case as the new variable char. is used for the next
             * user
             */
        } else {
            LOGGER.error(
                    "Usage model changed...But no resync strategy is known. Simulation results most likely are wrong.");
        }
    }

    /**
     * @param notification
     */
    private void syncClosedWorkload(final Notification notification) {
        final ClosedWorkload workload = (ClosedWorkload) notification.getNotifier();
        this.closedWorkloadPopulationChanged(workload, notification.getNewIntValue());
    }

    /**
     * @param notification
     */
    private void syncOpenWorkload(final Notification notification) {
        final OpenWorkload workload = (OpenWorkload) ((EObject) notification.getNotifier()).eContainer();
        this.openWorkloadInterarrivalChange(workload, notification.getNewStringValue());
    }

    private void openWorkloadInterarrivalChange(final Workload workload, final String newInterarrivalTime) {
        LOGGER.debug("Setting open workload interarrival time to " + newInterarrivalTime);
        this.runtimeModel.getUsageModels().getOpenWorkloadDriver((OpenWorkload) workload)
                .setInterarrivalTime(newInterarrivalTime);
    }

    private void closedWorkloadPopulationChanged(final Workload workload, final int newPopulation) {
        LOGGER.debug("Setting closed workload population to " + newPopulation);
        this.runtimeModel.getUsageModels().getClosedWorkloadDriver((ClosedWorkload) workload)
                .setPopulation(newPopulation);
    }

}

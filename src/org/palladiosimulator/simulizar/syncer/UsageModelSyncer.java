package org.palladiosimulator.simulizar.syncer;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.simulizar.runtimestate.SimuComRuntimeState;

import de.uka.ipd.sdq.pcm.usagemodel.ClosedWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.OpenWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelPackage;
import de.uka.ipd.sdq.pcm.usagemodel.Workload;

public class UsageModelSyncer extends AbstractSyncer<UsageModel> implements IModelSyncer {

    private final static Logger LOG = Logger.getLogger(UsageModelSyncer.class);

    public UsageModelSyncer(
            final SimuComRuntimeState simuComModel,
            final UsageModel model) {
        super(simuComModel, model);
    }

    @Override
    public void initializeSyncer() {
    }

    @Override
    protected void synchronizeSimulationEntities(final Notification notification) {
        LOG.debug("Usage model changed... Resync needed");
        switch (notification.getEventType()) {
        case Notification.REMOVING_ADAPTER:
        case Notification.RESOLVE:
            break;
        case Notification.SET:
            if (UsagemodelPackage.eINSTANCE.getWorkload().isInstance(notification.getNotifier())) {
                syncWorkload(notification);
            } else {
                LOG.error("Usage model changed...But no resync strategy is known. Simulation results most likely are wrong.");
            }
            break;
        default:
            LOG.error("Usage model changed...But no resync strategy is known. Simulation results most likely are wrong.");
            break;
        }
    }

    /**
     * @param notification
     */
    private void syncWorkload(final Notification notification) {
        final Workload workload = (Workload) notification.getNotifier();
        if (notification.getFeature() == UsagemodelPackage.eINSTANCE.getClosedWorkload_Population()) {
            closedWorkloadPopulationChanged(workload, notification.getNewIntValue());
        } else if (notification.getFeature() == UsagemodelPackage.eINSTANCE
                .getOpenWorkload_InterArrivalTime_OpenWorkload()) {
            openWorkloadInterarrivalChange(workload, notification.getNewStringValue());
        } else {
            LOG.error("Usage model changed...But no resync strategy is known. Simulation results most likely are wrong.");
        }
    }

    private void openWorkloadInterarrivalChange(final Workload workload, final String newInterarrivalTime) {
        LOG.debug("Setting open workload interarrival time to " + newInterarrivalTime);
        this.runtimeModel.getUsageModels().getOpenWorkloadDriver((OpenWorkload) workload)
                .setInterarrivalTime(newInterarrivalTime);
    }

    private void closedWorkloadPopulationChanged(final Workload workload, final int newPopulation) {
        LOG.debug("Setting closed workload population to " + newPopulation);
        this.runtimeModel.getUsageModels().getClosedWorkloadDriver((ClosedWorkload) workload)
                .setPopulation(newPopulation);
    }
}

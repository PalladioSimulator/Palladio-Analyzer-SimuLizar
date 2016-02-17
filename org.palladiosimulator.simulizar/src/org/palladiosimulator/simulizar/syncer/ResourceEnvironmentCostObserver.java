package org.palladiosimulator.simulizar.syncer;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.AGGREGATED_COST_OVER_TIME;
import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.COST_OVER_TIME;

import java.util.Arrays;
import java.util.HashMap;

import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.mdsdprofiles.api.StereotypeAPI;
import org.palladiosimulator.mdsdprofiles.notifier.MDSDProfilesNotifier;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;
import org.palladiosimulator.probeframework.probes.EventProbeList;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.probeframework.probes.TriggeredProbe;
import org.palladiosimulator.simulizar.runtimestate.CostModel;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.simulationevents.AggregatedContainerCostProbe;
import org.palladiosimulator.simulizar.simulationevents.ContainerCostProbe;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredContainerEntity;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredCostModelEntity;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;

public class ResourceEnvironmentCostObserver extends AbstractResourceEnvironmentObserver {

    private CostModel costModel;
    private HashMap<String, PeriodicallyTriggeredContainerEntity> periodicallyTriggeredContainerEntities;
    private MonitorRepository monitorRepository;

    public ResourceEnvironmentCostObserver() {
        super();
    }

    private void removeSimulatedResource(final ResourceContainer resourceContainer) {
        final PeriodicallyTriggeredContainerEntity triggeredEntity = this.periodicallyTriggeredContainerEntities
                .get(resourceContainer.getId());
        if (triggeredEntity == null)
            return;
        triggeredEntity.removeEvent();
        this.periodicallyTriggeredContainerEntities.remove(resourceContainer.getId());
    }

    private void initPeriodicCostCalculator(final ResourceContainer resourceContainer) {
        if (!StereotypeAPI.isStereotypeApplied(resourceContainer, "Price")) {
            return;
        }

        this.periodicallyTriggeredContainerEntities.put(resourceContainer.getId(),
                new PeriodicallyTriggeredContainerEntity(this.runtimeModel.getModel(), this.costModel,
                        resourceContainer));
    }

    private void initPeriodicCostModelCalculator() {
        if (!StereotypeAPI.isStereotypeApplied(this.model, "CostReport")) {
            return;
        }
        final double interval = StereotypeAPI.getTaggedValue(this.model, "interval", "CostReport");

        for (final MeasurementSpecification measurementSpecification : MonitorRepositoryUtil
                .getMeasurementSpecificationsForElement(this.monitorRepository, this.model)) {
            final String metricID = measurementSpecification.getMetricDescription().getId();

            if (metricID.equals(COST_OVER_TIME.getId())) {

                final SimuComModel simuComModel = this.runtimeModel.getModel();

                final Probe probe = new EventProbeList(COST_OVER_TIME,
                        new ContainerCostProbe(new PeriodicallyTriggeredCostModelEntity(simuComModel, this.costModel,
                                interval, interval)),
                        Arrays.asList((TriggeredProbe) new TakeCurrentSimulationTimeProbe(
                                simuComModel.getSimulationControl())));

                simuComModel.getProbeFrameworkContext().getCalculatorFactory()
                        .buildCostOverTimeCalculator(measurementSpecification.getMonitor().getMeasuringPoint(), probe);
            }
            if (metricID.equals(AGGREGATED_COST_OVER_TIME.getId())) {
                final SimuComModel simuComModel = this.runtimeModel.getModel();

                final Probe aggregatedProbe = new EventProbeList(AGGREGATED_COST_OVER_TIME,
                        new AggregatedContainerCostProbe(new PeriodicallyTriggeredCostModelEntity(simuComModel,
                                this.costModel, interval, interval)),
                        Arrays.asList((TriggeredProbe) new TakeCurrentSimulationTimeProbe(
                                simuComModel.getSimulationControl())));

                simuComModel.getProbeFrameworkContext().getCalculatorFactory().buildAggregatedCostOverTimeCalculator(
                        measurementSpecification.getMonitor().getMeasuringPoint(), aggregatedProbe);
            }
        }

    }

    @Override
    // Before initializing runtime state, always make sure initialize(T model) has been called
    public void initialize(final SimuLizarRuntimeState runtimeState) {
        super.initialize(runtimeState);
        this.monitorRepository = runtimeState.getModelAccess().getMonitorRepositoryModel();

        this.costModel = new CostModel();
        this.initPeriodicCostModelCalculator();
        this.periodicallyTriggeredContainerEntities = new HashMap<String, PeriodicallyTriggeredContainerEntity>();

        for (final ResourceContainer resourceContainer : this.model.getResourceContainer_ResourceEnvironment()) {
            initPeriodicCostCalculator(resourceContainer);
        }
    }

    @Override
    protected void setTaggedValue(final Notification notification) {
        final MDSDProfilesNotifier.TaggedValueTuple taggedValueTuple = ((MDSDProfilesNotifier.TaggedValueTuple) notification
                .getNewValue());
        if (ResourceenvironmentPackage.eINSTANCE.getResourceContainer().isInstance(notification.getNotifier())
                && taggedValueTuple.getStereotypeName().equals("Price")
                && taggedValueTuple.getTaggedValueName().equals("unit")) {
            // "unit" is the last tagged value expected for a complete specification to
            // initialize a periodic cost calculator.
            initPeriodicCostCalculator((ResourceContainer) notification.getNotifier());
        }
    }

    @Override
    protected void remove(final Notification notification) {
        if (notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                .getResourceEnvironment_ResourceContainer_ResourceEnvironment()) {
            this.removeSimulatedResource((ResourceContainer) notification.getOldValue());
        } else if (notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                .getResourceEnvironment_LinkingResources__ResourceEnvironment()
                || notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                        .getLinkingResource_CommunicationLinkResourceSpecifications_LinkingResource()
                || notification.getFeature() == ResourceenvironmentPackage.eINSTANCE
                        .getLinkingResource_ConnectedResourceContainers_LinkingResource()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ignoring sync (remove) of linking resources");
            }
        } else {
            this.logDebugInfo(notification);
        }
    }

}

package org.palladiosimulator.simulizar.syncer;

import java.util.HashMap;
import java.util.Map;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.commons.emfutils.EMFLoadHelper;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointFactory;
import org.palladiosimulator.edp2.models.measuringpoint.StringMeasuringPoint;
import org.palladiosimulator.edp2.util.MeasuringPointUtility;
import org.palladiosimulator.experimentanalysis.ISlidingWindowListener;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindow.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.experimentanalysis.SlidingWindowUtilizationAggregator;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.recorderframework.IRecorder;
import org.palladiosimulator.recorderframework.config.AbstractRecorderConfiguration;
import org.palladiosimulator.recorderframework.config.IRecorderConfiguration;
import org.palladiosimulator.recorderframework.utils.RecorderExtensionHelper;
import org.palladiosimulator.simulizar.metrics.aggregators.SimulationGovernedSlidingWindow;
import org.palladiosimulator.simulizar.pms.DelayedIntervall;
import org.palladiosimulator.simulizar.pms.Intervall;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.pms.PerformanceMeasurement;
import org.palladiosimulator.simulizar.pms.PerformanceMetricEnum;
import org.palladiosimulator.simulizar.pms.TemporalCharacterization;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.utils.PMSUtil;

import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.resourcetype.SchedulingPolicy;
import de.uka.ipd.sdq.simucomframework.resources.AbstractScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.ScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.SchedulingStrategy;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;

/**
 * Class to sync resource environment model with SimuCom. UGLY DRAFT!
 * 
 * @author Joachim Meyer, Sebastian Lehrig
 */
public class ResourceEnvironmentSyncer extends AbstractSyncer<ResourceEnvironment> implements IModelSyncer {

    private static final Logger LOGGER = Logger.getLogger(ResourceEnvironmentSyncer.class.getName());
    private final PMSModel pms;
    private final PRMModel prm;

    /**
     * Constructor
     * 
     * @param runtimeState
     *            the SimuCom model.
     */
    public ResourceEnvironmentSyncer(final SimuLizarRuntimeState runtimeState) {
        super(runtimeState, runtimeState.getModelAccess().getGlobalPCMModel().getAllocation()
                .getTargetResourceEnvironment_Allocation());
        this.pms = runtimeState.getModelAccess().getPMSModel();
        this.prm = runtimeState.getModelAccess().getPRMModel();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.simulizar.syncer.IModelSyncer#initializeSyncer()
     */
    @Override
    public void initializeSyncer() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Synchronise ResourceContainer and Simulated ResourcesContainer");
        }
        // add resource container, if not done already
        for (final ResourceContainer resourceContainer : model.getResourceContainer_ResourceEnvironment()) {
            final String resourceContainerId = resourceContainer.getId();

            SimulatedResourceContainer simulatedResourceContainer;
            if (runtimeModel.getModel().getResourceRegistry().containsResourceContainer(resourceContainerId)) {
                simulatedResourceContainer = (SimulatedResourceContainer) runtimeModel.getModel().getResourceRegistry()
                        .getResourceContainer(resourceContainerId);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("SimulatedResourceContainer already exists: " + simulatedResourceContainer);
                }
                // now sync active resources
                syncActiveResources(resourceContainer, simulatedResourceContainer);
            } else {
                createSimulatedResource(resourceContainer, resourceContainerId);
            }

        }

        LOGGER.debug("Synchronisation done");
        // TODO remove unused
    }

    /**
     * @param resourceContainer
     * @param resourceContainerId
     */
    private void createSimulatedResource(ResourceContainer resourceContainer, final String resourceContainerId) {
        final AbstractSimulatedResourceContainer simulatedResourceContainer = runtimeModel.getModel()
                .getResourceRegistry().createResourceContainer(resourceContainerId);
        LOGGER.debug("Added SimulatedResourceContainer: ID: " + resourceContainerId + " " + simulatedResourceContainer);
        // now sync active resources
        syncActiveResources(resourceContainer, simulatedResourceContainer);
    }

    @Override
    protected void synchronizeSimulationEntities(final Notification notification) {
        // TODO: Inspect notification and act accordingly
        initializeSyncer();
    }

    /**
     * Checks whether simulated resource (by type id) already exists in given simulated resource
     * container.
     * 
     * @param simulatedResourceContainer
     *            the simulated resource container.
     * @param typeId
     *            id of the resource.
     * @return the ScheduledResource.
     */
    private ScheduledResource resourceAlreadyExist(final AbstractSimulatedResourceContainer simulatedResourceContainer,
            final String typeId) {
        // Resource already exists?
        for (final AbstractScheduledResource abstractScheduledResource : simulatedResourceContainer
                .getActiveResources()) {
            if (abstractScheduledResource.getResourceTypeId().equals(typeId)) {

                return (ScheduledResource) abstractScheduledResource;

            }
        }
        return null;
    }

    /**
     * Sync resources in resource container. If simulated resource already exists in SimuCom,
     * setProcessingRate will be updated.
     * 
     * @param resourceContainer
     *            the resource container.
     * @param simulatedResourceContainer
     *            the corresponding simulated resource container in SimuCom.
     */
    private void syncActiveResources(final ResourceContainer resourceContainer,
            final AbstractSimulatedResourceContainer simulatedResourceContainer) {

        // add resources
        for (final ProcessingResourceSpecification processingResource : resourceContainer
                .getActiveResourceSpecifications_ResourceContainer()) {
            final String typeId = processingResource.getActiveResourceType_ActiveResourceSpecification().getId();
            final String processingRate = processingResource.getProcessingRate_ProcessingResourceSpecification()
                    .getSpecification();
            // processingRate does not need to be evaluated, will be done in
            // simulatedResourceContainers

            // SchedulingStrategy
            final SchedulingPolicy schedulingPolicy = processingResource.getSchedulingPolicy();

            String schedulingStrategy = schedulingPolicy.getId();
            if (schedulingStrategy.equals("ProcessorSharing")) {
                schedulingStrategy = SchedulingStrategy.PROCESSOR_SHARING;
            } else if (schedulingStrategy.equals("FCFS")) {
                schedulingStrategy = SchedulingStrategy.FCFS;
            } else if (schedulingStrategy.equals("Delay")) {
                schedulingStrategy = SchedulingStrategy.DELAY;
            }

            final ScheduledResource scheduledResource = this.resourceAlreadyExist(simulatedResourceContainer, typeId);
            if (existsResource(scheduledResource)) {
                scheduledResource.setProcessingRate(processingRate);
            } else {
                createSimulatedActiveResource(resourceContainer, simulatedResourceContainer, processingResource,
                        schedulingStrategy);
            }
        }
    }

    /**
     * @param scheduledResource
     *            Resource which existence shall be checked
     * @return true if resource exists
     */
    private boolean existsResource(final ScheduledResource scheduledResource) {
        return scheduledResource != null;
    }

    /**
     * @param measurementSpecification
     *            the measurement specification to check
     * @return true if it is monitored
     */
    private boolean isMonitored(final MeasurementSpecification measurementSpecification) {
        return measurementSpecification != null;
    }

    /**
     * 
     * @param resourceContainer
     * @param simulatedResourceContainer
     * @param processingResource
     * @param schedulingStrategy
     */
    private void createSimulatedActiveResource(final ResourceContainer resourceContainer,
            final AbstractSimulatedResourceContainer simulatedResourceContainer,
            final ProcessingResourceSpecification processingResource, String schedulingStrategy) {
        ((SimulatedResourceContainer) simulatedResourceContainer).addActiveResource(
                EMFLoadHelper.getResourceURI(processingResource), new String[] {}, resourceContainer.getId(),
                schedulingStrategy);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Added ActiveResource. TypeID: "
                    + processingResource.getActiveResourceType_ActiveResourceSpecification().getId()
                    + ", Description: " + ", SchedulingStrategy: " + schedulingStrategy);
        }

        MeasurementSpecification measurementSpecification = PMSUtil.isMonitored(pms, processingResource,
                PerformanceMetricEnum.UTILIZATION);

        if (measurementSpecification != null) {
            // get created active resource
            for (final AbstractScheduledResource abstractScheduledResource : simulatedResourceContainer
                    .getActiveResources()) {
                if (abstractScheduledResource.getName().equals(processingResource.getId())) {

                    PerformanceMeasurement pm = (PerformanceMeasurement) measurementSpecification.eContainer();
                    MeasuringPoint mp = pm.getMeasuringPoint();
                    RegisterCalculatorFactoryDecorator actualCalculatorFactory = RegisterCalculatorFactoryDecorator.class
                            .cast(this.runtimeModel.getModel().getProbeFrameworkContext().getCalculatorFactory());

                    Calculator calculator = actualCalculatorFactory.getCalculatorByMeasuringPointAndMetricDescription(
                            mp, MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE);

                    setupUtilizationRecorder(calculator, measurementSpecification);
                    break;
                }

            }
        }
    }

    private Calculator setupUtilizationRecorder(Calculator calculator,
            MeasurementSpecification utilizationMeasurementSpec) {

        TemporalCharacterization temporalRestriction = utilizationMeasurementSpec.getTemporalRestriction();
        Measure<Double, Duration> windowLength = null;
        Measure<Double, Duration> windowIncrement = null;
        if (temporalRestriction instanceof DelayedIntervall) {
            DelayedIntervall interval = (DelayedIntervall) temporalRestriction;
            windowLength = Measure.valueOf(interval.getIntervall(), SI.SECOND);
            windowIncrement = Measure.valueOf(interval.getDelay(), SI.SECOND);

        } else if (temporalRestriction instanceof Intervall) {
            Intervall interval = (Intervall) temporalRestriction;
            windowLength = Measure.valueOf(interval.getIntervall(), SI.SECOND);
            windowIncrement = windowLength;
        } else {
            throw new IllegalStateException(
                    "Temporal characterization for utilization measurement must be either Intervall or DelayedIntervall.");
        }

        Map<String, Object> recorderConfigurationMap = new HashMap<String, Object>();
        recorderConfigurationMap.put(AbstractRecorderConfiguration.RECORDER_ACCEPTED_METRIC,
                MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE);
        recorderConfigurationMap.put(AbstractRecorderConfiguration.MEASURING_POINT,
                createMeasuringPointFromCalculator(calculator));

        IRecorder baseRecorder = createBaseRecorder(recorderConfigurationMap);

        SimulationGovernedSlidingWindow window = createUtilizationSlidingWindow(windowLength, windowIncrement);

        org.palladiosimulator.experimentanalysis.ISlidingWindowListener aggregator = new SlidingWindowUtilizationAggregator(
                baseRecorder);
        SlidingWindowRecorder windowRecorder = createSlidingWindowRecorder(window, aggregator);
        // register recorder at calculator
        calculator.addObserver(windowRecorder);
        return calculator;
    }

    private SimulationGovernedSlidingWindow createUtilizationSlidingWindow(Measure<Double, Duration> windowLength,
            Measure<Double, Duration> windowIncrement) {
        ISlidingWindowMoveOnStrategy strategy = new KeepLastElementPriorToLowerBoundStrategy();

        return new SimulationGovernedSlidingWindow(windowLength, windowIncrement,
                MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE, strategy,
                this.runtimeModel.getModel());

    }

    private static MeasuringPoint createMeasuringPointFromCalculator(Calculator calc) {
        assert calc != null;

        StringMeasuringPoint result = MeasuringpointFactory.eINSTANCE.createStringMeasuringPoint();
        result.setMeasuringPoint(MeasuringPointUtility.measuringPointToString(calc.getMeasuringPoint()));

        return result;
    }

    private IRecorder createBaseRecorder(Map<String, Object> recorderConfigurationMap) {

        IRecorder result = RecorderExtensionHelper.instantiateRecorderImplementationForRecorder(this.runtimeModel
                .getModel().getConfiguration().getRecorderName());
        result.initialize(retrieveRecorderConfiguration(recorderConfigurationMap));
        return result;
    }

    private IRecorderConfiguration retrieveRecorderConfiguration(Map<String, Object> recorderConfigurationMap) {
        return this.runtimeModel.getModel().getConfiguration().getRecorderConfigurationFactory()
                .createRecorderConfiguration(recorderConfigurationMap);
    }

    private SlidingWindowRecorder createSlidingWindowRecorder(SimulationGovernedSlidingWindow window,
            ISlidingWindowListener windowListener) {

        SlidingWindowRecorder result = new SlidingWindowRecorder(window, windowListener);

        return result;
    }

}

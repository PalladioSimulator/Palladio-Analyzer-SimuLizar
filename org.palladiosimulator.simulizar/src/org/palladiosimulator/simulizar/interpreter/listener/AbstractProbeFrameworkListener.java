package org.palladiosimulator.simulizar.interpreter.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.PredicateUtils;
import org.apache.commons.collections15.Transformer;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.Monitor;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.StatisticalCharacterizationEnum;
import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.ICalculatorFactory;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.probeframework.probes.TriggeredProbe;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.metrics.aggregators.ResponseTimeAggregator;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;

/**
 * Class for listening to interpreter events in order to store collected data using the
 * ProbeFramework
 *
 * @author Steffen Becker, Sebastian Lehrig, Florian Rosenthal
 */
public abstract class AbstractProbeFrameworkListener extends AbstractInterpreterListener {

    private static final Logger LOGGER = Logger.getLogger(AbstractProbeFrameworkListener.class);
    private static final int START_PROBE_INDEX = 0;
    private static final int STOP_PROBE_INDEX = 1;

    protected final SimuComModel simuComModel;
    protected final ICalculatorFactory calculatorFactory;
    protected final Reconfigurator reconfigurator;
    private final IModelAccess modelAccess;

    private final Map<String, List<TriggeredProbe>> currentTimeProbes = new HashMap<String, List<TriggeredProbe>>();

    /**
     * @param modelAccessFactory
     *            Provides access to simulated models
     * @param simuComModel
     *            Provides access to the central simulation
     */
    public AbstractProbeFrameworkListener(final IModelAccess modelAccess, final SimuComModel simuComModel,
            final Reconfigurator reconfigurator) {
        super();
        this.modelAccess = modelAccess;
        this.calculatorFactory = simuComModel.getProbeFrameworkContext().getCalculatorFactory();
        this.simuComModel = simuComModel;
        this.reconfigurator = reconfigurator;

        this.initResponseTimeMeasurement();
        this.initReconfigurationTimeMeasurement();
        this.initExtensionMeasurements();
    }

    private void initExtensionMeasurements() {
        final Iterable<AbstractRecordingProbeFrameworkListenerDecorator> extensions = ExtensionHelper
                .getExecutableExtensions("org.palladiosimulator.simulizar.interpreter.listener.probeframework",
                        "decorator");
        for (final AbstractRecordingProbeFrameworkListenerDecorator decorator : extensions) {
            decorator.setProbeFrameworkListener(this);
            decorator.registerMeasurements();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#
     * beginUsageScenarioInterpretation
     * (de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
     */
    @Override
    public void beginUsageScenarioInterpretation(final ModelElementPassedEvent<UsageScenario> event) {
        this.startMeasurement(event);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#
     * endUsageScenarioInterpretation
     * (de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
     */
    @Override
    public void endUsageScenarioInterpretation(final ModelElementPassedEvent<UsageScenario> event) {
        this.endMeasurement(event);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#
     * beginEntryLevelSystemCallInterpretation
     * (de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
     */
    @Override
    public void beginEntryLevelSystemCallInterpretation(final ModelElementPassedEvent<EntryLevelSystemCall> event) {
        this.startMeasurement(event);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#
     * endEntryLevelSystemCallInterpretation
     * (de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
     */
    @Override
    public void endEntryLevelSystemCallInterpretation(final ModelElementPassedEvent<EntryLevelSystemCall> event) {
        this.endMeasurement(event);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.upb.pcm.simulizar.interpreter.listener.AbstractInterpreterListener#
     * beginExternalCallInterpretation
     * (de.upb.pcm.simulizar.interpreter.listener.ModelElementPassedEvent)
     */
    @Override
    public void beginExternalCallInterpretation(final RDSEFFElementPassedEvent<ExternalCallAction> event) {
        this.startMeasurement(event);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.upb.pcm.simulizar.interpreter.listener.AbstractInterpreterListener#
     * endExternalCallInterpretation
     * (de.upb.pcm.simulizar.interpreter.listener.ModelElementPassedEvent)
     */
    @Override
    public void endExternalCallInterpretation(final RDSEFFElementPassedEvent<ExternalCallAction> event) {
        this.endMeasurement(event);
    }

    @Override
    public <T extends EObject> void beginUnknownElementInterpretation(final ModelElementPassedEvent<T> event) {
    }

    @Override
    public <T extends EObject> void endUnknownElementInterpretation(final ModelElementPassedEvent<T> event) {
    }

    /**
     * Gets the {@link SimuComModel} which is related to this instance.
     *
     * @return A reference to the {@link SimuComModel} currently in use.
     */
    public SimuComModel getSimuComModel() {
        return this.simuComModel;
    }

    /**
     * Gets the {@link IModelAccess} attached to this instance.
     * 
     * @return A reference to the {@code IModelAccess}.
     */
    public IModelAccess getModelAccess() {
        return this.modelAccess;
    }

    /**
     * Gets the {@link RuntimeMeasurementModel} attached to this instance.
     *
     * @return A reference to the {@code RuntimeMeasurementModel}.
     */
    public RuntimeMeasurementModel getRuntimeMeasurementModel() {
        return this.modelAccess.getRuntimeMeasurementModel();
    }

    /**
     * Gets the {@link ICalculatorFactory} that is by this instance during the current simulation
     * run.
     *
     * @return A reference to the {@code ICalculatorFactory}.
     */
    public ICalculatorFactory getCalculatorFactory() {
        return this.calculatorFactory;
    }

    /**
     * Gets all associated {@link MeasurementSpecification}s that adhere to the given metric.
     *
     * @param soughtFor
     *            A {@link MetricDescription} denoting the target metric to look for.
     * @return An UNMODIFIABLE {@link Collection} containing all found measurement Specifications,
     *         which might be empty but never {@code null}.
     */
    public Collection<MeasurementSpecification> getMeasurementSpecificationsForMetricDescription(
            final MetricDescription soughtFor) {
        assert soughtFor != null;
        final MonitorRepository monitorRepositoryModel = this.modelAccess.getMonitorRepositoryModel();
        if (monitorRepositoryModel != null) {
            final Transformer<Monitor, MeasurementSpecification> transformer = new Transformer<Monitor, MeasurementSpecification>() {

                @Override
                public MeasurementSpecification transform(final Monitor monitor) {
                    for (final MeasurementSpecification m : monitor.getMeasurementSpecifications()) {
                        if (monitor.isActivated() && MetricDescriptionUtility
                                .metricDescriptionIdsEqual(m.getMetricDescription(), soughtFor)) {
                            return m;
                        }
                    }
                    return null;
                }
            };
            return Collections.unmodifiableCollection(
                    CollectionUtils.select(CollectionUtils.collect(monitorRepositoryModel.getMonitors(), transformer),
                            PredicateUtils.notNullPredicate()));
        }
        return Collections.emptyList();
    }

    /**
     * Initializes the <i>response time</i> measurements. First gets the monitored elements from the
     * monitor repository, then creates corresponding calculators and aggregators.
     *
     */
    private void initResponseTimeMeasurement() {
        for (final MeasurementSpecification responseTimeMeasurementSpec : this
                .getMeasurementSpecificationsForMetricDescription(MetricDescriptionConstants.RESPONSE_TIME_METRIC)) {
            if (responseTimeMeasurementSpec.getMonitor().isActivated()) {
                final MeasuringPoint measuringPoint = responseTimeMeasurementSpec.getMonitor().getMeasuringPoint();
                final List<Probe> probeList = this.createStartAndStopProbe(measuringPoint, this.simuComModel);
                final Calculator calculator = this.calculatorFactory.buildResponseTimeCalculator(measuringPoint,
                        probeList);

                if (responseTimeMeasurementSpec.getStatisticalCharacterization() != StatisticalCharacterizationEnum.NONE
                        && responseTimeMeasurementSpec.isTriggersSelfAdaptations()) {
                    try {
                        final IMeasurementSourceListener aggregator = new ResponseTimeAggregator(this.simuComModel,
                                this.getRuntimeMeasurementModel(), responseTimeMeasurementSpec, measuringPoint);
                        calculator.addObserver(aggregator);
                    } catch (final UnsupportedOperationException e) {
                        LOGGER.error(e);
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    /**
     * @param measuringPoint
     * @param simuComModel
     * @return list with start and stop probe
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected List<Probe> createStartAndStopProbe(final MeasuringPoint measuringPoint,
            final SimuComModel simuComModel) {
        final List probeList = new ArrayList<TriggeredProbe>(2);
        probeList.add(new TakeCurrentSimulationTimeProbe(simuComModel.getSimulationControl()));
        probeList.add(new TakeCurrentSimulationTimeProbe(simuComModel.getSimulationControl()));
        final EObject modelElement = MonitorRepositoryUtil.getMonitoredElement(measuringPoint);
        this.currentTimeProbes.put(((Entity) modelElement).getId(), Collections.unmodifiableList(probeList));
        return probeList;
    }

    /**
     * @param modelElement
     * @return
     */
    protected boolean entityIsAlreadyInstrumented(final EObject modelElement) {
        return this.currentTimeProbes.containsKey(((Entity) modelElement).getId());
    }

    /**
     * @param <T>
     * @param event
     */
    private <T extends Entity> void startMeasurement(final ModelElementPassedEvent<T> event) {
        if (this.currentTimeProbes.containsKey(((Entity) event.getModelElement()).getId())
                && this.simulationIsRunning()) {
            this.currentTimeProbes.get(((Entity) event.getModelElement()).getId()).get(START_PROBE_INDEX)
                    .takeMeasurement(event.getThread().getRequestContext());
        }
    }

    /**
     * @param event
     */
    private <T extends Entity> void endMeasurement(final ModelElementPassedEvent<T> event) {
        if (this.currentTimeProbes.containsKey(((Entity) event.getModelElement()).getId())
                && this.simulationIsRunning()) {
            this.currentTimeProbes.get(((Entity) event.getModelElement()).getId()).get(STOP_PROBE_INDEX)
                    .takeMeasurement(event.getThread().getRequestContext());
        }
    }

    @Override
    public void beginSystemOperationCallInterpretation(final ModelElementPassedEvent<OperationSignature> event) {
        if (this.currentTimeProbes.containsKey(((Entity) event.getModelElement()).getId())
                && this.simulationIsRunning()) {
            this.currentTimeProbes.get(((Entity) event.getModelElement()).getId()).get(START_PROBE_INDEX)
                    .takeMeasurement(event.getThread().getRequestContext());
        }
    }

    @Override
    public void endSystemOperationCallInterpretation(final ModelElementPassedEvent<OperationSignature> event) {
        if (this.currentTimeProbes.containsKey(((Entity) event.getModelElement()).getId())
                && this.simulationIsRunning()) {
            this.currentTimeProbes.get(((Entity) event.getModelElement()).getId()).get(STOP_PROBE_INDEX)
                    .takeMeasurement(event.getThread().getRequestContext());
        }
    }

    /**
     * Initializes reconfiguration time measurement.
     */
    protected abstract void initReconfigurationTimeMeasurement();

    private boolean simulationIsRunning() {
        return this.simuComModel.getSimulationControl().isRunning();
    }
}

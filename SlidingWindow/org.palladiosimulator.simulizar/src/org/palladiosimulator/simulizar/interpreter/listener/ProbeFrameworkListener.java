package org.palladiosimulator.simulizar.interpreter.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.quantity.Power;
import javax.measure.quantity.Quantity;
import javax.measure.unit.SI;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.NotNullPredicate;
import org.apache.commons.collections15.functors.TransformedPredicate;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.jscience.physics.amount.Amount;
import org.palladiosimulator.commons.emfutils.EMFLoadHelper;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointFactory;
import org.palladiosimulator.edp2.models.measuringpoint.ResourceURIMeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.StringMeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.util.MeasuringpointSwitch;
import org.palladiosimulator.edp2.util.MeasuringPointUtility;
import org.palladiosimulator.experimentanalysis.ISlidingWindowListener;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.experimentanalysis.SlidingWindowUtilizationAggregator;
import org.palladiosimulator.experimentanalysis.SlidingWindow.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.measurementframework.Measurement;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.AssemblyOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointFactory;
import org.palladiosimulator.pcmmeasuringpoint.SystemOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.UsageScenarioMeasuringPoint;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.ICalculatorFactory;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.probeframework.probes.TriggeredProbe;
import org.palladiosimulator.recorderframework.IRecorder;
import org.palladiosimulator.recorderframework.config.AbstractRecorderConfiguration;
import org.palladiosimulator.recorderframework.config.IRecorderConfiguration;
import org.palladiosimulator.recorderframework.utils.RecorderExtensionHelper;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.metrics.aggregators.ResponseTimeAggregator;
import org.palladiosimulator.simulizar.metrics.aggregators.SimulationGovernedSlidingWindow;
import org.palladiosimulator.simulizar.metrics.powerconsumption.SimulationTimeEvaluationScope;
import org.palladiosimulator.simulizar.pms.DelayedIntervall;
import org.palladiosimulator.simulizar.pms.Intervall;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.pms.PerformanceMeasurement;
import org.palladiosimulator.simulizar.pms.PerformanceMetricEnum;
import org.palladiosimulator.simulizar.pms.TemporalCharacterization;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.utils.PMSUtil;

import de.fzi.power.infrastructure.PowerProvidingEntity;
import de.fzi.power.infrastructure.util.InfrastructureSwitch;
import de.fzi.power.interpreter.ConsumptionContext;
import de.fzi.power.interpreter.PowerConsumptionSwitch;
import de.fzi.power.interpreter.PowerModelRegistry;
import de.fzi.power.interpreter.PowerModelUpdaterSwitch;
import de.fzi.power.interpreter.calculators.CalculatorInstantiator;
import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.core.entity.InterfaceProvidingEntity;
import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simulation.ISimulationListener;

/**
 * Class for listening to interpreter events in order to store collected data using the
 * ProbeFramework
 * 
 * @author Steffen Becker, Sebastian Lehrig, Florian Rosenthal
 */
public class ProbeFrameworkListener extends AbstractInterpreterListener {

    private static final Logger LOGGER = Logger.getLogger(ProbeFrameworkListener.class);
    private static final int START_PROBE_INDEX = 0;
    private static final int STOP_PROBE_INDEX = 1;

    private final PMSModel pmsModel;
    private final PRMModel prmModel;
    private final SimuComModel simuComModel;
    private final ICalculatorFactory calculatorFactory;

    private final Map<String, List<TriggeredProbe>> currentTimeProbes = new HashMap<String, List<TriggeredProbe>>();
    private TriggeredProbe reconfTimeProbe;

    /** Default EMF factory for measuring points. */
    private final MeasuringpointFactory measuringpointFactory = MeasuringpointFactory.eINSTANCE;
    private final PcmmeasuringpointFactory pcmMeasuringpointFactory = PcmmeasuringpointFactory.eINSTANCE;

    /**
     * @param modelAccessFactory
     *            Provides access to simulated models
     * @param simuComModel
     *            Provides access to the central simulation
     */
    public ProbeFrameworkListener(final IModelAccess modelAccessFactory, final SimuComModel simuComModel) {
        super();
        this.pmsModel = modelAccessFactory.getPMSModel();
        this.prmModel = modelAccessFactory.getPRMModel();
        this.calculatorFactory = simuComModel.getProbeFrameworkContext().getCalculatorFactory();
        this.simuComModel = simuComModel;
        this.reconfTimeProbe = null;

        initReponseTimeMeasurement();
        initUntilizationMeasurements();
        initPowerMeasurements();
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
    public <T extends EObject> void beginUnknownElementInterpretation(ModelElementPassedEvent<T> event) {
    }

    @Override
    public <T extends EObject> void endUnknownElementInterpretation(ModelElementPassedEvent<T> event) {
    }

    private Collection<MeasurementSpecification> getMeasurementSpecificationsForPerformanceMetric(
            final PerformanceMetricEnum metric) {
        if (this.pmsModel != null) {

            Transformer<PerformanceMeasurement, MeasurementSpecification> transformer = new Transformer<PerformanceMeasurement, MeasurementSpecification>() {

                @Override
                public MeasurementSpecification transform(PerformanceMeasurement arg0) {
                    for (MeasurementSpecification m : arg0.getMeasurementSpecification()) {
                        if (m.getPerformanceMetric() == metric)
                            return m;
                    }
                    return null;
                }
            };

            return CollectionUtils.select(
                    CollectionUtils.collect(this.pmsModel.getPerformanceMeasurements(), transformer),
                    NotNullPredicate.getInstance());

        }
        return Collections.emptyList();
    }

    private static final MeasuringpointSwitch<PowerProvidingEntity> MEASURING_POINT_SWITCH = new MeasuringpointSwitch<PowerProvidingEntity>() {

        private final InfrastructureSwitch<PowerProvidingEntity> infSwitch = new InfrastructureSwitch<PowerProvidingEntity>() {
            public PowerProvidingEntity casePowerProvidingEntity(PowerProvidingEntity ppe) {
                return ppe;
            }
        };

        public PowerProvidingEntity caseResourceURIMeasuringPoint(ResourceURIMeasuringPoint mp) {
            EObject modeObject = EMFLoadHelper.loadModel(mp.getResourceURI());
            return infSwitch.doSwitch(modeObject);
        }
    };

    // TODO draft
    //
    private void initPowerMeasurements() {
        Collection<MeasurementSpecification> powerMeasurementSpecs = getMeasurementSpecificationsForPerformanceMetric(PerformanceMetricEnum.POWER_CONSUMPTION);
        if (!powerMeasurementSpecs.isEmpty()) {
            Map<String, Object> recorderConfigurationMap = new HashMap<String, Object>();
            recorderConfigurationMap.put(AbstractRecorderConfiguration.RECORDER_ACCEPTED_METRIC,
                    MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE);

            for (MeasurementSpecification powerMeasurementSpec : powerMeasurementSpecs) {
                MeasuringPoint mp = ((PerformanceMeasurement) powerMeasurementSpec.eContainer()).getMeasuringPoint();
                final PowerProvidingEntity ppe = MEASURING_POINT_SWITCH.doSwitch(mp);
                final SimulationTimeEvaluationScope scope = new SimulationTimeEvaluationScope(ppe,
                        powerMeasurementSpec, this.simuComModel);
                scope.initialize();

                recorderConfigurationMap.put(AbstractRecorderConfiguration.MEASURING_POINT, mp);

                final IRecorder powerDataRecorder = RecorderExtensionHelper
                        .instantiateRecorderImplementationForRecorder(this.simuComModel.getConfiguration()
                                .getRecorderName());
                powerDataRecorder.initialize(this.simuComModel.getConfiguration().getRecorderConfigurationFactory()
                        .createRecorderConfiguration(recorderConfigurationMap));

                this.simuComModel.getConfiguration().addListener(new ISimulationListener() {

                    //TODO make power data available during simulation runs
                    //use listener?
                    @Override
                    public void simulationStop() {
                        PowerModelRegistry reg = new PowerModelRegistry();
                        ConsumptionContext context = ConsumptionContext.createConsumptionContext(ppe
                                .getDistributionPowerAssemblyContext().getPowerBindingRepository(), scope, reg);
                        PowerConsumptionSwitch powerSwitch = PowerConsumptionSwitch
                                .createPowerConsumptionSwitch(context);
                        new PowerModelUpdaterSwitch(reg, new CalculatorInstantiator()).doSwitch(ppe);

                        scope.reset();
                        for (Map<?, Measurement> m : scope) {
                            scope.next();
                            Amount<Power> powerAmount = powerSwitch.doSwitch(ppe);
                            Measure<Double, ? extends Quantity> powerMeasure = Measure.valueOf(
                                    powerAmount.doubleValue(SI.WATT), SI.WATT);
                            powerDataRecorder.writeData(new TupleMeasurement(
                                    MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE, m.values().iterator().next()
                                            .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC),
                                    powerMeasure));
                        }
                    }

                    @Override
                    public void simulationStart() {
                        // don't do nothing here
                    }
                });
            }
        }
    }

   
    private void initUntilizationMeasurements() {

        Collection<MeasurementSpecification> utilMeasurementSpecs = getMeasurementSpecificationsForPerformanceMetric(PerformanceMetricEnum.UTILIZATION);
        if (!utilMeasurementSpecs.isEmpty()) {
            RegisterCalculatorFactoryDecorator calculatorFactory = RegisterCalculatorFactoryDecorator.class
                    .cast(this.calculatorFactory);

            for (MeasurementSpecification spec : utilMeasurementSpecs) {
                MeasuringPoint mp = ((PerformanceMeasurement) spec.eContainer()).getMeasuringPoint();

                Calculator calculator = calculatorFactory.getCalculatorByMeasuringPointAndMetricDescription(mp,
                        MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE);

                setupUtilizationRecorder(calculator, spec);
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
                MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE, strategy, this.simuComModel);

    }

    private static MeasuringPoint createMeasuringPointFromCalculator(Calculator calc) {
        assert calc != null;

        StringMeasuringPoint result = MeasuringpointFactory.eINSTANCE.createStringMeasuringPoint();
        result.setMeasuringPoint(MeasuringPointUtility.measuringPointToString(calc.getMeasuringPoint()));

        return result;
    }

    private IRecorder createBaseRecorder(Map<String, Object> recorderConfigurationMap) {

        IRecorder result = RecorderExtensionHelper.instantiateRecorderImplementationForRecorder(this.simuComModel
                .getConfiguration().getRecorderName());
        result.initialize(retrieveRecorderConfiguration(recorderConfigurationMap));
        return result;
    }

    private IRecorderConfiguration retrieveRecorderConfiguration(Map<String, Object> recorderConfigurationMap) {
        return this.simuComModel.getConfiguration().getRecorderConfigurationFactory()
                .createRecorderConfiguration(recorderConfigurationMap);
    }

    private SlidingWindowRecorder createSlidingWindowRecorder(SimulationGovernedSlidingWindow window,
            ISlidingWindowListener windowListener) {

        SlidingWindowRecorder result = new SlidingWindowRecorder(window, windowListener);

        return result;
    }

    //
    // end draft
    /**
     * Initialize the response time measurements. First gets the monitored elements from the PMS
     * model, create according calculators, and aggregators.
     * 
     */
    private void initReponseTimeMeasurement() {

        for (MeasurementSpecification responseTimeMeasurementSpec : getMeasurementSpecificationsForPerformanceMetric(PerformanceMetricEnum.RESPONSE_TIME)) {
            MeasuringPoint measuringPoint = ((PerformanceMeasurement) responseTimeMeasurementSpec.eContainer())
                    .getMeasuringPoint();
            EObject modelElement = PMSUtil.getMonitoredElement(measuringPoint);

            List<Probe> probeList = createStartAndStopProbe(modelElement, this.simuComModel);
            Calculator calculator = this.calculatorFactory.buildResponseTimeCalculator(measuringPoint, probeList);

            try {
                IMeasurementSourceListener aggregator = new ResponseTimeAggregator(this.simuComModel, this.prmModel,
                        responseTimeMeasurementSpec, modelElement);
                calculator.addObserver(aggregator);
            } catch (final UnsupportedOperationException e) {
                LOGGER.error(e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * FIXME This method is not required anymore IF all types measuring points can be modeled
     * 
     * @param modelElement
     *            for which a MeasuringPoint shall be created
     * @param <T>
     *            extends Entity
     * @return MeasuringPoint for modelElement
     */
    private <T extends Entity> MeasuringPoint createMeasuringPoint(EObject modelElement) {

        MeasuringPoint result;
        if (modelElement == null) {
            throw new IllegalArgumentException("ModelElementPassedEvent cannot be null");
        } else if (modelElement instanceof ResourceContainer) {
            final ResourceContainer resourceContainer = (ResourceContainer) modelElement;

            // FIXME Always takes the first active resource of a given container. That should be
            // more flexible. [Lehrig]
            final ActiveResourceMeasuringPoint mp = this.pcmMeasuringpointFactory.createActiveResourceMeasuringPoint();
            mp.setActiveResource(resourceContainer.getActiveResourceSpecifications_ResourceContainer().get(0));
            mp.setReplicaID(0);
            result = mp;
        } else if (modelElement instanceof ExternalCallAction) {
            final ExternalCallAction externalCallAction = (ExternalCallAction) modelElement;

            AssemblyOperationMeasuringPoint mp = this.pcmMeasuringpointFactory.createAssemblyOperationMeasuringPoint();
            // FIXME How can I get the AssemblyContext from the monitored Element in advanced
            // (before running the simulation)
            // mp.setAssembly(((RDSEFFElementPassedEvent<ExternalCallAction>)
            // event).getAssemblyContext());
            mp.setOperationSignature(externalCallAction.getCalledService_ExternalService());
            mp.setRole(externalCallAction.getRole_ExternalService());
            result = mp;
        } else if (modelElement instanceof EntryLevelSystemCall) {
            final EntryLevelSystemCall entryLevelSystemCall = (EntryLevelSystemCall) modelElement;

            final SystemOperationMeasuringPoint mp = this.pcmMeasuringpointFactory
                    .createSystemOperationMeasuringPoint();
            final InterfaceProvidingEntity providingEntity = entryLevelSystemCall
                    .getProvidedRole_EntryLevelSystemCall().getProvidingEntity_ProvidedRole();
            if (providingEntity instanceof de.uka.ipd.sdq.pcm.system.System) {
                de.uka.ipd.sdq.pcm.system.System system = (de.uka.ipd.sdq.pcm.system.System) providingEntity;
                mp.setSystem(system);
            } else {
                throw new IllegalArgumentException("EntryLevelSystemCall \"" + entryLevelSystemCall.getEntityName()
                        + "\" does not reference a system.");
            }
            mp.setOperationSignature(entryLevelSystemCall.getOperationSignature__EntryLevelSystemCall());
            mp.setRole(entryLevelSystemCall.getProvidedRole_EntryLevelSystemCall());
            result = mp;
        } else if (modelElement instanceof UsageScenario) {
            final UsageScenario usageScenario = (UsageScenario) modelElement;

            final UsageScenarioMeasuringPoint mp = this.pcmMeasuringpointFactory.createUsageScenarioMeasuringPoint();
            mp.setUsageScenario(usageScenario);
            result = mp;
        } else {
            throw new IllegalArgumentException("Unknown model element  (" + modelElement.toString() + ")");
        }
        return result;
    }

    /**
     * @param modelElement
     * @param simuComModel
     * @return list with start and stop probe
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected List<Probe> createStartAndStopProbe(final EObject modelElement, final SimuComModel simuComModel) {
        final List probeList = new ArrayList<TriggeredProbe>(2);
        probeList.add(new TakeCurrentSimulationTimeProbe(simuComModel.getSimulationControl()));
        probeList.add(new TakeCurrentSimulationTimeProbe(simuComModel.getSimulationControl()));
        currentTimeProbes.put(((Entity) modelElement).getId(), Collections.unmodifiableList(probeList));
        return probeList;
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
        if (this.currentTimeProbes.containsKey(((Entity) event.getModelElement()).getId()) && simulationIsRunning()) {
            this.currentTimeProbes.get(((Entity) event.getModelElement()).getId()).get(START_PROBE_INDEX)
                    .takeMeasurement(event.getThread().getRequestContext());
        }
    }

    /**
     * @param event
     */
    private <T extends Entity> void endMeasurement(final ModelElementPassedEvent<T> event) {
        if (this.currentTimeProbes.containsKey(((Entity) event.getModelElement()).getId()) && simulationIsRunning()) {
            this.currentTimeProbes.get(((Entity) event.getModelElement()).getId()).get(STOP_PROBE_INDEX)
                    .takeMeasurement(event.getThread().getRequestContext());
        }
    }

    @Override
    public void reconfigurationInterpretation(final ReconfigurationEvent event) {
        if (this.reconfTimeProbe == null) {
            initReconfTimeMeasurement(event);
        }

        this.reconfTimeProbe.takeMeasurement();
    }

    /**
     * Initializes reconfiguration time measurement.
     * 
     * TODO StringMeasuringPoint should not be used by SimuLizar. Create something better! I could
     * imagine an EDP2 extension that introduces dedicated reconfiguration measuring points.
     * [Lehrig]
     * 
     * FIXME Dead code; no measurements taken here! Needs some more refactorings. [Lehrig]
     * 
     * @param event
     *            which was fired
     * @param <T>
     *            extends Entity
     */
    private <T extends Entity> void initReconfTimeMeasurement(final ReconfigurationEvent event) {
        this.reconfTimeProbe = new TakeCurrentSimulationTimeProbe(this.simuComModel.getSimulationControl());

        final StringMeasuringPoint measuringPoint = measuringpointFactory.createStringMeasuringPoint();
        measuringPoint.setMeasuringPoint("Reconfiguration");
        // this.calculatorFactory.buildStateOfActiveResourceCalculator(measuringPoint,
        // this.reconfTimeProbe);
    }

    private Boolean simulationIsRunning() {
        return this.simuComModel.getSimulationControl().isRunning();
    }
}

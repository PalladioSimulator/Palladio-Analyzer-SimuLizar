package org.palladiosimulator.simulizar.interpreter.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.PredicateUtils;
import org.apache.commons.collections15.Transformer;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointFactory;
import org.palladiosimulator.edp2.models.measuringpoint.StringMeasuringPoint;
import org.palladiosimulator.edp2.util.MeasuringPointUtility;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindow.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.experimentanalysis.SlidingWindowUtilizationAggregator;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
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
import org.palladiosimulator.recorderframework.utils.RecorderExtensionHelper;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.metrics.aggregators.ResponseTimeAggregator;
import org.palladiosimulator.simulizar.metrics.aggregators.SimulationGovernedSlidingWindow;
import org.palladiosimulator.simulizar.metrics.powerconsumption.PowerConsumptionPrmRecorder;
import org.palladiosimulator.simulizar.metrics.powerconsumption.SimulationTimeEnergyConsumptionCalculator;
import org.palladiosimulator.simulizar.metrics.powerconsumption.SimulationTimeEvaluationScope;
import org.palladiosimulator.simulizar.metrics.powerconsumption.SimulationTimePowerConsumptionCalculator;
import org.palladiosimulator.simulizar.pms.DelayedIntervall;
import org.palladiosimulator.simulizar.pms.Intervall;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.pms.PerformanceMeasurement;
import org.palladiosimulator.simulizar.pms.PerformanceMetricEnum;
import org.palladiosimulator.simulizar.pms.util.PmsSwitch;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.utils.PMSUtil;

import de.fzi.power.infrastructure.PowerProvidingEntity;
import de.fzi.power.interpreter.ConsumptionContext;
import de.fzi.power.interpreter.InterpreterUtils;
import de.fzi.power.interpreter.PowerModelRegistry;
import de.fzi.power.interpreter.PowerModelUpdaterSwitch;
import de.fzi.power.interpreter.calculators.ExtensibleCalculatorInstantiatorImpl;
import de.fzi.power.interpreter.calculators.energy.AbstractCumulativeEnergyCalculator;
import de.fzi.power.interpreter.calculators.energy.SimpsonRuleCumulativeEnergyCalculator;
import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.core.entity.InterfaceProvidingEntity;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.simucomframework.SimuComConfig;
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
    private static final MetricSetDescription POWER_CONSUMPTION_TUPLE_METRIC_DESC =
            MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE;
    private static final MetricSetDescription ENERGY_CONSUMPTION_TUPLE_METRIC_DESC =
            MetricDescriptionConstants.CUMULATIVE_ENERGY_CONSUMPTION_TUPLE;

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

            Transformer<PerformanceMeasurement, MeasurementSpecification> transformer =
                    new Transformer<PerformanceMeasurement, MeasurementSpecification>() {

                @Override
                public MeasurementSpecification transform(PerformanceMeasurement performanceMeasurement) {
                    for (MeasurementSpecification m : performanceMeasurement.getMeasurementSpecification()) {
                        if (m.getPerformanceMetric() == metric) {
                            return m;
                        }
                    }
                    return null;
                }
            };
            return CollectionUtils.select(
                    CollectionUtils.collect(this.pmsModel.getPerformanceMeasurements(), transformer),
                    PredicateUtils.notNullPredicate());

        }
        return Collections.emptyList();
    }

    /**
     * returns a two-element array: sliding window length is returned at index 0, window increment
     * at index 1
     */
    private static final PmsSwitch<Measure<Double, Duration>[]> WINDOW_PROPERTIES_SWITCH =
            new PmsSwitch<Measure<Double, Duration>[]>() {

        @Override
        public Measure<Double, Duration>[] caseDelayedIntervall(DelayedIntervall interval) {
            @SuppressWarnings("unchecked")
            Measure<Double, Duration>[] result = (Measure<Double, Duration>[]) new Measure<?, ?>[2];
            result[0] = Measure.valueOf(interval.getIntervall(), SI.SECOND);
            result[1] = Measure.valueOf(interval.getDelay(), SI.SECOND);
            return result;
        }

        @Override
        public Measure<Double, Duration>[] caseIntervall(Intervall interval) {
            @SuppressWarnings("unchecked")
            Measure<Double, Duration>[] result = (Measure<Double, Duration>[]) new Measure<?, ?>[2];
            result[0] = Measure.valueOf(interval.getIntervall(), SI.SECOND);
            result[1] = result[0];

            return result;
        }

        @Override
        public Measure<Double, Duration>[] defaultCase(EObject obj) {
            throw new IllegalStateException(
                    "Temporal characterization for utilization or measurement must be either Intervall or DelayedIntervall.");
        }
    };

    private static Map<String, Object> createRecorderConfigMapWithAcceptedMetric(
            MetricDescription recorderAcceptedMetric) {
        assert recorderAcceptedMetric != null;

        Map<String, Object> result = new HashMap<>();
        result.put(AbstractRecorderConfiguration.RECORDER_ACCEPTED_METRIC, recorderAcceptedMetric);
        return result;
    }

    private IRecorder initializeRecorder(Map<String, Object> recorderConfigMap) {
        assert recorderConfigMap != null;

        SimuComConfig config = this.simuComModel.getConfiguration();
        IRecorder recorder = RecorderExtensionHelper.instantiateRecorderImplementationForRecorder(config
                .getRecorderName());
        recorder.initialize(config.getRecorderConfigurationFactory().createRecorderConfiguration(recorderConfigMap));

        return recorder;
    }

   
    private static MeasuringPoint createStringMeasuringPointFromMeasuringPoint(MeasuringPoint mp, String nameSuffix) {
        assert mp != null && nameSuffix != null;
        
        StringMeasuringPoint result = MeasuringpointFactory.eINSTANCE.createStringMeasuringPoint();
        String mpName = !nameSuffix.isEmpty() ? MeasuringPointUtility.measuringPointToString(mp) + nameSuffix 
                : MeasuringPointUtility.measuringPointToString(mp);
        result.setMeasuringPoint(mpName);
        
        return result;
    }
    
    private void initPowerMeasurements() {
        Collection<MeasurementSpecification> powerMeasurementSpecs =
                getMeasurementSpecificationsForPerformanceMetric(PerformanceMetricEnum.POWER_CONSUMPTION);
        if (!powerMeasurementSpecs.isEmpty()) {
            Map<String, Object> powerRecorderConfigurationMap =
                    createRecorderConfigMapWithAcceptedMetric(POWER_CONSUMPTION_TUPLE_METRIC_DESC);
            Map<String, Object> energyRecorderConfigurationMap =
                    createRecorderConfigMapWithAcceptedMetric(ENERGY_CONSUMPTION_TUPLE_METRIC_DESC);
            PowerModelRegistry reg = new PowerModelRegistry();
            PowerModelUpdaterSwitch modelUpdaterSwitch = new PowerModelUpdaterSwitch(reg,
                    new ExtensibleCalculatorInstantiatorImpl());
            List<ConsumptionContext> createdContexts = new ArrayList<>(powerMeasurementSpecs.size());
            List<SimulationTimeEvaluationScope> createdScopes = new ArrayList<>(powerMeasurementSpecs.size());
            
            for (MeasurementSpecification measurementSpec : powerMeasurementSpecs) {
                MeasuringPoint mp = ((PerformanceMeasurement) measurementSpec.eContainer()).getMeasuringPoint();
                final PowerProvidingEntity ppe = InterpreterUtils.getPowerProvindingEntityFromMeasuringPoint(mp);
                if (ppe == null) {
                    throw new IllegalStateException("PerformanceMeasurement of type PowerConsumption has"
                            + " to be related to a PowerProvidingEntity!");
                }

                energyRecorderConfigurationMap.put(AbstractRecorderConfiguration.MEASURING_POINT,
                        createStringMeasuringPointFromMeasuringPoint(mp, " [Energy]"));
                powerRecorderConfigurationMap.put(AbstractRecorderConfiguration.MEASURING_POINT,
                        createStringMeasuringPointFromMeasuringPoint(mp, " [Power]"));

                Measure<Double, Duration>[] windowProperties = WINDOW_PROPERTIES_SWITCH.doSwitch(measurementSpec
                        .getTemporalRestriction());
                Measure<Double, Duration> initialOffset = windowProperties[0];
                Measure<Double, Duration> samplingPeriod = windowProperties[1];
                SimulationTimeEvaluationScope scope = SimulationTimeEvaluationScope.createScope(ppe,
                        this.simuComModel, initialOffset, samplingPeriod);

                modelUpdaterSwitch.doSwitch(ppe);
                ConsumptionContext context = ConsumptionContext.createConsumptionContext(ppe
                        .getDistributionPowerAssemblyContext().getPowerBindingRepository(), scope, reg);

                AbstractCumulativeEnergyCalculator energyCalculator =
                        new SimpsonRuleCumulativeEnergyCalculator(samplingPeriod, initialOffset);

                createdContexts.add(context);
                createdScopes.add(scope);
                SimulationTimePowerConsumptionCalculator powerConsumptionCalculator =
                        new SimulationTimePowerConsumptionCalculator(context, scope, ppe);
                SimulationTimeEnergyConsumptionCalculator energyConsumptionCalculator =
                        new SimulationTimeEnergyConsumptionCalculator(energyCalculator);
                
                scope.addListener(powerConsumptionCalculator);
                powerConsumptionCalculator.addObserver(initializeRecorder(powerRecorderConfigurationMap));
                powerConsumptionCalculator.addObserver(new PowerConsumptionPrmRecorder(this.prmModel, measurementSpec, ppe));
                powerConsumptionCalculator.addObserver(energyConsumptionCalculator);
                energyConsumptionCalculator.addObserver(initializeRecorder(energyRecorderConfigurationMap));
            }
            triggerAfterSimulationCleanup(createdContexts, createdScopes);
        }
    }

    private void triggerAfterSimulationCleanup(final Collection<ConsumptionContext> contextsToCleanup,
            final Collection<SimulationTimeEvaluationScope> scopesToCleanup) {
        assert contextsToCleanup != null && !contextsToCleanup.isEmpty();
        assert scopesToCleanup != null && !scopesToCleanup.isEmpty();

        this.simuComModel.getConfiguration().addListener(new ISimulationListener() {
            @Override
            public void simulationStop() {
                for (ConsumptionContext context : contextsToCleanup) {
                    context.cleanUp();
                }
                for (SimulationTimeEvaluationScope scope : scopesToCleanup) {
                    scope.removeAllListeners();
                }
            }
            @Override
            public void simulationStart() {
            }
        });
    }

    private void initUntilizationMeasurements() {
        Collection<MeasurementSpecification> utilMeasurementSpecs =
                getMeasurementSpecificationsForPerformanceMetric(PerformanceMetricEnum.UTILIZATION);
        if (!utilMeasurementSpecs.isEmpty()) {
            RegisterCalculatorFactoryDecorator calculatorFactory = RegisterCalculatorFactoryDecorator.class
                    .cast(this.calculatorFactory);
            ISlidingWindowMoveOnStrategy strategy = new KeepLastElementPriorToLowerBoundStrategy();

            for (MeasurementSpecification spec : utilMeasurementSpecs) {
                MeasuringPoint mp = ((PerformanceMeasurement) spec.eContainer()).getMeasuringPoint();

                Calculator calculator = calculatorFactory.getCalculatorByMeasuringPointAndMetricDescription(mp,
                        MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE);
                if (calculator == null) {
                    throw new IllegalStateException(
                            "Utilization measurements (sliding window based) cannot be initialized.\n"
                                    + "No state of active resource calculator available for: "
                                    + MeasuringPointUtility.measuringPointToString(mp) + "\n"
                                    + "Ensure that initializeModelSyncers() in SimulizarRuntimeState is called prior "
                                    + "to initializeInterpreterListeners()!");
                }
                setupUtilizationRecorder(calculator, spec, strategy);
            }
        }
    }

    private Calculator setupUtilizationRecorder(Calculator calculator,
            MeasurementSpecification utilizationMeasurementSpec, ISlidingWindowMoveOnStrategy moveOnStrategy) {

        Measure<Double, Duration>[] windowProperties = WINDOW_PROPERTIES_SWITCH.doSwitch(utilizationMeasurementSpec
                .getTemporalRestriction());

        Map<String, Object> recorderConfigurationMap =
                createRecorderConfigMapWithAcceptedMetric(MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE);
        recorderConfigurationMap.put(AbstractRecorderConfiguration.MEASURING_POINT,
                createStringMeasuringPointFromMeasuringPoint(calculator.getMeasuringPoint(), ""));

        IRecorder baseRecorder = initializeRecorder(recorderConfigurationMap);

        SimulationGovernedSlidingWindow window = new SimulationGovernedSlidingWindow(windowProperties[0],
                windowProperties[1], MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE, moveOnStrategy,
                this.simuComModel);

        org.palladiosimulator.experimentanalysis.ISlidingWindowListener aggregator = new SlidingWindowUtilizationAggregator(
                baseRecorder);
        SlidingWindowRecorder windowRecorder = new SlidingWindowRecorder(window, aggregator);
        // register recorder at calculator
        calculator.addObserver(windowRecorder);
        return calculator;
    }

    /**
     * Initialize the response time measurements. First gets the monitored elements from the PMS
     * model, create according calculators, and aggregators.
     * 
     */
    private void initReponseTimeMeasurement() {

        for (MeasurementSpecification responseTimeMeasurementSpec :
                getMeasurementSpecificationsForPerformanceMetric(PerformanceMetricEnum.RESPONSE_TIME)) {
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

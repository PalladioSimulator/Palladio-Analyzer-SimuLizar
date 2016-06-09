package org.palladiosimulator.simulizar.utilization.probeframework;

import static java.util.stream.Collectors.toList;
import static org.palladiosimulator.edp2.util.MeasurementsUtility.SLIDING_WINDOW_BASED_MEASUREMENT_TAG_KEY;
import static org.palladiosimulator.edp2.util.MeasurementsUtility.SLIDING_WINDOW_BASED_MEASUREMENT_TAG_VALUE;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.experimentanalysis.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.experimentanalysis.windowaggregators.SlidingWindowUtilizationAggregator;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.TemporalCharacterization;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointPackage;
import org.palladiosimulator.pcmmeasuringpoint.util.PcmmeasuringpointSwitch;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.recorderframework.config.IRecorderConfiguration;
import org.palladiosimulator.recorderframework.edp2.config.EDP2RecorderConfiguration;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractProbeFrameworkListener;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.slidingwindow.impl.SimulizarSlidingWindow;
import org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement.SlidingWindowRuntimeMeasurementsRecorder;
import org.palladiosimulator.simulizar.slidingwindow.util.SimulizarSlidingWindowUtil;

import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class UtilizationProbeFrameworkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final MetricSetDescription UTILIZATION_TUPLE_METRIC_DESC = MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE;
    private static final BaseMetricDescription UTILIZATION_METRIC_DESC = MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE;
    private static final MetricSetDescription STATE_TUPLE_METRIC_DESC = MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE;
    private static final EClass ACTIVE_RESOURCE_MP_ECLASS = PcmmeasuringpointPackage.Literals.ACTIVE_RESOURCE_MEASURING_POINT;

    private static final PcmmeasuringpointSwitch<Optional<ActiveResourceMeasuringPoint>> ACTIVE_RESOURCE_MP_SWITCH = new PcmmeasuringpointSwitch<Optional<ActiveResourceMeasuringPoint>>() {

        @Override
        public Optional<ActiveResourceMeasuringPoint> caseActiveResourceMeasuringPoint(
                final ActiveResourceMeasuringPoint activeResourceMeasuringPoint) {
            return Optional.of(activeResourceMeasuringPoint);
        }

        @Override
        public Optional<ActiveResourceMeasuringPoint> defaultCase(final EObject eObject) {
            return Optional.empty();
        }
    };

    private ISlidingWindowMoveOnStrategy moveOnStrategy = null;
    private SimuComModel model = null;
    private RuntimeMeasurementModel rmModel;
    private RegisterCalculatorFactoryDecorator calculatorFactory = null;

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();

        final Collection<MeasurementSpecification> utilMeasurementSpecs = new ArrayList<>(getProbeFrameworkListener()
                .getMeasurementSpecificationsForMetricDescription(UTILIZATION_TUPLE_METRIC_DESC));
        // also consider case when utilization metric rather than utilization
        // tuple metric is chosen
        utilMeasurementSpecs.addAll(
                getProbeFrameworkListener().getMeasurementSpecificationsForMetricDescription(UTILIZATION_METRIC_DESC));
        initUtilizationMeasurements(utilMeasurementSpecs);
    }

    @Override
    public void setProbeFrameworkListener(final AbstractProbeFrameworkListener probeFrameworkListener) {
        super.setProbeFrameworkListener(probeFrameworkListener);
        this.model = getProbeFrameworkListener().getSimuComModel();
        this.rmModel = getProbeFrameworkListener().getRuntimeMeasurementModel();
        this.calculatorFactory = RegisterCalculatorFactoryDecorator.class
                .cast(getProbeFrameworkListener().getCalculatorFactory());
    }

    private void initUtilizationMeasurements(final Collection<MeasurementSpecification> utilMeasurementSpecs) {
        assert utilMeasurementSpecs != null;

        if (!utilMeasurementSpecs.isEmpty()) {
            this.moveOnStrategy = new KeepLastElementPriorToLowerBoundStrategy();
            final Collection<Calculator> overallUtilizationCalculators = getAvailableOverallUtilizationCalculators();

            for (final MeasurementSpecification spec : utilMeasurementSpecs) {
                final MeasuringPoint mp = spec.getMonitor().getMeasuringPoint();

                final Optional<ActiveResourceMeasuringPoint> activeResourceMp = ACTIVE_RESOURCE_MP_SWITCH.doSwitch(mp);

                final Optional<Calculator> overallUtilizationCalculator = activeResourceMp
                        .filter(a -> a.getReplicaID() == 0).map(ActiveResourceMeasuringPoint::getActiveResource)
                        .filter(proc -> proc.getNumberOfReplicas() > 1)
                        .flatMap(proc -> findOverallUtilizationCalculatorForProcessingResource(proc,
                                overallUtilizationCalculators));

                final Optional<Calculator> stateOfActiveResourceCalculator = Optional.ofNullable(this.calculatorFactory
                        .getCalculatorByMeasuringPointAndMetricDescription(mp, STATE_TUPLE_METRIC_DESC));
                if (stateOfActiveResourceCalculator == null) {
                    throw new IllegalStateException(
                            "Utilization measurements (sliding window based) cannot be initialized.\n"
                                    + "No state of active resource calculator available for: "
                                    + mp.getStringRepresentation() + "\n"
                                    + "Ensure that initializeModelSyncers() in SimulizarRuntimeState is called prior "
                                    + "to initializeInterpreterListeners()!");
                }
                setupUtilizationRecorder(stateOfActiveResourceCalculator, spec, overallUtilizationCalculator);
            }
        }
    }

    private Collection<Calculator> getAvailableOverallUtilizationCalculators() {
        final Collection<Calculator> overallUtilizationCalculators = this.calculatorFactory.getRegisteredCalculators()
                .stream().filter(calc -> calc.isCompatibleWith(UTILIZATION_TUPLE_METRIC_DESC)
                        && ACTIVE_RESOURCE_MP_ECLASS.isInstance(calc.getMeasuringPoint()))
                .collect(toList());
        return overallUtilizationCalculators;
    }

    private static Optional<Calculator> findOverallUtilizationCalculatorForProcessingResource(
            final ProcessingResourceSpecification proc, final Collection<Calculator> overallUtilizationCalculators) {
        final String processingResourceId = proc.getId();
        return overallUtilizationCalculators.stream()
                .filter(calc -> ((ActiveResourceMeasuringPoint) calc.getMeasuringPoint()).getActiveResource().getId()
                        .equals(processingResourceId))
                .findAny();
    }

    private void setupUtilizationRecorder(final Optional<Calculator> stateOfActiveResourceCalculator,
            final MeasurementSpecification utilizationMeasurementSpec,
            final Optional<Calculator> overallUtilizationCalculator) {

        if (utilizationMeasurementSpec.getTemporalRestriction() == null) {
            throw new IllegalArgumentException(
                    "MetricDescription (Utilization of Active Resource Tuple) '" + utilizationMeasurementSpec.getName()
                            + "' of Monitor '" + utilizationMeasurementSpec.getMonitor().getEntityName()
                            + "' must provide a TemporalCharacterization of Type 'Interval' or 'DelayedInterval'");
        }
        
        stateOfActiveResourceCalculator.ifPresent(calc -> 
        	setUpSlidingWindowAggregatorAndRecorder(calc, utilizationMeasurementSpec, STATE_TUPLE_METRIC_DESC));
        overallUtilizationCalculator.ifPresent(calc -> 
    		setUpSlidingWindowAggregatorAndRecorder(calc, utilizationMeasurementSpec, UTILIZATION_TUPLE_METRIC_DESC));
        
    }
    
    private void setUpSlidingWindowAggregatorAndRecorder(Calculator calc, MeasurementSpecification spec, MetricSetDescription desc) {	
    	final SlidingWindowUtilizationAggregator aggregator = createSlidingWindowAggregator(calc, desc);
        // register recorder at calculator
        registerMeasurementsRecorder(calc,
                createSlidingWindowRecorder(spec.getTemporalRestriction(), aggregator));
        // forward utilization measurements to RuntimeMeasurementModel (the
        // former PRM)
        if (spec.isTriggersSelfAdaptations()) {
            aggregator.addRecorder(new SlidingWindowRuntimeMeasurementsRecorder(this.rmModel, spec));
        }    
    }

    private SlidingWindowUtilizationAggregator createSlidingWindowAggregator(final Calculator baseCalculator,
            final MetricSetDescription expectedWindowDataMetric) {

        final Map<String, Object> recorderConfigMap = createRecorderConfigMapWithAcceptedMetricAndMeasuringPoint(
                UTILIZATION_TUPLE_METRIC_DESC, baseCalculator.getMeasuringPoint());
        return new SlidingWindowUtilizationAggregator(expectedWindowDataMetric,
                super.initializeRecorder(recorderConfigMap));
    }

    private SlidingWindowRecorder createSlidingWindowRecorder(final TemporalCharacterization temporalCharacterization,
            final SlidingWindowUtilizationAggregator utilizationAggregator) {
        assert this.model != null && this.rmModel != null;

        final Measure<Double, Duration>[] windowProperties = SimulizarSlidingWindowUtil
                .getWindowPropertiesFromTemporalCharacterization(temporalCharacterization);

        return new SlidingWindowRecorder(
                new SimulizarSlidingWindow(windowProperties[0], windowProperties[1],
                        utilizationAggregator.getExpectedWindowDataMetric(), this.moveOnStrategy, this.model),
                utilizationAggregator);
    }

    @Override
    protected IRecorderConfiguration createRecorderConfiguration(final SimuComConfig config,
            final Map<String, Object> recorderConfigMap) {

        final IRecorderConfiguration recorderConfig = super.createRecorderConfiguration(config, recorderConfigMap);
        if (recorderConfig instanceof EDP2RecorderConfiguration) {
            tagMeasurement((EDP2RecorderConfiguration) recorderConfig);
        }
        return recorderConfig;
    }

    private void tagMeasurement(final EDP2RecorderConfiguration recorderConfig) {
        assert recorderConfig != null;

        final Measurement measurement = recorderConfig.getMeasurement();
        if (measurement == null) {
            throw new RuntimeException(
                    "Measurement null! Something went wrong, as this should have been created by the recorder configuration factory!");
        }
        measurement.getAdditionalInformation().put(SLIDING_WINDOW_BASED_MEASUREMENT_TAG_KEY,
                SLIDING_WINDOW_BASED_MEASUREMENT_TAG_VALUE);
    }
}

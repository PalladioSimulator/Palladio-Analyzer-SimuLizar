package org.palladiosimulator.simulizar.utilization.probeframework;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.experimentanalysis.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.experimentanalysis.windowaggregators.SlidingWindowUtilizationAggregator;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.ProcessingType;
import org.palladiosimulator.monitorrepository.TimeDriven;
import org.palladiosimulator.monitorrepository.WindowCharacterization;
import org.palladiosimulator.monitorrepository.util.MonitorRepositorySwitch;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointPackage;
import org.palladiosimulator.pcmmeasuringpoint.util.PcmmeasuringpointSwitch;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeFrameworkListener;
import org.palladiosimulator.simulizar.slidingwindow.impl.SimulizarSlidingWindow;
import org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement.SlidingWindowRuntimeMeasurementsRecorder;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class UtilizationProbeFrameworkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final MetricSetDescription UTILIZATION_TUPLE_METRIC_DESC = MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE;
    private static final BaseMetricDescription UTILIZATION_METRIC_DESC = MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE;
    private static final MetricSetDescription STATE_TUPLE_METRIC_DESC = MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE;
    private static final EClass ACTIVE_RESOURCE_MP_ECLASS = PcmmeasuringpointPackage.Literals.ACTIVE_RESOURCE_MEASURING_POINT;

    private static final MonitorRepositorySwitch<Optional<TimeDriven>> PROCESSING_TYPE_SWITCH = new MonitorRepositorySwitch<Optional<TimeDriven>>() {
        @Override
        public Optional<TimeDriven> caseTimeDriven(TimeDriven timeDriven) {
            return Optional.of(timeDriven);
        }

        @Override
        public Optional<TimeDriven> defaultCase(EObject eObject) {
            return Optional.empty();
        }
    };

    private static final PcmmeasuringpointSwitch<Optional<ActiveResourceMeasuringPoint>> ACTIVE_RESOURCE_MP_SWITCH = new PcmmeasuringpointSwitch<Optional<ActiveResourceMeasuringPoint>>() {

        @Override
        public Optional<ActiveResourceMeasuringPoint> caseActiveResourceMeasuringPoint(
                ActiveResourceMeasuringPoint activeResourceMeasuringPoint) {
            return Optional.of(activeResourceMeasuringPoint);
        }

        @Override
        public Optional<ActiveResourceMeasuringPoint> defaultCase(EObject eObject) {
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

        Collection<MeasurementSpecification> utilMeasurementSpecs = new ArrayList<>(getProbeFrameworkListener()
                .getMeasurementSpecificationsForMetricDescription(UTILIZATION_TUPLE_METRIC_DESC));
        // also consider case when utilization metric rather than utilization
        // tuple metric is chosen
        utilMeasurementSpecs.addAll(
                getProbeFrameworkListener().getMeasurementSpecificationsForMetricDescription(UTILIZATION_METRIC_DESC));
        initUtilizationMeasurements(utilMeasurementSpecs);
    }

    @Override
    public void setProbeFrameworkListener(ProbeFrameworkListener probeFrameworkListener) {
        super.setProbeFrameworkListener(probeFrameworkListener);
        this.model = getProbeFrameworkListener().getSimuComModel();
        this.rmModel = getProbeFrameworkListener().getRuntimeMeasurementModel();
        this.calculatorFactory = RegisterCalculatorFactoryDecorator.class
                .cast(getProbeFrameworkListener().getCalculatorFactory());
    }

    private void initUtilizationMeasurements(Collection<MeasurementSpecification> utilMeasurementSpecs) {
        assert utilMeasurementSpecs != null;

        if (!utilMeasurementSpecs.isEmpty()) {
            this.moveOnStrategy = new KeepLastElementPriorToLowerBoundStrategy();
            Collection<Calculator> overallUtilizationCalculators = getAvailableOverallUtilizationCalculators();

            for (MeasurementSpecification spec : utilMeasurementSpecs) {
                MeasuringPoint mp = spec.getMonitor().getMeasuringPoint();

                Optional<ActiveResourceMeasuringPoint> activeResourceMp = ACTIVE_RESOURCE_MP_SWITCH.doSwitch(mp);

                Optional<Calculator> overallUtilizationCalculator = activeResourceMp.filter(a -> a.getReplicaID() == 0)
                        .map(ActiveResourceMeasuringPoint::getActiveResource)
                        .filter(proc -> proc.getNumberOfReplicas() > 1)
                        .flatMap(proc -> findOverallUtilizationCalculatorForProcessingResource(proc,
                                overallUtilizationCalculators));

                Optional<TimeDriven> timeDrivenProcessingType = PROCESSING_TYPE_SWITCH
                        .doSwitch(spec.getProcessingType());

                Calculator stateOfActiveResourceCalculator = this.calculatorFactory
                        .getCalculatorByMeasuringPointAndMetricDescription(mp, STATE_TUPLE_METRIC_DESC);

                // this call crashes in case measurement specification is invalid
                // or if the required 'state of Active Resource' calculator is not present (i.e.,
                // null)
                checkValidity(spec, timeDrivenProcessingType, stateOfActiveResourceCalculator, mp);

                setupUtilizationRecorder(stateOfActiveResourceCalculator, spec, timeDrivenProcessingType.get(),
                        overallUtilizationCalculator);
            }
        }
    }

    private Collection<Calculator> getAvailableOverallUtilizationCalculators() {
        Collection<Calculator> overallUtilizationCalculators = this.calculatorFactory.getRegisteredCalculators()
                .stream().filter(calc -> calc.isCompatibleWith(UTILIZATION_TUPLE_METRIC_DESC)
                        && ACTIVE_RESOURCE_MP_ECLASS.isInstance(calc.getMeasuringPoint()))
                .collect(toList());
        return overallUtilizationCalculators;
    }

    private static Optional<Calculator> findOverallUtilizationCalculatorForProcessingResource(
            ProcessingResourceSpecification proc, Collection<Calculator> overallUtilizationCalculators) {
        String processingResourceId = proc.getId();
        return overallUtilizationCalculators.stream()
                .filter(calc -> ((ActiveResourceMeasuringPoint) calc.getMeasuringPoint()).getActiveResource().getId()
                        .equals(processingResourceId))
                .findAny();
    }

    private static void checkValidity(MeasurementSpecification utilizationMeasurementSpec,
            Optional<TimeDriven> aggregation, Calculator stateOfActiveResourceCalculator, MeasuringPoint mp) {

        if (stateOfActiveResourceCalculator == null) {
            throw new IllegalStateException("Utilization measurements (sliding window based) cannot be initialized.\n"
                    + "No state of active resource calculator available for: " + mp.getStringRepresentation() + "\n"
                    + "Ensure that initializeModelSyncers() in SimulizarRuntimeState is called prior "
                    + "to initializeInterpreterListeners()!");
        }

        if (!aggregation.isPresent()) {
            throw new IllegalStateException(
                    "MetricDescription (" + utilizationMeasurementSpec.getMetricDescription().getName() + ") '"
                            + utilizationMeasurementSpec.getName() + "' of Monitor '"
                            + utilizationMeasurementSpec.getMonitor().getEntityName() + "' must provide a "
                            + ProcessingType.class.getName() + " of Type '" + TimeDriven.class.getName() + "'!");
        }
    }

    private void setupUtilizationRecorder(Calculator stateOfActiveResourceCalculator,
            MeasurementSpecification utilizationMeasurementSpec, TimeDriven timeDrivenProcessingType,
            Optional<Calculator> overallUtilizationCalculator) {

        WindowCharacterization windowCharacterization = timeDrivenProcessingType.getWindowCharacterization();

        SlidingWindowUtilizationAggregator utilizationAggregator = createSlidingWindowAggregator(
                stateOfActiveResourceCalculator, STATE_TUPLE_METRIC_DESC);
        SlidingWindowRecorder windowRecorder = createSlidingWindowRecorder(windowCharacterization,
                utilizationAggregator);
        // register recorder at calculator
        registerMeasurementsRecorder(stateOfActiveResourceCalculator, windowRecorder);
        // forward utilization measurements to RuntimeMeasurementModel (the
        // former PRM)
        utilizationAggregator.addRecorder(new SlidingWindowRuntimeMeasurementsRecorder(this.rmModel,
                utilizationMeasurementSpec, utilizationMeasurementSpec.getMonitor().getMeasuringPoint()));

        overallUtilizationCalculator.ifPresent(calc -> {
            SlidingWindowUtilizationAggregator aggregator = createSlidingWindowAggregator(calc,
                    UTILIZATION_TUPLE_METRIC_DESC);
            // register recorder at calculator
            registerMeasurementsRecorder(calc, createSlidingWindowRecorder(windowCharacterization, aggregator));
            // forward utilization measurements to RuntimeMeasurementModel (the
            // former PRM)
            aggregator.addRecorder(new SlidingWindowRuntimeMeasurementsRecorder(this.rmModel,
                    utilizationMeasurementSpec, calc.getMeasuringPoint()));
        });
    }

    private SlidingWindowUtilizationAggregator createSlidingWindowAggregator(Calculator baseCalculator,
            MetricSetDescription expectedWindowDataMetric) {

        Map<String, Object> recorderConfigMap = createRecorderConfigMapWithAcceptedMetricAndMeasuringPoint(
                UTILIZATION_TUPLE_METRIC_DESC, baseCalculator.getMeasuringPoint());
        return new SlidingWindowUtilizationAggregator(expectedWindowDataMetric,
                super.initializeRecorder(recorderConfigMap));
    }

    private SlidingWindowRecorder createSlidingWindowRecorder(WindowCharacterization windowCharacterization,
            SlidingWindowUtilizationAggregator utilizationAggregator) {
        assert this.model != null && this.rmModel != null;

        return new SlidingWindowRecorder(
                new SimulizarSlidingWindow(windowCharacterization.getWindowLengthAsMeasure(),
                        windowCharacterization.getWindowIncrementAsMeasure(),
                        utilizationAggregator.getExpectedWindowDataMetric(), this.moveOnStrategy, this.model),
                utilizationAggregator);
    }
}

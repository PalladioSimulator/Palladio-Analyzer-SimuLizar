package org.palladiosimulator.simulizar.slidingwindow.probeframework;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.experimentanalysis.DiscardAllElementsPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindow;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.experimentanalysis.statisticalcharacterization.aggregators.SlidingWindowStatisticalCharacterizationAggregator;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.util.MetricSpecSwitch;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.monitorrepository.TimeDrivenAggregation;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractProbeFrameworkListener;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.slidingwindow.impl.SimulizarSlidingWindow;
import org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement.SlidingWindowRuntimeMeasurementsRecorder;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class SlidingWindowProbeFrameWorkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final EClass TIME_DRIVEN_AGGREGATION_ECLASS = MonitorRepositoryPackage.Literals.TIME_DRIVEN_AGGREGATION;

    private RegisterCalculatorFactoryDecorator calculatorFactory = null;
    private SimuComModel model;
    private RuntimeMeasurementModel runtimeMeasurementModel;
    private RetrieveCalculatorSwitch retrieveCalculatorSwitch;

    private ISlidingWindowMoveOnStrategy discreteMetricScopeStrategy = null;
    private ISlidingWindowMoveOnStrategy continuousMetricScopeStrategy = null;

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();
        initSlidingWindowBasedMeasurements();
    }

    @Override
    public void setProbeFrameworkListener(final AbstractProbeFrameworkListener listener) {
        super.setProbeFrameworkListener(listener);
        this.calculatorFactory = RegisterCalculatorFactoryDecorator.class
                .cast(getProbeFrameworkListener().getCalculatorFactory());
        this.model = getProbeFrameworkListener().getSimuComModel();
        this.runtimeMeasurementModel = getProbeFrameworkListener().getRuntimeMeasurementModel();
        this.retrieveCalculatorSwitch = new RetrieveCalculatorSwitch();
    }

    private void initSlidingWindowBasedMeasurements() {
        assert getProbeFrameworkListener() != null;

        Collection<MeasurementSpecification> measurementSpecs = this.getProbeFrameworkListener()
                .getMeasurementSpecificationsForProcessingType(TIME_DRIVEN_AGGREGATION_ECLASS);

        initTimeDrivenAggregators(measurementSpecs);
    }

    private void initTimeDrivenAggregators(final Collection<MeasurementSpecification> measurementSpecs) {
        if (!measurementSpecs.isEmpty()) {
            this.discreteMetricScopeStrategy = new DiscardAllElementsPriorToLowerBoundStrategy();
            this.continuousMetricScopeStrategy = new KeepLastElementPriorToLowerBoundStrategy();
            measurementSpecs.forEach(this::initAggregatorForMeasSpec);
        }
    }

    private void initAggregatorForMeasSpec(final MeasurementSpecification measurementSpec) {

        MeasuringPoint measuringPoint = measurementSpec.getMonitor().getMeasuringPoint();
        NumericalBaseMetricDescription expectedMetric = (NumericalBaseMetricDescription) measurementSpec
                .getMetricDescription();

        Optional<Calculator> calculator = this.retrieveCalculatorSwitch.retrieveCalculator(measuringPoint,
                expectedMetric);

        if (!calculator.isPresent()) {
            throw new IllegalStateException(
                    "Time driven aggregation of measurements (sliding window based) cannot be initialized.\n" + "No '"
                            + measurementSpec.getMetricDescription().getName() + "' calculator available for: "
                            + "MeasuringPoint '" + measuringPoint.getStringRepresentation() + "'.\n"
                            + "Affected Monitor: '" + measurementSpec.getMonitor().getEntityName() + "'\n"
                            + "Ensure that measurement calculator has been created and registered within the ProbeFrameworkListener class!");
        }

        ISlidingWindowMoveOnStrategy moveOnStrategy = null;
        switch (expectedMetric.getScopeOfValidity()) {
        case CONTINUOUS:
            moveOnStrategy = this.continuousMetricScopeStrategy;
            break;
        case DISCRETE:
            moveOnStrategy = this.discreteMetricScopeStrategy;
            break;
        default:
            throw new AssertionError();
        }

        TimeDrivenAggregation aggregation = (TimeDrivenAggregation) measurementSpec.getProcessingType();
        SlidingWindow window = new SimulizarSlidingWindow(aggregation.getWindowLengthAsMeasure(),
                aggregation.getWindowIncrementAsMeasure(), expectedMetric, moveOnStrategy, this.model);

        SlidingWindowStatisticalCharacterizationAggregator windowAggregator = new SlidingWindowStatisticalCharacterizationAggregator(
                aggregation.getStatisticalCharacterization().getAggregator(expectedMetric));

        if (measurementSpec.isTriggersSelfAdaptations()) {
            windowAggregator.addRecorder(new SlidingWindowRuntimeMeasurementsRecorder(this.runtimeMeasurementModel,
                    measurementSpec, measuringPoint));
        }

        super.registerMeasurementsRecorder(calculator.get(), new SlidingWindowRecorder(window, windowAggregator));
    }

    private class RetrieveCalculatorSwitch extends MetricSpecSwitch<Optional<Calculator>> {

        private MeasuringPoint currentMeasuringPoint;

        @Override
        public Optional<Calculator> caseMetricDescription(final MetricDescription metricDescription) {
            return Optional.ofNullable(SlidingWindowProbeFrameWorkListenerDecorator.this.calculatorFactory
                    .getCalculatorByMeasuringPointAndMetricDescription(this.currentMeasuringPoint, metricDescription));
        }

        @Override
        public Optional<Calculator> caseBaseMetricDescription(final BaseMetricDescription baseMetricDescription) {
            return SlidingWindowProbeFrameWorkListenerDecorator.this.calculatorFactory
                    .getCalculatorsForMeasuringPoint(this.currentMeasuringPoint).stream()
                    .filter(calc -> MetricDescriptionUtility.isBaseMetricDescriptionSubsumedByMetricDescription(
                            baseMetricDescription, calc.getMetricDesciption()))
                    .findAny();
        }

        @Override
        public Optional<Calculator> defaultCase(final EObject eObject) {
            return Optional.empty();
        }

        private Optional<Calculator> retrieveCalculator(final MeasuringPoint mp,
                final MetricDescription expectedMetric) {
            this.currentMeasuringPoint = mp;
            return this.doSwitch(expectedMetric);
        }
    }
}

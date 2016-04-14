package org.palladiosimulator.simulizar.slidingwindow.probeframework;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.experimentanalysis.FlushWindowStrategy;
import org.palladiosimulator.experimentanalysis.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindow;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.experimentanalysis.statisticalcharacterization.aggregators.StatisticalCharacterizationAggregator;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.util.MetricSpecSwitch;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.monitorrepository.TimeDrivenAggregation;
import org.palladiosimulator.monitorrepository.WindowCharacterization;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeFrameworkListener;
import org.palladiosimulator.simulizar.slidingwindow.impl.SimulizarSlidingWindow;
import org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement.SlidingWindowRuntimeMeasurementsRecorder;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class SlidingWindowProbeFrameWorkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final EClass TIME_DRIVEN_AGGREGATION_ECLASS = MonitorRepositoryPackage.Literals.TIME_DRIVEN_AGGREGATION;

    private RegisterCalculatorFactoryDecorator calculatorFactory = null;
    private SimuComModel model;
    private RuntimeMeasurementModel runtimeMeasurementModel;
    private RetrieveCalculatorSwitch retrieveCalculatorSwitch;
    private ISlidingWindowMoveOnStrategy moveOnStrategy = null;

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();
        initSlidingWindowBasedMeasurements();
    }

    @Override
    public void setProbeFrameworkListener(ProbeFrameworkListener listener) {
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

    private void initTimeDrivenAggregators(Collection<MeasurementSpecification> measurementSpecs) {
        if (!measurementSpecs.isEmpty()) {
            this.moveOnStrategy = new FlushWindowStrategy();
            measurementSpecs.forEach(m -> initAggregatorForMeasSpec(m));
        }
    }

    private void initAggregatorForMeasSpec(MeasurementSpecification measurementSpec) {

        MeasuringPoint measuringPoint = measurementSpec.getMonitor().getMeasuringPoint();
        MetricDescription expectedMetric = measurementSpec.getMetricDescription();
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

        TimeDrivenAggregation aggregation = (TimeDrivenAggregation) measurementSpec.getProcessingType();
        WindowCharacterization windowCharacterization = aggregation.getWindowCharacterization();

        SlidingWindow window = new SimulizarSlidingWindow(windowCharacterization.getWindowLengthAsMeasure(),
                windowCharacterization.getWindowIncrementAsMeasure(), expectedMetric, this.moveOnStrategy, this.model);

        StatisticalCharacterizationAggregator aggregator = aggregation.getStatisticalCharacterization()
                .getAggregator((NumericalBaseMetricDescription) expectedMetric);
        aggregator.addRecorder(new SlidingWindowRuntimeMeasurementsRecorder(this.runtimeMeasurementModel,
                measurementSpec, measuringPoint));

        super.registerMeasurementsRecorder(calculator.get(), new SlidingWindowRecorder(window, aggregator));
    }

    private class RetrieveCalculatorSwitch extends MetricSpecSwitch<Optional<Calculator>> {

        private MeasuringPoint currentMeasuringPoint;

        @Override
        public Optional<Calculator> caseMetricDescription(MetricDescription metricDescription) {
            return Optional.ofNullable(SlidingWindowProbeFrameWorkListenerDecorator.this.calculatorFactory
                    .getCalculatorByMeasuringPointAndMetricDescription(this.currentMeasuringPoint, metricDescription));
        }

        @Override
        public Optional<Calculator> caseBaseMetricDescription(BaseMetricDescription baseMetricDescription) {
            return SlidingWindowProbeFrameWorkListenerDecorator.this.calculatorFactory
                    .getCalculatorsForMeasuringPoint(this.currentMeasuringPoint).stream()
                    .filter(calc -> MetricDescriptionUtility.isBaseMetricDescriptionSubsumedByMetricDescription(
                            baseMetricDescription, calc.getMetricDesciption()))
                    .findAny();
        }

        @Override
        public Optional<Calculator> defaultCase(EObject eObject) {
            return Optional.empty();
        }

        private Optional<Calculator> retrieveCalculator(MeasuringPoint mp, MetricDescription expectedMetric) {
            this.currentMeasuringPoint = mp;
            return this.doSwitch(expectedMetric);
        }
    }
}

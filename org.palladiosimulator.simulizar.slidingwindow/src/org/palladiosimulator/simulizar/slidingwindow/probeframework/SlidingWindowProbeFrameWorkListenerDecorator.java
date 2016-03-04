package org.palladiosimulator.simulizar.slidingwindow.probeframework;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
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
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.monitorrepository.TimeDrivenAggregation;
import org.palladiosimulator.monitorrepository.WindowCharacterization;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.slidingwindow.impl.SimulizarSlidingWindow;
import org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement.SlidingWindowRuntimeMeasurementsRecorder;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class SlidingWindowProbeFrameWorkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final EClass TIME_DRIVEN_AGGREGATION_ECLASS = MonitorRepositoryPackage.Literals.TIME_DRIVEN_AGGREGATION;

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();
        initSlidingWindowBasedMeasurements();
    }

    private void initSlidingWindowBasedMeasurements() {
        assert getProbeFrameworkListener() != null;

        Collection<MeasurementSpecification> measurementSpecs = this.getProbeFrameworkListener()
                .getMeasurementSpecificationsForProcessingType(TIME_DRIVEN_AGGREGATION_ECLASS);

        initTimeDrivenAggregators(measurementSpecs);
    }

    private void initTimeDrivenAggregators(Collection<MeasurementSpecification> measurementSpecs) {
        if (!measurementSpecs.isEmpty()) {
            RegisterCalculatorFactoryDecorator calcFactory = RegisterCalculatorFactoryDecorator.class
                    .cast(getProbeFrameworkListener().getCalculatorFactory());
            ISlidingWindowMoveOnStrategy strategy = new FlushWindowStrategy();
            SimuComModel model = getProbeFrameworkListener().getSimuComModel();
            measurementSpecs.forEach(m -> initAggregatorForMeasSpec(m, calcFactory, strategy, model));
        }
    }

    private void initAggregatorForMeasSpec(MeasurementSpecification measurementSpec,
            RegisterCalculatorFactoryDecorator calcFactory, ISlidingWindowMoveOnStrategy strategy, SimuComModel model) {

        MeasuringPoint mp = measurementSpec.getMonitor().getMeasuringPoint();
        MetricDescription expectedMetric = measurementSpec.getMetricDescription();
        Optional<Calculator> calculator;
        BaseMetricDescription[] subsumedBaseMetrics = MetricDescriptionUtility.toBaseMetricDescriptions(expectedMetric);
        if (subsumedBaseMetrics.length == 1) {
            // check for the base metric manually
            calculator = calcFactory.getCalculatorsForMeasuringPoint(mp).stream()
                    .filter(calc -> MetricDescriptionUtility.isBaseMetricDescriptionSubsumedByMetricDescription(
                            subsumedBaseMetrics[0], calc.getMetricDesciption()))
                    .findAny();
        } else {
            // use convenience method that matches complete tuple metrics
            calculator = Optional.ofNullable(calcFactory.getCalculatorByMeasuringPointAndMetricDescription(mp,
                    measurementSpec.getMetricDescription()));
        }

        if (!calculator.isPresent()) {
            throw new IllegalStateException(
                    "Time driven aggregation of measurements (sliding window based) cannot be initialized.\n" + "No '"
                            + measurementSpec.getMetricDescription().getName() + "' calculator available for: "
                            + "MeasuringPoint '" + mp.getStringRepresentation() + "'.\n" + "Affected Monitor: '"
                            + measurementSpec.getMonitor().getEntityName() + "'\n"
                            + "Ensure that measurement calculator has been created and registered within the ProbeFrameworkListener class!");
        }

        TimeDrivenAggregation aggregation = (TimeDrivenAggregation) measurementSpec.getProcessingType();
        WindowCharacterization windowCharacterization = aggregation.getWindowCharacterization();

        SlidingWindow window = new SimulizarSlidingWindow(windowCharacterization.getWindowLengthAsMeasure(),
                windowCharacterization.getWindowIncrementAsMeasure(), expectedMetric, strategy, model);

        SlidingWindowRuntimeMeasurementsRecorder runtimeMeasurementsRecorder = new SlidingWindowRuntimeMeasurementsRecorder(
                getProbeFrameworkListener().getRuntimeMeasurementModel(), measurementSpec,
                measurementSpec.getMonitor().getMeasuringPoint());

        StatisticalCharacterizationAggregator aggregator = aggregation.getStatisticalCharacterization()
                .getAggregator((NumericalBaseMetricDescription) expectedMetric);
        aggregator.addRecorder(runtimeMeasurementsRecorder);

        registerMeasurementsRecorder(calculator.get(), new SlidingWindowRecorder(window, aggregator));
    }
}

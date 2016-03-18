package org.palladiosimulizar.aggregation.aggregators;

import java.util.Objects;
import java.util.Optional;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.monitorrepository.FixedSizeAggregation;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;

public class FixedSizeMeasurementsAggregator {

    private final MeasurementSpecification measurementSpecification;
    private final FixedSizeAggregation fixedSizeAggregation;

    public FixedSizeMeasurementsAggregator(MeasurementSpecification measurementSpec,
            RegisterCalculatorFactoryDecorator calcFactory) {
        this.measurementSpecification = Objects.requireNonNull(measurementSpec);

        this.fixedSizeAggregation = (FixedSizeAggregation) this.measurementSpecification.getProcessingType();

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
            throw new IllegalStateException("Fixed size aggregation of measurements cannot be initialized.\n" + "No '"
                    + measurementSpec.getMetricDescription().getName() + "' calculator available for: "
                    + "MeasuringPoint '" + mp.getStringRepresentation() + "'.\n" + "Affected Monitor: '"
                    + measurementSpec.getMonitor().getEntityName() + "'\n"
                    + "Ensure that measurement calculator has been created and registered within the ProbeFrameworkListener class!");
        }
        calculator.get().addObserver(new IMeasurementSourceListener() {

            @Override
            public void preUnregister() {
                // TODO Auto-generated method stub

            }

            @Override
            public void newMeasurementAvailable(MeasuringValue newMeasurement) {
                // TODO Auto-generated method stub

            }
        });
    }
}

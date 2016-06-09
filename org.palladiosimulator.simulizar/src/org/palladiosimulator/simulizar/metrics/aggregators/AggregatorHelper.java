package org.palladiosimulator.simulizar.metrics.aggregators;

import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public final class AggregatorHelper {

    private AggregatorHelper() {
    }

    public static void setupAggregator(final MeasurementSpecification measurementSpecification,
            final Calculator calculator, final RuntimeMeasurementModel runtimeMeasurementModel,
            final SimuComModel simuComModel) {
        if (calculator != null && measurementSpecification.isTriggersSelfAdaptations()) {
            if (!(measurementSpecification.getMetricDescription() instanceof NumericalBaseMetricDescription)) {
                throw new RuntimeException(
                        "measurementSpecification must conform to a NumericalBaseMetricDescription for double aggregation!");
            }

            final NumericalBaseMetricDescription metric = (NumericalBaseMetricDescription) measurementSpecification
                    .getMetricDescription();

            final IMeasurementSourceListener aggregator;

            switch (metric.getScopeOfValidity()) {
            case CONTINUOUS:
                aggregator = new ContinuousDoubleIntervalAggregator(simuComModel, runtimeMeasurementModel,
                        measurementSpecification);
                break;
            case DISCRETE:
                aggregator = new DiscreteDoubleIntervalAggregator(simuComModel, runtimeMeasurementModel,
                        measurementSpecification);
                break;
            default:
                throw new RuntimeException("Unsupported scope of validity: " + metric.getScopeOfValidity().getName());
            }

            calculator.addObserver(aggregator);
        }
    }
}
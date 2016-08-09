package org.palladiosimulator.simulizar.slidingwindow.utils;

import java.util.Objects;

import org.palladiosimulator.experimentanalysis.DiscardAllElementsPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.metricspec.MetricSpecPackage;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.monitorrepository.TimeDrivenAggregation;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.slidingwindow.aggregators.SlidingWindowStatisticalCharacterizationAggregator;
import org.palladiosimulator.simulizar.slidingwindow.impl.SimulizarSlidingWindow;
import org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement.SlidingWindowRuntimeMeasurementsRecorder;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public final class AggregatorHelper {

    private AggregatorHelper() {

    }

    public static void setupRuntimeAggregatorForMeasurementSpecification(
            final MeasurementSpecification measurementSpecification, final Calculator calculator,
            final RuntimeMeasurementModel runtimeMeasurementModel, final SimuComModel model) {

        if (measurementSpecification.isTriggersSelfAdaptations()
                && measurementSpecification.getMonitor().isActivated()) {

            // fails in case of wrong processing type
            TimeDrivenAggregation aggregation = checkAndGetProcessingType(measurementSpecification);

            // fails in case of wrong metric type
            NumericalBaseMetricDescription expectedMetric = checkAndGetNumericalBaseMetric(measurementSpecification);

            ISlidingWindowMoveOnStrategy moveOnStrategy = null;
            switch (expectedMetric.getScopeOfValidity()) {
            case CONTINUOUS:
                moveOnStrategy = new KeepLastElementPriorToLowerBoundStrategy();
                break;
            case DISCRETE:
                moveOnStrategy = new DiscardAllElementsPriorToLowerBoundStrategy();
                break;
            default:
                throw new AssertionError();
            }

            SlidingWindowStatisticalCharacterizationAggregator windowAggregator = new SlidingWindowStatisticalCharacterizationAggregator(
                    aggregation.getStatisticalCharacterization().getAggregator(expectedMetric));

            Objects.requireNonNull(calculator)
                    .addObserver(new SlidingWindowRecorder(
                            new SimulizarSlidingWindow(aggregation.getWindowLengthAsMeasure(),
                                    aggregation.getWindowIncrementAsMeasure(), expectedMetric, moveOnStrategy, model),
                            windowAggregator));

            // forward to PRM (i.e., RuntimeMeasurementModel)
            windowAggregator.addRecorder(new SlidingWindowRuntimeMeasurementsRecorder(runtimeMeasurementModel,
                    measurementSpecification, measurementSpecification.getMonitor().getMeasuringPoint()));
        }
    }

    private static NumericalBaseMetricDescription checkAndGetNumericalBaseMetric(
            final MeasurementSpecification measurementSpecification) {
        if (!MetricSpecPackage.Literals.NUMERICAL_BASE_METRIC_DESCRIPTION
                .isInstance(measurementSpecification.getMetricDescription())) {
            throw new IllegalArgumentException(
                    "Time driven aggregation of measurements (sliding window based) cannot be initialized:\n"
                            + "Currently, only "
                            + MetricSpecPackage.Literals.NUMERICAL_BASE_METRIC_DESCRIPTION.getName()
                            + "s are supported!\n" + "MeasuringPoint '"
                            + measurementSpecification.getMonitor().getMeasuringPoint() + "'\n" + "Affected Monitor: '"
                            + measurementSpecification.getMonitor().getEntityName() + "'");
        }

        return (NumericalBaseMetricDescription) measurementSpecification.getMetricDescription();
    }

    private static TimeDrivenAggregation checkAndGetProcessingType(
            final MeasurementSpecification measurementSpecification) {
        if (!MonitorRepositoryPackage.Literals.TIME_DRIVEN_AGGREGATION
                .isInstance(measurementSpecification.getProcessingType())) {
            throw new IllegalArgumentException(
                    "Time driven aggregation of measurements (sliding window based) cannot be initialized:\n"
                            + "MeasuringPoint '" + measurementSpecification.getMonitor().getMeasuringPoint() + "'\n"
                            + "MeasurementSpecification: '" + measurementSpecification.getName() + "'\n"
                            + "Affected Monitor: '" + measurementSpecification.getMonitor().getEntityName() + "'\n"
                            + MonitorRepositoryPackage.Literals.PROCESSING_TYPE.getName() + " must be "
                            + MonitorRepositoryPackage.Literals.TIME_DRIVEN_AGGREGATION.getName());
        }

        return (TimeDrivenAggregation) measurementSpecification.getProcessingType();
    }
}

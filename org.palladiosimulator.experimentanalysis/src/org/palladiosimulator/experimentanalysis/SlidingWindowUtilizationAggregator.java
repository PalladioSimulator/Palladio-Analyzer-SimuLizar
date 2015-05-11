package org.palladiosimulator.experimentanalysis;

import java.util.Iterator;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import org.jscience.physics.amount.Amount;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.recorderframework.IRecorder;

/**
 * This class is a {@link SlidingWindowAggregator} implementation which calculates the utilization
 * of an active resource based on a sequence of {@code (point in time, state of active resource)}
 * tuples collected by a {@link SlidingWindow}.<br>
 * The calculated utilization is passed to the attached {@link IRecorder} in the form of a
 * {@code (point in time, utilization of active resource)} tuple each time this implementation
 * processes new window data.
 * 
 * @see SlidingWindowRecorder
 * 
 * @author Florian Rosenthal
 *
 */
public class SlidingWindowUtilizationAggregator extends SlidingWindowAggregator {

    private static final MetricDescription EXPECTED_WINDOW_DATA_METRIC = MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE;
    private static final Amount<Duration> ZERO_DURATION = Amount.valueOf(0, SI.SECOND);

    /**
     * Initializes a new instance of the {@link SlidingWindowUtilizationAggregator} class with the
     * given parameter.
     * 
     * @param recorderToWriteInto
     *            An {@link IRecorder} this instance writes the aggregated window data into.
     *            Typically, a recorder that writes into a persistence framework like EDP 2 is
     *            passed here.
     * @throws IllegalArgumentException
     *             If the given {@link IRecorder} is {@code null}.
     */
    public SlidingWindowUtilizationAggregator(IRecorder recorderToWriteInto) {
        super(recorderToWriteInto);
    }
    
    @Override
    protected MeasuringValue processWindowData(Iterable<MeasuringValue> windowData,
            Measure<Double, Duration> windowLeftBound, Measure<Double, Duration> windowLength) {

        Amount<Duration> windowLeftBoundAmount = Amount.valueOf(windowLeftBound.getValue(), windowLeftBound.getUnit());
        Amount<Duration> windowLengthAmount = Amount.valueOf(windowLength.getValue(), windowLength.getUnit());
        Amount<Duration> windowRightBoundAmount = windowLengthAmount.plus(windowLeftBoundAmount);

        MeasuringValue result = null;
        Amount<Duration> busyTime = ZERO_DURATION;
        Iterator<MeasuringValue> iterator = windowData.iterator();

        if (iterator.hasNext()) {
            MeasuringValue currentMeasurement;
            long currentStateValue;
            Amount<Duration> currentPointInTimeAmount;
            MeasuringValue nextMeasurement = null;
            Amount<Duration> nextPointInTimeAmount = null;

            currentMeasurement = iterator.next(); // not null, as windowData not
                                                  // empty!
            currentStateValue = obtainStateValueFromMeasurement(currentMeasurement);
            currentPointInTimeAmount = obtainPointInTimeAmountFromMeasurement(currentMeasurement);
            boolean endLoop = false;

            do {
                // special treatment for amount that is out of window bounds:
                // consider only parts inside window
                currentPointInTimeAmount = currentPointInTimeAmount.isLessThan(windowLeftBoundAmount) ? windowLeftBoundAmount
                        : currentPointInTimeAmount;

                if (iterator.hasNext()) {
                    nextMeasurement = iterator.next();
                    nextPointInTimeAmount = obtainPointInTimeAmountFromMeasurement(nextMeasurement);
                } else {
                    // nextPointInTimeAmount is now the window's upper bound
                    nextPointInTimeAmount = windowRightBoundAmount;
                    endLoop = true; // no further elements available
                }
                // mac operation
                busyTime = busyTime.plus(nextPointInTimeAmount.minus(currentPointInTimeAmount).times(
                        Math.min(currentStateValue, 1)));

                if (!endLoop) {
                    currentMeasurement = nextMeasurement;
                    currentStateValue = obtainStateValueFromMeasurement(nextMeasurement);
                    currentPointInTimeAmount = nextPointInTimeAmount;
                }

            } while (!endLoop);
        }
        result = createUtilizationTupleMeasurement(busyTime, windowLengthAmount, windowRightBoundAmount);
        return result;
    }

    /**
     * Creates a {@link Measurement} containing the {@code utilization of active resource} captured at the given {@code point in time}.
     * @param busyTime An {@link Amount} indicating the busy time with the last sliding window period.
     * @param windowLength An {@link Amount} indicating the length of the sliding window.
     * @param pointInTime An {@link Amount} indicating the {@code point in time} this measurement is captured.
     * @return A {@link MeasuringValue} denoting the utilization {@code U} which is calculated as follows: {@code U = busyTime / windowLength}.
     */
    private static MeasuringValue createUtilizationTupleMeasurement(Amount<Duration> busyTime,
            Amount<Duration> windowLength, Amount<Duration> pointInTime) {
        
        assert windowLength.isGreaterThan(ZERO_DURATION);
        
        @SuppressWarnings("unchecked")
        Amount<Dimensionless> utilization = (Amount<Dimensionless>) busyTime.divide(windowLength);
        Measure<Double, Dimensionless> resultUtilizationMeasure = Measure.valueOf(utilization.doubleValue(Unit.ONE),
                Unit.ONE);
        Measure<Double, Duration> resultPointInTimeMeasure = Measure.valueOf(pointInTime.doubleValue(pointInTime.getUnit()),
                pointInTime.getUnit());
        return new TupleMeasurement(MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE,
                resultPointInTimeMeasure, resultUtilizationMeasure);
    }

    /**
     * Gets the {@code point in time} the given measurement was taken.
     * @param measurement A ({@code not null}) {@link MeasuringValue} instance containing a {@code point in time} measure.
     * @return An {@link Amount} that represents the {@code point in time} the given measurement was taken.
     */
    private static Amount<Duration> obtainPointInTimeAmountFromMeasurement(MeasuringValue measurement) {
        Measure<Double, Duration> measure = measurement
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        return Amount.valueOf(measure.getValue(), measure.getUnit());
    }

    /**
     * Gets the {@code state of active resource} value captured by the given measurement.
     * @param measurement A ({@code not null}) {@link MeasuringValue} instance containing a {@code state of active resource} measure.
     * @return A <b>nonnegative</b> long denoting the {@code state of active resource} value.
     */
    private static long obtainStateValueFromMeasurement(MeasuringValue measurement) {

        Measure<Long, ?> measure = measurement
                .getMeasureForMetric(MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC);

        return measure.getValue();
    }

    /**
     * Returns the {@link MetricDescription} all window data must adhere to in order to be processed
     * by this aggregator.
     * 
     * @return {@code MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE}
     */
    @Override
    public MetricDescription getExpectedWindowDataMetric() {
        return EXPECTED_WINDOW_DATA_METRIC;

    }
}

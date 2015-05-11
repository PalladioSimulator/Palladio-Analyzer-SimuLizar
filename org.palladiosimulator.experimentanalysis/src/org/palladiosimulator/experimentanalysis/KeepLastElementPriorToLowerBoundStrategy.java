package org.palladiosimulator.experimentanalysis;

import java.util.Deque;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.palladiosimulator.experimentanalysis.SlidingWindow.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;

/**
 * An implementation of {@link ISlidingWindowMoveOnStrategy} that keeps exactly
 * one measurement that is prior to the new lower bound of the
 * {@link SlidingWindow} after it moved on. More precisely, the very last
 * measurement prior to the new lower bound is maintained, while all preceding
 * ones are discarded. If all measurements are within the new bounds of the
 * window, this implementation does nothing. <br>
 * Main use case of this strategy is the calculation of a resource's utilization
 * (cf. {@link SlidingWindowUtilizationAggregator}).
 * 
 * @author Florian Rosenthal
 *
 */
public final class KeepLastElementPriorToLowerBoundStrategy implements
		ISlidingWindowMoveOnStrategy {

    private static boolean isFirstElementPriorToCurrentLowerBound(
			Deque<MeasuringValue> currentData,
			Measure<Double, Duration> newLowerBound) {
		assert !currentData.isEmpty();

		Measure<Double, Duration> pointInTime = currentData.peekFirst().getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
		return pointInTime.compareTo(newLowerBound) < 0;
	}

	@Override
	public void adjustData(Deque<MeasuringValue> currentData,
			Measure<Double, Duration> newLowerBound,
			Measure<Double, Duration> increment) {

		if (!currentData.isEmpty()
				&& isFirstElementPriorToCurrentLowerBound(currentData,
						newLowerBound)) {
			// this is the interesting case, otherwise: nothing to do as
			// all elements are in window
		    MeasuringValue first = currentData.pollFirst();
			while (!currentData.isEmpty()
					&& isFirstElementPriorToCurrentLowerBound(currentData,
							newLowerBound)) {
				first = currentData.pollFirst();
			}
			currentData.addFirst(first); // re-insert element

		}
	}
}

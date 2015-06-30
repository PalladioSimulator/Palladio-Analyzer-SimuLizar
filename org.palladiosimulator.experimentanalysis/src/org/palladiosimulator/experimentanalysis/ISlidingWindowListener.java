package org.palladiosimulator.experimentanalysis;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.metricspec.MetricDescription;

/**
 * This interface has to be implemented that want to observe {@link SlidingWindow}s. It provides a
 * callback method ({@link ISlidingWindowListener#onSlidingWindowFull(Iterable, Measure, Measure)}
 * that is triggered by each observed window once it is full, that is, just before it is about to
 * move on.<br>
 * As each {@link SlidingWindow} does only accepts measurements of a certain metric, only compatible
 * observers can be attached. Thus, observers have to also specify a metric they accept (cf.
 * {@link ISlidingWindowListener#getExpectedWindowDataMetric()}).
 * @see SlidingWindowAggregator
 * @see SlidingWindowRecorder
 * 
 * @author Florian Rosenthal
 *
 */
public interface ISlidingWindowListener {
    /**
     * This callback method is triggered by each observed window once it is full, that is, just
     * before it is about to move on.
     * 
     * @param windowData
     *            An {@link Iterable} containing the currently collected window data, that is, all
     *            {@link MeasuringValue}s within the window's current bounds.
     * @param windowLeftBound
     *            A {@link Measure} denoting the window's current lower/left bound.
     * @param windowLength
     *            A {@link Measure} denoting the current window length.
     */
    public void onSlidingWindowFull(Iterable<MeasuringValue> windowData, Measure<Double, Duration> windowLeftBound,
            Measure<Double, Duration> windowLength);

    /**
     * Gets the metric this listener is accepting.
     * @return A {@link MetricDescription} indicating the type of {@link SlidingWindow}s this listener can observe.
     * @see ISlidingWindowListener
     * @see SlidingWindow#getAcceptedMetric()
    */
    public MetricDescription getExpectedWindowDataMetric();

}

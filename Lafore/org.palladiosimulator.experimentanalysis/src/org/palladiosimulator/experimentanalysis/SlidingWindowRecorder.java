package org.palladiosimulator.experimentanalysis;

import java.util.Arrays;

import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.recorderframework.AbstractRecorder;
import org.palladiosimulator.recorderframework.config.IRecorderConfiguration;

/**
 * This class implements a {@link Recorder} which writes incoming data into a
 * sliding window rather than passing it on to a persistence framework like
 * EDP2.
 * 
 * @author Florian Rosenthal
 *
 */
public class SlidingWindowRecorder extends AbstractRecorder {

	private final SlidingWindow slidingWindow;

	/**
	 * Initializes a new instance of the {@link SlidingWindowRecorder} class
	 * with the given parameters.
	 * 
	 * @param slidingWindow
	 *            {@link SlidingWindow} to write incoming measurements into.
	 * @param listeners
	 *            A collection of {@link ISlidingWindowListener}s, typically
	 *            {@link SlidingWindowAggregator}s, that will be attached to the
	 *            given window ({@code null} is okay).
	 * 
	 * @throws IllegalArgumentException
	 *             If the given window is {@code null}.
	 */
	public SlidingWindowRecorder(SlidingWindow slidingWindow,
			Iterable<ISlidingWindowListener> listeners) {

		if (slidingWindow == null) {
			throw new IllegalArgumentException(
					"A sliding window always corresponds with a sliding window\n"
							+ "Thus, the passed window reference must not be null.");
		}
		this.slidingWindow = slidingWindow;
		if (listeners != null) {
			for (ISlidingWindowListener iSlidingWindowListener : listeners) {
				this.slidingWindow.addObserver(iSlidingWindowListener);
			}
		}
	}

	/**
	 * Initializes a new instance of the {@link SlidingWindowRecorder} class
	 * with the given parameters.
	 * 
	 * @param slidingWindow
	 *            {@link SlidingWindow} to write incoming measurements into.
	 * @param listener
	 *            A {@link ISlidingWindowListener}, typically a
	 *            {@link SlidingWindowAggregator}, that will be attached to the
	 *            given window.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given window is {@code null}.
	 */
	public SlidingWindowRecorder(SlidingWindow slidingWindow,
			ISlidingWindowListener listener) {
		this(slidingWindow, Arrays.asList(listener));
	}

	/**
	 * {@inheritDoc}<br>
	 * This implementation does nothing.
	 */
	@Override
	public void initialize(IRecorderConfiguration recorderConfiguration) {
	}

	/**
	 * {@inheritDoc}<br>
	 * That is, the given measurement is written into the associated
	 * {@link SlidingWindow}.
	 * 
	 * @throws IllegalArgumentException
	 *             If the given measurement is {@code null} or does not adhere
	 *             to the metric the associated window accepts.
	 * @throws IllegalStateException
	 *             If this window is not initialized, i.e.,
	 *             {@link SlidingWindow#initialize(SimuComModel)} has not been
	 *             called beforehand.
	 */
	@Override
	public void writeData(MeasuringValue measurement) {
		// just fill the sliding window
		this.slidingWindow.addMeasurement(measurement);
	}

	/**
	 * {@inheritDoc}<br>
	 * It has the corresponding {@link SlidingWindow} instance discard the
	 * collected data.
	 */
	@Override
	public void flush() {
		this.slidingWindow.flush();

	}
}
  
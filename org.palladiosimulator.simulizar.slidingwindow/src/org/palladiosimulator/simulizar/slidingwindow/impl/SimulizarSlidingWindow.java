package org.palladiosimulator.simulizar.slidingwindow.impl;

import javax.measure.Measurable;
import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import org.palladiosimulator.experimentanalysis.ISlidingWindowListener;
import org.palladiosimulator.experimentanalysis.SlidingWindow;
import org.palladiosimulator.experimentanalysis.SlidingWindowAggregator;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredSimulationEntity;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.ISimulationListener;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

/**
 * This class is a {@link SlidingWindow} subclass that is governed by and advances during simulation
 * runs.<br>
 * Once the window is full, i.e., the simulation time reaches the next multiple of the window
 * length, the window moves forward by a fixed increment and the collected measurements are
 * propagated to all connected {@link ISlidingWindowListener}s, e.g., certain
 * {@link SlidingWindowAggregator}s.<br>
 * 
 * 
 * @author Florian Rosenthal
 */
public class SimulizarSlidingWindow extends SlidingWindow {

    private ISimulationControl simControl = null;

    /**
     * Initializes a new instance of the {@link SimulizarSlidingWindow} class with the
     * given parameters.
     * 
     * @param windowLength
     *            The length of the window, given in any arbitrary {@link Duration}. Additionally,
     *            this measure also denotes the window increment.
     * @param acceptedMetrics
     *            As each window only accepts measurements that adhere to a certain metric, a
     *            {@link MetricDescription} of must be specified.
     * @param moveOnStrategy
     *            The {@link ISlidingWindowMoveOnStrategy} instance that defines how the collected
     *            data (i.e., the measurements) is adjusted when the window moves forward.
     * @param model
     *            The {@link SimuComModel} instance which governs this window.
     * @throws IllegalArgumentException
     *             In one of the following cases:
     *             <ul>
     *             <li>given window length is negative</li>
     *             <li>{@code acceptedMetrics}, {@code moveOnStrategy} or {@code model} is
     *             {@code null}</li>
     *             </ul>
     * @see #SlidingWindow(Measure, Measure, MetricDescription,
     *      ISlidingWindowMoveOnStrategy)
     */
    public SimulizarSlidingWindow(Measure<Double, Duration> windowLength, MetricDescription acceptedMetrics,
            ISlidingWindowMoveOnStrategy moveOnStrategy, SimuComModel model) {
        this(windowLength, windowLength, acceptedMetrics, moveOnStrategy, model);
    }

    /**
     * Initializes a new instance of the {@link SimulizarSlidingWindow} class with the
     * given parameters.
     * 
     * @param windowLength
     *            The length of the window, given in any arbitrary {@link Duration}.
     * @param increment
     *            This {@link Measure} indicates the increment by what the window is moved on, given
     *            in any arbitrary {@link Duration}.
     * @param acceptedMetrics
     *            As each window only accepts measurements that adhere to a certain metric, a
     *            {@link MetricDescription} of must be specified.
     * @param moveOnStrategy
     *            The {@link ISlidingWindowMoveOnStrategy} instance that defines how the collected
     *            data (i.e., the measurements) is adjusted when the window moves forward.
     * @param model
     *            The {@link SimuComModel} instance which governs this window.
     * @throws IllegalArgumentException
     *             In one of the following cases:
     *             <ul>
     *             <li>given window length or increment is negative</li>
     *             <li>{@code acceptedMetrics}, {@code moveOnStrategy} or {@code model} is
     *             {@code null}</li>
     *             </ul>
     */
    public SimulizarSlidingWindow(Measure<Double, Duration> windowLength, Measure<Double, Duration> increment,
            MetricDescription acceptedMetrics, ISlidingWindowMoveOnStrategy moveOnStrategy, SimuComModel model) {
        super(windowLength, increment, acceptedMetrics, moveOnStrategy);
        if (model == null) {
            throw new IllegalArgumentException("Sliding window must be initialized with a valid SimComModel instance.");
        }
        this.simControl = model.getSimulationControl();
        initializeTriggeredSimulationEntity(model);

    }

    private void initializeTriggeredSimulationEntity(SimuComModel model) {
        // ensure that point in times are given in seconds, as the simulation is
        // in sec
        new PeriodicallyTriggeredSimulationEntity(model, SimulizarSlidingWindow.this
                .getSpecifiedWindowLength().doubleValue(SI.SECOND), SimulizarSlidingWindow.this.getIncrement()
                .doubleValue(SI.SECOND)) {

            @Override
            protected void triggerInternal() {
                onWindowFullEvent();
            }
        };
        model.getConfiguration().addListener(new ISimulationListener() {

            @Override
            public void simulationStop() {
                onSimulationStop();
            }

            @Override
            public void simulationStart() {
            }
        });
    }

    /**
     * This method is invoked once the {@link ISimulationListener#simulationStop()} has occurred.
     */
    private void onSimulationStop() {
        Measurable<Duration> effectiveWindowLength = getEffectiveWindowLength();
        //only trigger event and move on if window is effective
        //effective window length might be equivalent to 0s if simulation time is integer multiple of window length 
        if (Double.compare(effectiveWindowLength.doubleValue(SI.SECOND), 0d) != 0) {
            onWindowFullEvent();
        }
    }
    
    /**
     * Gets the current upper bound of the window. Note that the bound might be smaller than
     * {@code getCurrentLowerBound() + windowLength} as defined in
     * {@link #SimulizarSlidingWindow(Measure, MetricDescription, ISlidingWindowMoveOnStrategy)}
     * or
     * {@link #SimulizarSlidingWindow(Measure, Measure, MetricDescription, ISlidingWindowMoveOnStrategy)}
     * .<br>
     * More precisely, this particular case occurs if the total simulation time is not an integer
     * multiple of the specified length.
     * 
     * @return A {@link Measure} denoting the current upper bound.
     * @see #getEffectiveWindowLength()
     */
    @Override
    public Measure<Double, Duration> getCurrentUpperBound() {
        double lowerBoundValue = this.getCurrentLowerBound().getValue();
        Unit<Duration> unit = this.getCurrentLowerBound().getUnit();
        
        // in the end smaller than leftBound + windowLength in case overall
        // simulation time
        // isn't a multiple of windowLength
        double upperBoundValue = Math.min(
                lowerBoundValue + this.getSpecifiedWindowLength().doubleValue(unit),
                this.simControl.getCurrentSimulationTime());

        return Measure.valueOf(upperBoundValue, unit);
    }

    /**
     * Gets the current, effective window length. Note that the effective window length might be
     * smaller than specified in
     * {@link #SimulizarSlidingWindow(Measure, MetricDescription, ISlidingWindowMoveOnStrategy)}
     * or
     * {@link #SimulizarSlidingWindow(Measure, Measure, MetricDescription, ISlidingWindowMoveOnStrategy)}
     * .<br>
     * More precisely, this particular case occurs if the total simulation time is not an integer
     * multiple of the specified length.
     * 
     * @return A {@link Measure} denoting the instantaneous effective window length.
     * @see #getCurrentUpperBound()
     */
    @Override
    public Measure<Double, Duration> getEffectiveWindowLength() {
        Unit<Duration> unit = this.getCurrentLowerBound().getUnit();
        // effective window length, might be smaller at the
        // end of simulation
        return Measure.valueOf(getCurrentUpperBound().doubleValue(unit)
                - this.getCurrentLowerBound().getValue(), unit);
    }
}

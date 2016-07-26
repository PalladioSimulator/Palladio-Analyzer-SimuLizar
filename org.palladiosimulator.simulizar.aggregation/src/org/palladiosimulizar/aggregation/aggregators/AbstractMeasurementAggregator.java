package org.palladiosimulizar.aggregation.aggregators;

import java.util.Objects;
import java.util.Optional;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.jscience.physics.amount.Amount;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementDrivenAggregation;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.monitorrepository.statisticalcharacterization.StatisticalCharacterizationAggregator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;

/**
 * This class implements a {@link PRMRecorder} dedicated to forwarding measurements resulting from a
 * {@link MeasurementDrivenAggregation} to the {@link RuntimeMeasurementModel}. Most of the
 * implementation is left open for concrete subclasses by the main feature of this class, a template
 * method for the handling of new, incoming measurements.
 * 
 * @author Florian Rosenthal
 *
 */
public abstract class AbstractMeasurementAggregator extends PRMRecorder implements IMeasurementSourceListener {

    private final StatisticalCharacterizationAggregator aggregator;
    private final NumericalBaseMetricDescription expectedMetric;
    private final int frequencyOfAggregation;

    private int measurementsUntilNextAggregation = 0;

    /**
     * Initializes a new instance of the {@link AbstractMeasurementAggregator} class with the given
     * parameters.
     * 
     * @param expectedMetric
     *            The expected {@link NumericalBaseMetricDescription} of the aggregated measurements
     *            to be forwarded to the runtime measurement model.
     * @param prmAccess
     *            The {@link RuntimeMeasurementModel} where the aggregation results are forwarded
     *            to.
     * @param aggregation
     *            The {@link MeasurementDrivenAggregation} model element indicating the way of
     *            measurement aggregation.
     * @throws NullPointerException
     *             In case any of the parameters is {@code null}.
     * @throws IllegalStateException
     *             If the value of the 'Frequency' attribute of the passed
     *             {@link MeasurementDrivenAggregation} is not positive.
     */
    AbstractMeasurementAggregator(NumericalBaseMetricDescription expectedMetric, RuntimeMeasurementModel prmAccess,
            MeasurementDrivenAggregation measurementDrivenAggregation) {

        super(prmAccess, Objects.requireNonNull(measurementDrivenAggregation).getMeasurementSpecification());
        this.expectedMetric = Objects.requireNonNull(expectedMetric);
        this.aggregator = measurementDrivenAggregation.getStatisticalCharacterization().getAggregator(expectedMetric);
        this.frequencyOfAggregation = measurementDrivenAggregation.getFrequency();
        if (this.frequencyOfAggregation < 1) {
            throw new IllegalStateException(
                    "Value of '" + MonitorRepositoryPackage.Literals.MEASUREMENT_DRIVEN_AGGREGATION__FREQUENCY.getName()
                            + "' attribute of '" + measurementDrivenAggregation.eClass().getName() + "' with id "
                            + measurementDrivenAggregation.getId() + " must be positive!");
        }
        resetCounter();
    }

    /**
     * Resets the counter for the next aggregation to the value specified by the 'Frequency'
     * attribute of the associated {@link MeasurementDrivenAggregation} model element.<br>
     * This method is called upon object construction and after the counter reached zero. In the
     * latter case, if an aggregation was done, the call directly happens after
     * {@link #onPostAggregate()} is invoked.
     * 
     * @see MeasurementDrivenAggregation#getFrequency()
     * @see #decrementCounter()
     * @see #onPostAggregate()
     */
    protected final void resetCounter() {
        this.measurementsUntilNextAggregation = this.frequencyOfAggregation;
    }

    /**
     * Decrements the counter for the next upcoming aggregation.<br>
     * This method is invoked directly after the collection of a new measurement.
     *
     * @see #collectMeasurement(MeasuringValue)
     * @see #resetCounter()
     * @see MeasurementDrivenAggregation#getFrequency()
     */
    protected final void decrementCounter() {
        assert this.measurementsUntilNextAggregation > 0;

        --this.measurementsUntilNextAggregation;
    }

    /**
     * {@inheritDoc}<br>
     * This method is implemented as a template method, where some steps are to be (re-)implemented
     * by the subclasses:
     * <ol>
     * <li>{@code // check whether received measurement is compliant with expected metric}</li>
     * <li>{@link #collectMeasurement(MeasuringValue)} is invoked</li>
     * <li>{@link #decrementCounter()} is invoked</li>
     * <li>
     * {@code // check whether counter for next upcoming aggregation has reached zero && aggregationRequired() == true}
     * </li>
     * </ol>
     * In case of an upcoming aggregation, the following steps are carried out additionally:
     * <ol>
     * <li>{@link #onPreAggregate()} is invoked</li>
     * <li>{@code // the actual aggregation is performed}</li>
     * <li>{@link #onPostAggregate()} is invoked</li>
     * <li>{@link #resetCounter()} is invoked (<b>Note:</b> Each time the counter has reached zero
     * this method is invoked, whether or not an aggregation was performed.)</li>
     * </ol>
     * 
     * @throws NullPointerException
     *             In case {@code newMeasurement == null}.
     * @throws IllegalStateException
     *             In case the received measurement is not compliant with the expected metric.
     */
    @Override
    public final void newMeasurementAvailable(MeasuringValue newMeasurement) {
        if (!Objects.requireNonNull(newMeasurement).isCompatibleWith(this.expectedMetric)
                && !MetricDescriptionUtility.isBaseMetricDescriptionSubsumedByMetricDescription(this.expectedMetric,
                        newMeasurement.getMetricDesciption())) {
            throw new IllegalStateException("Somehow a wrong measurement kind was passed.");
        }
        collectMeasurement(newMeasurement);
        decrementCounter();
        if (this.measurementsUntilNextAggregation == 0) {
            if (aggregationRequired()) {
                onPreAggregate();
                aggregate();
                onPostAggregate();
            }
            resetCounter();
        }
    }

    /**
     * {@inheritDoc}<br>
     * In particular, all measurements that are currently collected for aggregation are discarded
     * upon unregistering.
     * 
     * @see #clear()
     */
    @Override
    public void preUnregister() {
        super.detachFromPRM();
        clear();

    }

    /**
     * Discards all measurements collected for aggregation.
     */
    public abstract void clear();

    /**
     * This method has to be implemented by all subclasses. It is invoked by this instance each time
     * a new measurements has been collected, i.e., directly after
     * {@link #collectMeasurement(MeasuringValue)} and {@link #decrementCounter()} have been
     * invoked, <b>and</b> the counter for the next upcoming aggregation has reached zero.<br>
     * In case an aggregation is required, it is directly performed hereafter, yet preceded by the
     * invocation of {@link #onPreAggregate()} and followed by the invocation of
     * {@link #onPostAggregate()} and {@link #resetCounter()}.
     * 
     * @return {@code true} if an aggregation of the collected measurements shall be triggered,
     *         {@code false} otherwise.
     * 
     * @see #newMeasurementAvailable(MeasuringValue)
     * @see #onPreAggregate()
     * @see #onPostAggregate()
     */
    protected abstract boolean aggregationRequired();

    /**
     * Gets the left (lower) bound of the interval that all measurements that are aggregated lie in.
     * 
     * @return A non-negative {@link Amount} denoting the left bound of the interval.
     * @see #getIntervalEndTime()
     */
    protected abstract Amount<Duration> getIntervalStartTime();

    /**
     * Gets the right (upper) bound of the interval that all measurements that are aggregated lie
     * in.
     * 
     * @return A non-negative {@link Amount} denoting the right bound of the interval.
     * @see #getIntervalStartTime()
     */
    protected abstract Amount<Duration> getIntervalEndTime();

    /**
     * Gets the sequence of measurements to be aggregated.
     * 
     * @return An {@link Iterable} encapsulating the sequence of measurements to be aggregated.
     */
    protected abstract Iterable<MeasuringValue> getDataToAggregate();

    /**
     * This method has to be implemented by subclasses to collect a new measurement for aggregation.
     * 
     * @param newMeasurement
     *            A {@link MeasuringValue} denoting the measurement to include for aggregation. <br>
     *            It is ensured that the measurement passed here is not {@code null} and adheres to
     *            the expected metric, hence no more checks with regards to this have to be done by
     *            the implementations.
     * @see #decrementCounter()
     */
    protected abstract void collectMeasurement(MeasuringValue newMeasurement);

    /**
     * This method is invoked directly before an aggregation of the measurements is to be performed
     * and is intended to be overriden by subclasses to include implementation-specific behavior.
     * <br>
     * The default implementation does nothing.
     * 
     * @see #aggregationRequired()
     */
    protected void onPreAggregate() {
    }

    /**
     * This method is invoked directly after an aggregation of the measurements has been performed
     * and is intended to be overriden by subclasses to include implementation-specific behavior.
     * <br>
     * The default implementation does nothing.
     * 
     * @see #aggregationRequired()
     */
    protected void onPostAggregate() {
    }

    /**
     * Gets the numerical base metric description of the measurements that are to be forwarded to
     * the runtime measurements model.
     * 
     * @return The expected {@link NumericalBaseMetricDescription}.
     */
    protected final NumericalBaseMetricDescription getExpectedMetric() {
        return this.expectedMetric;
    }

    /**
     * Convenience method for all subclasses to obtain the point in time (which is assumed to be
     * present) a measurement was taken.
     *
     * @param measurement
     *            A {@link MeasuringValue} representing the measurement.
     * @return The corresponding point in time, expressed in terms of an {@link Amount}.
     */
    protected static Amount<Duration> getPointInTimeOfMeasurement(MeasuringValue measurement) {
        assert measurement != null;

        Measure<Double, Duration> pointInTimeMeasure = measurement
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);

        return Amount.valueOf(pointInTimeMeasure.getValue(), pointInTimeMeasure.getUnit());
    }

    private void aggregate() {
        MeasuringValue aggregatedData = this.aggregator.aggregateData(getDataToAggregate(), getIntervalStartTime(),
                getIntervalEndTime(), Optional.empty());

        // forward aggregated data (expressed as double in default unit of numerical base metric)
        super.updateMeasurementValue(aggregatedData.getMeasureForMetric(this.expectedMetric)
                .doubleValue(this.expectedMetric.getDefaultUnit()));
    }
}

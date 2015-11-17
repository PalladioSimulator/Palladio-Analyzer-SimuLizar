package org.palladiosimulator.simulizar.utils;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;

import de.uka.ipd.sdq.simulation.abstractsimengine.SimCondition;
import de.uka.ipd.sdq.statistics.IBatchAlgorithm;
import de.uka.ipd.sdq.statistics.estimation.ConfidenceInterval;
import de.uka.ipd.sdq.statistics.estimation.IConfidenceEstimator;

/**
 * Provides a stop condition which determines when to stop based on the confidence interval around a
 * point estimation (e.g. mean) for multiple runs.
 *
 * @author Matthias Becker
 *
 */
public class MultipleRunsConfidenceStopCondition implements SimCondition, IMeasurementSourceListener {

    private static final Logger LOGGER = Logger.getLogger(MultipleRunsConfidenceStopCondition.class);

    /** mean of the observations and the corresponding confidence interval */
    private ConfidenceInterval confidence;

    private boolean confidenceReached = false;

    private final IBatchAlgorithm batchAlgorithm;

    private final IConfidenceEstimator estimator;

    private final double confidenceLevel;

    private final double halfWidth;

    private int minBatches;

    /**
     *
     * @param model
     * @param batchAlgorithm
     * @param confidenceLevel
     *            the confidence level. Use values between 0 and 1.
     * @param halfWidth
     *            the relative half width of the target confidence interval. Use values between 0
     *            and 1.
     */
    public MultipleRunsConfidenceStopCondition(final IBatchAlgorithm batchAlgorithm,
            final IConfidenceEstimator estimator, final double confidenceLevel, final double halfWidth) {
        this.batchAlgorithm = batchAlgorithm;
        this.estimator = estimator;
        this.confidenceLevel = confidenceLevel;
        this.halfWidth = halfWidth;

        this.minBatches = 0;
    }

    @Override
    public boolean check() {
        return this.confidenceReached;
    }

    @Override
    public void newMeasurementAvailable(final MeasuringValue resultTuple) {
        final Measure<Double, Duration> reconfigurationTimeMeasure = resultTuple
                .getMeasureForMetric(MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC);
        final double reconfigurationTime = reconfigurationTimeMeasure.doubleValue(SI.SECOND);

        this.batchAlgorithm.offerSample(reconfigurationTime);
        if (this.batchAlgorithm.hasValidBatches() && this.batchAlgorithm.getBatchMeans().size() >= this.minBatches) {
            // estimate actual confidence interval
            final ConfidenceInterval ci = this.estimator.estimateConfidence(this.batchAlgorithm.getBatchMeans(),
                    this.confidenceLevel);

            if (ci != null) {
                // construct target confidence interval
                final ConfidenceInterval targetCI = new ConfidenceInterval(ci.getMean(), this.halfWidth,
                        this.confidenceLevel);

                if (targetCI.contains(ci)) {
                    if (LOGGER.isEnabledFor(Level.INFO)) {
                        LOGGER.info("Requested confidence reached.");
                    }
                    this.confidenceReached = true;
                    this.confidence = ci;

                    // request another batch in order to proceed with improving
                    // confidence interval's half-width until the simulation
                    // actually stops.
                    this.minBatches = this.batchAlgorithm.getBatchMeans().size() + 1;
                } else {
                    if (LOGGER.isEnabledFor(Level.INFO)) {
                        LOGGER.info("Requested confidence not yet reached.");
                    }

                    // request another batch in order to reduce the confidence
                    // interval's half-width
                    this.minBatches = this.batchAlgorithm.getBatchMeans().size() + 1;
                }
                if (LOGGER.isEnabledFor(Level.INFO)) {
                    LOGGER.info("Current confidence interval: Mean " + ci.getMean() + ", " + this.confidenceLevel * 100
                            + "% Confidence Interval " + "[" + ci.getLowerBound() + "," + ci.getUpperBound() + "]");
                }
            }
        }
    }

    public ConfidenceInterval getConfidence() {
        return this.confidence;
    }

    @Override
    public void preUnregister() {
        // TODO Auto-generated method stub
    }

}

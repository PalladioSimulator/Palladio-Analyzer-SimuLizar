package org.palladiosimulator.simulizar.elasticity.aggregator;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.stopcondition.ConfidenceStopCondition;
import de.uka.ipd.sdq.statistics.IBatchAlgorithm;
import de.uka.ipd.sdq.statistics.estimation.ConfidenceInterval;
import de.uka.ipd.sdq.statistics.estimation.IConfidenceEstimator;

public class ReconfigurationTimeAggregatorWithConfidence implements IMeasurementSourceListener {

	private static final Logger LOGGER = Logger.getLogger(ConfidenceStopCondition.class);

    private final SimuComModel model;

    private final String usageScenarioName;

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
     */
    public ReconfigurationTimeAggregatorWithConfidence(final SimuComModel model, final IBatchAlgorithm batchAlgorithm,
            final IConfidenceEstimator estimator, final double confidenceLevel, final double halfWidth) {
    	this.model = model;
        this.batchAlgorithm = batchAlgorithm;
        this.estimator = estimator;
        this.confidenceLevel = confidenceLevel;
        this.halfWidth = halfWidth;

        if (model.getConfiguration().getConfidenceModelElementName() == null) {
            throw new RuntimeException(
                    "SimuCom tried to set up a ConfidenceStopCondition, but no usage scenario name was given to measure the confidence for.");
        }
        this.usageScenarioName = model.getConfiguration().getConfidenceModelElementName();
        this.minBatches = 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.palladiosimulator.measurementframework.IMeasurementSourceListener#newMeasurementAvailable
     * (org.palladiosimulator.measurementframework.AbstractMeasureProvider)
     */
    @Override
    public void newMeasurementAvailable(final MeasuringValue measurement) {
    	final Measure<Double, Duration> responseTimeMeasure = measurement
                .getMeasureForMetric(MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC);
        final double responseTime = responseTimeMeasure.doubleValue(SI.SECOND);

        batchAlgorithm.offerSample(responseTime);
        if (batchAlgorithm.hasValidBatches() && batchAlgorithm.getBatchMeans().size() >= minBatches) {
            // estimate actual confidence interval
            final ConfidenceInterval ci = estimator.estimateConfidence(batchAlgorithm.getBatchMeans(), confidenceLevel);

            if (ci != null) {
                // construct target confidence interval
                final ConfidenceInterval targetCI = new ConfidenceInterval(ci.getMean(), halfWidth, confidenceLevel);

                if (targetCI.contains(ci)) {
                    if (LOGGER.isEnabledFor(Level.INFO)) {
                        LOGGER.info("Requested confidence reached.");
                    }
                    confidenceReached = true;
                    this.confidence = ci;

                    // request another batch in order to proceed with improving
                    // confidence interval's half-width until the simulation
                    // actually stops.
                    minBatches = batchAlgorithm.getBatchMeans().size() + 1;
                } else {
                    if (LOGGER.isEnabledFor(Level.INFO)) {
                        LOGGER.info("Requested confidence not yet reached.");
                    }

                    // request another batch in order to reduce the confidence
                    // interval's half-width
                    minBatches = batchAlgorithm.getBatchMeans().size() + 1;
                }
                if (LOGGER.isEnabledFor(Level.INFO)) {
                    LOGGER.info("Current confidence interval: Mean " + ci.getMean() + ", " + confidenceLevel * 100
                            + "% Confidence Interval " + "[" + ci.getLowerBound() + "," + ci.getUpperBound() + "]");
                }
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see org.palladiosimulator.measurementframework.IMeasurementSourceListener#preUnregister()
     */
    @Override
    public void preUnregister() {
        // Nothing to do
    }

	public boolean isConfidenceReached() {
		return confidenceReached;
	}

	public void setConfidenceReached(boolean confidenceReached) {
		this.confidenceReached = confidenceReached;
	}
    
}

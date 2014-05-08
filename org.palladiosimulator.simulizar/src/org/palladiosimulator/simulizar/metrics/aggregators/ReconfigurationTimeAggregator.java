/**
 * 
 */
package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.LinkedList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;

/**
 * @author Matthias
 * 
 */
public class ReconfigurationTimeAggregator implements IMeasurementSourceListener {

    private final List<Double> reconfigurationTimes;

    /**
     * 
     */
    public ReconfigurationTimeAggregator() {
        this.reconfigurationTimes = new LinkedList<Double>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.palladiosimulator.measurementspec.IMeasurementSourceListener#newMeasurementAvailable(
     * org.palladiosimulator.measurementspec.Measurement)
     */
    @Override
    public void newMeasurementAvailable(Measurement measurement) {
        final Measure<Double, Duration> currentSimulationTimeMeasure = measurement
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        final double simulationTime = currentSimulationTimeMeasure.doubleValue(SI.SECOND);

        this.reconfigurationTimes.add(currentSimulationTimeMeasure.doubleValue(SI.SECOND));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.measurementspec.IMeasurementSourceListener#preUnregister()
     */
    @Override
    public void preUnregister() {
        // TODO Auto-generated method stub

    }

}

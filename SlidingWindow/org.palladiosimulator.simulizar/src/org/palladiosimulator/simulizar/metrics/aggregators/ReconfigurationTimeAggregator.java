/**
 * 
 */
package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.LinkedList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.palladiosimulator.measurementframework.Measurement;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
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
     * org.palladiosimulator.measurementframework.IMeasurementSourceListener#newMeasurementAvailable
     * (org.palladiosimulator.measurementframework.AbstractMeasureProvider)
     */
    @Override
    public void newMeasurementAvailable(Measurement measurement) {
        final Measure<Double, Duration> currentSimulationTimeMeasure = measurement
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        final double simulationTime = currentSimulationTimeMeasure.doubleValue(SI.SECOND);

        this.reconfigurationTimes.add(simulationTime);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.measurementframework.IMeasurementSourceListener#preUnregister()
     */
    @Override
    public void preUnregister() {
        // TODO Auto-generated method stub

    }

}

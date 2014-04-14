/**
 * 
 */
package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.LinkedList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.palladiosimulator.measurementspec.IMeasurementSourceListener;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.metricspec.MetricDescriptionConstants;

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

    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.probespec.framework.measurements.IMeasurementSourceListener#newMeasurementAvailable(de.uka.ipd.sdq.probespec.framework.measurements.Measurement)
     */
    @Override
    public void newMeasurementAvailable(Measurement measurement) {
        final Measure<Double,Duration> currentSimulationTimeMeasure = measurement.getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        final double simulationTime = currentSimulationTimeMeasure.doubleValue(SI.SECOND);

        this.reconfigurationTimes.add(currentSimulationTimeMeasure.doubleValue(SI.SECOND));
    }

    /* (non-Javadoc)
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorListener#preUnregister()
     */
    @Override
    public void preUnregister() {
        // TODO Auto-generated method stub

    }

}

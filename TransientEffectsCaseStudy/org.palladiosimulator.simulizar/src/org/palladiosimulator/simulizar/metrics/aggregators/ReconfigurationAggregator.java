package org.palladiosimulator.simulizar.metrics.aggregators;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.quantity.Quantity;
import javax.measure.unit.SI;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;

/**
 * @author Matthias
 *
 */
public class ReconfigurationAggregator extends PRMRecorder implements IMeasurementSourceListener {

    public ReconfigurationAggregator(final RuntimeMeasurementModel runtimeMeasurementAccess,
            final MeasurementSpecification measurementSpecification, final Calculator responseTimeCalculator,
            final MeasuringPoint measuringPoint) {
        super(runtimeMeasurementAccess, measurementSpecification, measuringPoint);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void preUnregister() {
        // TODO Auto-generated method stub

    }

    @Override
    public void newMeasurementAvailable(final MeasuringValue measurement) {
        Measure<Double, Duration> reconfigurationTime = measurement.getMeasureForMetric(MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC);
        Measure<Double, Duration> reconfigurationPointInTime = measurement.getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        this.updateMeasurementValue(reconfigurationPointInTime.doubleValue(SI.SECOND) - reconfigurationTime.doubleValue(SI.SECOND));
    }

}

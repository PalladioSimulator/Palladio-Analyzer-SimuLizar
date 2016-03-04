package org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement;

import java.util.Arrays;
import java.util.Objects;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.recorderframework.IRecorder;
import org.palladiosimulator.recorderframework.config.IRecorderConfiguration;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;

public class SlidingWindowRuntimeMeasurementsRecorder extends PRMRecorder implements IRecorder {

    private static final NumericalBaseMetricDescription POINT_IN_TIME_METRIC = (NumericalBaseMetricDescription) MetricDescriptionConstants.POINT_IN_TIME_METRIC;
    private final NumericalBaseMetricDescription dataMetric;

    public SlidingWindowRuntimeMeasurementsRecorder(RuntimeMeasurementModel rmModel,
            MeasurementSpecification measurementSpecification, MeasuringPoint measuringPoint) {
        super(Objects.requireNonNull(rmModel), Objects.requireNonNull(measurementSpecification),
                Objects.requireNonNull(measuringPoint));
        this.dataMetric = getDataMetric();
    }

    private NumericalBaseMetricDescription getDataMetric() {
        return Arrays
                .stream(MetricDescriptionUtility
                        .toBaseMetricDescriptions(getMeasurementSpecification().getMetricDescription()))
                .filter(m -> !MetricDescriptionUtility.metricDescriptionIdsEqual(m, POINT_IN_TIME_METRIC)).findAny()
                .map(m -> (NumericalBaseMetricDescription) m)
                .orElseThrow(() -> new IllegalArgumentException("Data metric could not be found."));

    }

    @Override
    public void initialize(IRecorderConfiguration recorderConfiguration) {
    }

    @Override
    public void writeData(MeasuringValue measurement) {
        newMeasurementAvailable(measurement);

    }

    @Override
    public void flush() {
    }

    @Override
    public void newMeasurementAvailable(MeasuringValue newMeasurement) {
        if (newMeasurement == null
                || !newMeasurement.isCompatibleWith(getMeasurementSpecification().getMetricDescription())) {
            throw new IllegalArgumentException("Incompatible measurement received!");
        }
        Measure<Double, Quantity> measure = newMeasurement.getMeasureForMetric(this.dataMetric);
        // forward value (expressed as double in receiving unit!) to RuntimeMeasurementModel
        updateMeasurementValue(measure.doubleValue(measure.getUnit()));
    }

    @Override
    public void preUnregister() {
        detachFromPRM();
    }
}

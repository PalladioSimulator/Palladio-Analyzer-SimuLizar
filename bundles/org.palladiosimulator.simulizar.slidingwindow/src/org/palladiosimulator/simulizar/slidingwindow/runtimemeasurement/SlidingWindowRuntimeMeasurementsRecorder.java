package org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement;

import java.util.Arrays;
import java.util.Objects;

import jakarta.measure.Measure;
import jakarta.measure.quantity.Quantity;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.recorderframework.IRecorder;
import org.palladiosimulator.recorderframework.config.IRecorderConfiguration;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;

/**
 * This class is responsible for propagating sliding window based measurements from
 * {@link MeasurementSpecification}s to the RuntimeMeasurementModel (formerly known as PRM).<br>
 * Examples of such measurements are power or energy consumption measurements, or the sliding window
 * based computation of utilization.<br>
 * 
 * @author Florian Rosenthal
 *
 */
public class SlidingWindowRuntimeMeasurementsRecorder extends PRMRecorder implements IRecorder {

    private static final NumericalBaseMetricDescription POINT_IN_TIME_METRIC = (NumericalBaseMetricDescription) MetricDescriptionConstants.POINT_IN_TIME_METRIC;
    private final NumericalBaseMetricDescription dataMetric;

    /**
     * Initializes a new instance of the {@link SlidingWindowRuntimeMeasurementsRecorder} class with
     * the given arguments.
     * 
     * @param rmModel
     *            The {@link RuntimeMeasurementModel} all incoming data shall be forwarded to.
     * @param measurementSpecification
     *            The {@link MeasurementSpecification} as defined in a {@link MonitorRepository}
     *            model corresponding to the measurements to be propagated.
     * @param measuringPoint
     *            The {@link MeasuringPoint} to be used for the {@link RuntimeMeasurement}.
     * @throws NullPointerException
     *             In case any argument is {@code null}.
     * @see #SlidingWindowRuntimeMeasurementsRecorder(RuntimeMeasurementModel,
     *      MeasurementSpecification)
     */
    public SlidingWindowRuntimeMeasurementsRecorder(final RuntimeMeasurementModel rmModel,
            final MeasurementSpecification measurementSpecification, final MeasuringPoint measuringPoint) {
        super(Objects.requireNonNull(rmModel), Objects.requireNonNull(measurementSpecification),
                Objects.requireNonNull(measuringPoint));
        this.dataMetric = getDataMetric();
    }

    /**
     * Initializes a new instance of the {@link SlidingWindowRuntimeMeasurementsRecorder} class with
     * the given arguments.<br>
     * The {@link MeasuringPoint} to be used by the {@link RuntimeMeasurement} is obtained from the
     * {@link Monitor} associated with the passed {@link MeasurementSpecification}.
     * 
     * @param rmModel
     *            The {@link RuntimeMeasurementModel} all incoming data shall be forwarded to.
     * @param measurementSpecification
     *            The {@link MeasurementSpecification} as defined in a {@link MonitorRepository}
     *            model corresponding to the measurements to be propagated.
     * @throws NullPointerException
     *             In case either argument is {@code null}.
     * @see #SlidingWindowRuntimeMeasurementsRecorder(RuntimeMeasurementModel,
     *      MeasurementSpecification, MeasuringPoint)
     */
    public SlidingWindowRuntimeMeasurementsRecorder(final RuntimeMeasurementModel rmModel,
            final MeasurementSpecification measurementSpecification) {
        this(rmModel, Objects.requireNonNull(measurementSpecification),
                measurementSpecification.getMonitor().getMeasuringPoint());
    }

    private NumericalBaseMetricDescription getDataMetric() {
        // find the base matric of the data:
        // any metric that is not point in time, such as state of active resource,
        // or, if all base metrics are point in time, return point in time
        return Arrays
                .stream(MetricDescriptionUtility
                        .toBaseMetricDescriptions(getMeasurementSpecification().getMetricDescription()))
                .filter(m -> !MetricDescriptionUtility.metricDescriptionIdsEqual(m, POINT_IN_TIME_METRIC)).findAny()
                .map(m -> (NumericalBaseMetricDescription) m).orElse(POINT_IN_TIME_METRIC);

    }

    /**
     * {@inheritDoc}<br>
     * This implementation does nothing.
     */
    @Override
    public void initialize(final IRecorderConfiguration recorderConfiguration) {
    }

    @Override
    public void writeData(final MeasuringValue measurement) {
        newMeasurementAvailable(measurement);

    }

    /**
     * {@inheritDoc}<br>
     * This implementation does nothing.
     */
    @Override
    public void flush() {
    }

    /**
     * {@inheritDoc}<br>
     * This implementation forwards the measured value obtained from the given
     * {@link MeasuringValue} to the {@link RuntimeMeasurementModel}.
     * 
     * @throws NullPointerException
     *             In case {@link newMeasurement == null}.
     * @throws IllegalArgumentException
     *             In case {@code newMeasurement} is not compatible with the expected data metric.
     */
    @Override
    public void newMeasurementAvailable(final MeasuringValue newMeasurement) {
        if (Objects.requireNonNull(newMeasurement)
                .isCompatibleWith(getMeasurementSpecification().getMetricDescription())
                || MetricDescriptionUtility.isBaseMetricDescriptionSubsumedByMetricDescription(this.dataMetric,
                        newMeasurement.getMetricDesciption())) {

            Measure<Double, Quantity> measure = newMeasurement.getMeasureForMetric(this.dataMetric);
            // forward value (expressed as double in default unit!) to RuntimeMeasurementModel
            updateMeasurementValue(measure.doubleValue(this.dataMetric.getDefaultUnit()));
        } else {
            throw new IllegalArgumentException("Incompatible measurement received!");
        }
    }

    @Override
    public void preUnregister() {
        detachFromPRM();
    }
}

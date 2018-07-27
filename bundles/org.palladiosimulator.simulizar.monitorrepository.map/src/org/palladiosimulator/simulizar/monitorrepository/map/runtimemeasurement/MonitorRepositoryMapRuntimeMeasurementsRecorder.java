package org.palladiosimulator.simulizar.monitorrepository.map.runtimemeasurement;

import java.util.Objects;

import javax.measure.quantity.Quantity;
import javax.measure.unit.Unit;

import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSpecPackage;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.ProcessingType;
import org.palladiosimulator.monitorrepository.map.Map;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;
import org.palladiosimulator.simulizar.monitorrepository.map.probeframework.MonitorRepositoryMapProbeFrameworkListenerDecorator;

/**
 * This class is responsible for propagating the measurements that have undergone a transformation
 * according to the {@link Map} {@link ProcessingType} to the {@link RuntimeMeasurementModel}
 * (formerly known as PRM).
 *
 * @see Map#apply(MeasuringValue)
 * @see MonitorRepositoryMapProbeFrameworkListenerDecorator
 * @author Florian Rosenthal
 *
 */
public class MonitorRepositoryMapRuntimeMeasurementsRecorder extends PRMRecorder implements IMeasurementSourceListener {

    private final Map mapProcessingType;
    private final MetricDescription expectedInputMetric;
    private final NumericalBaseMetricDescription expectedOutputMetric;
    private final Unit<Quantity> defaultOutputUnit;
    private final boolean expectsBaseMetric;

    /**
     * Initializes a new instance of the {@link MonitorRepositoryMapRuntimeMeasurementsRecorder}
     * class with the given arguments.
     * 
     * @param rmModel
     *            The {@link RuntimeMeasurementModel} which the transformed measurements shall be
     *            forwarded to.
     * @param mapProcessingType
     *            The {@link Map} describing the transformation to be applied.
     * @throws NullPointerException
     *             In case either argument is {@code null}.
     * @throws IllegalStateException
     *             If the metric description of the transformed measurement is not a
     *             {@link NumericalBaseMetricDescription} as only numeric value can be forwarded so
     *             far.
     * @see Map#getOutputMetricDescription()
     */
    public MonitorRepositoryMapRuntimeMeasurementsRecorder(final RuntimeMeasurementModel rmModel,
            final Map mapProcessingType) {
        super(Objects.requireNonNull(rmModel), Objects.requireNonNull(mapProcessingType).getMeasurementSpecification());

        this.mapProcessingType = mapProcessingType;
        this.expectedInputMetric = getMeasurementSpecification().getMetricDescription();
        this.expectsBaseMetric = MetricSpecPackage.Literals.BASE_METRIC_DESCRIPTION
                .isInstance(this.expectedInputMetric);
        if (!MetricSpecPackage.Literals.NUMERICAL_BASE_METRIC_DESCRIPTION
                .isInstance(this.mapProcessingType.getOutputMetricDescription())) {
            throw new IllegalStateException("So far, only numerical values can be forwarded to "
                    + RuntimeMeasurementPackage.Literals.RUNTIME_MEASUREMENT_MODEL.getName()
                    + "s. Hence, the output metric of the measurement mapping ' " + mapProcessingType.getEntityName()
                    + "' (id: " + mapProcessingType.getId() + ") must be a "
                    + MetricSpecPackage.Literals.NUMERICAL_BASE_METRIC_DESCRIPTION.getName());
        }
        this.expectedOutputMetric = (NumericalBaseMetricDescription) this.mapProcessingType
                .getOutputMetricDescription();
        this.defaultOutputUnit = this.expectedOutputMetric.getDefaultUnit();

    }

    /**
     * {@inheritDoc}
     * 
     * @throws NullPointerException
     *             In case {@code newMeasurement == null}.
     * @throws IllegalStateException
     *             In case the received measurement is not compliant with the expected metric.
     */
    @Override
    public void newMeasurementAvailable(final MeasuringValue newMeasurement) {
        if (Objects.requireNonNull(newMeasurement).isCompatibleWith(this.expectedInputMetric) || (this.expectsBaseMetric
                && MetricDescriptionUtility.isBaseMetricDescriptionSubsumedByMetricDescription(
                        (BaseMetricDescription) this.expectedInputMetric, newMeasurement.getMetricDesciption()))) {

            MeasuringValue transformedMeasurement = this.mapProcessingType.apply(newMeasurement);

            // forward transformed data (expressed as double in default unit of output numerical
            // base metric)
            super.updateMeasurementValue(transformedMeasurement.getMeasureForMetric(this.expectedOutputMetric)
                    .doubleValue(this.defaultOutputUnit));
        } else {
            throw new IllegalStateException("Somehow a wrong measurement kind was passed.");
        }
    }

    @Override
    public void preUnregister() {
        this.detachFromPRM();
    }

}

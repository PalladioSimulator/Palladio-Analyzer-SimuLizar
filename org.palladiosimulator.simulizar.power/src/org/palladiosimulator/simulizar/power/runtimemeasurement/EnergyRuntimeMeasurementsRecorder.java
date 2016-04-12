package org.palladiosimulator.simulizar.power.runtimemeasurement;

import javax.measure.Measure;
import javax.measure.quantity.Energy;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;

/**
 * This class is responsible for propagating {@link MeasurementSpecification}s related to energy
 * consumption to the RuntimeMeasurementModel (formerly known as PRM).<br>
 * Being as this class implements the {@link IMeasurementSourceListener} interface, instances can be
 * attached to any object that produces {@link MeasuringValue}s which adhere to the
 * {@link MetricDescriptionConstants#CUMULATIVE_ENERGY_CONSUMPTION_TUPLE} metric.
 * 
 * @author Florian Rosenthal
 *
 */
public class EnergyRuntimeMeasurementsRecorder extends PRMRecorder implements IMeasurementSourceListener {

    /**
     * Initializes a new instance of the {@link EnergyRuntimeMeasurementsRecorder} class with the given
     * arguments.
     * 
     * @param rmModel
     *            The {@link RuntimeMeasurementModel} model instance the power consumption measurements shall be
     *            forwarded to.
     * @param measurementSpecification
     *            The {@link MeasurementSpecification} as defined in a {@link MonitorRepository}.
     * @param measuringPoint
     *            The {@link MeasuringPoint} that is associated with the given
     *            {@code measurementSpecification}.
     * @throws IllegalArgumentException
     *             In case one of the arguments is {@code null}.
     */
    public EnergyRuntimeMeasurementsRecorder(RuntimeMeasurementModel rmModel,
            MeasurementSpecification measurementSpecification, MeasuringPoint measuringPoint) {
        super(rmModel, measurementSpecification, measuringPoint);

        if (rmModel == null || measurementSpecification == null || measuringPoint == null) {
            throw new IllegalArgumentException("At least one argument is null.");
        }
        if (!MetricDescriptionUtility.metricDescriptionIdsEqual(measurementSpecification.getMetricDescription(),
                MetricDescriptionConstants.CUMULATIVE_ENERGY_CONSUMPTION_TUPLE)) {
            throw new IllegalArgumentException("Metric of given MeasurementSpecification instance must be "
                    + MetricDescriptionConstants.CUMULATIVE_ENERGY_CONSUMPTION_TUPLE.getName() + "!");
        }
    }

    /**
     * {@inheritDoc}<br>
     * When this method is called by the observed subject, the given {@link MeasuringValue} is
     * propagated to the associated {@link RuntimeMeasurementModel} instance.
     * 
     * @throws IllegalArgumentException
     *             In case the passed {@link MeasuringValue} is not compatible with the
     *             {@link MetricDescriptionConstants#CUMULATIVE_ENERGY_CONSUMPTION_TUPLE} metric.
     */
    @Override
    public void newMeasurementAvailable(MeasuringValue newMeasurement) {
        if (newMeasurement == null
                || !newMeasurement.isCompatibleWith(MetricDescriptionConstants.CUMULATIVE_ENERGY_CONSUMPTION_TUPLE)) {
            throw new IllegalArgumentException("New available measurement is not an energy consumption tuple!");
        }
        Measure<Double, Energy> energyMeasure = newMeasurement
                .getMeasureForMetric(MetricDescriptionConstants.ENERGY_CONSUMPTION);
        // forward power value (expressed as double in receiving unit!) to PRM
        updateMeasurementValue(energyMeasure.doubleValue(energyMeasure.getUnit()));
    }

    @Override
    public void preUnregister() {
        detachFromPRM();
    }

}

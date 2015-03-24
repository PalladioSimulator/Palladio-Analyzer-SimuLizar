package org.palladiosimulator.simulizar.metrics.powerconsumption;

import javax.measure.Measure;
import javax.measure.quantity.Power;

import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;

import de.fzi.power.infrastructure.PowerProvidingEntity;

/**
 * This class is responsible for propagating {@link MeasurementSpecification}s related to power consumption to the PRM.<br>
 * Being as this class implements the {@link IMeasurementSourceListener} interface, instances it can be attached to any object
 * that produces {@link MeasuringValue}s which adhere to the {@link MetricDescriptionConstants#POWER_CONSUMPTION_TUPLE} metric. 
 * @author Florian Rosenthal
 *
 */
public class PowerConsumptionPrmRecorder extends PRMRecorder implements IMeasurementSourceListener {

    /**
     * Initializes a new instance of the {@link PowerConsumptionPrmRecorder} class with the given arguments.
     * @param prmAccess The {@link PRMModel} model instance the power consumption measurements shall be forwarded to.
     * @param measurementSpecification The {@link MeasurementSpecification} as defined in a {@link MonitorRepository}.  
     * @param monitoredElement The {@link PowerProvidingEntity} that is monitored by the given {@code measurementSpecification}.
     */
    public PowerConsumptionPrmRecorder(PRMModel prmAccess, MeasurementSpecification measurementSpecification,
            PowerProvidingEntity monitoredElement) {
        super(prmAccess, measurementSpecification, monitoredElement);
        if (prmAccess == null || measurementSpecification == null || monitoredElement == null) {
            throw new IllegalArgumentException("At least one argument is null.");
        }
        if (!measurementSpecification.getMetricDescription().equals(MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE)) {
            throw new IllegalArgumentException("Metric of given MeasurementSpecification instance must be "
                    + MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE.getName() + "!");
        }
        addToPRM(getPCMModelElementMeasurement());
    }

    /**{@inheritDoc}<br>
     * When this method is called by the observed subject, the given {@link MeasuringValue} is propagated 
     * to the associated {@link PRMModel} instance.
     * @throws IllegalArgumentException In case the passed {@link MeasuringValue} is not compatible with 
     * the {@link MetricDescriptionConstants#POWER_CONSUMPTION_TUPLE} metric.
     */
    @Override
    public void newMeasurementAvailable(MeasuringValue newMeasurement) {
        if (newMeasurement == null || !newMeasurement.isCompatibleWith(MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE)) {
            throw new IllegalArgumentException("New available measurement is not a power consumption tuple!");
        }
        Measure<Double, Power> powerMeasure = newMeasurement.getMeasureForMetric(MetricDescriptionConstants.POWER_CONSUMPTION);
        //forward power value (expressed as double in receiving unit!) to PRM
        setMeasurementValue(powerMeasure.doubleValue(powerMeasure.getUnit()));
    }

    @Override
    public void preUnregister() {
        removeFromPRM(getPCMModelElementMeasurement());
    }

    @Override
    protected final void addToPRM(double newValue) {
        //argument value is ignored
        addToPRM(getPCMModelElementMeasurement());
    }
    
    private void addToPRM(PCMModelElementMeasurement pcmModelElementMeasurement) {
        assert pcmModelElementMeasurement != null;
        if (!getPrmModel().getPcmModelElementMeasurements().contains(pcmModelElementMeasurement)) {
            getPrmModel().getPcmModelElementMeasurements().add(pcmModelElementMeasurement);
        }
    }
    
    private void removeFromPRM(PCMModelElementMeasurement pcmModelElementMeasurement) {
        assert pcmModelElementMeasurement != null;
        getPrmModel().getPcmModelElementMeasurements().remove(pcmModelElementMeasurement);
    }
    
    private void setMeasurementValue(double newValue) {
        assert !Double.isInfinite(newValue) && !Double.isNaN(newValue);
                
        super.getPCMModelElementMeasurement().setMeasurementValue(newValue);
    }
}

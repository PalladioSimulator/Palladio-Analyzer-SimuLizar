package org.palladiosimulator.simulizar.metrics.powerconsumption;

import javax.measure.Measure;
import javax.measure.quantity.Power;

import org.palladiosimulator.measurementframework.Measurement;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PerformanceMetricEnum;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;

import de.fzi.power.infrastructure.PowerProvidingEntity;

public class PowerConsumptionPrmRecorder extends PRMRecorder implements IMeasurementSourceListener {

    public PowerConsumptionPrmRecorder(PRMModel prmAccess, MeasurementSpecification measurementSpecification,
            PowerProvidingEntity monitoredElement) {
        super(prmAccess, measurementSpecification, monitoredElement);
        if (prmAccess == null || measurementSpecification == null || monitoredElement == null) {
            throw new IllegalArgumentException("At least one argument is null.");
        }
        if (measurementSpecification.getPerformanceMetric() != PerformanceMetricEnum.POWER_CONSUMPTION) {
            throw new IllegalArgumentException("Metric of given MeasurementSpecification instance must be power consumption!");
        }
        addToPRM(getPCMModelElementMeasurement());
    }

    @Override
    public void newMeasurementAvailable(Measurement newMeasurement) {
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

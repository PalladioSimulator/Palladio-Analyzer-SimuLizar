package org.palladiosimulator.simulizar.monitorrepository.map.runtimemeasurement.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

import org.junit.Before;
import org.junit.Test;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointFactory;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.Monitor;
import org.palladiosimulator.monitorrepository.MonitorRepositoryFactory;
import org.palladiosimulator.monitorrepository.map.ExponentialSmoothing;
import org.palladiosimulator.monitorrepository.map.Map;
import org.palladiosimulator.monitorrepository.map.MapFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.monitorrepository.map.runtimemeasurement.MonitorRepositoryMapRuntimeMeasurementsRecorder;

public class MonitorRepositoryMapRuntimeMeasurementsRecorderTest {

    private static final double DELTA = Math.pow(10, -8);

    private Map mapProcessingType;
    private ExponentialSmoothing exponentialSmoothing;
    private RuntimeMeasurementModel rmModel;
    private MeasuringPoint measuringPoint;
    private Monitor monitor;
    private MeasurementSpecification spec;
    private Measure<Double, Duration> pointInTime;
    private MeasuringValue wrongMetricMeasurement;
    private MeasuringValue correctMeasurement;

    private InternalRuntimeMeasurementsRecorder rtMeasurementsRecorderUnderTest;

    @Before
    public void setUp() throws Exception {
        this.measuringPoint = MeasuringpointFactory.eINSTANCE.createResourceURIMeasuringPoint();
        this.measuringPoint.setStringRepresentation("MeasuringPoint");

        this.monitor = MonitorRepositoryFactory.eINSTANCE.createMonitor();
        this.monitor.setMeasuringPoint(this.measuringPoint);

        this.spec = MonitorRepositoryFactory.eINSTANCE.createMeasurementSpecification();
        this.spec.setMetricDescription(MetricDescriptionConstants.RESPONSE_TIME_METRIC);
        this.spec.setMonitor(this.monitor);

        this.exponentialSmoothing = MapFactory.eINSTANCE.createExponentialSmoothing();
        this.exponentialSmoothing.setSmoothingFactor(0.5); // computes the arithmetic mean then

        this.mapProcessingType = MapFactory.eINSTANCE.createMap();
        this.mapProcessingType.setEntityName("Map Processing Type Response Time");
        this.mapProcessingType.setMapper(this.exponentialSmoothing);
        this.mapProcessingType.setOutputMetricDescription(MetricDescriptionConstants.RESPONSE_TIME_METRIC);
        this.mapProcessingType.setMeasurementSpecification(this.spec);

        this.rmModel = RuntimeMeasurementFactory.eINSTANCE.createRuntimeMeasurementModel();

        this.rtMeasurementsRecorderUnderTest = new InternalRuntimeMeasurementsRecorder(this.rmModel,
                this.mapProcessingType);

        this.pointInTime = Measure.valueOf(10d, SI.SECOND);
        this.correctMeasurement = new TupleMeasurement(MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE,
                this.pointInTime, Measure.valueOf(1d / 60, NonSI.MINUTE)); // 1 second = 1/60 min
        this.wrongMetricMeasurement = new TupleMeasurement(MetricDescriptionConstants.POWER_CONSUMPTION_TUPLE,
                this.pointInTime, Measure.valueOf(42d, SI.WATT));
    }

    @Test(expected = NullPointerException.class)
    public void testCtorNullArgument() {
        new MonitorRepositoryMapRuntimeMeasurementsRecorder(null, this.mapProcessingType);
    }

    @Test(expected = NullPointerException.class)
    public void testCtorNullArgument2() {
        new MonitorRepositoryMapRuntimeMeasurementsRecorder(this.rmModel, null);
    }

    @Test(expected = IllegalStateException.class)
    public void testCtorIllegalStateArgument() {
        // use the response time metric set description to trigger the exception
        this.mapProcessingType.setOutputMetricDescription(MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE);
        new MonitorRepositoryMapRuntimeMeasurementsRecorder(this.rmModel, this.mapProcessingType);
    }

    @Test(expected = NullPointerException.class)
    public void testNewMeasurementAvailableNullArgument() {
        this.rtMeasurementsRecorderUnderTest.newMeasurementAvailable(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testNewMeasurementAvailableWrongMetric() {
        this.rtMeasurementsRecorderUnderTest.newMeasurementAvailable(this.wrongMetricMeasurement);
    }

    @Test
    public void testNewMeasurementAvailableCompatibleMetric() {
        this.rtMeasurementsRecorderUnderTest.newMeasurementAvailable(this.correctMeasurement);
        RuntimeMeasurement measurement = this.rtMeasurementsRecorderUnderTest.getMeasurement();

        assertEquals(this.spec.getId(), measurement.getMeasurementSpecification().getId());
        assertEquals(this.measuringPoint.getStringRepresentation(),
                measurement.getMeasuringPoint().getStringRepresentation());
        assertEquals(0.5, measurement.getMeasuringValue(), DELTA); // 0.5s

        this.rtMeasurementsRecorderUnderTest.newMeasurementAvailable(this.correctMeasurement);
        measurement = this.rtMeasurementsRecorderUnderTest.getMeasurement();
        assertEquals(0.75, measurement.getMeasuringValue(), DELTA);
    }

    @Test
    public void testPreUnregister() {
        List<RuntimeMeasurement> allMeasurements = this.rtMeasurementsRecorderUnderTest.getModel().getMeasurements();
        int size = allMeasurements.size();
        this.rtMeasurementsRecorderUnderTest.preUnregister();

        assertEquals(size - 1, this.rtMeasurementsRecorderUnderTest.getModel().getMeasurements().size());
        assertFalse(this.rmModel.getMeasurements().contains(this.rtMeasurementsRecorderUnderTest.getMeasurement()));
    }

    private class InternalRuntimeMeasurementsRecorder extends MonitorRepositoryMapRuntimeMeasurementsRecorder {

        public InternalRuntimeMeasurementsRecorder(final RuntimeMeasurementModel rmModel, final Map mapProcessingType) {
            super(rmModel, mapProcessingType);
        }

        public RuntimeMeasurement getMeasurement() {
            return super.getPRMMeasurement();
        }

        public RuntimeMeasurementModel getModel() {
            return super.getPrmModel();
        }

    }
}

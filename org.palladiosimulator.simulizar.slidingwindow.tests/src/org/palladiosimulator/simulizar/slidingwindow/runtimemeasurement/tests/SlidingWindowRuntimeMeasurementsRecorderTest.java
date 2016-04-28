package org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement.tests;

import static org.junit.Assert.assertEquals;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.SI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointFactory;
import org.palladiosimulator.measurementframework.BasicMeasurement;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepositoryFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement.SlidingWindowRuntimeMeasurementsRecorder;

public class SlidingWindowRuntimeMeasurementsRecorderTest {

    private RuntimeMeasurementModel rmModel;
    private MeasuringPoint measuringPoint;
    private MeasurementSpecification spec;
    private MeasuringValue wrongMetricMeasurement;
    private MeasuringValue correctMeasurement;

    private InternalSlidingWindowRuntimeMeasurementsRecorder recorderUnderTest;

    @Before
    public void setUp() throws Exception {
        this.rmModel = RuntimeMeasurementFactory.eINSTANCE.createRuntimeMeasurementModel();
        this.spec = MonitorRepositoryFactory.eINSTANCE.createMeasurementSpecification();
        this.measuringPoint = MeasuringpointFactory.eINSTANCE.createResourceURIMeasuringPoint();
        this.spec.setMetricDescription(MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE);

        Measure<Double, javax.measure.quantity.Duration> timeMeasure = Measure.valueOf(42.0, SI.SECOND);
        Measure<Double, Dimensionless> utilizationMeasure = Measure.valueOf(0.42, Dimensionless.UNIT);
        this.wrongMetricMeasurement = new BasicMeasurement<>(utilizationMeasure,
                MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE);
        this.correctMeasurement = new TupleMeasurement(MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE,
                timeMeasure, utilizationMeasure);

        this.recorderUnderTest = new InternalSlidingWindowRuntimeMeasurementsRecorder(this.rmModel, this.spec,
                this.measuringPoint);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test(expected = NullPointerException.class)
    public void testCtorNullArgument() {
        RuntimeMeasurementModel m = null;
        new SlidingWindowRuntimeMeasurementsRecorder(m, this.spec, this.measuringPoint);
    }

    @Test(expected = NullPointerException.class)
    public void testCtorNullArgument2() {
        new SlidingWindowRuntimeMeasurementsRecorder(this.rmModel, null, this.measuringPoint);
    }

    @Test(expected = NullPointerException.class)
    public void testCtorNullArgument3() {
        new SlidingWindowRuntimeMeasurementsRecorder(this.rmModel, this.spec, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorIllegalDataMetric() {
        this.spec.setMetricDescription(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        this.recorderUnderTest = new InternalSlidingWindowRuntimeMeasurementsRecorder(this.rmModel, this.spec,
                this.measuringPoint);
    }

    @Test(expected = NullPointerException.class)
    public void testNewMeasurementAvailableNull() {
        this.recorderUnderTest.newMeasurementAvailable(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNewMeasurementAvailableWrongMetric() {
        this.recorderUnderTest.newMeasurementAvailable(this.wrongMetricMeasurement);
    }

    @Test
    public void testNewMeasurementAvailable() {
        this.recorderUnderTest.newMeasurementAvailable(this.correctMeasurement);
        RuntimeMeasurement rmMeasurement = this.recorderUnderTest.getLastMeasurement();

        assertEquals(0.42, rmMeasurement.getMeasuringValue(), Math.pow(10, -8));
        assertEquals(this.spec.getId(), rmMeasurement.getMeasurementSpecification().getId());
    }

    private class InternalSlidingWindowRuntimeMeasurementsRecorder extends SlidingWindowRuntimeMeasurementsRecorder {

        public InternalSlidingWindowRuntimeMeasurementsRecorder(RuntimeMeasurementModel rmModel,
                MeasurementSpecification measurementSpecification, MeasuringPoint measuringPoint) {
            super(rmModel, measurementSpecification, measuringPoint);
        }

        private RuntimeMeasurement getLastMeasurement() {
            return this.getPRMMeasurement();
        }
    }
}

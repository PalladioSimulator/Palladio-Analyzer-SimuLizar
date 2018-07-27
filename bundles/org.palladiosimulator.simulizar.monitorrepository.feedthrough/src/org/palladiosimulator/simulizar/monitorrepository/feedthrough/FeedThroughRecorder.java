package org.palladiosimulator.simulizar.monitorrepository.feedthrough;

import java.util.Objects;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;

/**
 * A recorder that directly writes through the measurements from the observed calculator, or
 * other measurement source.
 * @author stier
 *
 */
public class FeedThroughRecorder extends PRMRecorder implements IMeasurementSourceListener {
    private NumericalBaseMetricDescription expectedMetric;

	public FeedThroughRecorder(final NumericalBaseMetricDescription expectedMetric,
    		final RuntimeMeasurementModel rmModel, final MeasurementSpecification measurementSpecification, 
    		final MeasuringPoint measuringPoint) {
        super(Objects.requireNonNull(rmModel), Objects.requireNonNull(measurementSpecification),
                Objects.requireNonNull(measuringPoint));
        this.expectedMetric = expectedMetric;
    }

	@Override
	public void newMeasurementAvailable(MeasuringValue newMeasurement) {
        super.updateMeasurementValue(newMeasurement.getMeasureForMetric(this.expectedMetric)
                .doubleValue(this.expectedMetric.getDefaultUnit()));
	}

	@Override
	public void preUnregister() {
		// TODO Auto-generated method stub
		
	}
}

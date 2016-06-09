package org.palladiosimulator.simulizar.metrics.aggregators;

import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

/**
 * Interval-based aggregator for any continuous double (or integer) values. Concrete metric, e.g.,
 * the number of jobs at an active resource, is determined by a given MeasurementSpecification.
 * 
 * @author Sebastian Lehrig, Marcus Hilbrich
 */
class ContinuousDoubleIntervalAggregator extends AbstractDoubleIntervalAggregator<ContinuousIntervalMeasurements> {

    public ContinuousDoubleIntervalAggregator(final SimuComModel model, final RuntimeMeasurementModel prmAccess,
            final MeasurementSpecification measurementSpecification) {
        super(model, prmAccess, measurementSpecification,
                new ContinuousIntervalMeasurements(0.0, SIMULATION_START_TIME));
    }

    @Override
    protected void finalizeCurrentIntervall(final double time) {
        super.finalizeCurrentIntervall(time);

        this.measurements.closeInterval(time);
        this.measurements = new ContinuousIntervalMeasurements(this.measurements, time);
    }

    @Override
    protected IStatisticalCharacterization<ContinuousIntervalMeasurements> getMedianCharacterization() {
        return new ContinuousMedian();
    }

    @Override
    protected IStatisticalCharacterization<ContinuousIntervalMeasurements> getArithmeticMeanCharacterization() {
        return new ContinuousArithmeticMean();
    }

    @Override
    protected IStatisticalCharacterization<ContinuousIntervalMeasurements> getGeometricMeanCharacterization() {
        return new ContinuousGeometricMean();
    }

    @Override
    protected IStatisticalCharacterization<ContinuousIntervalMeasurements> getHarmonicMeanCharacterization() {
        return new ContinuousHarmonicMean();
    }

    @Override
    protected void addMeasurement(final double measurement, final double time) {
        this.measurements.addMeasurement(new MeasureValueWithMeasuringTime(measurement, time));
    }
}

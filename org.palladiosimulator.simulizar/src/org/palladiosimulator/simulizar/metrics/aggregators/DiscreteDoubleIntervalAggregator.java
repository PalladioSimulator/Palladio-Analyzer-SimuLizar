package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.Collection;
import java.util.LinkedList;

import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

/**
 * Interval-based aggregator for any discrete double (or integer) values. Concrete metric, e.g.,
 * response times, is determined by a given MeasurementSpecification.
 * 
 * @author Sebastian Lehrig, Marcus Hilbrich
 */
class DiscreteDoubleIntervalAggregator extends AbstractDoubleIntervalAggregator<Collection<Double>> {

    public DiscreteDoubleIntervalAggregator(final SimuComModel model, final RuntimeMeasurementModel prmAccess,
            final MeasurementSpecification measurementSpecification) {
        super(model, prmAccess, measurementSpecification, new LinkedList<Double>());
    }

    @Override
    protected void finalizeCurrentIntervall(final double time) {
        if (this.measurements.size() > 0) {
            super.finalizeCurrentIntervall(time);
            this.measurements.clear();
        }
    }

    @Override
    protected IStatisticalCharacterization<Collection<Double>> getMedianCharacterization() {
        return new DiscreteMedian();
    }

    @Override
    protected IStatisticalCharacterization<Collection<Double>> getArithmeticMeanCharacterization() {
        return new DiscreteArithmeticMean();
    }

    @Override
    protected IStatisticalCharacterization<Collection<Double>> getGeometricMeanCharacterization() {
        return new DiscreteGeometricMean();
    }

    @Override
    protected IStatisticalCharacterization<Collection<Double>> getHarmonicMeanCharacterization() {
        return new DiscreteHarmonicMean();
    }

    @Override
    protected void addMeasurement(final double measurement, final double time) {
        this.measurements.add(measurement);
    }

}

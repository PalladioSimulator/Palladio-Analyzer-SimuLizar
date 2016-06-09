package org.palladiosimulator.simulizar.metrics.aggregators;

/**
 * @see http://math.stackexchange.com/questions/542199/geometric-mean-of-an-interval
 * @author Sebastian Lehrig, Marcus Hilbrich
 */
public class ContinuousGeometricMean implements IStatisticalCharacterization<ContinuousIntervalMeasurements> {

    @Override
    public double calculateStatisticalCharaterization(final ContinuousIntervalMeasurements measurements) {
        final Areas areas = new Areas(measurements, Areas.LOGARITHMIC_VALUE_MULTIPLICATION);
        return Math.exp(areas.getTotalArea() / measurements.getIntervalTime());
    }

}

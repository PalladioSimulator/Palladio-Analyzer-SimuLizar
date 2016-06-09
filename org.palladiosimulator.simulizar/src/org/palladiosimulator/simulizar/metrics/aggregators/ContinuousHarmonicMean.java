package org.palladiosimulator.simulizar.metrics.aggregators;

/**
 * @see http://math.stackexchange.com/questions/9007/harmonic-mean-and-logarithmic-mean
 * @author Sebastian Lehrig, Marcus Hilbrich
 */
public class ContinuousHarmonicMean implements IStatisticalCharacterization<ContinuousIntervalMeasurements> {

    @Override
    public double calculateStatisticalCharaterization(final ContinuousIntervalMeasurements measurements) {
        final Areas areas = new Areas(measurements, Areas.INVERSE_VALUE_MULTIPLICATION);
        return measurements.getIntervalTime() / areas.getTotalArea();
    }

}

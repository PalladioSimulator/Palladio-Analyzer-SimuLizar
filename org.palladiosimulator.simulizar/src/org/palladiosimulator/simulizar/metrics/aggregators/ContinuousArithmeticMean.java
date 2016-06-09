package org.palladiosimulator.simulizar.metrics.aggregators;

public class ContinuousArithmeticMean implements IStatisticalCharacterization<ContinuousIntervalMeasurements> {

    @Override
    public double calculateStatisticalCharaterization(final ContinuousIntervalMeasurements measurements) {
        final Areas areas = new Areas(measurements);
        return areas.getTotalArea() / measurements.getIntervalTime();
    }

}

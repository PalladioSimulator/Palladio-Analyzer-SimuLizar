package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.Collection;

public class HarmonicMean implements IStatisticalCharacterization {

    @Override
    public double calculateStatisticalCharaterization(final Collection<Double> measurements) {
        Double sumOfReciprocals = 0.0;
        for (final Double m : measurements) {
            sumOfReciprocals += (1.0 / m);
        }
        final double size = Double.valueOf(measurements.size());

        final double d = size / sumOfReciprocals;
        return d;
    }

}

package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.Collection;

public class HarmonicMean implements IStatisticalCharacterization {

    @Override
    public double calculateStatisticalCharaterization(Collection<Double> measurements) {
        Double sumOfReciprocals = 0.0;
        for (Double m : measurements) {
            sumOfReciprocals += (1.0 / m);
        }
        double size = Double.valueOf(measurements.size());

        double d = size / sumOfReciprocals;
        return d;
    }

}

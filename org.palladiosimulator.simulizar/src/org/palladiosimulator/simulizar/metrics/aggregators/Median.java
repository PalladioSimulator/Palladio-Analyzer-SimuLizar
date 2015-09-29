package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * The statistical characterization "Median"
 *
 * @author Igor Rogic
 *
 */
public class Median implements IStatisticalCharacterization {

    @Override
    public double calculateStatisticalCharaterization(final Collection<Double> measurements) {
        // transforming the collection to list in order to be able to sort it
        final List<Double> measurementsList = new ArrayList<Double>(measurements);
        Collections.sort(measurementsList);
        final int size = measurementsList.size();

        // dealing with two cases, a) odd number of measurements, and b) even number of measurements
        if (size % 2 == 1) {
            return measurementsList.get(size / 2);
        } else {
            final Double firstHalfEnd = measurementsList.get((size / 2) - 1);
            final Double secondHalfStart = measurementsList.get(size / 2);
            return (firstHalfEnd + secondHalfStart) / 2;
        }
    }

}

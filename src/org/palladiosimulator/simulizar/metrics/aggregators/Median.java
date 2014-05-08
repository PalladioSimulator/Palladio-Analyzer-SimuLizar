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
    public double calculateStatisticalCharaterization(Collection<Double> measurements) {
        // transforming the collection to list in order to be able to sort it
        List<Double> measurementsList = new ArrayList<Double>(measurements);
        Collections.sort(measurementsList);
        int size = measurementsList.size();

        // dealing with two cases, a) odd number of measurements, and b) even number of measurements
        if (size % 2 == 1) {
            return measurementsList.get(size / 2);
        } else {
            Double firstHalfEnd = measurementsList.get((size / 2) - 1);
            Double secondHalfStart = measurementsList.get(size / 2);
            return (firstHalfEnd + secondHalfStart) / 2;
        }
    }

}

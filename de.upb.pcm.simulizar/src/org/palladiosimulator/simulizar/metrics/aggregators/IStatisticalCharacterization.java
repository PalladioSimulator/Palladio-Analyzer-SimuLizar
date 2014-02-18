package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.Collection;

/**
 * Interface for a statistical characterization
 * 
 * @author Joachim Meyer
 * 
 */
public interface IStatisticalCharacterization {

    /**
     * Calculates the statistical characterization of the given measurements.
     * 
     * @param measurements
     *            a vector of double measurements
     * @return the statistical characterization.
     */
    double calculateStatisticalCharaterization(Collection<Double> measurements);

}

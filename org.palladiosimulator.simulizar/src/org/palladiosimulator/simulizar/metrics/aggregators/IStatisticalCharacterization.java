package org.palladiosimulator.simulizar.metrics.aggregators;

/**
 * Interface for a statistical characterization
 *
 * @author Joachim Meyer
 *
 */
public interface IStatisticalCharacterization<DATA_TYPE> {

    /**
     * Calculates the statistical characterization of the given measurements.
     *
     * @param measurements
     *            measurements
     * @return the statistical characterization.
     */
    double calculateStatisticalCharaterization(DATA_TYPE measurements);

}

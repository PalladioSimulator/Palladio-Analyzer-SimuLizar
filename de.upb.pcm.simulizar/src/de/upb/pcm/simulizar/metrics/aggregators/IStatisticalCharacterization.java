package de.upb.pcm.simulizar.metrics.aggregators;


import java.util.Vector;


/**
 * Interface for a statistical characterization
 * 
 * @author Joachim Meyer
 * 
 */
public interface IStatisticalCharacterization
{

   /**
    * Calculates the statistical characterization of the given measurements.
    * 
    * @param measurements a vector of double measurements
    * @return the statistical characterization.
    */
   Double calculateStatisticalCharaterization(Vector<Double> measurements);

}

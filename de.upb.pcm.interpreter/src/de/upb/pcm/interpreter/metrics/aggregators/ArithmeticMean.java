package de.upb.pcm.interpreter.metrics.aggregators;


import java.util.Vector;


/**
 * The statistical characterization "Arithmetic Mean"
 * 
 * @author Joachim Meyer
 * 
 */
public class ArithmeticMean implements IStatisticalCharacterization
{

   /**
    * Calculates the arithmetic mean of the given measurements.
    * 
    * @param measurements a vector of double measurements
    * @return the statistical characterization.
    */
   @Override
   public Double calculateStatisticalCharaterization(final Vector<Double> measurements)
   {
      double sum = 0.0;
      for (final Double measurement : measurements)
      {
         sum += measurement;
      }
      return sum / measurements.size();
   }

}

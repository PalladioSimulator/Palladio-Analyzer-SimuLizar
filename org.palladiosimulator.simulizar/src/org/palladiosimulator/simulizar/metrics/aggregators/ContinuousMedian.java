package org.palladiosimulator.simulizar.metrics.aggregators;

public class ContinuousMedian implements IStatisticalCharacterization<ContinuousIntervalMeasurements> {

    @Override
    public double calculateStatisticalCharaterization(final ContinuousIntervalMeasurements measurements) {
        final Areas areas = new Areas(measurements);
        final Double halfTotalArea = areas.getTotalArea() / 2;
        areas.sort();

        Double aggregatedArea = 0.0;
        for (final Area area : areas.getAreas()) {
            aggregatedArea += area.getArea();

            if (aggregatedArea >= halfTotalArea) {
                return area.getMeasureValue();
            }
        }

        throw new RuntimeException(
                "There must be a point where the aggregated area is larger than half of the total area!");
    }

}

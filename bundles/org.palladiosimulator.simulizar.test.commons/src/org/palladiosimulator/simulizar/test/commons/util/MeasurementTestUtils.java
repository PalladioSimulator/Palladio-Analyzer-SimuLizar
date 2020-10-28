package org.palladiosimulator.simulizar.test.commons.util;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.measure.quantity.Quantity;
import javax.measure.unit.Unit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.hamcrest.Matcher;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.models.ExperimentData.MeasurementRange;
import org.palladiosimulator.edp2.util.MeasurementsUtility;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.util.MetricSpecSwitch;

public final class MeasurementTestUtils {
    
    public static <Q extends Quantity> void allDoubleMeasurementValuesMatch(Measurement measurement,
            BaseMetricDescription valueMetric, Unit<Q> unit, Matcher<Double> matcher) {

        var dataSeriesIdx = (new MetricSpecSwitch<Integer>() {
            int index = 0;

            @Override
            public Integer caseBaseMetricDescription(BaseMetricDescription object) {
                if (object.getId()
                    .equals(valueMetric.getId())) {
                    return index;
                }
                return -1;
            }

            @Override
            public Integer caseMetricSetDescription(MetricSetDescription object) {
                var result = -1;
                var subsumedIterator = object.getSubsumedMetrics()
                    .iterator();
                while (subsumedIterator.hasNext() && result == -1) {
                    result = this.doSwitch(subsumedIterator.next());
                    index++;
                }

                return result;
            }

        }).doSwitch(measurement.getMeasuringType()
            .getMetric());

        measurement.getMeasurementRanges()
            .stream()
            .map(MeasurementRange::getRawMeasurements)
            .map(rm -> rm.getDataSeries()
                .get(dataSeriesIdx))
            .map(ds -> MeasurementsUtility.<Q> getMeasurementsDao(ds))
            .flatMap(dao -> dao.getMeasurements()
                .stream())
            .forEach(m -> assertThat(m.doubleValue(unit), matcher));
    }

    public static Optional<Measurement> getMeasurementOfAt(Collection<Measurement> measurements,
            MetricDescription metric, EObject location) {
        return measurements.stream()
            .filter(m -> m.getMeasuringType()
                .getMetric()
                .getId()
                .equals(metric.getId())
                    && m.getMeasuringType()
                        .getMeasuringPoint()
                        .getResourceURIRepresentation()
                        .equals(EcoreUtil.getURI(location)
                            .toString()))
            .findAny();
    }
}

package org.palladiosimulator.simulizar.test.commons.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.palladiosimulator.simulizar.test.commons.hamcrest.Matchers.asDoubleIn;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import javax.measure.unit.Unit;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.hamcrest.Matcher;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.models.ExperimentData.MeasurementRange;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MeasurementsUtility;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.util.MetricSpecSwitch;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;

public final class MeasurementTestUtils {

    public static <Q extends Quantity> void allDoubleMeasurementValuesMatch(Measurement measurement,
            BaseMetricDescription valueMetric, Unit<Q> unit, Matcher<Double> matcher) {
        allMeasurementValuesMatch(measurement, valueMetric, asDoubleIn(unit, matcher));
    }

    public static <Q extends Quantity> void allMeasurementValuesMatch(Measurement measurement,
            BaseMetricDescription valueMetric, Matcher<Measure<?, Q>> matcher) {
        assertThat(allMeasurementsOfMetric(measurement, valueMetric), everyItem(matcher));
    }

    public static <Q extends Quantity> List<Measure<?, Q>> allMeasurementsOfMetric(Measurement measurement,
            BaseMetricDescription valueMetric) {
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

        return measurement.getMeasurementRanges()
            .stream()
            .map(MeasurementRange::getRawMeasurements)
            .map(rm -> rm.getDataSeries()
                .get(dataSeriesIdx))
            .map(ds -> MeasurementsUtility.<Q> getMeasurementsDao(ds))
            .flatMap(dao -> dao.getMeasurements()
                .stream())
            .collect(Collectors.toList());
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
    
    public static Optional<Measurement> getMeasurementOfAt(Collection<Measurement> measurements,
            MetricDescription metric, MeasuringPoint location) {
        return measurements.stream()
            .filter(m -> m.getMeasuringType()
                .getMetric()
                .getId()
                .equals(metric.getId())
                    && sameMeasuringPoint(m.getMeasuringType().getMeasuringPoint(), location))
            .findAny();
    }
    
    public static boolean sameMeasuringPoint(MeasuringPoint point1, MeasuringPoint point2) {
        var id1 = MonitorRepositoryUtil.getMeasurementIdentifier(point1);
        var id2 = MonitorRepositoryUtil.getMeasurementIdentifier(point2);
        return id1.equals(id2);
    }
}

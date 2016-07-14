package org.palladiosimulizar.aggregation.aggregators;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.apache.commons.collections15.IteratorUtils;
import org.jscience.physics.amount.Amount;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.experimentanalysis.statisticalcharacterization.aggregators.StatisticalCharacterizationAggregator;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.measurementframework.listener.MeasurementSource;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.FixedSizeAggregation;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class FixedSizeMeasurementsAggregator extends MeasurementSource {

    private final MeasurementSpecification measurementSpecification;
    private final MetricDescription expectedMetric;
    private final FixedSizeAggregation fixedSizeAggregation;
    private final StatisticalCharacterizationAggregator aggregator;
    private final InternalBuffer buffer;

    private final SimuComModel simuComModel;

    private int measurementsUntilNextAggregation = 0;

    public FixedSizeMeasurementsAggregator(MeasurementSpecification measurementSpec,
            RegisterCalculatorFactoryDecorator calcFactory, SimuComModel simuComModel) {
        super(Objects.requireNonNull(measurementSpec).getMetricDescription());

        this.simuComModel = Objects.requireNonNull(simuComModel);
        this.measurementSpecification = measurementSpec;

        this.fixedSizeAggregation = (FixedSizeAggregation) this.measurementSpecification.getProcessingType();
        MeasuringPoint mp = measurementSpec.getMonitor().getMeasuringPoint();
        this.expectedMetric = measurementSpec.getMetricDescription();
        Optional<Calculator> calculator;
        BaseMetricDescription[] subsumedBaseMetrics = MetricDescriptionUtility.toBaseMetricDescriptions(expectedMetric);
        if (subsumedBaseMetrics.length == 1) {
            // check for the base metric manually
            calculator = calcFactory.getCalculatorsForMeasuringPoint(mp).stream()
                    .filter(calc -> MetricDescriptionUtility.isBaseMetricDescriptionSubsumedByMetricDescription(
                            subsumedBaseMetrics[0], calc.getMetricDesciption()))
                    .findAny();
        } else {
            // use convenience method that matches complete tuple metrics
            calculator = Optional.ofNullable(calcFactory.getCalculatorByMeasuringPointAndMetricDescription(mp,
                    measurementSpec.getMetricDescription()));
        }
        if (!calculator.isPresent()) {
            throw new IllegalStateException("Fixed size aggregation of measurements cannot be initialized.\n" + "No '"
                    + measurementSpec.getMetricDescription().getName() + "' calculator available for: "
                    + "MeasuringPoint '" + mp.getStringRepresentation() + "'.\n" + "Affected Monitor: '"
                    + measurementSpec.getMonitor().getEntityName() + "'\n"
                    + "Ensure that measurement calculator has been created and registered within the ProbeFrameworkListener class!");
        }
        this.aggregator = this.fixedSizeAggregation.getStatisticalCharacterization()
                .getAggregator((NumericalBaseMetricDescription) this.expectedMetric);

        this.buffer = new InternalBuffer(this.fixedSizeAggregation.getNumberOfMeasurements());

        resetCounter();
        calculator.get().addObserver(new IMeasurementSourceListener() {

            @Override
            public void preUnregister() {
                FixedSizeMeasurementsAggregator.this.buffer.clear();

            }

            @Override
            public void newMeasurementAvailable(MeasuringValue newMeasurement) {
                FixedSizeMeasurementsAggregator.this.collectMeasurement(newMeasurement);
            }
        });
    }

    private void collectMeasurement(MeasuringValue newMeasurement) {
        if (!Objects.requireNonNull(newMeasurement).isCompatibleWith(this.expectedMetric)
                && !MetricDescriptionUtility.isBaseMetricDescriptionSubsumedByMetricDescription(
                        (BaseMetricDescription) this.expectedMetric, newMeasurement.getMetricDesciption())) {
            throw new IllegalStateException("Somehow a wrong measurement kind was passed.");
        }
        this.buffer.add(newMeasurement);

        decrementCounter();
        if (aggregationRequired()) {
            aggregate();
            resetCounter();
        }
    }

    private void resetCounter() {
        this.measurementsUntilNextAggregation = this.fixedSizeAggregation.getFrequency();
    }

    private void decrementCounter() {
        --this.measurementsUntilNextAggregation;
    }

    private boolean aggregationRequired() {
        return this.measurementsUntilNextAggregation == 0;
    }

    private void aggregate() {
        Measure<Double, Duration> start = this.buffer.getOldestElement()
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        Measure<Double, Duration> end = this.buffer.getNewestElement()
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);

        MeasuringValue aggregatedData = this.aggregator.aggregateData(this.buffer,
                Amount.valueOf(start.getValue(), start.getUnit()), Amount.valueOf(end.getValue(), end.getUnit()),
                Optional.empty());

        super.notifyMeasurementSourceListener(aggregatedData);
    }

    private static final class InternalBuffer implements Iterable<MeasuringValue> {

        // the data container will be used in a circular way
        private final MeasuringValue[] data;
        // pointer to the least recent element of the data
        // that is the element which was added least recently
        // this is as base "address" for all data accesses
        private int oldestElementPointer = 0;
        private int currentElementCount = 0;

        private InternalBuffer(int capacity) {
            assert capacity > 0;
            this.data = new MeasuringValue[capacity];
        }

        public int size() {
            return this.currentElementCount;
        }

        public int capacity() {
            return this.data.length;
        }

        public boolean isEmpty() {
            return this.currentElementCount == 0;
        }

        public boolean isFull() {
            return size() == capacity();
        }

        public boolean add(MeasuringValue measuringValue) {
            if (isFull()) {
                addFull(measuringValue);
            } else {
                addNotFull(measuringValue);
            }
            return true;
        }

        public MeasuringValue getOldestElement() {
            return this.data[oldestElementPointer];
        }

        private void addFull(MeasuringValue newElemement) {
            assert isFull() && newElemement != null;

            this.data[this.oldestElementPointer] = newElemement;
            adjustOldestElementPointer();
        }

        private void addNotFull(MeasuringValue elementToAdd) {
            assert !isFull() && elementToAdd != null;

            int index = (this.oldestElementPointer + this.currentElementCount) % capacity();
            this.data[index] = elementToAdd;
            this.currentElementCount++;
        }

        private void adjustOldestElementPointer() {
            this.oldestElementPointer++;
            this.oldestElementPointer %= capacity();
            assert this.oldestElementPointer >= 0 && this.oldestElementPointer < capacity();
        }

        public void clear() {
            Arrays.fill(data, null);
            this.oldestElementPointer = this.currentElementCount = 0;

        }

        public MeasuringValue getNewestElement() {
            return this.data[(this.oldestElementPointer + size() - 1) % capacity()];
        }

        @Override
        public Iterator<MeasuringValue> iterator() {
            return IteratorUtils.arrayIterator(this.data);
        }

    }
}

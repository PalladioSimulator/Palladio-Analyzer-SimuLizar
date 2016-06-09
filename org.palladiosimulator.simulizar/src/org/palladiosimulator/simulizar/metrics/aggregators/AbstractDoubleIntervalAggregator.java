package org.palladiosimulator.simulizar.metrics.aggregators;

import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.measurementframework.measureprovider.AbstractMeasureProvider;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.Intervall;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredSimulationEntity;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

abstract class AbstractDoubleIntervalAggregator<DATA_TYPE> extends PRMRecorder implements IMeasurementSourceListener {

    protected static final double SIMULATION_START_TIME = 0.0;

    /** Measurements taken in the interval currently investigated. */
    protected DATA_TYPE measurements;

    private final SimuComModel model;

    /** Metric description of the measurements to be aggregated. */
    private final NumericalBaseMetricDescription metricDescription;

    /** Aggregation strategy to be used, e.g., arithmetic mean. */
    private final IStatisticalCharacterization<DATA_TYPE> statisticalCharacterization;

    public AbstractDoubleIntervalAggregator(final SimuComModel model, final RuntimeMeasurementModel prmAccess,
            final MeasurementSpecification measurementSpecification, final DATA_TYPE measurements) {
        super(prmAccess, measurementSpecification);

        if (!(measurementSpecification.getMetricDescription() instanceof NumericalBaseMetricDescription)) {
            throw new RuntimeException(
                    "measurementSpecification must conform to a NumericalBaseMetricDescription for double aggregation!");
        }

        if (!(measurementSpecification.getTemporalRestriction() instanceof Intervall)) {
            throw new UnsupportedOperationException("Only Intervall is currently supported");
        }

        this.metricDescription = (NumericalBaseMetricDescription) measurementSpecification.getMetricDescription();
        this.model = model;
        this.measurements = measurements;

        switch (measurementSpecification.getStatisticalCharacterization()) {
        case MEDIAN:
            this.statisticalCharacterization = getMedianCharacterization();
            break;
        case ARITHMETIC_MEAN:
            this.statisticalCharacterization = getArithmeticMeanCharacterization();
            break;
        case GEOMETRIC_MEAN:
            this.statisticalCharacterization = getGeometricMeanCharacterization();
            break;
        case HARMONIC_MEAN:
            this.statisticalCharacterization = getHarmonicMeanCharacterization();
            break;
        default:
            throw new UnsupportedOperationException("This aggregator is currently not supported");
        }

        new PeriodicallyTriggeredSimulationEntity(model, SIMULATION_START_TIME,
                ((Intervall) measurementSpecification.getTemporalRestriction()).getIntervall()) {

            @Override
            protected void triggerInternal() {
                AbstractDoubleIntervalAggregator.this.finalizeCurrentIntervall(
                        AbstractDoubleIntervalAggregator.this.model.getSimulationControl().getCurrentSimulationTime());
            }

        };
    }

    protected abstract IStatisticalCharacterization<DATA_TYPE> getMedianCharacterization();

    protected abstract IStatisticalCharacterization<DATA_TYPE> getArithmeticMeanCharacterization();

    protected abstract IStatisticalCharacterization<DATA_TYPE> getGeometricMeanCharacterization();

    protected abstract IStatisticalCharacterization<DATA_TYPE> getHarmonicMeanCharacterization();

    protected void finalizeCurrentIntervall(final double time) {
        final double statisticalCharacterization = this.statisticalCharacterization
                .calculateStatisticalCharaterization(this.measurements);
        this.updateMeasurementValue(statisticalCharacterization);
    }

    /**
     * Nothing to do here.
     */
    @Override
    public final void preUnregister() {
    }

    /**
     * @see org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener#newMeasurementAvailable(AbstractMeasureProvider)
     */
    @Override
    public void newMeasurementAvailable(final MeasuringValue measurement) {
        addMeasurement(getDoubleMeasurement(measurement), this.model.getSimulationControl().getCurrentSimulationTime());
    }

    protected abstract void addMeasurement(final double measurement, final double time);

    protected double getDoubleMeasurement(final MeasuringValue measuringValue) {
        return measuringValue.getMeasureForMetric(this.metricDescription)
                .doubleValue(this.metricDescription.getDefaultUnit());
    }
}
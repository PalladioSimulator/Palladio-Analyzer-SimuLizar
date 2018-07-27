package org.palladiosimulator.simulizar.monitorrepository.map.probeframework;

import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSpecPackage;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.ProcessingType;
import org.palladiosimulator.monitorrepository.map.Map;
import org.palladiosimulator.monitorrepository.map.MapPackage;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractProbeFrameworkListener;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.monitorrepository.map.runtimemeasurement.MonitorRepositoryMapRuntimeMeasurementsRecorder;

/**
 * Implementation of the {@link AbstractRecordingProbeFrameworkListenerDecorator} class dedicated to
 * initialize transformations (indicated by the {@link ProcessingType} {@link Map} in the
 * corresponding {@link MeasurementSpecification}) of collected measurements.
 * 
 * @author Florian Rosenthal
 *
 */
public class MonitorRepositoryMapProbeFrameworkListenerDecorator
        extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final EClass MAP_PROCESSING_TYPE = MapPackage.Literals.MAP;

    private RuntimeMeasurementModel rmModel = null;
    private RegisterCalculatorFactoryDecorator calculatorFactory = null;

    /**
     * {@inheritDoc}
     * 
     * @throws NullPointerException
     *             In case {@code probeFrameworkListener == null}.
     */
    @Override
    public void setProbeFrameworkListener(final AbstractProbeFrameworkListener probeFrameworkListener) {
        super.setProbeFrameworkListener(Objects.requireNonNull(probeFrameworkListener));

        this.rmModel = getProbeFrameworkListener().getRuntimeMeasurementModel();
        this.calculatorFactory = RegisterCalculatorFactoryDecorator.class
                .cast(getProbeFrameworkListener().getCalculatorFactory());
    }

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();

        getProbeFrameworkListener().getMeasurementSpecificationsForProcessingType(MAP_PROCESSING_TYPE).stream()
                .filter(MeasurementSpecification::isTriggersSelfAdaptations).forEach(this::initMapping);
    }

    private void initMapping(final MeasurementSpecification measurementSpecification) {
        MetricDescription metric = measurementSpecification.getMetricDescription();
        MeasuringPoint measuringPoint = measurementSpecification.getMonitor().getMeasuringPoint();

        Calculator baseCalculator = getBaseCalculator(metric, measuringPoint).orElseThrow(
                () -> new IllegalStateException("Mapping/Transformation of measurements cannot be initialized.\n"
                        + "No '" + metric.getName() + "' calculator available for: " + "MeasuringPoint '"
                        + measuringPoint.getStringRepresentation() + "'.\n" + "Affected Monitor: '"
                        + measurementSpecification.getMonitor().getEntityName() + "'\n"
                        + "Ensure that measurement calculator has been created and registered within the ProbeFrameworkListener class!"));

        baseCalculator.addObserver(new MonitorRepositoryMapRuntimeMeasurementsRecorder(this.rmModel,
                (Map) measurementSpecification.getProcessingType()));
    }

    private Optional<Calculator> getBaseCalculator(final MetricDescription metric,
            final MeasuringPoint measuringPoint) {
        Calculator baseCalculator = this.calculatorFactory
                .getCalculatorByMeasuringPointAndMetricDescription(measuringPoint, metric);
        if (baseCalculator == null && MetricSpecPackage.Literals.BASE_METRIC_DESCRIPTION.isInstance(metric)) {
            return this.calculatorFactory.getCalculatorsForMeasuringPoint(measuringPoint).stream()
                    .filter(calc -> MetricDescriptionUtility.isBaseMetricDescriptionSubsumedByMetricDescription(
                            (BaseMetricDescription) metric, calc.getMetricDesciption()))
                    .findAny();

        }
        return Optional.ofNullable(baseCalculator);
    }
}

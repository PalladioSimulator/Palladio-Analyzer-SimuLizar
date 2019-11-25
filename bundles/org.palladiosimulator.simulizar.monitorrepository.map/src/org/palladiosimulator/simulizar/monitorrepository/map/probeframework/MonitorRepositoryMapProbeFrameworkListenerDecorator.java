package org.palladiosimulator.simulizar.monitorrepository.map.probeframework;

import java.util.Objects;

import org.eclipse.emf.ecore.EClass;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.ProcessingType;
import org.palladiosimulator.monitorrepository.map.Map;
import org.palladiosimulator.monitorrepository.map.MapPackage;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractProbeFrameworkListener;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.interpreter.listener.DeferredMeasurementInitialization;
import org.palladiosimulator.simulizar.monitorrepository.map.runtimemeasurement.MonitorRepositoryMapRuntimeMeasurementsRecorder;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

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

        PCMPartitionManager manager = getProbeFrameworkListener().getPCMPartitionManager();
        this.rmModel = manager.findModel(RuntimeMeasurementPackage.eINSTANCE.getRuntimeMeasurementModel());
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

        DeferredMeasurementInitialization.forCalculatorFactoryDecorator(calculatorFactory)
                .onMetricDescriptionAndMeasuringPoint(metric, measuringPoint,
                        () -> new MonitorRepositoryMapRuntimeMeasurementsRecorder(this.rmModel,
                                (Map) measurementSpecification.getProcessingType()));
	}
}

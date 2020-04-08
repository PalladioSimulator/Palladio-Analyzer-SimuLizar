package org.palladiosimulator.simulizar.monitorrepository.feedthrough;

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricSpecPackage;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.util.MetricSpecSwitch;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.probeframework.calculator.IObservableCalculatorRegistry;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractProbeFrameworkListener;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.interpreter.listener.DeferredMeasurementInitialization;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

/**
 * Registers PRM recorders that directly update measurements in the RuntimeMeasurement model with
 * the measurements from a fitting calculator.
 * @author stier
 *
 */
public class FeedThroughDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

	private static final EClass FEED_THROUGH_ECLASS = MonitorRepositoryPackage.Literals.FEED_THROUGH;
	
    private IObservableCalculatorRegistry calculatorRegistry;
    private RuntimeMeasurementModel runtimeMeasurementModel;
	 
	@Override
	public void registerMeasurements() {
		super.registerMeasurements();
		
        this.getProbeFrameworkListener().getMeasurementSpecificationsForProcessingType(FEED_THROUGH_ECLASS).stream()
		.filter(MeasurementSpecification::isTriggersSelfAdaptations)
        .forEach(this::initFeedThroughMeasurements);
	}
	
    @Override
    public void setProbeFrameworkListener(final AbstractProbeFrameworkListener listener) {
        super.setProbeFrameworkListener(listener);
        this.calculatorRegistry = getProbeFrameworkContext().getCalculatorRegistry();
        PCMPartitionManager manager = getProbeFrameworkListener().getPCMPartitionManager();
        this.runtimeMeasurementModel = manager.findModel(RuntimeMeasurementPackage.eINSTANCE.getRuntimeMeasurementModel());
    }
	
	private void initFeedThroughMeasurements(final MeasurementSpecification measurementSpecification) {
        MeasuringPoint measuringPoint = measurementSpecification.getMonitor().getMeasuringPoint();
        Optional<NumericalBaseMetricDescription> expectedMetric = GET_NUMERICAL_BASE_METRIC_SWITCH
                .doSwitch(measurementSpecification.getMetricDescription());	
        
        checkValidity(expectedMetric, measurementSpecification);
        
        DeferredMeasurementInitialization.forCalculatorFactoryDecorator(calculatorRegistry)
                .onMetricDescriptionAndMeasuringPoint(expectedMetric.get(), measuringPoint,
                        () -> new FeedThroughRecorder(expectedMetric.get(), runtimeMeasurementModel,
                                measurementSpecification, measuringPoint));
	}
	
    private static final MetricSpecSwitch<Optional<NumericalBaseMetricDescription>> GET_NUMERICAL_BASE_METRIC_SWITCH = new MetricSpecSwitch<Optional<NumericalBaseMetricDescription>>() {

        @Override
        public Optional<NumericalBaseMetricDescription> caseNumericalBaseMetricDescription(
                final NumericalBaseMetricDescription numericalBaseMetricDescription) {
            return Optional.of(numericalBaseMetricDescription);
        }

        @Override
        public Optional<NumericalBaseMetricDescription> defaultCase(final EObject eObject) {
            return Optional.empty();
        }
    };
    
    private static void checkValidity(final Optional<NumericalBaseMetricDescription> expectedMetric,
            final MeasurementSpecification spec) {
        if (!expectedMetric.isPresent()) {
            throw new IllegalStateException("Cannot initialize measurements aggregation defined by "
                    + spec.eClass().getName() + " with id '" + spec.getId() + "':\nSo far, only "
                    + MetricSpecPackage.Literals.NUMERICAL_BASE_METRIC_DESCRIPTION.getName()
                    + "s are supported for fixed and variable size aggregation!");
        }
    }
}

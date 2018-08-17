package org.palladiosimulizar.aggregation.probeframework;

import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.metricspec.MetricSpecPackage;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.util.MetricSpecSwitch;
import org.palladiosimulator.monitorrepository.FixedSizeAggregation;
import org.palladiosimulator.monitorrepository.MeasurementDrivenAggregation;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.monitorrepository.VariableSizeAggregation;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractProbeFrameworkListener;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.palladiosimulizar.aggregation.aggregators.AbstractMeasurementAggregator;
import org.palladiosimulizar.aggregation.aggregators.FixedSizeMeasurementsAggregator;
import org.palladiosimulizar.aggregation.aggregators.VariableSizeMeasurementAggregator;

/**
 * Implementation of the {@link AbstractRecordingProbeFrameworkListenerDecorator} class dedicated to
 * initialize {@link MeasurementDrivenAggregation}s.
 * 
 * @see AbstractMeasurementAggregator
 * 
 * @author Florian Rosenthal
 *
 */
public class AggregatorsProbeFrameworkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final String FIXED_EXCEPTION_MESSAGE = "Fixed size aggregation of measurements cannot be initialized.\n";
	private static final String VARIABLE_EXCEPTION_MESSAGE = "Variable size aggregation of measurements cannot be initialized.\n";
	private static final EClass FIXED_SIZE_AGGREGATION_ECLASS = MonitorRepositoryPackage.Literals.FIXED_SIZE_AGGREGATION;
    private static final EClass VARIABLE_SIZE_AGGREGATION_ECLASS = MonitorRepositoryPackage.Literals.VARIABLE_SIZE_AGGREGATION;
   
    private RegisterCalculatorFactoryDecorator calculatorFactory;
    private RuntimeMeasurementModel runtimeMeasurementModel;

    @Override
    public void setProbeFrameworkListener(final AbstractProbeFrameworkListener listener) {
        super.setProbeFrameworkListener(listener);
        this.calculatorFactory = RegisterCalculatorFactoryDecorator.class
                .cast(getProbeFrameworkListener().getCalculatorFactory());
        PCMPartitionManager manager = getProbeFrameworkListener().getPCMPartitionManager();
        this.runtimeMeasurementModel = manager.findModel(RuntimeMeasurementPackage.eINSTANCE.getRuntimeMeasurementModel());
    }

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();

        this.getProbeFrameworkListener().getMeasurementSpecificationsForProcessingType(FIXED_SIZE_AGGREGATION_ECLASS)
                .stream().filter(MeasurementSpecification::isTriggersSelfAdaptations)
                .forEach(this::initFixedSizeAggregation);
        this.getProbeFrameworkListener().getMeasurementSpecificationsForProcessingType(VARIABLE_SIZE_AGGREGATION_ECLASS)
                .stream().filter(MeasurementSpecification::isTriggersSelfAdaptations)
                .forEach(this::initVariableSizeAggregation);
    }

    private void initVariableSizeAggregation(final MeasurementSpecification measurementSpecification) {
        MeasuringPoint measuringPoint = measurementSpecification.getMonitor().getMeasuringPoint();
        Optional<NumericalBaseMetricDescription> expectedMetric = GET_NUMERICAL_BASE_METRIC_SWITCH
                .doSwitch(measurementSpecification.getMetricDescription());

        // fails in case optional is empty
        checkValidity(expectedMetric, measurementSpecification);

        Calculator correspondingBaseCalculator = getBaseCalculator(measurementSpecification, measuringPoint,
				expectedMetric, VARIABLE_EXCEPTION_MESSAGE);

        correspondingBaseCalculator.addObserver(new VariableSizeMeasurementAggregator(expectedMetric.get(),
                this.runtimeMeasurementModel, (VariableSizeAggregation) measurementSpecification.getProcessingType()));
    }

    private void initFixedSizeAggregation(final MeasurementSpecification measurementSpecification) {
        MeasuringPoint measuringPoint = measurementSpecification.getMonitor().getMeasuringPoint();
        Optional<NumericalBaseMetricDescription> expectedMetric = GET_NUMERICAL_BASE_METRIC_SWITCH
                .doSwitch(measurementSpecification.getMetricDescription());

        // fails in case optional is empty
        checkValidity(expectedMetric, measurementSpecification);

        Calculator correspondingBaseCalculator = getBaseCalculator(measurementSpecification, measuringPoint,
				expectedMetric, FIXED_EXCEPTION_MESSAGE);

        correspondingBaseCalculator.addObserver(new FixedSizeMeasurementsAggregator(expectedMetric.get(),
                this.runtimeMeasurementModel, (FixedSizeAggregation) measurementSpecification.getProcessingType()));
    }

	private Calculator getBaseCalculator(final MeasurementSpecification measurementSpecification,
			MeasuringPoint measuringPoint, Optional<NumericalBaseMetricDescription> expectedMetric,
			String message) {
		Calculator correspondingBaseCalculator = getBaseCalculator(expectedMetric.get(),
                measuringPoint).<IllegalStateException> orElseThrow(() -> new IllegalStateException(
                        message + "No '"
                                + expectedMetric.get().getName() + "' calculator available for: " + "MeasuringPoint '"
                                + measuringPoint.getStringRepresentation() + "'.\n" + "Affected Monitor: '"
                                + measurementSpecification.getMonitor().getEntityName() + "'\n"
                                + "Ensure that measurement calculator has been created and registered within the ProbeFrameworkListener class!"));
		return correspondingBaseCalculator;
	}

    private Optional<Calculator> getBaseCalculator(final NumericalBaseMetricDescription metric,
            final MeasuringPoint measuringPoint) {
        Calculator baseCalculator = this.calculatorFactory
                .getCalculatorByMeasuringPointAndMetricDescription(measuringPoint, metric);
        if (baseCalculator == null) {
            return this.calculatorFactory.getCalculatorsForMeasuringPoint(measuringPoint)
                    .stream().filter(calc -> MetricDescriptionUtility
                            .isBaseMetricDescriptionSubsumedByMetricDescription(metric, calc.getMetricDesciption()))
                    .findAny();

        }
        return Optional.of(baseCalculator);
    }

    private static void checkValidity(final Optional<NumericalBaseMetricDescription> expectedMetric,
            final MeasurementSpecification spec) {
        if (!expectedMetric.isPresent()) {
            throw new IllegalStateException("Cannot initialize measurements aggregation defined by "
                    + spec.eClass().getName() + " with id '" + spec.getId() + "':\nSo far, only "
                    + MetricSpecPackage.Literals.NUMERICAL_BASE_METRIC_DESCRIPTION.getName()
                    + "s are supported for fixed and variable size aggregation!");
        }
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
}

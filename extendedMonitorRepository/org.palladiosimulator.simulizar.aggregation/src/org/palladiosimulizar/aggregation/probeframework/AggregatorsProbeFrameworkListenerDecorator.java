package org.palladiosimulizar.aggregation.probeframework;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.util.MetricSpecSwitch;
import org.palladiosimulator.monitorrepository.FixedSizeAggregation;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.monitorrepository.VariableSizeAggregation;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractProbeFrameworkListener;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulizar.aggregation.aggregators.FixedSizeMeasurementsAggregator;
import org.palladiosimulizar.aggregation.aggregators.VariableSizeMeasurementAggregator;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class AggregatorsProbeFrameworkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final EClass FIXED_SIZE_AGGREGATION_ECLASS = MonitorRepositoryPackage.Literals.FIXED_SIZE_AGGREGATION;
    private static final EClass VARIABLE_SIZE_AGGREGATION_ECLASS = MonitorRepositoryPackage.Literals.VARIABLE_SIZE_AGGREGATION;

    private RegisterCalculatorFactoryDecorator calculatorFactory;
    private RuntimeMeasurementModel runtimeMeasurementModel;
    private SimuComModel model;

    @Override
    public void setProbeFrameworkListener(AbstractProbeFrameworkListener listener) {
        super.setProbeFrameworkListener(listener);
        this.calculatorFactory = RegisterCalculatorFactoryDecorator.class
                .cast(getProbeFrameworkListener().getCalculatorFactory());
        this.model = getProbeFrameworkListener().getSimuComModel();
        this.runtimeMeasurementModel = getProbeFrameworkListener().getRuntimeMeasurementModel();
    }

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();

        Collection<MeasurementSpecification> fixedSizeAggregationMeasurementSpecs = this.getProbeFrameworkListener()
                .getMeasurementSpecificationsForProcessingType(FIXED_SIZE_AGGREGATION_ECLASS);
        Collection<MeasurementSpecification> variableSizeAggregationMeasurementSpecs = this.getProbeFrameworkListener()
                .getMeasurementSpecificationsForProcessingType(VARIABLE_SIZE_AGGREGATION_ECLASS);

        fixedSizeAggregationMeasurementSpecs.stream().filter(MeasurementSpecification::isTriggersSelfAdaptations)
                .forEach(this::initFixedSizeAggregation);
        variableSizeAggregationMeasurementSpecs.stream().filter(MeasurementSpecification::isTriggersSelfAdaptations)
                .forEach(this::initVariableSizeAggregation);
    }

    private void initVariableSizeAggregation(MeasurementSpecification measurementSpecification) {
        MeasuringPoint measuringPoint = measurementSpecification.getMonitor().getMeasuringPoint();
        Optional<NumericalBaseMetricDescription> expectedMetric = GET_NUMERICAL_BASE_METRIC_SWITCH
                .doSwitch(measurementSpecification.getMetricDescription());

        // fails in case optional is empty
        checkValidity(expectedMetric);

        Calculator correspondingBaseCalculator = getBaseCalculator(expectedMetric.get(),
                measuringPoint).<IllegalStateException> orElseThrow(() -> new IllegalStateException(
                        "Variable size aggregation of measurements cannot be initialized.\n" + "No '"
                                + expectedMetric.get().getName() + "' calculator available for: " + "MeasuringPoint '"
                                + measuringPoint.getStringRepresentation() + "'.\n" + "Affected Monitor: '"
                                + measurementSpecification.getMonitor().getEntityName() + "'\n"
                                + "Ensure that measurement calculator has been created and registered within the ProbeFrameworkListener class!"));

        correspondingBaseCalculator.addObserver(new VariableSizeMeasurementAggregator(expectedMetric.get(),
                this.runtimeMeasurementModel, (VariableSizeAggregation) measurementSpecification.getProcessingType()));
    }

    private void initFixedSizeAggregation(MeasurementSpecification measurementSpecification) {
        MeasuringPoint measuringPoint = measurementSpecification.getMonitor().getMeasuringPoint();
        Optional<NumericalBaseMetricDescription> expectedMetric = GET_NUMERICAL_BASE_METRIC_SWITCH
                .doSwitch(measurementSpecification.getMetricDescription());

        // fails in case optional is empty
        checkValidity(expectedMetric);

        Calculator correspondingBaseCalculator = getBaseCalculator(expectedMetric.get(),
                measuringPoint).<IllegalStateException> orElseThrow(() -> new IllegalStateException(
                        "Fixed size aggregation of measurements cannot be initialized.\n" + "No '"
                                + expectedMetric.get().getName() + "' calculator available for: " + "MeasuringPoint '"
                                + measuringPoint.getStringRepresentation() + "'.\n" + "Affected Monitor: '"
                                + measurementSpecification.getMonitor().getEntityName() + "'\n"
                                + "Ensure that measurement calculator has been created and registered within the ProbeFrameworkListener class!"));

        correspondingBaseCalculator.addObserver(new FixedSizeMeasurementsAggregator(expectedMetric.get(),
                this.runtimeMeasurementModel, (FixedSizeAggregation) measurementSpecification.getProcessingType()));
    }

    private Optional<Calculator> getBaseCalculator(NumericalBaseMetricDescription metric,
            MeasuringPoint measuringPoint) {
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

    private static void checkValidity(Optional<NumericalBaseMetricDescription> expectedMetric) {
        if (!expectedMetric.isPresent()) {
            throw new IllegalStateException("So far, only " + NumericalBaseMetricDescription.class.getSimpleName()
                    + "s are supported for fixed and variable size aggregation!");
        }
    }

    private static final MetricSpecSwitch<Optional<NumericalBaseMetricDescription>> GET_NUMERICAL_BASE_METRIC_SWITCH = new MetricSpecSwitch<Optional<NumericalBaseMetricDescription>>() {

        @Override
        public Optional<NumericalBaseMetricDescription> caseNumericalBaseMetricDescription(
                NumericalBaseMetricDescription numericalBaseMetricDescription) {
            return Optional.of(numericalBaseMetricDescription);
        }

        @Override
        public Optional<NumericalBaseMetricDescription> defaultCase(EObject eObject) {
            return Optional.empty();
        }
    };
}

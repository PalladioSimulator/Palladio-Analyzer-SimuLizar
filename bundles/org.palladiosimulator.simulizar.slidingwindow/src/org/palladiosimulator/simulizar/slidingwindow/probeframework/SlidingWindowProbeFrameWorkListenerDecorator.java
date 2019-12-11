package org.palladiosimulator.simulizar.slidingwindow.probeframework;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.experimentanalysis.DiscardAllElementsPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindow;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.metricspec.MetricSpecPackage;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.monitorrepository.TimeDrivenAggregation;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractProbeFrameworkListener;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.slidingwindow.aggregators.SlidingWindowStatisticalCharacterizationAggregator;
import org.palladiosimulator.simulizar.slidingwindow.impl.SimulizarSlidingWindow;
import org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement.SlidingWindowRuntimeMeasurementsRecorder;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

/**
 * Implementation of the {@link AbstractRecordingProbeFrameworkListenerDecorator} class dedicated to
 * initialize {@link TimeDrivenAggregation}s of recorded measurements which are based on
 * {@link SimulizarSlidingWindow}s.
 * 
 * @see SlidingWindowRuntimeMeasurementsRecorder
 * @author Florian Rosenthal
 *
 */
public class SlidingWindowProbeFrameWorkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final EClass TIME_DRIVEN_AGGREGATION_ECLASS = MonitorRepositoryPackage.Literals.TIME_DRIVEN_AGGREGATION;

    private RegisterCalculatorFactoryDecorator calculatorFactory = null;
    private SimuComModel model;
    private RuntimeMeasurementModel runtimeMeasurementModel;

    private ISlidingWindowMoveOnStrategy discreteMetricScopeStrategy = null;
    private ISlidingWindowMoveOnStrategy continuousMetricScopeStrategy = null;

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();
        initSlidingWindowBasedMeasurements();
    }

    @Override
    public void setProbeFrameworkListener(final AbstractProbeFrameworkListener listener) {
        super.setProbeFrameworkListener(listener);
        this.calculatorFactory = RegisterCalculatorFactoryDecorator.class
                .cast(getProbeFrameworkListener().getCalculatorFactory());
        this.model = getProbeFrameworkListener().getSimuComModel();
        PCMPartitionManager manager = getProbeFrameworkListener().getPCMPartitionManager();
        this.runtimeMeasurementModel = manager.findModel(RuntimeMeasurementPackage.eINSTANCE.getRuntimeMeasurementModel());
    }

    private void initSlidingWindowBasedMeasurements() {
        assert getProbeFrameworkListener() != null;

        Collection<MeasurementSpecification> measurementSpecs = this.getProbeFrameworkListener()
                .getMeasurementSpecificationsForProcessingType(TIME_DRIVEN_AGGREGATION_ECLASS);

        initTimeDrivenAggregators(measurementSpecs);
    }

    private void initTimeDrivenAggregators(final Collection<MeasurementSpecification> measurementSpecs) {
        if (!measurementSpecs.isEmpty()) {
            this.discreteMetricScopeStrategy = new DiscardAllElementsPriorToLowerBoundStrategy();
            this.continuousMetricScopeStrategy = new KeepLastElementPriorToLowerBoundStrategy();
            measurementSpecs.forEach(this::initAggregatorForMeasSpec);
        }
    }

    private void initAggregatorForMeasSpec(final MeasurementSpecification measurementSpec) {

        MeasuringPoint measuringPoint = measurementSpec.getMonitor().getMeasuringPoint();
        if (!MetricSpecPackage.Literals.NUMERICAL_BASE_METRIC_DESCRIPTION
                .isInstance(measurementSpec.getMetricDescription())) {
            throw new IllegalStateException(
                    "Time driven aggregation of measurements (sliding window based) cannot be initialized:\n"
                            + "Currently, only "
                            + MetricSpecPackage.Literals.NUMERICAL_BASE_METRIC_DESCRIPTION.getName()
                            + "s are supported!\n" + "MeasuringPoint '" + measuringPoint.getStringRepresentation()
                            + "'\n" + "Affected Monitor: '" + measurementSpec.getMonitor().getEntityName() + "'");
        }

        NumericalBaseMetricDescription expectedMetric = (NumericalBaseMetricDescription) measurementSpec
                .getMetricDescription();

        Calculator calculator = this.calculatorFactory.getCalculatorsForMeasuringPoint(measuringPoint).stream()
                .filter(calc -> MetricDescriptionUtility
                        .isBaseMetricDescriptionSubsumedByMetricDescription(expectedMetric, calc.getMetricDesciption()))
                .findAny()
                .orElseThrow(() -> new IllegalStateException(
                        "Time driven aggregation of measurements (sliding window based) cannot be initialized.\n"
                                + "No '" + expectedMetric.getName() + "' calculator available for: "
                                + "MeasuringPoint '" + measuringPoint.getStringRepresentation() + "'.\n"
                                + "Affected Monitor: '" + measurementSpec.getMonitor().getEntityName() + "'\n"
                                + "Ensure that measurement calculator has been created and registered within the ProbeFrameworkListener class!"));

        ISlidingWindowMoveOnStrategy moveOnStrategy = null;
        switch (expectedMetric.getScopeOfValidity()) {
        case CONTINUOUS:
            moveOnStrategy = this.continuousMetricScopeStrategy;
            break;
        case DISCRETE:
            moveOnStrategy = this.discreteMetricScopeStrategy;
            break;
        default:
            throw new AssertionError();
        }

        TimeDrivenAggregation aggregation = (TimeDrivenAggregation) measurementSpec.getProcessingType();
        SlidingWindow window = new SimulizarSlidingWindow(aggregation.getWindowLengthAsMeasure(),
                aggregation.getWindowIncrementAsMeasure(), expectedMetric, moveOnStrategy, this.model);

        SlidingWindowStatisticalCharacterizationAggregator windowAggregator = new SlidingWindowStatisticalCharacterizationAggregator(
                aggregation.getStatisticalCharacterization().getAggregator(expectedMetric));

        if (measurementSpec.isTriggersSelfAdaptations()) {
            // forward to PRM (i.e., RuntimeMeasurementModel)
            windowAggregator.addRecorder(new SlidingWindowRuntimeMeasurementsRecorder(this.runtimeMeasurementModel,
                    measurementSpec, measuringPoint));
        }

        super.registerMeasurementsRecorder(calculator, new SlidingWindowRecorder(window, windowAggregator));
    }
}

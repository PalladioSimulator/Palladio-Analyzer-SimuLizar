package org.palladiosimulator.simulizar.utilization.probeframework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.experimentanalysis.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindow;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.experimentanalysis.windowaggregators.SlidingWindowAggregator;
import org.palladiosimulator.experimentanalysis.windowaggregators.SlidingWindowUtilizationAggregator;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.ProcessingType;
import org.palladiosimulator.monitorrepository.TimeDriven;
import org.palladiosimulator.monitorrepository.WindowCharacterization;
import org.palladiosimulator.monitorrepository.util.MonitorRepositorySwitch;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.recorderframework.IRecorder;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.slidingwindow.impl.SimulizarSlidingWindow;
import org.palladiosimulator.simulizar.slidingwindow.runtimemeasurement.SlidingWindowRuntimeMeasurementsRecorder;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class UtilizationProbeFrameworkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final MetricSetDescription UTILIZATION_TUPLE_METRIC_DESC = MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE;
    private static final BaseMetricDescription UTILIZATION_METRIC_DESC = MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE;
    private static final MetricSetDescription STATE_TUPLE_METRIC_DESC = MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE;

    private static final MonitorRepositorySwitch<Optional<TimeDriven>> PROCESSING_TYPE_SWITCH = new MonitorRepositorySwitch<Optional<TimeDriven>>() {
        @Override
        public Optional<TimeDriven> caseTimeDriven(TimeDriven timeDriven) {
            return Optional.of(timeDriven);
        }

        @Override
        public Optional<TimeDriven> defaultCase(EObject eObject) {
            return Optional.empty();
        }
    };

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();
        initUtilizationMeasurements();
    }

    private void initUtilizationMeasurements() {
        assert getProbeFrameworkListener() != null;

        Collection<MeasurementSpecification> utilMeasurementSpecs = new ArrayList<>(getProbeFrameworkListener()
                .getMeasurementSpecificationsForMetricDescription(UTILIZATION_TUPLE_METRIC_DESC));
        // also consider case when utilization metric rather than utilization tuple metric is chosen
        utilMeasurementSpecs.addAll(
                getProbeFrameworkListener().getMeasurementSpecificationsForMetricDescription(UTILIZATION_METRIC_DESC));
        if (!utilMeasurementSpecs.isEmpty()) {
            RegisterCalculatorFactoryDecorator calcFactory = RegisterCalculatorFactoryDecorator.class
                    .cast(getProbeFrameworkListener().getCalculatorFactory());
            ISlidingWindowMoveOnStrategy strategy = new KeepLastElementPriorToLowerBoundStrategy();
            SimuComModel model = getProbeFrameworkListener().getSimuComModel();

            for (MeasurementSpecification spec : utilMeasurementSpecs) {
                MeasuringPoint mp = spec.getMonitor().getMeasuringPoint();

                Calculator calculator = calcFactory.getCalculatorByMeasuringPointAndMetricDescription(mp,
                        STATE_TUPLE_METRIC_DESC);
                if (calculator == null) {
                    throw new IllegalStateException(
                            "Utilization measurements (sliding window based) cannot be initialized.\n"
                                    + "No state of active resource calculator available for: "
                                    + mp.getStringRepresentation() + "\n"
                                    + "Ensure that initializeModelSyncers() in SimulizarRuntimeState is called prior "
                                    + "to initializeInterpreterListeners()!");
                }
                setupUtilizationRecorder(calculator, spec, strategy, model,
                        getProbeFrameworkListener().getRuntimeMeasurementModel());
            }
        }
    }

    private void setupUtilizationRecorder(Calculator calculator,
            final MeasurementSpecification utilizationMeasurementSpec, ISlidingWindowMoveOnStrategy moveOnStrategy,
            SimuComModel model, RuntimeMeasurementModel rmModel) {

        Optional<TimeDriven> timeDrivenProcessingType = PROCESSING_TYPE_SWITCH
                .doSwitch(utilizationMeasurementSpec.getProcessingType());

        // this call crashes in case measurement specification is invalid
        checkValidity(utilizationMeasurementSpec, timeDrivenProcessingType);

        WindowCharacterization windowCharacterization = timeDrivenProcessingType.get().getWindowCharacterization();

        Map<String, Object> recorderConfigurationMap = createRecorderConfigMapWithAcceptedMetricAndMeasuringPoint(
                UTILIZATION_TUPLE_METRIC_DESC, calculator.getMeasuringPoint());

        IRecorder baseRecorder = initializeRecorder(recorderConfigurationMap);

        SlidingWindow window = new SimulizarSlidingWindow(windowCharacterization.getWindowLengthAsMeasure(),
                windowCharacterization.getWindowIncrementAsMeasure(), STATE_TUPLE_METRIC_DESC, moveOnStrategy, model);

        SlidingWindowAggregator utilizationAggregator = new SlidingWindowUtilizationAggregator(baseRecorder);
        SlidingWindowRecorder windowRecorder = new SlidingWindowRecorder(window, utilizationAggregator);
        // register recorder at calculator
        registerMeasurementsRecorder(calculator, windowRecorder);
        // forward utilization measurements to RuntimeMeasurementModel (the former PRM)
        utilizationAggregator.addRecorder(new SlidingWindowRuntimeMeasurementsRecorder(rmModel,
                utilizationMeasurementSpec, utilizationMeasurementSpec.getMonitor().getMeasuringPoint()));
    }

    private static void checkValidity(MeasurementSpecification utilizationMeasurementSpec,
            Optional<TimeDriven> aggregation) {

        if (!aggregation.isPresent()) {
            throw new IllegalArgumentException(
                    "MetricDescription (" + utilizationMeasurementSpec.getMetricDescription().getName() + ") '"
                            + utilizationMeasurementSpec.getName() + "' of Monitor '"
                            + utilizationMeasurementSpec.getMonitor().getEntityName() + "' must provide a "
                            + ProcessingType.class.getName() + " of Type '" + TimeDriven.class.getName() + "'!");
        }
    }
}

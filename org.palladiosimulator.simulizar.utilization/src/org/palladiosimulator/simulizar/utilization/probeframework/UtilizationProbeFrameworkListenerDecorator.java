package org.palladiosimulator.simulizar.utilization.probeframework;

import java.util.Collection;
import java.util.Map;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindow;
import org.palladiosimulator.experimentanalysis.SlidingWindow.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindowAggregator;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.experimentanalysis.SlidingWindowUtilizationAggregator;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.recorderframework.IRecorder;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractRecordingProbeFrameworkListenerDecorator;
import org.palladiosimulator.simulizar.slidingwindow.impl.SimulizarSlidingWindow;
import org.palladiosimulator.simulizar.slidingwindow.util.SimulizarSlidingWindowUtil;
import org.palladiosimulator.simulizar.utilization.runtimemeasurement.UtilizationRuntimeMeasurementsRecorder;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class UtilizationProbeFrameworkListenerDecorator extends AbstractRecordingProbeFrameworkListenerDecorator {

    private static final MetricSetDescription UTILIZATION_TUPLE_METRIC_DESC = MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE;
    private static final MetricSetDescription STATE_TUPLE_METRIC_DESC = MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE;

    @Override
    public void registerMeasurements() {
        super.registerMeasurements();
        initUtilizationMeasurements();
    }

    private void initUtilizationMeasurements() {
        assert getProbeFrameworkListener() != null;

        Collection<MeasurementSpecification> utilMeasurementSpecs = getProbeFrameworkListener()
                .getMeasurementSpecificationsForMetricDescription(UTILIZATION_TUPLE_METRIC_DESC);
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
                setupUtilizationRecorder(calculator, spec, strategy, model);
            }
        }
    }

    private void setupUtilizationRecorder(Calculator calculator, MeasurementSpecification utilizationMeasurementSpec,
            ISlidingWindowMoveOnStrategy moveOnStrategy, SimuComModel model) {

        if (utilizationMeasurementSpec.getTemporalRestriction() == null) {
            throw new IllegalArgumentException("MetricDescription (Utilization of Active Resource Tuple) '"
                    + utilizationMeasurementSpec.getName() + "' of Monitor '"
                    + utilizationMeasurementSpec.getMonitor().getEntityName()
                    + "' must provide a TemporalCharacterization of Type 'Interval' or 'DelayedInterval'");
        }

        Measure<Double, Duration>[] windowProperties = SimulizarSlidingWindowUtil
                .getWindowPropertiesFromTemporalCharacterization(utilizationMeasurementSpec.getTemporalRestriction());

        Map<String, Object> recorderConfigurationMap = createRecorderConfigMapWithAcceptedMetricAndMeasuringPoint(
                UTILIZATION_TUPLE_METRIC_DESC, calculator.getMeasuringPoint());

        IRecorder baseRecorder = initializeRecorder(recorderConfigurationMap);

        SlidingWindow window = new SimulizarSlidingWindow(windowProperties[0], windowProperties[1],
                STATE_TUPLE_METRIC_DESC, moveOnStrategy, model);

        SlidingWindowAggregator utilizationAggregator = new SlidingWindowUtilizationAggregator(baseRecorder);
        SlidingWindowRecorder windowRecorder = new SlidingWindowRecorder(window, utilizationAggregator);
        // register recorder at calculator
        registerMeasurementsRecorder(calculator, windowRecorder);
        // forward utilization measurements to RuntimeMeasurementModel (the former PRM)
        utilizationAggregator.addRecorder(new UtilizationRuntimeMeasurementsRecorder(getProbeFrameworkListener()
                .getRuntimeMeasurementModel(), utilizationMeasurementSpec, utilizationMeasurementSpec.getMonitor()
                .getMeasuringPoint()));
    }
}

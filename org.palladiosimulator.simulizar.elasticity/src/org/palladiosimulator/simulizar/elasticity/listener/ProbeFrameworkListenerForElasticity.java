package org.palladiosimulator.simulizar.elasticity.listener;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC_TUPLE;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.interpreter.listener.ProbeFrameworkListener;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.probes.TakeReconfigurationDurationProbe;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.CalculatorHelper;

public class ProbeFrameworkListenerForElasticity extends ProbeFrameworkListener {

	public ProbeFrameworkListenerForElasticity(IModelAccess modelAccess, SimuComModel simuComModel,
			Reconfigurator reconfigurator, IMeasurementSourceListener reconfigurationTimeAggregator) {
		super(modelAccess, simuComModel, reconfigurator);
		 initReconfigurationTimeMeasurement(reconfigurator, reconfigurationTimeAggregator);
	}
	
	private void initReconfigurationTimeMeasurement(Reconfigurator reconfigurator, IMeasurementSourceListener reconfigurationTimeAggregator) {
		for (final MeasurementSpecification reconfigurationTimeMeasurementSpec : this
                .getMeasurementSpecificationsForMetricDescription(
                        MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC)) {
			final MeasuringPoint measuringPoint = reconfigurationTimeMeasurementSpec.getMonitor().getMeasuringPoint();
			final Probe probe = CalculatorHelper.getEventProbeSetWithCurrentTime(RECONFIGURATION_TIME_METRIC_TUPLE,
                    this.getSimuComModel().getSimulationControl(),
                    new TakeReconfigurationDurationProbe(reconfigurator));
			final Calculator calculator = this.getCalculatorFactory().buildReconfigurationTimeCalculator(measuringPoint, probe);
			calculator.addObserver(reconfigurationTimeAggregator);
		}
	}

}

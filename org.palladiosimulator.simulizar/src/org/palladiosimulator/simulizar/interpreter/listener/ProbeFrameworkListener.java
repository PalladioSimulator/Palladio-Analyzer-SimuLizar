package org.palladiosimulator.simulizar.interpreter.listener;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC_TUPLE;

import org.apache.log4j.Logger;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.reconfiguration.probes.TakeReconfigurationDurationProbe;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.CalculatorHelper;

public class ProbeFrameworkListener extends AbstractProbeFrameworkListener {

	private static final Logger LOGGER = Logger.getLogger(ProbeFrameworkListener.class);
	
	public ProbeFrameworkListener(IModelAccess modelAccess, SimuComModel simuComModel,
			Reconfigurator reconfigurator) {
		super(modelAccess, simuComModel, reconfigurator);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initReconfigurationTimeMeasurement() {
		for (final MeasurementSpecification reconfigurationTimeMeasurementSpec : this
                .getMeasurementSpecificationsForMetricDescription(
                        MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC)) {
            final MeasuringPoint measuringPoint = reconfigurationTimeMeasurementSpec.getMonitor().getMeasuringPoint();

            LOGGER.info("Created Reconfiguration Time Measuring Point");

            final Probe probe = CalculatorHelper.getEventProbeSetWithCurrentTime(RECONFIGURATION_TIME_METRIC_TUPLE,
                    this.simuComModel.getSimulationControl(),
                    new TakeReconfigurationDurationProbe(this.reconfigurator));
            this.calculatorFactory.buildReconfigurationTimeCalculator(measuringPoint, probe);
        }
	}

}

package de.upb.mdse.simulizar.loadbalancer.analyser.filter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.measure.unit.SI;

import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.annotation.Plugin;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.common.configuration.Configuration;
import de.uka.ipd.sdq.sensorframework.dao.file.FileDAOFactory;
import de.uka.ipd.sdq.sensorframework.entities.Experiment;
import de.uka.ipd.sdq.sensorframework.entities.ExperimentRun;
import de.uka.ipd.sdq.sensorframework.entities.TimeSpanSensor;

@Plugin()
public class SensorFrameWorkWriterFilter extends AbstractFilterPlugin {
	
	public static final String INPUT_RESPONSE_TIME_MEASUREMENTS = "rtMeasurements";
	private FileDAOFactory factory;
	private Experiment experiment;
	private ExperimentRun experimentRun;
	private Map<MethodID,TimeSpanSensor> sensors = new HashMap<MethodID, TimeSpanSensor>();
	
	public SensorFrameWorkWriterFilter(Configuration configuration) {
		super(configuration);
		initialiseSensorFramework();
	}
	
	private void initialiseSensorFramework() {
		factory = new FileDAOFactory("C:/data");
		experiment = factory.createExperimentDAO().addExperiment("ProtoDyn");
		experimentRun =experiment.addExperimentRun("ProtoDynRun "+new Date().toString());
		Runtime.getRuntime().addShutdownHook(new Thread(){

			@Override
			public void run() {
				super.run();
				factory.finalizeAndClose();
			}
			
		});
	}

	@Override
	public Configuration getCurrentConfiguration() {
		final Configuration configuration = new Configuration();
		return configuration;
	}

	@Override
	protected Configuration getDefaultConfiguration() {
		final Configuration configuration = new Configuration();
		return configuration;
	}
	
	@InputPort(name = INPUT_RESPONSE_TIME_MEASUREMENTS, description = "Receives incoming execution records", eventTypes = { ResponseTimeMeasurement.class })
	public final void inputEvent(final Object inEvent) {
		ResponseTimeMeasurement rtMeasurement = (ResponseTimeMeasurement) inEvent;
		if (!sensors.containsKey(rtMeasurement.getMethodID())) {
			TimeSpanSensor newSensor = experiment.addTimeSpanSensor(rtMeasurement.getMethodID().toString());
			sensors.put(rtMeasurement.getMethodID(), newSensor);
		}
		TimeSpanSensor sensor = sensors.get(rtMeasurement.getMethodID());
		experimentRun.addTimeSpanMeasurement(sensor, rtMeasurement.getTimestamp(), rtMeasurement.getMeasurement().doubleValue(SI.SECOND));
	}
}

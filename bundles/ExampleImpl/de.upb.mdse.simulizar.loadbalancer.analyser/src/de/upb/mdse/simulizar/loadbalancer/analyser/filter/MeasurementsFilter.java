package de.upb.mdse.simulizar.loadbalancer.analyser.filter;

import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.annotation.OutputPort;
import kieker.analysis.plugin.annotation.Plugin;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.common.configuration.Configuration;

@Plugin(outputPorts = @OutputPort(name = MeasurementsFilter.OUTPUT_MEASUREMENTS, 
	description = "Provides filtered measurements", 
	eventTypes = { ResponseTimeMeasurement.class }))
public class MeasurementsFilter extends AbstractFilterPlugin {

	public static final String OUTPUT_MEASUREMENTS = "outputMeasurements";
	public static final String INPUT_RESPONSE_TIME_MEASUREMENTS = "inputMeasurements";
	
	public MeasurementsFilter(Configuration configuration) {
		super(configuration);
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
		if (rtMeasurement.getMethodID().getHost().equals("LOADBALANCER")) {
			super.deliver(OUTPUT_MEASUREMENTS, inEvent);
		}
	}
	
}
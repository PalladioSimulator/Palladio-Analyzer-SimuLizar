package de.upb.mdse.simulizar.loadbalancer.analyser.filter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.annotation.OutputPort;
import kieker.analysis.plugin.annotation.Plugin;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.common.configuration.Configuration;

import org.jscience.physics.amount.Amount;

@Plugin(outputPorts = @OutputPort(name = BatchFilter.OUTPUT_BATCH_MAP, 
		description = "Provides batched values as map", 
		eventTypes = { Map.class }))
public class BatchFilter extends AbstractFilterPlugin {

	private static final Amount<Duration> DEFAULT_BATCH_SIZE = Amount.valueOf(5, SI.SECOND);
	
	public static final String OUTPUT_BATCH_MAP = "batchMap";
	public static final String INPUT_RESPONSE_TIME_MEASUREMENTS = "rtMeasurements";

	private Amount<Duration> lowerBatchIntervalBound = null;
	private Amount<Duration> upperBatchIntervalBound = null;
	
	private Map<Identifiable,Set<ResponseTimeMeasurement>> mappedValues = new HashMap<Identifiable, Set<ResponseTimeMeasurement>>();
	
	public BatchFilter(Configuration configuration) {
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
		if (isFirstMeasurement()) {
			initialiseBounds(rtMeasurement);
		} else {
			Amount<Duration> timestamp = Amount.valueOf(rtMeasurement.getTimestamp(), SI.NANO(SI.SECOND));
			if (timestamp.isLessThan(lowerBatchIntervalBound)) {
				// Old measurement, drop it
				return;
			}
			if (timestamp.isGreaterThan(upperBatchIntervalBound)) {
				lowerBatchIntervalBound = upperBatchIntervalBound;
				upperBatchIntervalBound = lowerBatchIntervalBound.plus(DEFAULT_BATCH_SIZE);
				super.deliver(OUTPUT_BATCH_MAP, mappedValues);
				mappedValues = new HashMap<Identifiable, Set<ResponseTimeMeasurement>>();
			}
			if (!mappedValues.containsKey(rtMeasurement.getMethodID())) {
				mappedValues.put(rtMeasurement.getMethodID(), new HashSet<ResponseTimeMeasurement>());
			}
			mappedValues.get(rtMeasurement.getMethodID()).add(rtMeasurement);
		}
	}

	private void initialiseBounds(ResponseTimeMeasurement rtMeasurement) {
		lowerBatchIntervalBound = Amount.valueOf(rtMeasurement.getTimestamp(), SI.NANO(SI.SECOND));
		upperBatchIntervalBound = lowerBatchIntervalBound.plus(DEFAULT_BATCH_SIZE);
	}

	private boolean isFirstMeasurement() {
		return lowerBatchIntervalBound == null;
	}
}

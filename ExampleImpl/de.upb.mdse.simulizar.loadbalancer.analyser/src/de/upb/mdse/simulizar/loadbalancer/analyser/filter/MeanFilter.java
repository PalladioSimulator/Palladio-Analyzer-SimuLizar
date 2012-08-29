package de.upb.mdse.simulizar.loadbalancer.analyser.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.measure.unit.SI;

import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.annotation.OutputPort;
import kieker.analysis.plugin.annotation.Plugin;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.common.configuration.Configuration;

import org.jscience.physics.amount.Amount;

@Plugin(outputPorts = @OutputPort(name = MeanFilter.OUTPUT_MEAN_MAP, 
		description = "Provides batched mean values as map", 
		eventTypes = { Map.class }))
public class MeanFilter extends AbstractFilterPlugin {

	public static final String OUTPUT_MEAN_MAP = "batchMeanMap";
	public static final String INPUT_BATCH_MAP = "batchMap";

	public MeanFilter(Configuration configuration) {
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
	
	@SuppressWarnings("unchecked")
	@InputPort(name = INPUT_BATCH_MAP, description = "Receives incoming batches", eventTypes = { Map.class })
	public final void inputEvent(final Object inEvent) {
		Map<Identifiable,Set<Measurement<?>>> map = (Map<Identifiable, Set<Measurement<?>>>) inEvent;
		Map<Identifiable,Amount<?>> result = new HashMap<Identifiable, Amount<?>>();
		for (Map.Entry<Identifiable, Set<Measurement<?>>> entry : map.entrySet()) {
			Amount<?> mean = Amount.valueOf(0, SI.SECOND);
			for (Measurement<?> measurement : entry.getValue()) {
				mean = mean.plus(measurement.getMeasurement());
			}
			mean = mean.divide(entry.getValue().size());
			result.put(entry.getKey(), mean);
		}
		super.deliver(OUTPUT_MEAN_MAP, result);
	}
}

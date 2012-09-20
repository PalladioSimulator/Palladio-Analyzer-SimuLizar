package de.upb.mdse.simulizar.loadbalancer.analyser.filter;

import java.util.Map;

import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.annotation.OutputPort;
import kieker.analysis.plugin.annotation.Plugin;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.analysis.plugin.reader.jms.JMSReader;
import kieker.common.configuration.Configuration;
import kieker.common.logging.Log;
import kieker.common.logging.LogFactory;

import org.jscience.physics.amount.Amount;

import de.upb.mdse.simulizar.loadbalancer.analyser.helper.MeasureHelper;

@Plugin(outputPorts = @OutputPort(name = ThresholdFilter.OUTPUT_THRESHOLD_VIOLATED_IDS, 
		description = "Provides batched values as map", 
		eventTypes = { Identifiable.class }))
public class ThresholdFilter extends AbstractFilterPlugin {

	private static final String HOSTID = "LOADBALANCER";
	private static final String METHOD = "public de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe defaultrepository.impl.ports.ILastverteiler_lastverteiler.entpacke0(de.uka.ipd.sdq.simucomframework.variables.StackContext)";

	private static final Log LOG = LogFactory.getLog(JMSReader.class);
	
	public ThresholdFilter(Configuration configuration) {
		super(configuration);
	}

	public static final String OUTPUT_THRESHOLD_VIOLATED_IDS = "violatedIDs";
	public static final String INPUT_MEAN_RT_MAP = "mean_Rt_Measurements";
	private static final Amount<Duration> THRESHOLD = Amount.valueOf(0.8,SI.SECOND);

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
	@InputPort(name = INPUT_MEAN_RT_MAP, description = "Receives incoming execution records", eventTypes = { Map.class })
	public final void inputEvent(final Object inEvent) {
		Map<Identifiable,Amount<Duration>> means = (Map<Identifiable, Amount<Duration>>) inEvent;
		MethodID methodID = new MethodID(METHOD, HOSTID);
		if (!means.containsKey(methodID)) {
			LOG.error("Tried to find method measurements for "+methodID+", but failed...");
			return;
		}
		Amount<Duration> timeToCheck = means.get(methodID);
		if (LOG.isDebugEnabled()) {
			LOG.debug(MeasureHelper.formatDuration(timeToCheck) + " < " + MeasureHelper.formatDuration(THRESHOLD) + "? "+timeToCheck.isGreaterThan(THRESHOLD));
		}
		if (timeToCheck.isGreaterThan(THRESHOLD)) {
			super.deliver(OUTPUT_THRESHOLD_VIOLATED_IDS, methodID);
		}
	}
	
}

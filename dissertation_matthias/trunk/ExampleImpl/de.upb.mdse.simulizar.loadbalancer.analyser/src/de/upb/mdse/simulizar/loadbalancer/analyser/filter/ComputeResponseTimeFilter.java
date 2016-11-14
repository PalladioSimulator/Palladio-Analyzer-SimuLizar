package de.upb.mdse.simulizar.loadbalancer.analyser.filter;

import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.annotation.OutputPort;
import kieker.analysis.plugin.annotation.Plugin;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.common.configuration.Configuration;
import kieker.common.logging.Log;
import kieker.common.logging.LogFactory;
import kieker.common.record.controlflow.OperationExecutionRecord;

import org.jscience.physics.amount.Amount;

import de.upb.mdse.simulizar.loadbalancer.analyser.helper.MeasureHelper;

@Plugin(outputPorts = @OutputPort(name = ComputeResponseTimeFilter.OUTPUT_PORT_RESPONSE_TIMES, 
	description = "Provides response times of operation execution times as stream", 
	eventTypes = { ResponseTimeMeasurement.class }))
public class ComputeResponseTimeFilter extends AbstractFilterPlugin {

	public static final String OUTPUT_PORT_RESPONSE_TIMES = "outputResponseTimes";
	public static final String INPUT_PORT_NAME_EVENTS = "inputExecutionRecords";
	
	private static final Log LOG = LogFactory.getLog(ComputeResponseTimeFilter.class);
	
	//private final JFreeChartObserver display = new JFreeChartObserver("Measurements");

	public ComputeResponseTimeFilter(Configuration configuration) {
		super(configuration);
		
		//display.showObserver();
		//display.generateTestData();
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

	@InputPort(name = INPUT_PORT_NAME_EVENTS, description = "Receives incoming execution records", eventTypes = { OperationExecutionRecord.class })
	public final void inputEvent(final Object inEvent) {
		OperationExecutionRecord record = (OperationExecutionRecord) inEvent;
		long diff = record.getTout() - record.getTin();
		Amount<Duration> result = Amount.valueOf(diff, SI.NANO(SI.SECOND));
		LOG.debug(MeasureHelper.formatDuration(result));
		
		//this.display.displayMeasurement(record.getLoggingTimestamp(), diff);
		
		super.deliver(OUTPUT_PORT_RESPONSE_TIMES, new ResponseTimeMeasurement(record.getOperationSignature(), record.getHostname(), result, record.getLoggingTimestamp()));
	}

}

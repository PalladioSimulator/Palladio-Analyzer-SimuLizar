package de.upb.mdse.simulizar.loadbalancer.analyser.filter;

import java.util.HashMap;
import java.util.Map;

import javax.measure.unit.SI;

import kieker.analysis.plugin.annotation.InputPort;
import kieker.analysis.plugin.annotation.Plugin;
import kieker.analysis.plugin.filter.AbstractFilterPlugin;
import kieker.common.configuration.Configuration;

import org.jfree.data.xy.XYSeries;

import de.upb.mdse.simulizar.loadbalancer.analyser.chart.JFreeChartObserver;

@Plugin()
public class JFreeChartFilter extends AbstractFilterPlugin {
	
	public static final String INPUT_RESPONSE_TIME_MEASUREMENTS= "rtMeasurements";
	
	private final JFreeChartObserver jfreechart = new JFreeChartObserver("Measurements");
	private Map<MethodID, XYSeries> series = new HashMap<MethodID, XYSeries>();

	public JFreeChartFilter(Configuration configuration) {
		super(configuration);
		jfreechart.showObserver();
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
	
	@InputPort(name = INPUT_RESPONSE_TIME_MEASUREMENTS, description = "Receives incoming response times", eventTypes = { ResponseTimeMeasurement.class })
	public final void inputEvent(final Object inEvent) {
		ResponseTimeMeasurement rtMeasurement = (ResponseTimeMeasurement) inEvent;
		if (!rtMeasurement.getMethodID().getHost().equals("LOADBALANCER")) {
			if (!series.containsKey(rtMeasurement.getMethodID())) {
				XYSeries newSeries = new XYSeries(rtMeasurement.getMethodID().getHost());
				series.put(rtMeasurement.getMethodID(), newSeries);
				jfreechart.addSeries(newSeries);
			}
			XYSeries theSeries = series.get(rtMeasurement.getMethodID());
			theSeries.add(rtMeasurement.getTimestamp(), rtMeasurement.getMeasurement().doubleValue(SI.SECOND));
		}
	}

}

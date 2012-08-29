package de.upb.mdse.simulizar.loadbalancer.analyser.helper;

import javax.measure.Measurable;
import javax.measure.quantity.Duration;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

public class MeasureHelper {

	@SuppressWarnings("unchecked")
	public static String formatDuration(Measurable<Duration> result) {
		if (result == null)
			return "null";
		
		Unit<Duration>[] units = new Unit[] {SI.NANO(SI.SECOND), SI.MICRO(SI.SECOND), SI.MILLI(SI.SECOND), SI.SECOND, NonSI.MINUTE, NonSI.HOUR};
		for (Unit<Duration> u : units) {
			double value = result.doubleValue(u);
			if (Math.abs(value) < 1000) {
				return value + " " + u;
			}
		}
		return result.toString();
	}
	
}

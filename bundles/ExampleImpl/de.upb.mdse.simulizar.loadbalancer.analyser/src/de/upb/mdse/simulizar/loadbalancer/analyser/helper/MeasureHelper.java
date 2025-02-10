package de.upb.mdse.simulizar.loadbalancer.analyser.helper;

import jakarta.measure.Measurable;
import jakarta.measure.quantity.Duration;
import jakarta.measure.unit.NonSI;
import jakarta.measure.unit.SI;
import jakarta.measure.unit.Unit;

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

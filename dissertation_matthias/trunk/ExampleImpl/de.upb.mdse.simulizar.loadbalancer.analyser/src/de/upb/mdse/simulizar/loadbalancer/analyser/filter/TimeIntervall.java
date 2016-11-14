package de.upb.mdse.simulizar.loadbalancer.analyser.filter;

import javax.measure.quantity.Duration;

import javolution.lang.Immutable;
import javolution.lang.ValueType;

import org.jscience.physics.amount.Amount;

public class TimeIntervall implements Immutable, ValueType {

	private final Amount<Duration> lowerIntervalBound;
	private final Amount<Duration> upperIntervalBound;
	
	/**
	 * @param lowerBatchIntervalBound
	 * @param upperBatchIntervalBound
	 */
	public TimeIntervall(Amount<Duration> lowerBatchIntervalBound,
			Amount<Duration> upperBatchIntervalBound) {
		super();
		this.lowerIntervalBound = lowerBatchIntervalBound;
		this.upperIntervalBound = upperBatchIntervalBound;
	}

	/**
	 * @return the lowerBatchIntervalBound
	 */
	public final Amount<Duration> getLowerBatchIntervalBound() {
		return lowerIntervalBound;
	}

	/**
	 * @return the upperBatchIntervalBound
	 */
	public final Amount<Duration> getUpperBatchIntervalBound() {
		return upperIntervalBound;
	}
	
	public boolean isIn(Amount<Duration> point) {
		return lowerIntervalBound.isLessThan(point) &&
				upperIntervalBound.isGreaterThan(point);
	}
	
	public boolean isTimestampBefore(Amount<Duration> point) {
		return lowerIntervalBound.isGreaterThan(point);
	}

	public boolean isTimestampAfter(Amount<Duration> point) {
		return upperIntervalBound.isLessThan(point);
	}
	
	public TimeIntervall shift(Amount<Duration> offset) {
		return new TimeIntervall(lowerIntervalBound.plus(offset), upperIntervalBound.plus(offset));
	}

	@Override
	public Object copy() {
		return new TimeIntervall(lowerIntervalBound, upperIntervalBound);
	}

}

package de.upb.mdse.simulizar.loadbalancer.analyser.filter;

import javax.measure.quantity.Duration;

import org.jscience.physics.amount.Amount;

import de.upb.mdse.simulizar.loadbalancer.analyser.helper.MeasureHelper;

/**
 * @author snowball
 *
 */
public class ResponseTimeMeasurement implements Measurement<Duration> {
	
	private final long timestamp;
	private final MethodID methodID;
	private final Amount<Duration> responseTime;
	
	/**
	 * @param method
	 * @param host
	 * @param responseTime
	 * @param timestamp 
	 */
	public ResponseTimeMeasurement(String method, String host,
			Amount<Duration> responseTime, long timestamp) {
		super();
		this.methodID = new MethodID(method, host);
		this.responseTime = responseTime;
		this.timestamp = timestamp;
	}

	/**
	 * @return the timestamp
	 */
	public final long getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the method
	 */
	public final MethodID getMethodID() {
		return methodID;
	}

	/**
	 * @return the responseTime
	 */
	public final Amount<Duration> getResponseTime() {
		return responseTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ">" + MeasureHelper.formatDuration(responseTime) + "<";
	}

	@Override
	public Amount<Duration> getMeasurement() {
		return getResponseTime();
	}
	
}

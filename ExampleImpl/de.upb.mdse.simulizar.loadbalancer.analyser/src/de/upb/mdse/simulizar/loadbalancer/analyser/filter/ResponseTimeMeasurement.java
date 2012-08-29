package de.upb.mdse.simulizar.loadbalancer.analyser.filter;

import javax.measure.quantity.Duration;

import org.jscience.physics.amount.Amount;

import de.upb.mdse.simulizar.loadbalancer.analyser.helper.MeasureHelper;

/**
 * @author snowball
 *
 */
public class ResponseTimeMeasurement implements Measurement<Duration> {

	public class MethodID implements Identifiable {
		
		private final String method;
		private final String host;
		
		/**
		 * @param method
		 * @param host
		 */
		public MethodID(String method, String host) {
			super();
			this.method = method;
			this.host = host;
		}
		
		/**
		 * @return the method
		 */
		public final String getMethod() {
			return method;
		}
		
		/**
		 * @return the host
		 */
		public final String getHost() {
			return host;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((host == null) ? 0 : host.hashCode());
			result = prime * result
					+ ((method == null) ? 0 : method.hashCode());
			return result;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MethodID other = (MethodID) obj;
			if (host == null) {
				if (other.host != null)
					return false;
			} else if (!host.equals(other.host))
				return false;
			if (method == null) {
				if (other.method != null)
					return false;
			} else if (!method.equals(other.method))
				return false;
			return true;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "MethodID [method=" + method + ", host=" + host + "]";
		}
	}
	
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

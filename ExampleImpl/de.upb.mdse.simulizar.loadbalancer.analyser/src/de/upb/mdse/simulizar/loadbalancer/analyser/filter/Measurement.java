package de.upb.mdse.simulizar.loadbalancer.analyser.filter;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

public interface Measurement<V,Q extends Quantity> {

	public Measure<V,Q> getMeasurement();
	
}

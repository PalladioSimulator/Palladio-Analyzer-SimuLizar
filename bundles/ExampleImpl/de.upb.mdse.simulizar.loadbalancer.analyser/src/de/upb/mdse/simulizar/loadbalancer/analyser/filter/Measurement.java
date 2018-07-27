package de.upb.mdse.simulizar.loadbalancer.analyser.filter;

import javax.measure.quantity.Quantity;

import org.jscience.physics.amount.Amount;

public interface Measurement<Q extends Quantity> {

	public Amount<Q> getMeasurement();
	
}

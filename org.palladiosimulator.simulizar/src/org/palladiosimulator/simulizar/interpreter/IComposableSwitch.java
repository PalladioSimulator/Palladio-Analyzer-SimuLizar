package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.util.Switch;

/**
 * Interface for a switch which is able to composed together with other switches.
 * 
 * @author Matthias Rombach
 *
 */
public interface IComposableSwitch {
	
	/**
	 * @return the ComposedSwitch containing the implementing switch or any other switch preferred for the doSwitch
	 */
	public Switch<Object> getParentSwitch();
}

package de.upb.pcm.interpreter.sdinterpreter;

import org.eclipse.emf.ecore.EObject;

/**
 * Interface for a component that is able to reconfigure the model@runtime.
 * @author snowball
 *
 */
public interface IReconfigurator {

	public abstract void runReconfiguration(EObject monitoredElement);

}
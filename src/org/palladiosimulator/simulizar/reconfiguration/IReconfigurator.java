package org.palladiosimulator.simulizar.reconfiguration;

import org.eclipse.emf.ecore.EObject;

/**
 * Interface for a component that is able to reconfigure the PCM model@runtime.
 * 
 * @author snowball
 * 
 */
public interface IReconfigurator {

    /**
     * Trigger a reconfiguration of the model@runtime. Clients should both check whether they
     * should reconfigure and do the reconfiguration if the check is positive.
     * 
     * @param monitoredElement PCM model element for which a new sensor measurement arrived. 
     */
    public abstract void checkAndExecute(EObject monitoredElement);

}

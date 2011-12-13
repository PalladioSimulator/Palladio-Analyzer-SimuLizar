/**
 * 
 */
package de.upb.pcm.interpreter.interfaces;


import org.eclipse.emf.ecore.EObject;


/**
 * Common interface for pcm model switches.
 * 
 * @author Joachim Meyer
 * @param <T> return type of switch methods.
 */
public interface IPCMModelSwitch<T>
{
   /**
    * Starts switch with given pcm model element.
    * 
    * @param object the pcm model element.
    * @return any Object of type T.
    */
   T doSwitch(EObject object);
}

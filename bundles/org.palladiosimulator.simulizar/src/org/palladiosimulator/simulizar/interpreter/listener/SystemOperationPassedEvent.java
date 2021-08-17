package org.palladiosimulator.simulizar.interpreter.listener;

import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Signature;

import de.uka.ipd.sdq.simucomframework.Context;



/**
 * Event for the SystemOperation, which needs three elements
 * to be identified: System, ProvidedRole, Signature.
 * 
 * @author Marco Kugler
 *
 * @param <T>
 * @param <R>
 * @param <S>
 */
public class SystemOperationPassedEvent<T extends org.palladiosimulator.pcm.system.System, R extends ProvidedRole, S extends Signature> extends ModelElementPassedEvent<T> {

    private final R providedRole;
    private final S signature;

    public SystemOperationPassedEvent(final R providedRole, final EventType eventType, final Context context,
            final S signature, final T system) {
        
        super(system, eventType, context);
        this.providedRole = providedRole;
        this.signature = signature;
        
    }
    
    public S getSignature() {
        return signature;
    }

    public R getProvidedRole() {
        return providedRole;
    }
    
    public T getSystem() {
        return this.getModelElement();
    }
}

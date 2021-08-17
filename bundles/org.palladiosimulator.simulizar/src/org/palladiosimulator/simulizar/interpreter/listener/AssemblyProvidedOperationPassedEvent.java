package org.palladiosimulator.simulizar.interpreter.listener;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Signature;

import de.uka.ipd.sdq.simucomframework.Context;

/**
 * Event for the AssemblyProvidedOperation, which needs three elements
 * to be identified: AssemblyContext, ProvidedRoel, Signature.
 * 
 * @author Marco Kugler
 *
 * @param <T>
 * @param <R>
 * @param <S>
 */
public class AssemblyProvidedOperationPassedEvent<T extends AssemblyContext, R extends ProvidedRole, S extends Signature>
        extends ModelElementPassedEvent<T> {

    private final R providedRole;
    private final S signature;

    public AssemblyProvidedOperationPassedEvent(final R providedRole, final EventType eventType, final Context context,
            final S signature, final T assemblyContext) {
        super(assemblyContext, eventType, context);
        this.signature = signature;
        this.providedRole = providedRole;
    }

    public R getProvidedRole() {
        return providedRole;
    }

    public S getSignature() {
        return signature;
    }

    public T getAssemblyContext() {
        return this.getModelElement();
    }
}

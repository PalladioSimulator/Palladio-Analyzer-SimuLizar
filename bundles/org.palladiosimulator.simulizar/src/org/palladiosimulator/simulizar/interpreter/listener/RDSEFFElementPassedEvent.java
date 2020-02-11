package org.palladiosimulator.simulizar.interpreter.listener;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;

import de.uka.ipd.sdq.simucomframework.Context;

public class RDSEFFElementPassedEvent<T extends EObject> extends ModelElementPassedEvent<T> {

    private final AssemblyContext assemblyContext;

    public RDSEFFElementPassedEvent(final T modelElement, final EventType eventType, final Context context,
            final AssemblyContext assemblyContext) {
        super(modelElement, eventType, context);
        this.assemblyContext = assemblyContext;
    }

    /**
     * @return the assemblyContext
     */
    public AssemblyContext getAssemblyContext() {
        return this.assemblyContext;
    }

}

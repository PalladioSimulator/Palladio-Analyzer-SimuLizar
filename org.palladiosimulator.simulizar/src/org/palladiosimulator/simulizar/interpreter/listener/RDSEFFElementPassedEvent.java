package org.palladiosimulator.simulizar.interpreter.listener;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

public class RDSEFFElementPassedEvent<T extends EObject> extends ModelElementPassedEvent<T> {

    private final AssemblyContext assemblyContext;

    public RDSEFFElementPassedEvent(final T modelElement, final EventType eventType, final SimuComSimProcess thread,
            final AssemblyContext assemblyContext) {
        super(modelElement, eventType, thread);
        this.assemblyContext = assemblyContext;
    }

    /**
     * @return the assemblyContext
     */
    public AssemblyContext getAssemblyContext() {
        return this.assemblyContext;
    }

}

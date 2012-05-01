package de.upb.pcm.simulizar.interpreter.listener;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

public class RDSEFFElementPassedEvent<T extends EObject> extends ModelElementPassedEvent<T> {

	private final AssemblyContext assemblyContext;

	public RDSEFFElementPassedEvent(T modelElement, EventType eventType, SimuComSimProcess thread, AssemblyContext assemblyContext) {
		super(modelElement, eventType, thread);
		this.assemblyContext = assemblyContext;
	}

	/**
	 * @return the assemblyContext
	 */
	public AssemblyContext getAssemblyContext() {
		return assemblyContext;
	}
	
}

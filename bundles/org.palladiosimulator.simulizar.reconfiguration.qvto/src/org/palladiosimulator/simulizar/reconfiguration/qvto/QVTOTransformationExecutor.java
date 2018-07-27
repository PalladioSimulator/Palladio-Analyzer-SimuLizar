package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.m2m.qvt.oml.ExecutionContext;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.ModelExtent;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;

public class QVTOTransformationExecutor {
	private AtomicBoolean inUse = new AtomicBoolean(false);
	
	protected TransformationExecutor internalExecutor = null;

	public QVTOTransformationExecutor(URI uri) {
		setUpInternalExecutor(uri, Optional.empty());
	}
	
	public QVTOTransformationExecutor(URI uri, EPackage.Registry registry) {
		setUpInternalExecutor(uri, Optional.of(registry));
	}
	
	public Diagnostic loadTransformation() {
		return internalExecutor.loadTransformation();
	}
	
	public Diagnostic loadTransformation(IProgressMonitor monitor) {
		return internalExecutor.loadTransformation(monitor);
	}
	
	public ExecutionDiagnostic execute(ExecutionContext executionContext,
			ModelExtent... modelParameters) {
		if (!this.inUse.compareAndSet(false, true)) {
			throw new IllegalStateException("This QVTOTransformationExecutor instance is already in use");
		}
		ExecutionDiagnostic result = internalExecutor.execute(executionContext, modelParameters);
		internalExecutor.cleanup();
		this.inUse.set(false);
		return result;
	}
	
	public boolean isInUse() {
		return this.inUse.get();
	}
	
	protected void setUpInternalExecutor(URI uri, Optional<EPackage.Registry> registry) {
		if (registry.isPresent()) {
			internalExecutor = new TransformationExecutor(uri, registry.get());
		} else {
			internalExecutor = new TransformationExecutor(uri);
		}
	}

}

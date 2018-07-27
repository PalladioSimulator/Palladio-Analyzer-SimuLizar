package org.palladiosimulator.simulizar.reconfiguration.qvto.util;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Supplier;

import org.palladiosimulator.simulizar.reconfiguration.qvto.QVTOTransformationExecutor;

public class QVTOTransformationExecutorPool {
	private final Collection<SoftReference<QVTOTransformationExecutor>> availableExecutors;
	private final ReferenceQueue<QVTOTransformationExecutor> collectedExecutors;
	
	protected final Supplier<QVTOTransformationExecutor> executorSupplier;
	
	public QVTOTransformationExecutorPool(Supplier<QVTOTransformationExecutor> executorSupplier) {
		availableExecutors = new LinkedList<SoftReference<QVTOTransformationExecutor>>();
		collectedExecutors = new ReferenceQueue<QVTOTransformationExecutor>();
		this.executorSupplier = executorSupplier;
	}
	
	public QVTOTransformationExecutor getExecutor() {
		for (Reference<? extends QVTOTransformationExecutor> ref = collectedExecutors.poll(); ref != null; ref = collectedExecutors.poll()) {
			availableExecutors.remove(ref);
		}
		return availableExecutors.stream().map(ref -> ref.get()).filter(ref -> ref != null).filter(exec -> !exec.isInUse()).findFirst()
			.orElseGet(() -> {
				QVTOTransformationExecutor exec = executorSupplier.get();
				availableExecutors.add(new SoftReference<QVTOTransformationExecutor>(exec, collectedExecutors));
				return exec;
			});
	}

}

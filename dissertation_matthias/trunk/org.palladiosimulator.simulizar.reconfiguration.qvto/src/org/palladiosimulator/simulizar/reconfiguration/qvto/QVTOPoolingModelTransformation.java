package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.util.Collection;
import java.util.function.Supplier;

import org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.QVTOTransformationExecutorPool;

@SuppressWarnings("restriction")
public class QVTOPoolingModelTransformation extends QvtoModelTransformation {
	protected final QVTOTransformationExecutorPool executorPool;
	public QVTOPoolingModelTransformation(OperationalTransformation transformation, Supplier<QVTOTransformationExecutor> executorSupplier,
			Collection<TransformationParameterInformation> paramInfo) {
		super(transformation, null, paramInfo);
		this.executorPool = new QVTOTransformationExecutorPool(executorSupplier);
	}

	@Override
	public QVTOTransformationExecutor getTransformationExecutor() {
		return executorPool.getExecutor();
	}
}

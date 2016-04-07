/**
 */
package org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation;
import org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.QvtoTransformation;
import org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.QvtoreconfigurationPackage;
import org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Qvto Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class QvtoTransformationImpl extends ModelTransformationImpl<OperationalTransformation> implements QvtoTransformation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QvtoTransformationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QvtoreconfigurationPackage.Literals.QVTO_TRANSFORMATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setModelTransformation(OperationalTransformation newModelTransformation) {
		super.setModelTransformation(newModelTransformation);
	}

} //QvtoTransformationImpl

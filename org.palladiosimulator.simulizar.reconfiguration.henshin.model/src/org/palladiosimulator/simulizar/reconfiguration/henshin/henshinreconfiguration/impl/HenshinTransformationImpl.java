/**
 */
package org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.henshin.model.Unit;
import org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.HenshinTransformation;
import org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.HenshinreconfigurationPackage;
import org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Henshin Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class HenshinTransformationImpl extends ModelTransformationImpl<Unit> implements HenshinTransformation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HenshinTransformationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HenshinreconfigurationPackage.Literals.HENSHIN_TRANSFORMATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setModelTransformation(Unit newModelTransformation) {
		super.setModelTransformation(newModelTransformation);
	}

} //HenshinTransformationImpl

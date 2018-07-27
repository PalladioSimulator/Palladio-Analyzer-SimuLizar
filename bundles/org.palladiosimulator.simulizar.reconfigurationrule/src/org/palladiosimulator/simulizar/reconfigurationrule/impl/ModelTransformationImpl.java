/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;
import org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Transformation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl#getModelTransformation <em>Model Transformation</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ModelTransformationImpl<ExecutableTransformationElement extends Object> extends MinimalEObjectImpl.Container implements ModelTransformation<ExecutableTransformationElement> {
	/**
	 * The cached value of the '{@link #getModelTransformation() <em>Model Transformation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelTransformation()
	 * @generated
	 * @ordered
	 */
	protected ExecutableTransformationElement modelTransformation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelTransformationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReconfigurationrulePackage.Literals.MODEL_TRANSFORMATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public ExecutableTransformationElement getModelTransformation() {
		if (modelTransformation != null && ((EObject)modelTransformation).eIsProxy()) {
			InternalEObject oldModelTransformation = (InternalEObject)modelTransformation;
			modelTransformation = (ExecutableTransformationElement)eResolveProxy(oldModelTransformation);
			if (modelTransformation != oldModelTransformation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReconfigurationrulePackage.MODEL_TRANSFORMATION__MODEL_TRANSFORMATION, oldModelTransformation, modelTransformation));
			}
		}
		return modelTransformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutableTransformationElement basicGetModelTransformation() {
		return modelTransformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelTransformation(ExecutableTransformationElement newModelTransformation) {
		ExecutableTransformationElement oldModelTransformation = modelTransformation;
		modelTransformation = newModelTransformation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReconfigurationrulePackage.MODEL_TRANSFORMATION__MODEL_TRANSFORMATION, oldModelTransformation, modelTransformation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReconfigurationrulePackage.MODEL_TRANSFORMATION__MODEL_TRANSFORMATION:
				if (resolve) return getModelTransformation();
				return basicGetModelTransformation();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ReconfigurationrulePackage.MODEL_TRANSFORMATION__MODEL_TRANSFORMATION:
				setModelTransformation((ExecutableTransformationElement)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ReconfigurationrulePackage.MODEL_TRANSFORMATION__MODEL_TRANSFORMATION:
				setModelTransformation((ExecutableTransformationElement)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ReconfigurationrulePackage.MODEL_TRANSFORMATION__MODEL_TRANSFORMATION:
				return modelTransformation != null;
		}
		return super.eIsSet(featureID);
	}

} //ModelTransformationImpl

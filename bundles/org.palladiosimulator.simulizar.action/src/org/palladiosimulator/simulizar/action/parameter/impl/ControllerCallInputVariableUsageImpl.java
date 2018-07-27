/**
 */
package org.palladiosimulator.simulizar.action.parameter.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;

import org.palladiosimulator.pcm.parameter.VariableUsage;

import org.palladiosimulator.simulizar.action.core.ControllerCall;

import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection;
import org.palladiosimulator.simulizar.action.parameter.ParameterPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Controller Call Input Variable Usage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageImpl#getVariableUsage <em>Variable Usage</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageImpl#getCorrespondingControllerCall <em>Corresponding Controller Call</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageImpl#getContainingCollection <em>Containing Collection</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ControllerCallInputVariableUsageImpl extends EntityImpl implements ControllerCallInputVariableUsage {
	/**
	 * The cached value of the '{@link #getVariableUsage() <em>Variable Usage</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariableUsage()
	 * @generated
	 * @ordered
	 */
	protected VariableUsage variableUsage;

	/**
	 * The cached value of the '{@link #getCorrespondingControllerCall() <em>Corresponding Controller Call</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrespondingControllerCall()
	 * @generated
	 * @ordered
	 */
	protected ControllerCall correspondingControllerCall;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControllerCallInputVariableUsageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ParameterPackage.Literals.CONTROLLER_CALL_INPUT_VARIABLE_USAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariableUsage getVariableUsage() {
		return variableUsage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariableUsage(VariableUsage newVariableUsage, NotificationChain msgs) {
		VariableUsage oldVariableUsage = variableUsage;
		variableUsage = newVariableUsage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE, oldVariableUsage,
					newVariableUsage);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVariableUsage(VariableUsage newVariableUsage) {
		if (newVariableUsage != variableUsage) {
			NotificationChain msgs = null;
			if (variableUsage != null)
				msgs = ((InternalEObject) variableUsage).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE,
						null, msgs);
			if (newVariableUsage != null)
				msgs = ((InternalEObject) newVariableUsage).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE - ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE,
						null, msgs);
			msgs = basicSetVariableUsage(newVariableUsage, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE, newVariableUsage,
					newVariableUsage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ControllerCall getCorrespondingControllerCall() {
		if (correspondingControllerCall != null && correspondingControllerCall.eIsProxy()) {
			InternalEObject oldCorrespondingControllerCall = (InternalEObject) correspondingControllerCall;
			correspondingControllerCall = (ControllerCall) eResolveProxy(oldCorrespondingControllerCall);
			if (correspondingControllerCall != oldCorrespondingControllerCall) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL,
							oldCorrespondingControllerCall, correspondingControllerCall));
			}
		}
		return correspondingControllerCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ControllerCall basicGetCorrespondingControllerCall() {
		return correspondingControllerCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCorrespondingControllerCall(ControllerCall newCorrespondingControllerCall) {
		ControllerCall oldCorrespondingControllerCall = correspondingControllerCall;
		correspondingControllerCall = newCorrespondingControllerCall;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL,
					oldCorrespondingControllerCall, correspondingControllerCall));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ControllerCallInputVariableUsageCollection getContainingCollection() {
		if (eContainerFeatureID() != ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION)
			return null;
		return (ControllerCallInputVariableUsageCollection) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainingCollection(
			ControllerCallInputVariableUsageCollection newContainingCollection, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newContainingCollection,
				ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContainingCollection(ControllerCallInputVariableUsageCollection newContainingCollection) {
		if (newContainingCollection != eInternalContainer()
				|| (eContainerFeatureID() != ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION
						&& newContainingCollection != null)) {
			if (EcoreUtil.isAncestor(this, newContainingCollection))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainingCollection != null)
				msgs = ((InternalEObject) newContainingCollection).eInverseAdd(this,
						ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES,
						ControllerCallInputVariableUsageCollection.class, msgs);
			msgs = basicSetContainingCollection(newContainingCollection, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION,
					newContainingCollection, newContainingCollection));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetContainingCollection((ControllerCallInputVariableUsageCollection) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE:
			return basicSetVariableUsage(null, msgs);
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
			return basicSetContainingCollection(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
			return eInternalContainer().eInverseRemove(this,
					ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES,
					ControllerCallInputVariableUsageCollection.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE:
			return getVariableUsage();
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL:
			if (resolve)
				return getCorrespondingControllerCall();
			return basicGetCorrespondingControllerCall();
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
			return getContainingCollection();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE:
			setVariableUsage((VariableUsage) newValue);
			return;
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL:
			setCorrespondingControllerCall((ControllerCall) newValue);
			return;
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
			setContainingCollection((ControllerCallInputVariableUsageCollection) newValue);
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
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE:
			setVariableUsage((VariableUsage) null);
			return;
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL:
			setCorrespondingControllerCall((ControllerCall) null);
			return;
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
			setContainingCollection((ControllerCallInputVariableUsageCollection) null);
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
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE:
			return variableUsage != null;
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL:
			return correspondingControllerCall != null;
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
			return getContainingCollection() != null;
		}
		return super.eIsSet(featureID);
	}

} //ControllerCallInputVariableUsageImpl

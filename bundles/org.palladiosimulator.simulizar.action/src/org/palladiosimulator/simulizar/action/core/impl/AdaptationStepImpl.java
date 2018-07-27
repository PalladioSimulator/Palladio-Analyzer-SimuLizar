/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;

import org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.CorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Adaptation Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl#getAdaptationBehavior <em>Adaptation Behavior</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AdaptationStepImpl extends EntityImpl implements AdaptationStep {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdaptationStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ADAPTATION_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AbstractAdaptationBehavior getAdaptationBehavior() {
		if (eContainerFeatureID() != CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR)
			return null;
		return (AbstractAdaptationBehavior) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAdaptationBehavior(AbstractAdaptationBehavior newAdaptationBehavior,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newAdaptationBehavior,
				CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAdaptationBehavior(AbstractAdaptationBehavior newAdaptationBehavior) {
		if (newAdaptationBehavior != eInternalContainer()
				|| (eContainerFeatureID() != CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR
						&& newAdaptationBehavior != null)) {
			if (EcoreUtil.isAncestor(this, newAdaptationBehavior))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAdaptationBehavior != null)
				msgs = ((InternalEObject) newAdaptationBehavior).eInverseAdd(this,
						CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS, AbstractAdaptationBehavior.class,
						msgs);
			msgs = basicSetAdaptationBehavior(newAdaptationBehavior, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR,
					newAdaptationBehavior, newAdaptationBehavior));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetAdaptationBehavior((AbstractAdaptationBehavior) otherEnd, msgs);
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
		case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
			return basicSetAdaptationBehavior(null, msgs);
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
		case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
			return eInternalContainer().eInverseRemove(this, CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS,
					AbstractAdaptationBehavior.class, msgs);
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
		case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
			return getAdaptationBehavior();
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
		case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
			setAdaptationBehavior((AbstractAdaptationBehavior) newValue);
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
		case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
			setAdaptationBehavior((AbstractAdaptationBehavior) null);
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
		case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
			return getAdaptationBehavior() != null;
		}
		return super.eIsSet(featureID);
	}

} //AdaptationStepImpl

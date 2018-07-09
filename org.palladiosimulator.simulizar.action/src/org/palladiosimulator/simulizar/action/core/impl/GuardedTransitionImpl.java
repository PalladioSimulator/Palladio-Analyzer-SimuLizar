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

import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.GuardedStep;
import org.palladiosimulator.simulizar.action.core.GuardedTransition;
import org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Guarded Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.GuardedTransitionImpl#getConditionURI <em>Condition URI</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.GuardedTransitionImpl#getGuardedStep <em>Guarded Step</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.GuardedTransitionImpl#getNestedAdaptationBehavior <em>Nested Adaptation Behavior</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GuardedTransitionImpl extends EntityImpl implements GuardedTransition {
	/**
	 * The default value of the '{@link #getConditionURI() <em>Condition URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionURI()
	 * @generated
	 * @ordered
	 */
	protected static final String CONDITION_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConditionURI() <em>Condition URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConditionURI()
	 * @generated
	 * @ordered
	 */
	protected String conditionURI = CONDITION_URI_EDEFAULT;

	/**
	 * The cached value of the '{@link #getNestedAdaptationBehavior() <em>Nested Adaptation Behavior</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNestedAdaptationBehavior()
	 * @generated
	 * @ordered
	 */
	protected NestedAdaptationBehavior nestedAdaptationBehavior;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GuardedTransitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.GUARDED_TRANSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConditionURI() {
		return conditionURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConditionURI(String newConditionURI) {
		String oldConditionURI = conditionURI;
		conditionURI = newConditionURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.GUARDED_TRANSITION__CONDITION_URI,
					oldConditionURI, conditionURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GuardedStep getGuardedStep() {
		if (eContainerFeatureID() != CorePackage.GUARDED_TRANSITION__GUARDED_STEP)
			return null;
		return (GuardedStep) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGuardedStep(GuardedStep newGuardedStep, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newGuardedStep, CorePackage.GUARDED_TRANSITION__GUARDED_STEP, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGuardedStep(GuardedStep newGuardedStep) {
		if (newGuardedStep != eInternalContainer()
				|| (eContainerFeatureID() != CorePackage.GUARDED_TRANSITION__GUARDED_STEP && newGuardedStep != null)) {
			if (EcoreUtil.isAncestor(this, newGuardedStep))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGuardedStep != null)
				msgs = ((InternalEObject) newGuardedStep).eInverseAdd(this,
						CorePackage.GUARDED_STEP__GUARDED_TRANSITIONS, GuardedStep.class, msgs);
			msgs = basicSetGuardedStep(newGuardedStep, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.GUARDED_TRANSITION__GUARDED_STEP,
					newGuardedStep, newGuardedStep));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NestedAdaptationBehavior getNestedAdaptationBehavior() {
		return nestedAdaptationBehavior;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNestedAdaptationBehavior(NestedAdaptationBehavior newNestedAdaptationBehavior,
			NotificationChain msgs) {
		NestedAdaptationBehavior oldNestedAdaptationBehavior = nestedAdaptationBehavior;
		nestedAdaptationBehavior = newNestedAdaptationBehavior;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR, oldNestedAdaptationBehavior,
					newNestedAdaptationBehavior);
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
	public void setNestedAdaptationBehavior(NestedAdaptationBehavior newNestedAdaptationBehavior) {
		if (newNestedAdaptationBehavior != nestedAdaptationBehavior) {
			NotificationChain msgs = null;
			if (nestedAdaptationBehavior != null)
				msgs = ((InternalEObject) nestedAdaptationBehavior).eInverseRemove(this,
						CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION, NestedAdaptationBehavior.class,
						msgs);
			if (newNestedAdaptationBehavior != null)
				msgs = ((InternalEObject) newNestedAdaptationBehavior).eInverseAdd(this,
						CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION, NestedAdaptationBehavior.class,
						msgs);
			msgs = basicSetNestedAdaptationBehavior(newNestedAdaptationBehavior, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR, newNestedAdaptationBehavior,
					newNestedAdaptationBehavior));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetGuardedStep((GuardedStep) otherEnd, msgs);
		case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
			if (nestedAdaptationBehavior != null)
				msgs = ((InternalEObject) nestedAdaptationBehavior).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE - CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR, null,
						msgs);
			return basicSetNestedAdaptationBehavior((NestedAdaptationBehavior) otherEnd, msgs);
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
		case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
			return basicSetGuardedStep(null, msgs);
		case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
			return basicSetNestedAdaptationBehavior(null, msgs);
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
		case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
			return eInternalContainer().eInverseRemove(this, CorePackage.GUARDED_STEP__GUARDED_TRANSITIONS,
					GuardedStep.class, msgs);
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
		case CorePackage.GUARDED_TRANSITION__CONDITION_URI:
			return getConditionURI();
		case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
			return getGuardedStep();
		case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
			return getNestedAdaptationBehavior();
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
		case CorePackage.GUARDED_TRANSITION__CONDITION_URI:
			setConditionURI((String) newValue);
			return;
		case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
			setGuardedStep((GuardedStep) newValue);
			return;
		case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
			setNestedAdaptationBehavior((NestedAdaptationBehavior) newValue);
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
		case CorePackage.GUARDED_TRANSITION__CONDITION_URI:
			setConditionURI(CONDITION_URI_EDEFAULT);
			return;
		case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
			setGuardedStep((GuardedStep) null);
			return;
		case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
			setNestedAdaptationBehavior((NestedAdaptationBehavior) null);
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
		case CorePackage.GUARDED_TRANSITION__CONDITION_URI:
			return CONDITION_URI_EDEFAULT == null ? conditionURI != null : !CONDITION_URI_EDEFAULT.equals(conditionURI);
		case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
			return getGuardedStep() != null;
		case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
			return nestedAdaptationBehavior != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (conditionURI: ");
		result.append(conditionURI);
		result.append(')');
		return result.toString();
	}

} //GuardedTransitionImpl

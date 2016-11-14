/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;
import org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage;
import org.palladiosimulator.simulizar.reconfigurationrule.Tactic;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tactic</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.TacticImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.TacticImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.TacticImpl#getAction <em>Action</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TacticImpl extends NamedElementImpl implements Tactic {
	/**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final int PRIORITY_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected int priority = PRIORITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelTransformation<?>> condition;

	/**
	 * The cached value of the '{@link #getAction() <em>Action</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAction()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelTransformation<?>> action;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TacticImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReconfigurationrulePackage.Literals.TACTIC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(int newPriority) {
		int oldPriority = priority;
		priority = newPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReconfigurationrulePackage.TACTIC__PRIORITY, oldPriority, priority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelTransformation<?>> getCondition() {
		if (condition == null) {
			condition = new EObjectContainmentEList<ModelTransformation<?>>(ModelTransformation.class, this, ReconfigurationrulePackage.TACTIC__CONDITION);
		}
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelTransformation<?>> getAction() {
		if (action == null) {
			action = new EObjectContainmentEList<ModelTransformation<?>>(ModelTransformation.class, this, ReconfigurationrulePackage.TACTIC__ACTION);
		}
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReconfigurationrulePackage.TACTIC__CONDITION:
				return ((InternalEList<?>)getCondition()).basicRemove(otherEnd, msgs);
			case ReconfigurationrulePackage.TACTIC__ACTION:
				return ((InternalEList<?>)getAction()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ReconfigurationrulePackage.TACTIC__PRIORITY:
				return getPriority();
			case ReconfigurationrulePackage.TACTIC__CONDITION:
				return getCondition();
			case ReconfigurationrulePackage.TACTIC__ACTION:
				return getAction();
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
			case ReconfigurationrulePackage.TACTIC__PRIORITY:
				setPriority((Integer)newValue);
				return;
			case ReconfigurationrulePackage.TACTIC__CONDITION:
				getCondition().clear();
				getCondition().addAll((Collection<? extends ModelTransformation<?>>)newValue);
				return;
			case ReconfigurationrulePackage.TACTIC__ACTION:
				getAction().clear();
				getAction().addAll((Collection<? extends ModelTransformation<?>>)newValue);
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
			case ReconfigurationrulePackage.TACTIC__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
			case ReconfigurationrulePackage.TACTIC__CONDITION:
				getCondition().clear();
				return;
			case ReconfigurationrulePackage.TACTIC__ACTION:
				getAction().clear();
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
			case ReconfigurationrulePackage.TACTIC__PRIORITY:
				return priority != PRIORITY_EDEFAULT;
			case ReconfigurationrulePackage.TACTIC__CONDITION:
				return condition != null && !condition.isEmpty();
			case ReconfigurationrulePackage.TACTIC__ACTION:
				return action != null && !action.isEmpty();
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
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (priority: ");
		result.append(priority);
		result.append(')');
		return result.toString();
	}

} //TacticImpl

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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;
import org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule;
import org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reconfiguration Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationRuleImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationRuleImpl#getConditionCheck <em>Condition Check</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationRuleImpl#getTransformationAction <em>Transformation Action</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationRuleImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReconfigurationRuleImpl extends MinimalEObjectImpl.Container implements ReconfigurationRule {
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
     * The cached value of the '{@link #getConditionCheck() <em>Condition Check</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConditionCheck()
     * @generated
     * @ordered
     */
    protected EList<ModelTransformation<?>> conditionCheck;

    /**
     * The cached value of the '{@link #getTransformationAction() <em>Transformation Action</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTransformationAction()
     * @generated
     * @ordered
     */
    protected EList<ModelTransformation<?>> transformationAction;

    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ReconfigurationRuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return reconfigurationrulePackage.Literals.RECONFIGURATION_RULE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, reconfigurationrulePackage.RECONFIGURATION_RULE__PRIORITY, oldPriority, priority));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ModelTransformation<?>> getConditionCheck() {
        if (conditionCheck == null) {
            conditionCheck = new EObjectContainmentEList<ModelTransformation<?>>(ModelTransformation.class, this, reconfigurationrulePackage.RECONFIGURATION_RULE__CONDITION_CHECK);
        }
        return conditionCheck;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ModelTransformation<?>> getTransformationAction() {
        if (transformationAction == null) {
            transformationAction = new EObjectContainmentEList<ModelTransformation<?>>(ModelTransformation.class, this, reconfigurationrulePackage.RECONFIGURATION_RULE__TRANSFORMATION_ACTION);
        }
        return transformationAction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, reconfigurationrulePackage.RECONFIGURATION_RULE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case reconfigurationrulePackage.RECONFIGURATION_RULE__CONDITION_CHECK:
                return ((InternalEList<?>)getConditionCheck()).basicRemove(otherEnd, msgs);
            case reconfigurationrulePackage.RECONFIGURATION_RULE__TRANSFORMATION_ACTION:
                return ((InternalEList<?>)getTransformationAction()).basicRemove(otherEnd, msgs);
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
            case reconfigurationrulePackage.RECONFIGURATION_RULE__PRIORITY:
                return getPriority();
            case reconfigurationrulePackage.RECONFIGURATION_RULE__CONDITION_CHECK:
                return getConditionCheck();
            case reconfigurationrulePackage.RECONFIGURATION_RULE__TRANSFORMATION_ACTION:
                return getTransformationAction();
            case reconfigurationrulePackage.RECONFIGURATION_RULE__NAME:
                return getName();
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
            case reconfigurationrulePackage.RECONFIGURATION_RULE__PRIORITY:
                setPriority((Integer)newValue);
                return;
            case reconfigurationrulePackage.RECONFIGURATION_RULE__CONDITION_CHECK:
                getConditionCheck().clear();
                getConditionCheck().addAll((Collection<? extends ModelTransformation<?>>)newValue);
                return;
            case reconfigurationrulePackage.RECONFIGURATION_RULE__TRANSFORMATION_ACTION:
                getTransformationAction().clear();
                getTransformationAction().addAll((Collection<? extends ModelTransformation<?>>)newValue);
                return;
            case reconfigurationrulePackage.RECONFIGURATION_RULE__NAME:
                setName((String)newValue);
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
            case reconfigurationrulePackage.RECONFIGURATION_RULE__PRIORITY:
                setPriority(PRIORITY_EDEFAULT);
                return;
            case reconfigurationrulePackage.RECONFIGURATION_RULE__CONDITION_CHECK:
                getConditionCheck().clear();
                return;
            case reconfigurationrulePackage.RECONFIGURATION_RULE__TRANSFORMATION_ACTION:
                getTransformationAction().clear();
                return;
            case reconfigurationrulePackage.RECONFIGURATION_RULE__NAME:
                setName(NAME_EDEFAULT);
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
            case reconfigurationrulePackage.RECONFIGURATION_RULE__PRIORITY:
                return priority != PRIORITY_EDEFAULT;
            case reconfigurationrulePackage.RECONFIGURATION_RULE__CONDITION_CHECK:
                return conditionCheck != null && !conditionCheck.isEmpty();
            case reconfigurationrulePackage.RECONFIGURATION_RULE__TRANSFORMATION_ACTION:
                return transformationAction != null && !transformationAction.isEmpty();
            case reconfigurationrulePackage.RECONFIGURATION_RULE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
        result.append(", name: ");
        result.append(name);
        result.append(')');
        return result.toString();
    }

} //ReconfigurationRuleImpl

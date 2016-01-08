/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.GuardedAction;
import org.palladiosimulator.simulizar.action.core.GuardedTransition;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Guarded Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.GuardedActionImpl#getGuardedTransitions
 * <em>Guarded Transitions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GuardedActionImpl extends AdaptationActionImpl implements GuardedAction {
    /**
     * The cached value of the '{@link #getGuardedTransitions() <em>Guarded Transitions</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getGuardedTransitions()
     * @generated
     * @ordered
     */
    protected EList<GuardedTransition> guardedTransitions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected GuardedActionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.GUARDED_ACTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<GuardedTransition> getGuardedTransitions() {
        if (this.guardedTransitions == null) {
            this.guardedTransitions = new EObjectContainmentWithInverseEList<GuardedTransition>(GuardedTransition.class,
                    this, CorePackage.GUARDED_ACTION__GUARDED_TRANSITIONS,
                    CorePackage.GUARDED_TRANSITION__GUARDED_ACTION);
        }
        return this.guardedTransitions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case CorePackage.GUARDED_ACTION__GUARDED_TRANSITIONS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getGuardedTransitions()).basicAdd(otherEnd,
                    msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case CorePackage.GUARDED_ACTION__GUARDED_TRANSITIONS:
            return ((InternalEList<?>) this.getGuardedTransitions()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case CorePackage.GUARDED_ACTION__GUARDED_TRANSITIONS:
            return this.getGuardedTransitions();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case CorePackage.GUARDED_ACTION__GUARDED_TRANSITIONS:
            this.getGuardedTransitions().clear();
            this.getGuardedTransitions().addAll((Collection<? extends GuardedTransition>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case CorePackage.GUARDED_ACTION__GUARDED_TRANSITIONS:
            this.getGuardedTransitions().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case CorePackage.GUARDED_ACTION__GUARDED_TRANSITIONS:
            return this.guardedTransitions != null && !this.guardedTransitions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // GuardedActionImpl

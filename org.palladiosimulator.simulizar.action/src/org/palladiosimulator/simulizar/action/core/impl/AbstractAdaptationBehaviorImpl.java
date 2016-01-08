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
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationAction;
import org.palladiosimulator.simulizar.action.core.CorePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Abstract Adaptation Behavior</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.AbstractAdaptationBehaviorImpl#getAdaptationActions
 * <em>Adaptation Actions</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractAdaptationBehaviorImpl extends EntityImpl implements AbstractAdaptationBehavior {
    /**
     * The cached value of the '{@link #getAdaptationActions() <em>Adaptation Actions</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAdaptationActions()
     * @generated
     * @ordered
     */
    protected EList<AdaptationAction> adaptationActions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected AbstractAdaptationBehaviorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.ABSTRACT_ADAPTATION_BEHAVIOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<AdaptationAction> getAdaptationActions() {
        if (this.adaptationActions == null) {
            this.adaptationActions = new EObjectContainmentWithInverseEList<AdaptationAction>(AdaptationAction.class,
                    this, CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS,
                    CorePackage.ADAPTATION_ACTION__ADAPTATION_BEHAVIOR);
        }
        return this.adaptationActions;
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
        case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getAdaptationActions()).basicAdd(otherEnd,
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
        case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS:
            return ((InternalEList<?>) this.getAdaptationActions()).basicRemove(otherEnd, msgs);
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
        case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS:
            return this.getAdaptationActions();
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
        case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS:
            this.getAdaptationActions().clear();
            this.getAdaptationActions().addAll((Collection<? extends AdaptationAction>) newValue);
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
        case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS:
            this.getAdaptationActions().clear();
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
        case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS:
            return this.adaptationActions != null && !this.adaptationActions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // AbstractAdaptationBehaviorImpl

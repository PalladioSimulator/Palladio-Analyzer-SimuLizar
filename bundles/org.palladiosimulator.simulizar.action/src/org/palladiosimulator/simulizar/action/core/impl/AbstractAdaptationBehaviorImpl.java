/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.CorePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Abstract Adaptation
 * Behavior</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.AbstractAdaptationBehaviorImpl#getAdaptationSteps
 * <em>Adaptation Steps</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractAdaptationBehaviorImpl extends EntityImpl implements AbstractAdaptationBehavior {
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
    @SuppressWarnings("unchecked")
    @Override
    public EList<AdaptationStep> getAdaptationSteps() {
        return (EList<AdaptationStep>) this.eDynamicGet(CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS,
                CorePackage.Literals.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS, true, true);
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
        case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getAdaptationSteps()).basicAdd(otherEnd,
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
        case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS:
            return ((InternalEList<?>) this.getAdaptationSteps()).basicRemove(otherEnd, msgs);
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
        case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS:
            return this.getAdaptationSteps();
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
        case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS:
            this.getAdaptationSteps()
                .clear();
            this.getAdaptationSteps()
                .addAll((Collection<? extends AdaptationStep>) newValue);
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
        case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS:
            this.getAdaptationSteps()
                .clear();
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
        case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS:
            return !this.getAdaptationSteps()
                .isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // AbstractAdaptationBehaviorImpl

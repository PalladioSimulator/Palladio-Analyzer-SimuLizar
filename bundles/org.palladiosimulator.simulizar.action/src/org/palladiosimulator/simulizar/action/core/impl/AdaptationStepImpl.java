/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.CorePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Adaptation Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl#getAdaptationBehavior
 * <em>Adaptation Behavior</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AdaptationStepImpl extends EntityImpl implements AdaptationStep {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AdaptationStepImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.ADAPTATION_STEP;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AbstractAdaptationBehavior getAdaptationBehavior() {
        return (AbstractAdaptationBehavior) this.eDynamicGet(CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR,
                CorePackage.Literals.ADAPTATION_STEP__ADAPTATION_BEHAVIOR, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetAdaptationBehavior(final AbstractAdaptationBehavior newAdaptationBehavior,
            NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newAdaptationBehavior,
                CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAdaptationBehavior(final AbstractAdaptationBehavior newAdaptationBehavior) {
        this.eDynamicSet(CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR,
                CorePackage.Literals.ADAPTATION_STEP__ADAPTATION_BEHAVIOR, newAdaptationBehavior);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetAdaptationBehavior((AbstractAdaptationBehavior) otherEnd, msgs);
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
        case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
            return this.basicSetAdaptationBehavior(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(final NotificationChain msgs) {
        switch (this.eContainerFeatureID()) {
        case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
            return this.eInternalContainer()
                .eInverseRemove(this, CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS,
                        AbstractAdaptationBehavior.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
            return this.getAdaptationBehavior();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
            this.setAdaptationBehavior((AbstractAdaptationBehavior) newValue);
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
        case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
            this.setAdaptationBehavior((AbstractAdaptationBehavior) null);
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
        case CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR:
            return this.getAdaptationBehavior() != null;
        }
        return super.eIsSet(featureID);
    }

} // AdaptationStepImpl

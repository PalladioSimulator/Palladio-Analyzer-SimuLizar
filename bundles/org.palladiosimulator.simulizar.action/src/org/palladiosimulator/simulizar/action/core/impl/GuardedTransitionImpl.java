/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.GuardedStep;
import org.palladiosimulator.simulizar.action.core.GuardedTransition;
import org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Guarded
 * Transition</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.GuardedTransitionImpl#getConditionURI
 * <em>Condition URI</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.GuardedTransitionImpl#getGuardedStep
 * <em>Guarded Step</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.GuardedTransitionImpl#getNestedAdaptationBehavior
 * <em>Nested Adaptation Behavior</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GuardedTransitionImpl extends EntityImpl implements GuardedTransition {
    /**
     * The default value of the '{@link #getConditionURI() <em>Condition URI</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionURI()
     * @generated
     * @ordered
     */
    protected static final String CONDITION_URI_EDEFAULT = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GuardedTransitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.GUARDED_TRANSITION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getConditionURI() {
        return (String) this.eDynamicGet(CorePackage.GUARDED_TRANSITION__CONDITION_URI,
                CorePackage.Literals.GUARDED_TRANSITION__CONDITION_URI, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setConditionURI(final String newConditionURI) {
        this.eDynamicSet(CorePackage.GUARDED_TRANSITION__CONDITION_URI,
                CorePackage.Literals.GUARDED_TRANSITION__CONDITION_URI, newConditionURI);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GuardedStep getGuardedStep() {
        return (GuardedStep) this.eDynamicGet(CorePackage.GUARDED_TRANSITION__GUARDED_STEP,
                CorePackage.Literals.GUARDED_TRANSITION__GUARDED_STEP, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetGuardedStep(final GuardedStep newGuardedStep, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newGuardedStep, CorePackage.GUARDED_TRANSITION__GUARDED_STEP,
                msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setGuardedStep(final GuardedStep newGuardedStep) {
        this.eDynamicSet(CorePackage.GUARDED_TRANSITION__GUARDED_STEP,
                CorePackage.Literals.GUARDED_TRANSITION__GUARDED_STEP, newGuardedStep);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NestedAdaptationBehavior getNestedAdaptationBehavior() {
        return (NestedAdaptationBehavior) this.eDynamicGet(CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR,
                CorePackage.Literals.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetNestedAdaptationBehavior(
            final NestedAdaptationBehavior newNestedAdaptationBehavior, NotificationChain msgs) {
        msgs = this.eDynamicInverseAdd((InternalEObject) newNestedAdaptationBehavior,
                CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setNestedAdaptationBehavior(final NestedAdaptationBehavior newNestedAdaptationBehavior) {
        this.eDynamicSet(CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR,
                CorePackage.Literals.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR, newNestedAdaptationBehavior);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetGuardedStep((GuardedStep) otherEnd, msgs);
        case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
            final NestedAdaptationBehavior nestedAdaptationBehavior = this.getNestedAdaptationBehavior();
            if (nestedAdaptationBehavior != null) {
                msgs = ((InternalEObject) nestedAdaptationBehavior).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR, null,
                        msgs);
            }
            return this.basicSetNestedAdaptationBehavior((NestedAdaptationBehavior) otherEnd, msgs);
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
        case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
            return this.basicSetGuardedStep(null, msgs);
        case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
            return this.basicSetNestedAdaptationBehavior(null, msgs);
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
        case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
            return this.eInternalContainer()
                .eInverseRemove(this, CorePackage.GUARDED_STEP__GUARDED_TRANSITIONS, GuardedStep.class, msgs);
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
        case CorePackage.GUARDED_TRANSITION__CONDITION_URI:
            return this.getConditionURI();
        case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
            return this.getGuardedStep();
        case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
            return this.getNestedAdaptationBehavior();
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
        case CorePackage.GUARDED_TRANSITION__CONDITION_URI:
            this.setConditionURI((String) newValue);
            return;
        case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
            this.setGuardedStep((GuardedStep) newValue);
            return;
        case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
            this.setNestedAdaptationBehavior((NestedAdaptationBehavior) newValue);
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
        case CorePackage.GUARDED_TRANSITION__CONDITION_URI:
            this.setConditionURI(CONDITION_URI_EDEFAULT);
            return;
        case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
            this.setGuardedStep((GuardedStep) null);
            return;
        case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
            this.setNestedAdaptationBehavior((NestedAdaptationBehavior) null);
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
        case CorePackage.GUARDED_TRANSITION__CONDITION_URI:
            return CONDITION_URI_EDEFAULT == null ? this.getConditionURI() != null
                    : !CONDITION_URI_EDEFAULT.equals(this.getConditionURI());
        case CorePackage.GUARDED_TRANSITION__GUARDED_STEP:
            return this.getGuardedStep() != null;
        case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
            return this.getNestedAdaptationBehavior() != null;
        }
        return super.eIsSet(featureID);
    }

} // GuardedTransitionImpl

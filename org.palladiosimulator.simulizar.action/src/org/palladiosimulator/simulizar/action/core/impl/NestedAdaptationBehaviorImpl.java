/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.GuardedTransition;
import org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Nested Adaptation Behavior</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.NestedAdaptationBehaviorImpl#getGuardedTransition
 * <em>Guarded Transition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NestedAdaptationBehaviorImpl extends AbstractAdaptationBehaviorImpl implements NestedAdaptationBehavior {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected NestedAdaptationBehaviorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.NESTED_ADAPTATION_BEHAVIOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GuardedTransition getGuardedTransition() {
        if (this.eContainerFeatureID() != CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION) {
            return null;
        }
        return (GuardedTransition) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetGuardedTransition(final GuardedTransition newGuardedTransition,
            NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newGuardedTransition,
                CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setGuardedTransition(final GuardedTransition newGuardedTransition) {
        if (newGuardedTransition != this.eInternalContainer()
                || (this.eContainerFeatureID() != CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION
                        && newGuardedTransition != null)) {
            if (EcoreUtil.isAncestor(this, newGuardedTransition)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newGuardedTransition != null) {
                msgs = ((InternalEObject) newGuardedTransition).eInverseAdd(this,
                        CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR, GuardedTransition.class, msgs);
            }
            msgs = this.basicSetGuardedTransition(newGuardedTransition, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION, newGuardedTransition,
                    newGuardedTransition));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetGuardedTransition((GuardedTransition) otherEnd, msgs);
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
        case CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION:
            return this.basicSetGuardedTransition(null, msgs);
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
        case CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION:
            return this.eInternalContainer().eInverseRemove(this,
                    CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR, GuardedTransition.class, msgs);
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
        case CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION:
            return this.getGuardedTransition();
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
        case CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION:
            this.setGuardedTransition((GuardedTransition) newValue);
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
        case CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION:
            this.setGuardedTransition((GuardedTransition) null);
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
        case CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION:
            return this.getGuardedTransition() != null;
        }
        return super.eIsSet(featureID);
    }

} // NestedAdaptationBehaviorImpl

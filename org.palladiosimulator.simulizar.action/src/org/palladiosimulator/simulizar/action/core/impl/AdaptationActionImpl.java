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
import org.palladiosimulator.simulizar.action.core.AdaptationAction;
import org.palladiosimulator.simulizar.action.core.CorePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Adaptation Action</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationActionImpl#getAdaptationBehavior
 * <em>Adaptation Behavior</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AdaptationActionImpl extends EntityImpl implements AdaptationAction {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected AdaptationActionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.ADAPTATION_ACTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public AbstractAdaptationBehavior getAdaptationBehavior() {
        if (this.eContainerFeatureID() != CorePackage.ADAPTATION_ACTION__ADAPTATION_BEHAVIOR) {
            return null;
        }
        return (AbstractAdaptationBehavior) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetAdaptationBehavior(final AbstractAdaptationBehavior newAdaptationBehavior,
            NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newAdaptationBehavior,
                CorePackage.ADAPTATION_ACTION__ADAPTATION_BEHAVIOR, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setAdaptationBehavior(final AbstractAdaptationBehavior newAdaptationBehavior) {
        if (newAdaptationBehavior != this.eInternalContainer()
                || (this.eContainerFeatureID() != CorePackage.ADAPTATION_ACTION__ADAPTATION_BEHAVIOR
                        && newAdaptationBehavior != null)) {
            if (EcoreUtil.isAncestor(this, newAdaptationBehavior)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newAdaptationBehavior != null) {
                msgs = ((InternalEObject) newAdaptationBehavior).eInverseAdd(this,
                        CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS, AbstractAdaptationBehavior.class,
                        msgs);
            }
            msgs = this.basicSetAdaptationBehavior(newAdaptationBehavior, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    CorePackage.ADAPTATION_ACTION__ADAPTATION_BEHAVIOR, newAdaptationBehavior, newAdaptationBehavior));
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
        case CorePackage.ADAPTATION_ACTION__ADAPTATION_BEHAVIOR:
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
        case CorePackage.ADAPTATION_ACTION__ADAPTATION_BEHAVIOR:
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
        case CorePackage.ADAPTATION_ACTION__ADAPTATION_BEHAVIOR:
            return this.eInternalContainer().eInverseRemove(this,
                    CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS, AbstractAdaptationBehavior.class,
                    msgs);
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
        case CorePackage.ADAPTATION_ACTION__ADAPTATION_BEHAVIOR:
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
        case CorePackage.ADAPTATION_ACTION__ADAPTATION_BEHAVIOR:
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
        case CorePackage.ADAPTATION_ACTION__ADAPTATION_BEHAVIOR:
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
        case CorePackage.ADAPTATION_ACTION__ADAPTATION_BEHAVIOR:
            return this.getAdaptationBehavior() != null;
        }
        return super.eIsSet(featureID);
    }

} // AdaptationActionImpl

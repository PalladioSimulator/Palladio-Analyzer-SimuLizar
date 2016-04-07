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
import org.palladiosimulator.simulizar.action.core.GuardedAction;
import org.palladiosimulator.simulizar.action.core.GuardedTransition;
import org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Guarded Transition</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.GuardedTransitionImpl#getConditionURI
 * <em>Condition URI</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.GuardedTransitionImpl#getGuardedAction
 * <em>Guarded Action</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.GuardedTransitionImpl#getNestedAdaptationBehavior
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
     * The cached value of the '{@link #getConditionURI() <em>Condition URI</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getConditionURI()
     * @generated
     * @ordered
     */
    protected String conditionURI = CONDITION_URI_EDEFAULT;

    /**
     * The cached value of the '{@link #getNestedAdaptationBehavior()
     * <em>Nested Adaptation Behavior</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getNestedAdaptationBehavior()
     * @generated
     * @ordered
     */
    protected NestedAdaptationBehavior nestedAdaptationBehavior;

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
        return this.conditionURI;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setConditionURI(final String newConditionURI) {
        final String oldConditionURI = this.conditionURI;
        this.conditionURI = newConditionURI;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.GUARDED_TRANSITION__CONDITION_URI,
                    oldConditionURI, this.conditionURI));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GuardedAction getGuardedAction() {
        if (this.eContainerFeatureID() != CorePackage.GUARDED_TRANSITION__GUARDED_ACTION) {
            return null;
        }
        return (GuardedAction) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetGuardedAction(final GuardedAction newGuardedAction, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newGuardedAction,
                CorePackage.GUARDED_TRANSITION__GUARDED_ACTION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setGuardedAction(final GuardedAction newGuardedAction) {
        if (newGuardedAction != this.eInternalContainer()
                || (this.eContainerFeatureID() != CorePackage.GUARDED_TRANSITION__GUARDED_ACTION
                        && newGuardedAction != null)) {
            if (EcoreUtil.isAncestor(this, newGuardedAction)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newGuardedAction != null) {
                msgs = ((InternalEObject) newGuardedAction).eInverseAdd(this,
                        CorePackage.GUARDED_ACTION__GUARDED_TRANSITIONS, GuardedAction.class, msgs);
            }
            msgs = this.basicSetGuardedAction(newGuardedAction, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.GUARDED_TRANSITION__GUARDED_ACTION,
                    newGuardedAction, newGuardedAction));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NestedAdaptationBehavior getNestedAdaptationBehavior() {
        return this.nestedAdaptationBehavior;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetNestedAdaptationBehavior(
            final NestedAdaptationBehavior newNestedAdaptationBehavior, NotificationChain msgs) {
        final NestedAdaptationBehavior oldNestedAdaptationBehavior = this.nestedAdaptationBehavior;
        this.nestedAdaptationBehavior = newNestedAdaptationBehavior;
        if (this.eNotificationRequired()) {
            final ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR, oldNestedAdaptationBehavior,
                    newNestedAdaptationBehavior);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setNestedAdaptationBehavior(final NestedAdaptationBehavior newNestedAdaptationBehavior) {
        if (newNestedAdaptationBehavior != this.nestedAdaptationBehavior) {
            NotificationChain msgs = null;
            if (this.nestedAdaptationBehavior != null) {
                msgs = ((InternalEObject) this.nestedAdaptationBehavior).eInverseRemove(this,
                        CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION, NestedAdaptationBehavior.class,
                        msgs);
            }
            if (newNestedAdaptationBehavior != null) {
                msgs = ((InternalEObject) newNestedAdaptationBehavior).eInverseAdd(this,
                        CorePackage.NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION, NestedAdaptationBehavior.class,
                        msgs);
            }
            msgs = this.basicSetNestedAdaptationBehavior(newNestedAdaptationBehavior, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR, newNestedAdaptationBehavior,
                    newNestedAdaptationBehavior));
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
        case CorePackage.GUARDED_TRANSITION__GUARDED_ACTION:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetGuardedAction((GuardedAction) otherEnd, msgs);
        case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
            if (this.nestedAdaptationBehavior != null) {
                msgs = ((InternalEObject) this.nestedAdaptationBehavior).eInverseRemove(this,
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
        case CorePackage.GUARDED_TRANSITION__GUARDED_ACTION:
            return this.basicSetGuardedAction(null, msgs);
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
        case CorePackage.GUARDED_TRANSITION__GUARDED_ACTION:
            return this.eInternalContainer().eInverseRemove(this, CorePackage.GUARDED_ACTION__GUARDED_TRANSITIONS,
                    GuardedAction.class, msgs);
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
        case CorePackage.GUARDED_TRANSITION__GUARDED_ACTION:
            return this.getGuardedAction();
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
        case CorePackage.GUARDED_TRANSITION__GUARDED_ACTION:
            this.setGuardedAction((GuardedAction) newValue);
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
        case CorePackage.GUARDED_TRANSITION__GUARDED_ACTION:
            this.setGuardedAction((GuardedAction) null);
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
            return CONDITION_URI_EDEFAULT == null ? this.conditionURI != null
                    : !CONDITION_URI_EDEFAULT.equals(this.conditionURI);
        case CorePackage.GUARDED_TRANSITION__GUARDED_ACTION:
            return this.getGuardedAction() != null;
        case CorePackage.GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR:
            return this.nestedAdaptationBehavior != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }

        final StringBuffer result = new StringBuffer(super.toString());
        result.append(" (conditionURI: ");
        result.append(this.conditionURI);
        result.append(')');
        return result.toString();
    }

} // GuardedTransitionImpl

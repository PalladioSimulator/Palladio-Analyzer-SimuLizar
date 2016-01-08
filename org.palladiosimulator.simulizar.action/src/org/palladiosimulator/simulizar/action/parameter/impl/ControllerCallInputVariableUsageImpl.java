/**
 */
package org.palladiosimulator.simulizar.action.parameter.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection;
import org.palladiosimulator.simulizar.action.parameter.ParameterPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Controller Call Input Variable Usage</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageImpl#getVariableUsage
 * <em>Variable Usage</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageImpl#getCorrespondingControllerCall
 * <em>Corresponding Controller Call</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageImpl#getContainingCollection
 * <em>Containing Collection</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ControllerCallInputVariableUsageImpl extends EntityImpl implements ControllerCallInputVariableUsage {
    /**
     * The cached value of the '{@link #getVariableUsage() <em>Variable Usage</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getVariableUsage()
     * @generated
     * @ordered
     */
    protected VariableUsage variableUsage;

    /**
     * The cached value of the '{@link #getCorrespondingControllerCall()
     * <em>Corresponding Controller Call</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getCorrespondingControllerCall()
     * @generated
     * @ordered
     */
    protected ControllerCall correspondingControllerCall;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ControllerCallInputVariableUsageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ParameterPackage.Literals.CONTROLLER_CALL_INPUT_VARIABLE_USAGE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public VariableUsage getVariableUsage() {
        return this.variableUsage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetVariableUsage(final VariableUsage newVariableUsage, NotificationChain msgs) {
        final VariableUsage oldVariableUsage = this.variableUsage;
        this.variableUsage = newVariableUsage;
        if (this.eNotificationRequired()) {
            final ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE, oldVariableUsage,
                    newVariableUsage);
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
    public void setVariableUsage(final VariableUsage newVariableUsage) {
        if (newVariableUsage != this.variableUsage) {
            NotificationChain msgs = null;
            if (this.variableUsage != null) {
                msgs = ((InternalEObject) this.variableUsage).eInverseRemove(this,
                        EOPPOSITE_FEATURE_BASE - ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE,
                        null, msgs);
            }
            if (newVariableUsage != null) {
                msgs = ((InternalEObject) newVariableUsage).eInverseAdd(this,
                        EOPPOSITE_FEATURE_BASE - ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE,
                        null, msgs);
            }
            msgs = this.basicSetVariableUsage(newVariableUsage, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE, newVariableUsage,
                    newVariableUsage));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ControllerCall getCorrespondingControllerCall() {
        if (this.correspondingControllerCall != null && this.correspondingControllerCall.eIsProxy()) {
            final InternalEObject oldCorrespondingControllerCall = (InternalEObject) this.correspondingControllerCall;
            this.correspondingControllerCall = (ControllerCall) this.eResolveProxy(oldCorrespondingControllerCall);
            if (this.correspondingControllerCall != oldCorrespondingControllerCall) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL,
                            oldCorrespondingControllerCall, this.correspondingControllerCall));
                }
            }
        }
        return this.correspondingControllerCall;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ControllerCall basicGetCorrespondingControllerCall() {
        return this.correspondingControllerCall;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setCorrespondingControllerCall(final ControllerCall newCorrespondingControllerCall) {
        final ControllerCall oldCorrespondingControllerCall = this.correspondingControllerCall;
        this.correspondingControllerCall = newCorrespondingControllerCall;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL,
                    oldCorrespondingControllerCall, this.correspondingControllerCall));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ControllerCallInputVariableUsageCollection getContainingCollection() {
        if (this.eContainerFeatureID() != ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION) {
            return null;
        }
        return (ControllerCallInputVariableUsageCollection) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetContainingCollection(
            final ControllerCallInputVariableUsageCollection newContainingCollection, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newContainingCollection,
                ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setContainingCollection(final ControllerCallInputVariableUsageCollection newContainingCollection) {
        if (newContainingCollection != this.eInternalContainer()
                || (this.eContainerFeatureID() != ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION
                        && newContainingCollection != null)) {
            if (EcoreUtil.isAncestor(this, newContainingCollection)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newContainingCollection != null) {
                msgs = ((InternalEObject) newContainingCollection).eInverseAdd(this,
                        ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES,
                        ControllerCallInputVariableUsageCollection.class, msgs);
            }
            msgs = this.basicSetContainingCollection(newContainingCollection, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION,
                    newContainingCollection, newContainingCollection));
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
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetContainingCollection((ControllerCallInputVariableUsageCollection) otherEnd, msgs);
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
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE:
            return this.basicSetVariableUsage(null, msgs);
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
            return this.basicSetContainingCollection(null, msgs);
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
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
            return this.eInternalContainer().eInverseRemove(this,
                    ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES,
                    ControllerCallInputVariableUsageCollection.class, msgs);
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
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE:
            return this.getVariableUsage();
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL:
            if (resolve) {
                return this.getCorrespondingControllerCall();
            }
            return this.basicGetCorrespondingControllerCall();
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
            return this.getContainingCollection();
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
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE:
            this.setVariableUsage((VariableUsage) newValue);
            return;
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL:
            this.setCorrespondingControllerCall((ControllerCall) newValue);
            return;
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
            this.setContainingCollection((ControllerCallInputVariableUsageCollection) newValue);
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
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE:
            this.setVariableUsage((VariableUsage) null);
            return;
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL:
            this.setCorrespondingControllerCall((ControllerCall) null);
            return;
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
            this.setContainingCollection((ControllerCallInputVariableUsageCollection) null);
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
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE:
            return this.variableUsage != null;
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL:
            return this.correspondingControllerCall != null;
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
            return this.getContainingCollection() != null;
        }
        return super.eIsSet(featureID);
    }

} // ControllerCallInputVariableUsageImpl

/**
 */
package org.palladiosimulator.simulizar.action.parameter.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.pcm.parameter.VariableUsage;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection;
import org.palladiosimulator.simulizar.action.parameter.ParameterPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Controller Call Input
 * Variable Usage</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageImpl#getVariableUsage
 * <em>Variable Usage</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageImpl#getCorrespondingControllerCall
 * <em>Corresponding Controller Call</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageImpl#getContainingCollection
 * <em>Containing Collection</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ControllerCallInputVariableUsageImpl extends EntityImpl implements ControllerCallInputVariableUsage {
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
        return (VariableUsage) this.eDynamicGet(ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE,
                ParameterPackage.Literals.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetVariableUsage(final VariableUsage newVariableUsage, NotificationChain msgs) {
        msgs = this.eDynamicInverseAdd((InternalEObject) newVariableUsage,
                ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setVariableUsage(final VariableUsage newVariableUsage) {
        this.eDynamicSet(ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE,
                ParameterPackage.Literals.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE, newVariableUsage);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ControllerCall getCorrespondingControllerCall() {
        return (ControllerCall) this.eDynamicGet(
                ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL,
                ParameterPackage.Literals.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL, true,
                true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ControllerCall basicGetCorrespondingControllerCall() {
        return (ControllerCall) this.eDynamicGet(
                ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL,
                ParameterPackage.Literals.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL, false,
                true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCorrespondingControllerCall(final ControllerCall newCorrespondingControllerCall) {
        this.eDynamicSet(ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL,
                ParameterPackage.Literals.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL,
                newCorrespondingControllerCall);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ControllerCallInputVariableUsageCollection getContainingCollection() {
        return (ControllerCallInputVariableUsageCollection) this.eDynamicGet(
                ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION,
                ParameterPackage.Literals.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION, true, true);
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
        this.eDynamicSet(ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION,
                ParameterPackage.Literals.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION,
                newContainingCollection);
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
            return this.eInternalContainer()
                .eInverseRemove(this,
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
            return this.getVariableUsage() != null;
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL:
            return this.basicGetCorrespondingControllerCall() != null;
        case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION:
            return this.getContainingCollection() != null;
        }
        return super.eIsSet(featureID);
    }

} // ControllerCallInputVariableUsageImpl

/**
 */
package org.palladiosimulator.runtimemeasurement.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Model</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementModelImpl#getMeasurements
 * <em>Measurements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RuntimeMeasurementModelImpl extends IdentifierImpl implements RuntimeMeasurementModel {
    /**
     * The cached value of the '{@link #getMeasurements() <em>Measurements</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMeasurements()
     * @generated
     * @ordered
     */
    protected EList<RuntimeMeasurement> measurements;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected RuntimeMeasurementModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RuntimeMeasurementPackage.Literals.RUNTIME_MEASUREMENT_MODEL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<RuntimeMeasurement> getMeasurements() {
        if (this.measurements == null) {
            this.measurements = new EObjectContainmentEList<RuntimeMeasurement>(RuntimeMeasurement.class, this,
                    RuntimeMeasurementPackage.RUNTIME_MEASUREMENT_MODEL__MEASUREMENTS);
        }
        return this.measurements;
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
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT_MODEL__MEASUREMENTS:
            return ((InternalEList<?>) this.getMeasurements()).basicRemove(otherEnd, msgs);
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
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT_MODEL__MEASUREMENTS:
            return this.getMeasurements();
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
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT_MODEL__MEASUREMENTS:
            this.getMeasurements().clear();
            this.getMeasurements().addAll((Collection<? extends RuntimeMeasurement>) newValue);
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
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT_MODEL__MEASUREMENTS:
            this.getMeasurements().clear();
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
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT_MODEL__MEASUREMENTS:
            return this.measurements != null && !this.measurements.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // RuntimeMeasurementModelImpl

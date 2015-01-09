/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.pms.PerformanceMeasurement;
import org.palladiosimulator.simulizar.pms.PmsPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>PMS Model</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.pms.impl.PMSModelImpl#getPerformanceMeasurements <em>Performance Measurements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PMSModelImpl extends UniqueElementImpl implements PMSModel {
    /**
     * The cached value of the '{@link #getPerformanceMeasurements() <em>Performance Measurements</em>}' containment reference list.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #getPerformanceMeasurements()
     * @generated
     * @ordered
     */
    protected EList<PerformanceMeasurement> performanceMeasurements;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected PMSModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PmsPackage.Literals.PMS_MODEL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EList<PerformanceMeasurement> getPerformanceMeasurements() {
        if (performanceMeasurements == null) {
            performanceMeasurements = new EObjectContainmentEList<PerformanceMeasurement>(PerformanceMeasurement.class, this, PmsPackage.PMS_MODEL__PERFORMANCE_MEASUREMENTS);
        }
        return performanceMeasurements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PmsPackage.PMS_MODEL__PERFORMANCE_MEASUREMENTS:
                return ((InternalEList<?>)getPerformanceMeasurements()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PmsPackage.PMS_MODEL__PERFORMANCE_MEASUREMENTS:
                return getPerformanceMeasurements();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case PmsPackage.PMS_MODEL__PERFORMANCE_MEASUREMENTS:
                getPerformanceMeasurements().clear();
                getPerformanceMeasurements().addAll((Collection<? extends PerformanceMeasurement>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case PmsPackage.PMS_MODEL__PERFORMANCE_MEASUREMENTS:
                getPerformanceMeasurements().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case PmsPackage.PMS_MODEL__PERFORMANCE_MEASUREMENTS:
                return performanceMeasurements != null && !performanceMeasurements.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // PMSModelImpl

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.prm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>PRM Model</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.prm.impl.PRMModelImpl#getPcmModelElementMeasurements
 * <em>Pcm Model Element Measurements</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PRMModelImpl extends UniqueElementImpl implements PRMModel {
    /**
     * The cached value of the '{@link #getPcmModelElementMeasurements()
     * <em>Pcm Model Element Measurements</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getPcmModelElementMeasurements()
     * @generated
     * @ordered
     */
    protected EList<PCMModelElementMeasurement> pcmModelElementMeasurements;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected PRMModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PrmPackage.Literals.PRM_MODEL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<PCMModelElementMeasurement> getPcmModelElementMeasurements() {
        if (pcmModelElementMeasurements == null) {
            pcmModelElementMeasurements = new EObjectContainmentEList<PCMModelElementMeasurement>(
                    PCMModelElementMeasurement.class, this, PrmPackage.PRM_MODEL__PCM_MODEL_ELEMENT_MEASUREMENTS);
        }
        return pcmModelElementMeasurements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PrmPackage.PRM_MODEL__PCM_MODEL_ELEMENT_MEASUREMENTS:
            return ((InternalEList<?>) getPcmModelElementMeasurements()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PrmPackage.PRM_MODEL__PCM_MODEL_ELEMENT_MEASUREMENTS:
            return getPcmModelElementMeasurements();
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
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case PrmPackage.PRM_MODEL__PCM_MODEL_ELEMENT_MEASUREMENTS:
            getPcmModelElementMeasurements().clear();
            getPcmModelElementMeasurements().addAll((Collection<? extends PCMModelElementMeasurement>) newValue);
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
    public void eUnset(int featureID) {
        switch (featureID) {
        case PrmPackage.PRM_MODEL__PCM_MODEL_ELEMENT_MEASUREMENTS:
            getPcmModelElementMeasurements().clear();
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
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case PrmPackage.PRM_MODEL__PCM_MODEL_ELEMENT_MEASUREMENTS:
            return pcmModelElementMeasurements != null && !pcmModelElementMeasurements.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // PRMModelImpl

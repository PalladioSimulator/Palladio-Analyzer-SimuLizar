/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.prm.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.palladiosimulator.simulizar.prm.PrmPackage;
import org.palladiosimulator.simulizar.prm.ResourceContainerMeasurement;

import de.uka.ipd.sdq.pcm.resourcetype.ProcessingResourceType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Resource Container Measurement</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.prm.impl.ResourceContainerMeasurementImpl#getProcessingResourceType
 * <em>Processing Resource Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceContainerMeasurementImpl extends PCMModelElementMeasurementImpl implements
        ResourceContainerMeasurement {
    /**
     * The cached value of the '{@link #getProcessingResourceType()
     * <em>Processing Resource Type</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getProcessingResourceType()
     * @generated
     * @ordered
     */
    protected ProcessingResourceType processingResourceType;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ResourceContainerMeasurementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PrmPackage.Literals.RESOURCE_CONTAINER_MEASUREMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ProcessingResourceType getProcessingResourceType() {
        if (this.processingResourceType != null && ((EObject) this.processingResourceType).eIsProxy()) {
            final InternalEObject oldProcessingResourceType = (InternalEObject) this.processingResourceType;
            this.processingResourceType = (ProcessingResourceType) this.eResolveProxy(oldProcessingResourceType);
            if (this.processingResourceType != oldProcessingResourceType) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            PrmPackage.RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE,
                            oldProcessingResourceType, this.processingResourceType));
                }
            }
        }
        return this.processingResourceType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ProcessingResourceType basicGetProcessingResourceType() {
        return this.processingResourceType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setProcessingResourceType(final ProcessingResourceType newProcessingResourceType) {
        final ProcessingResourceType oldProcessingResourceType = this.processingResourceType;
        this.processingResourceType = newProcessingResourceType;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    PrmPackage.RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE, oldProcessingResourceType,
                    this.processingResourceType));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case PrmPackage.RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE:
            if (resolve) {
                return this.getProcessingResourceType();
            }
            return this.basicGetProcessingResourceType();
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
        case PrmPackage.RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE:
            this.setProcessingResourceType((ProcessingResourceType) newValue);
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
        case PrmPackage.RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE:
            this.setProcessingResourceType((ProcessingResourceType) null);
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
        case PrmPackage.RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE:
            return this.processingResourceType != null;
        }
        return super.eIsSet(featureID);
    }

} // ResourceContainerMeasurementImpl

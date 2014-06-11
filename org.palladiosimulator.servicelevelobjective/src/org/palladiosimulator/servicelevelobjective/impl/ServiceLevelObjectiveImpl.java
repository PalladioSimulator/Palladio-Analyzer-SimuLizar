/**
 */
package org.palladiosimulator.servicelevelobjective.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;
import org.palladiosimulator.servicelevelobjective.Threshold;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Level Objective</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.servicelevelobjective.impl.ServiceLevelObjectiveImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.palladiosimulator.servicelevelobjective.impl.ServiceLevelObjectiveImpl#getLowerThreshold <em>Lower Threshold</em>}</li>
 *   <li>{@link org.palladiosimulator.servicelevelobjective.impl.ServiceLevelObjectiveImpl#getUpperThreshold <em>Upper Threshold</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceLevelObjectiveImpl extends NamedElementImpl implements ServiceLevelObjective {
    /**
     * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected static final String DESCRIPTION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected String description = DESCRIPTION_EDEFAULT;

    /**
     * The cached value of the '{@link #getLowerThreshold() <em>Lower Threshold</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLowerThreshold()
     * @generated
     * @ordered
     */
    protected Threshold lowerThreshold;

    /**
     * The cached value of the '{@link #getUpperThreshold() <em>Upper Threshold</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getUpperThreshold()
     * @generated
     * @ordered
     */
    protected Threshold upperThreshold;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ServiceLevelObjectiveImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ServicelevelObjectivePackage.Literals.SERVICE_LEVEL_OBJECTIVE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDescription(String newDescription) {
        String oldDescription = description;
        description = newDescription;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__DESCRIPTION, oldDescription, description));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Threshold getLowerThreshold() {
        return lowerThreshold;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLowerThreshold(Threshold newLowerThreshold, NotificationChain msgs) {
        Threshold oldLowerThreshold = lowerThreshold;
        lowerThreshold = newLowerThreshold;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD, oldLowerThreshold, newLowerThreshold);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLowerThreshold(Threshold newLowerThreshold) {
        if (newLowerThreshold != lowerThreshold) {
            NotificationChain msgs = null;
            if (lowerThreshold != null)
                msgs = ((InternalEObject)lowerThreshold).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD, null, msgs);
            if (newLowerThreshold != null)
                msgs = ((InternalEObject)newLowerThreshold).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD, null, msgs);
            msgs = basicSetLowerThreshold(newLowerThreshold, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD, newLowerThreshold, newLowerThreshold));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Threshold getUpperThreshold() {
        return upperThreshold;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetUpperThreshold(Threshold newUpperThreshold, NotificationChain msgs) {
        Threshold oldUpperThreshold = upperThreshold;
        upperThreshold = newUpperThreshold;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD, oldUpperThreshold, newUpperThreshold);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUpperThreshold(Threshold newUpperThreshold) {
        if (newUpperThreshold != upperThreshold) {
            NotificationChain msgs = null;
            if (upperThreshold != null)
                msgs = ((InternalEObject)upperThreshold).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD, null, msgs);
            if (newUpperThreshold != null)
                msgs = ((InternalEObject)newUpperThreshold).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD, null, msgs);
            msgs = basicSetUpperThreshold(newUpperThreshold, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD, newUpperThreshold, newUpperThreshold));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD:
                return basicSetLowerThreshold(null, msgs);
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD:
                return basicSetUpperThreshold(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__DESCRIPTION:
                return getDescription();
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD:
                return getLowerThreshold();
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD:
                return getUpperThreshold();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD:
                setLowerThreshold((Threshold)newValue);
                return;
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD:
                setUpperThreshold((Threshold)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD:
                setLowerThreshold((Threshold)null);
                return;
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD:
                setUpperThreshold((Threshold)null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD:
                return lowerThreshold != null;
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD:
                return upperThreshold != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (description: ");
        result.append(description);
        result.append(')');
        return result.toString();
    }

} //ServiceLevelObjectiveImpl

/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.EnactAdaptationAction;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Enact Adaptation Action</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.EnactAdaptationActionImpl#getAdaptationStepURI
 * <em>Adaptation Step URI</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnactAdaptationActionImpl extends AdaptationActionImpl implements EnactAdaptationAction {
    /**
     * The default value of the '{@link #getAdaptationStepURI() <em>Adaptation Step URI</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAdaptationStepURI()
     * @generated
     * @ordered
     */
    protected static final String ADAPTATION_STEP_URI_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAdaptationStepURI() <em>Adaptation Step URI</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAdaptationStepURI()
     * @generated
     * @ordered
     */
    protected String adaptationStepURI = ADAPTATION_STEP_URI_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected EnactAdaptationActionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.ENACT_ADAPTATION_ACTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getAdaptationStepURI() {
        return this.adaptationStepURI;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setAdaptationStepURI(final String newAdaptationStepURI) {
        final String oldAdaptationStepURI = this.adaptationStepURI;
        this.adaptationStepURI = newAdaptationStepURI;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    CorePackage.ENACT_ADAPTATION_ACTION__ADAPTATION_STEP_URI, oldAdaptationStepURI,
                    this.adaptationStepURI));
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
        case CorePackage.ENACT_ADAPTATION_ACTION__ADAPTATION_STEP_URI:
            return this.getAdaptationStepURI();
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
        case CorePackage.ENACT_ADAPTATION_ACTION__ADAPTATION_STEP_URI:
            this.setAdaptationStepURI((String) newValue);
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
        case CorePackage.ENACT_ADAPTATION_ACTION__ADAPTATION_STEP_URI:
            this.setAdaptationStepURI(ADAPTATION_STEP_URI_EDEFAULT);
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
        case CorePackage.ENACT_ADAPTATION_ACTION__ADAPTATION_STEP_URI:
            return ADAPTATION_STEP_URI_EDEFAULT == null ? this.adaptationStepURI != null
                    : !ADAPTATION_STEP_URI_EDEFAULT.equals(this.adaptationStepURI);
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
        result.append(" (adaptationStepURI: ");
        result.append(this.adaptationStepURI);
        result.append(')');
        return result.toString();
    }

} // EnactAdaptationActionImpl

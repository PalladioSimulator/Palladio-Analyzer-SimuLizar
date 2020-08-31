/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.ecore.EClass;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.EnactAdaptationStep;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Enact Adaptation
 * Step</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.EnactAdaptationStepImpl#getAdaptationStepURI
 * <em>Adaptation Step URI</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnactAdaptationStepImpl extends AdaptationStepImpl implements EnactAdaptationStep {
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EnactAdaptationStepImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.ENACT_ADAPTATION_STEP;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getAdaptationStepURI() {
        return (String) this.eDynamicGet(CorePackage.ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI,
                CorePackage.Literals.ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAdaptationStepURI(final String newAdaptationStepURI) {
        this.eDynamicSet(CorePackage.ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI,
                CorePackage.Literals.ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI, newAdaptationStepURI);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case CorePackage.ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI:
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
        case CorePackage.ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI:
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
        case CorePackage.ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI:
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
        case CorePackage.ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI:
            return ADAPTATION_STEP_URI_EDEFAULT == null ? this.getAdaptationStepURI() != null
                    : !ADAPTATION_STEP_URI_EDEFAULT.equals(this.getAdaptationStepURI());
        }
        return super.eIsSet(featureID);
    }

} // EnactAdaptationStepImpl

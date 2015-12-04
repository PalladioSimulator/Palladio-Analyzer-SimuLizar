/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.GuardedAdaptationBehavior;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Guarded Adaptation Behavior</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.GuardedAdaptationBehaviorImpl#getPreconditionURI
 * <em>Precondition URI</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GuardedAdaptationBehaviorImpl extends AdaptationActionImpl implements GuardedAdaptationBehavior {
    /**
     * The default value of the '{@link #getPreconditionURI() <em>Precondition URI</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPreconditionURI()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_URI_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPreconditionURI() <em>Precondition URI</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPreconditionURI()
     * @generated
     * @ordered
     */
    protected String preconditionURI = PRECONDITION_URI_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GuardedAdaptationBehaviorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.GUARDED_ADAPTATION_BEHAVIOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getPreconditionURI() {
        return this.preconditionURI;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setPreconditionURI(final String newPreconditionURI) {
        final String oldPreconditionURI = this.preconditionURI;
        this.preconditionURI = newPreconditionURI;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    CorePackage.GUARDED_ADAPTATION_BEHAVIOR__PRECONDITION_URI, oldPreconditionURI,
                    this.preconditionURI));
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
        case CorePackage.GUARDED_ADAPTATION_BEHAVIOR__PRECONDITION_URI:
            return this.getPreconditionURI();
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
        case CorePackage.GUARDED_ADAPTATION_BEHAVIOR__PRECONDITION_URI:
            this.setPreconditionURI((String) newValue);
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
        case CorePackage.GUARDED_ADAPTATION_BEHAVIOR__PRECONDITION_URI:
            this.setPreconditionURI(PRECONDITION_URI_EDEFAULT);
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
        case CorePackage.GUARDED_ADAPTATION_BEHAVIOR__PRECONDITION_URI:
            return PRECONDITION_URI_EDEFAULT == null ? this.preconditionURI != null
                    : !PRECONDITION_URI_EDEFAULT.equals(this.preconditionURI);
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
        result.append(" (preconditionURI: ");
        result.append(this.preconditionURI);
        result.append(')');
        return result.toString();
    }

} // GuardedAdaptationBehaviorImpl

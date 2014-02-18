/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.palladiosimulator.simulizar.pms.Intervall;
import org.palladiosimulator.simulizar.pms.PmsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intervall</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.pms.impl.IntervallImpl#getIntervall <em>Intervall</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntervallImpl extends TemporalCharacterizationImpl implements Intervall
{
   /**
     * The default value of the '{@link #getIntervall() <em>Intervall</em>}' attribute.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @see #getIntervall()
     * @generated
     * @ordered
     */
   protected static final double INTERVALL_EDEFAULT = 0.0;

   /**
     * The cached value of the '{@link #getIntervall() <em>Intervall</em>}' attribute.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @see #getIntervall()
     * @generated
     * @ordered
     */
   protected double intervall = INTERVALL_EDEFAULT;

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   protected IntervallImpl()
   {
        super();
    }

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   @Override
   protected EClass eStaticClass()
   {
        return PmsPackage.Literals.INTERVALL;
    }

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   public double getIntervall()
   {
        return intervall;
    }

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   public void setIntervall(double newIntervall)
   {
        double oldIntervall = intervall;
        intervall = newIntervall;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PmsPackage.INTERVALL__INTERVALL, oldIntervall, intervall));
    }

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
        switch (featureID) {
            case PmsPackage.INTERVALL__INTERVALL:
                return getIntervall();
        }
        return super.eGet(featureID, resolve, coreType);
    }

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   @Override
   public void eSet(int featureID, Object newValue)
   {
        switch (featureID) {
            case PmsPackage.INTERVALL__INTERVALL:
                setIntervall((Double)newValue);
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
   public void eUnset(int featureID)
   {
        switch (featureID) {
            case PmsPackage.INTERVALL__INTERVALL:
                setIntervall(INTERVALL_EDEFAULT);
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
   public boolean eIsSet(int featureID)
   {
        switch (featureID) {
            case PmsPackage.INTERVALL__INTERVALL:
                return intervall != INTERVALL_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   @Override
   public String toString()
   {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (intervall: ");
        result.append(intervall);
        result.append(')');
        return result.toString();
    }

} //IntervallImpl

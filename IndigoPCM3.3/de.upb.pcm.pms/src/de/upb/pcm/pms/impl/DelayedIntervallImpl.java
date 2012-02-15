/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.upb.pcm.pms.impl;

import de.upb.pcm.pms.DelayedIntervall;
import de.upb.pcm.pms.PmsPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Delayed Intervall</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.upb.pcm.pms.impl.DelayedIntervallImpl#getDelay <em>Delay</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DelayedIntervallImpl extends IntervallImpl implements DelayedIntervall
{
   /**
    * The default value of the '{@link #getDelay() <em>Delay</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDelay()
    * @generated
    * @ordered
    */
   protected static final double DELAY_EDEFAULT = 0.0;

   /**
    * The cached value of the '{@link #getDelay() <em>Delay</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getDelay()
    * @generated
    * @ordered
    */
   protected double delay = DELAY_EDEFAULT;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected DelayedIntervallImpl()
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
      return PmsPackage.Literals.DELAYED_INTERVALL;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public double getDelay()
   {
      return delay;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setDelay(double newDelay)
   {
      double oldDelay = delay;
      delay = newDelay;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, PmsPackage.DELAYED_INTERVALL__DELAY, oldDelay, delay));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   @Override
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case PmsPackage.DELAYED_INTERVALL__DELAY:
            return getDelay();
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
      switch (featureID)
      {
         case PmsPackage.DELAYED_INTERVALL__DELAY:
            setDelay((Double)newValue);
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
      switch (featureID)
      {
         case PmsPackage.DELAYED_INTERVALL__DELAY:
            setDelay(DELAY_EDEFAULT);
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
      switch (featureID)
      {
         case PmsPackage.DELAYED_INTERVALL__DELAY:
            return delay != DELAY_EDEFAULT;
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
      result.append(" (delay: ");
      result.append(delay);
      result.append(')');
      return result.toString();
   }

} //DelayedIntervallImpl

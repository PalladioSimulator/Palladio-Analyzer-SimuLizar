/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.upb.pcm.prm.impl;

import de.uka.ipd.sdq.pcm.resourcetype.ProcessingResourceType;

import de.upb.pcm.prm.PrmPackage;
import de.upb.pcm.prm.ResourceContainerMeasurement;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Container Measurement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.upb.pcm.prm.impl.ResourceContainerMeasurementImpl#getProcessingResourceType <em>Processing Resource Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceContainerMeasurementImpl extends PCMModelElementMeasurementImpl implements ResourceContainerMeasurement
{
   /**
    * The cached value of the '{@link #getProcessingResourceType() <em>Processing Resource Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getProcessingResourceType()
    * @generated
    * @ordered
    */
   protected ProcessingResourceType processingResourceType;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected ResourceContainerMeasurementImpl()
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
      return PrmPackage.Literals.RESOURCE_CONTAINER_MEASUREMENT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ProcessingResourceType getProcessingResourceType()
   {
      if (processingResourceType != null && processingResourceType.eIsProxy())
      {
         InternalEObject oldProcessingResourceType = (InternalEObject)processingResourceType;
         processingResourceType = (ProcessingResourceType)eResolveProxy(oldProcessingResourceType);
         if (processingResourceType != oldProcessingResourceType)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, PrmPackage.RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE, oldProcessingResourceType, processingResourceType));
         }
      }
      return processingResourceType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public ProcessingResourceType basicGetProcessingResourceType()
   {
      return processingResourceType;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setProcessingResourceType(ProcessingResourceType newProcessingResourceType)
   {
      ProcessingResourceType oldProcessingResourceType = processingResourceType;
      processingResourceType = newProcessingResourceType;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, PrmPackage.RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE, oldProcessingResourceType, processingResourceType));
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
         case PrmPackage.RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE:
            if (resolve) return getProcessingResourceType();
            return basicGetProcessingResourceType();
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
         case PrmPackage.RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE:
            setProcessingResourceType((ProcessingResourceType)newValue);
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
         case PrmPackage.RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE:
            setProcessingResourceType((ProcessingResourceType)null);
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
         case PrmPackage.RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE:
            return processingResourceType != null;
      }
      return super.eIsSet(featureID);
   }

} //ResourceContainerMeasurementImpl

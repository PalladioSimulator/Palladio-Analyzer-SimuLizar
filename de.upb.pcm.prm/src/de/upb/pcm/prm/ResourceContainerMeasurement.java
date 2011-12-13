/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.upb.pcm.prm;

import de.uka.ipd.sdq.pcm.resourcetype.ProcessingResourceType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Container Measurement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Measurement for a resource of a resource container.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.upb.pcm.prm.ResourceContainerMeasurement#getProcessingResourceType <em>Processing Resource Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.upb.pcm.prm.PrmPackage#getResourceContainerMeasurement()
 * @model
 * @generated
 */
public interface ResourceContainerMeasurement extends PCMModelElementMeasurement
{
   /**
    * Returns the value of the '<em><b>Processing Resource Type</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Processing Resource Type</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Processing Resource Type</em>' reference.
    * @see #setProcessingResourceType(ProcessingResourceType)
    * @see de.upb.pcm.prm.PrmPackage#getResourceContainerMeasurement_ProcessingResourceType()
    * @model
    * @generated
    */
   ProcessingResourceType getProcessingResourceType();

   /**
    * Sets the value of the '{@link de.upb.pcm.prm.ResourceContainerMeasurement#getProcessingResourceType <em>Processing Resource Type</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Processing Resource Type</em>' reference.
    * @see #getProcessingResourceType()
    * @generated
    */
   void setProcessingResourceType(ProcessingResourceType value);

} // ResourceContainerMeasurement

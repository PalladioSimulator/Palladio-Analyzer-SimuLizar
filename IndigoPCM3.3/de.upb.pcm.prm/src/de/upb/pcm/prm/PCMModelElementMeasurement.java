/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.upb.pcm.prm;


import org.eclipse.emf.ecore.EObject;

import de.upb.pcm.pms.MeasurementSpecification;


/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>PCM Model Element Measurement</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Measurement for a pcm model element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.upb.pcm.prm.PCMModelElementMeasurement#getPcmModelElement <em>Pcm Model Element</em>}</li>
 *   <li>{@link de.upb.pcm.prm.PCMModelElementMeasurement#getMeasurementSpecification <em>Measurement Specification</em>}</li>
 *   <li>{@link de.upb.pcm.prm.PCMModelElementMeasurement#getMeasurementValue <em>Measurement Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.upb.pcm.prm.PrmPackage#getPCMModelElementMeasurement()
 * @model
 * @generated
 */
public interface PCMModelElementMeasurement extends UniqueElement
{
   /**
    * Returns the value of the '<em><b>Pcm Model Element</b></em>' reference.
    * <!-- begin-user-doc
    * -->
    * <p>
    * If the meaning of the '<em>Pcm Model Element</em>' reference isn't clear, there really should
    * be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Pcm Model Element</em>' reference.
    * @see #setPcmModelElement(EObject)
    * @see de.upb.pcm.prm.PrmPackage#getPCMModelElementMeasurement_PcmModelElement()
    * @model
    * @generated
    */
   EObject getPcmModelElement();


   /**
    * Sets the value of the '{@link de.upb.pcm.prm.PCMModelElementMeasurement#getPcmModelElement <em>Pcm Model Element</em>}' reference.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @param value the new value of the '<em>Pcm Model Element</em>' reference.
    * @see #getPcmModelElement()
    * @generated
    */
   void setPcmModelElement(EObject value);


   /**
    * Returns the value of the '<em><b>Measurement Specification</b></em>' reference. <!--
    * begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Measurement Specification</em>' reference isn't clear, there really
    * should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * 
    * @return the value of the '<em>Measurement Specification</em>' reference.
    * @see #setMeasurementSpecification(MeasurementSpecification)
    * @see de.upb.pcm.prm.PrmPackage#getPCMModelElementMeasurement_MeasurementSpecification()
    * @model
    * @generated
    */
   MeasurementSpecification getMeasurementSpecification();


   /**
    * Sets the value of the '{@link de.upb.pcm.prm.PCMModelElementMeasurement#getMeasurementSpecification <em>Measurement Specification</em>}' reference.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @param value the new value of the '<em>Measurement Specification</em>' reference.
    * @see #getMeasurementSpecification()
    * @generated
    */
   void setMeasurementSpecification(MeasurementSpecification value);


   /**
    * Returns the value of the '<em><b>Measurement Value</b></em>' attribute.
    * The default value is <code>"0.0"</code>.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Measurement Value</em>' attribute isn't clear, there really should
    * be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Measurement Value</em>' attribute.
    * @see #setMeasurementValue(double)
    * @see de.upb.pcm.prm.PrmPackage#getPCMModelElementMeasurement_MeasurementValue()
    * @model default="0.0"
    * @generated
    */
   double getMeasurementValue();


   /**
    * Sets the value of the '{@link de.upb.pcm.prm.PCMModelElementMeasurement#getMeasurementValue <em>Measurement Value</em>}' attribute.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @param value the new value of the '<em>Measurement Value</em>' attribute.
    * @see #getMeasurementValue()
    * @generated
    */
   void setMeasurementValue(double value);

} // PCMModelElementMeasurement

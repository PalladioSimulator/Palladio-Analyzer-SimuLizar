/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.upb.pcm.pms;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PCM Element Monitor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.upb.pcm.pms.PCMElementMonitor#getMeasurementSpecification <em>Measurement Specification</em>}</li>
 *   <li>{@link de.upb.pcm.pms.PCMElementMonitor#getPcmElementClassifier <em>Pcm Element Classifier</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.upb.pcm.pms.PmsPackage#getPCMElementMonitor()
 * @model
 * @generated
 */
public interface PCMElementMonitor extends UniqueElement
{
   /**
    * Returns the value of the '<em><b>Measurement Specification</b></em>' containment reference list.
    * The list contents are of type {@link de.upb.pcm.pms.MeasurementSpecification}.
    * It is bidirectional and its opposite is '{@link de.upb.pcm.pms.MeasurementSpecification#getPcmElementMonitor <em>Pcm Element Monitor</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Measurement Specification</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Measurement Specification</em>' containment reference list.
    * @see de.upb.pcm.pms.PmsPackage#getPCMElementMonitor_MeasurementSpecification()
    * @see de.upb.pcm.pms.MeasurementSpecification#getPcmElementMonitor
    * @model opposite="pcmElementMonitor" containment="true" required="true"
    * @generated
    */
   EList<MeasurementSpecification> getMeasurementSpecification();

   /**
    * Returns the value of the '<em><b>Pcm Element Classifier</b></em>' reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Pcm Element Classifier</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Pcm Element Classifier</em>' reference.
    * @see #setPcmElementClassifier(EClass)
    * @see de.upb.pcm.pms.PmsPackage#getPCMElementMonitor_PcmElementClassifier()
    * @model
    * @generated
    */
   EClass getPcmElementClassifier();

   /**
    * Sets the value of the '{@link de.upb.pcm.pms.PCMElementMonitor#getPcmElementClassifier <em>Pcm Element Classifier</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Pcm Element Classifier</em>' reference.
    * @see #getPcmElementClassifier()
    * @generated
    */
   void setPcmElementClassifier(EClass value);

} // PCMElementMonitor

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.upb.pcm.pms;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Monitoring Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.upb.pcm.pms.MonitoringSpecification#getPerformanceMeasurements <em>Performance Measurements</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.upb.pcm.pms.PmsPackage#getMonitoringSpecification()
 * @model
 * @generated
 */
public interface MonitoringSpecification extends UniqueElement
{
   /**
    * Returns the value of the '<em><b>Performance Measurements</b></em>' containment reference list.
    * The list contents are of type {@link de.upb.pcm.pms.PerformanceMeasurement}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Performance Measurements</em>' containment reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Performance Measurements</em>' containment reference list.
    * @see de.upb.pcm.pms.PmsPackage#getMonitoringSpecification_PerformanceMeasurements()
    * @model containment="true"
    * @generated
    */
   EList<PerformanceMeasurement> getPerformanceMeasurements();

} // MonitoringSpecification

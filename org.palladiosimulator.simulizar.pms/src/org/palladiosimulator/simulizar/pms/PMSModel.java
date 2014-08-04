/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>PMS Model</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> Base class representing the pms model. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.pms.PMSModel#getPerformanceMeasurements <em>
 * Performance Measurements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.pms.PmsPackage#getPMSModel()
 * @model
 * @generated
 */
public interface PMSModel extends UniqueElement {
    /**
     * Returns the value of the '<em><b>Performance Measurements</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.palladiosimulator.simulizar.pms.PerformanceMeasurement}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Performance Measurements</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Performance Measurements</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.pms.PmsPackage#getPMSModel_PerformanceMeasurements()
     * @model containment="true"
     * @generated
     */
    EList<PerformanceMeasurement> getPerformanceMeasurements();

} // PMSModel

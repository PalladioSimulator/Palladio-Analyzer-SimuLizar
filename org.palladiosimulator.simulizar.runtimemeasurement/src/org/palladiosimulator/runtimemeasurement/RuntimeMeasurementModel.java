/**
 */
package org.palladiosimulator.runtimemeasurement;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Model</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> Base class representing the RuntimeMeasurement model. <!-- end-model-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel#getMeasurements <em>
 * Measurements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage#getRuntimeMeasurementModel()
 * @model
 * @generated
 */
public interface RuntimeMeasurementModel extends EObject, Identifier {
    /**
     * Returns the value of the '<em><b>Measurements</b></em>' containment reference list. The list
     * contents are of type {@link org.palladiosimulator.runtimemeasurement.RuntimeMeasurement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Measurements</em>' containment reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Measurements</em>' containment reference list.
     * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage#getRuntimeMeasurementModel_Measurements()
     * @model containment="true"
     * @generated
     */
    EList<RuntimeMeasurement> getMeasurements();

} // RuntimeMeasurementModel

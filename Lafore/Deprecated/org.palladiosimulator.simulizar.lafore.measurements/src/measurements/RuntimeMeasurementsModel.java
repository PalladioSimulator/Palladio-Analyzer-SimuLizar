/**
 */
package measurements;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Runtime Measurements Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link measurements.RuntimeMeasurementsModel#getMeasurements <em>Measurements</em>}</li>
 * </ul>
 * </p>
 *
 * @see measurements.MeasurementsPackage#getRuntimeMeasurementsModel()
 * @model
 * @generated
 */
public interface RuntimeMeasurementsModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Measurements</b></em>' containment reference list.
	 * The list contents are of type {@link measurements.MeasuredMonitor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Measurements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Measurements</em>' containment reference list.
	 * @see measurements.MeasurementsPackage#getRuntimeMeasurementsModel_Measurements()
	 * @model containment="true"
	 * @generated
	 */
	EList<MeasuredMonitor> getMeasurements();

} // RuntimeMeasurementsModel

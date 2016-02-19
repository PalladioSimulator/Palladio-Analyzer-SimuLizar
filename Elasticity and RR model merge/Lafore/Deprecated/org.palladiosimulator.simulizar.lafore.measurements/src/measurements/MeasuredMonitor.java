/**
 */
package measurements;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.Monitor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measured Monitor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link measurements.MeasuredMonitor#getValue <em>Value</em>}</li>
 *   <li>{@link measurements.MeasuredMonitor#getMetric <em>Metric</em>}</li>
 *   <li>{@link measurements.MeasuredMonitor#getMonitor <em>Monitor</em>}</li>
 * </ul>
 * </p>
 *
 * @see measurements.MeasurementsPackage#getMeasuredMonitor()
 * @model
 * @generated
 */
public interface MeasuredMonitor extends EObject {
	/**
	 * Returns the value of the '<em><b>Monitor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Monitor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Monitor</em>' reference.
	 * @see #setMonitor(Monitor)
	 * @see measurements.MeasurementsPackage#getMeasuredMonitor_Monitor()
	 * @model
	 * @generated
	 */
	Monitor getMonitor();

	/**
	 * Sets the value of the '{@link measurements.MeasuredMonitor#getMonitor <em>Monitor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Monitor</em>' reference.
	 * @see #getMonitor()
	 * @generated
	 */
	void setMonitor(Monitor value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(double)
	 * @see measurements.MeasurementsPackage#getMeasuredMonitor_Value()
	 * @model default="0.0"
	 * @generated
	 */
	double getValue();

	/**
	 * Sets the value of the '{@link measurements.MeasuredMonitor#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(double value);

	/**
	 * Returns the value of the '<em><b>Metric</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metric</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metric</em>' reference.
	 * @see #setMetric(MeasurementSpecification)
	 * @see measurements.MeasurementsPackage#getMeasuredMonitor_Metric()
	 * @model
	 * @generated
	 */
	MeasurementSpecification getMetric();

	/**
	 * Sets the value of the '{@link measurements.MeasuredMonitor#getMetric <em>Metric</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metric</em>' reference.
	 * @see #getMetric()
	 * @generated
	 */
	void setMetric(MeasurementSpecification value);

} // MeasuredMonitor

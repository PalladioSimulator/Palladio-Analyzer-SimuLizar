/**
 */
package measurements;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see measurements.MeasurementsPackage
 * @generated
 */
public interface MeasurementsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MeasurementsFactory eINSTANCE = measurements.impl.MeasurementsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Measured Monitor</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measured Monitor</em>'.
	 * @generated
	 */
	MeasuredMonitor createMeasuredMonitor();

	/**
	 * Returns a new object of class '<em>Runtime Measurements Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Runtime Measurements Model</em>'.
	 * @generated
	 */
	RuntimeMeasurementsModel createRuntimeMeasurementsModel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	MeasurementsPackage getMeasurementsPackage();

} //MeasurementsFactory

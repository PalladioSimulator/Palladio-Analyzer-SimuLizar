/**
 */
package measurements;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see measurements.MeasurementsFactory
 * @model kind="package"
 * @generated
 */
public interface MeasurementsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "measurements";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://simulizar.palladiosimulator.org/lafore/measurements/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "measurements";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MeasurementsPackage eINSTANCE = measurements.impl.MeasurementsPackageImpl.init();

	/**
	 * The meta object id for the '{@link measurements.impl.MeasuredMonitorImpl <em>Measured Monitor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see measurements.impl.MeasuredMonitorImpl
	 * @see measurements.impl.MeasurementsPackageImpl#getMeasuredMonitor()
	 * @generated
	 */
	int MEASURED_MONITOR = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_MONITOR__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Metric</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_MONITOR__METRIC = 1;

	/**
	 * The feature id for the '<em><b>Monitor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_MONITOR__MONITOR = 2;

	/**
	 * The number of structural features of the '<em>Measured Monitor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_MONITOR_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Measured Monitor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURED_MONITOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link measurements.impl.RuntimeMeasurementsModelImpl <em>Runtime Measurements Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see measurements.impl.RuntimeMeasurementsModelImpl
	 * @see measurements.impl.MeasurementsPackageImpl#getRuntimeMeasurementsModel()
	 * @generated
	 */
	int RUNTIME_MEASUREMENTS_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Measurements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_MEASUREMENTS_MODEL__MEASUREMENTS = 0;

	/**
	 * The number of structural features of the '<em>Runtime Measurements Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_MEASUREMENTS_MODEL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Runtime Measurements Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_MEASUREMENTS_MODEL_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link measurements.MeasuredMonitor <em>Measured Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measured Monitor</em>'.
	 * @see measurements.MeasuredMonitor
	 * @generated
	 */
	EClass getMeasuredMonitor();

	/**
	 * Returns the meta object for the reference '{@link measurements.MeasuredMonitor#getMonitor <em>Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Monitor</em>'.
	 * @see measurements.MeasuredMonitor#getMonitor()
	 * @see #getMeasuredMonitor()
	 * @generated
	 */
	EReference getMeasuredMonitor_Monitor();

	/**
	 * Returns the meta object for the attribute '{@link measurements.MeasuredMonitor#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see measurements.MeasuredMonitor#getValue()
	 * @see #getMeasuredMonitor()
	 * @generated
	 */
	EAttribute getMeasuredMonitor_Value();

	/**
	 * Returns the meta object for the reference '{@link measurements.MeasuredMonitor#getMetric <em>Metric</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Metric</em>'.
	 * @see measurements.MeasuredMonitor#getMetric()
	 * @see #getMeasuredMonitor()
	 * @generated
	 */
	EReference getMeasuredMonitor_Metric();

	/**
	 * Returns the meta object for class '{@link measurements.RuntimeMeasurementsModel <em>Runtime Measurements Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Runtime Measurements Model</em>'.
	 * @see measurements.RuntimeMeasurementsModel
	 * @generated
	 */
	EClass getRuntimeMeasurementsModel();

	/**
	 * Returns the meta object for the containment reference list '{@link measurements.RuntimeMeasurementsModel#getMeasurements <em>Measurements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Measurements</em>'.
	 * @see measurements.RuntimeMeasurementsModel#getMeasurements()
	 * @see #getRuntimeMeasurementsModel()
	 * @generated
	 */
	EReference getRuntimeMeasurementsModel_Measurements();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MeasurementsFactory getMeasurementsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link measurements.impl.MeasuredMonitorImpl <em>Measured Monitor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see measurements.impl.MeasuredMonitorImpl
		 * @see measurements.impl.MeasurementsPackageImpl#getMeasuredMonitor()
		 * @generated
		 */
		EClass MEASURED_MONITOR = eINSTANCE.getMeasuredMonitor();

		/**
		 * The meta object literal for the '<em><b>Monitor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURED_MONITOR__MONITOR = eINSTANCE.getMeasuredMonitor_Monitor();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEASURED_MONITOR__VALUE = eINSTANCE.getMeasuredMonitor_Value();

		/**
		 * The meta object literal for the '<em><b>Metric</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEASURED_MONITOR__METRIC = eINSTANCE.getMeasuredMonitor_Metric();

		/**
		 * The meta object literal for the '{@link measurements.impl.RuntimeMeasurementsModelImpl <em>Runtime Measurements Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see measurements.impl.RuntimeMeasurementsModelImpl
		 * @see measurements.impl.MeasurementsPackageImpl#getRuntimeMeasurementsModel()
		 * @generated
		 */
		EClass RUNTIME_MEASUREMENTS_MODEL = eINSTANCE.getRuntimeMeasurementsModel();

		/**
		 * The meta object literal for the '<em><b>Measurements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RUNTIME_MEASUREMENTS_MODEL__MEASUREMENTS = eINSTANCE.getRuntimeMeasurementsModel_Measurements();

	}

} //MeasurementsPackage

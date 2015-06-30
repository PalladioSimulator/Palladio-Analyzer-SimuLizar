/**
 */
package measurements.impl;

import measurements.MeasuredMonitor;
import measurements.MeasurementsFactory;
import measurements.MeasurementsPackage;
import measurements.RuntimeMeasurementsModel;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MeasurementsPackageImpl extends EPackageImpl implements MeasurementsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass measuredMonitorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runtimeMeasurementsModelEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see measurements.MeasurementsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MeasurementsPackageImpl() {
		super(eNS_URI, MeasurementsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link MeasurementsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MeasurementsPackage init() {
		if (isInited) return (MeasurementsPackage)EPackage.Registry.INSTANCE.getEPackage(MeasurementsPackage.eNS_URI);

		// Obtain or create and register package
		MeasurementsPackageImpl theMeasurementsPackage = (MeasurementsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MeasurementsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MeasurementsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		MonitorRepositoryPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theMeasurementsPackage.createPackageContents();

		// Initialize created meta-data
		theMeasurementsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMeasurementsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MeasurementsPackage.eNS_URI, theMeasurementsPackage);
		return theMeasurementsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMeasuredMonitor() {
		return measuredMonitorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasuredMonitor_Monitor() {
		return (EReference)measuredMonitorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMeasuredMonitor_Value() {
		return (EAttribute)measuredMonitorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMeasuredMonitor_Metric() {
		return (EReference)measuredMonitorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRuntimeMeasurementsModel() {
		return runtimeMeasurementsModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRuntimeMeasurementsModel_Measurements() {
		return (EReference)runtimeMeasurementsModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementsFactory getMeasurementsFactory() {
		return (MeasurementsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		measuredMonitorEClass = createEClass(MEASURED_MONITOR);
		createEAttribute(measuredMonitorEClass, MEASURED_MONITOR__VALUE);
		createEReference(measuredMonitorEClass, MEASURED_MONITOR__METRIC);
		createEReference(measuredMonitorEClass, MEASURED_MONITOR__MONITOR);

		runtimeMeasurementsModelEClass = createEClass(RUNTIME_MEASUREMENTS_MODEL);
		createEReference(runtimeMeasurementsModelEClass, RUNTIME_MEASUREMENTS_MODEL__MEASUREMENTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		MonitorRepositoryPackage theMonitorRepositoryPackage = (MonitorRepositoryPackage)EPackage.Registry.INSTANCE.getEPackage(MonitorRepositoryPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(measuredMonitorEClass, MeasuredMonitor.class, "MeasuredMonitor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMeasuredMonitor_Value(), ecorePackage.getEDouble(), "value", "0.0", 0, 1, MeasuredMonitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasuredMonitor_Metric(), theMonitorRepositoryPackage.getMeasurementSpecification(), null, "metric", null, 0, 1, MeasuredMonitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMeasuredMonitor_Monitor(), theMonitorRepositoryPackage.getMonitor(), null, "monitor", null, 0, 1, MeasuredMonitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(runtimeMeasurementsModelEClass, RuntimeMeasurementsModel.class, "RuntimeMeasurementsModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRuntimeMeasurementsModel_Measurements(), this.getMeasuredMonitor(), null, "measurements", null, 0, -1, RuntimeMeasurementsModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //MeasurementsPackageImpl

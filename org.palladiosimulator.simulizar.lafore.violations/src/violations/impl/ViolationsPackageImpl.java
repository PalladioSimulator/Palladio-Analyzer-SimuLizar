/**
 */
package violations.impl;

import de.uka.ipd.sdq.pcm.PcmPackage;

import de.uka.ipd.sdq.pcm.core.entity.EntityPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;

import violations.NonQuantifiableViolation;
import violations.QuantifiableViolation;
import violations.RuntimeViolationsModel;
import violations.Violation;
import violations.ViolationType;
import violations.ViolationsFactory;
import violations.ViolationsPackage;
import violations.ViolationsRepository;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ViolationsPackageImpl extends EPackageImpl implements ViolationsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass violationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass violationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass violationsRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runtimeViolationsModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass quantifiableViolationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nonQuantifiableViolationEClass = null;

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
	 * @see violations.ViolationsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ViolationsPackageImpl() {
		super(eNS_URI, ViolationsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ViolationsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ViolationsPackage init() {
		if (isInited) return (ViolationsPackage)EPackage.Registry.INSTANCE.getEPackage(ViolationsPackage.eNS_URI);

		// Obtain or create and register package
		ViolationsPackageImpl theViolationsPackage = (ViolationsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ViolationsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ViolationsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		PcmPackage.eINSTANCE.eClass();
		ServicelevelObjectivePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theViolationsPackage.createPackageContents();

		// Initialize created meta-data
		theViolationsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theViolationsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ViolationsPackage.eNS_URI, theViolationsPackage);
		return theViolationsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getViolationType() {
		return violationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getViolationType_Description() {
		return (EAttribute)violationTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getViolationType_Slo() {
		return (EReference)violationTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getViolation() {
		return violationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getViolation_ViolationType() {
		return (EReference)violationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getViolationsRepository() {
		return violationsRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getViolationsRepository_ViolationTypes() {
		return (EReference)violationsRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRuntimeViolationsModel() {
		return runtimeViolationsModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRuntimeViolationsModel_Violations() {
		return (EReference)runtimeViolationsModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQuantifiableViolation() {
		return quantifiableViolationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuantifiableViolation_Percentage() {
		return (EAttribute)quantifiableViolationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNonQuantifiableViolation() {
		return nonQuantifiableViolationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationsFactory getViolationsFactory() {
		return (ViolationsFactory)getEFactoryInstance();
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
		violationTypeEClass = createEClass(VIOLATION_TYPE);
		createEAttribute(violationTypeEClass, VIOLATION_TYPE__DESCRIPTION);
		createEReference(violationTypeEClass, VIOLATION_TYPE__SLO);

		violationEClass = createEClass(VIOLATION);
		createEReference(violationEClass, VIOLATION__VIOLATION_TYPE);

		violationsRepositoryEClass = createEClass(VIOLATIONS_REPOSITORY);
		createEReference(violationsRepositoryEClass, VIOLATIONS_REPOSITORY__VIOLATION_TYPES);

		runtimeViolationsModelEClass = createEClass(RUNTIME_VIOLATIONS_MODEL);
		createEReference(runtimeViolationsModelEClass, RUNTIME_VIOLATIONS_MODEL__VIOLATIONS);

		quantifiableViolationEClass = createEClass(QUANTIFIABLE_VIOLATION);
		createEAttribute(quantifiableViolationEClass, QUANTIFIABLE_VIOLATION__PERCENTAGE);

		nonQuantifiableViolationEClass = createEClass(NON_QUANTIFIABLE_VIOLATION);
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
		EntityPackage theEntityPackage = (EntityPackage)EPackage.Registry.INSTANCE.getEPackage(EntityPackage.eNS_URI);
		ServicelevelObjectivePackage theServicelevelObjectivePackage = (ServicelevelObjectivePackage)EPackage.Registry.INSTANCE.getEPackage(ServicelevelObjectivePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		violationTypeEClass.getESuperTypes().add(theEntityPackage.getEntity());
		violationEClass.getESuperTypes().add(theEntityPackage.getEntity());
		violationsRepositoryEClass.getESuperTypes().add(theEntityPackage.getEntity());
		runtimeViolationsModelEClass.getESuperTypes().add(theEntityPackage.getEntity());
		quantifiableViolationEClass.getESuperTypes().add(this.getViolation());
		nonQuantifiableViolationEClass.getESuperTypes().add(this.getViolation());

		// Initialize classes and features; add operations and parameters
		initEClass(violationTypeEClass, ViolationType.class, "ViolationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getViolationType_Description(), ecorePackage.getEString(), "description", null, 0, 1, ViolationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getViolationType_Slo(), theServicelevelObjectivePackage.getServiceLevelObjective(), null, "slo", null, 1, 1, ViolationType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(violationEClass, Violation.class, "Violation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getViolation_ViolationType(), this.getViolationType(), null, "violationType", null, 1, 1, Violation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(violationsRepositoryEClass, ViolationsRepository.class, "ViolationsRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getViolationsRepository_ViolationTypes(), this.getViolationType(), null, "violationTypes", null, 0, -1, ViolationsRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(runtimeViolationsModelEClass, RuntimeViolationsModel.class, "RuntimeViolationsModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRuntimeViolationsModel_Violations(), this.getViolation(), null, "violations", null, 0, -1, RuntimeViolationsModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(quantifiableViolationEClass, QuantifiableViolation.class, "QuantifiableViolation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getQuantifiableViolation_Percentage(), ecorePackage.getEInt(), "percentage", null, 1, 1, QuantifiableViolation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nonQuantifiableViolationEClass, NonQuantifiableViolation.class, "NonQuantifiableViolation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //ViolationsPackageImpl

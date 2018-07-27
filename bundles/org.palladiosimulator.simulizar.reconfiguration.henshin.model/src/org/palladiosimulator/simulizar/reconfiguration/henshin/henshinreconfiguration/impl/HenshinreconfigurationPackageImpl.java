/**
 */
package org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.henshin.model.HenshinPackage;
import org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.HenshinTransformation;
import org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.HenshinreconfigurationFactory;
import org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.HenshinreconfigurationPackage;
import org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class HenshinreconfigurationPackageImpl extends EPackageImpl implements HenshinreconfigurationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass henshinTransformationEClass = null;

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
	 * @see org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.HenshinreconfigurationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private HenshinreconfigurationPackageImpl() {
		super(eNS_URI, HenshinreconfigurationFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link HenshinreconfigurationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static HenshinreconfigurationPackage init() {
		if (isInited) return (HenshinreconfigurationPackage)EPackage.Registry.INSTANCE.getEPackage(HenshinreconfigurationPackage.eNS_URI);

		// Obtain or create and register package
		HenshinreconfigurationPackageImpl theHenshinreconfigurationPackage = (HenshinreconfigurationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof HenshinreconfigurationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new HenshinreconfigurationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		HenshinPackage.eINSTANCE.eClass();
		ReconfigurationrulePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theHenshinreconfigurationPackage.createPackageContents();

		// Initialize created meta-data
		theHenshinreconfigurationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theHenshinreconfigurationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(HenshinreconfigurationPackage.eNS_URI, theHenshinreconfigurationPackage);
		return theHenshinreconfigurationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getHenshinTransformation() {
		return henshinTransformationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HenshinreconfigurationFactory getHenshinreconfigurationFactory() {
		return (HenshinreconfigurationFactory)getEFactoryInstance();
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
		henshinTransformationEClass = createEClass(HENSHIN_TRANSFORMATION);
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
		ReconfigurationrulePackage theReconfigurationrulePackage = (ReconfigurationrulePackage)EPackage.Registry.INSTANCE.getEPackage(ReconfigurationrulePackage.eNS_URI);
		HenshinPackage theHenshinPackage = (HenshinPackage)EPackage.Registry.INSTANCE.getEPackage(HenshinPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		EGenericType g1 = createEGenericType(theReconfigurationrulePackage.getModelTransformation());
		EGenericType g2 = createEGenericType(theHenshinPackage.getUnit());
		g1.getETypeArguments().add(g2);
		henshinTransformationEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes, features, and operations; add parameters
		initEClass(henshinTransformationEClass, HenshinTransformation.class, "HenshinTransformation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //HenshinreconfigurationPackageImpl

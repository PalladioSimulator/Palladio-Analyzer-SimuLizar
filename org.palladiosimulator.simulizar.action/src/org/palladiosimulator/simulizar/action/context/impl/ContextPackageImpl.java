/**
 */
package org.palladiosimulator.simulizar.action.context.impl;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

import de.uka.ipd.sdq.probfunction.ProbfunctionPackage;

import de.uka.ipd.sdq.stoex.StoexPackage;

import de.uka.ipd.sdq.units.UnitsPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.modelversioning.emfprofile.EMFProfilePackage;

import org.palladiosimulator.pcm.PcmPackage;

import org.palladiosimulator.simulizar.action.context.ContextFactory;
import org.palladiosimulator.simulizar.action.context.ContextPackage;
import org.palladiosimulator.simulizar.action.context.ExecutionContext;

import org.palladiosimulator.simulizar.action.core.CorePackage;

import org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl;

import org.palladiosimulator.simulizar.action.instance.InstancePackage;

import org.palladiosimulator.simulizar.action.instance.impl.InstancePackageImpl;

import org.palladiosimulator.simulizar.action.mapping.MappingPackage;

import org.palladiosimulator.simulizar.action.mapping.impl.MappingPackageImpl;

import org.palladiosimulator.simulizar.action.parameter.ParameterPackage;

import org.palladiosimulator.simulizar.action.parameter.impl.ParameterPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ContextPackageImpl extends EPackageImpl implements ContextPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionContextEClass = null;

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
	 * @see org.palladiosimulator.simulizar.action.context.ContextPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ContextPackageImpl() {
		super(eNS_URI, ContextFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ContextPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ContextPackage init() {
		if (isInited)
			return (ContextPackage) EPackage.Registry.INSTANCE.getEPackage(ContextPackage.eNS_URI);

		// Obtain or create and register package
		ContextPackageImpl theContextPackage = (ContextPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof ContextPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
						: new ContextPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		EMFProfilePackage.eINSTANCE.eClass();
		IdentifierPackage.eINSTANCE.eClass();
		PcmPackage.eINSTANCE.eClass();
		ProbfunctionPackage.eINSTANCE.eClass();
		StoexPackage.eINSTANCE.eClass();
		UnitsPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		CorePackageImpl theCorePackage = (CorePackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI)
						: CorePackage.eINSTANCE);
		MappingPackageImpl theMappingPackage = (MappingPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(MappingPackage.eNS_URI) instanceof MappingPackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(MappingPackage.eNS_URI)
						: MappingPackage.eINSTANCE);
		InstancePackageImpl theInstancePackage = (InstancePackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(InstancePackage.eNS_URI) instanceof InstancePackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(InstancePackage.eNS_URI)
						: InstancePackage.eINSTANCE);
		ParameterPackageImpl theParameterPackage = (ParameterPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(ParameterPackage.eNS_URI) instanceof ParameterPackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(ParameterPackage.eNS_URI)
						: ParameterPackage.eINSTANCE);

		// Create package meta-data objects
		theContextPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theMappingPackage.createPackageContents();
		theInstancePackage.createPackageContents();
		theParameterPackage.createPackageContents();

		// Initialize created meta-data
		theContextPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theMappingPackage.initializePackageContents();
		theInstancePackage.initializePackageContents();
		theParameterPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theContextPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ContextPackage.eNS_URI, theContextPackage);
		return theContextPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExecutionContext() {
		return executionContextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContextFactory getContextFactory() {
		return (ContextFactory) getEFactoryInstance();
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
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		executionContextEClass = createEClass(EXECUTION_CONTEXT);
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
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		IdentifierPackage theIdentifierPackage = (IdentifierPackage) EPackage.Registry.INSTANCE
				.getEPackage(IdentifierPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		executionContextEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());

		// Initialize classes and features; add operations and parameters
		initEClass(executionContextEClass, ExecutionContext.class, "ExecutionContext", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //ContextPackageImpl

/**
 */
package org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.StoryDiagramActivity;
import org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.StorydiagramreconfigurationFactory;
import org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.StorydiagramreconfigurationPackage;
import org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage;
import org.storydriven.storydiagrams.StorydiagramsPackage;

import org.storydriven.storydiagrams.activities.ActivitiesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StorydiagramreconfigurationPackageImpl extends EPackageImpl implements StorydiagramreconfigurationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass storyDiagramActivityEClass = null;

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
	 * @see org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.StorydiagramreconfigurationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StorydiagramreconfigurationPackageImpl() {
		super(eNS_URI, StorydiagramreconfigurationFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link StorydiagramreconfigurationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StorydiagramreconfigurationPackage init() {
		if (isInited) return (StorydiagramreconfigurationPackage)EPackage.Registry.INSTANCE.getEPackage(StorydiagramreconfigurationPackage.eNS_URI);

		// Obtain or create and register package
		StorydiagramreconfigurationPackageImpl theStorydiagramreconfigurationPackage = (StorydiagramreconfigurationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StorydiagramreconfigurationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StorydiagramreconfigurationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ReconfigurationrulePackage.eINSTANCE.eClass();
		StorydiagramsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theStorydiagramreconfigurationPackage.createPackageContents();

		// Initialize created meta-data
		theStorydiagramreconfigurationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStorydiagramreconfigurationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StorydiagramreconfigurationPackage.eNS_URI, theStorydiagramreconfigurationPackage);
		return theStorydiagramreconfigurationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStoryDiagramActivity() {
		return storyDiagramActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StorydiagramreconfigurationFactory getStorydiagramreconfigurationFactory() {
		return (StorydiagramreconfigurationFactory)getEFactoryInstance();
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
		storyDiagramActivityEClass = createEClass(STORY_DIAGRAM_ACTIVITY);
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
		ActivitiesPackage theActivitiesPackage = (ActivitiesPackage)EPackage.Registry.INSTANCE.getEPackage(ActivitiesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		EGenericType g1 = createEGenericType(theReconfigurationrulePackage.getModelTransformation());
		EGenericType g2 = createEGenericType(theActivitiesPackage.getActivity());
		g1.getETypeArguments().add(g2);
		storyDiagramActivityEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes, features, and operations; add parameters
		initEClass(storyDiagramActivityEClass, StoryDiagramActivity.class, "StoryDiagramActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //StorydiagramreconfigurationPackageImpl

/**
 */
package org.palladiosimulator.simulizar.reconfiguration.storydiagramreconfiguration.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.palladiosimulator.simulizar.reconfiguration.storydiagramreconfiguration.StoryDiagramActivity;
import org.palladiosimulator.simulizar.reconfiguration.storydiagramreconfiguration.StoryDiagramReconfigurationFactory;
import org.palladiosimulator.simulizar.reconfiguration.storydiagramreconfiguration.StoryDiagramReconfigurationPackage;

import org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage;

import org.storydriven.storydiagrams.StorydiagramsPackage;

import org.storydriven.storydiagrams.activities.ActivitiesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StoryDiagramReconfigurationPackageImpl extends EPackageImpl implements StoryDiagramReconfigurationPackage {
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
     * @see org.palladiosimulator.simulizar.reconfiguration.storydiagramreconfiguration.StoryDiagramReconfigurationPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private StoryDiagramReconfigurationPackageImpl() {
        super(eNS_URI, StoryDiagramReconfigurationFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link StoryDiagramReconfigurationPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static StoryDiagramReconfigurationPackage init() {
        if (isInited) return (StoryDiagramReconfigurationPackage)EPackage.Registry.INSTANCE.getEPackage(StoryDiagramReconfigurationPackage.eNS_URI);

        // Obtain or create and register package
        StoryDiagramReconfigurationPackageImpl theStoryDiagramReconfigurationPackage = (StoryDiagramReconfigurationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StoryDiagramReconfigurationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StoryDiagramReconfigurationPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        reconfigurationrulePackage.eINSTANCE.eClass();
        StorydiagramsPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theStoryDiagramReconfigurationPackage.createPackageContents();

        // Initialize created meta-data
        theStoryDiagramReconfigurationPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theStoryDiagramReconfigurationPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(StoryDiagramReconfigurationPackage.eNS_URI, theStoryDiagramReconfigurationPackage);
        return theStoryDiagramReconfigurationPackage;
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
    public StoryDiagramReconfigurationFactory getStoryDiagramReconfigurationFactory() {
        return (StoryDiagramReconfigurationFactory)getEFactoryInstance();
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
        reconfigurationrulePackage thereconfigurationrulePackage = (reconfigurationrulePackage)EPackage.Registry.INSTANCE.getEPackage(reconfigurationrulePackage.eNS_URI);
        ActivitiesPackage theActivitiesPackage = (ActivitiesPackage)EPackage.Registry.INSTANCE.getEPackage(ActivitiesPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        EGenericType g1 = createEGenericType(thereconfigurationrulePackage.getModelTransformation());
        EGenericType g2 = createEGenericType(theActivitiesPackage.getActivity());
        g1.getETypeArguments().add(g2);
        storyDiagramActivityEClass.getEGenericSuperTypes().add(g1);

        // Initialize classes, features, and operations; add parameters
        initEClass(storyDiagramActivityEClass, StoryDiagramActivity.class, "StoryDiagramActivity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);
    }

} //StoryDiagramReconfigurationPackageImpl

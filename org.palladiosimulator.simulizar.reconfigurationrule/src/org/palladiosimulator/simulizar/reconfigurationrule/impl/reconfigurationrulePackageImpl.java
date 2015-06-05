/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;
import org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule;
import org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRuleSet;
import org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationruleFactory;
import org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class reconfigurationrulePackageImpl extends EPackageImpl implements reconfigurationrulePackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass reconfigurationRuleSetEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass reconfigurationRuleEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass modelTransformationEClass = null;

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
     * @see org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private reconfigurationrulePackageImpl() {
        super(eNS_URI, reconfigurationruleFactory.eINSTANCE);
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
     * <p>This method is used to initialize {@link reconfigurationrulePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static reconfigurationrulePackage init() {
        if (isInited) return (reconfigurationrulePackage)EPackage.Registry.INSTANCE.getEPackage(reconfigurationrulePackage.eNS_URI);

        // Obtain or create and register package
        reconfigurationrulePackageImpl thereconfigurationrulePackage = (reconfigurationrulePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof reconfigurationrulePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new reconfigurationrulePackageImpl());

        isInited = true;

        // Create package meta-data objects
        thereconfigurationrulePackage.createPackageContents();

        // Initialize created meta-data
        thereconfigurationrulePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thereconfigurationrulePackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(reconfigurationrulePackage.eNS_URI, thereconfigurationrulePackage);
        return thereconfigurationrulePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getReconfigurationRuleSet() {
        return reconfigurationRuleSetEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getReconfigurationRuleSet_Rules() {
        return (EReference)reconfigurationRuleSetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getReconfigurationRuleSet_Name() {
        return (EAttribute)reconfigurationRuleSetEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getReconfigurationRule() {
        return reconfigurationRuleEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getReconfigurationRule_Priority() {
        return (EAttribute)reconfigurationRuleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getReconfigurationRule_ConditionCheck() {
        return (EReference)reconfigurationRuleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getReconfigurationRule_TransformationAction() {
        return (EReference)reconfigurationRuleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getReconfigurationRule_Name() {
        return (EAttribute)reconfigurationRuleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getModelTransformation() {
        return modelTransformationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getModelTransformation_ModelTransformation() {
        return (EReference)modelTransformationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public reconfigurationruleFactory getreconfigurationruleFactory() {
        return (reconfigurationruleFactory)getEFactoryInstance();
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
        reconfigurationRuleSetEClass = createEClass(RECONFIGURATION_RULE_SET);
        createEReference(reconfigurationRuleSetEClass, RECONFIGURATION_RULE_SET__RULES);
        createEAttribute(reconfigurationRuleSetEClass, RECONFIGURATION_RULE_SET__NAME);

        reconfigurationRuleEClass = createEClass(RECONFIGURATION_RULE);
        createEAttribute(reconfigurationRuleEClass, RECONFIGURATION_RULE__PRIORITY);
        createEReference(reconfigurationRuleEClass, RECONFIGURATION_RULE__CONDITION_CHECK);
        createEReference(reconfigurationRuleEClass, RECONFIGURATION_RULE__TRANSFORMATION_ACTION);
        createEAttribute(reconfigurationRuleEClass, RECONFIGURATION_RULE__NAME);

        modelTransformationEClass = createEClass(MODEL_TRANSFORMATION);
        createEReference(modelTransformationEClass, MODEL_TRANSFORMATION__MODEL_TRANSFORMATION);
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

        // Create type parameters
        ETypeParameter modelTransformationEClass_ExecutableTransformationElement = addETypeParameter(modelTransformationEClass, "ExecutableTransformationElement");

        // Set bounds for type parameters
        EGenericType g1 = createEGenericType(ecorePackage.getEJavaObject());
        modelTransformationEClass_ExecutableTransformationElement.getEBounds().add(g1);

        // Add supertypes to classes

        // Initialize classes, features, and operations; add parameters
        initEClass(reconfigurationRuleSetEClass, ReconfigurationRuleSet.class, "ReconfigurationRuleSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getReconfigurationRuleSet_Rules(), this.getReconfigurationRule(), null, "rules", null, 0, -1, ReconfigurationRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getReconfigurationRuleSet_Name(), ecorePackage.getEString(), "name", null, 1, 1, ReconfigurationRuleSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(reconfigurationRuleEClass, ReconfigurationRule.class, "ReconfigurationRule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getReconfigurationRule_Priority(), ecorePackage.getEInt(), "priority", "-1", 1, 1, ReconfigurationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        g1 = createEGenericType(this.getModelTransformation());
        EGenericType g2 = createEGenericType();
        g1.getETypeArguments().add(g2);
        initEReference(getReconfigurationRule_ConditionCheck(), g1, null, "conditionCheck", null, 1, -1, ReconfigurationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        g1 = createEGenericType(this.getModelTransformation());
        g2 = createEGenericType();
        g1.getETypeArguments().add(g2);
        initEReference(getReconfigurationRule_TransformationAction(), g1, null, "transformationAction", null, 1, -1, ReconfigurationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getReconfigurationRule_Name(), ecorePackage.getEString(), "name", null, 1, 1, ReconfigurationRule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(modelTransformationEClass, ModelTransformation.class, "ModelTransformation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        g1 = createEGenericType(modelTransformationEClass_ExecutableTransformationElement);
        initEReference(getModelTransformation_ModelTransformation(), g1, null, "modelTransformation", null, 0, 1, ModelTransformation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //reconfigurationrulePackageImpl

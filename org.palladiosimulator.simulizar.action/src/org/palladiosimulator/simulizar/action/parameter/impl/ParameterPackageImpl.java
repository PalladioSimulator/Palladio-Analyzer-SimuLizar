/**
 */
package org.palladiosimulator.simulizar.action.parameter.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.palladiosimulator.pcm.PcmPackage;
import org.palladiosimulator.pcm.core.entity.EntityPackage;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl;
import org.palladiosimulator.simulizar.action.instance.InstancePackage;
import org.palladiosimulator.simulizar.action.instance.impl.InstancePackageImpl;
import org.palladiosimulator.simulizar.action.mapping.MappingPackage;
import org.palladiosimulator.simulizar.action.mapping.impl.MappingPackageImpl;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection;
import org.palladiosimulator.simulizar.action.parameter.ParameterFactory;
import org.palladiosimulator.simulizar.action.parameter.ParameterPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class ParameterPackageImpl extends EPackageImpl implements ParameterPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass controllerCallInputVariableUsageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass controllerCallInputVariableUsageCollectionEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI
     * value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init
     * init()}, which also performs initialization of the package, or returns the registered
     * package, if one already exists. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.palladiosimulator.simulizar.action.parameter.ParameterPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ParameterPackageImpl() {
        super(eNS_URI, ParameterFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others
     * upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link ParameterPackage#eINSTANCE} when that field is
     * accessed. Clients should not invoke it directly. Instead, they should simply access that
     * field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ParameterPackage init() {
        if (isInited) {
            return (ParameterPackage) EPackage.Registry.INSTANCE.getEPackage(ParameterPackage.eNS_URI);
        }

        // Obtain or create and register package
        final ParameterPackageImpl theParameterPackage = (ParameterPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof ParameterPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new ParameterPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EMFProfilePackage.eINSTANCE.eClass();
        PcmPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        final CorePackageImpl theCorePackage = (CorePackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
        final MappingPackageImpl theMappingPackage = (MappingPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(MappingPackage.eNS_URI) instanceof MappingPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(MappingPackage.eNS_URI) : MappingPackage.eINSTANCE);
        final InstancePackageImpl theInstancePackage = (InstancePackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(InstancePackage.eNS_URI) instanceof InstancePackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(InstancePackage.eNS_URI) : InstancePackage.eINSTANCE);

        // Create package meta-data objects
        theParameterPackage.createPackageContents();
        theCorePackage.createPackageContents();
        theMappingPackage.createPackageContents();
        theInstancePackage.createPackageContents();

        // Initialize created meta-data
        theParameterPackage.initializePackageContents();
        theCorePackage.initializePackageContents();
        theMappingPackage.initializePackageContents();
        theInstancePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theParameterPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ParameterPackage.eNS_URI, theParameterPackage);
        return theParameterPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getControllerCallInputVariableUsage() {
        return this.controllerCallInputVariableUsageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getControllerCallInputVariableUsage_VariableUsage() {
        return (EReference) this.controllerCallInputVariableUsageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getControllerCallInputVariableUsage_CorrespondingControllerCall() {
        return (EReference) this.controllerCallInputVariableUsageEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getControllerCallInputVariableUsage_ContainingCollection() {
        return (EReference) this.controllerCallInputVariableUsageEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getControllerCallInputVariableUsageCollection() {
        return this.controllerCallInputVariableUsageCollectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getControllerCallInputVariableUsageCollection_ControllerCallInputVariableUsages() {
        return (EReference) this.controllerCallInputVariableUsageCollectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ParameterFactory getParameterFactory() {
        return (ParameterFactory) this.getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on
     * any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (this.isCreated) {
            return;
        }
        this.isCreated = true;

        // Create classes and their features
        this.controllerCallInputVariableUsageEClass = this.createEClass(CONTROLLER_CALL_INPUT_VARIABLE_USAGE);
        this.createEReference(this.controllerCallInputVariableUsageEClass,
                CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE);
        this.createEReference(this.controllerCallInputVariableUsageEClass,
                CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL);
        this.createEReference(this.controllerCallInputVariableUsageEClass,
                CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION);

        this.controllerCallInputVariableUsageCollectionEClass = this
                .createEClass(CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION);
        this.createEReference(this.controllerCallInputVariableUsageCollectionEClass,
                CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have
     * no affect on any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;

        // Initialize package
        this.setName(eNAME);
        this.setNsPrefix(eNS_PREFIX);
        this.setNsURI(eNS_URI);

        // Obtain other dependent packages
        final EntityPackage theEntityPackage = (EntityPackage) EPackage.Registry.INSTANCE
                .getEPackage(EntityPackage.eNS_URI);
        final org.palladiosimulator.pcm.parameter.ParameterPackage theParameterPackage_1 = (org.palladiosimulator.pcm.parameter.ParameterPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.palladiosimulator.pcm.parameter.ParameterPackage.eNS_URI);
        final CorePackage theCorePackage = (CorePackage) EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        this.controllerCallInputVariableUsageEClass.getESuperTypes().add(theEntityPackage.getEntity());
        this.controllerCallInputVariableUsageCollectionEClass.getESuperTypes().add(theEntityPackage.getEntity());

        // Initialize classes and features; add operations and parameters
        this.initEClass(this.controllerCallInputVariableUsageEClass, ControllerCallInputVariableUsage.class,
                "ControllerCallInputVariableUsage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getControllerCallInputVariableUsage_VariableUsage(),
                theParameterPackage_1.getVariableUsage(), null, "variableUsage", null, 1, 1,
                ControllerCallInputVariableUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getControllerCallInputVariableUsage_CorrespondingControllerCall(),
                theCorePackage.getControllerCall(), null, "correspondingControllerCall", null, 1, 1,
                ControllerCallInputVariableUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getControllerCallInputVariableUsage_ContainingCollection(),
                this.getControllerCallInputVariableUsageCollection(),
                this.getControllerCallInputVariableUsageCollection_ControllerCallInputVariableUsages(),
                "containingCollection", null, 0, 1, ControllerCallInputVariableUsage.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.controllerCallInputVariableUsageCollectionEClass,
                ControllerCallInputVariableUsageCollection.class, "ControllerCallInputVariableUsageCollection",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getControllerCallInputVariableUsageCollection_ControllerCallInputVariableUsages(),
                this.getControllerCallInputVariableUsage(),
                this.getControllerCallInputVariableUsage_ContainingCollection(), "controllerCallInputVariableUsages",
                null, 0, -1, ControllerCallInputVariableUsageCollection.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        this.createResource(eNS_URI);
    }

} // ParameterPackageImpl

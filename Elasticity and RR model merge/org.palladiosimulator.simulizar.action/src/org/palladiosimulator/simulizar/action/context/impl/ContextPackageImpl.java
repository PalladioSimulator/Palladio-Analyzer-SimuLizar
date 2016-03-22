/**
 */
package org.palladiosimulator.simulizar.action.context.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
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

import de.uka.ipd.sdq.identifier.IdentifierPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class ContextPackageImpl extends EPackageImpl implements ContextPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass executionContextEClass = null;

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
     * @see org.palladiosimulator.simulizar.action.context.ContextPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ContextPackageImpl() {
        super(eNS_URI, ContextFactory.eINSTANCE);
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
     * This method is used to initialize {@link ContextPackage#eINSTANCE} when that field is
     * accessed. Clients should not invoke it directly. Instead, they should simply access that
     * field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ContextPackage init() {
        if (isInited) {
            return (ContextPackage) EPackage.Registry.INSTANCE.getEPackage(ContextPackage.eNS_URI);
        }

        // Obtain or create and register package
        final ContextPackageImpl theContextPackage = (ContextPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof ContextPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new ContextPackageImpl());

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
        final ParameterPackageImpl theParameterPackage = (ParameterPackageImpl) (EPackage.Registry.INSTANCE
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getExecutionContext() {
        return this.executionContextEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContextFactory getContextFactory() {
        return (ContextFactory) this.getEFactoryInstance();
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
        this.executionContextEClass = this.createEClass(EXECUTION_CONTEXT);
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
        final IdentifierPackage theIdentifierPackage = (IdentifierPackage) EPackage.Registry.INSTANCE
                .getEPackage(IdentifierPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        this.executionContextEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());

        // Initialize classes and features; add operations and parameters
        this.initEClass(this.executionContextEClass, ExecutionContext.class, "ExecutionContext", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        this.createResource(eNS_URI);
    }

} // ContextPackageImpl

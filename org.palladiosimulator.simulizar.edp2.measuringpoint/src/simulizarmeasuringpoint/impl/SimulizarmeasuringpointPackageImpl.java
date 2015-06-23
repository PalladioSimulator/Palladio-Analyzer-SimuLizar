/**
 */
package simulizarmeasuringpoint.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage;
import org.palladiosimulator.edp2.models.Repository.RepositoryPackage;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointPackage;

import simulizarmeasuringpoint.ReconfigurationMeasuringPoint;
import simulizarmeasuringpoint.SimulizarmeasuringpointFactory;
import simulizarmeasuringpoint.SimulizarmeasuringpointPackage;
import org.palladiosimulator.pcm.PcmPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class SimulizarmeasuringpointPackageImpl extends EPackageImpl implements SimulizarmeasuringpointPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass reconfigurationMeasuringPointEClass = null;

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
     * @see simulizarmeasuringpoint.SimulizarmeasuringpointPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private SimulizarmeasuringpointPackageImpl() {
        super(eNS_URI, SimulizarmeasuringpointFactory.eINSTANCE);
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
     * This method is used to initialize {@link SimulizarmeasuringpointPackage#eINSTANCE} when that
     * field is accessed. Clients should not invoke it directly. Instead, they should simply access
     * that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static SimulizarmeasuringpointPackage init() {
        if (isInited) {
            return (SimulizarmeasuringpointPackage) EPackage.Registry.INSTANCE
                    .getEPackage(SimulizarmeasuringpointPackage.eNS_URI);
        }

        // Obtain or create and register package
        final SimulizarmeasuringpointPackageImpl theSimulizarmeasuringpointPackage = (SimulizarmeasuringpointPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof SimulizarmeasuringpointPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new SimulizarmeasuringpointPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        ExperimentDataPackage.eINSTANCE.eClass();
        RepositoryPackage.eINSTANCE.eClass();
        MeasuringpointPackage.eINSTANCE.eClass();
        PcmPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theSimulizarmeasuringpointPackage.createPackageContents();

        // Initialize created meta-data
        theSimulizarmeasuringpointPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theSimulizarmeasuringpointPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(SimulizarmeasuringpointPackage.eNS_URI, theSimulizarmeasuringpointPackage);
        return theSimulizarmeasuringpointPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getReconfigurationMeasuringPoint() {
        return this.reconfigurationMeasuringPointEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SimulizarmeasuringpointFactory getSimulizarmeasuringpointFactory() {
        return (SimulizarmeasuringpointFactory) this.getEFactoryInstance();
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
        this.reconfigurationMeasuringPointEClass = this.createEClass(RECONFIGURATION_MEASURING_POINT);
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
        final MeasuringpointPackage theMeasuringpointPackage = (MeasuringpointPackage) EPackage.Registry.INSTANCE
                .getEPackage(MeasuringpointPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        this.reconfigurationMeasuringPointEClass.getESuperTypes().add(
                theMeasuringpointPackage.getResourceURIMeasuringPoint());

        // Initialize classes and features; add operations and parameters
        this.initEClass(this.reconfigurationMeasuringPointEClass, ReconfigurationMeasuringPoint.class,
                "ReconfigurationMeasuringPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        this.createResource(eNS_URI);
    }

} // SimulizarmeasuringpointPackageImpl

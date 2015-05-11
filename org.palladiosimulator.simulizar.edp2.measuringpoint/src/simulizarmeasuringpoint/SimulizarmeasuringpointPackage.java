/**
 */
package simulizarmeasuringpoint;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta
 * objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see simulizarmeasuringpoint.SimulizarmeasuringpointFactory
 * @model kind="package"
 * @generated
 */
public interface SimulizarmeasuringpointPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "simulizarmeasuringpoint";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://palladiosimulator.org/simulizar/measuringpoint";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "simulizarmeasuringpoint";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    SimulizarmeasuringpointPackage eINSTANCE = simulizarmeasuringpoint.impl.SimulizarmeasuringpointPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link simulizarmeasuringpoint.impl.ReconfigurationMeasuringPointImpl
     * <em>Reconfiguration Measuring Point</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see simulizarmeasuringpoint.impl.ReconfigurationMeasuringPointImpl
     * @see simulizarmeasuringpoint.impl.SimulizarmeasuringpointPackageImpl#getReconfigurationMeasuringPoint()
     * @generated
     */
    int RECONFIGURATION_MEASURING_POINT = 0;

    /**
     * The feature id for the '<em><b>Measuring Point Repository</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONFIGURATION_MEASURING_POINT__MEASURING_POINT_REPOSITORY = MeasuringpointPackage.STRING_MEASURING_POINT__MEASURING_POINT_REPOSITORY;

    /**
     * The feature id for the '<em><b>String Representation</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONFIGURATION_MEASURING_POINT__STRING_REPRESENTATION = MeasuringpointPackage.STRING_MEASURING_POINT__STRING_REPRESENTATION;

    /**
     * The feature id for the '<em><b>Resource URI Representation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONFIGURATION_MEASURING_POINT__RESOURCE_URI_REPRESENTATION = MeasuringpointPackage.STRING_MEASURING_POINT__RESOURCE_URI_REPRESENTATION;

    /**
     * The feature id for the '<em><b>Measuring Point</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONFIGURATION_MEASURING_POINT__MEASURING_POINT = MeasuringpointPackage.STRING_MEASURING_POINT__MEASURING_POINT;

    /**
     * The number of structural features of the '<em>Reconfiguration Measuring Point</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONFIGURATION_MEASURING_POINT_FEATURE_COUNT = MeasuringpointPackage.STRING_MEASURING_POINT_FEATURE_COUNT + 0;

    /**
     * Returns the meta object for class '
     * {@link simulizarmeasuringpoint.ReconfigurationMeasuringPoint
     * <em>Reconfiguration Measuring Point</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Reconfiguration Measuring Point</em>'.
     * @see simulizarmeasuringpoint.ReconfigurationMeasuringPoint
     * @generated
     */
    EClass getReconfigurationMeasuringPoint();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    SimulizarmeasuringpointFactory getSimulizarmeasuringpointFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link simulizarmeasuringpoint.impl.ReconfigurationMeasuringPointImpl
         * <em>Reconfiguration Measuring Point</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see simulizarmeasuringpoint.impl.ReconfigurationMeasuringPointImpl
         * @see simulizarmeasuringpoint.impl.SimulizarmeasuringpointPackageImpl#getReconfigurationMeasuringPoint()
         * @generated
         */
        EClass RECONFIGURATION_MEASURING_POINT = eINSTANCE.getReconfigurationMeasuringPoint();

    }

} // SimulizarmeasuringpointPackage

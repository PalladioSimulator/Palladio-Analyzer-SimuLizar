/**
 */
package org.palladiosimulator.runtimemeasurement;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

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
 * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurementFactory
 * @model kind="package"
 * @generated
 */
public interface RuntimeMeasurementPackage extends EPackage {

    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "runtimemeasurement";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://simulizar.palladiosimulator.org/RuntimeMeasurement/1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "srm";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    RuntimeMeasurementPackage eINSTANCE = org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementPackageImpl
            .init();

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementModelImpl
     * <em>Model</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementModelImpl
     * @see org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementPackageImpl#getRuntimeMeasurementModel()
     * @generated
     */
    int RUNTIME_MEASUREMENT_MODEL = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RUNTIME_MEASUREMENT_MODEL__ID = IdentifierPackage.IDENTIFIER__ID;

    /**
     * The feature id for the '<em><b>Measurements</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RUNTIME_MEASUREMENT_MODEL__MEASUREMENTS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Model</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RUNTIME_MEASUREMENT_MODEL_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementImpl
     * <em>Runtime Measurement</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementImpl
     * @see org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementPackageImpl#getRuntimeMeasurement()
     * @generated
     */
    int RUNTIME_MEASUREMENT = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RUNTIME_MEASUREMENT__ID = IdentifierPackage.IDENTIFIER__ID;

    /**
     * The feature id for the '<em><b>Measuring Point</b></em>' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RUNTIME_MEASUREMENT__MEASURING_POINT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Measurement Specification</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RUNTIME_MEASUREMENT__MEASUREMENT_SPECIFICATION = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Measuring Value</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RUNTIME_MEASUREMENT__MEASURING_VALUE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Runtime Measurement</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RUNTIME_MEASUREMENT_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 3;

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel <em>Model</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Model</em>'.
     * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel
     * @generated
     */
    EClass getRuntimeMeasurementModel();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel#getMeasurements
     * <em>Measurements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Measurements</em>'.
     * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel#getMeasurements()
     * @see #getRuntimeMeasurementModel()
     * @generated
     */
    EReference getRuntimeMeasurementModel_Measurements();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.runtimemeasurement.RuntimeMeasurement
     * <em>Runtime Measurement</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Runtime Measurement</em>'.
     * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurement
     * @generated
     */
    EClass getRuntimeMeasurement();

    /**
     * Returns the meta object for the reference '
     * {@link org.palladiosimulator.runtimemeasurement.RuntimeMeasurement#getMeasuringPoint
     * <em>Measuring Point</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Measuring Point</em>'.
     * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurement#getMeasuringPoint()
     * @see #getRuntimeMeasurement()
     * @generated
     */
    EReference getRuntimeMeasurement_MeasuringPoint();

    /**
     * Returns the meta object for the reference '
     * {@link org.palladiosimulator.runtimemeasurement.RuntimeMeasurement#getMeasurementSpecification
     * <em>Measurement Specification</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Measurement Specification</em>'.
     * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurement#getMeasurementSpecification()
     * @see #getRuntimeMeasurement()
     * @generated
     */
    EReference getRuntimeMeasurement_MeasurementSpecification();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.runtimemeasurement.RuntimeMeasurement#getMeasuringValue
     * <em>Measuring Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Measuring Value</em>'.
     * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurement#getMeasuringValue()
     * @see #getRuntimeMeasurement()
     * @generated
     */
    EAttribute getRuntimeMeasurement_MeasuringValue();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    RuntimeMeasurementFactory getRuntimeMeasurementFactory();

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
         * {@link org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementModelImpl
         * <em>Model</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementModelImpl
         * @see org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementPackageImpl#getRuntimeMeasurementModel()
         * @generated
         */
        EClass RUNTIME_MEASUREMENT_MODEL = eINSTANCE.getRuntimeMeasurementModel();

        /**
         * The meta object literal for the '<em><b>Measurements</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RUNTIME_MEASUREMENT_MODEL__MEASUREMENTS = eINSTANCE.getRuntimeMeasurementModel_Measurements();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementImpl
         * <em>Runtime Measurement</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementImpl
         * @see org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementPackageImpl#getRuntimeMeasurement()
         * @generated
         */
        EClass RUNTIME_MEASUREMENT = eINSTANCE.getRuntimeMeasurement();

        /**
         * The meta object literal for the '<em><b>Measuring Point</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RUNTIME_MEASUREMENT__MEASURING_POINT = eINSTANCE.getRuntimeMeasurement_MeasuringPoint();

        /**
         * The meta object literal for the '<em><b>Measurement Specification</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RUNTIME_MEASUREMENT__MEASUREMENT_SPECIFICATION = eINSTANCE
                .getRuntimeMeasurement_MeasurementSpecification();

        /**
         * The meta object literal for the '<em><b>Measuring Value</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute RUNTIME_MEASUREMENT__MEASURING_VALUE = eINSTANCE.getRuntimeMeasurement_MeasuringValue();

    }

} // RuntimeMeasurementPackage

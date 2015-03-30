/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.prm;

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
 * @see org.palladiosimulator.simulizar.prm.PrmFactory
 * @model kind="package"
 * @generated
 */
public interface PrmPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "prm";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://simulizar.palladiosimulator.org/PalladioRuntimeMonitoring/1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "prm";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    PrmPackage eINSTANCE = org.palladiosimulator.simulizar.prm.impl.PrmPackageImpl.init();

    /**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.prm.impl.PRMModelImpl
     * <em>PRM Model</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.prm.impl.PRMModelImpl
     * @see org.palladiosimulator.simulizar.prm.impl.PrmPackageImpl#getPRMModel()
     * @generated
     */
    int PRM_MODEL = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PRM_MODEL__ID = IdentifierPackage.IDENTIFIER__ID;

    /**
     * The feature id for the '<em><b>Measurements</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PRM_MODEL__MEASUREMENTS = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>PRM Model</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PRM_MODEL_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.prm.impl.PRMMeasurementImpl <em>PRM Measurement</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.prm.impl.PRMMeasurementImpl
     * @see org.palladiosimulator.simulizar.prm.impl.PrmPackageImpl#getPRMMeasurement()
     * @generated
     */
    int PRM_MEASUREMENT = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PRM_MEASUREMENT__ID = IdentifierPackage.IDENTIFIER__ID;

    /**
     * The feature id for the '<em><b>Measuring Point</b></em>' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PRM_MEASUREMENT__MEASURING_POINT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Measurement Specification</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PRM_MEASUREMENT__MEASUREMENT_SPECIFICATION = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Measuring Value</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PRM_MEASUREMENT__MEASURING_VALUE = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>PRM Measurement</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PRM_MEASUREMENT_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 3;

    /**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.prm.PRMModel
     * <em>PRM Model</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>PRM Model</em>'.
     * @see org.palladiosimulator.simulizar.prm.PRMModel
     * @generated
     */
    EClass getPRMModel();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.palladiosimulator.simulizar.prm.PRMModel#getMeasurements <em>Measurements</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Measurements</em>'.
     * @see org.palladiosimulator.simulizar.prm.PRMModel#getMeasurements()
     * @see #getPRMModel()
     * @generated
     */
    EReference getPRMModel_Measurements();

    /**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.prm.PRMMeasurement
     * <em>PRM Measurement</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>PRM Measurement</em>'.
     * @see org.palladiosimulator.simulizar.prm.PRMMeasurement
     * @generated
     */
    EClass getPRMMeasurement();

    /**
     * Returns the meta object for the reference '
     * {@link org.palladiosimulator.simulizar.prm.PRMMeasurement#getMeasuringPoint
     * <em>Measuring Point</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Measuring Point</em>'.
     * @see org.palladiosimulator.simulizar.prm.PRMMeasurement#getMeasuringPoint()
     * @see #getPRMMeasurement()
     * @generated
     */
    EReference getPRMMeasurement_MeasuringPoint();

    /**
     * Returns the meta object for the reference '
     * {@link org.palladiosimulator.simulizar.prm.PRMMeasurement#getMeasurementSpecification
     * <em>Measurement Specification</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Measurement Specification</em>'.
     * @see org.palladiosimulator.simulizar.prm.PRMMeasurement#getMeasurementSpecification()
     * @see #getPRMMeasurement()
     * @generated
     */
    EReference getPRMMeasurement_MeasurementSpecification();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.prm.PRMMeasurement#getMeasuringValue
     * <em>Measuring Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Measuring Value</em>'.
     * @see org.palladiosimulator.simulizar.prm.PRMMeasurement#getMeasuringValue()
     * @see #getPRMMeasurement()
     * @generated
     */
    EAttribute getPRMMeasurement_MeasuringValue();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    PrmFactory getPrmFactory();

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
         * {@link org.palladiosimulator.simulizar.prm.impl.PRMModelImpl <em>PRM Model</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.prm.impl.PRMModelImpl
         * @see org.palladiosimulator.simulizar.prm.impl.PrmPackageImpl#getPRMModel()
         * @generated
         */
        EClass PRM_MODEL = eINSTANCE.getPRMModel();

        /**
         * The meta object literal for the '<em><b>Measurements</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference PRM_MODEL__MEASUREMENTS = eINSTANCE.getPRMModel_Measurements();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.prm.impl.PRMMeasurementImpl
         * <em>PRM Measurement</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.prm.impl.PRMMeasurementImpl
         * @see org.palladiosimulator.simulizar.prm.impl.PrmPackageImpl#getPRMMeasurement()
         * @generated
         */
        EClass PRM_MEASUREMENT = eINSTANCE.getPRMMeasurement();

        /**
         * The meta object literal for the '<em><b>Measuring Point</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference PRM_MEASUREMENT__MEASURING_POINT = eINSTANCE.getPRMMeasurement_MeasuringPoint();

        /**
         * The meta object literal for the '<em><b>Measurement Specification</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference PRM_MEASUREMENT__MEASUREMENT_SPECIFICATION = eINSTANCE.getPRMMeasurement_MeasurementSpecification();

        /**
         * The meta object literal for the '<em><b>Measuring Value</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute PRM_MEASUREMENT__MEASURING_VALUE = eINSTANCE.getPRMMeasurement_MeasuringValue();

    }

} // PrmPackage

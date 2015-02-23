/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import de.uka.ipd.sdq.identifier.IdentifierPackage;
import de.uka.ipd.sdq.pcm.core.entity.EntityPackage;

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
 * @see org.palladiosimulator.simulizar.pms.PmsFactory
 * @model kind="package"
 * @generated
 */
public interface PmsPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "pms";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://simulizar.palladiosimulator.org/PalladioMonitoringSpecification/1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "pms";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    PmsPackage eINSTANCE = org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.pms.impl.MonitorRepositoryImpl
     * <em>Monitor Repository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.pms.impl.MonitorRepositoryImpl
     * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getMonitorRepository()
     * @generated
     */
    int MONITOR_REPOSITORY = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MONITOR_REPOSITORY__ID = EntityPackage.ENTITY__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MONITOR_REPOSITORY__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Monitors</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MONITOR_REPOSITORY__MONITORS = EntityPackage.ENTITY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Monitor Repository</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MONITOR_REPOSITORY_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.pms.impl.MonitorImpl
     * <em>Monitor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.pms.impl.MonitorImpl
     * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getMonitor()
     * @generated
     */
    int MONITOR = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MONITOR__ID = EntityPackage.ENTITY__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MONITOR__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Measurement Specification</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MONITOR__MEASUREMENT_SPECIFICATION = EntityPackage.ENTITY_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Measuring Point</b></em>' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MONITOR__MEASURING_POINT = EntityPackage.ENTITY_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Monitor</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MONITOR_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.pms.impl.MeasurementSpecificationImpl
     * <em>Measurement Specification</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.pms.impl.MeasurementSpecificationImpl
     * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getMeasurementSpecification()
     * @generated
     */
    int MEASUREMENT_SPECIFICATION = 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MEASUREMENT_SPECIFICATION__ID = IdentifierPackage.IDENTIFIER__ID;

    /**
     * The feature id for the '<em><b>Temporal Restriction</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Statistical Characterization</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Metric Description</b></em>' reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MEASUREMENT_SPECIFICATION__METRIC_DESCRIPTION = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Measurement Specification</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MEASUREMENT_SPECIFICATION_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.pms.impl.TemporalCharacterizationImpl
     * <em>Temporal Characterization</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.pms.impl.TemporalCharacterizationImpl
     * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getTemporalCharacterization()
     * @generated
     */
    int TEMPORAL_CHARACTERIZATION = 3;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEMPORAL_CHARACTERIZATION__ID = IdentifierPackage.IDENTIFIER__ID;

    /**
     * The number of structural features of the '<em>Temporal Characterization</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEMPORAL_CHARACTERIZATION_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.pms.impl.IntervallImpl
     * <em>Intervall</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.pms.impl.IntervallImpl
     * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getIntervall()
     * @generated
     */
    int INTERVALL = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERVALL__ID = TEMPORAL_CHARACTERIZATION__ID;

    /**
     * The feature id for the '<em><b>Intervall</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERVALL__INTERVALL = TEMPORAL_CHARACTERIZATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Intervall</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERVALL_FEATURE_COUNT = TEMPORAL_CHARACTERIZATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.pms.impl.DelayedIntervallImpl
     * <em>Delayed Intervall</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.pms.impl.DelayedIntervallImpl
     * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getDelayedIntervall()
     * @generated
     */
    int DELAYED_INTERVALL = 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELAYED_INTERVALL__ID = INTERVALL__ID;

    /**
     * The feature id for the '<em><b>Intervall</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELAYED_INTERVALL__INTERVALL = INTERVALL__INTERVALL;

    /**
     * The feature id for the '<em><b>Delay</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELAYED_INTERVALL__DELAY = INTERVALL_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Delayed Intervall</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELAYED_INTERVALL_FEATURE_COUNT = INTERVALL_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.pms.impl.TimeFrameImpl
     * <em>Time Frame</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.pms.impl.TimeFrameImpl
     * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getTimeFrame()
     * @generated
     */
    int TIME_FRAME = 6;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TIME_FRAME__ID = TEMPORAL_CHARACTERIZATION__ID;

    /**
     * The feature id for the '<em><b>Start</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TIME_FRAME__START = TEMPORAL_CHARACTERIZATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Stop</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TIME_FRAME__STOP = TEMPORAL_CHARACTERIZATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Time Frame</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TIME_FRAME_FEATURE_COUNT = TEMPORAL_CHARACTERIZATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum
     * <em>Statistical Characterization Enum</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum
     * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getStatisticalCharacterizationEnum()
     * @generated
     */
    int STATISTICAL_CHARACTERIZATION_ENUM = 7;

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.pms.MonitorRepository <em>Monitor Repository</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Monitor Repository</em>'.
     * @see org.palladiosimulator.simulizar.pms.MonitorRepository
     * @generated
     */
    EClass getMonitorRepository();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.palladiosimulator.simulizar.pms.MonitorRepository#getMonitors <em>Monitors</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Monitors</em>'.
     * @see org.palladiosimulator.simulizar.pms.MonitorRepository#getMonitors()
     * @see #getMonitorRepository()
     * @generated
     */
    EReference getMonitorRepository_Monitors();

    /**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.pms.Monitor
     * <em>Monitor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Monitor</em>'.
     * @see org.palladiosimulator.simulizar.pms.Monitor
     * @generated
     */
    EClass getMonitor();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.palladiosimulator.simulizar.pms.Monitor#getMeasurementSpecification
     * <em>Measurement Specification</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Measurement Specification</em>'.
     * @see org.palladiosimulator.simulizar.pms.Monitor#getMeasurementSpecification()
     * @see #getMonitor()
     * @generated
     */
    EReference getMonitor_MeasurementSpecification();

    /**
     * Returns the meta object for the reference '
     * {@link org.palladiosimulator.simulizar.pms.Monitor#getMeasuringPoint
     * <em>Measuring Point</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Measuring Point</em>'.
     * @see org.palladiosimulator.simulizar.pms.Monitor#getMeasuringPoint()
     * @see #getMonitor()
     * @generated
     */
    EReference getMonitor_MeasuringPoint();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.pms.MeasurementSpecification
     * <em>Measurement Specification</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Measurement Specification</em>'.
     * @see org.palladiosimulator.simulizar.pms.MeasurementSpecification
     * @generated
     */
    EClass getMeasurementSpecification();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.palladiosimulator.simulizar.pms.MeasurementSpecification#getTemporalRestriction
     * <em>Temporal Restriction</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Temporal Restriction</em>'.
     * @see org.palladiosimulator.simulizar.pms.MeasurementSpecification#getTemporalRestriction()
     * @see #getMeasurementSpecification()
     * @generated
     */
    EReference getMeasurementSpecification_TemporalRestriction();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.pms.MeasurementSpecification#getStatisticalCharacterization
     * <em>Statistical Characterization</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Statistical Characterization</em>'.
     * @see org.palladiosimulator.simulizar.pms.MeasurementSpecification#getStatisticalCharacterization()
     * @see #getMeasurementSpecification()
     * @generated
     */
    EAttribute getMeasurementSpecification_StatisticalCharacterization();

    /**
     * Returns the meta object for the reference '
     * {@link org.palladiosimulator.simulizar.pms.MeasurementSpecification#getMetricDescription
     * <em>Metric Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Metric Description</em>'.
     * @see org.palladiosimulator.simulizar.pms.MeasurementSpecification#getMetricDescription()
     * @see #getMeasurementSpecification()
     * @generated
     */
    EReference getMeasurementSpecification_MetricDescription();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.pms.TemporalCharacterization
     * <em>Temporal Characterization</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Temporal Characterization</em>'.
     * @see org.palladiosimulator.simulizar.pms.TemporalCharacterization
     * @generated
     */
    EClass getTemporalCharacterization();

    /**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.pms.Intervall
     * <em>Intervall</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Intervall</em>'.
     * @see org.palladiosimulator.simulizar.pms.Intervall
     * @generated
     */
    EClass getIntervall();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.pms.Intervall#getIntervall <em>Intervall</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Intervall</em>'.
     * @see org.palladiosimulator.simulizar.pms.Intervall#getIntervall()
     * @see #getIntervall()
     * @generated
     */
    EAttribute getIntervall_Intervall();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.pms.DelayedIntervall <em>Delayed Intervall</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Delayed Intervall</em>'.
     * @see org.palladiosimulator.simulizar.pms.DelayedIntervall
     * @generated
     */
    EClass getDelayedIntervall();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.pms.DelayedIntervall#getDelay <em>Delay</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Delay</em>'.
     * @see org.palladiosimulator.simulizar.pms.DelayedIntervall#getDelay()
     * @see #getDelayedIntervall()
     * @generated
     */
    EAttribute getDelayedIntervall_Delay();

    /**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.pms.TimeFrame
     * <em>Time Frame</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Time Frame</em>'.
     * @see org.palladiosimulator.simulizar.pms.TimeFrame
     * @generated
     */
    EClass getTimeFrame();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.pms.TimeFrame#getStart <em>Start</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Start</em>'.
     * @see org.palladiosimulator.simulizar.pms.TimeFrame#getStart()
     * @see #getTimeFrame()
     * @generated
     */
    EAttribute getTimeFrame_Start();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.pms.TimeFrame#getStop <em>Stop</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Stop</em>'.
     * @see org.palladiosimulator.simulizar.pms.TimeFrame#getStop()
     * @see #getTimeFrame()
     * @generated
     */
    EAttribute getTimeFrame_Stop();

    /**
     * Returns the meta object for enum '
     * {@link org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum
     * <em>Statistical Characterization Enum</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Statistical Characterization Enum</em>'.
     * @see org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum
     * @generated
     */
    EEnum getStatisticalCharacterizationEnum();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    PmsFactory getPmsFactory();

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
         * {@link org.palladiosimulator.simulizar.pms.impl.MonitorRepositoryImpl
         * <em>Monitor Repository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.pms.impl.MonitorRepositoryImpl
         * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getMonitorRepository()
         * @generated
         */
        EClass MONITOR_REPOSITORY = eINSTANCE.getMonitorRepository();

        /**
         * The meta object literal for the '<em><b>Monitors</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MONITOR_REPOSITORY__MONITORS = eINSTANCE.getMonitorRepository_Monitors();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.pms.impl.MonitorImpl <em>Monitor</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.pms.impl.MonitorImpl
         * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getMonitor()
         * @generated
         */
        EClass MONITOR = eINSTANCE.getMonitor();

        /**
         * The meta object literal for the '<em><b>Measurement Specification</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MONITOR__MEASUREMENT_SPECIFICATION = eINSTANCE.getMonitor_MeasurementSpecification();

        /**
         * The meta object literal for the '<em><b>Measuring Point</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MONITOR__MEASURING_POINT = eINSTANCE.getMonitor_MeasuringPoint();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.pms.impl.MeasurementSpecificationImpl
         * <em>Measurement Specification</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.pms.impl.MeasurementSpecificationImpl
         * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getMeasurementSpecification()
         * @generated
         */
        EClass MEASUREMENT_SPECIFICATION = eINSTANCE.getMeasurementSpecification();

        /**
         * The meta object literal for the '<em><b>Temporal Restriction</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION = eINSTANCE
                .getMeasurementSpecification_TemporalRestriction();

        /**
         * The meta object literal for the '<em><b>Statistical Characterization</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION = eINSTANCE
                .getMeasurementSpecification_StatisticalCharacterization();

        /**
         * The meta object literal for the '<em><b>Metric Description</b></em>' reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MEASUREMENT_SPECIFICATION__METRIC_DESCRIPTION = eINSTANCE
                .getMeasurementSpecification_MetricDescription();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.pms.impl.TemporalCharacterizationImpl
         * <em>Temporal Characterization</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.pms.impl.TemporalCharacterizationImpl
         * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getTemporalCharacterization()
         * @generated
         */
        EClass TEMPORAL_CHARACTERIZATION = eINSTANCE.getTemporalCharacterization();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.pms.impl.IntervallImpl <em>Intervall</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.pms.impl.IntervallImpl
         * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getIntervall()
         * @generated
         */
        EClass INTERVALL = eINSTANCE.getIntervall();

        /**
         * The meta object literal for the '<em><b>Intervall</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute INTERVALL__INTERVALL = eINSTANCE.getIntervall_Intervall();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.pms.impl.DelayedIntervallImpl
         * <em>Delayed Intervall</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.pms.impl.DelayedIntervallImpl
         * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getDelayedIntervall()
         * @generated
         */
        EClass DELAYED_INTERVALL = eINSTANCE.getDelayedIntervall();

        /**
         * The meta object literal for the '<em><b>Delay</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DELAYED_INTERVALL__DELAY = eINSTANCE.getDelayedIntervall_Delay();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.pms.impl.TimeFrameImpl <em>Time Frame</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.pms.impl.TimeFrameImpl
         * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getTimeFrame()
         * @generated
         */
        EClass TIME_FRAME = eINSTANCE.getTimeFrame();

        /**
         * The meta object literal for the '<em><b>Start</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TIME_FRAME__START = eINSTANCE.getTimeFrame_Start();

        /**
         * The meta object literal for the '<em><b>Stop</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TIME_FRAME__STOP = eINSTANCE.getTimeFrame_Stop();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum
         * <em>Statistical Characterization Enum</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum
         * @see org.palladiosimulator.simulizar.pms.impl.PmsPackageImpl#getStatisticalCharacterizationEnum()
         * @generated
         */
        EEnum STATISTICAL_CHARACTERIZATION_ENUM = eINSTANCE.getStatisticalCharacterizationEnum();

    }

} // PmsPackage

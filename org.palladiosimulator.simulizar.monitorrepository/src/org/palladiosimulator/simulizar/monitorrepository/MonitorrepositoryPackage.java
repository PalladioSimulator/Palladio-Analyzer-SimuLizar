/**
 */
package org.palladiosimulator.simulizar.monitorrepository;

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
 * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryFactory
 * @model kind="package" annotation=
 *        "http://www.eclipse.org/OCL/Import ecore='http://www.eclipse.org/emf/2002/Ecore' edp2='../../../plugin/org.palladiosimulator.edp2/model/EDP2.ecore#//measuringpoint' entity='../../../plugin/de.uka.ipd.sdq.pcm/model/pcm.ecore#//core/entity' identifier='../../../plugin/de.uka.ipd.sdq.identifier/model/identifier.ecore#/' metricspec='../../../plugin/org.palladiosimulator.metricspec/model/metricspec.ecore#/'"
 *        annotation=
 *        "http://www.eclipse.org/emf/2002/Ecore invocationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL' settingDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL' validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL'"
 * @generated
 */
public interface MonitorrepositoryPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "monitorrepository";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://palladiosimulator.org/SimuLizar/MonitorRepository/1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "monitorrepository";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    MonitorrepositoryPackage eINSTANCE = org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl
            .init();

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.impl.MonitorRepositoryImpl
     * <em>Monitor Repository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorRepositoryImpl
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getMonitorRepository()
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
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.impl.MonitorImpl <em>Monitor</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorImpl
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getMonitor()
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
     * The feature id for the '<em><b>Measurement Specifications</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MONITOR__MEASUREMENT_SPECIFICATIONS = EntityPackage.ENTITY_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Measuring Point</b></em>' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MONITOR__MEASURING_POINT = EntityPackage.ENTITY_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Monitor Repository</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MONITOR__MONITOR_REPOSITORY = EntityPackage.ENTITY_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Monitor</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MONITOR_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.impl.MeasurementSpecificationImpl
     * <em>Measurement Specification</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.MeasurementSpecificationImpl
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getMeasurementSpecification()
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
     * The feature id for the '<em><b>Monitor</b></em>' container reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MEASUREMENT_SPECIFICATION__MONITOR = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MEASUREMENT_SPECIFICATION__NAME = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Measurement Specification</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MEASUREMENT_SPECIFICATION_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.impl.TemporalCharacterizationImpl
     * <em>Temporal Characterization</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.TemporalCharacterizationImpl
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getTemporalCharacterization()
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
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.impl.IntervallImpl
     * <em>Intervall</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.IntervallImpl
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getIntervall()
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
     * {@link org.palladiosimulator.simulizar.monitorrepository.impl.DelayedIntervallImpl
     * <em>Delayed Intervall</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.DelayedIntervallImpl
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getDelayedIntervall()
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
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.impl.TimeFrameImpl
     * <em>Time Frame</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.TimeFrameImpl
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getTimeFrame()
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
     * {@link org.palladiosimulator.simulizar.monitorrepository.StatisticalCharacterizationEnum
     * <em>Statistical Characterization Enum</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.palladiosimulator.simulizar.monitorrepository.StatisticalCharacterizationEnum
     * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getStatisticalCharacterizationEnum()
     * @generated
     */
    int STATISTICAL_CHARACTERIZATION_ENUM = 7;

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MonitorRepository
     * <em>Monitor Repository</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Monitor Repository</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorRepository
     * @generated
     */
    EClass getMonitorRepository();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MonitorRepository#getMonitors
     * <em>Monitors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Monitors</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorRepository#getMonitors()
     * @see #getMonitorRepository()
     * @generated
     */
    EReference getMonitorRepository_Monitors();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.monitorrepository.Monitor <em>Monitor</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Monitor</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.Monitor
     * @generated
     */
    EClass getMonitor();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.palladiosimulator.simulizar.monitorrepository.Monitor#getMeasurementSpecifications
     * <em>Measurement Specifications</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Measurement Specifications</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.Monitor#getMeasurementSpecifications()
     * @see #getMonitor()
     * @generated
     */
    EReference getMonitor_MeasurementSpecifications();

    /**
     * Returns the meta object for the reference '
     * {@link org.palladiosimulator.simulizar.monitorrepository.Monitor#getMeasuringPoint
     * <em>Measuring Point</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Measuring Point</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.Monitor#getMeasuringPoint()
     * @see #getMonitor()
     * @generated
     */
    EReference getMonitor_MeasuringPoint();

    /**
     * Returns the meta object for the container reference '
     * {@link org.palladiosimulator.simulizar.monitorrepository.Monitor#getMonitorRepository
     * <em>Monitor Repository</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the container reference '<em>Monitor Repository</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.Monitor#getMonitorRepository()
     * @see #getMonitor()
     * @generated
     */
    EReference getMonitor_MonitorRepository();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification
     * <em>Measurement Specification</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Measurement Specification</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification
     * @generated
     */
    EClass getMeasurementSpecification();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getTemporalRestriction
     * <em>Temporal Restriction</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Temporal Restriction</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getTemporalRestriction()
     * @see #getMeasurementSpecification()
     * @generated
     */
    EReference getMeasurementSpecification_TemporalRestriction();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getStatisticalCharacterization
     * <em>Statistical Characterization</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Statistical Characterization</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getStatisticalCharacterization()
     * @see #getMeasurementSpecification()
     * @generated
     */
    EAttribute getMeasurementSpecification_StatisticalCharacterization();

    /**
     * Returns the meta object for the reference '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getMetricDescription
     * <em>Metric Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Metric Description</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getMetricDescription()
     * @see #getMeasurementSpecification()
     * @generated
     */
    EReference getMeasurementSpecification_MetricDescription();

    /**
     * Returns the meta object for the container reference '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getMonitor
     * <em>Monitor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the container reference '<em>Monitor</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getMonitor()
     * @see #getMeasurementSpecification()
     * @generated
     */
    EReference getMeasurementSpecification_Monitor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getName()
     * @see #getMeasurementSpecification()
     * @generated
     */
    EAttribute getMeasurementSpecification_Name();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.monitorrepository.TemporalCharacterization
     * <em>Temporal Characterization</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Temporal Characterization</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.TemporalCharacterization
     * @generated
     */
    EClass getTemporalCharacterization();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.monitorrepository.Intervall <em>Intervall</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Intervall</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.Intervall
     * @generated
     */
    EClass getIntervall();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.monitorrepository.Intervall#getIntervall
     * <em>Intervall</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Intervall</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.Intervall#getIntervall()
     * @see #getIntervall()
     * @generated
     */
    EAttribute getIntervall_Intervall();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.monitorrepository.DelayedIntervall
     * <em>Delayed Intervall</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Delayed Intervall</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.DelayedIntervall
     * @generated
     */
    EClass getDelayedIntervall();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.monitorrepository.DelayedIntervall#getDelay
     * <em>Delay</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Delay</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.DelayedIntervall#getDelay()
     * @see #getDelayedIntervall()
     * @generated
     */
    EAttribute getDelayedIntervall_Delay();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.monitorrepository.TimeFrame <em>Time Frame</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Time Frame</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.TimeFrame
     * @generated
     */
    EClass getTimeFrame();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.monitorrepository.TimeFrame#getStart <em>Start</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Start</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.TimeFrame#getStart()
     * @see #getTimeFrame()
     * @generated
     */
    EAttribute getTimeFrame_Start();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.monitorrepository.TimeFrame#getStop <em>Stop</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Stop</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.TimeFrame#getStop()
     * @see #getTimeFrame()
     * @generated
     */
    EAttribute getTimeFrame_Stop();

    /**
     * Returns the meta object for enum '
     * {@link org.palladiosimulator.simulizar.monitorrepository.StatisticalCharacterizationEnum
     * <em>Statistical Characterization Enum</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Statistical Characterization Enum</em>'.
     * @see org.palladiosimulator.simulizar.monitorrepository.StatisticalCharacterizationEnum
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
    MonitorrepositoryFactory getMonitorrepositoryFactory();

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
         * {@link org.palladiosimulator.simulizar.monitorrepository.impl.MonitorRepositoryImpl
         * <em>Monitor Repository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorRepositoryImpl
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getMonitorRepository()
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
         * {@link org.palladiosimulator.simulizar.monitorrepository.impl.MonitorImpl
         * <em>Monitor</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorImpl
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getMonitor()
         * @generated
         */
        EClass MONITOR = eINSTANCE.getMonitor();

        /**
         * The meta object literal for the '<em><b>Measurement Specifications</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MONITOR__MEASUREMENT_SPECIFICATIONS = eINSTANCE.getMonitor_MeasurementSpecifications();

        /**
         * The meta object literal for the '<em><b>Measuring Point</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MONITOR__MEASURING_POINT = eINSTANCE.getMonitor_MeasuringPoint();

        /**
         * The meta object literal for the '<em><b>Monitor Repository</b></em>' container reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MONITOR__MONITOR_REPOSITORY = eINSTANCE.getMonitor_MonitorRepository();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.monitorrepository.impl.MeasurementSpecificationImpl
         * <em>Measurement Specification</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.MeasurementSpecificationImpl
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getMeasurementSpecification()
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
         * The meta object literal for the '<em><b>Monitor</b></em>' container reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MEASUREMENT_SPECIFICATION__MONITOR = eINSTANCE.getMeasurementSpecification_Monitor();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute MEASUREMENT_SPECIFICATION__NAME = eINSTANCE.getMeasurementSpecification_Name();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.monitorrepository.impl.TemporalCharacterizationImpl
         * <em>Temporal Characterization</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.TemporalCharacterizationImpl
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getTemporalCharacterization()
         * @generated
         */
        EClass TEMPORAL_CHARACTERIZATION = eINSTANCE.getTemporalCharacterization();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.monitorrepository.impl.IntervallImpl
         * <em>Intervall</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.IntervallImpl
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getIntervall()
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
         * {@link org.palladiosimulator.simulizar.monitorrepository.impl.DelayedIntervallImpl
         * <em>Delayed Intervall</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.DelayedIntervallImpl
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getDelayedIntervall()
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
         * {@link org.palladiosimulator.simulizar.monitorrepository.impl.TimeFrameImpl
         * <em>Time Frame</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.TimeFrameImpl
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getTimeFrame()
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
         * {@link org.palladiosimulator.simulizar.monitorrepository.StatisticalCharacterizationEnum
         * <em>Statistical Characterization Enum</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.monitorrepository.StatisticalCharacterizationEnum
         * @see org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryPackageImpl#getStatisticalCharacterizationEnum()
         * @generated
         */
        EEnum STATISTICAL_CHARACTERIZATION_ENUM = eINSTANCE.getStatisticalCharacterizationEnum();

    }

} // MonitorrepositoryPackage

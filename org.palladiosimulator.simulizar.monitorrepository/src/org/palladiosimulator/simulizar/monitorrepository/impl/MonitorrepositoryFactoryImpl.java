/**
 */
package org.palladiosimulator.simulizar.monitorrepository.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.palladiosimulator.simulizar.monitorrepository.DelayedIntervall;
import org.palladiosimulator.simulizar.monitorrepository.Intervall;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.Monitor;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryFactory;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;
import org.palladiosimulator.simulizar.monitorrepository.StatisticalCharacterizationEnum;
import org.palladiosimulator.simulizar.monitorrepository.TimeFrame;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class MonitorrepositoryFactoryImpl extends EFactoryImpl implements MonitorrepositoryFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static MonitorrepositoryFactory init() {
        try {
            final MonitorrepositoryFactory theMonitorrepositoryFactory = (MonitorrepositoryFactory) EPackage.Registry.INSTANCE
                    .getEFactory(MonitorrepositoryPackage.eNS_URI);
            if (theMonitorrepositoryFactory != null) {
                return theMonitorrepositoryFactory;
            }
        } catch (final Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new MonitorrepositoryFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MonitorrepositoryFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(final EClass eClass) {
        switch (eClass.getClassifierID()) {
        case MonitorrepositoryPackage.MONITOR_REPOSITORY:
            return this.createMonitorRepository();
        case MonitorrepositoryPackage.MONITOR:
            return this.createMonitor();
        case MonitorrepositoryPackage.MEASUREMENT_SPECIFICATION:
            return this.createMeasurementSpecification();
        case MonitorrepositoryPackage.INTERVALL:
            return this.createIntervall();
        case MonitorrepositoryPackage.DELAYED_INTERVALL:
            return this.createDelayedIntervall();
        case MonitorrepositoryPackage.TIME_FRAME:
            return this.createTimeFrame();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object createFromString(final EDataType eDataType, final String initialValue) {
        switch (eDataType.getClassifierID()) {
        case MonitorrepositoryPackage.STATISTICAL_CHARACTERIZATION_ENUM:
            return this.createStatisticalCharacterizationEnumFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String convertToString(final EDataType eDataType, final Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case MonitorrepositoryPackage.STATISTICAL_CHARACTERIZATION_ENUM:
            return this.convertStatisticalCharacterizationEnumToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MonitorRepository createMonitorRepository() {
        final MonitorRepositoryImpl monitorRepository = new MonitorRepositoryImpl();
        return monitorRepository;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Monitor createMonitor() {
        final MonitorImpl monitor = new MonitorImpl();
        return monitor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MeasurementSpecification createMeasurementSpecification() {
        final MeasurementSpecificationImpl measurementSpecification = new MeasurementSpecificationImpl();
        return measurementSpecification;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Intervall createIntervall() {
        final IntervallImpl intervall = new IntervallImpl();
        return intervall;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DelayedIntervall createDelayedIntervall() {
        final DelayedIntervallImpl delayedIntervall = new DelayedIntervallImpl();
        return delayedIntervall;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TimeFrame createTimeFrame() {
        final TimeFrameImpl timeFrame = new TimeFrameImpl();
        return timeFrame;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public StatisticalCharacterizationEnum createStatisticalCharacterizationEnumFromString(final EDataType eDataType,
            final String initialValue) {
        final StatisticalCharacterizationEnum result = StatisticalCharacterizationEnum.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                    + eDataType.getName() + "'");
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertStatisticalCharacterizationEnumToString(final EDataType eDataType, final Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MonitorrepositoryPackage getMonitorrepositoryPackage() {
        return (MonitorrepositoryPackage) this.getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static MonitorrepositoryPackage getPackage() {
        return MonitorrepositoryPackage.eINSTANCE;
    }

} // MonitorrepositoryFactoryImpl

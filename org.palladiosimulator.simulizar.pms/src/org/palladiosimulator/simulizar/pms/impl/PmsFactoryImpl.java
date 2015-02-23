/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.palladiosimulator.simulizar.pms.DelayedIntervall;
import org.palladiosimulator.simulizar.pms.Intervall;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.Monitor;
import org.palladiosimulator.simulizar.pms.MonitorRepository;
import org.palladiosimulator.simulizar.pms.PmsFactory;
import org.palladiosimulator.simulizar.pms.PmsPackage;
import org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum;
import org.palladiosimulator.simulizar.pms.TimeFrame;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class PmsFactoryImpl extends EFactoryImpl implements PmsFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static PmsFactory init() {
        try {
            final PmsFactory thePmsFactory = (PmsFactory) EPackage.Registry.INSTANCE.getEFactory(PmsPackage.eNS_URI);
            if (thePmsFactory != null) {
                return thePmsFactory;
            }
        } catch (final Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new PmsFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public PmsFactoryImpl() {
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
        case PmsPackage.MONITOR_REPOSITORY:
            return this.createMonitorRepository();
        case PmsPackage.MONITOR:
            return this.createMonitor();
        case PmsPackage.MEASUREMENT_SPECIFICATION:
            return this.createMeasurementSpecification();
        case PmsPackage.INTERVALL:
            return this.createIntervall();
        case PmsPackage.DELAYED_INTERVALL:
            return this.createDelayedIntervall();
        case PmsPackage.TIME_FRAME:
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
        case PmsPackage.STATISTICAL_CHARACTERIZATION_ENUM:
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
        case PmsPackage.STATISTICAL_CHARACTERIZATION_ENUM:
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
    public PmsPackage getPmsPackage() {
        return (PmsPackage) this.getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static PmsPackage getPackage() {
        return PmsPackage.eINSTANCE;
    }

} // PmsFactoryImpl

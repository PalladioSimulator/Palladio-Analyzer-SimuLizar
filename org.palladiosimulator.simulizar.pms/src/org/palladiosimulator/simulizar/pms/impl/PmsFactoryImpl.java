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
import org.palladiosimulator.simulizar.pms.*;
import org.palladiosimulator.simulizar.pms.DelayedIntervall;
import org.palladiosimulator.simulizar.pms.Intervall;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.pms.PerformanceMeasurement;
import org.palladiosimulator.simulizar.pms.PerformanceMetricEnum;
import org.palladiosimulator.simulizar.pms.PmsFactory;
import org.palladiosimulator.simulizar.pms.PmsPackage;
import org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum;
import org.palladiosimulator.simulizar.pms.TimeFrame;
import org.palladiosimulator.simulizar.pms.UniqueElement;

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
            PmsFactory thePmsFactory = (PmsFactory) EPackage.Registry.INSTANCE.getEFactory(PmsPackage.eNS_URI);
            if (thePmsFactory != null) {
                return thePmsFactory;
            }
        } catch (Exception exception) {
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
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case PmsPackage.PMS_MODEL:
            return createPMSModel();
        case PmsPackage.PERFORMANCE_MEASUREMENT:
            return createPerformanceMeasurement();
        case PmsPackage.MEASUREMENT_SPECIFICATION:
            return createMeasurementSpecification();
        case PmsPackage.INTERVALL:
            return createIntervall();
        case PmsPackage.DELAYED_INTERVALL:
            return createDelayedIntervall();
        case PmsPackage.TIME_FRAME:
            return createTimeFrame();
        case PmsPackage.UNIQUE_ELEMENT:
            return createUniqueElement();
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
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
        case PmsPackage.PERFORMANCE_METRIC_ENUM:
            return createPerformanceMetricEnumFromString(eDataType, initialValue);
        case PmsPackage.STATISTICAL_CHARACTERIZATION_ENUM:
            return createStatisticalCharacterizationEnumFromString(eDataType, initialValue);
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
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case PmsPackage.PERFORMANCE_METRIC_ENUM:
            return convertPerformanceMetricEnumToString(eDataType, instanceValue);
        case PmsPackage.STATISTICAL_CHARACTERIZATION_ENUM:
            return convertStatisticalCharacterizationEnumToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PMSModel createPMSModel() {
        PMSModelImpl pmsModel = new PMSModelImpl();
        return pmsModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PerformanceMeasurement createPerformanceMeasurement() {
        PerformanceMeasurementImpl performanceMeasurement = new PerformanceMeasurementImpl();
        return performanceMeasurement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MeasurementSpecification createMeasurementSpecification() {
        MeasurementSpecificationImpl measurementSpecification = new MeasurementSpecificationImpl();
        return measurementSpecification;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Intervall createIntervall() {
        IntervallImpl intervall = new IntervallImpl();
        return intervall;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DelayedIntervall createDelayedIntervall() {
        DelayedIntervallImpl delayedIntervall = new DelayedIntervallImpl();
        return delayedIntervall;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TimeFrame createTimeFrame() {
        TimeFrameImpl timeFrame = new TimeFrameImpl();
        return timeFrame;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public UniqueElement createUniqueElement() {
        UniqueElementImpl uniqueElement = new UniqueElementImpl();
        return uniqueElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PerformanceMetricEnum createPerformanceMetricEnumFromString(EDataType eDataType, String initialValue) {
        PerformanceMetricEnum result = PerformanceMetricEnum.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                    + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertPerformanceMetricEnumToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public StatisticalCharacterizationEnum createStatisticalCharacterizationEnumFromString(EDataType eDataType,
            String initialValue) {
        StatisticalCharacterizationEnum result = StatisticalCharacterizationEnum.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
                    + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertStatisticalCharacterizationEnumToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PmsPackage getPmsPackage() {
        return (PmsPackage) getEPackage();
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

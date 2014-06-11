/**
 */
package org.palladiosimulator.servicelevelobjective.impl;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.palladiosimulator.servicelevelobjective.HardThreshold;
import org.palladiosimulator.servicelevelobjective.LinearFuzzyThreshold;
import org.palladiosimulator.servicelevelobjective.NamedElement;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectiveFactory;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ServicelevelObjectiveFactoryImpl extends EFactoryImpl implements ServicelevelObjectiveFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ServicelevelObjectiveFactory init() {
        try {
            ServicelevelObjectiveFactory theServicelevelObjectiveFactory = (ServicelevelObjectiveFactory)EPackage.Registry.INSTANCE.getEFactory(ServicelevelObjectivePackage.eNS_URI);
            if (theServicelevelObjectiveFactory != null) {
                return theServicelevelObjectiveFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ServicelevelObjectiveFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ServicelevelObjectiveFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE_REPOSITORY: return createServiceLevelObjectiveRepository();
            case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE: return createServiceLevelObjective();
            case ServicelevelObjectivePackage.HARD_THRESHOLD: return createHardThreshold();
            case ServicelevelObjectivePackage.LINEAR_FUZZY_THRESHOLD: return createLinearFuzzyThreshold();
            case ServicelevelObjectivePackage.NAMED_ELEMENT: return createNamedElement();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case ServicelevelObjectivePackage.JS_MEASURE:
                return createJSMeasureFromString(eDataType, initialValue);
            case ServicelevelObjectivePackage.JS_QUANTITY:
                return createJSQuantityFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case ServicelevelObjectivePackage.JS_MEASURE:
                return convertJSMeasureToString(eDataType, instanceValue);
            case ServicelevelObjectivePackage.JS_QUANTITY:
                return convertJSQuantityToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ServiceLevelObjectiveRepository createServiceLevelObjectiveRepository() {
        ServiceLevelObjectiveRepositoryImpl serviceLevelObjectiveRepository = new ServiceLevelObjectiveRepositoryImpl();
        return serviceLevelObjectiveRepository;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ServiceLevelObjective createServiceLevelObjective() {
        ServiceLevelObjectiveImpl serviceLevelObjective = new ServiceLevelObjectiveImpl();
        return serviceLevelObjective;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public HardThreshold createHardThreshold() {
        HardThresholdImpl hardThreshold = new HardThresholdImpl();
        return hardThreshold;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public LinearFuzzyThreshold createLinearFuzzyThreshold() {
        LinearFuzzyThresholdImpl linearFuzzyThreshold = new LinearFuzzyThresholdImpl();
        return linearFuzzyThreshold;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NamedElement createNamedElement() {
        NamedElementImpl namedElement = new NamedElementImpl();
        return namedElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Measure<?, ?> createJSMeasureFromString(EDataType eDataType, String initialValue) {
        return (Measure<?, ?>)super.createFromString(initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertJSMeasureToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Quantity createJSQuantityFromString(EDataType eDataType, String initialValue) {
        return (Quantity)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertJSQuantityToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ServicelevelObjectivePackage getServicelevelObjectivePackage() {
        return (ServicelevelObjectivePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ServicelevelObjectivePackage getPackage() {
        return ServicelevelObjectivePackage.eINSTANCE;
    }

} //ServicelevelObjectiveFactoryImpl

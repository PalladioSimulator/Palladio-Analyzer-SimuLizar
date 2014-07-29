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
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class ServicelevelObjectiveFactoryImpl extends EFactoryImpl implements ServicelevelObjectiveFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ServicelevelObjectiveFactory init() {
        try {
            final ServicelevelObjectiveFactory theServicelevelObjectiveFactory = (ServicelevelObjectiveFactory) EPackage.Registry.INSTANCE
                    .getEFactory(ServicelevelObjectivePackage.eNS_URI);
            if (theServicelevelObjectiveFactory != null) {
                return theServicelevelObjectiveFactory;
            }
        } catch (final Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ServicelevelObjectiveFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ServicelevelObjectiveFactoryImpl() {
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
        case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE_REPOSITORY:
            return this.createServiceLevelObjectiveRepository();
        case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE:
            return this.createServiceLevelObjective();
        case ServicelevelObjectivePackage.HARD_THRESHOLD:
            return this.createHardThreshold();
        case ServicelevelObjectivePackage.LINEAR_FUZZY_THRESHOLD:
            return this.createLinearFuzzyThreshold();
        case ServicelevelObjectivePackage.NAMED_ELEMENT:
            return this.createNamedElement();
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
        case ServicelevelObjectivePackage.JS_MEASURE:
            return this.createJSMeasureFromString(eDataType, initialValue);
        case ServicelevelObjectivePackage.JS_QUANTITY:
            return this.createJSQuantityFromString(eDataType, initialValue);
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
        case ServicelevelObjectivePackage.JS_MEASURE:
            return this.convertJSMeasureToString(eDataType, instanceValue);
        case ServicelevelObjectivePackage.JS_QUANTITY:
            return this.convertJSQuantityToString(eDataType, instanceValue);
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
    public ServiceLevelObjectiveRepository createServiceLevelObjectiveRepository() {
        final ServiceLevelObjectiveRepositoryImpl serviceLevelObjectiveRepository = new ServiceLevelObjectiveRepositoryImpl();
        return serviceLevelObjectiveRepository;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ServiceLevelObjective createServiceLevelObjective() {
        final ServiceLevelObjectiveImpl serviceLevelObjective = new ServiceLevelObjectiveImpl();
        return serviceLevelObjective;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public HardThreshold createHardThreshold() {
        final HardThresholdImpl hardThreshold = new HardThresholdImpl();
        return hardThreshold;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LinearFuzzyThreshold createLinearFuzzyThreshold() {
        final LinearFuzzyThresholdImpl linearFuzzyThreshold = new LinearFuzzyThresholdImpl();
        return linearFuzzyThreshold;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NamedElement createNamedElement() {
        final NamedElementImpl namedElement = new NamedElementImpl();
        return namedElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Measure<?, ?> createJSMeasureFromString(final EDataType eDataType, final String initialValue) {
        return (Measure<?, ?>) super.createFromString(initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertJSMeasureToString(final EDataType eDataType, final Object instanceValue) {
        return super.convertToString(instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Quantity createJSQuantityFromString(final EDataType eDataType, final String initialValue) {
        return (Quantity) super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertJSQuantityToString(final EDataType eDataType, final Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ServicelevelObjectivePackage getServicelevelObjectivePackage() {
        return (ServicelevelObjectivePackage) this.getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ServicelevelObjectivePackage getPackage() {
        return ServicelevelObjectivePackage.eINSTANCE;
    }

} // ServicelevelObjectiveFactoryImpl

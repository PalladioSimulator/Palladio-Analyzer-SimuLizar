/**
 */
package org.palladiosimulator.runtimemeasurement.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class RuntimeMeasurementFactoryImpl extends EFactoryImpl implements RuntimeMeasurementFactory {

    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static RuntimeMeasurementFactory init() {
        try {
            final RuntimeMeasurementFactory theRuntimeMeasurementFactory = (RuntimeMeasurementFactory) EPackage.Registry.INSTANCE
                    .getEFactory(RuntimeMeasurementPackage.eNS_URI);
            if (theRuntimeMeasurementFactory != null) {
                return theRuntimeMeasurementFactory;
            }
        } catch (final Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new RuntimeMeasurementFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public RuntimeMeasurementFactoryImpl() {
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
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT_MODEL:
            return this.createRuntimeMeasurementModel();
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT:
            return this.createRuntimeMeasurement();
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
    public RuntimeMeasurementModel createRuntimeMeasurementModel() {
        final RuntimeMeasurementModelImpl runtimeMeasurementModel = new RuntimeMeasurementModelImpl();
        return runtimeMeasurementModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RuntimeMeasurement createRuntimeMeasurement() {
        final RuntimeMeasurementImpl runtimeMeasurement = new RuntimeMeasurementImpl();
        return runtimeMeasurement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RuntimeMeasurementPackage getRuntimeMeasurementPackage() {
        return (RuntimeMeasurementPackage) this.getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static RuntimeMeasurementPackage getPackage() {
        return RuntimeMeasurementPackage.eINSTANCE;
    }

} // RuntimeMeasurementFactoryImpl

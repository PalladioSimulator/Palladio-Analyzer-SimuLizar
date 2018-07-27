/**
 */
package simulizarmeasuringpoint.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import simulizarmeasuringpoint.ReconfigurationMeasuringPoint;
import simulizarmeasuringpoint.SimulizarmeasuringpointFactory;
import simulizarmeasuringpoint.SimulizarmeasuringpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class SimulizarmeasuringpointFactoryImpl extends EFactoryImpl implements SimulizarmeasuringpointFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static SimulizarmeasuringpointFactory init() {
        try {
            final SimulizarmeasuringpointFactory theSimulizarmeasuringpointFactory = (SimulizarmeasuringpointFactory) EPackage.Registry.INSTANCE
                    .getEFactory(SimulizarmeasuringpointPackage.eNS_URI);
            if (theSimulizarmeasuringpointFactory != null) {
                return theSimulizarmeasuringpointFactory;
            }
        } catch (final Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new SimulizarmeasuringpointFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public SimulizarmeasuringpointFactoryImpl() {
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
        case SimulizarmeasuringpointPackage.RECONFIGURATION_MEASURING_POINT:
            return this.createReconfigurationMeasuringPoint();
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
    public ReconfigurationMeasuringPoint createReconfigurationMeasuringPoint() {
        final ReconfigurationMeasuringPointImpl reconfigurationMeasuringPoint = new ReconfigurationMeasuringPointImpl();
        return reconfigurationMeasuringPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SimulizarmeasuringpointPackage getSimulizarmeasuringpointPackage() {
        return (SimulizarmeasuringpointPackage) this.getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static SimulizarmeasuringpointPackage getPackage() {
        return SimulizarmeasuringpointPackage.eINSTANCE;
    }

} // SimulizarmeasuringpointFactoryImpl

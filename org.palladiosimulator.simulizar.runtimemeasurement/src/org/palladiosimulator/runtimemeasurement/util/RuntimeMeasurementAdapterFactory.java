/**
 */
package org.palladiosimulator.runtimemeasurement.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;

import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter
 * <code>createXXX</code> method for each class of the model. <!-- end-user-doc -->
 *
 * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage
 * @generated
 */
public class RuntimeMeasurementAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static RuntimeMeasurementPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public RuntimeMeasurementAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = RuntimeMeasurementPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc
     * --> This implementation returns <code>true</code> if the object is either the model's package
     * or is an instance object of the model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(final Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    protected RuntimeMeasurementSwitch<Adapter> modelSwitch = new RuntimeMeasurementSwitch<Adapter>() {
        @Override
        public Adapter caseRuntimeMeasurementModel(final RuntimeMeasurementModel object) {
            return RuntimeMeasurementAdapterFactory.this.createRuntimeMeasurementModelAdapter();
        }

        @Override
        public Adapter caseRuntimeMeasurement(final RuntimeMeasurement object) {
            return RuntimeMeasurementAdapterFactory.this.createRuntimeMeasurementAdapter();
        }

        @Override
        public Adapter caseIdentifier(final Identifier object) {
            return RuntimeMeasurementAdapterFactory.this.createIdentifierAdapter();
        }

        @Override
        public Adapter defaultCase(final EObject object) {
            return RuntimeMeasurementAdapterFactory.this.createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(final Notifier target) {
        return this.modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel <em>Model</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel
     * @generated
     */
    public Adapter createRuntimeMeasurementModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.runtimemeasurement.RuntimeMeasurement
     * <em>Runtime Measurement</em>}'. <!-- begin-user-doc --> This default implementation returns
     * null so that we can easily ignore cases; it's useful to ignore a case when inheritance will
     * catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurement
     * @generated
     */
    public Adapter createRuntimeMeasurementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link de.uka.ipd.sdq.identifier.Identifier
     * <em>Identifier</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all
     * the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see de.uka.ipd.sdq.identifier.Identifier
     * @generated
     */
    public Adapter createIdentifierAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default
     * implementation returns null. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // RuntimeMeasurementAdapterFactory

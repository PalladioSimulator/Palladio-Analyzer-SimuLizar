/**
 */
package org.palladiosimulator.servicelevelobjective.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.servicelevelobjective.HardThreshold;
import org.palladiosimulator.servicelevelobjective.LinearFuzzyThreshold;
import org.palladiosimulator.servicelevelobjective.NamedElement;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;
import org.palladiosimulator.servicelevelobjective.Threshold;

import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter
 * <code>createXXX</code> method for each class of the model. <!-- end-user-doc -->
 * 
 * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage
 * @generated
 */
public class ServicelevelObjectiveAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static ServicelevelObjectivePackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ServicelevelObjectiveAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = ServicelevelObjectivePackage.eINSTANCE;
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
    protected ServicelevelObjectiveSwitch<Adapter> modelSwitch = new ServicelevelObjectiveSwitch<Adapter>() {
        @Override
        public Adapter caseServiceLevelObjectiveRepository(final ServiceLevelObjectiveRepository object) {
            return ServicelevelObjectiveAdapterFactory.this.createServiceLevelObjectiveRepositoryAdapter();
        }

        @Override
        public Adapter caseServiceLevelObjective(final ServiceLevelObjective object) {
            return ServicelevelObjectiveAdapterFactory.this.createServiceLevelObjectiveAdapter();
        }

        @Override
        public Adapter caseThreshold(final Threshold object) {
            return ServicelevelObjectiveAdapterFactory.this.createThresholdAdapter();
        }

        @Override
        public Adapter caseHardThreshold(final HardThreshold object) {
            return ServicelevelObjectiveAdapterFactory.this.createHardThresholdAdapter();
        }

        @Override
        public Adapter caseLinearFuzzyThreshold(final LinearFuzzyThreshold object) {
            return ServicelevelObjectiveAdapterFactory.this.createLinearFuzzyThresholdAdapter();
        }

        @Override
        public Adapter caseNamedElement(final NamedElement object) {
            return ServicelevelObjectiveAdapterFactory.this.createNamedElementAdapter();
        }

        @Override
        public Adapter caseIdentifier(final Identifier object) {
            return ServicelevelObjectiveAdapterFactory.this.createIdentifierAdapter();
        }

        @Override
        public Adapter defaultCase(final EObject object) {
            return ServicelevelObjectiveAdapterFactory.this.createEObjectAdapter();
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
     * {@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository
     * <em>Service Level Objective Repository</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's useful to ignore a case
     * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository
     * @generated
     */
    public Adapter createServiceLevelObjectiveRepositoryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective
     * <em>Service Level Objective</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.palladiosimulator.servicelevelobjective.ServiceLevelObjective
     * @generated
     */
    public Adapter createServiceLevelObjectiveAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.servicelevelobjective.Threshold <em>Threshold</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.palladiosimulator.servicelevelobjective.Threshold
     * @generated
     */
    public Adapter createThresholdAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.servicelevelobjective.HardThreshold <em>Hard Threshold</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.palladiosimulator.servicelevelobjective.HardThreshold
     * @generated
     */
    public Adapter createHardThresholdAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.servicelevelobjective.LinearFuzzyThreshold
     * <em>Linear Fuzzy Threshold</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.palladiosimulator.servicelevelobjective.LinearFuzzyThreshold
     * @generated
     */
    public Adapter createLinearFuzzyThresholdAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.servicelevelobjective.NamedElement <em>Named Element</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.palladiosimulator.servicelevelobjective.NamedElement
     * @generated
     */
    public Adapter createNamedElementAdapter() {
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

} // ServicelevelObjectiveAdapterFactory

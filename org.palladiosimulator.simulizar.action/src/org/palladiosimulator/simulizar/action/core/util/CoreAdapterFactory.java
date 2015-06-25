/**
 */
package org.palladiosimulator.simulizar.action.core.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.action.core.Action;
import org.palladiosimulator.simulizar.action.core.ActionRepository;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingStep;
import org.palladiosimulator.simulizar.action.core.RoleType;

import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter
 * <code>createXXX</code> method for each class of the model. <!-- end-user-doc -->
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage
 * @generated
 */
public class CoreAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static CorePackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public CoreAdapterFactory() {
        if (modelPackage == null)
        {
            modelPackage = CorePackage.eINSTANCE;
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
        if (object == modelPackage)
        {
            return true;
        }
        if (object instanceof EObject)
        {
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
    protected CoreSwitch<Adapter> modelSwitch =
            new CoreSwitch<Adapter>()
            {
        @Override
        public Adapter caseAction(final Action object)
        {
            return CoreAdapterFactory.this.createActionAdapter();
        }

        @Override
        public Adapter caseAdaptationStep(final AdaptationStep object)
        {
            return CoreAdapterFactory.this.createAdaptationStepAdapter();
        }

        @Override
        public Adapter caseResourceDemandingStep(final ResourceDemandingStep object)
        {
            return CoreAdapterFactory.this.createResourceDemandingStepAdapter();
        }

        @Override
        public Adapter caseRoleType(final RoleType object)
        {
            return CoreAdapterFactory.this.createRoleTypeAdapter();
        }

        @Override
        public Adapter caseActionRepository(final ActionRepository object)
        {
            return CoreAdapterFactory.this.createActionRepositoryAdapter();
        }

        @Override
        public Adapter caseControllerCall(final ControllerCall object)
        {
            return CoreAdapterFactory.this.createControllerCallAdapter();
        }

        @Override
        public Adapter caseIdentifier(final Identifier object)
        {
            return CoreAdapterFactory.this.createIdentifierAdapter();
        }

        @Override
        public Adapter caseNamedElement(final org.palladiosimulator.pcm.core.entity.NamedElement object)
        {
            return CoreAdapterFactory.this.createNamedElementAdapter();
        }

        @Override
        public Adapter caseEntity(final org.palladiosimulator.pcm.core.entity.Entity object)
        {
            return CoreAdapterFactory.this.createEntityAdapter();
        }

        @Override
        public Adapter defaultCase(final EObject object)
        {
            return CoreAdapterFactory.this.createEObjectAdapter();
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
     * {@link org.palladiosimulator.simulizar.action.core.Action <em>Action</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
              * @see org.palladiosimulator.simulizar.action.core.Action
              * @generated
              */
             public Adapter createActionAdapter() {
                 return null;
             }

             /**
              * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationStep <em>Adaptation Step</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
              * @see org.palladiosimulator.simulizar.action.core.AdaptationStep
              * @generated
              */
             public Adapter createAdaptationStepAdapter() {
                 return null;
             }

             /**
              * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.simulizar.action.core.ResourceDemandingStep
     * <em>Resource Demanding Step</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
              * @see org.palladiosimulator.simulizar.action.core.ResourceDemandingStep
              * @generated
              */
             public Adapter createResourceDemandingStepAdapter() {
                 return null;
             }

             /**
              * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.simulizar.action.core.RoleType <em>Role Type</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
              * @see org.palladiosimulator.simulizar.action.core.RoleType
              * @generated
              */
             public Adapter createRoleTypeAdapter() {
                 return null;
             }

             /**
              * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.simulizar.action.core.ActionRepository
     * <em>Action Repository</em>}'. <!-- begin-user-doc --> This default implementation returns
     * null so that we can easily ignore cases; it's useful to ignore a case when inheritance will
     * catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
              * @see org.palladiosimulator.simulizar.action.core.ActionRepository
              * @generated
              */
             public Adapter createActionRepositoryAdapter() {
                 return null;
             }

             /**
              * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.simulizar.action.core.ControllerCall <em>Controller Call</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
              * @see org.palladiosimulator.simulizar.action.core.ControllerCall
              * @generated
              */
             public Adapter createControllerCallAdapter() {
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
              * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.pcm.core.entity.NamedElement <em>Named Element</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
              * @see org.palladiosimulator.pcm.core.entity.NamedElement
              * @generated
              */
             public Adapter createNamedElementAdapter() {
                 return null;
             }

             /**
              * Creates a new adapter for an object of class '
     * {@link org.palladiosimulator.pcm.core.entity.Entity <em>Entity</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
              * @see org.palladiosimulator.pcm.core.entity.Entity
              * @generated
              */
             public Adapter createEntityAdapter() {
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

} // CoreAdapterFactory

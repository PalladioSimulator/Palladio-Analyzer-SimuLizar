/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.core.CorePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Adaptation Behavior
 * Repository</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorRepositoryImpl#getActions
 * <em>Actions</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorRepositoryImpl#getIncludedRepositories
 * <em>Included Repositories</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AdaptationBehaviorRepositoryImpl extends EntityImpl implements AdaptationBehaviorRepository {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AdaptationBehaviorRepositoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.ADAPTATION_BEHAVIOR_REPOSITORY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public EList<AdaptationBehavior> getActions() {
        return (EList<AdaptationBehavior>) this.eDynamicGet(CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS,
                CorePackage.Literals.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public EList<AdaptationBehaviorRepository> getIncludedRepositories() {
        return (EList<AdaptationBehaviorRepository>) this.eDynamicGet(
                CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__INCLUDED_REPOSITORIES,
                CorePackage.Literals.ADAPTATION_BEHAVIOR_REPOSITORY__INCLUDED_REPOSITORIES, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getActions()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS:
            return ((InternalEList<?>) this.getActions()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS:
            return this.getActions();
        case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__INCLUDED_REPOSITORIES:
            return this.getIncludedRepositories();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS:
            this.getActions()
                .clear();
            this.getActions()
                .addAll((Collection<? extends AdaptationBehavior>) newValue);
            return;
        case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__INCLUDED_REPOSITORIES:
            this.getIncludedRepositories()
                .clear();
            this.getIncludedRepositories()
                .addAll((Collection<? extends AdaptationBehaviorRepository>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS:
            this.getActions()
                .clear();
            return;
        case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__INCLUDED_REPOSITORIES:
            this.getIncludedRepositories()
                .clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS:
            return !this.getActions()
                .isEmpty();
        case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__INCLUDED_REPOSITORIES:
            return !this.getIncludedRepositories()
                .isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // AdaptationBehaviorRepositoryImpl

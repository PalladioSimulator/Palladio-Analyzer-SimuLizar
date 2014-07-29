/**
 */
package org.palladiosimulator.servicelevelobjective.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Service Level Objective Repository</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.servicelevelobjective.impl.ServiceLevelObjectiveRepositoryImpl#getServicelevelobjectives
 * <em>Servicelevelobjectives</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceLevelObjectiveRepositoryImpl extends MinimalEObjectImpl.Container implements
ServiceLevelObjectiveRepository {
    /**
     * The cached value of the '{@link #getServicelevelobjectives() <em>Servicelevelobjectives</em>}
     * ' containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getServicelevelobjectives()
     * @generated
     * @ordered
     */
    protected EList<ServiceLevelObjective> servicelevelobjectives;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ServiceLevelObjectiveRepositoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ServicelevelObjectivePackage.Literals.SERVICE_LEVEL_OBJECTIVE_REPOSITORY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ServiceLevelObjective> getServicelevelobjectives() {
        if (this.servicelevelobjectives == null) {
            this.servicelevelobjectives = new EObjectContainmentEList<ServiceLevelObjective>(
                    ServiceLevelObjective.class, this,
                    ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE_REPOSITORY__SERVICELEVELOBJECTIVES);
        }
        return this.servicelevelobjectives;
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
        case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE_REPOSITORY__SERVICELEVELOBJECTIVES:
            return ((InternalEList<?>) this.getServicelevelobjectives()).basicRemove(otherEnd, msgs);
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
        case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE_REPOSITORY__SERVICELEVELOBJECTIVES:
            return this.getServicelevelobjectives();
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
        case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE_REPOSITORY__SERVICELEVELOBJECTIVES:
            this.getServicelevelobjectives().clear();
            this.getServicelevelobjectives().addAll((Collection<? extends ServiceLevelObjective>) newValue);
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
        case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE_REPOSITORY__SERVICELEVELOBJECTIVES:
            this.getServicelevelobjectives().clear();
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
        case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE_REPOSITORY__SERVICELEVELOBJECTIVES:
            return this.servicelevelobjectives != null && !this.servicelevelobjectives.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ServiceLevelObjectiveRepositoryImpl

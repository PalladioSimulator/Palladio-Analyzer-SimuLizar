/**
 */
package org.palladiosimulator.simulizar.pms.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.simulizar.pms.Monitor;
import org.palladiosimulator.simulizar.pms.MonitorRepository;
import org.palladiosimulator.simulizar.pms.PmsPackage;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Monitor Repository</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.pms.impl.MonitorRepositoryImpl#getMonitors <em>
 * Monitors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MonitorRepositoryImpl extends EntityImpl implements MonitorRepository {
    /**
     * The cached value of the '{@link #getMonitors() <em>Monitors</em>}' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMonitors()
     * @generated
     * @ordered
     */
    protected EList<Monitor> monitors;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MonitorRepositoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PmsPackage.Literals.MONITOR_REPOSITORY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Monitor> getMonitors() {
        if (this.monitors == null) {
            this.monitors = new EObjectContainmentEList<Monitor>(Monitor.class, this,
                    PmsPackage.MONITOR_REPOSITORY__MONITORS);
        }
        return this.monitors;
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
        case PmsPackage.MONITOR_REPOSITORY__MONITORS:
            return ((InternalEList<?>) this.getMonitors()).basicRemove(otherEnd, msgs);
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
        case PmsPackage.MONITOR_REPOSITORY__MONITORS:
            return this.getMonitors();
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
        case PmsPackage.MONITOR_REPOSITORY__MONITORS:
            this.getMonitors().clear();
            this.getMonitors().addAll((Collection<? extends Monitor>) newValue);
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
        case PmsPackage.MONITOR_REPOSITORY__MONITORS:
            this.getMonitors().clear();
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
        case PmsPackage.MONITOR_REPOSITORY__MONITORS:
            return this.monitors != null && !this.monitors.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // MonitorRepositoryImpl

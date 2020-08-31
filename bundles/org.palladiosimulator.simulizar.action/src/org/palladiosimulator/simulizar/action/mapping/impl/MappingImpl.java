/**
 */
package org.palladiosimulator.simulizar.action.mapping.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.simulizar.action.mapping.ControllerMapping;
import org.palladiosimulator.simulizar.action.mapping.Mapping;
import org.palladiosimulator.simulizar.action.mapping.MappingPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Mapping</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.mapping.impl.MappingImpl#getControllerMappings
 * <em>Controller Mappings</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MappingImpl extends EntityImpl implements Mapping {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MappingPackage.Literals.MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public EList<ControllerMapping> getControllerMappings() {
        return (EList<ControllerMapping>) this.eDynamicGet(MappingPackage.MAPPING__CONTROLLER_MAPPINGS,
                MappingPackage.Literals.MAPPING__CONTROLLER_MAPPINGS, true, true);
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
        case MappingPackage.MAPPING__CONTROLLER_MAPPINGS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getControllerMappings()).basicAdd(otherEnd,
                    msgs);
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
        case MappingPackage.MAPPING__CONTROLLER_MAPPINGS:
            return ((InternalEList<?>) this.getControllerMappings()).basicRemove(otherEnd, msgs);
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
        case MappingPackage.MAPPING__CONTROLLER_MAPPINGS:
            return this.getControllerMappings();
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
        case MappingPackage.MAPPING__CONTROLLER_MAPPINGS:
            this.getControllerMappings()
                .clear();
            this.getControllerMappings()
                .addAll((Collection<? extends ControllerMapping>) newValue);
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
        case MappingPackage.MAPPING__CONTROLLER_MAPPINGS:
            this.getControllerMappings()
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
        case MappingPackage.MAPPING__CONTROLLER_MAPPINGS:
            return !this.getControllerMappings()
                .isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // MappingImpl

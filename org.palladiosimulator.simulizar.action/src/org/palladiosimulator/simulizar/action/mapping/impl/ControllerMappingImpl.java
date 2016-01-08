/**
 */
package org.palladiosimulator.simulizar.action.mapping.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.mapping.ControllerMapping;
import org.palladiosimulator.simulizar.action.mapping.Mapping;
import org.palladiosimulator.simulizar.action.mapping.MappingPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Controller Mapping</b></em>
 * '. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.mapping.impl.ControllerMappingImpl#getMappedCall
 * <em>Mapped Call</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.mapping.impl.ControllerMappingImpl#getControllerRole
 * <em>Controller Role</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.mapping.impl.ControllerMappingImpl#getMapping
 * <em>Mapping</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ControllerMappingImpl extends EntityImpl implements ControllerMapping {
    /**
     * The cached value of the '{@link #getMappedCall() <em>Mapped Call</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMappedCall()
     * @generated
     * @ordered
     */
    protected ControllerCall mappedCall;

    /**
     * The cached value of the '{@link #getControllerRole() <em>Controller Role</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getControllerRole()
     * @generated
     * @ordered
     */
    protected OperationProvidedRole controllerRole;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ControllerMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MappingPackage.Literals.CONTROLLER_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ControllerCall getMappedCall() {
        if (this.mappedCall != null && this.mappedCall.eIsProxy()) {
            final InternalEObject oldMappedCall = (InternalEObject) this.mappedCall;
            this.mappedCall = (ControllerCall) this.eResolveProxy(oldMappedCall);
            if (this.mappedCall != oldMappedCall) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            MappingPackage.CONTROLLER_MAPPING__MAPPED_CALL, oldMappedCall, this.mappedCall));
                }
            }
        }
        return this.mappedCall;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ControllerCall basicGetMappedCall() {
        return this.mappedCall;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMappedCall(final ControllerCall newMappedCall) {
        final ControllerCall oldMappedCall = this.mappedCall;
        this.mappedCall = newMappedCall;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.CONTROLLER_MAPPING__MAPPED_CALL,
                    oldMappedCall, this.mappedCall));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public OperationProvidedRole getControllerRole() {
        if (this.controllerRole != null && ((EObject) this.controllerRole).eIsProxy()) {
            final InternalEObject oldControllerRole = (InternalEObject) this.controllerRole;
            this.controllerRole = (OperationProvidedRole) this.eResolveProxy(oldControllerRole);
            if (this.controllerRole != oldControllerRole) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            MappingPackage.CONTROLLER_MAPPING__CONTROLLER_ROLE, oldControllerRole,
                            this.controllerRole));
                }
            }
        }
        return this.controllerRole;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public OperationProvidedRole basicGetControllerRole() {
        return this.controllerRole;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setControllerRole(final OperationProvidedRole newControllerRole) {
        final OperationProvidedRole oldControllerRole = this.controllerRole;
        this.controllerRole = newControllerRole;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MappingPackage.CONTROLLER_MAPPING__CONTROLLER_ROLE, oldControllerRole, this.controllerRole));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Mapping getMapping() {
        if (this.eContainerFeatureID() != MappingPackage.CONTROLLER_MAPPING__MAPPING) {
            return null;
        }
        return (Mapping) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetMapping(final Mapping newMapping, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newMapping, MappingPackage.CONTROLLER_MAPPING__MAPPING, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMapping(final Mapping newMapping) {
        if (newMapping != this.eInternalContainer()
                || (this.eContainerFeatureID() != MappingPackage.CONTROLLER_MAPPING__MAPPING && newMapping != null)) {
            if (EcoreUtil.isAncestor(this, newMapping)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newMapping != null) {
                msgs = ((InternalEObject) newMapping).eInverseAdd(this, MappingPackage.MAPPING__CONTROLLER_MAPPINGS,
                        Mapping.class, msgs);
            }
            msgs = this.basicSetMapping(newMapping, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.CONTROLLER_MAPPING__MAPPING,
                    newMapping, newMapping));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case MappingPackage.CONTROLLER_MAPPING__MAPPING:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetMapping((Mapping) otherEnd, msgs);
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
        case MappingPackage.CONTROLLER_MAPPING__MAPPING:
            return this.basicSetMapping(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(final NotificationChain msgs) {
        switch (this.eContainerFeatureID()) {
        case MappingPackage.CONTROLLER_MAPPING__MAPPING:
            return this.eInternalContainer().eInverseRemove(this, MappingPackage.MAPPING__CONTROLLER_MAPPINGS,
                    Mapping.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case MappingPackage.CONTROLLER_MAPPING__MAPPED_CALL:
            if (resolve) {
                return this.getMappedCall();
            }
            return this.basicGetMappedCall();
        case MappingPackage.CONTROLLER_MAPPING__CONTROLLER_ROLE:
            if (resolve) {
                return this.getControllerRole();
            }
            return this.basicGetControllerRole();
        case MappingPackage.CONTROLLER_MAPPING__MAPPING:
            return this.getMapping();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case MappingPackage.CONTROLLER_MAPPING__MAPPED_CALL:
            this.setMappedCall((ControllerCall) newValue);
            return;
        case MappingPackage.CONTROLLER_MAPPING__CONTROLLER_ROLE:
            this.setControllerRole((OperationProvidedRole) newValue);
            return;
        case MappingPackage.CONTROLLER_MAPPING__MAPPING:
            this.setMapping((Mapping) newValue);
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
        case MappingPackage.CONTROLLER_MAPPING__MAPPED_CALL:
            this.setMappedCall((ControllerCall) null);
            return;
        case MappingPackage.CONTROLLER_MAPPING__CONTROLLER_ROLE:
            this.setControllerRole((OperationProvidedRole) null);
            return;
        case MappingPackage.CONTROLLER_MAPPING__MAPPING:
            this.setMapping((Mapping) null);
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
        case MappingPackage.CONTROLLER_MAPPING__MAPPED_CALL:
            return this.mappedCall != null;
        case MappingPackage.CONTROLLER_MAPPING__CONTROLLER_ROLE:
            return this.controllerRole != null;
        case MappingPackage.CONTROLLER_MAPPING__MAPPING:
            return this.getMapping() != null;
        }
        return super.eIsSet(featureID);
    }

} // ControllerMappingImpl

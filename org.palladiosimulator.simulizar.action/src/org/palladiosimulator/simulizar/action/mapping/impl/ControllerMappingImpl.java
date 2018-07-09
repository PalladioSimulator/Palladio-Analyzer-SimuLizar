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
 *   <li>{@link org.palladiosimulator.simulizar.action.mapping.impl.ControllerMappingImpl#getMappedCall <em>Mapped Call</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.mapping.impl.ControllerMappingImpl#getControllerRole <em>Controller Role</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.mapping.impl.ControllerMappingImpl#getMapping <em>Mapping</em>}</li>
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
	 * @see #getControllerRole()
	 * @generated
	 * @ordered
	 */
	protected OperationProvidedRole controllerRole;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ControllerMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MappingPackage.Literals.CONTROLLER_MAPPING;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ControllerCall getMappedCall() {
		if (mappedCall != null && mappedCall.eIsProxy()) {
			InternalEObject oldMappedCall = (InternalEObject) mappedCall;
			mappedCall = (ControllerCall) eResolveProxy(oldMappedCall);
			if (mappedCall != oldMappedCall) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							MappingPackage.CONTROLLER_MAPPING__MAPPED_CALL, oldMappedCall, mappedCall));
			}
		}
		return mappedCall;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ControllerCall basicGetMappedCall() {
		return mappedCall;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMappedCall(ControllerCall newMappedCall) {
		ControllerCall oldMappedCall = mappedCall;
		mappedCall = newMappedCall;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.CONTROLLER_MAPPING__MAPPED_CALL,
					oldMappedCall, mappedCall));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OperationProvidedRole getControllerRole() {
		if (controllerRole != null && ((EObject) controllerRole).eIsProxy()) {
			InternalEObject oldControllerRole = (InternalEObject) controllerRole;
			controllerRole = (OperationProvidedRole) eResolveProxy(oldControllerRole);
			if (controllerRole != oldControllerRole) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							MappingPackage.CONTROLLER_MAPPING__CONTROLLER_ROLE, oldControllerRole, controllerRole));
			}
		}
		return controllerRole;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OperationProvidedRole basicGetControllerRole() {
		return controllerRole;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setControllerRole(OperationProvidedRole newControllerRole) {
		OperationProvidedRole oldControllerRole = controllerRole;
		controllerRole = newControllerRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.CONTROLLER_MAPPING__CONTROLLER_ROLE,
					oldControllerRole, controllerRole));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Mapping getMapping() {
		if (eContainerFeatureID() != MappingPackage.CONTROLLER_MAPPING__MAPPING)
			return null;
		return (Mapping) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMapping(Mapping newMapping, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newMapping, MappingPackage.CONTROLLER_MAPPING__MAPPING, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMapping(Mapping newMapping) {
		if (newMapping != eInternalContainer()
				|| (eContainerFeatureID() != MappingPackage.CONTROLLER_MAPPING__MAPPING && newMapping != null)) {
			if (EcoreUtil.isAncestor(this, newMapping))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newMapping != null)
				msgs = ((InternalEObject) newMapping).eInverseAdd(this, MappingPackage.MAPPING__CONTROLLER_MAPPINGS,
						Mapping.class, msgs);
			msgs = basicSetMapping(newMapping, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MappingPackage.CONTROLLER_MAPPING__MAPPING,
					newMapping, newMapping));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case MappingPackage.CONTROLLER_MAPPING__MAPPING:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetMapping((Mapping) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case MappingPackage.CONTROLLER_MAPPING__MAPPING:
			return basicSetMapping(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case MappingPackage.CONTROLLER_MAPPING__MAPPING:
			return eInternalContainer().eInverseRemove(this, MappingPackage.MAPPING__CONTROLLER_MAPPINGS, Mapping.class,
					msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case MappingPackage.CONTROLLER_MAPPING__MAPPED_CALL:
			if (resolve)
				return getMappedCall();
			return basicGetMappedCall();
		case MappingPackage.CONTROLLER_MAPPING__CONTROLLER_ROLE:
			if (resolve)
				return getControllerRole();
			return basicGetControllerRole();
		case MappingPackage.CONTROLLER_MAPPING__MAPPING:
			return getMapping();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case MappingPackage.CONTROLLER_MAPPING__MAPPED_CALL:
			setMappedCall((ControllerCall) newValue);
			return;
		case MappingPackage.CONTROLLER_MAPPING__CONTROLLER_ROLE:
			setControllerRole((OperationProvidedRole) newValue);
			return;
		case MappingPackage.CONTROLLER_MAPPING__MAPPING:
			setMapping((Mapping) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case MappingPackage.CONTROLLER_MAPPING__MAPPED_CALL:
			setMappedCall((ControllerCall) null);
			return;
		case MappingPackage.CONTROLLER_MAPPING__CONTROLLER_ROLE:
			setControllerRole((OperationProvidedRole) null);
			return;
		case MappingPackage.CONTROLLER_MAPPING__MAPPING:
			setMapping((Mapping) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case MappingPackage.CONTROLLER_MAPPING__MAPPED_CALL:
			return mappedCall != null;
		case MappingPackage.CONTROLLER_MAPPING__CONTROLLER_ROLE:
			return controllerRole != null;
		case MappingPackage.CONTROLLER_MAPPING__MAPPING:
			return getMapping() != null;
		}
		return super.eIsSet(featureID);
	}

} // ControllerMappingImpl

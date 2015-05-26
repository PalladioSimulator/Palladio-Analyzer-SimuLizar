/**
 */
package org.palladiosimulator.simulizar.action.mapping.impl;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;
import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.simulizar.action.mapping.ControllerMapping;
import org.palladiosimulator.simulizar.action.mapping.Mapping;
import org.palladiosimulator.simulizar.action.mapping.MappingPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.mapping.impl.MappingImpl#getControllerMappings <em>Controller Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MappingImpl extends EntityImpl implements Mapping {
	/**
     * The cached value of the '{@link #getControllerMappings() <em>Controller Mappings</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getControllerMappings()
     * @generated
     * @ordered
     */
	protected EList<ControllerMapping> controllerMappings;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected MappingImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return MappingPackage.Literals.MAPPING;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<ControllerMapping> getControllerMappings() {
        if (controllerMappings == null) {
            controllerMappings = new EObjectContainmentWithInverseEList<ControllerMapping>(ControllerMapping.class, this, MappingPackage.MAPPING__CONTROLLER_MAPPINGS, MappingPackage.CONTROLLER_MAPPING__MAPPING);
        }
        return controllerMappings;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case MappingPackage.MAPPING__CONTROLLER_MAPPINGS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getControllerMappings()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case MappingPackage.MAPPING__CONTROLLER_MAPPINGS:
                return ((InternalEList<?>)getControllerMappings()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case MappingPackage.MAPPING__CONTROLLER_MAPPINGS:
                return getControllerMappings();
        }
        return super.eGet(featureID, resolve, coreType);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case MappingPackage.MAPPING__CONTROLLER_MAPPINGS:
                getControllerMappings().clear();
                getControllerMappings().addAll((Collection<? extends ControllerMapping>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void eUnset(int featureID) {
        switch (featureID) {
            case MappingPackage.MAPPING__CONTROLLER_MAPPINGS:
                getControllerMappings().clear();
                return;
        }
        super.eUnset(featureID);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public boolean eIsSet(int featureID) {
        switch (featureID) {
            case MappingPackage.MAPPING__CONTROLLER_MAPPINGS:
                return controllerMappings != null && !controllerMappings.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //MappingImpl

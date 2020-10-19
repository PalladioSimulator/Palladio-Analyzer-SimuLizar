/**
 */
package org.palladiosimulator.simulizar.action.instance;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.action.core.RoleType;

import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Role</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.instance.Role#getRoleSet <em>Role
 * Set</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.instance.Role#getRoleType <em>Role
 * Type</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.instance.Role#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.instance.InstancePackage#getRole()
 * @model
 * @generated
 */
public interface Role extends EObject, Identifier {
    /**
     * Returns the value of the '<em><b>Role Set</b></em>' container reference. It is bidirectional
     * and its opposite is '{@link org.palladiosimulator.simulizar.action.instance.RoleSet#getRoles
     * <em>Roles</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Role Set</em>' container reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Role Set</em>' container reference.
     * @see #setRoleSet(RoleSet)
     * @see org.palladiosimulator.simulizar.action.instance.InstancePackage#getRole_RoleSet()
     * @see org.palladiosimulator.simulizar.action.instance.RoleSet#getRoles
     * @model opposite="roles" transient="false"
     * @generated
     */
    RoleSet getRoleSet();

    /**
     * Sets the value of the '{@link org.palladiosimulator.simulizar.action.instance.Role#getRoleSet
     * <em>Role Set</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Role Set</em>' container reference.
     * @see #getRoleSet()
     * @generated
     */
    void setRoleSet(RoleSet value);

    /**
     * Returns the value of the '<em><b>Role Type</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Role Type</em>' reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Role Type</em>' reference.
     * @see #setRoleType(RoleType)
     * @see org.palladiosimulator.simulizar.action.instance.InstancePackage#getRole_RoleType()
     * @model required="true"
     * @generated
     */
    RoleType getRoleType();

    /**
     * Sets the value of the
     * '{@link org.palladiosimulator.simulizar.action.instance.Role#getRoleType <em>Role Type</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Role Type</em>' reference.
     * @see #getRoleType()
     * @generated
     */
    void setRoleType(RoleType value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' reference isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Value</em>' reference.
     * @see #setValue(EObject)
     * @see org.palladiosimulator.simulizar.action.instance.InstancePackage#getRole_Value()
     * @model
     * @generated
     */
    EObject getValue();

    /**
     * Sets the value of the '{@link org.palladiosimulator.simulizar.action.instance.Role#getValue
     * <em>Value</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value</em>' reference.
     * @see #getValue()
     * @generated
     */
    void setValue(EObject value);

} // Role

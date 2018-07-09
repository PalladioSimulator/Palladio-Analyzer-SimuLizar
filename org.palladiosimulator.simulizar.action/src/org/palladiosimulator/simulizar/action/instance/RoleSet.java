/**
 */
package org.palladiosimulator.simulizar.action.instance;

import de.uka.ipd.sdq.identifier.Identifier;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.instance.RoleSet#getRoles <em>Roles</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.instance.InstancePackage#getRoleSet()
 * @model
 * @generated
 */
public interface RoleSet extends EObject, Identifier {
	/**
	 * Returns the value of the '<em><b>Roles</b></em>' containment reference list.
	 * The list contents are of type {@link org.palladiosimulator.simulizar.action.instance.Role}.
	 * It is bidirectional and its opposite is '{@link org.palladiosimulator.simulizar.action.instance.Role#getRoleSet <em>Role Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' containment reference list.
	 * @see org.palladiosimulator.simulizar.action.instance.InstancePackage#getRoleSet_Roles()
	 * @see org.palladiosimulator.simulizar.action.instance.Role#getRoleSet
	 * @model opposite="roleSet" containment="true" required="true"
	 * @generated
	 */
	EList<Role> getRoles();

} // RoleSet

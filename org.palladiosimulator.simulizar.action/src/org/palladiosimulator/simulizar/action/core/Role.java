/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.core.entity.Entity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.Role#getType <em>Type</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.Role#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getRole()
 * @model
 * @generated
 */
public interface Role extends EObject, Entity {
    /**
     * Returns the value of the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' reference.
     * @see #setType(EClass)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getRole_Type()
     * @model
     * @generated
     */
    EClass getType();

    /**
     * Sets the value of the '{@link org.palladiosimulator.simulizar.action.core.Role#getType <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
    void setType(EClass value);

    /**
     * Returns the value of the '<em><b>Action</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.palladiosimulator.simulizar.action.core.Action#getInvolvedRoles <em>Involved Roles</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Action</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Action</em>' container reference.
     * @see #setAction(Action)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getRole_Action()
     * @see org.palladiosimulator.simulizar.action.core.Action#getInvolvedRoles
     * @model opposite="involvedRoles" transient="false"
     * @generated
     */
    Action getAction();

    /**
     * Sets the value of the '{@link org.palladiosimulator.simulizar.action.core.Role#getAction <em>Action</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Action</em>' container reference.
     * @see #getAction()
     * @generated
     */
    void setAction(Action value);

} // Role

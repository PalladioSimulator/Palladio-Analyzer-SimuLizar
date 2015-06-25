/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Action Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.ActionRepository#getActions <em>Actions
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getActionRepository()
 * @model
 * @generated
 */
public interface ActionRepository extends EObject, org.palladiosimulator.pcm.core.entity.Entity {
    /**
     * Returns the value of the '<em><b>Actions</b></em>' containment reference list. The list
     * contents are of type {@link org.palladiosimulator.simulizar.action.core.Action}. It is
     * bidirectional and its opposite is '
     * {@link org.palladiosimulator.simulizar.action.core.Action#getRepository <em>Repository</em>}
     * '. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Actions</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Actions</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getActionRepository_Actions()
     * @see org.palladiosimulator.simulizar.action.core.Action#getRepository
     * @model opposite="repository" containment="true"
     * @generated
     */
    EList<Action> getActions();

} // ActionRepository

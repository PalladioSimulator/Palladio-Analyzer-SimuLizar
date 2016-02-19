/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.core.entity.Entity;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Adaptation Behavior Repository</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository#getActions
 * <em>Actions</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository#getIncludedRepositories
 * <em>Included Repositories</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationBehaviorRepository()
 * @model
 * @generated
 */
public interface AdaptationBehaviorRepository extends EObject, Entity {
    /**
     * Returns the value of the '<em><b>Actions</b></em>' containment reference list. The list
     * contents are of type {@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior}.
     * It is bidirectional and its opposite is '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getRepository
     * <em>Repository</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Actions</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Actions</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationBehaviorRepository_Actions()
     * @see org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getRepository
     * @model opposite="repository" containment="true"
     * @generated
     */
    EList<AdaptationBehavior> getActions();

    /**
     * Returns the value of the '<em><b>Included Repositories</b></em>' reference list. The list
     * contents are of type
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Included Repositories</em>' reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Included Repositories</em>' reference list.
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationBehaviorRepository_IncludedRepositories()
     * @model
     * @generated
     */
    EList<AdaptationBehaviorRepository> getIncludedRepositories();

} // AdaptationBehaviorRepository

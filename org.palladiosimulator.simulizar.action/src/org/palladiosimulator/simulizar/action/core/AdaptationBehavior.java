/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.common.util.EList;
import org.modelversioning.emfprofile.Profile;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Adaptation Behavior</b></em>
 * '. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getInvolvedRoles
 * <em>Involved Roles</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getTransientStateProfile
 * <em>Transient State Profile</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getRepository
 * <em>Repository</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationBehavior()
 * @model
 * @generated
 */
public interface AdaptationBehavior extends AbstractAdaptationBehavior {
    /**
     * Returns the value of the '<em><b>Involved Roles</b></em>' containment reference list. The
     * list contents are of type {@link org.palladiosimulator.simulizar.action.core.RoleType}. It is
     * bidirectional and its opposite is '
     * {@link org.palladiosimulator.simulizar.action.core.RoleType#getAction <em>Action</em>}'. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Involved Roles</em>' containment reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Involved Roles</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationBehavior_InvolvedRoles()
     * @see org.palladiosimulator.simulizar.action.core.RoleType#getAction
     * @model opposite="action" containment="true" required="true"
     * @generated
     */
    EList<RoleType> getInvolvedRoles();

    /**
     * Returns the value of the '<em><b>Transient State Profile</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transient State Profile</em>' reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Transient State Profile</em>' reference.
     * @see #setTransientStateProfile(Profile)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationBehavior_TransientStateProfile()
     * @model
     * @generated
     */
    Profile getTransientStateProfile();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getTransientStateProfile
     * <em>Transient State Profile</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Transient State Profile</em>' reference.
     * @see #getTransientStateProfile()
     * @generated
     */
    void setTransientStateProfile(Profile value);

    /**
     * Returns the value of the '<em><b>Repository</b></em>' container reference. It is
     * bidirectional and its opposite is '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository#getActions
     * <em>Actions</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Repository</em>' container reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Repository</em>' container reference.
     * @see #setRepository(AdaptationBehaviorRepository)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationBehavior_Repository()
     * @see org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository#getActions
     * @model opposite="actions" transient="false"
     * @generated
     */
    AdaptationBehaviorRepository getRepository();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getRepository
     * <em>Repository</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Repository</em>' container reference.
     * @see #getRepository()
     * @generated
     */
    void setRepository(AdaptationBehaviorRepository value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model required="true" affectedRoleSetRequired="true"
     *        controllerCallsVariableUsagesRequired="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel body='return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState.createTransientEffectInterpreter(affectedRoleSet, controllerCallsVariableUsages, getRepository()).doSwitch(this);'"
     * @generated
     */
    boolean execute(RoleSet affectedRoleSet, ControllerCallInputVariableUsageCollection controllerCallsVariableUsages);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model required="true" affectedRoleSetRequired="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel body='return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState.createTransientEffectInterpreter(affectedRoleSet, getRepository()).doSwitch(this);'"
     * @generated
     */
    boolean execute(RoleSet affectedRoleSet);

} // AdaptationBehavior

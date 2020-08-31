/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.common.util.EList;
import org.modelversioning.emfprofile.Profile;
import org.palladiosimulator.simulizar.action.context.ExecutionContext;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Adaptation
 * Behavior</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getInvolvedRoles
 * <em>Involved Roles</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getTransientStateProfile
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
     * bidirectional and its opposite is
     * '{@link org.palladiosimulator.simulizar.action.core.RoleType#getAction <em>Action</em>}'.
     * <!-- begin-user-doc -->
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
     * Sets the value of the
     * '{@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getTransientStateProfile
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
     * bidirectional and its opposite is
     * '{@link org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository#getActions
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
     * Sets the value of the
     * '{@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getRepository
     * <em>Repository</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Repository</em>' container reference.
     * @see #getRepository()
     * @generated
     */
    void setRepository(AdaptationBehaviorRepository value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Executes this
     * AdaptationBehavior for the given RoleSet and with the given VariableUsages. As no
     * ExecutionContext is passed, this behavior will be processed by the main thread, i.e. by the
     * underlying ReconfigurationProcess. <!-- end-model-doc -->
     *
     * @model required="true" affectedRoleSetRequired="true"
     *        controllerCallsVariableUsagesRequired="true"
     *        resourceTableManagerDataType="org.palladiosimulator.simulizar.action.core.IResourceTableManager"
     * @generated
     */
    boolean execute(RoleSet affectedRoleSet, ControllerCallInputVariableUsageCollection controllerCallsVariableUsages,
            IResourceTableManager resourceTableManager);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Executes this
     * AdaptationBehavior for the given RoleSet and with the given VariableUsages. Note that the
     * behavior is synchronously executed by the process that is associated with the given context.
     * <!-- end-model-doc -->
     *
     * @model required="true" affectedRoleSetRequired="true"
     *        controllerCallsVariableUsagesRequired="true"
     *        resourceTableManagerDataType="org.palladiosimulator.simulizar.action.core.IResourceTableManager"
     * @generated
     */
    boolean execute(RoleSet affectedRoleSet, ControllerCallInputVariableUsageCollection controllerCallsVariableUsages,
            ExecutionContext executionContext, IResourceTableManager resourceTableManager);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Executes this
     * AdaptationBehavior for the given RoleSet. As no ExecutionContext is passed, this behavior
     * will be processed by the main thread, i.e. by the underlying ReconfigurationProcess. <!--
     * end-model-doc -->
     *
     * @model required="true" affectedRoleSetRequired="true"
     *        resourceTableManagerDataType="org.palladiosimulator.simulizar.action.core.IResourceTableManager"
     * @generated
     */
    boolean execute(RoleSet affectedRoleSet, IResourceTableManager resourceTableManager);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Executes this
     * AdaptationBehavior for the given RoleSet and with the given VariableUsages. Note that the
     * behavior is synchronously executed by the process that is associated with the given context.
     * <!-- end-model-doc -->
     *
     * @model required="true" affectedRoleSetRequired="true"
     *        resourceTableManagerDataType="org.palladiosimulator.simulizar.action.core.IResourceTableManager"
     * @generated
     */
    boolean execute(RoleSet affectedRoleSet, ExecutionContext executionContext,
            IResourceTableManager resourceTableManager);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Triggers the
     * asynchronous execution of this AdaptationBehavior for the given RoleSet and VariableUsages.
     * The ExecutionContext of the executing process, which is created before the async process is
     * spawned, is returned. It should be passed to synchronous adaptation behavior executions that
     * are done within. <!-- end-model-doc -->
     *
     * @model required="true" affectedRoleSetRequired="true"
     *        controllerCallsVariableUsagesRequired="true"
     *        resourceTableManagerDataType="org.palladiosimulator.simulizar.action.core.IResourceTableManager"
     * @generated
     */
    ExecutionContext executeAsync(RoleSet affectedRoleSet,
            ControllerCallInputVariableUsageCollection controllerCallsVariableUsages,
            IResourceTableManager resourceTableManager);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Triggers the
     * asynchronous execution of this AdaptationBehavior for the given RoleSet and VariableUsages in
     * the given ExecutionContext. To conform to ome overloaded versions of this method, this
     * context is also returned. It should be passed to synchronous adaptation behavior executions
     * that are done within. <!-- end-model-doc -->
     *
     * @model resourceTableManagerDataType="org.palladiosimulator.simulizar.action.core.IResourceTableManager"
     *        controllerCallsVariableUsagesRequired="true"
     * @generated
     */
    ExecutionContext executeAsync(IResourceTableManager resourceTableManager, RoleSet affectedRoleSet,
            ControllerCallInputVariableUsageCollection controllerCallsVariableUsages,
            ExecutionContext asyncExecutionContext);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model required="true" affectedRoleSetRequired="true"
     *        resourceTableManagerDataType="org.palladiosimulator.simulizar.action.core.IResourceTableManager"
     * @generated
     */
    ExecutionContext executeAsync(RoleSet affectedRoleSet, IResourceTableManager resourceTableManager);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Triggers the
     * asynchronous execution of this AdaptationBehavior for the given RoleSet in the given
     * ExecutionContext. To conform to ome overloaded versions of this method, this context is also
     * returned. It should be passed to synchronous adaptation behavior executions that are done
     * within. <!-- end-model-doc -->
     *
     * @model resourceTableManagerDataType="org.palladiosimulator.simulizar.action.core.IResourceTableManager"
     * @generated
     */
    ExecutionContext executeAsync(RoleSet affectedRoleSet, ExecutionContext asyncExecutionContext,
            IResourceTableManager resourceTableManager);

} // AdaptationBehavior

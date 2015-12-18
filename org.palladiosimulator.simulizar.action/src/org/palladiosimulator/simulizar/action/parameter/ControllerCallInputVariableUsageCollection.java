/**
 */
package org.palladiosimulator.simulizar.action.parameter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.core.entity.Entity;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Controller Call Input Variable Usage Collection</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection#getControllerCallInputVariableUsages
 * <em>Controller Call Input Variable Usages</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.parameter.ParameterPackage#getControllerCallInputVariableUsageCollection()
 * @model
 * @generated
 */
public interface ControllerCallInputVariableUsageCollection extends EObject, Entity {
    /**
     * Returns the value of the '<em><b>Controller Call Input Variable Usages</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage}. It
     * is bidirectional and its opposite is '
     * {@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getContainingCollection
     * <em>Containing Collection</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Controller Call Input Variable Usages</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Controller Call Input Variable Usages</em>' containment
     *         reference list.
     * @see org.palladiosimulator.simulizar.action.parameter.ParameterPackage#getControllerCallInputVariableUsageCollection_ControllerCallInputVariableUsages()
     * @see org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getContainingCollection
     * @model opposite="containingCollection" containment="true"
     * @generated
     */
    EList<ControllerCallInputVariableUsage> getControllerCallInputVariableUsages();

} // ControllerCallInputVariableUsageCollection

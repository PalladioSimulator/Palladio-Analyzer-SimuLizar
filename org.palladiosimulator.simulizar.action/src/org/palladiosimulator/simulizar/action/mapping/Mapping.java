/**
 */
package org.palladiosimulator.simulizar.action.mapping;

import de.uka.ipd.sdq.pcm.core.entity.Entity;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.mapping.Mapping#getControllerMappings <em>Controller Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.action.mapping.MappingPackage#getMapping()
 * @model
 * @generated
 */
public interface Mapping extends EObject, Entity {

	/**
     * Returns the value of the '<em><b>Controller Mappings</b></em>' containment reference list.
     * The list contents are of type {@link org.palladiosimulator.simulizar.action.mapping.ControllerMapping}.
     * It is bidirectional and its opposite is '{@link org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getMapping <em>Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Controller Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Controller Mappings</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.action.mapping.MappingPackage#getMapping_ControllerMappings()
     * @see org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getMapping
     * @model opposite="mapping" containment="true" required="true"
     * @generated
     */
	EList<ControllerMapping> getControllerMappings();
} // Mapping

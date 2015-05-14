/**
 */
package vsmappings;

import de.uka.ipd.sdq.pcm.core.entity.Entity;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>VS Mapping Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link vsmappings.VSMappingRepository#getVsmappings <em>Vsmappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see vsmappings.VsmappingsPackage#getVSMappingRepository()
 * @model
 * @generated
 */
public interface VSMappingRepository extends EObject, Entity {
	/**
	 * Returns the value of the '<em><b>Vsmappings</b></em>' containment reference list.
	 * The list contents are of type {@link vsmappings.VSMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vsmappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vsmappings</em>' containment reference list.
	 * @see vsmappings.VsmappingsPackage#getVSMappingRepository_Vsmappings()
	 * @model containment="true"
	 * @generated
	 */
	EList<VSMapping> getVsmappings();

} // VSMappingRepository

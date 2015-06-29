/**
 */
package violationstrategymappings;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Violation Strategy Mapping Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link violationstrategymappings.ViolationStrategyMappingRepository#getVsmappings <em>Vsmappings</em>}</li>
 * </ul>
 *
 * @see violationstrategymappings.ViolationstrategymappingsPackage#getViolationStrategyMappingRepository()
 * @model
 * @generated
 */
public interface ViolationStrategyMappingRepository extends EObject, org.palladiosimulator.pcm.core.entity.Entity {
	/**
	 * Returns the value of the '<em><b>Vsmappings</b></em>' containment reference list.
	 * The list contents are of type {@link violationstrategymappings.ViolationStrategyMapping}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vsmappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vsmappings</em>' containment reference list.
	 * @see violationstrategymappings.ViolationstrategymappingsPackage#getViolationStrategyMappingRepository_Vsmappings()
	 * @model containment="true"
	 * @generated
	 */
	EList<ViolationStrategyMapping> getVsmappings();

} // ViolationStrategyMappingRepository

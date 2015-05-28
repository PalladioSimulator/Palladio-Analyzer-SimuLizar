/**
 */
package strategies;

import de.uka.ipd.sdq.pcm.core.entity.Entity;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Runtime Strategies Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link strategies.RuntimeStrategiesModel#getStrategies <em>Strategies</em>}</li>
 * </ul>
 * </p>
 *
 * @see strategies.StrategiesPackage#getRuntimeStrategiesModel()
 * @model
 * @generated
 */
public interface RuntimeStrategiesModel extends EObject, Entity {
	/**
	 * Returns the value of the '<em><b>Strategies</b></em>' containment reference list.
	 * The list contents are of type {@link strategies.Strategy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strategies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategies</em>' containment reference list.
	 * @see strategies.StrategiesPackage#getRuntimeStrategiesModel_Strategies()
	 * @model containment="true"
	 * @generated
	 */
	EList<Strategy> getStrategies();

} // RuntimeStrategiesModel

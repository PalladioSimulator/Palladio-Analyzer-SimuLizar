/**
 */
package strategies;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Strategy Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link strategies.StrategyType#getBehavior <em>Behavior</em>}</li>
 * </ul>
 *
 * @see strategies.StrategiesPackage#getStrategyType()
 * @model
 * @generated
 */
public interface StrategyType extends EObject, org.palladiosimulator.pcm.seff.ResourceDemandingSEFF, org.palladiosimulator.pcm.core.entity.Entity {
	/**
	 * Returns the value of the '<em><b>Behavior</b></em>' reference list.
	 * The list contents are of type {@link strategies.ReconfigurationAction}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behavior</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behavior</em>' reference list.
	 * @see strategies.StrategiesPackage#getStrategyType_Behavior()
	 * @model
	 * @generated
	 */
	EList<ReconfigurationAction> getBehavior();

} // StrategyType

/**
 */
package org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration;

import org.eclipse.emf.ecore.EFactory;
import org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.impl.QvtoreconfigurationFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.QvtoreconfigurationPackage
 * @generated
 */
public interface QvtoreconfigurationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QvtoreconfigurationFactory eINSTANCE = QvtoreconfigurationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Qvto Transformation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Qvto Transformation</em>'.
	 * @generated
	 */
	QvtoTransformation createQvtoTransformation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	QvtoreconfigurationPackage getQvtoreconfigurationPackage();

} //QvtoreconfigurationFactory

/**
 */
package org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QvtoreconfigurationFactoryImpl extends EFactoryImpl implements QvtoreconfigurationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static QvtoreconfigurationFactory init() {
		try {
			QvtoreconfigurationFactory theQvtoreconfigurationFactory = (QvtoreconfigurationFactory)EPackage.Registry.INSTANCE.getEFactory(QvtoreconfigurationPackage.eNS_URI);
			if (theQvtoreconfigurationFactory != null) {
				return theQvtoreconfigurationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new QvtoreconfigurationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QvtoreconfigurationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case QvtoreconfigurationPackage.QVTO_TRANSFORMATION: return createQvtoTransformation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QvtoTransformation createQvtoTransformation() {
		QvtoTransformationImpl qvtoTransformation = new QvtoTransformationImpl();
		return qvtoTransformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QvtoreconfigurationPackage getQvtoreconfigurationPackage() {
		return (QvtoreconfigurationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static QvtoreconfigurationPackage getPackage() {
		return QvtoreconfigurationPackage.eINSTANCE;
	}

} //QvtoreconfigurationFactoryImpl

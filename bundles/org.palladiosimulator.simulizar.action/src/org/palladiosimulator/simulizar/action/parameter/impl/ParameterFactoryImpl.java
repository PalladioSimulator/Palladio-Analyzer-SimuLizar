/**
 */
package org.palladiosimulator.simulizar.action.parameter.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.palladiosimulator.simulizar.action.parameter.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ParameterFactoryImpl extends EFactoryImpl implements ParameterFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParameterFactory init() {
		try {
			ParameterFactory theParameterFactory = (ParameterFactory) EPackage.Registry.INSTANCE
					.getEFactory(ParameterPackage.eNS_URI);
			if (theParameterFactory != null) {
				return theParameterFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ParameterFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterFactoryImpl() {
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
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE:
			return createControllerCallInputVariableUsage();
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION:
			return createControllerCallInputVariableUsageCollection();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ControllerCallInputVariableUsage createControllerCallInputVariableUsage() {
		ControllerCallInputVariableUsageImpl controllerCallInputVariableUsage = new ControllerCallInputVariableUsageImpl();
		return controllerCallInputVariableUsage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ControllerCallInputVariableUsageCollection createControllerCallInputVariableUsageCollection() {
		ControllerCallInputVariableUsageCollectionImpl controllerCallInputVariableUsageCollection = new ControllerCallInputVariableUsageCollectionImpl();
		return controllerCallInputVariableUsageCollection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ParameterPackage getParameterPackage() {
		return (ParameterPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ParameterPackage getPackage() {
		return ParameterPackage.eINSTANCE;
	}

} //ParameterFactoryImpl

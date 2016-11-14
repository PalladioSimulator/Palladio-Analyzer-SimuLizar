/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.palladiosimulator.simulizar.reconfigurationrule.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReconfigurationruleFactoryImpl extends EFactoryImpl implements ReconfigurationruleFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReconfigurationruleFactory init() {
		try {
			ReconfigurationruleFactory theReconfigurationruleFactory = (ReconfigurationruleFactory)EPackage.Registry.INSTANCE.getEFactory(ReconfigurationrulePackage.eNS_URI);
			if (theReconfigurationruleFactory != null) {
				return theReconfigurationruleFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ReconfigurationruleFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReconfigurationruleFactoryImpl() {
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
			case ReconfigurationrulePackage.RECONFIGURATION: return createReconfiguration();
			case ReconfigurationrulePackage.TACTIC: return createTactic();
			case ReconfigurationrulePackage.STRATEGY: return createStrategy();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Reconfiguration createReconfiguration() {
		ReconfigurationImpl reconfiguration = new ReconfigurationImpl();
		return reconfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Tactic createTactic() {
		TacticImpl tactic = new TacticImpl();
		return tactic;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Strategy createStrategy() {
		StrategyImpl strategy = new StrategyImpl();
		return strategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReconfigurationrulePackage getReconfigurationrulePackage() {
		return (ReconfigurationrulePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ReconfigurationrulePackage getPackage() {
		return ReconfigurationrulePackage.eINSTANCE;
	}

} //ReconfigurationruleFactoryImpl

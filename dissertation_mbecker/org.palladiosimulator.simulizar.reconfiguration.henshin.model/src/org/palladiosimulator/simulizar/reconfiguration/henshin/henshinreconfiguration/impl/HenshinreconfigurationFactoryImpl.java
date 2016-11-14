/**
 */
package org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class HenshinreconfigurationFactoryImpl extends EFactoryImpl implements HenshinreconfigurationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static HenshinreconfigurationFactory init() {
		try {
			HenshinreconfigurationFactory theHenshinreconfigurationFactory = (HenshinreconfigurationFactory)EPackage.Registry.INSTANCE.getEFactory(HenshinreconfigurationPackage.eNS_URI);
			if (theHenshinreconfigurationFactory != null) {
				return theHenshinreconfigurationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new HenshinreconfigurationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HenshinreconfigurationFactoryImpl() {
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
			case HenshinreconfigurationPackage.HENSHIN_TRANSFORMATION: return createHenshinTransformation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HenshinTransformation createHenshinTransformation() {
		HenshinTransformationImpl henshinTransformation = new HenshinTransformationImpl();
		return henshinTransformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HenshinreconfigurationPackage getHenshinreconfigurationPackage() {
		return (HenshinreconfigurationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static HenshinreconfigurationPackage getPackage() {
		return HenshinreconfigurationPackage.eINSTANCE;
	}

} //HenshinreconfigurationFactoryImpl

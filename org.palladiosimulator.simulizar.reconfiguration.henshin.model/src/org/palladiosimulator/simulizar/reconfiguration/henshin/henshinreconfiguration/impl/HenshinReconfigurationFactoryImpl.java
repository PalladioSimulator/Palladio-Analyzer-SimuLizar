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
public class HenshinReconfigurationFactoryImpl extends EFactoryImpl implements HenshinReconfigurationFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static HenshinReconfigurationFactory init() {
        try {
            HenshinReconfigurationFactory theHenshinReconfigurationFactory = (HenshinReconfigurationFactory)EPackage.Registry.INSTANCE.getEFactory(HenshinReconfigurationPackage.eNS_URI);
            if (theHenshinReconfigurationFactory != null) {
                return theHenshinReconfigurationFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new HenshinReconfigurationFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HenshinReconfigurationFactoryImpl() {
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
            case HenshinReconfigurationPackage.HENSHIN_TRANSFORMATION: return createHenshinTransformation();
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
    public HenshinReconfigurationPackage getHenshinReconfigurationPackage() {
        return (HenshinReconfigurationPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static HenshinReconfigurationPackage getPackage() {
        return HenshinReconfigurationPackage.eINSTANCE;
    }

} //HenshinReconfigurationFactoryImpl

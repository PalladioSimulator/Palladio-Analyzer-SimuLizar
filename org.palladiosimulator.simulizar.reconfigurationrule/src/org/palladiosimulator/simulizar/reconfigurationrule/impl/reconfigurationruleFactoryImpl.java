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
public class reconfigurationruleFactoryImpl extends EFactoryImpl implements reconfigurationruleFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static reconfigurationruleFactory init() {
        try {
            reconfigurationruleFactory thereconfigurationruleFactory = (reconfigurationruleFactory)EPackage.Registry.INSTANCE.getEFactory(reconfigurationrulePackage.eNS_URI);
            if (thereconfigurationruleFactory != null) {
                return thereconfigurationruleFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new reconfigurationruleFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public reconfigurationruleFactoryImpl() {
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
            case reconfigurationrulePackage.RECONFIGURATION_RULE_SET: return createReconfigurationRuleSet();
            case reconfigurationrulePackage.RECONFIGURATION_RULE: return createReconfigurationRule();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReconfigurationRuleSet createReconfigurationRuleSet() {
        ReconfigurationRuleSetImpl reconfigurationRuleSet = new ReconfigurationRuleSetImpl();
        return reconfigurationRuleSet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ReconfigurationRule createReconfigurationRule() {
        ReconfigurationRuleImpl reconfigurationRule = new ReconfigurationRuleImpl();
        return reconfigurationRule;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public reconfigurationrulePackage getreconfigurationrulePackage() {
        return (reconfigurationrulePackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static reconfigurationrulePackage getPackage() {
        return reconfigurationrulePackage.eINSTANCE;
    }

} //reconfigurationruleFactoryImpl

/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.palladiosimulator.simulizar.reconfigurationrule.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage
 * @generated
 */
public class reconfigurationruleSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static reconfigurationrulePackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public reconfigurationruleSwitch() {
        if (modelPackage == null) {
            modelPackage = reconfigurationrulePackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case reconfigurationrulePackage.RECONFIGURATION_RULE_SET: {
                ReconfigurationRuleSet reconfigurationRuleSet = (ReconfigurationRuleSet)theEObject;
                T result = caseReconfigurationRuleSet(reconfigurationRuleSet);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case reconfigurationrulePackage.RECONFIGURATION_RULE: {
                ReconfigurationRule reconfigurationRule = (ReconfigurationRule)theEObject;
                T result = caseReconfigurationRule(reconfigurationRule);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case reconfigurationrulePackage.MODEL_TRANSFORMATION: {
                ModelTransformation<?> modelTransformation = (ModelTransformation<?>)theEObject;
                T result = caseModelTransformation(modelTransformation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Reconfiguration Rule Set</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Reconfiguration Rule Set</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseReconfigurationRuleSet(ReconfigurationRuleSet object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Reconfiguration Rule</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Reconfiguration Rule</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseReconfigurationRule(ReconfigurationRule object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Model Transformation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Model Transformation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public <ExecutableTransformationElement extends Object> T caseModelTransformation(ModelTransformation<ExecutableTransformationElement> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} //reconfigurationruleSwitch

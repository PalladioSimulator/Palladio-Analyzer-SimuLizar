/**
 */
package dlim.impl;

import de.uka.ipd.sdq.pcm.parameter.VariableUsage;

import dlim.DlimPackage;
import dlim.InputParameterCharacterizationContainer;
import dlim.Sequence;

import dlim.TimeDependentWorkFunctionContainer;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Parameter Characterization Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dlim.impl.InputParameterCharacterizationContainerImpl#getVariableUsage <em>Variable Usage</em>}</li>
 *   <li>{@link dlim.impl.InputParameterCharacterizationContainerImpl#getParameterValue <em>Parameter Value</em>}</li>
 *   <li>{@link dlim.impl.InputParameterCharacterizationContainerImpl#getTDWFC_ParameterCharacterizationContainers <em>TDWFC Parameter Characterization Containers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputParameterCharacterizationContainerImpl extends MinimalEObjectImpl.Container implements InputParameterCharacterizationContainer {
	/**
	 * The cached value of the '{@link #getVariableUsage() <em>Variable Usage</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVariableUsage()
	 * @generated
	 * @ordered
	 */
	protected VariableUsage variableUsage;

	/**
	 * The cached value of the '{@link #getParameterValue() <em>Parameter Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterValue()
	 * @generated
	 * @ordered
	 */
	protected Sequence parameterValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InputParameterCharacterizationContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DlimPackage.Literals.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VariableUsage getVariableUsage() {
		return variableUsage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVariableUsage(VariableUsage newVariableUsage, NotificationChain msgs) {
		VariableUsage oldVariableUsage = variableUsage;
		variableUsage = newVariableUsage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__VARIABLE_USAGE, oldVariableUsage, newVariableUsage);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVariableUsage(VariableUsage newVariableUsage) {
		if (newVariableUsage != variableUsage) {
			NotificationChain msgs = null;
			if (variableUsage != null)
				msgs = ((InternalEObject)variableUsage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__VARIABLE_USAGE, null, msgs);
			if (newVariableUsage != null)
				msgs = ((InternalEObject)newVariableUsage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__VARIABLE_USAGE, null, msgs);
			msgs = basicSetVariableUsage(newVariableUsage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__VARIABLE_USAGE, newVariableUsage, newVariableUsage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sequence getParameterValue() {
		return parameterValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParameterValue(Sequence newParameterValue, NotificationChain msgs) {
		Sequence oldParameterValue = parameterValue;
		parameterValue = newParameterValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__PARAMETER_VALUE, oldParameterValue, newParameterValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterValue(Sequence newParameterValue) {
		if (newParameterValue != parameterValue) {
			NotificationChain msgs = null;
			if (parameterValue != null)
				msgs = ((InternalEObject)parameterValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__PARAMETER_VALUE, null, msgs);
			if (newParameterValue != null)
				msgs = ((InternalEObject)newParameterValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__PARAMETER_VALUE, null, msgs);
			msgs = basicSetParameterValue(newParameterValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__PARAMETER_VALUE, newParameterValue, newParameterValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeDependentWorkFunctionContainer getTDWFC_ParameterCharacterizationContainers() {
		if (eContainerFeatureID() != DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__TDWFC_PARAMETER_CHARACTERIZATION_CONTAINERS) return null;
		return (TimeDependentWorkFunctionContainer)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTDWFC_ParameterCharacterizationContainers(TimeDependentWorkFunctionContainer newTDWFC_ParameterCharacterizationContainers, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newTDWFC_ParameterCharacterizationContainers, DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__TDWFC_PARAMETER_CHARACTERIZATION_CONTAINERS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTDWFC_ParameterCharacterizationContainers(TimeDependentWorkFunctionContainer newTDWFC_ParameterCharacterizationContainers) {
		if (newTDWFC_ParameterCharacterizationContainers != eInternalContainer() || (eContainerFeatureID() != DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__TDWFC_PARAMETER_CHARACTERIZATION_CONTAINERS && newTDWFC_ParameterCharacterizationContainers != null)) {
			if (EcoreUtil.isAncestor(this, newTDWFC_ParameterCharacterizationContainers))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTDWFC_ParameterCharacterizationContainers != null)
				msgs = ((InternalEObject)newTDWFC_ParameterCharacterizationContainers).eInverseAdd(this, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PARAMETER_CHARATERIZATION_CONTAINERS, TimeDependentWorkFunctionContainer.class, msgs);
			msgs = basicSetTDWFC_ParameterCharacterizationContainers(newTDWFC_ParameterCharacterizationContainers, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__TDWFC_PARAMETER_CHARACTERIZATION_CONTAINERS, newTDWFC_ParameterCharacterizationContainers, newTDWFC_ParameterCharacterizationContainers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__TDWFC_PARAMETER_CHARACTERIZATION_CONTAINERS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetTDWFC_ParameterCharacterizationContainers((TimeDependentWorkFunctionContainer)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__VARIABLE_USAGE:
				return basicSetVariableUsage(null, msgs);
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__PARAMETER_VALUE:
				return basicSetParameterValue(null, msgs);
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__TDWFC_PARAMETER_CHARACTERIZATION_CONTAINERS:
				return basicSetTDWFC_ParameterCharacterizationContainers(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__TDWFC_PARAMETER_CHARACTERIZATION_CONTAINERS:
				return eInternalContainer().eInverseRemove(this, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PARAMETER_CHARATERIZATION_CONTAINERS, TimeDependentWorkFunctionContainer.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__VARIABLE_USAGE:
				return getVariableUsage();
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__PARAMETER_VALUE:
				return getParameterValue();
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__TDWFC_PARAMETER_CHARACTERIZATION_CONTAINERS:
				return getTDWFC_ParameterCharacterizationContainers();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__VARIABLE_USAGE:
				setVariableUsage((VariableUsage)newValue);
				return;
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__PARAMETER_VALUE:
				setParameterValue((Sequence)newValue);
				return;
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__TDWFC_PARAMETER_CHARACTERIZATION_CONTAINERS:
				setTDWFC_ParameterCharacterizationContainers((TimeDependentWorkFunctionContainer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__VARIABLE_USAGE:
				setVariableUsage((VariableUsage)null);
				return;
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__PARAMETER_VALUE:
				setParameterValue((Sequence)null);
				return;
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__TDWFC_PARAMETER_CHARACTERIZATION_CONTAINERS:
				setTDWFC_ParameterCharacterizationContainers((TimeDependentWorkFunctionContainer)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__VARIABLE_USAGE:
				return variableUsage != null;
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__PARAMETER_VALUE:
				return parameterValue != null;
			case DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__TDWFC_PARAMETER_CHARACTERIZATION_CONTAINERS:
				return getTDWFC_ParameterCharacterizationContainers() != null;
		}
		return super.eIsSet(featureID);
	}

} //InputParameterCharacterizationContainerImpl

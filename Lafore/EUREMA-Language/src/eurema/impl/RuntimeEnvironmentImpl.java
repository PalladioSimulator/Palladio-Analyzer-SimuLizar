/**
 */
package eurema.impl;

import eurema.Architecture;
import eurema.EuremaPackage;
import eurema.ExecutionContext;
import eurema.RuntimeEnvironment;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Runtime Environment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.RuntimeEnvironmentImpl#getExecutions <em>Executions</em>}</li>
 *   <li>{@link eurema.impl.RuntimeEnvironmentImpl#getArchitecture <em>Architecture</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RuntimeEnvironmentImpl extends MinimalEObjectImpl.Container implements RuntimeEnvironment {
	/**
	 * The cached value of the '{@link #getExecutions() <em>Executions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutions()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionContext> executions;

	/**
	 * The cached value of the '{@link #getArchitecture() <em>Architecture</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArchitecture()
	 * @generated
	 * @ordered
	 */
	protected Architecture architecture;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RuntimeEnvironmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.RUNTIME_ENVIRONMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionContext> getExecutions() {
		if (executions == null) {
			executions = new EObjectContainmentWithInverseEList.Resolving<ExecutionContext>(ExecutionContext.class, this, EuremaPackage.RUNTIME_ENVIRONMENT__EXECUTIONS, EuremaPackage.EXECUTION_CONTEXT__ENVIRONMENT);
		}
		return executions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Architecture getArchitecture() {
		if (architecture != null && architecture.eIsProxy()) {
			InternalEObject oldArchitecture = (InternalEObject)architecture;
			architecture = (Architecture)eResolveProxy(oldArchitecture);
			if (architecture != oldArchitecture) {
				InternalEObject newArchitecture = (InternalEObject)architecture;
				NotificationChain msgs =  oldArchitecture.eInverseRemove(this, EuremaPackage.ARCHITECTURE__ENVIRONMENT, Architecture.class, null);
				if (newArchitecture.eInternalContainer() == null) {
					msgs =  newArchitecture.eInverseAdd(this, EuremaPackage.ARCHITECTURE__ENVIRONMENT, Architecture.class, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.RUNTIME_ENVIRONMENT__ARCHITECTURE, oldArchitecture, architecture));
			}
		}
		return architecture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Architecture basicGetArchitecture() {
		return architecture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArchitecture(Architecture newArchitecture, NotificationChain msgs) {
		Architecture oldArchitecture = architecture;
		architecture = newArchitecture;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EuremaPackage.RUNTIME_ENVIRONMENT__ARCHITECTURE, oldArchitecture, newArchitecture);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArchitecture(Architecture newArchitecture) {
		if (newArchitecture != architecture) {
			NotificationChain msgs = null;
			if (architecture != null)
				msgs = ((InternalEObject)architecture).eInverseRemove(this, EuremaPackage.ARCHITECTURE__ENVIRONMENT, Architecture.class, msgs);
			if (newArchitecture != null)
				msgs = ((InternalEObject)newArchitecture).eInverseAdd(this, EuremaPackage.ARCHITECTURE__ENVIRONMENT, Architecture.class, msgs);
			msgs = basicSetArchitecture(newArchitecture, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.RUNTIME_ENVIRONMENT__ARCHITECTURE, newArchitecture, newArchitecture));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EuremaPackage.RUNTIME_ENVIRONMENT__EXECUTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExecutions()).basicAdd(otherEnd, msgs);
			case EuremaPackage.RUNTIME_ENVIRONMENT__ARCHITECTURE:
				if (architecture != null)
					msgs = ((InternalEObject)architecture).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EuremaPackage.RUNTIME_ENVIRONMENT__ARCHITECTURE, null, msgs);
				return basicSetArchitecture((Architecture)otherEnd, msgs);
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
			case EuremaPackage.RUNTIME_ENVIRONMENT__EXECUTIONS:
				return ((InternalEList<?>)getExecutions()).basicRemove(otherEnd, msgs);
			case EuremaPackage.RUNTIME_ENVIRONMENT__ARCHITECTURE:
				return basicSetArchitecture(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EuremaPackage.RUNTIME_ENVIRONMENT__EXECUTIONS:
				return getExecutions();
			case EuremaPackage.RUNTIME_ENVIRONMENT__ARCHITECTURE:
				if (resolve) return getArchitecture();
				return basicGetArchitecture();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EuremaPackage.RUNTIME_ENVIRONMENT__EXECUTIONS:
				getExecutions().clear();
				getExecutions().addAll((Collection<? extends ExecutionContext>)newValue);
				return;
			case EuremaPackage.RUNTIME_ENVIRONMENT__ARCHITECTURE:
				setArchitecture((Architecture)newValue);
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
			case EuremaPackage.RUNTIME_ENVIRONMENT__EXECUTIONS:
				getExecutions().clear();
				return;
			case EuremaPackage.RUNTIME_ENVIRONMENT__ARCHITECTURE:
				setArchitecture((Architecture)null);
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
			case EuremaPackage.RUNTIME_ENVIRONMENT__EXECUTIONS:
				return executions != null && !executions.isEmpty();
			case EuremaPackage.RUNTIME_ENVIRONMENT__ARCHITECTURE:
				return architecture != null;
		}
		return super.eIsSet(featureID);
	}

} //RuntimeEnvironmentImpl

/**
 */
package eurema.impl;

import eurema.Architecture;
import eurema.EuremaPackage;
import eurema.Layer;
import eurema.ModelResourceSet;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Architecture</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.ArchitectureImpl#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link eurema.impl.ArchitectureImpl#getLayers <em>Layers</em>}</li>
 *   <li>{@link eurema.impl.ArchitectureImpl#getModelResourceSet <em>Model Resource Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ArchitectureImpl extends MinimalEObjectImpl.Container implements Architecture {
	/**
	 * The cached value of the '{@link #getLayers() <em>Layers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLayers()
	 * @generated
	 * @ordered
	 */
	protected EList<Layer> layers;

	/**
	 * The cached value of the '{@link #getModelResourceSet() <em>Model Resource Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelResourceSet()
	 * @generated
	 * @ordered
	 */
	protected ModelResourceSet modelResourceSet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArchitectureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.ARCHITECTURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuntimeEnvironment getEnvironment() {
		if (eContainerFeatureID() != EuremaPackage.ARCHITECTURE__ENVIRONMENT) return null;
		return (RuntimeEnvironment)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuntimeEnvironment basicGetEnvironment() {
		if (eContainerFeatureID() != EuremaPackage.ARCHITECTURE__ENVIRONMENT) return null;
		return (RuntimeEnvironment)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEnvironment(RuntimeEnvironment newEnvironment, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newEnvironment, EuremaPackage.ARCHITECTURE__ENVIRONMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnvironment(RuntimeEnvironment newEnvironment) {
		if (newEnvironment != eInternalContainer() || (eContainerFeatureID() != EuremaPackage.ARCHITECTURE__ENVIRONMENT && newEnvironment != null)) {
			if (EcoreUtil.isAncestor(this, newEnvironment))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEnvironment != null)
				msgs = ((InternalEObject)newEnvironment).eInverseAdd(this, EuremaPackage.RUNTIME_ENVIRONMENT__ARCHITECTURE, RuntimeEnvironment.class, msgs);
			msgs = basicSetEnvironment(newEnvironment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.ARCHITECTURE__ENVIRONMENT, newEnvironment, newEnvironment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Layer> getLayers() {
		if (layers == null) {
			layers = new EObjectContainmentWithInverseEList.Resolving<Layer>(Layer.class, this, EuremaPackage.ARCHITECTURE__LAYERS, EuremaPackage.LAYER__ARCHITECTURE);
		}
		return layers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelResourceSet getModelResourceSet() {
		if (modelResourceSet != null && modelResourceSet.eIsProxy()) {
			InternalEObject oldModelResourceSet = (InternalEObject)modelResourceSet;
			modelResourceSet = (ModelResourceSet)eResolveProxy(oldModelResourceSet);
			if (modelResourceSet != oldModelResourceSet) {
				InternalEObject newModelResourceSet = (InternalEObject)modelResourceSet;
				NotificationChain msgs = oldModelResourceSet.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EuremaPackage.ARCHITECTURE__MODEL_RESOURCE_SET, null, null);
				if (newModelResourceSet.eInternalContainer() == null) {
					msgs = newModelResourceSet.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EuremaPackage.ARCHITECTURE__MODEL_RESOURCE_SET, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.ARCHITECTURE__MODEL_RESOURCE_SET, oldModelResourceSet, modelResourceSet));
			}
		}
		return modelResourceSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelResourceSet basicGetModelResourceSet() {
		return modelResourceSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModelResourceSet(ModelResourceSet newModelResourceSet, NotificationChain msgs) {
		ModelResourceSet oldModelResourceSet = modelResourceSet;
		modelResourceSet = newModelResourceSet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EuremaPackage.ARCHITECTURE__MODEL_RESOURCE_SET, oldModelResourceSet, newModelResourceSet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModelResourceSet(ModelResourceSet newModelResourceSet) {
		if (newModelResourceSet != modelResourceSet) {
			NotificationChain msgs = null;
			if (modelResourceSet != null)
				msgs = ((InternalEObject)modelResourceSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EuremaPackage.ARCHITECTURE__MODEL_RESOURCE_SET, null, msgs);
			if (newModelResourceSet != null)
				msgs = ((InternalEObject)newModelResourceSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EuremaPackage.ARCHITECTURE__MODEL_RESOURCE_SET, null, msgs);
			msgs = basicSetModelResourceSet(newModelResourceSet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.ARCHITECTURE__MODEL_RESOURCE_SET, newModelResourceSet, newModelResourceSet));
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
			case EuremaPackage.ARCHITECTURE__ENVIRONMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetEnvironment((RuntimeEnvironment)otherEnd, msgs);
			case EuremaPackage.ARCHITECTURE__LAYERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLayers()).basicAdd(otherEnd, msgs);
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
			case EuremaPackage.ARCHITECTURE__ENVIRONMENT:
				return basicSetEnvironment(null, msgs);
			case EuremaPackage.ARCHITECTURE__LAYERS:
				return ((InternalEList<?>)getLayers()).basicRemove(otherEnd, msgs);
			case EuremaPackage.ARCHITECTURE__MODEL_RESOURCE_SET:
				return basicSetModelResourceSet(null, msgs);
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
			case EuremaPackage.ARCHITECTURE__ENVIRONMENT:
				return eInternalContainer().eInverseRemove(this, EuremaPackage.RUNTIME_ENVIRONMENT__ARCHITECTURE, RuntimeEnvironment.class, msgs);
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
			case EuremaPackage.ARCHITECTURE__ENVIRONMENT:
				if (resolve) return getEnvironment();
				return basicGetEnvironment();
			case EuremaPackage.ARCHITECTURE__LAYERS:
				return getLayers();
			case EuremaPackage.ARCHITECTURE__MODEL_RESOURCE_SET:
				if (resolve) return getModelResourceSet();
				return basicGetModelResourceSet();
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
			case EuremaPackage.ARCHITECTURE__ENVIRONMENT:
				setEnvironment((RuntimeEnvironment)newValue);
				return;
			case EuremaPackage.ARCHITECTURE__LAYERS:
				getLayers().clear();
				getLayers().addAll((Collection<? extends Layer>)newValue);
				return;
			case EuremaPackage.ARCHITECTURE__MODEL_RESOURCE_SET:
				setModelResourceSet((ModelResourceSet)newValue);
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
			case EuremaPackage.ARCHITECTURE__ENVIRONMENT:
				setEnvironment((RuntimeEnvironment)null);
				return;
			case EuremaPackage.ARCHITECTURE__LAYERS:
				getLayers().clear();
				return;
			case EuremaPackage.ARCHITECTURE__MODEL_RESOURCE_SET:
				setModelResourceSet((ModelResourceSet)null);
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
			case EuremaPackage.ARCHITECTURE__ENVIRONMENT:
				return basicGetEnvironment() != null;
			case EuremaPackage.ARCHITECTURE__LAYERS:
				return layers != null && !layers.isEmpty();
			case EuremaPackage.ARCHITECTURE__MODEL_RESOURCE_SET:
				return modelResourceSet != null;
		}
		return super.eIsSet(featureID);
	}

} //ArchitectureImpl

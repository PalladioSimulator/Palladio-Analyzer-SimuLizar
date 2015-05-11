/**
 */
package eurema.impl;

import eurema.EuremaPackage;
import eurema.MegamodelModule;
import eurema.ModelResource;
import eurema.RuntimeModel;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.ModelResourceImpl#getURI <em>URI</em>}</li>
 *   <li>{@link eurema.impl.ModelResourceImpl#getName <em>Name</em>}</li>
 *   <li>{@link eurema.impl.ModelResourceImpl#getBoundByRuntimeModels <em>Bound By Runtime Models</em>}</li>
 *   <li>{@link eurema.impl.ModelResourceImpl#getBoundByMegamodelModules <em>Bound By Megamodel Modules</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelResourceImpl extends MinimalEObjectImpl.Container implements ModelResource {
	/**
	 * The default value of the '{@link #getURI() <em>URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getURI()
	 * @generated
	 * @ordered
	 */
	protected static final String URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getURI() <em>URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getURI()
	 * @generated
	 * @ordered
	 */
	protected String uri = URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBoundByRuntimeModels() <em>Bound By Runtime Models</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBoundByRuntimeModels()
	 * @generated
	 * @ordered
	 */
	protected EList<RuntimeModel> boundByRuntimeModels;

	/**
	 * The cached value of the '{@link #getBoundByMegamodelModules() <em>Bound By Megamodel Modules</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBoundByMegamodelModules()
	 * @generated
	 * @ordered
	 */
	protected EList<MegamodelModule> boundByMegamodelModules;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.MODEL_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getURI() {
		return uri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setURI(String newURI) {
		String oldURI = uri;
		uri = newURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MODEL_RESOURCE__URI, oldURI, uri));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MODEL_RESOURCE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RuntimeModel> getBoundByRuntimeModels() {
		if (boundByRuntimeModels == null) {
			boundByRuntimeModels = new EObjectWithInverseResolvingEList<RuntimeModel>(RuntimeModel.class, this, EuremaPackage.MODEL_RESOURCE__BOUND_BY_RUNTIME_MODELS, EuremaPackage.RUNTIME_MODEL__BINDING);
		}
		return boundByRuntimeModels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MegamodelModule> getBoundByMegamodelModules() {
		if (boundByMegamodelModules == null) {
			boundByMegamodelModules = new EObjectWithInverseResolvingEList<MegamodelModule>(MegamodelModule.class, this, EuremaPackage.MODEL_RESOURCE__BOUND_BY_MEGAMODEL_MODULES, EuremaPackage.MEGAMODEL_MODULE__BINDING);
		}
		return boundByMegamodelModules;
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
			case EuremaPackage.MODEL_RESOURCE__BOUND_BY_RUNTIME_MODELS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBoundByRuntimeModels()).basicAdd(otherEnd, msgs);
			case EuremaPackage.MODEL_RESOURCE__BOUND_BY_MEGAMODEL_MODULES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBoundByMegamodelModules()).basicAdd(otherEnd, msgs);
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
			case EuremaPackage.MODEL_RESOURCE__BOUND_BY_RUNTIME_MODELS:
				return ((InternalEList<?>)getBoundByRuntimeModels()).basicRemove(otherEnd, msgs);
			case EuremaPackage.MODEL_RESOURCE__BOUND_BY_MEGAMODEL_MODULES:
				return ((InternalEList<?>)getBoundByMegamodelModules()).basicRemove(otherEnd, msgs);
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
			case EuremaPackage.MODEL_RESOURCE__URI:
				return getURI();
			case EuremaPackage.MODEL_RESOURCE__NAME:
				return getName();
			case EuremaPackage.MODEL_RESOURCE__BOUND_BY_RUNTIME_MODELS:
				return getBoundByRuntimeModels();
			case EuremaPackage.MODEL_RESOURCE__BOUND_BY_MEGAMODEL_MODULES:
				return getBoundByMegamodelModules();
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
			case EuremaPackage.MODEL_RESOURCE__URI:
				setURI((String)newValue);
				return;
			case EuremaPackage.MODEL_RESOURCE__NAME:
				setName((String)newValue);
				return;
			case EuremaPackage.MODEL_RESOURCE__BOUND_BY_RUNTIME_MODELS:
				getBoundByRuntimeModels().clear();
				getBoundByRuntimeModels().addAll((Collection<? extends RuntimeModel>)newValue);
				return;
			case EuremaPackage.MODEL_RESOURCE__BOUND_BY_MEGAMODEL_MODULES:
				getBoundByMegamodelModules().clear();
				getBoundByMegamodelModules().addAll((Collection<? extends MegamodelModule>)newValue);
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
			case EuremaPackage.MODEL_RESOURCE__URI:
				setURI(URI_EDEFAULT);
				return;
			case EuremaPackage.MODEL_RESOURCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EuremaPackage.MODEL_RESOURCE__BOUND_BY_RUNTIME_MODELS:
				getBoundByRuntimeModels().clear();
				return;
			case EuremaPackage.MODEL_RESOURCE__BOUND_BY_MEGAMODEL_MODULES:
				getBoundByMegamodelModules().clear();
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
			case EuremaPackage.MODEL_RESOURCE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case EuremaPackage.MODEL_RESOURCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EuremaPackage.MODEL_RESOURCE__BOUND_BY_RUNTIME_MODELS:
				return boundByRuntimeModels != null && !boundByRuntimeModels.isEmpty();
			case EuremaPackage.MODEL_RESOURCE__BOUND_BY_MEGAMODEL_MODULES:
				return boundByMegamodelModules != null && !boundByMegamodelModules.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (URI: ");
		result.append(uri);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ModelResourceImpl

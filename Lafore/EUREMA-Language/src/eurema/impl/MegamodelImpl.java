/**
 */
package eurema.impl;

import eurema.DecisionOperation;
import eurema.EuremaPackage;
import eurema.FinalOperation;
import eurema.InitialOperation;
import eurema.Megamodel;
import eurema.MegamodelModule;
import eurema.Model;
import eurema.OperationBehavior;
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
 * An implementation of the model object '<em><b>Megamodel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.MegamodelImpl#getInitialOperations <em>Initial Operations</em>}</li>
 *   <li>{@link eurema.impl.MegamodelImpl#getFinalOperations <em>Final Operations</em>}</li>
 *   <li>{@link eurema.impl.MegamodelImpl#getDecisionOperations <em>Decision Operations</em>}</li>
 *   <li>{@link eurema.impl.MegamodelImpl#getBehavior <em>Behavior</em>}</li>
 *   <li>{@link eurema.impl.MegamodelImpl#getModels <em>Models</em>}</li>
 *   <li>{@link eurema.impl.MegamodelImpl#getUid <em>Uid</em>}</li>
 *   <li>{@link eurema.impl.MegamodelImpl#getName <em>Name</em>}</li>
 *   <li>{@link eurema.impl.MegamodelImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link eurema.impl.MegamodelImpl#getModule <em>Module</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MegamodelImpl extends MinimalEObjectImpl.Container implements Megamodel {
	/**
	 * The cached value of the '{@link #getInitialOperations() <em>Initial Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<InitialOperation> initialOperations;

	/**
	 * The cached value of the '{@link #getFinalOperations() <em>Final Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinalOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<FinalOperation> finalOperations;

	/**
	 * The cached value of the '{@link #getDecisionOperations() <em>Decision Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDecisionOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<DecisionOperation> decisionOperations;

	/**
	 * The cached value of the '{@link #getBehavior() <em>Behavior</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBehavior()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationBehavior> behavior;

	/**
	 * The cached value of the '{@link #getModels() <em>Models</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModels()
	 * @generated
	 * @ordered
	 */
	protected EList<Model> models;

	/**
	 * The default value of the '{@link #getUid() <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUid()
	 * @generated
	 * @ordered
	 */
	protected static final String UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUid() <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUid()
	 * @generated
	 * @ordered
	 */
	protected String uid = UID_EDEFAULT;

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
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MegamodelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.MEGAMODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InitialOperation> getInitialOperations() {
		if (initialOperations == null) {
			initialOperations = new EObjectContainmentWithInverseEList.Resolving<InitialOperation>(InitialOperation.class, this, EuremaPackage.MEGAMODEL__INITIAL_OPERATIONS, EuremaPackage.INITIAL_OPERATION__MEGAMODEL);
		}
		return initialOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FinalOperation> getFinalOperations() {
		if (finalOperations == null) {
			finalOperations = new EObjectContainmentWithInverseEList.Resolving<FinalOperation>(FinalOperation.class, this, EuremaPackage.MEGAMODEL__FINAL_OPERATIONS, EuremaPackage.FINAL_OPERATION__MEGAMODEL);
		}
		return finalOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DecisionOperation> getDecisionOperations() {
		if (decisionOperations == null) {
			decisionOperations = new EObjectContainmentWithInverseEList.Resolving<DecisionOperation>(DecisionOperation.class, this, EuremaPackage.MEGAMODEL__DECISION_OPERATIONS, EuremaPackage.DECISION_OPERATION__MEGAMODEL);
		}
		return decisionOperations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationBehavior> getBehavior() {
		if (behavior == null) {
			behavior = new EObjectContainmentWithInverseEList.Resolving<OperationBehavior>(OperationBehavior.class, this, EuremaPackage.MEGAMODEL__BEHAVIOR, EuremaPackage.OPERATION_BEHAVIOR__MEGAMODEL);
		}
		return behavior;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Model> getModels() {
		if (models == null) {
			models = new EObjectContainmentWithInverseEList.Resolving<Model>(Model.class, this, EuremaPackage.MEGAMODEL__MODELS, EuremaPackage.MODEL__MEGAMODEL);
		}
		return models;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUid(String newUid) {
		String oldUid = uid;
		uid = newUid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL__UID, oldUid, uid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MegamodelModule getModule() {
		if (eContainerFeatureID() != EuremaPackage.MEGAMODEL__MODULE) return null;
		return (MegamodelModule)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MegamodelModule basicGetModule() {
		if (eContainerFeatureID() != EuremaPackage.MEGAMODEL__MODULE) return null;
		return (MegamodelModule)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModule(MegamodelModule newModule, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModule, EuremaPackage.MEGAMODEL__MODULE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModule(MegamodelModule newModule) {
		if (newModule != eInternalContainer() || (eContainerFeatureID() != EuremaPackage.MEGAMODEL__MODULE && newModule != null)) {
			if (EcoreUtil.isAncestor(this, newModule))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModule != null)
				msgs = ((InternalEObject)newModule).eInverseAdd(this, EuremaPackage.MEGAMODEL_MODULE__MEGAMODEL, MegamodelModule.class, msgs);
			msgs = basicSetModule(newModule, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL__MODULE, newModule, newModule));
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
			case EuremaPackage.MEGAMODEL__INITIAL_OPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getInitialOperations()).basicAdd(otherEnd, msgs);
			case EuremaPackage.MEGAMODEL__FINAL_OPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFinalOperations()).basicAdd(otherEnd, msgs);
			case EuremaPackage.MEGAMODEL__DECISION_OPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDecisionOperations()).basicAdd(otherEnd, msgs);
			case EuremaPackage.MEGAMODEL__BEHAVIOR:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getBehavior()).basicAdd(otherEnd, msgs);
			case EuremaPackage.MEGAMODEL__MODELS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getModels()).basicAdd(otherEnd, msgs);
			case EuremaPackage.MEGAMODEL__MODULE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModule((MegamodelModule)otherEnd, msgs);
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
			case EuremaPackage.MEGAMODEL__INITIAL_OPERATIONS:
				return ((InternalEList<?>)getInitialOperations()).basicRemove(otherEnd, msgs);
			case EuremaPackage.MEGAMODEL__FINAL_OPERATIONS:
				return ((InternalEList<?>)getFinalOperations()).basicRemove(otherEnd, msgs);
			case EuremaPackage.MEGAMODEL__DECISION_OPERATIONS:
				return ((InternalEList<?>)getDecisionOperations()).basicRemove(otherEnd, msgs);
			case EuremaPackage.MEGAMODEL__BEHAVIOR:
				return ((InternalEList<?>)getBehavior()).basicRemove(otherEnd, msgs);
			case EuremaPackage.MEGAMODEL__MODELS:
				return ((InternalEList<?>)getModels()).basicRemove(otherEnd, msgs);
			case EuremaPackage.MEGAMODEL__MODULE:
				return basicSetModule(null, msgs);
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
			case EuremaPackage.MEGAMODEL__MODULE:
				return eInternalContainer().eInverseRemove(this, EuremaPackage.MEGAMODEL_MODULE__MEGAMODEL, MegamodelModule.class, msgs);
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
			case EuremaPackage.MEGAMODEL__INITIAL_OPERATIONS:
				return getInitialOperations();
			case EuremaPackage.MEGAMODEL__FINAL_OPERATIONS:
				return getFinalOperations();
			case EuremaPackage.MEGAMODEL__DECISION_OPERATIONS:
				return getDecisionOperations();
			case EuremaPackage.MEGAMODEL__BEHAVIOR:
				return getBehavior();
			case EuremaPackage.MEGAMODEL__MODELS:
				return getModels();
			case EuremaPackage.MEGAMODEL__UID:
				return getUid();
			case EuremaPackage.MEGAMODEL__NAME:
				return getName();
			case EuremaPackage.MEGAMODEL__DESCRIPTION:
				return getDescription();
			case EuremaPackage.MEGAMODEL__MODULE:
				if (resolve) return getModule();
				return basicGetModule();
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
			case EuremaPackage.MEGAMODEL__INITIAL_OPERATIONS:
				getInitialOperations().clear();
				getInitialOperations().addAll((Collection<? extends InitialOperation>)newValue);
				return;
			case EuremaPackage.MEGAMODEL__FINAL_OPERATIONS:
				getFinalOperations().clear();
				getFinalOperations().addAll((Collection<? extends FinalOperation>)newValue);
				return;
			case EuremaPackage.MEGAMODEL__DECISION_OPERATIONS:
				getDecisionOperations().clear();
				getDecisionOperations().addAll((Collection<? extends DecisionOperation>)newValue);
				return;
			case EuremaPackage.MEGAMODEL__BEHAVIOR:
				getBehavior().clear();
				getBehavior().addAll((Collection<? extends OperationBehavior>)newValue);
				return;
			case EuremaPackage.MEGAMODEL__MODELS:
				getModels().clear();
				getModels().addAll((Collection<? extends Model>)newValue);
				return;
			case EuremaPackage.MEGAMODEL__UID:
				setUid((String)newValue);
				return;
			case EuremaPackage.MEGAMODEL__NAME:
				setName((String)newValue);
				return;
			case EuremaPackage.MEGAMODEL__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EuremaPackage.MEGAMODEL__MODULE:
				setModule((MegamodelModule)newValue);
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
			case EuremaPackage.MEGAMODEL__INITIAL_OPERATIONS:
				getInitialOperations().clear();
				return;
			case EuremaPackage.MEGAMODEL__FINAL_OPERATIONS:
				getFinalOperations().clear();
				return;
			case EuremaPackage.MEGAMODEL__DECISION_OPERATIONS:
				getDecisionOperations().clear();
				return;
			case EuremaPackage.MEGAMODEL__BEHAVIOR:
				getBehavior().clear();
				return;
			case EuremaPackage.MEGAMODEL__MODELS:
				getModels().clear();
				return;
			case EuremaPackage.MEGAMODEL__UID:
				setUid(UID_EDEFAULT);
				return;
			case EuremaPackage.MEGAMODEL__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EuremaPackage.MEGAMODEL__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EuremaPackage.MEGAMODEL__MODULE:
				setModule((MegamodelModule)null);
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
			case EuremaPackage.MEGAMODEL__INITIAL_OPERATIONS:
				return initialOperations != null && !initialOperations.isEmpty();
			case EuremaPackage.MEGAMODEL__FINAL_OPERATIONS:
				return finalOperations != null && !finalOperations.isEmpty();
			case EuremaPackage.MEGAMODEL__DECISION_OPERATIONS:
				return decisionOperations != null && !decisionOperations.isEmpty();
			case EuremaPackage.MEGAMODEL__BEHAVIOR:
				return behavior != null && !behavior.isEmpty();
			case EuremaPackage.MEGAMODEL__MODELS:
				return models != null && !models.isEmpty();
			case EuremaPackage.MEGAMODEL__UID:
				return UID_EDEFAULT == null ? uid != null : !UID_EDEFAULT.equals(uid);
			case EuremaPackage.MEGAMODEL__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EuremaPackage.MEGAMODEL__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EuremaPackage.MEGAMODEL__MODULE:
				return basicGetModule() != null;
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
		result.append(" (uid: ");
		result.append(uid);
		result.append(", name: ");
		result.append(name);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //MegamodelImpl

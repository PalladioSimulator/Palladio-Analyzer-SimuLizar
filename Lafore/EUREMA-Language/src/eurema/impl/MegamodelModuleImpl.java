/**
 */
package eurema.impl;

import eurema.EuremaPackage;
import eurema.ExecutionContext;
import eurema.Megamodel;
import eurema.MegamodelModule;
import eurema.MegamodelModuleTrigger;
import eurema.ModelResource;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Megamodel Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.MegamodelModuleImpl#getMegamodel <em>Megamodel</em>}</li>
 *   <li>{@link eurema.impl.MegamodelModuleImpl#getContext <em>Context</em>}</li>
 *   <li>{@link eurema.impl.MegamodelModuleImpl#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link eurema.impl.MegamodelModuleImpl#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MegamodelModuleImpl extends ModuleImpl implements MegamodelModule {
	/**
	 * The cached value of the '{@link #getMegamodel() <em>Megamodel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMegamodel()
	 * @generated
	 * @ordered
	 */
	protected Megamodel megamodel;

	/**
	 * The cached value of the '{@link #getContext() <em>Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContext()
	 * @generated
	 * @ordered
	 */
	protected ExecutionContext context;

	/**
	 * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected MegamodelModuleTrigger trigger;

	/**
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected ModelResource binding;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MegamodelModuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.MEGAMODEL_MODULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Megamodel getMegamodel() {
		if (megamodel != null && megamodel.eIsProxy()) {
			InternalEObject oldMegamodel = (InternalEObject)megamodel;
			megamodel = (Megamodel)eResolveProxy(oldMegamodel);
			if (megamodel != oldMegamodel) {
				InternalEObject newMegamodel = (InternalEObject)megamodel;
				NotificationChain msgs =  oldMegamodel.eInverseRemove(this, EuremaPackage.MEGAMODEL__MODULE, Megamodel.class, null);
				if (newMegamodel.eInternalContainer() == null) {
					msgs =  newMegamodel.eInverseAdd(this, EuremaPackage.MEGAMODEL__MODULE, Megamodel.class, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.MEGAMODEL_MODULE__MEGAMODEL, oldMegamodel, megamodel));
			}
		}
		return megamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Megamodel basicGetMegamodel() {
		return megamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMegamodel(Megamodel newMegamodel, NotificationChain msgs) {
		Megamodel oldMegamodel = megamodel;
		megamodel = newMegamodel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL_MODULE__MEGAMODEL, oldMegamodel, newMegamodel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMegamodel(Megamodel newMegamodel) {
		if (newMegamodel != megamodel) {
			NotificationChain msgs = null;
			if (megamodel != null)
				msgs = ((InternalEObject)megamodel).eInverseRemove(this, EuremaPackage.MEGAMODEL__MODULE, Megamodel.class, msgs);
			if (newMegamodel != null)
				msgs = ((InternalEObject)newMegamodel).eInverseAdd(this, EuremaPackage.MEGAMODEL__MODULE, Megamodel.class, msgs);
			msgs = basicSetMegamodel(newMegamodel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL_MODULE__MEGAMODEL, newMegamodel, newMegamodel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContext getContext() {
		if (context != null && context.eIsProxy()) {
			InternalEObject oldContext = (InternalEObject)context;
			context = (ExecutionContext)eResolveProxy(oldContext);
			if (context != oldContext) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.MEGAMODEL_MODULE__CONTEXT, oldContext, context));
			}
		}
		return context;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContext basicGetContext() {
		return context;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContext(ExecutionContext newContext, NotificationChain msgs) {
		ExecutionContext oldContext = context;
		context = newContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL_MODULE__CONTEXT, oldContext, newContext);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContext(ExecutionContext newContext) {
		if (newContext != context) {
			NotificationChain msgs = null;
			if (context != null)
				msgs = ((InternalEObject)context).eInverseRemove(this, EuremaPackage.EXECUTION_CONTEXT__EXECUTING, ExecutionContext.class, msgs);
			if (newContext != null)
				msgs = ((InternalEObject)newContext).eInverseAdd(this, EuremaPackage.EXECUTION_CONTEXT__EXECUTING, ExecutionContext.class, msgs);
			msgs = basicSetContext(newContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL_MODULE__CONTEXT, newContext, newContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MegamodelModuleTrigger getTrigger() {
		if (trigger != null && trigger.eIsProxy()) {
			InternalEObject oldTrigger = (InternalEObject)trigger;
			trigger = (MegamodelModuleTrigger)eResolveProxy(oldTrigger);
			if (trigger != oldTrigger) {
				InternalEObject newTrigger = (InternalEObject)trigger;
				NotificationChain msgs =  oldTrigger.eInverseRemove(this, EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE, MegamodelModuleTrigger.class, null);
				if (newTrigger.eInternalContainer() == null) {
					msgs =  newTrigger.eInverseAdd(this, EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE, MegamodelModuleTrigger.class, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.MEGAMODEL_MODULE__TRIGGER, oldTrigger, trigger));
			}
		}
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MegamodelModuleTrigger basicGetTrigger() {
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrigger(MegamodelModuleTrigger newTrigger, NotificationChain msgs) {
		MegamodelModuleTrigger oldTrigger = trigger;
		trigger = newTrigger;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL_MODULE__TRIGGER, oldTrigger, newTrigger);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrigger(MegamodelModuleTrigger newTrigger) {
		if (newTrigger != trigger) {
			NotificationChain msgs = null;
			if (trigger != null)
				msgs = ((InternalEObject)trigger).eInverseRemove(this, EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE, MegamodelModuleTrigger.class, msgs);
			if (newTrigger != null)
				msgs = ((InternalEObject)newTrigger).eInverseAdd(this, EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE, MegamodelModuleTrigger.class, msgs);
			msgs = basicSetTrigger(newTrigger, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL_MODULE__TRIGGER, newTrigger, newTrigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelResource getBinding() {
		if (binding != null && binding.eIsProxy()) {
			InternalEObject oldBinding = (InternalEObject)binding;
			binding = (ModelResource)eResolveProxy(oldBinding);
			if (binding != oldBinding) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.MEGAMODEL_MODULE__BINDING, oldBinding, binding));
			}
		}
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelResource basicGetBinding() {
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBinding(ModelResource newBinding, NotificationChain msgs) {
		ModelResource oldBinding = binding;
		binding = newBinding;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL_MODULE__BINDING, oldBinding, newBinding);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(ModelResource newBinding) {
		if (newBinding != binding) {
			NotificationChain msgs = null;
			if (binding != null)
				msgs = ((InternalEObject)binding).eInverseRemove(this, EuremaPackage.MODEL_RESOURCE__BOUND_BY_MEGAMODEL_MODULES, ModelResource.class, msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, EuremaPackage.MODEL_RESOURCE__BOUND_BY_MEGAMODEL_MODULES, ModelResource.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL_MODULE__BINDING, newBinding, newBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EuremaPackage.MEGAMODEL_MODULE__MEGAMODEL:
				if (megamodel != null)
					msgs = ((InternalEObject)megamodel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EuremaPackage.MEGAMODEL_MODULE__MEGAMODEL, null, msgs);
				return basicSetMegamodel((Megamodel)otherEnd, msgs);
			case EuremaPackage.MEGAMODEL_MODULE__CONTEXT:
				if (context != null)
					msgs = ((InternalEObject)context).eInverseRemove(this, EuremaPackage.EXECUTION_CONTEXT__EXECUTING, ExecutionContext.class, msgs);
				return basicSetContext((ExecutionContext)otherEnd, msgs);
			case EuremaPackage.MEGAMODEL_MODULE__TRIGGER:
				if (trigger != null)
					msgs = ((InternalEObject)trigger).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EuremaPackage.MEGAMODEL_MODULE__TRIGGER, null, msgs);
				return basicSetTrigger((MegamodelModuleTrigger)otherEnd, msgs);
			case EuremaPackage.MEGAMODEL_MODULE__BINDING:
				if (binding != null)
					msgs = ((InternalEObject)binding).eInverseRemove(this, EuremaPackage.MODEL_RESOURCE__BOUND_BY_MEGAMODEL_MODULES, ModelResource.class, msgs);
				return basicSetBinding((ModelResource)otherEnd, msgs);
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
			case EuremaPackage.MEGAMODEL_MODULE__MEGAMODEL:
				return basicSetMegamodel(null, msgs);
			case EuremaPackage.MEGAMODEL_MODULE__CONTEXT:
				return basicSetContext(null, msgs);
			case EuremaPackage.MEGAMODEL_MODULE__TRIGGER:
				return basicSetTrigger(null, msgs);
			case EuremaPackage.MEGAMODEL_MODULE__BINDING:
				return basicSetBinding(null, msgs);
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
			case EuremaPackage.MEGAMODEL_MODULE__MEGAMODEL:
				if (resolve) return getMegamodel();
				return basicGetMegamodel();
			case EuremaPackage.MEGAMODEL_MODULE__CONTEXT:
				if (resolve) return getContext();
				return basicGetContext();
			case EuremaPackage.MEGAMODEL_MODULE__TRIGGER:
				if (resolve) return getTrigger();
				return basicGetTrigger();
			case EuremaPackage.MEGAMODEL_MODULE__BINDING:
				if (resolve) return getBinding();
				return basicGetBinding();
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
			case EuremaPackage.MEGAMODEL_MODULE__MEGAMODEL:
				setMegamodel((Megamodel)newValue);
				return;
			case EuremaPackage.MEGAMODEL_MODULE__CONTEXT:
				setContext((ExecutionContext)newValue);
				return;
			case EuremaPackage.MEGAMODEL_MODULE__TRIGGER:
				setTrigger((MegamodelModuleTrigger)newValue);
				return;
			case EuremaPackage.MEGAMODEL_MODULE__BINDING:
				setBinding((ModelResource)newValue);
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
			case EuremaPackage.MEGAMODEL_MODULE__MEGAMODEL:
				setMegamodel((Megamodel)null);
				return;
			case EuremaPackage.MEGAMODEL_MODULE__CONTEXT:
				setContext((ExecutionContext)null);
				return;
			case EuremaPackage.MEGAMODEL_MODULE__TRIGGER:
				setTrigger((MegamodelModuleTrigger)null);
				return;
			case EuremaPackage.MEGAMODEL_MODULE__BINDING:
				setBinding((ModelResource)null);
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
			case EuremaPackage.MEGAMODEL_MODULE__MEGAMODEL:
				return megamodel != null;
			case EuremaPackage.MEGAMODEL_MODULE__CONTEXT:
				return context != null;
			case EuremaPackage.MEGAMODEL_MODULE__TRIGGER:
				return trigger != null;
			case EuremaPackage.MEGAMODEL_MODULE__BINDING:
				return binding != null;
		}
		return super.eIsSet(featureID);
	}

} //MegamodelModuleImpl

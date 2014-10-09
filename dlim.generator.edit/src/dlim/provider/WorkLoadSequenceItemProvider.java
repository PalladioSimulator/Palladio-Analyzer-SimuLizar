/**
 */
package dlim.provider;


import dlim.DlimFactory;
import dlim.DlimPackage;
import dlim.WorkLoadSequence;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link dlim.WorkLoadSequence} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class WorkLoadSequenceItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkLoadSequenceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addWorkFunctionContainersPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addTerminateAfterTimePropertyDescriptor(object);
			addTerminateAfterLoopsPropertyDescriptor(object);
			addFirstIterationStartPropertyDescriptor(object);
			addFirstIterationEndPropertyDescriptor(object);
			addLoopDurationPropertyDescriptor(object);
			addFinalDurationPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Work Function Containers feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addWorkFunctionContainersPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_WorkLoadSequence_workFunctionContainers_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkLoadSequence_workFunctionContainers_feature", "_UI_WorkLoadSequence_type"),
				 DlimPackage.Literals.WORK_LOAD_SEQUENCE__WORK_FUNCTION_CONTAINERS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_WorkLoadSequence_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkLoadSequence_name_feature", "_UI_WorkLoadSequence_type"),
				 DlimPackage.Literals.WORK_LOAD_SEQUENCE__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Terminate After Time feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTerminateAfterTimePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_WorkLoadSequence_terminateAfterTime_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkLoadSequence_terminateAfterTime_feature", "_UI_WorkLoadSequence_type"),
				 DlimPackage.Literals.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_TIME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Terminate After Loops feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTerminateAfterLoopsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_WorkLoadSequence_terminateAfterLoops_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkLoadSequence_terminateAfterLoops_feature", "_UI_WorkLoadSequence_type"),
				 DlimPackage.Literals.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_LOOPS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the First Iteration Start feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFirstIterationStartPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_WorkLoadSequence_firstIterationStart_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkLoadSequence_firstIterationStart_feature", "_UI_WorkLoadSequence_type"),
				 DlimPackage.Literals.WORK_LOAD_SEQUENCE__FIRST_ITERATION_START,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the First Iteration End feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFirstIterationEndPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_WorkLoadSequence_firstIterationEnd_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkLoadSequence_firstIterationEnd_feature", "_UI_WorkLoadSequence_type"),
				 DlimPackage.Literals.WORK_LOAD_SEQUENCE__FIRST_ITERATION_END,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Loop Duration feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLoopDurationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_WorkLoadSequence_loopDuration_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkLoadSequence_loopDuration_feature", "_UI_WorkLoadSequence_type"),
				 DlimPackage.Literals.WORK_LOAD_SEQUENCE__LOOP_DURATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Final Duration feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFinalDurationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_WorkLoadSequence_finalDuration_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_WorkLoadSequence_finalDuration_feature", "_UI_WorkLoadSequence_type"),
				 DlimPackage.Literals.WORK_LOAD_SEQUENCE__FINAL_DURATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(DlimPackage.Literals.WORK_LOAD_SEQUENCE__REFERENCE_CLOCK);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns WorkLoadSequence.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/WorkLoadSequence"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((WorkLoadSequence)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_WorkLoadSequence_type") :
			getString("_UI_WorkLoadSequence_type") + " " + label;
	}
	

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(WorkLoadSequence.class)) {
			case DlimPackage.WORK_LOAD_SEQUENCE__NAME:
			case DlimPackage.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_TIME:
			case DlimPackage.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_LOOPS:
			case DlimPackage.WORK_LOAD_SEQUENCE__FIRST_ITERATION_START:
			case DlimPackage.WORK_LOAD_SEQUENCE__FIRST_ITERATION_END:
			case DlimPackage.WORK_LOAD_SEQUENCE__LOOP_DURATION:
			case DlimPackage.WORK_LOAD_SEQUENCE__FINAL_DURATION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__REFERENCE_CLOCK:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(DlimPackage.Literals.WORK_LOAD_SEQUENCE__REFERENCE_CLOCK,
				 DlimFactory.eINSTANCE.createReferenceClockObject()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return DlimEditPlugin.INSTANCE;
	}

}

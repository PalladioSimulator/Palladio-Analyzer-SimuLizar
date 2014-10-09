/**
 */
package dlim.provider;


import dlim.DlimPackage;
import dlim.TimeDependentWorkFunctionContainer;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link dlim.TimeDependentWorkFunctionContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TimeDependentWorkFunctionContainerItemProvider extends TimeDependentFunctionContainerItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeDependentWorkFunctionContainerItemProvider(AdapterFactory adapterFactory) {
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

			addWorkPropertyDescriptor(object);
			addWorkStartTimePropertyDescriptor(object);
			addWorkDurationPropertyDescriptor(object);
			addTimeSynchronizationPropertyDescriptor(object);
			addMutualLoadFunctionPropertyDescriptor(object);
			addPriorityPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Work feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addWorkPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TimeDependentWorkFunctionContainer_work_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TimeDependentWorkFunctionContainer_work_feature", "_UI_TimeDependentWorkFunctionContainer_type"),
				 DlimPackage.Literals.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Work Start Time feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addWorkStartTimePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TimeDependentWorkFunctionContainer_workStartTime_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TimeDependentWorkFunctionContainer_workStartTime_feature", "_UI_TimeDependentWorkFunctionContainer_type"),
				 DlimPackage.Literals.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_START_TIME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Work Duration feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addWorkDurationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TimeDependentWorkFunctionContainer_workDuration_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TimeDependentWorkFunctionContainer_workDuration_feature", "_UI_TimeDependentWorkFunctionContainer_type"),
				 DlimPackage.Literals.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_DURATION,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Time Synchronization feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTimeSynchronizationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TimeDependentWorkFunctionContainer_timeSynchronization_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TimeDependentWorkFunctionContainer_timeSynchronization_feature", "_UI_TimeDependentWorkFunctionContainer_type"),
				 DlimPackage.Literals.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__TIME_SYNCHRONIZATION,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Mutual Load Function feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMutualLoadFunctionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TimeDependentWorkFunctionContainer_mutualLoadFunction_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TimeDependentWorkFunctionContainer_mutualLoadFunction_feature", "_UI_TimeDependentWorkFunctionContainer_type"),
				 DlimPackage.Literals.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__MUTUAL_LOAD_FUNCTION,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Priority feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPriorityPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TimeDependentWorkFunctionContainer_priority_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TimeDependentWorkFunctionContainer_priority_feature", "_UI_TimeDependentWorkFunctionContainer_type"),
				 DlimPackage.Literals.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PRIORITY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This returns TimeDependentWorkFunctionContainer.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TimeDependentWorkFunctionContainer"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((TimeDependentWorkFunctionContainer)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_TimeDependentWorkFunctionContainer_type") :
			getString("_UI_TimeDependentWorkFunctionContainer_type") + " " + label;
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

		switch (notification.getFeatureID(TimeDependentWorkFunctionContainer.class)) {
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_START_TIME:
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_DURATION:
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PRIORITY:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
	}

}

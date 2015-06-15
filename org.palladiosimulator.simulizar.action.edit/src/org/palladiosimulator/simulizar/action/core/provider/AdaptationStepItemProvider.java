/**
 */
package org.palladiosimulator.simulizar.action.core.provider;


import de.uka.ipd.sdq.pcm.core.entity.provider.EntityItemProvider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.CorePackage;

/**
 * This is the item provider adapter for a {@link org.palladiosimulator.simulizar.action.core.AdaptationStep} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AdaptationStepItemProvider extends EntityItemProvider {
	/**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public AdaptationStepItemProvider(AdapterFactory adapterFactory) {
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

            addControllerCompletionURIPropertyDescriptor(object);
            addAdaptationStepURIPropertyDescriptor(object);
            addPreconditionURIPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

	/**
     * This adds a property descriptor for the Controller Completion URI feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addControllerCompletionURIPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AdaptationStep_controllerCompletionURI_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AdaptationStep_controllerCompletionURI_feature", "_UI_AdaptationStep_type"),
                 CorePackage.Literals.ADAPTATION_STEP__CONTROLLER_COMPLETION_URI,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Adaptation Step URI feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addAdaptationStepURIPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AdaptationStep_adaptationStepURI_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AdaptationStep_adaptationStepURI_feature", "_UI_AdaptationStep_type"),
                 CorePackage.Literals.ADAPTATION_STEP__ADAPTATION_STEP_URI,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This adds a property descriptor for the Precondition URI feature.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected void addPreconditionURIPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_AdaptationStep_preconditionURI_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_AdaptationStep_preconditionURI_feature", "_UI_AdaptationStep_type"),
                 CorePackage.Literals.ADAPTATION_STEP__PRECONDITION_URI,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
                 null,
                 null));
    }

	/**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getText(Object object) {
        String label = ((AdaptationStep)object).getId();
        return label == null || label.length() == 0 ?
            getString("_UI_AdaptationStep_type") :
            getString("_UI_AdaptationStep_type") + " " + label;
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

        switch (notification.getFeatureID(AdaptationStep.class)) {
            case CorePackage.ADAPTATION_STEP__CONTROLLER_COMPLETION_URI:
            case CorePackage.ADAPTATION_STEP__ADAPTATION_STEP_URI:
            case CorePackage.ADAPTATION_STEP__PRECONDITION_URI:
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

	/**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ResourceLocator getResourceLocator() {
        return ActionsEditPlugin.INSTANCE;
    }

}

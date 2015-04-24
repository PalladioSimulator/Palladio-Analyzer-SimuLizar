/**
 */
package org.palladiosimulator.runtimemeasurement.provider;

import de.uka.ipd.sdq.identifier.provider.IdentifierItemProvider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;

/**
 * This is the item provider adapter for a {@link org.palladiosimulator.runtimemeasurement.RuntimeMeasurement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RuntimeMeasurementItemProvider extends IdentifierItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public RuntimeMeasurementItemProvider(AdapterFactory adapterFactory) {
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

            addMeasuringPointPropertyDescriptor(object);
            addMeasurementSpecificationPropertyDescriptor(object);
            addMeasuringValuePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Measuring Point feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addMeasuringPointPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_RuntimeMeasurement_measuringPoint_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_RuntimeMeasurement_measuringPoint_feature",
                        "_UI_RuntimeMeasurement_type"),
                RuntimeMeasurementPackage.Literals.RUNTIME_MEASUREMENT__MEASURING_POINT, true, false, true, null, null,
                null));
    }

    /**
     * This adds a property descriptor for the Measurement Specification feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addMeasurementSpecificationPropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_RuntimeMeasurement_measurementSpecification_feature"),
                getString("_UI_PropertyDescriptor_description",
                        "_UI_RuntimeMeasurement_measurementSpecification_feature", "_UI_RuntimeMeasurement_type"),
                RuntimeMeasurementPackage.Literals.RUNTIME_MEASUREMENT__MEASUREMENT_SPECIFICATION, true, false, true,
                null, null, null));
    }

    /**
     * This adds a property descriptor for the Measuring Value feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addMeasuringValuePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add(createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                getResourceLocator(),
                getString("_UI_RuntimeMeasurement_measuringValue_feature"),
                getString("_UI_PropertyDescriptor_description", "_UI_RuntimeMeasurement_measuringValue_feature",
                        "_UI_RuntimeMeasurement_type"),
                RuntimeMeasurementPackage.Literals.RUNTIME_MEASUREMENT__MEASURING_VALUE, true, false, false,
                ItemPropertyDescriptor.REAL_VALUE_IMAGE, null, null));
    }

    /**
     * This returns RuntimeMeasurement.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/RuntimeMeasurement"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((RuntimeMeasurement) object).getId();
        return label == null || label.length() == 0 ? getString("_UI_RuntimeMeasurement_type")
                : getString("_UI_RuntimeMeasurement_type") + " " + label;
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

        switch (notification.getFeatureID(RuntimeMeasurement.class)) {
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASURING_VALUE:
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
        return RuntimeMeasurementEditPlugin.INSTANCE;
    }

}

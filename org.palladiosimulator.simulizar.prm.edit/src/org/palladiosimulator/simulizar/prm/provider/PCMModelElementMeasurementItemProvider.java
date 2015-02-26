/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.prm.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.PrmPackage;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class PCMModelElementMeasurementItemProvider extends UniqueElementItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public PCMModelElementMeasurementItemProvider(final AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(final Object object) {
        if (this.itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            this.addPcmModelElementPropertyDescriptor(object);
            this.addMonitorRepositoryPropertyDescriptor(object);
            this.addMeasurementValuePropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Pcm Model Element feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addPcmModelElementPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_PCMModelElementMeasurement_pcmModelElement_feature"), this.getString(
                        "_UI_PropertyDescriptor_description", "_UI_PCMModelElementMeasurement_pcmModelElement_feature",
                        "_UI_PCMModelElementMeasurement_type"),
                PrmPackage.Literals.PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT, true, false, true, null, null,
                null));
    }

    /**
     * This adds a property descriptor for the Monitor Repository feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addMonitorRepositoryPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_PCMModelElementMeasurement_monitorRepository_feature"), this.getString(
                        "_UI_PropertyDescriptor_description",
                        "_UI_PCMModelElementMeasurement_monitorRepository_feature",
                        "_UI_PCMModelElementMeasurement_type"),
                        PrmPackage.Literals.PCM_MODEL_ELEMENT_MEASUREMENT__MONITOR_REPOSITORY, true, false, true, null, null,
                        null));
    }

    /**
     * This adds a property descriptor for the Measurement Value feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addMeasurementValuePropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_PCMModelElementMeasurement_measurementValue_feature"), this.getString(
                        "_UI_PropertyDescriptor_description",
                        "_UI_PCMModelElementMeasurement_measurementValue_feature",
                        "_UI_PCMModelElementMeasurement_type"),
                        PrmPackage.Literals.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE, true, false, false,
                        ItemPropertyDescriptor.REAL_VALUE_IMAGE, null, null));
    }

    /**
     * This returns PCMModelElementMeasurement.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/PCMModelElementMeasurement"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((PCMModelElementMeasurement) object).getGuid();
        return label == null || label.length() == 0 ? this.getString("_UI_PCMModelElementMeasurement_type") : this
                .getString("_UI_PCMModelElementMeasurement_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}
     * . <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(final Notification notification) {
        this.updateChildren(notification);

        switch (notification.getFeatureID(PCMModelElementMeasurement.class)) {
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE:
            this.fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that
     * can be created under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(final Collection<Object> newChildDescriptors, final Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

}

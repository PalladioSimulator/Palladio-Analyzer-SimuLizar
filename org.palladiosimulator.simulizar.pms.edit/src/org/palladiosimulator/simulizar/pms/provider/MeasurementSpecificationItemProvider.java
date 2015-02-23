/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PmsFactory;
import org.palladiosimulator.simulizar.pms.PmsPackage;
import org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum;

import de.uka.ipd.sdq.identifier.provider.IdentifierItemProvider;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.simulizar.pms.MeasurementSpecification} object. <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 *
 * @generated
 */
public class MeasurementSpecificationItemProvider extends IdentifierItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public MeasurementSpecificationItemProvider(final AdapterFactory adapterFactory) {
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

            this.addStatisticalCharacterizationPropertyDescriptor(object);
            this.addMetricDescriptionPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Statistical Characterization feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addStatisticalCharacterizationPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_MeasurementSpecification_statisticalCharacterization_feature"), this.getString(
                        "_UI_PropertyDescriptor_description",
                        "_UI_MeasurementSpecification_statisticalCharacterization_feature",
                        "_UI_MeasurementSpecification_type"),
                PmsPackage.Literals.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION, true, false, false,
                ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Metric Description feature. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addMetricDescriptionPropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors
                .add(this.createItemPropertyDescriptor(((ComposeableAdapterFactory) this.adapterFactory)
                .getRootAdapterFactory(), this.getResourceLocator(), this
                .getString("_UI_MeasurementSpecification_metricDescription_feature"), this.getString(
                        "_UI_PropertyDescriptor_description", "_UI_MeasurementSpecification_metricDescription_feature",
                        "_UI_MeasurementSpecification_type"),
                        PmsPackage.Literals.MEASUREMENT_SPECIFICATION__METRIC_DESCRIPTION, true, false, true, null,
                        null, null));
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate
     * feature for an {@link org.eclipse.emf.edit.command.AddCommand},
     * {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(final Object object) {
        if (this.childrenFeatures == null) {
            super.getChildrenFeatures(object);
            this.childrenFeatures.add(PmsPackage.Literals.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION);
        }
        return this.childrenFeatures;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(final Object object, final Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns MeasurementSpecification.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/MeasurementSpecification"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final StatisticalCharacterizationEnum labelValue = ((MeasurementSpecification) object)
                .getStatisticalCharacterization();
        final String label = labelValue == null ? null : labelValue.toString();
        return label == null || label.length() == 0 ? this.getString("_UI_MeasurementSpecification_type") : this
                .getString("_UI_MeasurementSpecification_type") + " " + label;
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

        switch (notification.getFeatureID(MeasurementSpecification.class)) {
        case PmsPackage.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION:
            this.fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        case PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION:
            this.fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
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

        newChildDescriptors.add(this.createChildParameter(
                PmsPackage.Literals.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION,
                PmsFactory.eINSTANCE.createIntervall()));

        newChildDescriptors.add(this.createChildParameter(
                PmsPackage.Literals.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION,
                PmsFactory.eINSTANCE.createDelayedIntervall()));

        newChildDescriptors.add(this.createChildParameter(
                PmsPackage.Literals.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION,
                PmsFactory.eINSTANCE.createTimeFrame()));
    }

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return PmsEditPlugin.INSTANCE;
    }

}

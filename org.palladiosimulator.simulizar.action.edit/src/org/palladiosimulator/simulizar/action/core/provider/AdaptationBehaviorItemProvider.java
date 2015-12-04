/**
 */
package org.palladiosimulator.simulizar.action.core.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.pcm.core.entity.provider.EntityItemProvider;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.CoreFactory;
import org.palladiosimulator.simulizar.action.core.CorePackage;

/**
 * This is the item provider adapter for a
 * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class AdaptationBehaviorItemProvider extends EntityItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public AdaptationBehaviorItemProvider(final AdapterFactory adapterFactory) {
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

            this.addTransientStateProfilePropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Transient State Profile feature. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addTransientStateProfilePropertyDescriptor(final Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(
                ((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_AdaptationBehavior_transientStateProfile_feature"),
                this.getString("_UI_PropertyDescriptor_description",
                        "_UI_AdaptationBehavior_transientStateProfile_feature", "_UI_AdaptationBehavior_type"),
                CorePackage.Literals.ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE, true, false, true, null, null,
                null));
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
            this.childrenFeatures.add(CorePackage.Literals.ADAPTATION_BEHAVIOR__ADAPTATION_STEPS);
            this.childrenFeatures.add(CorePackage.Literals.ADAPTATION_BEHAVIOR__INVOLVED_ROLES);
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
     * This returns AdaptationBehavior.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object getImage(final Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/AdaptationBehavior"));
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public String getText(final Object object) {
        final String label = ((AdaptationBehavior) object).getId();
        return label == null || label.length() == 0 ? this.getString("_UI_AdaptationBehavior_type")
                : this.getString("_UI_AdaptationBehavior_type") + " " + label;
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

        switch (notification.getFeatureID(AdaptationBehavior.class)) {
        case CorePackage.ADAPTATION_BEHAVIOR__ADAPTATION_STEPS:
        case CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES:
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

        newChildDescriptors.add(this.createChildParameter(CorePackage.Literals.ADAPTATION_BEHAVIOR__ADAPTATION_STEPS,
                CoreFactory.eINSTANCE.createResourceDemandingAction()));

        newChildDescriptors.add(this.createChildParameter(CorePackage.Literals.ADAPTATION_BEHAVIOR__ADAPTATION_STEPS,
                CoreFactory.eINSTANCE.createStateTransformingAction()));

        newChildDescriptors.add(this.createChildParameter(CorePackage.Literals.ADAPTATION_BEHAVIOR__ADAPTATION_STEPS,
                CoreFactory.eINSTANCE.createGuardedAdaptationBehavior()));

        newChildDescriptors.add(this.createChildParameter(CorePackage.Literals.ADAPTATION_BEHAVIOR__ADAPTATION_STEPS,
                CoreFactory.eINSTANCE.createEnactAdaptationAction()));

        newChildDescriptors.add(this.createChildParameter(CorePackage.Literals.ADAPTATION_BEHAVIOR__INVOLVED_ROLES,
                CoreFactory.eINSTANCE.createRoleType()));
    }

    /**
     * Return the resource locator for this item provider's resources. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return ActionsEditPlugin.INSTANCE;
    }

}

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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.palladiosimulator.simulizar.pms.DelayedIntervall;
import org.palladiosimulator.simulizar.pms.PmsPackage;

/**
 * This is the item provider adapter for a {@link org.palladiosimulator.simulizar.pms.DelayedIntervall} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DelayedIntervallItemProvider
   extends IntervallItemProvider
   implements
      IEditingDomainItemProvider,
      IStructuredItemContentProvider,
      ITreeItemContentProvider,
      IItemLabelProvider,
      IItemPropertySource
{
   /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   public DelayedIntervallItemProvider(AdapterFactory adapterFactory)
   {
        super(adapterFactory);
    }

   /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   @Override
   public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
   {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addDelayPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

   /**
     * This adds a property descriptor for the Delay feature.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   protected void addDelayPropertyDescriptor(Object object)
   {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_DelayedIntervall_delay_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_DelayedIntervall_delay_feature", "_UI_DelayedIntervall_type"),
                 PmsPackage.Literals.DELAYED_INTERVALL__DELAY,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
                 null,
                 null));
    }

   /**
     * This returns DelayedIntervall.gif.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   @Override
   public Object getImage(Object object)
   {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/DelayedIntervall"));
    }

   /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   @Override
   public String getText(Object object)
   {
        String label = ((DelayedIntervall)object).getGuid();
        return label == null || label.length() == 0 ?
            getString("_UI_DelayedIntervall_type") :
            getString("_UI_DelayedIntervall_type") + " " + label;
    }

   /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   @Override
   public void notifyChanged(Notification notification)
   {
        updateChildren(notification);

        switch (notification.getFeatureID(DelayedIntervall.class)) {
            case PmsPackage.DELAYED_INTERVALL__DELAY:
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
   protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
   {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

}

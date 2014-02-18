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
import org.palladiosimulator.simulizar.pms.Intervall;
import org.palladiosimulator.simulizar.pms.PmsPackage;

/**
 * This is the item provider adapter for a {@link org.palladiosimulator.simulizar.pms.Intervall} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IntervallItemProvider
   extends TemporalCharacterizationItemProvider
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
   public IntervallItemProvider(AdapterFactory adapterFactory)
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

            addIntervallPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

   /**
     * This adds a property descriptor for the Intervall feature.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   protected void addIntervallPropertyDescriptor(Object object)
   {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_Intervall_intervall_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_Intervall_intervall_feature", "_UI_Intervall_type"),
                 PmsPackage.Literals.INTERVALL__INTERVALL,
                 true,
                 false,
                 false,
                 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
                 null,
                 null));
    }

   /**
     * This returns Intervall.gif.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   @Override
   public Object getImage(Object object)
   {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Intervall"));
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
        String label = ((Intervall)object).getGuid();
        return label == null || label.length() == 0 ?
            getString("_UI_Intervall_type") :
            getString("_UI_Intervall_type") + " " + label;
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

        switch (notification.getFeatureID(Intervall.class)) {
            case PmsPackage.INTERVALL__INTERVALL:
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

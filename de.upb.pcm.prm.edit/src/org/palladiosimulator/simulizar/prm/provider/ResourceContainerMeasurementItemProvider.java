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
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.palladiosimulator.simulizar.prm.PrmPackage;
import org.palladiosimulator.simulizar.prm.ResourceContainerMeasurement;

/**
 * This is the item provider adapter for a {@link org.palladiosimulator.simulizar.prm.ResourceContainerMeasurement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ResourceContainerMeasurementItemProvider
   extends PCMModelElementMeasurementItemProvider
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
   public ResourceContainerMeasurementItemProvider(AdapterFactory adapterFactory)
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

            addProcessingResourceTypePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

   /**
     * This adds a property descriptor for the Processing Resource Type feature.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   protected void addProcessingResourceTypePropertyDescriptor(Object object)
   {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ResourceContainerMeasurement_processingResourceType_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ResourceContainerMeasurement_processingResourceType_feature", "_UI_ResourceContainerMeasurement_type"),
                 PrmPackage.Literals.RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE,
                 true,
                 false,
                 true,
                 null,
                 null,
                 null));
    }

   /**
     * This returns ResourceContainerMeasurement.gif.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   @Override
   public Object getImage(Object object)
   {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ResourceContainerMeasurement"));
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
        String label = ((ResourceContainerMeasurement)object).getGuid();
        return label == null || label.length() == 0 ?
            getString("_UI_ResourceContainerMeasurement_type") :
            getString("_UI_ResourceContainerMeasurement_type") + " " + label;
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

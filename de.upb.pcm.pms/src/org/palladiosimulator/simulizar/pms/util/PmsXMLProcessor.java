/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.palladiosimulator.simulizar.pms.PmsPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PmsXMLProcessor extends XMLProcessor
{

   /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   public PmsXMLProcessor()
   {
        super((EPackage.Registry.INSTANCE));
        PmsPackage.eINSTANCE.eClass();
    }
   
   /**
     * Register for "*" and "xml" file extensions the PmsResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   @Override
   protected Map<String, Resource.Factory> getRegistrations()
   {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new PmsResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new PmsResourceFactoryImpl());
        }
        return registrations;
    }

} //PmsXMLProcessor

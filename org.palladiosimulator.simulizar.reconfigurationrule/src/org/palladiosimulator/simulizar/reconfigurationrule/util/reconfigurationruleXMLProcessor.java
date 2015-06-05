/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class reconfigurationruleXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public reconfigurationruleXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        reconfigurationrulePackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the reconfigurationruleResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new reconfigurationruleResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new reconfigurationruleResourceFactoryImpl());
        }
        return registrations;
    }

} //reconfigurationruleXMLProcessor

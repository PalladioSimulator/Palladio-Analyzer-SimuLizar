/**
 */
package org.palladiosimulator.simulizar.reconfiguration.storydiagramreconfiguration.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.palladiosimulator.simulizar.reconfiguration.storydiagramreconfiguration.StoryDiagramReconfigurationPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class StoryDiagramReconfigurationXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StoryDiagramReconfigurationXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        StoryDiagramReconfigurationPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the StoryDiagramReconfigurationResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (registrations == null) {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new StoryDiagramReconfigurationResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new StoryDiagramReconfigurationResourceFactoryImpl());
        }
        return registrations;
    }

} //StoryDiagramReconfigurationXMLProcessor

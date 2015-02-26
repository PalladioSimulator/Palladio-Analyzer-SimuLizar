/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.prm.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.palladiosimulator.simulizar.prm.PrmPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 *
 * @generated
 */
public class PrmXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public PrmXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        PrmPackage.eINSTANCE.eClass();
    }

    /**
     * Register for "*" and "xml" file extensions the PrmResourceFactoryImpl factory. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (this.registrations == null) {
            super.getRegistrations();
            this.registrations.put(XML_EXTENSION, new PrmResourceFactoryImpl());
            this.registrations.put(STAR_EXTENSION, new PrmResourceFactoryImpl());
        }
        return this.registrations;
    }

} // PrmXMLProcessor

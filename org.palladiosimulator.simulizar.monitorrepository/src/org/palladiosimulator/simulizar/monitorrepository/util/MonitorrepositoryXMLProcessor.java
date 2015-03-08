/**
 */
package org.palladiosimulator.simulizar.monitorrepository.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 *
 * @generated
 */
public class MonitorrepositoryXMLProcessor extends XMLProcessor {

    /**
     * Public constructor to instantiate the helper. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MonitorrepositoryXMLProcessor() {
        super((EPackage.Registry.INSTANCE));
        MonitorrepositoryPackage.eINSTANCE.eClass();
    }

    /**
     * Register for "*" and "xml" file extensions the MonitorrepositoryResourceFactoryImpl factory.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations() {
        if (this.registrations == null) {
            super.getRegistrations();
            this.registrations.put(XML_EXTENSION, new MonitorrepositoryResourceFactoryImpl());
            this.registrations.put(STAR_EXTENSION, new MonitorrepositoryResourceFactoryImpl());
        }
        return this.registrations;
    }

} // MonitorrepositoryXMLProcessor

/**
 */
package violationstrategymappings.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import violationstrategymappings.ViolationstrategymappingsPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ViolationstrategymappingsXMLProcessor extends XMLProcessor {

	/**
	 * Public constructor to instantiate the helper.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationstrategymappingsXMLProcessor() {
		super((EPackage.Registry.INSTANCE));
		ViolationstrategymappingsPackage.eINSTANCE.eClass();
	}
	
	/**
	 * Register for "*" and "xml" file extensions the ViolationstrategymappingsResourceFactoryImpl factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Map<String, Resource.Factory> getRegistrations() {
		if (registrations == null) {
			super.getRegistrations();
			registrations.put(XML_EXTENSION, new ViolationstrategymappingsResourceFactoryImpl());
			registrations.put(STAR_EXTENSION, new ViolationstrategymappingsResourceFactoryImpl());
		}
		return registrations;
	}

} //ViolationstrategymappingsXMLProcessor

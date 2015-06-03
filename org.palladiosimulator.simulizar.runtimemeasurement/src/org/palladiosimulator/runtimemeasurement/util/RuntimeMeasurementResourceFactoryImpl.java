/**
 */
package org.palladiosimulator.runtimemeasurement.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * <!-- begin-user-doc --> The <b>Resource Factory</b> associated with the package. <!--
 * end-user-doc -->
 *
 * @see org.palladiosimulator.runtimemeasurement.util.RuntimeMeasurementResourceImpl
 * @generated
 */
public class RuntimeMeasurementResourceFactoryImpl extends ResourceFactoryImpl {

    /**
     * Creates an instance of the resource factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public RuntimeMeasurementResourceFactoryImpl() {
        super();
    }

    /**
     * Creates an instance of the resource. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Resource createResource(final URI uri) {
        final Resource result = new RuntimeMeasurementResourceImpl(uri);
        return result;
    }

} // RuntimeMeasurementResourceFactoryImpl

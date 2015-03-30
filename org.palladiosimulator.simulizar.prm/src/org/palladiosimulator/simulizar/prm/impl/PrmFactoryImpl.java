/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.prm.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.palladiosimulator.simulizar.prm.PRMMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmFactory;
import org.palladiosimulator.simulizar.prm.PrmPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class PrmFactoryImpl extends EFactoryImpl implements PrmFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static PrmFactory init() {
        try {
            final PrmFactory thePrmFactory = (PrmFactory) EPackage.Registry.INSTANCE.getEFactory(PrmPackage.eNS_URI);
            if (thePrmFactory != null) {
                return thePrmFactory;
            }
        } catch (final Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new PrmFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public PrmFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject create(final EClass eClass) {
        switch (eClass.getClassifierID()) {
        case PrmPackage.PRM_MODEL:
            return this.createPRMModel();
        case PrmPackage.PRM_MEASUREMENT:
            return this.createPRMMeasurement();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PRMModel createPRMModel() {
        final PRMModelImpl prmModel = new PRMModelImpl();
        return prmModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PRMMeasurement createPRMMeasurement() {
        final PRMMeasurementImpl prmMeasurement = new PRMMeasurementImpl();
        return prmMeasurement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PrmPackage getPrmPackage() {
        return (PrmPackage) this.getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static PrmPackage getPackage() {
        return PrmPackage.eINSTANCE;
    }

} // PrmFactoryImpl

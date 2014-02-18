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
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmFactory;
import org.palladiosimulator.simulizar.prm.PrmPackage;
import org.palladiosimulator.simulizar.prm.ResourceContainerMeasurement;
import org.palladiosimulator.simulizar.prm.UniqueElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PrmFactoryImpl extends EFactoryImpl implements PrmFactory
{
   /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   public static PrmFactory init()
   {
        try {
            PrmFactory thePrmFactory = (PrmFactory)EPackage.Registry.INSTANCE.getEFactory(PrmPackage.eNS_URI);
            if (thePrmFactory != null) {
                return thePrmFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new PrmFactoryImpl();
    }

   /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   public PrmFactoryImpl()
   {
        super();
    }

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   @Override
   public EObject create(EClass eClass)
   {
        switch (eClass.getClassifierID()) {
            case PrmPackage.PRM_MODEL: return createPRMModel();
            case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT: return createPCMModelElementMeasurement();
            case PrmPackage.UNIQUE_ELEMENT: return createUniqueElement();
            case PrmPackage.RESOURCE_CONTAINER_MEASUREMENT: return createResourceContainerMeasurement();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   public PRMModel createPRMModel()
   {
        PRMModelImpl prmModel = new PRMModelImpl();
        return prmModel;
    }

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   public PCMModelElementMeasurement createPCMModelElementMeasurement()
   {
        PCMModelElementMeasurementImpl pcmModelElementMeasurement = new PCMModelElementMeasurementImpl();
        return pcmModelElementMeasurement;
    }

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   public UniqueElement createUniqueElement()
   {
        UniqueElementImpl uniqueElement = new UniqueElementImpl();
        return uniqueElement;
    }

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   public ResourceContainerMeasurement createResourceContainerMeasurement()
   {
        ResourceContainerMeasurementImpl resourceContainerMeasurement = new ResourceContainerMeasurementImpl();
        return resourceContainerMeasurement;
    }

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   public PrmPackage getPrmPackage()
   {
        return (PrmPackage)getEPackage();
    }

   /**
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
   @Deprecated
   public static PrmPackage getPackage()
   {
        return PrmPackage.eINSTANCE;
    }

} //PrmFactoryImpl

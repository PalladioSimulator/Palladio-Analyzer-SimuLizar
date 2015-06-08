/**
 */
package org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QvtoReconfigurationFactoryImpl extends EFactoryImpl implements QvtoReconfigurationFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static QvtoReconfigurationFactory init() {
        try {
            QvtoReconfigurationFactory theQvtoReconfigurationFactory = (QvtoReconfigurationFactory)EPackage.Registry.INSTANCE.getEFactory(QvtoReconfigurationPackage.eNS_URI);
            if (theQvtoReconfigurationFactory != null) {
                return theQvtoReconfigurationFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new QvtoReconfigurationFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QvtoReconfigurationFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case QvtoReconfigurationPackage.QVTO_TRANSFORMATION: return createQvtoTransformation();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QvtoTransformation createQvtoTransformation() {
        QvtoTransformationImpl qvtoTransformation = new QvtoTransformationImpl();
        return qvtoTransformation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QvtoReconfigurationPackage getQvtoReconfigurationPackage() {
        return (QvtoReconfigurationPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static QvtoReconfigurationPackage getPackage() {
        return QvtoReconfigurationPackage.eINSTANCE;
    }

} //QvtoReconfigurationFactoryImpl

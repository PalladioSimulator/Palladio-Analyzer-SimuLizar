/**
 */
package org.palladiosimulator.simulizar.action.context;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta
 * objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.palladiosimulator.simulizar.action.context.ContextFactory
 * @model kind="package"
 * @generated
 */
public interface ContextPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "context";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://simulizar.palladiosimulator.org/Actions/Context/1.1";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "org.palladiosimulator.action";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    ContextPackage eINSTANCE = org.palladiosimulator.simulizar.action.context.impl.ContextPackageImpl.init();

    /**
     * The meta object id for the
     * '{@link org.palladiosimulator.simulizar.action.context.impl.ExecutionContextImpl
     * <em>Execution Context</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.action.context.impl.ExecutionContextImpl
     * @see org.palladiosimulator.simulizar.action.context.impl.ContextPackageImpl#getExecutionContext()
     * @generated
     */
    int EXECUTION_CONTEXT = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_CONTEXT__ID = IdentifierPackage.IDENTIFIER__ID;

    /**
     * The number of structural features of the '<em>Execution Context</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_CONTEXT_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

    /**
     * Returns the meta object for class
     * '{@link org.palladiosimulator.simulizar.action.context.ExecutionContext <em>Execution
     * Context</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Execution Context</em>'.
     * @see org.palladiosimulator.simulizar.action.context.ExecutionContext
     * @generated
     */
    EClass getExecutionContext();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ContextFactory getContextFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the
         * '{@link org.palladiosimulator.simulizar.action.context.impl.ExecutionContextImpl
         * <em>Execution Context</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.action.context.impl.ExecutionContextImpl
         * @see org.palladiosimulator.simulizar.action.context.impl.ContextPackageImpl#getExecutionContext()
         * @generated
         */
        EClass EXECUTION_CONTEXT = eINSTANCE.getExecutionContext();

    }

} // ContextPackage

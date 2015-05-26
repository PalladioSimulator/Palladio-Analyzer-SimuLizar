/**
 */
package org.palladiosimulator.simulizar.action.mapping;

import de.uka.ipd.sdq.pcm.core.entity.EntityPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.palladiosimulator.simulizar.action.mapping.MappingFactory
 * @model kind="package"
 * @generated
 */
public interface MappingPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "mapping";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://simulizar.palladiosimulator.org/Actions/Mapping/1.0";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "org.palladiosimulator.action";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	MappingPackage eINSTANCE = org.palladiosimulator.simulizar.action.mapping.impl.MappingPackageImpl.init();

	/**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.action.mapping.impl.MappingImpl <em>Mapping</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.simulizar.action.mapping.impl.MappingImpl
     * @see org.palladiosimulator.simulizar.action.mapping.impl.MappingPackageImpl#getMapping()
     * @generated
     */
	int MAPPING = 0;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAPPING__ID = EntityPackage.ENTITY__ID;

	/**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAPPING__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

	/**
     * The feature id for the '<em><b>Controller Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAPPING__CONTROLLER_MAPPINGS = EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Mapping</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int MAPPING_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.action.mapping.impl.ControllerMappingImpl <em>Controller Mapping</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.simulizar.action.mapping.impl.ControllerMappingImpl
     * @see org.palladiosimulator.simulizar.action.mapping.impl.MappingPackageImpl#getControllerMapping()
     * @generated
     */
	int CONTROLLER_MAPPING = 1;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROLLER_MAPPING__ID = EntityPackage.ENTITY__ID;

	/**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROLLER_MAPPING__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

	/**
     * The feature id for the '<em><b>Mapped Call</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROLLER_MAPPING__MAPPED_CALL = EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Controller Role</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROLLER_MAPPING__CONTROLLER_ROLE = EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Mapping</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROLLER_MAPPING__MAPPING = EntityPackage.ENTITY_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Controller Mapping</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROLLER_MAPPING_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 3;


	/**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.action.mapping.Mapping <em>Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Mapping</em>'.
     * @see org.palladiosimulator.simulizar.action.mapping.Mapping
     * @generated
     */
	EClass getMapping();

	/**
     * Returns the meta object for the containment reference list '{@link org.palladiosimulator.simulizar.action.mapping.Mapping#getControllerMappings <em>Controller Mappings</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Controller Mappings</em>'.
     * @see org.palladiosimulator.simulizar.action.mapping.Mapping#getControllerMappings()
     * @see #getMapping()
     * @generated
     */
	EReference getMapping_ControllerMappings();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.action.mapping.ControllerMapping <em>Controller Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Controller Mapping</em>'.
     * @see org.palladiosimulator.simulizar.action.mapping.ControllerMapping
     * @generated
     */
	EClass getControllerMapping();

	/**
     * Returns the meta object for the reference '{@link org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getMappedCall <em>Mapped Call</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Mapped Call</em>'.
     * @see org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getMappedCall()
     * @see #getControllerMapping()
     * @generated
     */
	EReference getControllerMapping_MappedCall();

	/**
     * Returns the meta object for the reference '{@link org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getControllerRole <em>Controller Role</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Controller Role</em>'.
     * @see org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getControllerRole()
     * @see #getControllerMapping()
     * @generated
     */
	EReference getControllerMapping_ControllerRole();

	/**
     * Returns the meta object for the container reference '{@link org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getMapping <em>Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Mapping</em>'.
     * @see org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getMapping()
     * @see #getControllerMapping()
     * @generated
     */
	EReference getControllerMapping_Mapping();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	MappingFactory getMappingFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link org.palladiosimulator.simulizar.action.mapping.impl.MappingImpl <em>Mapping</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.simulizar.action.mapping.impl.MappingImpl
         * @see org.palladiosimulator.simulizar.action.mapping.impl.MappingPackageImpl#getMapping()
         * @generated
         */
		EClass MAPPING = eINSTANCE.getMapping();

		/**
         * The meta object literal for the '<em><b>Controller Mappings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference MAPPING__CONTROLLER_MAPPINGS = eINSTANCE.getMapping_ControllerMappings();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.simulizar.action.mapping.impl.ControllerMappingImpl <em>Controller Mapping</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.simulizar.action.mapping.impl.ControllerMappingImpl
         * @see org.palladiosimulator.simulizar.action.mapping.impl.MappingPackageImpl#getControllerMapping()
         * @generated
         */
		EClass CONTROLLER_MAPPING = eINSTANCE.getControllerMapping();

		/**
         * The meta object literal for the '<em><b>Mapped Call</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONTROLLER_MAPPING__MAPPED_CALL = eINSTANCE.getControllerMapping_MappedCall();

		/**
         * The meta object literal for the '<em><b>Controller Role</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONTROLLER_MAPPING__CONTROLLER_ROLE = eINSTANCE.getControllerMapping_ControllerRole();

		/**
         * The meta object literal for the '<em><b>Mapping</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONTROLLER_MAPPING__MAPPING = eINSTANCE.getControllerMapping_Mapping();

	}

} //MappingPackage

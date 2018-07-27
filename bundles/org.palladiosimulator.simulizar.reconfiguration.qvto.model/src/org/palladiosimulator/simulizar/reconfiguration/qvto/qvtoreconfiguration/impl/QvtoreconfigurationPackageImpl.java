/**
 */
package org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.m2m.internal.qvt.oml.expressions.ExpressionsPackage;
import org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.QvtoTransformation;
import org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.QvtoreconfigurationFactory;
import org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.QvtoreconfigurationPackage;
import org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QvtoreconfigurationPackageImpl extends EPackageImpl implements QvtoreconfigurationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass qvtoTransformationEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.QvtoreconfigurationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private QvtoreconfigurationPackageImpl() {
		super(eNS_URI, QvtoreconfigurationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link QvtoreconfigurationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static QvtoreconfigurationPackage init() {
		if (isInited) return (QvtoreconfigurationPackage)EPackage.Registry.INSTANCE.getEPackage(QvtoreconfigurationPackage.eNS_URI);

		// Obtain or create and register package
		QvtoreconfigurationPackageImpl theQvtoreconfigurationPackage = (QvtoreconfigurationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof QvtoreconfigurationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new QvtoreconfigurationPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ExpressionsPackage.eINSTANCE.eClass();
		ReconfigurationrulePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theQvtoreconfigurationPackage.createPackageContents();

		// Initialize created meta-data
		theQvtoreconfigurationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theQvtoreconfigurationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(QvtoreconfigurationPackage.eNS_URI, theQvtoreconfigurationPackage);
		return theQvtoreconfigurationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQvtoTransformation() {
		return qvtoTransformationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QvtoreconfigurationFactory getQvtoreconfigurationFactory() {
		return (QvtoreconfigurationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		qvtoTransformationEClass = createEClass(QVTO_TRANSFORMATION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ReconfigurationrulePackage theReconfigurationrulePackage = (ReconfigurationrulePackage)EPackage.Registry.INSTANCE.getEPackage(ReconfigurationrulePackage.eNS_URI);
		ExpressionsPackage theExpressionsPackage = (ExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		EGenericType g1 = createEGenericType(theReconfigurationrulePackage.getModelTransformation());
		EGenericType g2 = createEGenericType(theExpressionsPackage.getOperationalTransformation());
		g1.getETypeArguments().add(g2);
		qvtoTransformationEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes, features, and operations; add parameters
		initEClass(qvtoTransformationEClass, QvtoTransformation.class, "QvtoTransformation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //QvtoreconfigurationPackageImpl

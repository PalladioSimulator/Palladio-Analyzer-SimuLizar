/**
 */
package strategies.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import strategies.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StrategiesFactoryImpl extends EFactoryImpl implements StrategiesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StrategiesFactory init() {
		try {
			StrategiesFactory theStrategiesFactory = (StrategiesFactory)EPackage.Registry.INSTANCE.getEFactory(StrategiesPackage.eNS_URI);
			if (theStrategiesFactory != null) {
				return theStrategiesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StrategiesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StrategiesFactoryImpl() {
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
			case StrategiesPackage.RUNTIME_STRATEGIES_MODEL: return createRuntimeStrategiesModel();
			case StrategiesPackage.STRATEGY: return createStrategy();
			case StrategiesPackage.STRATEGY_TYPE: return createStrategyType();
			case StrategiesPackage.RECONFIGURATION_ACTION: return createReconfigurationAction();
			case StrategiesPackage.STRATEGIES_REPOSITORY: return createStrategiesRepository();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuntimeStrategiesModel createRuntimeStrategiesModel() {
		RuntimeStrategiesModelImpl runtimeStrategiesModel = new RuntimeStrategiesModelImpl();
		return runtimeStrategiesModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Strategy createStrategy() {
		StrategyImpl strategy = new StrategyImpl();
		return strategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StrategyType createStrategyType() {
		StrategyTypeImpl strategyType = new StrategyTypeImpl();
		return strategyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReconfigurationAction createReconfigurationAction() {
		ReconfigurationActionImpl reconfigurationAction = new ReconfigurationActionImpl();
		return reconfigurationAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StrategiesRepository createStrategiesRepository() {
		StrategiesRepositoryImpl strategiesRepository = new StrategiesRepositoryImpl();
		return strategiesRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StrategiesPackage getStrategiesPackage() {
		return (StrategiesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static StrategiesPackage getPackage() {
		return StrategiesPackage.eINSTANCE;
	}

} //StrategiesFactoryImpl

/**
 */
package measurements.impl;

import measurements.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MeasurementsFactoryImpl extends EFactoryImpl implements MeasurementsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MeasurementsFactory init() {
		try {
			MeasurementsFactory theMeasurementsFactory = (MeasurementsFactory)EPackage.Registry.INSTANCE.getEFactory(MeasurementsPackage.eNS_URI);
			if (theMeasurementsFactory != null) {
				return theMeasurementsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MeasurementsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementsFactoryImpl() {
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
			case MeasurementsPackage.MEASURED_MONITOR: return createMeasuredMonitor();
			case MeasurementsPackage.RUNTIME_MEASUREMENTS_MODEL: return createRuntimeMeasurementsModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasuredMonitor createMeasuredMonitor() {
		MeasuredMonitorImpl measuredMonitor = new MeasuredMonitorImpl();
		return measuredMonitor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuntimeMeasurementsModel createRuntimeMeasurementsModel() {
		RuntimeMeasurementsModelImpl runtimeMeasurementsModel = new RuntimeMeasurementsModelImpl();
		return runtimeMeasurementsModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementsPackage getMeasurementsPackage() {
		return (MeasurementsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MeasurementsPackage getPackage() {
		return MeasurementsPackage.eINSTANCE;
	}

} //MeasurementsFactoryImpl

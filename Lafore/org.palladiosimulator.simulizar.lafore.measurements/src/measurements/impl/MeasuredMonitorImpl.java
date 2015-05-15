/**
 */
package measurements.impl;

import measurements.MeasuredMonitor;
import measurements.MeasurementsPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.Monitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Measured Monitor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link measurements.impl.MeasuredMonitorImpl#getValue <em>Value</em>}</li>
 *   <li>{@link measurements.impl.MeasuredMonitorImpl#getMetric <em>Metric</em>}</li>
 *   <li>{@link measurements.impl.MeasuredMonitorImpl#getMonitor <em>Monitor</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasuredMonitorImpl extends MinimalEObjectImpl.Container implements MeasuredMonitor {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final double VALUE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected double value = VALUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMetric() <em>Metric</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetric()
	 * @generated
	 * @ordered
	 */
	protected MeasurementSpecification metric;

	/**
	 * The cached value of the '{@link #getMonitor() <em>Monitor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitor()
	 * @generated
	 * @ordered
	 */
	protected Monitor monitor;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MeasuredMonitorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MeasurementsPackage.Literals.MEASURED_MONITOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Monitor getMonitor() {
		if (monitor != null && monitor.eIsProxy()) {
			InternalEObject oldMonitor = (InternalEObject)monitor;
			monitor = (Monitor)eResolveProxy(oldMonitor);
			if (monitor != oldMonitor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MeasurementsPackage.MEASURED_MONITOR__MONITOR, oldMonitor, monitor));
			}
		}
		return monitor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Monitor basicGetMonitor() {
		return monitor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMonitor(Monitor newMonitor) {
		Monitor oldMonitor = monitor;
		monitor = newMonitor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeasurementsPackage.MEASURED_MONITOR__MONITOR, oldMonitor, monitor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(double newValue) {
		double oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeasurementsPackage.MEASURED_MONITOR__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementSpecification getMetric() {
		if (metric != null && metric.eIsProxy()) {
			InternalEObject oldMetric = (InternalEObject)metric;
			metric = (MeasurementSpecification)eResolveProxy(oldMetric);
			if (metric != oldMetric) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MeasurementsPackage.MEASURED_MONITOR__METRIC, oldMetric, metric));
			}
		}
		return metric;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MeasurementSpecification basicGetMetric() {
		return metric;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetric(MeasurementSpecification newMetric) {
		MeasurementSpecification oldMetric = metric;
		metric = newMetric;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MeasurementsPackage.MEASURED_MONITOR__METRIC, oldMetric, metric));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MeasurementsPackage.MEASURED_MONITOR__VALUE:
				return getValue();
			case MeasurementsPackage.MEASURED_MONITOR__METRIC:
				if (resolve) return getMetric();
				return basicGetMetric();
			case MeasurementsPackage.MEASURED_MONITOR__MONITOR:
				if (resolve) return getMonitor();
				return basicGetMonitor();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MeasurementsPackage.MEASURED_MONITOR__VALUE:
				setValue((Double)newValue);
				return;
			case MeasurementsPackage.MEASURED_MONITOR__METRIC:
				setMetric((MeasurementSpecification)newValue);
				return;
			case MeasurementsPackage.MEASURED_MONITOR__MONITOR:
				setMonitor((Monitor)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MeasurementsPackage.MEASURED_MONITOR__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case MeasurementsPackage.MEASURED_MONITOR__METRIC:
				setMetric((MeasurementSpecification)null);
				return;
			case MeasurementsPackage.MEASURED_MONITOR__MONITOR:
				setMonitor((Monitor)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MeasurementsPackage.MEASURED_MONITOR__VALUE:
				return value != VALUE_EDEFAULT;
			case MeasurementsPackage.MEASURED_MONITOR__METRIC:
				return metric != null;
			case MeasurementsPackage.MEASURED_MONITOR__MONITOR:
				return monitor != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (value: ");
		result.append(value);
		result.append(')');
		return result.toString();
	}

} //MeasuredMonitorImpl

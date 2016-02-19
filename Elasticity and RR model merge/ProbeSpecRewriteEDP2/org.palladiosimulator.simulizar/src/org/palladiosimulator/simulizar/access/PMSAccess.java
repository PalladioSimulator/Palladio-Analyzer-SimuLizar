package org.palladiosimulator.simulizar.access;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.pms.PerformanceMeasurement;
import org.palladiosimulator.simulizar.pms.PerformanceMetricEnum;

/**
 * Access class for pms model.
 * 
 * @author Joachim Meyer
 */
public class PMSAccess extends AbstractModelAccess<PMSModel> {

    /**
     * Constructor
     * 
     * @param modelHelper
     *            the model helper.
     */
    public PMSAccess(final ModelHelper modelHelper) {
        super(modelHelper);
    }

    /**
     * @return the pms model.
     */
    @Override
    public PMSModel getModel() {
        return this.getModelHelper().getGlobalPMSModel();
    }

    /**
     * Method checks if given element should be monitored with given performance metric. If yes, it
     * will return the corresponding MeasurementSpecification, otherwise null.
     * 
     * @param element
     *            the element to be checked.
     * @param performanceMetric
     *            the performance metric.
     * @return the MeasurementSpecification, if element should be monitored according to given
     *         performance metric, otherwise null
     */
    public MeasurementSpecification isMonitored(final EObject element, final PerformanceMetricEnum performanceMetric) {
        if (this.getModelHelper().pmsModelExists()) {
            for (final PerformanceMeasurement performanceMeasurement : this.getModel().getPerformanceMeasurements()) {
                if (performanceMeasurement.getPcmElementClassifier().getClassifierID() == element.eClass()
                        .getClassifierID()) {
                    for (final MeasurementSpecification measurementSpecification : performanceMeasurement
                            .getMeasurementSpecification()) {
                        if (measurementSpecification.getPerformanceMetric() == performanceMetric) {
                            return measurementSpecification;
                        }
                    }

                }
            }
        }

        return null;
    }
}

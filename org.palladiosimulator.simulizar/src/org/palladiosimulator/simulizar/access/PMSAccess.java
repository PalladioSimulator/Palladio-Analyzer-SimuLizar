package org.palladiosimulator.simulizar.access;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.ResourceURIMeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.StringMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.AssemblyOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.AssemblyPassiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.SubSystemOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.SystemOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.UsageScenarioMeasuringPoint;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.pms.PerformanceMeasurement;
import org.palladiosimulator.simulizar.pms.PerformanceMetricEnum;

import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.simucomframework.ModelsAtRuntime;

/**
 * Access class for pms model.
 * 
 * @author Joachim Meyer, Sebastian Lehrig
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
                if (elementConformingToMeasuringPoint(element, performanceMeasurement.getMeasuringPoint())) {
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

    /**
     * TODO Really ugly, hacked code ;) Refactor into measuring point objects' class? [Lehrig]
     */
    private Boolean elementConformingToMeasuringPoint(final EObject element, final MeasuringPoint measuringPoint) {
        if (measuringPoint == null) {
            throw new IllegalArgumentException("Measuring point cannot be null");
        } else if (measuringPoint instanceof ResourceURIMeasuringPoint) {
            final String resourceUri = ((ResourceURIMeasuringPoint) measuringPoint).getResourceURI();
            return ModelsAtRuntime.getResourceURI(element).equals(resourceUri);
        } else if (measuringPoint instanceof StringMeasuringPoint) {
            throw new IllegalArgumentException("String measuring points are forbidden for SimuLizar");
        } else if (measuringPoint instanceof ActiveResourceMeasuringPoint) {
            ProcessingResourceSpecification activeResource = ((ActiveResourceMeasuringPoint) measuringPoint)
                    .getActiveResource();
            if (element instanceof ResourceContainer) {
                ResourceContainer resourceContainer = (ResourceContainer) element;
                return resourceContainer.getId().equals(
                        activeResource.getResourceContainer_ProcessingResourceSpecification().getId());
            } else {
                throw new IllegalArgumentException(
                        "ResourceContainer is the only supported ActiveResourceMeasuringPoint");
            }
        } else if (measuringPoint instanceof AssemblyOperationMeasuringPoint) {
            AssemblyOperationMeasuringPoint mP = (AssemblyOperationMeasuringPoint) measuringPoint;
            if (element instanceof ExternalCallAction) {
                ExternalCallAction externalCallAction = (ExternalCallAction) element;
                return externalCallAction.getCalledService_ExternalService().getId()
                        .equals(mP.getOperationSignature().getId())
                        && externalCallAction.getRole_ExternalService().getId().equals(mP.getRole().getId());
            } else {
                throw new IllegalArgumentException("Unsupported element for a AssemblyOperationMeasuringPoint");
            }
        } else if (measuringPoint instanceof AssemblyPassiveResourceMeasuringPoint) {
            throw new IllegalArgumentException("Passive resources are currently unsupported by SimuLizar");
        } else if (measuringPoint instanceof SubSystemOperationMeasuringPoint) {
            throw new IllegalArgumentException("Subsystems are currently unsupported by SimuLizar");
        } else if (measuringPoint instanceof SystemOperationMeasuringPoint) {
            SystemOperationMeasuringPoint mP = (SystemOperationMeasuringPoint) measuringPoint;
            if (element instanceof EntryLevelSystemCall) {
                EntryLevelSystemCall entryLevelSystemCall = (EntryLevelSystemCall) element;
                return entryLevelSystemCall.getOperationSignature__EntryLevelSystemCall().getId()
                        .equals(mP.getOperationSignature().getId())
                        && entryLevelSystemCall.getProvidedRole_EntryLevelSystemCall().getId()
                                .equals(mP.getRole().getId());
            } else {
                throw new IllegalArgumentException("Unsupported element for a SystemOperationMeasuringPoint");
            }
        } else if (measuringPoint instanceof UsageScenarioMeasuringPoint) {
            UsageScenarioMeasuringPoint mP = (UsageScenarioMeasuringPoint) measuringPoint;
            if (element instanceof UsageScenario) {
                UsageScenario usageScenario = (UsageScenario) element;
                return usageScenario.getId().equals(mP.getUsageScenario().getId());
            }
        } else {
            throw new IllegalArgumentException("Unknown measuring point type");
        }
        return false;
    }
}

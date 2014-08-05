package org.palladiosimulator.simulizar.utils;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.ResourceURIMeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.StringMeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.util.MeasuringpointSwitch;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.AssemblyOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.AssemblyPassiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.SubSystemOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.SystemOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.UsageScenarioMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.util.PcmmeasuringpointSwitch;
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

public final class PMSUtil {

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
    public static MeasurementSpecification isMonitored(final PMSModel pmsModel, final EObject element,
            final PerformanceMetricEnum performanceMetric) {
        if (pmsModel != null) {
            for (final PerformanceMeasurement performanceMeasurement : pmsModel.getPerformanceMeasurements()) {
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

    private static boolean elementConformingToMeasuringPoint(final EObject element, final MeasuringPoint measuringPoint) {
        if (measuringPoint == null) {
            throw new IllegalArgumentException("Measuring point cannot be null");
        }

        Boolean result = checkGeneralMeasuringPoints(element, measuringPoint);

        if (result == null) {
            result = checkPCMMeasuringPoints(element, measuringPoint);

            if (result == null) {
                throw new IllegalArgumentException("Unknown measuring point type");
            }
        }

        return result.booleanValue();
    }

    private static Boolean checkPCMMeasuringPoints(final EObject element, final MeasuringPoint measuringPoint) {
        return new PcmmeasuringpointSwitch<Boolean>() {

            @Override
            public Boolean caseActiveResourceMeasuringPoint(ActiveResourceMeasuringPoint object) {
                final ProcessingResourceSpecification activeResource = object.getActiveResource();
                if (element instanceof ResourceContainer) {
                    ResourceContainer resourceContainer = (ResourceContainer) element;
                    return resourceContainer.getId().equals(
                            activeResource.getResourceContainer_ProcessingResourceSpecification().getId());
                } else {
                    throw new IllegalArgumentException(
                            "ResourceContainer is the only supported ActiveResourceMeasuringPoint");
                }
            }

            @Override
            public Boolean caseAssemblyOperationMeasuringPoint(AssemblyOperationMeasuringPoint object) {
                if (element instanceof ExternalCallAction) {
                    final ExternalCallAction externalCallAction = (ExternalCallAction) element;
                    return externalCallAction.getCalledService_ExternalService().getId()
                            .equals(object.getOperationSignature().getId())
                            && externalCallAction.getRole_ExternalService().getId().equals(object.getRole().getId());
                } else {
                    throw new IllegalArgumentException("Unsupported element for a AssemblyOperationMeasuringPoint");
                }
            }

            @Override
            public Boolean caseAssemblyPassiveResourceMeasuringPoint(AssemblyPassiveResourceMeasuringPoint object) {
                throw new IllegalArgumentException("Passive resources are currently unsupported by SimuLizar");
            }

            @Override
            public Boolean caseSubSystemOperationMeasuringPoint(SubSystemOperationMeasuringPoint object) {
                throw new IllegalArgumentException("Subsystems are currently unsupported by SimuLizar");
            }

            @Override
            public Boolean caseSystemOperationMeasuringPoint(SystemOperationMeasuringPoint object) {
                if (element instanceof EntryLevelSystemCall) {
                    EntryLevelSystemCall entryLevelSystemCall = (EntryLevelSystemCall) element;
                    return entryLevelSystemCall.getOperationSignature__EntryLevelSystemCall().getId()
                            .equals(object.getOperationSignature().getId())
                            && entryLevelSystemCall.getProvidedRole_EntryLevelSystemCall().getId()
                                    .equals(object.getRole().getId());
                } else {
                    throw new IllegalArgumentException("Unsupported element for a SystemOperationMeasuringPoint");
                }
            }

            @Override
            public Boolean caseUsageScenarioMeasuringPoint(UsageScenarioMeasuringPoint object) {
                if (element instanceof UsageScenario) {
                    UsageScenario usageScenario = (UsageScenario) element;
                    return usageScenario.getId().equals(object.getUsageScenario().getId());
                }

                throw new IllegalArgumentException("Unknown UsageScenarioMeasuringPoint");
            }

        }

        .doSwitch(measuringPoint);
    }

    private static Boolean checkGeneralMeasuringPoints(final EObject element, final MeasuringPoint measuringPoint) {
        return new MeasuringpointSwitch<Boolean>() {

            @Override
            public Boolean caseResourceURIMeasuringPoint(ResourceURIMeasuringPoint object) {
                final String measuringPointResourceURI = object.getResourceURI();
                final String elementResourceFragment = ModelsAtRuntime.getResourceFragment(element);
                return measuringPointResourceURI.endsWith(elementResourceFragment);
            }

            @Override
            public Boolean caseStringMeasuringPoint(StringMeasuringPoint object) {
                throw new IllegalArgumentException("String measuring points are forbidden for SimuLizar");
            };

        }

        .doSwitch(measuringPoint);
    }

}

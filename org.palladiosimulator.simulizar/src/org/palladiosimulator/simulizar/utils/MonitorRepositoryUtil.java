package org.palladiosimulator.simulizar.utils;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.emfutils.EMFLoadHelper;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.ResourceURIMeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.StringMeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.util.MeasuringpointSwitch;
import org.palladiosimulator.edp2.util.MeasuringPointUtility;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.AssemblyOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.AssemblyPassiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.EntryLevelSystemCallMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.ExternalCallActionMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.ResourceEnvironmentMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.SubSystemOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.SystemOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.UsageScenarioMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.util.PcmmeasuringpointSwitch;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.Monitor;
import org.palladiosimulator.simulizar.pms.MonitorRepository;

import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;

/**
 * Util methods for the monitoring model
 * 
 * @author Steffen Becker, Sebastian Lehrig, Matthias Becker
 * 
 */

public final class MonitorRepositoryUtil {

    /**
     * Method checks if given element should be monitored with given performance metric. If yes, it
     * will return the corresponding MeasurementSpecification, otherwise null.
     * 
     * @param pmsModel
     *            the monitoring model
     * @param element
     *            the element to be checked.
     * @param performanceMetric
     *            the performance metric.
     * @return the MeasurementSpecification, if element should be monitored according to given
     *         performance metric, otherwise null
     */
    public static MeasurementSpecification isMonitored(final MonitorRepository pmsModel, final EObject element,
            final MetricDescription metricDescription) {
        if (pmsModel != null) {
            for (final Monitor performanceMeasurement : pmsModel.getMonitors()) {
                if (elementConformingToMeasuringPoint(element, performanceMeasurement.getMeasuringPoint())) {
                    for (final MeasurementSpecification measurementSpecification : performanceMeasurement
                            .getMeasurementSpecification()) {
                        if (measurementSpecification.getMetricDescription().getId().equals(metricDescription.getId())) {
                            return measurementSpecification;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Method returns the monitored element EObject for a measuring point.
     * 
     * @param mp
     *            the measuring point for which the monitored element shall be returned
     * @return the monitored element
     */
    public static EObject getMonitoredElement(final MeasuringPoint mp) {

        EObject eobject = getEObjectFromPCMMeasuringPoint(mp);

        if (eobject == null) {
            eobject = getEObjectFromGeneralMeasuringPoint(mp);
            if (eobject == null) {
                throw new IllegalArgumentException("Could not find EObject for MeasuringPoint \""
                        + MeasuringPointUtility.measuringPointToString(mp) + "\"");
            }
        }
        return eobject;
    }

    /**
     * Returns the measured element EObject for a general measuring point.
     * 
     * @param measuringPoint
     *            the measuring point
     * @return the measured element
     */
    private static EObject getEObjectFromGeneralMeasuringPoint(MeasuringPoint measuringPoint) {
        return new MeasuringpointSwitch<EObject>() {
            @Override
            public EObject caseResourceURIMeasuringPoint(ResourceURIMeasuringPoint object) {
                return EMFLoadHelper.loadModel(object.getResourceURI());
            }
        }.doSwitch(measuringPoint);
    }

    /**
     * Returns the measured element EObject for a PCM measuring point.
     * 
     * @param measuringPoint
     *            the measuring point
     * @return the measured element
     */
    private static EObject getEObjectFromPCMMeasuringPoint(MeasuringPoint measuringPoint) {

        return new PcmmeasuringpointSwitch<EObject>() {

            @Override
            public EObject caseEntryLevelSystemCallMeasuringPoint(EntryLevelSystemCallMeasuringPoint object) {
                return object.getEntryLevelSystemCall();
            }

            @Override
            public EObject caseUsageScenarioMeasuringPoint(UsageScenarioMeasuringPoint object) {
                return object.getUsageScenario();
            }

            @Override
            public EObject caseResourceEnvironmentMeasuringPoint(ResourceEnvironmentMeasuringPoint object) {
                return object.getResourceEnvironment();
            };

        }.doSwitch(measuringPoint);
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
                }

                return false;
            }

            @Override
            public Boolean caseAssemblyOperationMeasuringPoint(AssemblyOperationMeasuringPoint object) {
                if (element instanceof ExternalCallAction) {
                    final ExternalCallAction externalCallAction = (ExternalCallAction) element;
                    return externalCallAction.getCalledService_ExternalService().getId()
                            .equals(object.getOperationSignature().getId())
                            && externalCallAction.getRole_ExternalService().getId().equals(object.getRole().getId());
                }

                return false;
            }

            @Override
            public Boolean caseAssemblyPassiveResourceMeasuringPoint(AssemblyPassiveResourceMeasuringPoint object) {
                throw new IllegalArgumentException("Passive resources are currently unsupported by SimuLizar");
            }

            @Override
            public Boolean caseEntryLevelSystemCallMeasuringPoint(EntryLevelSystemCallMeasuringPoint object) {
                if (element instanceof EntryLevelSystemCall) {
                    final EntryLevelSystemCall entryLevelSystemCall = (EntryLevelSystemCall) element;
                    return entryLevelSystemCall.getId().equals(object.getEntryLevelSystemCall().getId());
                }

                return false;
            }

            @Override
            public Boolean caseExternalCallActionMeasuringPoint(ExternalCallActionMeasuringPoint object) {
                if (element instanceof ExternalCallAction) {
                    final ExternalCallAction externalCallAction = (ExternalCallAction) element;
                    return externalCallAction.getId().equals(object.getExternalCall().getId());
                }

                return false;
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
                }

                return false;
            }

            @Override
            public Boolean caseUsageScenarioMeasuringPoint(UsageScenarioMeasuringPoint object) {
                if (element instanceof UsageScenario) {
                    UsageScenario usageScenario = (UsageScenario) element;
                    return usageScenario.getId().equals(object.getUsageScenario().getId());
                }

                return false;
            }

            @Override
            public Boolean caseResourceEnvironmentMeasuringPoint(ResourceEnvironmentMeasuringPoint object) {
                if (element instanceof ResourceEnvironment) {
                    ResourceEnvironment resourceEnvironment = (ResourceEnvironment) element;
                    return resourceEnvironment.getEntityName().equals(object.getResourceEnvironment().getEntityName());
                }

                return false;
            };

        }

        .doSwitch(measuringPoint);
    }

    private static Boolean checkGeneralMeasuringPoints(final EObject element, final MeasuringPoint measuringPoint) {
        return new MeasuringpointSwitch<Boolean>() {

            @Override
            public Boolean caseResourceURIMeasuringPoint(ResourceURIMeasuringPoint object) {
                final String measuringPointResourceURI = object.getResourceURI();
                final String elementResourceFragment = EMFLoadHelper.getResourceFragment(element);
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

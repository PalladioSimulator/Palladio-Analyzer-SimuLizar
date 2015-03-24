package org.palladiosimulator.simulizar.utils;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.emfutils.EMFLoadHelper;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.ResourceURIMeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.StringMeasuringPoint;
import org.palladiosimulator.edp2.models.measuringpoint.util.MeasuringpointSwitch;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.AssemblyOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.AssemblyPassiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.SubSystemOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.SystemOperationMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.UsageScenarioMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.util.PcmmeasuringpointSwitch;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.Monitor;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;

import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.resourceenvironment.util.ResourceenvironmentSwitch;
import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.seff.util.SeffSwitch;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.pcm.usagemodel.util.UsagemodelSwitch;

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
     * @param monitorRepositoryModel
     *            the monitoring model
     * @param element
     *            the element to be checked.
     * @param performanceMetric
     *            the performance metric.
     * @return the MeasurementSpecification, if element should be monitored according to given
     *         performance metric, otherwise null
     */
    public static MeasurementSpecification isMonitored(final MonitorRepository monitorRepositoryModel,
            final EObject element, final MetricDescription metricDescription) {
        if (monitorRepositoryModel != null) {
            for (final Monitor monitor : monitorRepositoryModel.getMonitors()) {
                if (elementConformingToMeasuringPoint(element, monitor.getMeasuringPoint())) {
                    for (final MeasurementSpecification measurementSpecification : monitor
                            .getMeasurementSpecifications()) {
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
                throw new IllegalArgumentException("Could not find EObject for MeasuringPoint");
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
            public EObject caseUsageScenarioMeasuringPoint(UsageScenarioMeasuringPoint object) {
                return object.getUsageScenario();
            }

            @Override
            public EObject caseActiveResourceMeasuringPoint(ActiveResourceMeasuringPoint object) {
                EObject result = null;
                if (object != null) {
                    result = object.getActiveResource();
                }
                return result;
            }
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

            private boolean checkActiveResourceMeasuringPoint(ActiveResourceMeasuringPoint mp) {
                final ProcessingResourceSpecification activeResource = mp.getActiveResource();

                return new ResourceenvironmentSwitch<Boolean>() {

                    @Override
                    public Boolean caseResourceContainer(ResourceContainer resourceContainer) {
                        return resourceContainer.getId().equals(
                                activeResource.getResourceContainer_ProcessingResourceSpecification().getId());
                    }

                    @Override
                    public Boolean caseProcessingResourceSpecification(ProcessingResourceSpecification spec) {
                        return activeResource.getId().equals(spec.getId());
                    }

                    @Override
                    public Boolean defaultCase(EObject obj) {
                        return false;
                    }

                }.doSwitch(element);
            }

            @Override
            public Boolean caseActiveResourceMeasuringPoint(ActiveResourceMeasuringPoint object) {
                return this.checkActiveResourceMeasuringPoint(object);
            }

            private boolean checkAssemblyOperationMeasuringPoint(final AssemblyOperationMeasuringPoint mp) {
                return new SeffSwitch<Boolean>() {
                    
                    @Override
                    public Boolean caseExternalCallAction(ExternalCallAction externalCallAction) {
                        return externalCallAction.getCalledService_ExternalService().getId()
                                .equals(mp.getOperationSignature().getId())
                                && externalCallAction.getRole_ExternalService().getId().equals(mp.getRole().getId());
                    }
                    
                    @Override
                    public Boolean defaultCase(EObject object) {
                        return false;
                    }
                    
                }.doSwitch(element);
            }
            
            @Override
            public Boolean caseAssemblyOperationMeasuringPoint(AssemblyOperationMeasuringPoint object) {
                return this.checkAssemblyOperationMeasuringPoint(object);
            }

            @Override
            public Boolean caseAssemblyPassiveResourceMeasuringPoint(AssemblyPassiveResourceMeasuringPoint object) {
                throw new IllegalArgumentException("Passive resources are currently unsupported by SimuLizar");
            }

            @Override
            public Boolean caseSubSystemOperationMeasuringPoint(SubSystemOperationMeasuringPoint object) {
                throw new IllegalArgumentException("Subsystems are currently unsupported by SimuLizar");
            }

            private boolean checkSystemOperationMeasuringPoint(final SystemOperationMeasuringPoint mp) {
                return new UsagemodelSwitch<Boolean>() {
                    
                    @Override
                    public Boolean caseEntryLevelSystemCall(EntryLevelSystemCall entryLevelSystemCall) {
                        return entryLevelSystemCall.getOperationSignature__EntryLevelSystemCall().getId()
                                .equals(mp.getOperationSignature().getId())
                                && entryLevelSystemCall.getProvidedRole_EntryLevelSystemCall().getId()
                                        .equals(mp.getRole().getId());
                    }
                    
                    @Override
                    public Boolean defaultCase(EObject object) {
                        return false;
                    }
                }.doSwitch(element);
            }
            
            @Override
            public Boolean caseSystemOperationMeasuringPoint(SystemOperationMeasuringPoint object) {
                return this.checkSystemOperationMeasuringPoint(object);
            }

            private boolean checkUsageScenarioMeasuringPoint(final UsageScenarioMeasuringPoint mp) {
                return new UsagemodelSwitch<Boolean>() {

                    @Override
                    public Boolean caseUsageScenario(UsageScenario usageScenario) {
                        return usageScenario.getId().equals(mp.getUsageScenario().getId());
                    }
                    
                    @Override
                    public Boolean defaultCase(EObject object) {
                        return false;
                    }
                }.doSwitch(element);
            }
            
            @Override
            public Boolean caseUsageScenarioMeasuringPoint(UsageScenarioMeasuringPoint object) {
               return this.checkUsageScenarioMeasuringPoint(object);
            }

        }.doSwitch(measuringPoint);
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

        }.doSwitch(measuringPoint);
    }
}

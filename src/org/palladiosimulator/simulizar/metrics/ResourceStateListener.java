package org.palladiosimulator.simulizar.metrics;

import java.util.ArrayList;

import org.palladiosimulator.simulizar.access.IModelAccessFactory;
import org.palladiosimulator.simulizar.access.PRMAccess;
import org.palladiosimulator.simulizar.pms.Intervall;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.prm.ResourceContainerMeasurement;

import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.pcm.resourcetype.ProcessingResourceType;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.IStateListener;

/**
 * Utilization performance metric for resources, based on queue length at resources. Interval starts
 * at first change of queue length. Interval limited to simple interval.
 * 
 * @author Joachim Meyer
 * 
 */
public class ResourceStateListener implements IStateListener {
    private double start = 0.0;

    private double lastSimulationTime = 0.0;

    private final ArrayList<Double> measurements = new ArrayList<Double>();

    private boolean lastTimeIdle = true;

    private final double timeIntervall;

    private final ResourceContainerMeasurement resourceContainerMeasurement;

    private final ProcessingResourceSpecification processingResource;

    private final SimuComModel simuComModel;

    private final PRMAccess prmAccess;

    /**
     * Constructor
     * 
     * @param processingResourceType
     *            the processing resource type (pcm) of the resource.
     * @param abstractScheduledResource
     *            the corresponding SimuCom simulated resource of the resource.
     * @param measurementSpecification
     *            the measurement specification of the resource container of the resource.
     * @param resourceContainerMeasurement
     *            the resource container measurement of the prm.
     * @param resourceContainer
     *            the pcm resource container of the resource.
     * @param processingResource
     *            the pcm processing resource specification of the resource.
     */
    public ResourceStateListener(final ProcessingResourceType processingResourceType,
            final AbstractScheduledResource abstractScheduledResource, final SimuComModel simuComModel,
            final MeasurementSpecification measurementSpecification,
            final ResourceContainerMeasurement resourceContainerMeasurement, final ResourceContainer resourceContainer,
            final ProcessingResourceSpecification processingResource, final IModelAccessFactory modelAccessFactory) {
        super();
        this.timeIntervall = ((Intervall) measurementSpecification.getTemporalRestriction()).getIntervall();
        this.simuComModel = simuComModel;
        this.lastSimulationTime = simuComModel.getSimulationControl().getCurrentSimulationTime();
        this.resourceContainerMeasurement = resourceContainerMeasurement;
        this.processingResource = processingResource;
        abstractScheduledResource.addStateListener(this, 0);
        this.prmAccess = modelAccessFactory.getPRMModelAccess();
    }

    /**
     * Add measurement for measurement specification and ResourceContainerMeasurement to prm model.
     * Sets the corresponding processing resource type.
     * 
     * @param value
     *            the measurement value.
     */
    protected void addToPRM(final double value) {
        this.prmAccess.getModel().getPcmModelElementMeasurements().remove(this.resourceContainerMeasurement);
        this.resourceContainerMeasurement.setMeasurementValue(value);
        this.resourceContainerMeasurement.setProcessingResourceType(this.processingResource
                .getActiveResourceType_ActiveResourceSpecification());
        this.prmAccess.getModel().getPcmModelElementMeasurements().add(this.resourceContainerMeasurement);
    }

    /**
     * @see de.uka.ipd.sdq.simucomframework.resources.IStateListener#stateChanged(int, int)
     */
    @Override
    public void stateChanged(final int queueLength, final int instanceId) {
        if (this.simuComModel.getSimulationControl().isRunning()) {
            final double simulationTime = this.simuComModel.getSimulationControl().getCurrentSimulationTime();
            if (this.lastTimeIdle) {
                this.lastTimeIdle = false;
                // calculate time of zero jobs
                final double idleTime = simulationTime - this.lastSimulationTime;
                this.measurements.add(idleTime);
            }
            if (simulationTime <= this.start + this.timeIntervall) {
                if (queueLength == 0) {
                    this.lastTimeIdle = true;
                }
            } else {
                this.start = simulationTime;
                final double utilization = 1 - (this.summArray(this.measurements) / this.timeIntervall);

                this.addToPRM(utilization);

                this.measurements.clear();
            }
            this.lastSimulationTime = simulationTime;
        }
    }

    /**
     * Sums double values in the given list.
     * 
     * @param list
     *            list of double values.
     * @return the sum of all values.
     */
    private double summArray(final ArrayList<Double> list) {
        double sum = 0.0;
        for (final Double idleTime : list) {
            sum += idleTime;
        }
        return sum;
    }

}

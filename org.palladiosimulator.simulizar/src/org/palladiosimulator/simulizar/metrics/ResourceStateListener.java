package org.palladiosimulator.simulizar.metrics;

import java.util.ArrayList;

import org.palladiosimulator.monitorrepository.Intervall;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;

import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.AbstractScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.IStateListener;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

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

    private final RuntimeMeasurement measurement;

    private final ISimulationControl simulationControl;

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
     * @param processingResource
     *            the pcm processing resource specification of the resource.
     */
    public ResourceStateListener(final AbstractScheduledResource abstractScheduledResource,
            final ISimulationControl iSimulationControl, final MeasurementSpecification measurementSpecification,
            final ResourceContainer resourceContainer, final RuntimeMeasurementModel prm) {
        super();
        this.timeIntervall = ((Intervall) measurementSpecification.getTemporalRestriction()).getIntervall();
        this.simulationControl = iSimulationControl;
        this.lastSimulationTime = simulationControl.getCurrentSimulationTime();

        this.measurement = RuntimeMeasurementFactory.eINSTANCE.createRuntimeMeasurement();
        this.measurement.setMeasurementSpecification(measurementSpecification);
        this.measurement.setMeasuringPoint(measurementSpecification.getMonitor().getMeasuringPoint());
        prm.getMeasurements().add(this.measurement);

        abstractScheduledResource.addStateListener(this,
                ((ActiveResourceMeasuringPoint) this.measurement.getMeasuringPoint()).getReplicaID());
    }

    /**
     * Add measurement for measurement specification and ResourceContainerMeasurement to prm model.
     * Sets the corresponding processing resource type.
     * 
     * @param value
     *            the measurement value.
     */
    private void addToPRM(final double value) {
        this.measurement.setMeasuringValue(value);
    }

    /**
     * @see de.uka.ipd.sdq.simucomframework.resources.IStateListener#stateChanged(int, int)
     */
    @Override
    public void stateChanged(final long queueLength, final int instanceId) {
        if (this.simulationControl.isRunning()) {
            final double simulationTime = this.simulationControl.getCurrentSimulationTime();
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

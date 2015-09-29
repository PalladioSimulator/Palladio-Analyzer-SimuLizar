package org.palladiosimulator.simulizar.simulationevents;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;

import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.BasicTriggeredProbe;

import de.uka.ipd.sdq.simucomframework.ResourceRegistry;

/**
 * Probes the number of resource containers within a resource environment. The probe listens to a
 * resource registry (event source type), informing about any changes within the resource
 * environment. Therefore, the probe has to implement the <code>IResourceEnvironmentListener</code>
 * interface and to register itself in the <code>registerListener</code> method to this resource
 * registry.
 *
 * @author Sebastian Lehrig
 */
public class TakeNumberOfResourceContainersTriggeredProbe extends BasicTriggeredProbe<Long, Dimensionless> {

    ResourceRegistry resourceRegistry;

    public TakeNumberOfResourceContainersTriggeredProbe(final ResourceRegistry resourceReg) {
        super(MetricDescriptionConstants.NUMBER_OF_RESOURCE_CONTAINERS);
        this.resourceRegistry = resourceReg;
    }

    @Override
    protected Measure<Long, Dimensionless> getBasicMeasure(final RequestContext measurementContext) {
        return Measure.valueOf(new Long(this.resourceRegistry.getSimulatedResourceContainers().size()),
                Dimensionless.UNIT);
    }
}

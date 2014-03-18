package org.palladiosimulator.simulizar.interpreter.probes;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import de.uka.ipd.sdq.probespec.framework.constants.MetricDescriptionConstants;
import de.uka.ipd.sdq.probespec.framework.probes.BasicProbe;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class ResponseTimeProbe extends BasicProbe<Double, Duration> {

    private final SimuComModel simulationContext;

    public ResponseTimeProbe(SimuComModel simCtx) {
        super(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        this.simulationContext = simCtx;
    }

    @Override
    protected Measure<Double, Duration> getBasicMeasure(RequestContext measurementContext) {
        return Measure.valueOf(simulationContext.getSimulationStatus().getCurrentSimulationTime(), SI.SECOND);
    }

}

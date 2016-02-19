package org.palladiosimulator.simulizar.metrics.aggregators;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.measurementframework.Measurement;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.prm.PRMModel;

/**
 * @author Matthias
 * 
 */
public class ReconfigurationAggregator extends PRMRecorder implements IMeasurementSourceListener {

    public ReconfigurationAggregator(final PRMModel prmAccess, final MeasurementSpecification measurementSpecification,
            final Calculator responseTimeCalculator, final String measurementId, final EObject monitoredElement,
            final double baseSimulationTime) {
        super(prmAccess, measurementSpecification, monitoredElement);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void preUnregister() {
        // TODO Auto-generated method stub

    }

    @Override
    public void newMeasurementAvailable(Measurement measurement) {
        // TODO Auto-generated method stub

    }

}

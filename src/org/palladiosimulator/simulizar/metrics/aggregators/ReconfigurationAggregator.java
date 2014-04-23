/**
 * 
 */
package org.palladiosimulator.simulizar.metrics.aggregators;

import javax.activation.UnsupportedDataTypeException;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.measurementspec.IMeasurementSourceListener;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.simulizar.access.PRMAccess;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;

/**
 * @author Matthias
 * 
 */
public class ReconfigurationAggregator extends PRMRecorder implements IMeasurementSourceListener {

    public ReconfigurationAggregator(final PRMAccess prmAccess,
            final MeasurementSpecification measurementSpecification, final Calculator responseTimeCalculator,
            final String measurementId, final EObject monitoredElement,
            final PCMModelElementMeasurement pcmModelElementMeasurement, final double baseSimulationTime)
            throws UnsupportedDataTypeException {
        super(prmAccess, measurementSpecification, pcmModelElementMeasurement);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.simulizar.metrics.PRMRecorder#getMonitoredPCMModellElement()
     */
    @Override
    protected EObject getMonitoredPCMModellElement() {
        // TODO Auto-generated method stub
        return null;
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

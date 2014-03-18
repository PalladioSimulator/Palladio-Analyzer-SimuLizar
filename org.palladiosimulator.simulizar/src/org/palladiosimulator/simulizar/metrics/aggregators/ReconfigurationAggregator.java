/**
 * 
 */
package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.List;

import javax.activation.UnsupportedDataTypeException;
import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.access.PRMAccess;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;

import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorListener;

/**
 * @author Matthias
 * 
 */
public class ReconfigurationAggregator extends PRMRecorder implements ICalculatorListener {

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
    public void calculated(List<Measure<?, ? extends Quantity>> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void preUnregister() {
        // TODO Auto-generated method stub
        
    }

}

package org.palladiosimulator.simulizar.utils;

import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;

import de.uka.ipd.sdq.probespec.framework.probes.Probe;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;
import de.uka.ipd.sdq.probespec.framework.ProbeSpecContext;
import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorFactory;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

import java.util.List;

/**
 * Util for the Probe Specification Framework.
 * 
 * @author Joachim Meyer
 * 
 */
public class PCMInterpreterProbeSpecUtil {
    protected static final Logger LOG = Logger.getLogger(PCMInterpreterProbeSpecUtil.class.getName());

    final ProbeSpecContext probeSpecContext;

    private final SimuComModel simuComModel;

    /**
     * Constructor
     * 
     * @param modelHelper
     *            the model helper.
     */
    public PCMInterpreterProbeSpecUtil(final SimuComModel simuComModel) {
        super();
        this.simuComModel = simuComModel;
        this.probeSpecContext = simuComModel.getProbeSpecContext();
    }

    /**
     * Creates a response time calculator in the probe specification framework.
     * 
     * @param measurementId
     *            id of this measurement.
     * @param calculatorName
     *            name of the response time calculator
     * @param measurementSpecification
     *            the corresponding measurement specification from the pms model.
     * @param pcmElement
     *            the pcm element to be measured.
     * @param simuComModel
     *            the SimuCom model.
     * @return the created calculator, null if calculator already exists.
     */
    public Calculator createResponseTimeCalculator(final List<Probe> probeList,
            final String calculatorName,
            final EObject pcmElement) {

        //FIXME: Use EObject instead of calculatorName
        
        final ICalculatorFactory calculatorFactory = this.probeSpecContext.getCalculatorFactory();
            
        final Calculator responseTimeCalculator = calculatorFactory.buildResponseTimeCalculator(calculatorName, probeList);

        return responseTimeCalculator;
    }


    /**
     * Takes current time sample.
     * 
     * @param probeID
     *            the start or stop probe id.
     * @param measurementId
     *            id of the measurement.
     * @param simProcess
     *            the sim process.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void takeCurrentTimeSample(final Probe probe, final RequestContext reqCtx) {
        
        probe.takeMeasurement(reqCtx);
    }

    public ProbeSpecContext getProbeSpecContext() {
        return this.probeSpecContext;
    }

}

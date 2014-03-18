/**
 * 
 */
package org.palladiosimulator.simulizar.interpreter.listener;

import java.util.LinkedList;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;
import javax.measure.quantity.Duration;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.access.IModelAccessFactory;
import org.palladiosimulator.simulizar.access.PMSAccess;
import org.palladiosimulator.simulizar.access.PRMAccess;
import org.palladiosimulator.simulizar.interpreter.probes.ResponseTimeProbe;
import org.palladiosimulator.simulizar.metrics.aggregators.ResponseTimeAggregator;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PerformanceMetricEnum;
import org.palladiosimulator.simulizar.prm.PrmFactory;
import org.palladiosimulator.simulizar.utils.PCMInterpreterProbeSpecUtil;

import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.probespec.framework.probes.BasicProbe;
import de.uka.ipd.sdq.probespec.framework.probes.Probe;
import de.uka.ipd.sdq.probespec.framework.requestcontext.RequestContext;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

/**
 * @author snowball
 * 
 */
public class ProbeSpecListener extends AbstractInterpreterListener {

    private static final String END_PROBE_MARKER = "_resp2";
    private static final String START_PROBE_MARKER = "_resp1";
    private final PMSAccess pmsModelAccess;
    private final PRMAccess prmAccess;
    private final PCMInterpreterProbeSpecUtil pcmInterpreterProbeSpecUtil;
    private ResponseTimeProbe startProbe;
    private ResponseTimeProbe stopProbe;
    private final static Logger LOG = Logger.getLogger(ProbeSpecListener.class);
    
    /**
	 * 
	 */
    public ProbeSpecListener(final IModelAccessFactory modelAccessFactory, final SimuComModel simuComModel) {
        super();
        this.pmsModelAccess = modelAccessFactory.getPMSModelAccess();
        this.prmAccess = modelAccessFactory.getPRMModelAccess();
        this.pcmInterpreterProbeSpecUtil = new PCMInterpreterProbeSpecUtil(simuComModel);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#
     * beginUsageScenarioInterpretation
     * (de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
     */
    @Override
    public void beginUsageScenarioInterpretation(final ModelElementPassedEvent<UsageScenario> event) {
        this.startMeasurement(event);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#
     * endUsageScenarioInterpretation
     * (de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
     */
    @Override
    public void endUsageScenarioInterpretation(final ModelElementPassedEvent<UsageScenario> event) {
        this.endMeasurement(event);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#
     * beginEntryLevelSystemCallInterpretation
     * (de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
     */
    @Override
    public void beginEntryLevelSystemCallInterpretation(final ModelElementPassedEvent<EntryLevelSystemCall> event) {
        this.startMeasurement(event);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#
     * endEntryLevelSystemCallInterpretation
     * (de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
     */
    @Override
    public void endEntryLevelSystemCallInterpretation(final ModelElementPassedEvent<EntryLevelSystemCall> event) {
        this.endMeasurement(event);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.upb.pcm.simulizar.interpreter.listener.AbstractInterpreterListener#
     * beginExternalCallInterpretation
     * (de.upb.pcm.simulizar.interpreter.listener.ModelElementPassedEvent)
     */
    @Override
    public void beginExternalCallInterpretation(final RDSEFFElementPassedEvent<ExternalCallAction> event) {
        this.startMeasurement(event);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.upb.pcm.simulizar.interpreter.listener.AbstractInterpreterListener#
     * endExternalCallInterpretation
     * (de.upb.pcm.simulizar.interpreter.listener.ModelElementPassedEvent)
     */
    @Override
    public void endExternalCallInterpretation(final RDSEFFElementPassedEvent<ExternalCallAction> event) {
        this.endMeasurement(event);
    }

    /**
     * Initializes response time measurement.
     * 
     */
    private <T extends Entity> void initReponseTimeMeasurement(final ModelElementPassedEvent<T> event) {
        
        EObject modelElement = event.getModelElement();
        SimuComModel simuComModel = event.getThread().getModel();
        
        final MeasurementSpecification measurementSpecification = this.pmsModelAccess.isMonitored(modelElement,
                PerformanceMetricEnum.RESPONSE_TIME);
        if (measurementSpecification != null) {
            
            final String calculatorName = this.getCalculatorName(event);
            
            final List<Probe> probeList = new LinkedList<Probe>();
            
            this.startProbe = new ResponseTimeProbe(simuComModel);
            this.stopProbe = new ResponseTimeProbe(simuComModel);
            
            probeList.add(this.startProbe);
            probeList.add(this.stopProbe);
            
            final Calculator calculator = this.pcmInterpreterProbeSpecUtil.createResponseTimeCalculator(probeList,
                    calculatorName, modelElement);

            if (calculatorWasCreated(calculator)) {
                try {
                    new ResponseTimeAggregator(this.prmAccess, measurementSpecification, calculator, calculatorName,
                            modelElement, PrmFactory.eINSTANCE.createPCMModelElementMeasurement(), simuComModel
                                    .getSimulationControl().getCurrentSimulationTime());
                } catch (final UnsupportedDataTypeException e) {
                    LOG.error(e);
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * @param calculator
     * @return
     */
    private boolean calculatorWasCreated(final Calculator calculator) {
        return calculator != null;
    }

    /**
     * @param <T>
     * @param event
     */
    private <T extends Entity> void startMeasurement(final ModelElementPassedEvent<T> event) {
        
        this.initReponseTimeMeasurement(event);
        
        //this.pcmInterpreterProbeSpecUtil.takeCurrentTimeSample(startProbe, event.getThread().getRequestContext());
        this.startProbe.takeMeasurement(event.getThread().getRequestContext());
    }

    /**
     * @param event
     */
    private <T extends Entity> void endMeasurement(final ModelElementPassedEvent<T> event) {
        
        
        //this.pcmInterpreterProbeSpecUtil.takeCurrentTimeSample(stopProbe, event.getThread().getRequestContext());
        this.stopProbe.takeMeasurement(event.getThread().getRequestContext());
    }

    /**
     * @param event
     * @return
     */
    private <T extends Entity> String getCalculatorName(final ModelElementPassedEvent<T> event) {
        final Entity entity = event.getModelElement();
        final StringBuilder sb = new StringBuilder();

        sb.append(entity.eClass().getName());
        sb.append(" >");
        sb.append(entity.getEntityName());
        sb.append(" (ID: ");
        sb.append(entity.getId());
        if (event instanceof RDSEFFElementPassedEvent) {
            final RDSEFFElementPassedEvent<T> rdseffEvent = (RDSEFFElementPassedEvent<T>) event;
            sb.append(", AssCtx: ");
            sb.append(rdseffEvent.getAssemblyContext().getId());
        }
        sb.append(" )<");

        return sb.toString();
    }
}

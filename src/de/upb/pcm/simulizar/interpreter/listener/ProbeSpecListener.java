/**
 * 
 */
package de.upb.pcm.simulizar.interpreter.listener;

import javax.activation.UnsupportedDataTypeException;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.upb.pcm.pms.MeasurementSpecification;
import de.upb.pcm.pms.PerformanceMetricEnum;
import de.upb.pcm.prm.PrmFactory;
import de.upb.pcm.simulizar.access.IModelAccessFactory;
import de.upb.pcm.simulizar.access.PMSAccess;
import de.upb.pcm.simulizar.access.PRMAccess;
import de.upb.pcm.simulizar.metrics.aggregators.ResponseTimeAggregator;
import de.upb.pcm.simulizar.utils.PCMInterpreterProbeSpecUtil;

/**
 * @author snowball
 *
 */
public class ProbeSpecListener extends AbstractInterpreterListener {

	private static final String END_PROBE_MARKER = "_resp2";
	private static final String START_PROBE_MARKER = "_resp1";
	private final PMSAccess pmsModelAccess;
	private final PRMAccess prmAccess;
	private PCMInterpreterProbeSpecUtil pcmInterpreterProbeSpecUtil;

	/**
	 * 
	 */
	public ProbeSpecListener(final IModelAccessFactory modelAccessFactory, final SimuComModel simuComModel) {
		super();
		this.pmsModelAccess = modelAccessFactory.getPMSModelAccess();
		this.prmAccess = modelAccessFactory.getPRMModelAccess();
		this.pcmInterpreterProbeSpecUtil = new PCMInterpreterProbeSpecUtil(simuComModel);
	}

	/* (non-Javadoc)
	 * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#beginUsageScenarioInterpretation(de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void beginUsageScenarioInterpretation(ModelElementPassedEvent<UsageScenario> event) {
		startMeasurement(event);
	}

	/* (non-Javadoc)
	 * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#endUsageScenarioInterpretation(de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void endUsageScenarioInterpretation(ModelElementPassedEvent<UsageScenario> event) {
		endMeasurement(event);
	}

	/* (non-Javadoc)
	 * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#beginEntryLevelSystemCallInterpretation(de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void beginEntryLevelSystemCallInterpretation(ModelElementPassedEvent<EntryLevelSystemCall> event) {
		startMeasurement(event);
	}

	/* (non-Javadoc)
	 * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#endEntryLevelSystemCallInterpretation(de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void endEntryLevelSystemCallInterpretation(ModelElementPassedEvent<EntryLevelSystemCall> event) {
		endMeasurement(event);
	}

	/* (non-Javadoc)
	 * @see de.upb.pcm.simulizar.interpreter.listener.AbstractInterpreterListener#beginExternalCallInterpretation(de.upb.pcm.simulizar.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void beginExternalCallInterpretation(RDSEFFElementPassedEvent<ExternalCallAction> event) {
		startMeasurement(event);
	}

	/* (non-Javadoc)
	 * @see de.upb.pcm.simulizar.interpreter.listener.AbstractInterpreterListener#endExternalCallInterpretation(de.upb.pcm.simulizar.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void endExternalCallInterpretation(RDSEFFElementPassedEvent<ExternalCallAction> event) {
		endMeasurement(event);
	}

	/**
	 * Initializes response time measurement.
	 * 
	 * @param modelElement
	 *            the model element to be measured.
	 * @param calculatorName
	 *            the name of the response time calculator.
	 * @param startProbeId
	 *            start probe id.
	 * @param stopProbeId
	 *            stop probe id.
	 */
	private void initReponseTimeMeasurement(final EObject modelElement, final String calculatorName, final String startProbeId,
			final String stopProbeId, SimuComModel simuComModel) {
		MeasurementSpecification measurementSpecification = 
				pmsModelAccess.isMonitored(modelElement, PerformanceMetricEnum.RESPONSE_TIME);
		if (measurementSpecification != null) {

			final Calculator calculator = this.pcmInterpreterProbeSpecUtil.createResponseTimeCalculator(startProbeId, stopProbeId,
					calculatorName, calculatorName, measurementSpecification, modelElement, simuComModel);

			if (calculator != null) {
				try {
					new ResponseTimeAggregator(this.prmAccess, measurementSpecification, calculator, calculatorName, modelElement,
							PrmFactory.eINSTANCE.createPCMModelElementMeasurement(), simuComModel.getSimulationControl()
									.getCurrentSimulationTime());
				} catch (final UnsupportedDataTypeException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param <T>
	 * @param event
	 */
	private <T extends Entity> void startMeasurement(ModelElementPassedEvent<T> event) {
		final String calculatorName = getCalculatorName(event);
		final String startProbeID = calculatorName + START_PROBE_MARKER;
		final String stopProbeID = calculatorName + END_PROBE_MARKER;
	
		initReponseTimeMeasurement(event.getModelElement(), calculatorName, startProbeID, stopProbeID, event.getThread().getModel());
	
		this.pcmInterpreterProbeSpecUtil.takeCurrentTimeSample(startProbeID, calculatorName, event.getThread());
	}

	/**
	 * @param event
	 */
	private <T extends Entity> void endMeasurement(ModelElementPassedEvent<T> event) {
		final String calculatorName = getCalculatorName(event);
		final String stopProbeID = calculatorName + END_PROBE_MARKER;
	
		this.pcmInterpreterProbeSpecUtil.takeCurrentTimeSample(stopProbeID, calculatorName, event.getThread());
	}

	/**
	 * @param event
	 * @return
	 */
	private <T extends Entity> String getCalculatorName(ModelElementPassedEvent<T> event) {
		Entity entity = event.getModelElement();
		StringBuilder sb = new StringBuilder();
		
		sb.append(entity.eClass().getName());
		sb.append(" >");
		sb.append(entity.getEntityName());
		sb.append(" (ID: ");
		sb.append(entity.getId());
		if (event instanceof RDSEFFElementPassedEvent) {
			RDSEFFElementPassedEvent<T> rdseffEvent = (RDSEFFElementPassedEvent<T>) event;
			sb.append(", AssCtx: ");
			sb.append(rdseffEvent.getAssemblyContext().getId());
		}
		sb.append(" )<");
		
		return sb.toString();
	}
}

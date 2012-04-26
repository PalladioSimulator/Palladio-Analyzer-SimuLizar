/**
 * 
 */
package de.upb.pcm.interpreter.interpreter.listener;

import javax.activation.UnsupportedDataTypeException;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.identifier.Identifier;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.upb.pcm.interpreter.access.IModelAccessFactory;
import de.upb.pcm.interpreter.access.PMSAccess;
import de.upb.pcm.interpreter.access.PRMAccess;
import de.upb.pcm.interpreter.metrics.aggregators.ResponseTimeAggregator;
import de.upb.pcm.interpreter.utils.PCMInterpreterProbeSpecUtil;
import de.upb.pcm.pms.MeasurementSpecification;
import de.upb.pcm.pms.PerformanceMetricEnum;
import de.upb.pcm.prm.PrmFactory;

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
	private <T extends Identifier> void startMeasurement(ModelElementPassedEvent<T> event) {
		final String calculatorName = event.getModelElement().getId();
		final String startProbeID = calculatorName + START_PROBE_MARKER;
		final String stopProbeID = calculatorName + END_PROBE_MARKER;
	
		initReponseTimeMeasurement(event.getModelElement(), calculatorName, startProbeID, stopProbeID, event.getThread().getModel());
	
		this.pcmInterpreterProbeSpecUtil.takeCurrentTimeSample(startProbeID, calculatorName, event.getThread());
	}

	/**
	 * @param event
	 */
	private <T extends Identifier> void endMeasurement(ModelElementPassedEvent<T> event) {
		final String calculatorName = event.getModelElement().getId();
		final String stopProbeID = calculatorName + END_PROBE_MARKER;
	
		this.pcmInterpreterProbeSpecUtil.takeCurrentTimeSample(stopProbeID, calculatorName, event.getThread());
	}
}

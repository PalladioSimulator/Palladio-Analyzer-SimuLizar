package org.palladiosimulator.simulizar.usagemodel;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredSimulationEntity;

import de.uka.ipd.sdq.pcm.usagemodel.OpenWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import dlim.TimeDependentWorkFunctionContainer;
import dlim.WorkLoadSequence;
import dlim.generator.ArrivalRateTuple;
import dlim.generator.IGeneratorConstants;
import dlim.generator.ModelEvaluator;

public class UsageEvolver {
	/** Logger of this class. */
	private static final Logger LOGGER = Logger.getLogger(UsageEvolver.class
			.getName());

	private final SimuLizarRuntimeState runtimeState;
	private final UsageModel usageModel;
	private final WorkLoadSequence dynamicWorkload;

	public UsageEvolver(final SimuLizarRuntimeState runtimeState) {
		super();
		this.runtimeState = runtimeState;
		this.usageModel = runtimeState.getModelAccess().getGlobalPCMModel().getUsageModel();
		this.dynamicWorkload = runtimeState.getModelAccess().getDEModel();
	}

	public void start() {
		new PeriodicallyTriggeredSimulationEntity(this.runtimeState.getModel(), 0, 1000) {
			double step = 0.5;
			long count = 0;
			OpenWorkload wl = (OpenWorkload) usageModel.getUsageScenario_UsageModel().get(0).getWorkload_UsageScenario();
			EList<TimeDependentWorkFunctionContainer> tdwfContainer = dynamicWorkload.getWorkFunctionContainers();

			// DlimPackage dlimPackage = DlimPackage.eINSTANCE;
			// DlimFactory dlimFactory = dlimPackage.getDlimFactory();
			// Sequence dynamicWorkload = dlimFactory.createSequence();

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.palladiosimulator.simulizar.simulationevents.
			 * PeriodicallyTriggeredSimulationEntity #triggerInternal()
			 */
			@Override
			protected void triggerInternal() {
				LOGGER.info("Dynamic Workload model has been specified: "+ dynamicWorkload.getName());
				if (dynamicWorkload != null) {
//					ArrayList<ArrivalRateTuple> arrivalRateList = new ArrayList<ArrivalRateTuple>();
					LOGGER.info("Duration: "+dynamicWorkload.getFinalDuration());
					double samplingStep = 1.0;
					for (double i = samplingStep; i < dynamicWorkload.getFinalDuration(); i += samplingStep) {
						LOGGER.info(i+" second in "+dynamicWorkload.getFinalDuration());
						for (TimeDependentWorkFunctionContainer tdwfc : dynamicWorkload.getWorkFunctionContainers()) {
							double workStartTime = tdwfc.getWorkStartTime();
							double workEndTime = tdwfc.getWorkStartTime() + tdwfc.getWorkDuration();
							if (i >= workStartTime && i <= workEndTime) {
								int usIndex = -1;
								for (UsageScenario us : usageModel.getUsageScenario_UsageModel()) {
									if (us.getId().equalsIgnoreCase(tdwfc.getWork().getId())) {
										usIndex = usageModel.getUsageScenario_UsageModel().indexOf(us);
									}
								}
//								OpenWorkload activeWorkLoad = (OpenWorkload) tdwfc.getWork().getWorkload_UsageScenario();
								OpenWorkload activeWorkLoad = (OpenWorkload) usageModel.getUsageScenario_UsageModel().get(usIndex).getWorkload_UsageScenario();
								LOGGER.info("Active work: "+tdwfc.getWork().getEntityName());
								ModelEvaluator evaluator = new ModelEvaluator(tdwfc.getLoadSequence(), 5, IGeneratorConstants.EVALUATION);
								double loadTimeSample = 0.0;
								loadTimeSample = i - tdwfc.getWorkStartTime();
								double y = evaluator.getArrivalRateAtTime(loadTimeSample);
								activeWorkLoad.getInterArrivalTime_OpenWorkload().setSpecification("Exp(" + y + ")");
								LOGGER.info("At sampling time = "+i+" -"+tdwfc.getWork().getEntityName()+"- is active with a load = "+y+" (load sampling time = "+loadTimeSample+")");
							}
						}
					}
					
					// int terminateAfterLoops = dynamicWorkload
					// .getTerminateAfterLoops();
					// double terminateAfterTime = dynamicWorkload
					// .getTerminateAfterTime();
					// double finalDuration =
					// dynamicWorkload.getFinalDuration();
					// double loopDuration = dynamicWorkload.getLoopDuration();
					// EList<TimeDependentFunctionContainer>
					// sequenceFunctionContainersList = dynamicWorkload
					// .getSequenceFunctionContainers();
					// EList<Combinator> cominatorList = dynamicWorkload
					// .getCombine();
					
					// ArrayList<ArrivalRateTuple> innerArrivalRateList = new
					// ArrayList<ArrivalRateTuple>();
					
					// initial implementation
//					ModelEvaluator evaluator = new ModelEvaluator(dynamicWorkload, 5, IGeneratorConstants.EVALUATION);
					
//					for (double i = samplingStep / 2.0; i < evaluator.getDuration(); i += samplingStep) {
//						double y = evaluator.getArrivalRateAtTime(i);
//						arrivalRateList.add(new ArrivalRateTuple(i, y));

						// double innerY = evaluator.getArrivalRateDelta(i, -1,
						// new boolean[1]);
						// innerArrivalRateList.add(new
						// ArrivalRateTuple(i,innerY));
//					}
//					for (ArrivalRateTuple t : arrivalRateList) {
//						int y = (int) t.getArrivalRate();
//						wl.getInterArrivalTime_OpenWorkload().setSpecification(
//								"Exp(" + y + ")");
						// double innerSequenceArrRate =
						// evaluator.getArrivalRateDelta(t.getTimeStamp(), -1,
						// new boolean[1]);
						// double innerSequenceArrRate =
						// innerArrivalRateList.get(arrivalRateList.indexOf(t)).getArrivalRate();
						// for (int i = 0; i <
						// dynamicWorkload.getCombine().size(); i++) {
						// boolean[] isMult = new boolean[1];
						// double delta =
						// evaluator.getArrivalRateDelta(t.getTimeStamp(), i,
						// isMult);
						// wl.getInterArrivalTime_OpenWorkload().setSpecification("Exp("
						// + delta + ")");
						// }

//					}

				}
				// if (false && count <= 4) {
				// OpenWorkload wl = (OpenWorkload) usageModel
				// .getUsageScenario_UsageModel().get(0)
				// .getWorkload_UsageScenario();
				// wl.getInterArrivalTime_OpenWorkload().setSpecification("Exp("
				// + (0.5d + count++ * step) + ")");
				// }
			}

		};
	}
}

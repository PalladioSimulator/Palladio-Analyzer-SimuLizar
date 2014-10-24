package org.palladiosimulator.simulizar.usagemodel;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredSimulationEntity;

import de.uka.ipd.sdq.pcm.core.CoreFactory;
import de.uka.ipd.sdq.pcm.core.PCMRandomVariable;
import de.uka.ipd.sdq.pcm.parameter.VariableUsage;
import de.uka.ipd.sdq.pcm.usagemodel.AbstractUserAction;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.OpenWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelFactory;
import dlim.InputParameterCharacterizationContainer;
import dlim.TimeDependentWorkFunctionContainer;
import dlim.WorkLoadSequence;
import dlim.generator.IGeneratorConstants;
import dlim.generator.ModelEvaluator;

public class UsageEvolver {
	/** Logger of this class. */
	private static final Logger LOGGER = Logger.getLogger(UsageEvolver.class.getName());

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
			// double step = 0.5;
			// long count = 0;
			// OpenWorkload wl = (OpenWorkload)
			// usageModel.getUsageScenario_UsageModel().get(0).getWorkload_UsageScenario();
			// EList<TimeDependentWorkFunctionContainer> tdwfContainer =
			// dynamicWorkload.getWorkFunctionContainers();

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.palladiosimulator.simulizar.simulationevents.
			 * PeriodicallyTriggeredSimulationEntity #triggerInternal()
			 */
			@Override
			protected void triggerInternal() {
				LOGGER.info("Dynamic Workload model has been specified: " + dynamicWorkload.getName());
				if (dynamicWorkload != null) {
					// ArrayList<ArrivalRateTuple> arrivalRateList = new
					// ArrayList<ArrivalRateTuple>();
					LOGGER.info("Duration: " + dynamicWorkload.getTerminateAfterTime());
					double samplingStep = 1.0;

					for (double i = 0; i <= dynamicWorkload.getTerminateAfterTime(); i += samplingStep) {
						LOGGER.info(i + " second in " + dynamicWorkload.getTerminateAfterTime());
						ArrayList<TimeDependentWorkFunctionContainer> activeWorkLoadList = new ArrayList<TimeDependentWorkFunctionContainer>();
						ArrayList<TimeDependentWorkFunctionContainer> inActiveWorkLoadList = new ArrayList<TimeDependentWorkFunctionContainer>();
						for (TimeDependentWorkFunctionContainer tdwfc : dynamicWorkload.getWorkFunctionContainers()) {
							double workStartTime = tdwfc.getWorkStartTime();
							double workEndTime = tdwfc.getWorkStartTime() + tdwfc.getWorkDuration();
							if (i >= workStartTime && i <= workEndTime) {
								activeWorkLoadList.add(tdwfc);
							} else {
								if (tdwfc.getTimeSynchronization() != null) {
									if (i >= tdwfc.getTimeSynchronization().getWorkStartTime()
											&& i <= (tdwfc.getTimeSynchronization().getWorkStartTime() + tdwfc.getTimeSynchronization().getWorkDuration())) {
										activeWorkLoadList.add(tdwfc);
									}
								} else {
									inActiveWorkLoadList.add(tdwfc);
								}
							}
						}

						for (TimeDependentWorkFunctionContainer inActiveTdwfc : inActiveWorkLoadList) {
							int usIndex = -1;
							for (UsageScenario us : usageModel.getUsageScenario_UsageModel()) {
								if (us.getId().equalsIgnoreCase(inActiveTdwfc.getWork().getId())) {
									usIndex = usageModel.getUsageScenario_UsageModel().indexOf(us);
								}
							}
							OpenWorkload inActiveWorkLoad = (OpenWorkload) usageModel.getUsageScenario_UsageModel().get(usIndex).getWorkload_UsageScenario();
							inActiveWorkLoad.getInterArrivalTime_OpenWorkload().setSpecification("0.0");
						}

						for (TimeDependentWorkFunctionContainer activeTdwfc : activeWorkLoadList) {
							int usIndex = -1;
							for (UsageScenario us : usageModel.getUsageScenario_UsageModel()) {
								if (us.getId().equalsIgnoreCase(activeTdwfc.getWork().getId())) {
									usIndex = usageModel.getUsageScenario_UsageModel().indexOf(us);
								}
							}
							UsageScenario activeScenario = usageModel.getUsageScenario_UsageModel().get(usIndex);
							OpenWorkload activeScenario_WorkLoad = null;
							if (activeScenario.getWorkload_UsageScenario() != null) {
								activeScenario_WorkLoad = (OpenWorkload) activeScenario.getWorkload_UsageScenario();
							} else {
								activeScenario_WorkLoad = UsagemodelFactory.eINSTANCE.createOpenWorkload();
								PCMRandomVariable pcmRandomVariable = CoreFactory.eINSTANCE.createPCMRandomVariable();
								activeScenario_WorkLoad.setInterArrivalTime_OpenWorkload(pcmRandomVariable);
								activeScenario.setWorkload_UsageScenario(activeScenario_WorkLoad);
							}

							ModelEvaluator evaluator = null;
							if (activeTdwfc.getLoadSequence() != null) {
								evaluator = new ModelEvaluator(activeTdwfc.getLoadSequence(), 5, IGeneratorConstants.EVALUATION);
							} else if (activeTdwfc.getMutualLoadFunction() != null) {
								evaluator = new ModelEvaluator(activeTdwfc.getMutualLoadFunction().getLoadSequence(), 5, IGeneratorConstants.EVALUATION);
							}

							double loadTimeSample = 0.0;
							double y = 0.0;
							if (evaluator != null) {
								loadTimeSample = i - activeTdwfc.getWorkStartTime();
								y = evaluator.getArrivalRateAtTime(loadTimeSample);
							}

							if (activeTdwfc.getParameterCharaterizationContainers() != null && activeTdwfc.getParameterCharaterizationContainers().size() > 0) {
								for (AbstractUserAction aua : activeScenario.getScenarioBehaviour_UsageScenario().getActions_ScenarioBehaviour()) {
									if (aua instanceof EntryLevelSystemCall) {
										for (VariableUsage vu : ((EntryLevelSystemCall) aua).getInputParameterUsages_EntryLevelSystemCall()) {
											for (InputParameterCharacterizationContainer iPcc : activeTdwfc.getParameterCharaterizationContainers()) {
												if (vu.getNamedReference__VariableUsage().getReferenceName().equals(iPcc.getVariableUsage().getNamedReference__VariableUsage().getReferenceName())) {
													if (iPcc.getParameterValue() != null) {
														ModelEvaluator paramEvalutor = new ModelEvaluator(iPcc.getParameterValue(), 5, IGeneratorConstants.EVALUATION);
														vu.getVariableCharacterisation_VariableUsage().get(0).getSpecification_VariableCharacterisation()
																.setSpecification(String.valueOf(paramEvalutor.getArrivalRateAtTime(loadTimeSample)));
														break;
													}
												}
											}
										}
									}
								}
							}

							activeScenario_WorkLoad.getInterArrivalTime_OpenWorkload().setSpecification(String.valueOf(y));
							LOGGER.info("At sampling time = " + i + " -" + activeTdwfc.getWork().getEntityName() + "- is active with a load = " + y + " (load sampling time = " + loadTimeSample + ")");
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
					// ModelEvaluator evaluator = new
					// ModelEvaluator(dynamicWorkload, 5,
					// IGeneratorConstants.EVALUATION);

					// for (double i = samplingStep / 2.0; i <
					// evaluator.getDuration(); i += samplingStep) {
					// double y = evaluator.getArrivalRateAtTime(i);
					// arrivalRateList.add(new ArrivalRateTuple(i, y));

					// double innerY = evaluator.getArrivalRateDelta(i, -1,
					// new boolean[1]);
					// innerArrivalRateList.add(new
					// ArrivalRateTuple(i,innerY));
					// }
					// for (ArrivalRateTuple t : arrivalRateList) {
					// int y = (int) t.getArrivalRate();
					// wl.getInterArrivalTime_OpenWorkload().setSpecification(
					// "Exp(" + y + ")");
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

					// }

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

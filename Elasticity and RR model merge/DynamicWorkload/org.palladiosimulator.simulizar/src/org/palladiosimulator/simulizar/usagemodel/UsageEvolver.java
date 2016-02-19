package org.palladiosimulator.simulizar.usagemodel;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredSimulationEntity;

import de.uka.ipd.sdq.pcm.parameter.VariableUsage;
import de.uka.ipd.sdq.pcm.usagemodel.AbstractUserAction;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.OpenWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
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
	private final List<TimeDependentWorkFunctionContainer> activeWorkLoadList;
	private final List<UsageScenario> activeWorkList;

	public UsageEvolver(final SimuLizarRuntimeState runtimeState) {
		super();
		this.runtimeState = runtimeState;
		this.usageModel = runtimeState.getModelAccess().getGlobalPCMModel().getUsageModel();
		this.dynamicWorkload = runtimeState.getModelAccess().getDEModel();
		this.activeWorkLoadList = new ArrayList<TimeDependentWorkFunctionContainer>();
		this.activeWorkList = new ArrayList<UsageScenario>();
	}

	public void start() {
		new PeriodicallyTriggeredSimulationEntity(this.runtimeState.getModel(), 0, 1) {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.palladiosimulator.simulizar.simulationevents.
			 * PeriodicallyTriggeredSimulationEntity #triggerInternal()
			 */
			@Override
			protected void triggerInternal() {
				if (dynamicWorkload != null) {
					LOGGER.debug("Dynamic Workload model has been specified: " + dynamicWorkload.getName());

					double currentSimulationTime = getModel().getSimulationControl().getCurrentSimulationTime();
					LOGGER.debug("Periodic trigger for entity UsageEvolver occurred at simulation time " + getModel().getSimulationControl().getCurrentSimulationTime());

					buildActiveAndInactiveWorkLists(currentSimulationTime);

					setInactiveWorkloads();

					setActiveWorkloads(currentSimulationTime);
				}
			}

			private void buildActiveAndInactiveWorkLists(double currentSimulationTime) {
				double workStartTime = 0.0;
				double workEndTime = 0.0;
				double synchronizedWorkStartTime = 0.0;
				double synchronizedWorkEndTime = 0.0;
				activeWorkLoadList.clear();
				activeWorkList.clear();
				for (TimeDependentWorkFunctionContainer tdwfc : dynamicWorkload.getWorkFunctionContainers()) {
					int usIndex = getUsageScenarioIndex(tdwfc);
					workStartTime = tdwfc.getWorkStartTime();
					workEndTime = tdwfc.getWorkStartTime() + tdwfc.getWorkDuration();
					if (currentSimulationTime >= workStartTime && currentSimulationTime <= workEndTime) {
						activeWorkLoadList.add(tdwfc);
						if (!(activeWorkList.contains(usageModel.getUsageScenario_UsageModel().get(usIndex))))
							activeWorkList.add(usageModel.getUsageScenario_UsageModel().get(usIndex));
					} else {
						if (tdwfc.getTimeSynchronization() != null) {
							synchronizedWorkStartTime = tdwfc.getTimeSynchronization().getWorkStartTime();
							synchronizedWorkEndTime = tdwfc.getTimeSynchronization().getWorkStartTime() + tdwfc.getTimeSynchronization().getWorkDuration();
							if (currentSimulationTime >= synchronizedWorkStartTime && currentSimulationTime <= synchronizedWorkEndTime) {
								activeWorkLoadList.add(tdwfc);
								if (!(activeWorkList.contains(usageModel.getUsageScenario_UsageModel().get(usIndex))))
									activeWorkList.add(usageModel.getUsageScenario_UsageModel().get(usIndex));
							}
						}
					}
				}
			}

			private void setInactiveWorkloads() {
				OpenWorkload inActiveWorkLoad;
				for (UsageScenario inactiveWork : usageModel.getUsageScenario_UsageModel()) {
					if (!(activeWorkList.contains(inactiveWork))) {
						inActiveWorkLoad = (OpenWorkload) inactiveWork.getWorkload_UsageScenario();
						inActiveWorkLoad.getInterArrivalTime_OpenWorkload().setSpecification("Exp(1)");
					}

				}
			}

			private void setActiveWorkloads(double currentSimulationTime) {
				OpenWorkload activeScenario_WorkLoad = null;
				ModelEvaluator evaluator = null;
				for (TimeDependentWorkFunctionContainer activeTdwfc : activeWorkLoadList) {
					int usIndex = getUsageScenarioIndex(activeTdwfc);
					UsageScenario activeScenario = usageModel.getUsageScenario_UsageModel().get(usIndex);

					if (activeScenario.getWorkload_UsageScenario() != null && activeScenario.getWorkload_UsageScenario() instanceof OpenWorkload) {
						activeScenario_WorkLoad = (OpenWorkload) activeScenario.getWorkload_UsageScenario();

						if (activeTdwfc.getLoadSequence() != null) {
							evaluator = new ModelEvaluator(activeTdwfc.getLoadSequence(), 5, IGeneratorConstants.EVALUATION);
						} else {
							if (activeTdwfc.getMutualLoadFunction() != null)
								evaluator = new ModelEvaluator(activeTdwfc.getMutualLoadFunction().getLoadSequence(), 5, IGeneratorConstants.EVALUATION);
						}

						double loadTimeSample = 0.0;
						double loadValue = 1;
						if (evaluator != null) {
							loadTimeSample = currentSimulationTime - activeTdwfc.getWorkStartTime();
							loadValue = evaluator.getArrivalRateAtTime(loadTimeSample);
							LOGGER.debug("load value = " + loadValue);
							if (loadValue == 0.0)
								loadValue = 1;
						}

						// activeScenario =
						// setInputParameterCharacterizationValue(activeTdwfc,
						// activeScenario, loadTimeSample,
						// currentSimulationTime);

						activeScenario_WorkLoad.getInterArrivalTime_OpenWorkload().setSpecification("Exp(" + loadValue + ")");
						LOGGER.debug("At sampling time = " + currentSimulationTime + " -" + activeTdwfc.getWork().getEntityName() + "- is active with a load = " + loadValue
								+ " (load sampling time = " + loadTimeSample + ")");
					}
				}
			}

			private int getUsageScenarioIndex(TimeDependentWorkFunctionContainer tdwfc) {
				int usIndex = -1;
				for (UsageScenario us : usageModel.getUsageScenario_UsageModel()) {
					if (us.getId().equalsIgnoreCase(tdwfc.getWork().getId())) {
						usIndex = usageModel.getUsageScenario_UsageModel().indexOf(us);
					}
				}
				return usIndex;
			}

			private UsageScenario setInputParameterCharacterizationValue(TimeDependentWorkFunctionContainer activeTdwfc, UsageScenario activeScenario, double loadTimeSample,
					double currentSimulationTime) {
				if (activeTdwfc.getParameterCharaterizationContainers() != null && activeTdwfc.getParameterCharaterizationContainers().size() > 0) {
					for (AbstractUserAction aua : activeScenario.getScenarioBehaviour_UsageScenario().getActions_ScenarioBehaviour()) {
						if (aua instanceof EntryLevelSystemCall) {
							for (VariableUsage vu : ((EntryLevelSystemCall) aua).getInputParameterUsages_EntryLevelSystemCall()) {
								for (InputParameterCharacterizationContainer iPcc : activeTdwfc.getParameterCharaterizationContainers()) {
									if (vu.getNamedReference__VariableUsage().getReferenceName().equalsIgnoreCase(iPcc.getVariableUsage().getNamedReference__VariableUsage().getReferenceName())) {
										if (iPcc.getParameterValue() != null) {
											ModelEvaluator paramEvalutor = new ModelEvaluator(iPcc.getParameterValue(), 5, IGeneratorConstants.EVALUATION);
											vu.getVariableCharacterisation_VariableUsage().get(0).getSpecification_VariableCharacterisation()
													.setSpecification("Exp(" + paramEvalutor.getArrivalRateAtTime(loadTimeSample) + ")");
											LOGGER.debug("At sampling time = " + currentSimulationTime + " - IPCC with value = " + String.valueOf(paramEvalutor.getArrivalRateAtTime(loadTimeSample)));

										}
									}
								}
							}
						}
					}
				}
				return activeScenario;
			}

		};
	}
}

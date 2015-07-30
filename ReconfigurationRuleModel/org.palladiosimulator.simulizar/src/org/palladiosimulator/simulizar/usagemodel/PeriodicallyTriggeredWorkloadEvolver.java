package org.palladiosimulator.simulizar.usagemodel;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredSimulationEntity;
import org.scaledl.usageevolution.Usage;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.WorkParameterEvolution;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.generator.ModelEvaluator;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.usagemodel.ClosedWorkload;
import org.palladiosimulator.pcm.usagemodel.OpenWorkload;
import org.palladiosimulator.pcm.usagemodel.Workload;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class PeriodicallyTriggeredWorkloadEvolver extends PeriodicallyTriggeredSimulationEntity {
    private static final Logger LOGGER = Logger.getLogger(PeriodicallyTriggeredWorkloadEvolver.class);

    final long simuTime;
    final long dlimDuration;
    long limboTime = 0;
    SimuLizarRuntimeState rtState;

    public PeriodicallyTriggeredWorkloadEvolver(SimuComModel model, double firstOccurrence, double delay,
            long simuTime, long dlimDuration, SimuLizarRuntimeState rtState) {
        super(model, firstOccurrence, delay);

        this.dlimDuration = dlimDuration;
        this.simuTime = simuTime;

        this.rtState = rtState;

    }

    protected ModelEvaluator getLoadEvaluator() {
        // TODO: consider optimizing this by adding a loadEvaluator instance variable that is
        // lazily initialized by this call. Must first determine whether this has side
        // effects on the simulation.

        // Create the load evaluator by finding the DLIM sequence for the first usage
        // within the usage evolution model.
        UsageEvolution usageEvolution = rtState.getModelAccess().getUsageEvolutionModel();
        if (usageEvolution != null) {
            Usage usage = usageEvolution.getUsages().get(0);
            if (usage != null) {
                Sequence loadEvolutionSequence = usage.getLoadEvolution();
                if (loadEvolutionSequence != null) {
                    return new ModelEvaluator(loadEvolutionSequence);
                }
            }
        }
        return null;
    }

    protected Map<VariableCharacterisation, ModelEvaluator> getWorkEvaluators() {
        // TODO: consider optimizing this by changing workEvaluators to be an instance variable that
        // is lazily initialized by this call. Must first determine whether this has side
        // effects on the simulation.

        // Build the hashmap from work parameters of the first usage in the usage evolution
        // and to their corresponding model evaluators
        Map<VariableCharacterisation, ModelEvaluator> workEvaluators = new HashMap<VariableCharacterisation, ModelEvaluator>();

        UsageEvolution usageEvolution = rtState.getModelAccess().getUsageEvolutionModel();
        if (usageEvolution != null) {
            Usage usage = usageEvolution.getUsages().get(0);

            if (usage != null) {
                for (WorkParameterEvolution workParam : usage.getWorkEvolutions()) {
                    VariableCharacterisation varChar = workParam.getVariableCharacterisation();
                    Sequence paramSequence = workParam.getEvolution();
                    if (varChar == null) {
                        LOGGER.error("Skipping evolution of unspecified work parameter");
                        continue;
                    }
                    if (paramSequence == null) {
                        LOGGER.error("Skipping unspecified evolution for work parameter " + varChar);
                        continue;
                    }

                    // Add parameter and model evaluator for the work parameter
                    workEvaluators.put(varChar, new ModelEvaluator(paramSequence));
                }
            }
        }
        return workEvaluators;
    }

    @Override
    protected void triggerInternal() {
        if (limboTime <= dlimDuration) {
            // First, evolve load if load evaluator exists
            ModelEvaluator loadEvaluator = getLoadEvaluator();

            if (loadEvaluator != null) {
                evolveLoad(loadEvaluator);
            }

            // Then, iterate through work parameters to evolve
            Map<VariableCharacterisation, ModelEvaluator> workEvaluators = getWorkEvaluators();
            for (VariableCharacterisation workParam : workEvaluators.keySet()) {
                evolveWork(workParam, workEvaluators.get(workParam));
            }

            limboTime++;
        }
    }

    protected double getNewRate(ModelEvaluator evaluator) {
        if (limboTime == dlimDuration) {
            // The LIMBO evaluator do not define a value at the total duration
            // time, so get a value close to end of the simulation by requesting
            // the value one millionth of a time unit before the total duration
            return evaluator.getArrivalRateAtTime(limboTime - 0.000001);
        } else {
            return evaluator.getArrivalRateAtTime(limboTime);
        }
    }

    protected void evolveLoad(ModelEvaluator loadEvaluator) {
        double newRate = getNewRate(loadEvaluator);

        // TODO: if we want to support multiple usage instances with using different
        // usage scenarios, we need to go through the usage evolution model instead.
        // However, modifying properties found through the usage scenario referred
        // from the usage evolution model do not currently cause changes that are
        // considered by the SimuLizar. Probably the reference from the usage object
        // (see last line of this comment) is to another copy of the usage model.
        // These issues must be resolved to support multiple usages.
        // Workload wl = usage.getScenario().getWorkload_UsageScenario();
        // Workload wl =
        // usageModel.getUsageScenario_UsageModel().get(0).getWorkload_UsageScenario();
        Workload wl = rtState.getModelAccess().getUsageEvolutionModel().getUsages().get(0).getScenario()
                .getWorkload_UsageScenario();
        if (wl != null) {
            if (wl instanceof OpenWorkload) {
                PCMRandomVariable openwl = ((OpenWorkload) wl).getInterArrivalTime_OpenWorkload();
                if (newRate != 0) {
                    // Using inverse value to convert from arrival rate to inter arrival
                    // time
                    newRate = 1 / newRate;
                } else {
                    // If the arrival rate is 0, the inter arrival time should be
                    // infinite. We use the max integer value as an approximation
                    newRate = Integer.MAX_VALUE;
                }

                String newRateStr = Double.toString(newRate);
                if (newRateStr.equals(openwl.getSpecification())) {
                    LOGGER.info("Inter arrival time is still: " + newRateStr);
                } else {
                    LOGGER.info("Changing inter arrival time from: " + openwl.getSpecification() + " to :" + newRateStr);
                    openwl.setSpecification(newRateStr);
                }
            } else if (wl instanceof ClosedWorkload) {
                int newRateInt = (int) Math.round(newRate);
                int oldRate = ((ClosedWorkload) wl).getPopulation();
                if (newRateInt == oldRate) {
                    LOGGER.info("Closed workload population is still: " + newRateInt);
                } else {
                    LOGGER.info("Changing closed workload population from: " + oldRate + " to " + newRateInt);
                    ((ClosedWorkload) wl).setPopulation(newRateInt);
                }
            }
        }
    }

    protected void evolveWork(VariableCharacterisation workParameter, ModelEvaluator evaluator) {
        if (evaluator == null)
            return;

        // Support only long values for now
        long newRate = Math.round(getNewRate(evaluator));
        String newRateStr = Long.toString(newRate);

        LOGGER.info("Changing work from "
                + workParameter.getSpecification_VariableCharacterisation().getSpecification() + " to " + newRateStr);

        workParameter.getSpecification_VariableCharacterisation().setSpecification(newRateStr);
    }
}

package org.palladiosimulator.simulizar.usagemodel;

import java.util.Map;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredSimulationEntity;

import tools.descartes.dlim.generator.ModelEvaluator;
import de.uka.ipd.sdq.pcm.core.PCMRandomVariable;
import de.uka.ipd.sdq.pcm.parameter.VariableCharacterisation;
import de.uka.ipd.sdq.pcm.usagemodel.ClosedWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.OpenWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.Workload;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class PeriodicallyTriggeredWorkloadEvolver extends PeriodicallyTriggeredSimulationEntity {
    private static final Logger LOGGER = Logger.getLogger(PeriodicallyTriggeredWorkloadEvolver.class);

    final long simuTime;
    final long dlimDuration;
    long limboTime = 0;
    private final ModelEvaluator loadEvaluator;
    private final Map<VariableCharacterisation, ModelEvaluator> workEvaluators;
    private final UsageModel usageModel;

    public PeriodicallyTriggeredWorkloadEvolver(SimuComModel model, double firstOccurrence, double delay,
            long simuTime, long dlimDuration, ModelEvaluator loadEvaluator,
            Map<VariableCharacterisation, ModelEvaluator> workEvaluators, UsageModel usageModel) {
        super(model, firstOccurrence, delay);

        this.dlimDuration = dlimDuration;
        this.simuTime = simuTime;
        this.loadEvaluator = loadEvaluator;
        this.workEvaluators = workEvaluators;
        this.usageModel = usageModel;
    }

    @Override
    protected void triggerInternal() {

        if (limboTime <= dlimDuration) {
            // First, evolve load if load evaluator exists
            if (loadEvaluator != null) {
                evolveLoad();
            }

            // Then, iterate through work parameters to evolve
            for (VariableCharacterisation workParam : workEvaluators.keySet()) {
                evolveWork(workParam);
            }

            limboTime++;
        }
    }

    protected double getNewRate(ModelEvaluator evaluator) {
        if (limboTime == dlimDuration) {
            // The LIMBO evaluator do not define a value at the total duration
            // time, so get a value close to end of the simulation by requesting
            // the value one millionth of a time unit before the total duration
            return loadEvaluator.getArrivalRateAtTime(limboTime - 0.000001);
        } else {
            return loadEvaluator.getArrivalRateAtTime(limboTime);
        }
    }

    protected void evolveLoad() {
        double newRate = getNewRate(loadEvaluator);

        // TODO: if we want to support multiple usage instances with using different
        // usage scenarios, we need to go through the usage evolution model instead.
        // However, modifying properties found through the usage scenario referred
        // from the usage evolution model do not currently cause changes that are
        // considered by the SimuLizar. Probably the reference from the usage object
        // (see last line of this comment) is to another copy of the usage model.
        // These issues must be resolved to support multiple usages.
        // Workload wl = usage.getScenario().getWorkload_UsageScenario();
        Workload wl = usageModel.getUsageScenario_UsageModel().get(0).getWorkload_UsageScenario();
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

    protected void evolveWork(VariableCharacterisation workParameter) {
        ModelEvaluator evaluator = workEvaluators.get(workParameter);
        if (evaluator == null)
            return;

        double newRate = getNewRate(evaluator);
        String newRateStr = Double.toString(newRate);

        // Simplest approach would be to just use the workParameter directly, but does this refer to
        // the right object?
        workParameter.getSpecification_VariableCharacterisation().setSpecification(newRateStr);

        /*
         * // Locate parameter to evolve for (AbstractUserAction act :
         * usageModel.getUsageScenario_UsageModel().get(0)
         * .getScenarioBehaviour_UsageScenario().getActions_ScenarioBehaviour()) {
         * 
         * if (act instanceof EntryLevelSystemCall) { EntryLevelSystemCall actCall =
         * (EntryLevelSystemCall) act; VariableUsage varUsage =
         * actCall.getInputParameterUsages_EntryLevelSystemCall().get(0); VariableCharacterisation
         * varChar = varUsage.getVariableCharacterisation_VariableUsage().get(0);
         * 
         * varChar.getSpecification_VariableCharacterisation().setSpecification(newRateStr); //
         * varUsage.getVariableCharacterisation_VariableUsage(). }
         * 
         * }
         * 
         * usageModel.getUsageScenario_UsageModel();
         */
    }
}

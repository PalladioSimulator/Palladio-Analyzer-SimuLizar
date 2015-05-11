package org.palladiosimulator.simulizar.usagemodel;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredSimulationEntity;
import org.scaledl.usageevolution.Usage;
import org.scaledl.usageevolution.UsageEvolution;

import tools.descartes.dlim.Sequence;
import tools.descartes.dlim.generator.ModelEvaluator;
import de.uka.ipd.sdq.pcm.core.PCMRandomVariable;
import de.uka.ipd.sdq.pcm.usagemodel.ClosedWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.OpenWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;
import de.uka.ipd.sdq.pcm.usagemodel.Workload;

public class UsageEvolver {

    private final SimuLizarRuntimeState runtimeState;
    private final UsageModel usageModel;
    private final UsageEvolution usageEvolution;
    private final Usage usage;
    private final Sequence sequence;
    private ModelEvaluator evaluator = null;
    private static final Logger LOGGER = Logger.getLogger(UsageEvolver.class);

    public UsageEvolver(final SimuLizarRuntimeState runtimeState) {
        super();
        this.runtimeState = runtimeState;
        this.usageModel = runtimeState.getModelAccess().getGlobalPCMModel().getUsageModel();
        this.usageEvolution = runtimeState.getModelAccess().getUsageEvolutionModel();

        if (usageEvolution != null) {
            LOGGER.info("Usages: " + usageEvolution.getUsages().size());
            usage = usageEvolution.getUsages().get(0);
            if (usage != null) {
                sequence = usage.getLoadEvolution();
                if (sequence != null) {
                    LOGGER.info("LIMBO duration: " + sequence.getFinalDuration());
                    evaluator = new ModelEvaluator(sequence);
                }
            } else {
                sequence = null;
            }

        } else {
            usage = null;
            sequence = null;
        }

        LOGGER.info("SimuTime: " + runtimeState.getModel().getConfiguration().getSimuTime());
    }

    public void start() {
        final long simuTime = runtimeState.getModel().getConfiguration().getSimuTime();
        final long limboDuration;
        long timePerStep = 1000;
        if (sequence != null) {
            LOGGER.info("LIMBO First Iteration end: " + sequence.getFirstIterationEnd());
            // Divide the simulation time available on the duration in the LIMBO
            // model, but add one to include simulation time 0 and max.
            limboDuration = (long) sequence.getFinalDuration();
            timePerStep = simuTime / (limboDuration + 1);
        } else {
            limboDuration = 24;
        }
        new PeriodicallyTriggeredSimulationEntity(this.runtimeState.getModel(), 0, timePerStep) {
            double step = 0.5;
            long limboTime = 0;

            /*
             * (non-Javadoc)
             * 
             * @see org.palladiosimulator.simulizar.simulationevents.
             * PeriodicallyTriggeredSimulationEntity #triggerInternal()
             */
            @Override
            protected void triggerInternal() {
                LOGGER.info("LIMBO time: " + limboTime);

                if ((evaluator != null) && (limboTime <= limboDuration)) {
                    double newRate;
                    if (limboTime == limboDuration) {
                        // The LIMBO evaluator do not define a value at the total duration
                        // time, so get a value close to end of the simulation by requesting
                        // the value one millionth of a time unit before the total duration
                        newRate = evaluator.getArrivalRateAtTime(limboTime - 0.000001);
                    } else {
                        newRate = evaluator.getArrivalRateAtTime(limboTime);
                    }

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
                                LOGGER.info("Changing inter arrival time from: " + openwl.getSpecification() + " to :"
                                        + newRateStr);
                                openwl.setSpecification(newRateStr);
                            }
                        } else if (wl instanceof ClosedWorkload) {
                            int newRateInt = (int) Math.round(newRate);
                            int oldRate = ((ClosedWorkload) wl).getPopulation();
                            if (newRateInt == oldRate) {
                                LOGGER.info("Closed workload population is still: " + newRateInt);
                            } else {
                                LOGGER.info("Changing closed workload population from: " + oldRate + " to "
                                        + newRateInt);
                                ((ClosedWorkload) wl).setPopulation(newRateInt);
                            }
                        }
                    }

                    limboTime++;
                }

                // Initial example code for how to update specification of an open workload
                // OpenWorkload wl = (OpenWorkload)
                // usageModel.getUsageScenario_UsageModel().get(0).getWorkload_UsageScenario();
                // wl.getInterArrivalTime_OpenWorkload().setSpecification("Exp("
                // + (0.5d + limboTime++ * step) + ")");
            }

        };
    }
}

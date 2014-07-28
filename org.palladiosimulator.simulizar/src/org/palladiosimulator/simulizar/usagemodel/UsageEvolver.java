package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.simulizar.runtimestate.SimuComRuntimeState;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredSimulationEntity;

import de.uka.ipd.sdq.pcm.usagemodel.OpenWorkload;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;

public class UsageEvolver {

    private final SimuComRuntimeState runtimeState;
    private final UsageModel usageModel;

    public UsageEvolver(final SimuComRuntimeState runtimeState, final UsageModel usageModel) {
        super();
        this.runtimeState = runtimeState;
        this.usageModel = usageModel;
    }

    public void start() {
        new PeriodicallyTriggeredSimulationEntity(this.runtimeState.getModel(), 0, 1000) {
            double step = 0.5;
            long count = 0;

            /*
             * (non-Javadoc)
             * 
             * @see
             * org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredSimulationEntity
             * #triggerInternal()
             */
            @Override
            protected void triggerInternal() {
                if (false && count <= 4) {
                    OpenWorkload wl = (OpenWorkload) usageModel.getUsageScenario_UsageModel().get(0)
                            .getWorkload_UsageScenario();
                    wl.getInterArrivalTime_OpenWorkload().setSpecification(
                            "Exp(" + (0.5d + count++ * step) + ")");
                }
            }

        };
    }

}

package org.palladiosimulator.simulizar.runtimestate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostModel {

    private final Map<String, List<CostTuple>> container2Cost;

    public CostModel() {
        super();
        this.container2Cost = new HashMap<String, List<CostTuple>>();
    }

    public void addCostTuple(final String containerID, final Double timestamp, final Double cost) {
        this.addCostTuple(containerID, new CostTuple(timestamp, cost));
    }

    public void addCostTuple(final String containerID, final CostTuple costTuple) {
        if (this.container2Cost.get(containerID) == null) {
            final List<CostTuple> containerList = new ArrayList<CostTuple>();
            containerList.add(costTuple);
            this.container2Cost.put(containerID, containerList);
        } else {
            this.container2Cost.get(containerID).add(costTuple);
        }
    }

    public List<CostTuple> getCostTuples(final String containerID) {
        if (this.container2Cost.get(containerID) == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(this.container2Cost.get(containerID));
    }

    public List<CostTuple> getCostTuplesForIntervalForContainer(final String containerID, final Double startTime,
            final Double endTime) {
        final List<CostTuple> costTuples = this.getCostTuples(containerID);
        final List<CostTuple> costTuplesInInterval = new ArrayList<CostTuple>();

        for (final CostTuple costTuple : costTuples) {
            if (costTuple.getPointInTime() >= startTime && costTuple.getPointInTime() < endTime) {
                costTuplesInInterval.add(costTuple);
            }
        }

        return Collections.unmodifiableList(costTuplesInInterval);
    }

    public List<CostTuple> getCostTuplesForInterval(final Double startTime, final Double endTime) {
        final List<CostTuple> costTuplesInInterval = new ArrayList<CostTuple>();

        for (final String id : this.container2Cost.keySet()) {
            costTuplesInInterval.addAll(this.getCostTuplesForIntervalForContainer(id, startTime, endTime));
        }

        return Collections.unmodifiableList(costTuplesInInterval);
    }
}

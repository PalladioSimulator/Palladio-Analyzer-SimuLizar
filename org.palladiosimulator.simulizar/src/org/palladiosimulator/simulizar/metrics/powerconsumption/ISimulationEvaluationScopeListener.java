package org.palladiosimulator.simulizar.metrics.powerconsumption;


public interface ISimulationEvaluationScopeListener {
    public void newElementAvailable();
    
    public void preUnregister();
}

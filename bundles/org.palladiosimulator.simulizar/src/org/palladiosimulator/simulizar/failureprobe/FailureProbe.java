package org.palladiosimulator.simulizar.failureprobe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.Unit;

import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.measurementframework.measure.IdentifierMeasure;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.DataType;
import org.palladiosimulator.metricspec.Identifier;
import org.palladiosimulator.metricspec.Scale;
import org.palladiosimulator.metricspec.util.builder.TextualBaseMetricDescriptionBuilder;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.probeframework.probes.BasicEventProbe;
import org.palladiosimulator.reliability.FailureStatistics;
import org.palladiosimulator.reliability.MarkovFailureType;
import org.palladiosimulator.simulizar.interpreter.FailureStackFrame;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.listener.AbstractInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.FailureOccurredEvent;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.ModelElementPassedEvent;

public class FailureProbe extends BasicEventProbe<AbstractObservable<IInterpreterListener>, Identifier, Dimensionless> {

	
	/**
	 * Used for translating MarkovFailureTypes
	 */
	private FailureStatistics failureStats;
	
	private UsageScenario targetScenario;
	
	private static BaseMetricDescription metric = null;
	
	public FailureProbe(UsageScenario targetScenario, final AbstractObservable<IInterpreterListener> observable, InterpreterDefaultContext failureStack,
			FailureStatistics failureStats) {
        super(observable, getOrCreateMetricDescription(FailureStatistics.getSimFailureTypes(), failureStats.getExecutionResultId(null)));
        this.failureStats = failureStats;
        this.targetScenario = targetScenario;
	}

	/**
	 * Copied From TakeExecutionResultProbe
	 */
    public static BaseMetricDescription getOrCreateMetricDescription(
            final Map<MarkovFailureType, Identifier> simFailureTypes, final Identifier successIdentifier) {
    	if(metric == null) {
    		metric =  TextualBaseMetricDescriptionBuilder.newTextualBaseMetricDescriptionBuilder().name("Execution Result")
                    .textualDescription("Enumeration of all failure types which might happen in a reliability simulation")
                    .scale(Scale.NOMINAL).dataType(DataType.QUANTITATIVE).identifiers(simFailureTypes.values())
                    .identifiers(successIdentifier).build();
    	}
        return metric;
    }

    @Override
    protected void registerListener() {
    	//TODO: UsageScenario instanzen untershceiden sich???
    	final Map<String, FailureStackFrame<?>> failedExecutionsCache = new ConcurrentHashMap<>();
       this.eventSource.addObserver(new AbstractInterpreterListener() {
        	
        	 @Override
    		public void usageScenarioFailure(FailureOccurredEvent<UsageScenario> event) {
        		 UsageScenario scenario = event.getModelElement();
        		 if(scenario.getId().equals(targetScenario.getId())) {
     				failedExecutionsCache.put(scenario.getId(), event.getFailure());        			 
        		 }
    		}
        	
        	@Override
        	public void endUsageScenarioInterpretation(ModelElementPassedEvent<UsageScenario> ev) {
         		UsageScenario scenario = ev.getModelElement();
        		if(scenario.getId().equals(targetScenario.getId())) {
	       			 //null if no failure occurred
	       			FailureStackFrame<?> failure = failedExecutionsCache.remove(scenario.getId());
	         		
	             	final Identifier resultFailureIdentifier;
	         		if(failure != null) {
	         			//Failure
	         			MarkovFailureType mt = failure.translateToMarkovFailureType(failureStats);
	 					resultFailureIdentifier = failureStats.getExecutionResultId(mt);
	         		} else {
	         			resultFailureIdentifier = failureStats.getExecutionResultId(null);
	         		}
	                 final Measure<Identifier, Dimensionless> result = IdentifierMeasure.valueOf(resultFailureIdentifier, Unit.ONE);
	                 FailureProbe.this.notify(result);
	       		 }
	       		 
        		
        	}
		});
    }


}

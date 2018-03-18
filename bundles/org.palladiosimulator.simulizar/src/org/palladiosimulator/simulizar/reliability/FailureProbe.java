package org.palladiosimulator.simulizar.reliability;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.unit.Unit;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.measurementframework.measure.IdentifierMeasure;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.DataType;
import org.palladiosimulator.metricspec.Identifier;
import org.palladiosimulator.metricspec.Scale;
import org.palladiosimulator.metricspec.util.builder.TextualBaseMetricDescriptionBuilder;
import org.palladiosimulator.pcm.reliability.FailureType;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.probeframework.probes.BasicEventProbe;
import org.palladiosimulator.reliability.FailureStatistics;
import org.palladiosimulator.reliability.MarkovFailureType;
import org.palladiosimulator.simulizar.reliability.listener.DelegatingEventListener;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.interpreter.listener.FailureHandledEvent;
import org.palladiosimulator.simulizar.interpreter.listener.FailureOccurredEvent;
import org.palladiosimulator.simulizar.interpreter.listener.IEventListener;
import org.palladiosimulator.simulizar.interpreter.listener.ModelElementPassedEvent;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

public class FailureProbe extends BasicEventProbe<AbstractObservable<IEventListener<?>>, Identifier, Dimensionless> {

	
	/**
	 * Used for translating MarkovFailureTypes
	 */
	private FailureStatistics failureStats;
	
	private String targetElementId;
	
	private static BaseMetricDescription metric = null;
	
	public FailureProbe(de.uka.ipd.sdq.identifier.Identifier targetElement, final AbstractObservable<IEventListener<?>> eventProvider,
			FailureStatistics failureStats) {
		
        super(eventProvider, getOrCreateMetricDescription(FailureStatistics.getSimFailureTypes(), 
        		failureStats.getExecutionResultId(null)));
        this.failureStats = failureStats;
        this.targetElementId = targetElement.getId();
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

    @SuppressWarnings("unchecked")
	@Override
    protected void registerListener() {
    	final Map<SimuComSimProcess, FailureStackFrame<?>> failureState = new ConcurrentHashMap<>();
    	
    	this.eventSource.addObserver(new DelegatingEventListener<FailureOccurredEvent<?,?>>(
			(Class<? extends FailureOccurredEvent<?, ?>>) FailureOccurredEvent.class,
			(event) -> {
				failureState.put(event.getThread(), event.getFailure());						
			}) 
		);
    	this.eventSource.addObserver(new DelegatingEventListener<FailureHandledEvent<FailureType>>(
			(Class<? extends FailureHandledEvent<FailureType>>) FailureHandledEvent.class,
			(event) -> {
				failureState.remove(event.getThread());						
			}) 
		);

    	this.eventSource.addObserver(new DelegatingEventListener<ModelElementPassedEvent<?>>(
    			(Class<? extends ModelElementPassedEvent<?>>) ModelElementPassedEvent.class,
    			(event) -> {
    				if(event.getEventType() == EventType.END) {
    					EObject modelElement = event.getModelElement();
    					
    					if(modelElement instanceof de.uka.ipd.sdq.identifier.Identifier) {
    						String id = ((de.uka.ipd.sdq.identifier.Identifier)modelElement).getId();
    						if(id.equals(targetElementId)) {
    							
    							FailureStackFrame<?> failure = failureState.get(event.getThread());
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
    					
    					if(modelElement instanceof UsageScenario) {
    						//cleanup
    						failureState.remove(event.getThread());									
    					}
    				}
    			})
		);
    }


}

package org.palladiosimulator.simulizar.failureprobe;

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
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.probeframework.probes.BasicEventProbe;
import org.palladiosimulator.reliability.FailureStatistics;
import org.palladiosimulator.reliability.MarkovFailureType;
import org.palladiosimulator.simulizar.interpreter.FailureStackFrame;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.interpreter.listener.FailureEvent;
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

    @Override
    protected void registerListener() {
    	final Map<SimuComSimProcess, FailureStackFrame<?>> failureState = new ConcurrentHashMap<>();
    	
    	//Failure Occurence and Handling listener
    	this.eventSource.addObserver(new IEventListener<FailureEvent<?>>() {

			@Override
			public void eventOccurred(FailureEvent<?> event) {
				FailureStackFrame<?> failure = event.getFailure();
				SimuComSimProcess thread = event.getThread();
				if(event instanceof FailureOccurredEvent<?, ?>) {
					failureState.put(thread, failure);
				} else if(event instanceof FailureHandledEvent<?>) {
					failureState.remove(thread);					
				} else {
					throw new RuntimeException("unexpected event type!");
				}
			}
			
			@SuppressWarnings("unchecked")
			public Class<FailureEvent<?>> getEventType() {
				// TODO Auto-generated method stub
				return (Class<FailureEvent<?>>)(Class<?>)FailureEvent.class;
			}
		});
    	
    	//ModelElementPassed listener
    	this.eventSource.addObserver(new IEventListener<ModelElementPassedEvent<?>>() {

			@Override
			public void eventOccurred(ModelElementPassedEvent<? extends EObject> event) {
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
				
			}
			
			@SuppressWarnings("unchecked")
			public Class<ModelElementPassedEvent<? extends EObject>> getEventType() {
				// TODO Auto-generated method stub
				return (Class<ModelElementPassedEvent<? extends EObject>>)(Class<?>)ModelElementPassedEvent.class;
			}
		});
    }


}

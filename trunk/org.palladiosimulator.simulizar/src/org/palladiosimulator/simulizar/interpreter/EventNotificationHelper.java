/**
 *
 */
package org.palladiosimulator.simulizar.interpreter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.util.RepositorySwitch;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.util.SeffSwitch;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.util.UsagemodelSwitch;
import org.palladiosimulator.simulizar.interpreter.listener.AssemblyProvidedOperationPassedEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.ModelElementPassedEvent;
import org.palladiosimulator.simulizar.interpreter.listener.RDSEFFElementPassedEvent;

/**
 * @author snowball, Sebastian Krach
 *
 */
public class EventNotificationHelper extends AbstractObservable<IInterpreterListener> {
    
    
    private static BinaryOperator<Consumer<ModelElementPassedEvent<? extends EObject>>> BEGIN_END_SWITCH = (beginFunc, endFunc) -> 
    	((ModelElementPassedEvent<? extends EObject> event) -> {
    	    if (event.getEventType() == EventType.BEGIN) 
    	    	beginFunc.accept(event);
    	    else
    	    	endFunc.accept(event);
    	    
    	});
    
    @SuppressWarnings("unchecked")
    private UsagemodelSwitch<Optional<Consumer<ModelElementPassedEvent<? extends EObject>>>> USAGE_MODEL_NOTIFICATOR_SELECTOR = new UsagemodelSwitch<Optional<Consumer<ModelElementPassedEvent<? extends EObject>>>>() {
		public Optional<Consumer<ModelElementPassedEvent<? extends EObject>>> caseEntryLevelSystemCall(EntryLevelSystemCall object) {
			return Optional.of(
		    	BEGIN_END_SWITCH.apply(
	    			(ev -> getEventDispatcher().beginEntryLevelSystemCallInterpretation((ModelElementPassedEvent<EntryLevelSystemCall>) ev)),
	    			(ev -> getEventDispatcher().endEntryLevelSystemCallInterpretation((ModelElementPassedEvent<EntryLevelSystemCall>) ev))
				)
		    );
		};
		
		public Optional<Consumer<ModelElementPassedEvent<? extends EObject>>> caseUsageScenario(UsageScenario object) {
		    return Optional.of(
				BEGIN_END_SWITCH.apply(
	    			((ModelElementPassedEvent<? extends EObject> ev) -> getEventDispatcher().beginUsageScenarioInterpretation((ModelElementPassedEvent<UsageScenario>) ev)),
	    			((ModelElementPassedEvent<? extends EObject> ev) -> getEventDispatcher().endUsageScenarioInterpretation((ModelElementPassedEvent<UsageScenario>) ev))
	    		)
		    );
			    		
		};
		
		public Optional<Consumer<ModelElementPassedEvent<? extends EObject>>> defaultCase(EObject object) {
		    return Optional.empty();
		};
    };
    
    @SuppressWarnings("unchecked")
    private RepositorySwitch<Optional<Consumer<ModelElementPassedEvent<? extends EObject>>>> REPOSITORY_NOTIFICATOR_SELECTOR = new RepositorySwitch<Optional<Consumer<ModelElementPassedEvent<? extends EObject>>>>() {
    	public Optional<Consumer<ModelElementPassedEvent<? extends EObject>>> caseOperationSignature(OperationSignature object) {
    		return Optional.of(    
	    		BEGIN_END_SWITCH.apply(
    				((ModelElementPassedEvent<? extends EObject> ev) -> getEventDispatcher().beginSystemOperationCallInterpretation((ModelElementPassedEvent<OperationSignature>) ev)),
    				((ModelElementPassedEvent<? extends EObject> ev) -> getEventDispatcher().endSystemOperationCallInterpretation((ModelElementPassedEvent<OperationSignature>) ev))
			    )
    		);		
		};
		
		public Optional<Consumer<ModelElementPassedEvent<? extends EObject>>> caseOperationProvidedRole(OperationProvidedRole object) {
    		return Optional.of(    
	    		BEGIN_END_SWITCH.apply(
    				((ModelElementPassedEvent<? extends EObject> ev) -> getEventDispatcher().beginAssemblyProvidedOperationCallInterpretation((AssemblyProvidedOperationPassedEvent<OperationProvidedRole, OperationSignature>) ev)),
    				((ModelElementPassedEvent<? extends EObject> ev) -> getEventDispatcher().endAssemblyProvidedOperationCallInterpretation((AssemblyProvidedOperationPassedEvent<OperationProvidedRole, OperationSignature>) ev))
			    )
    		);	
		};
		
		public Optional<Consumer<ModelElementPassedEvent<? extends EObject>>> defaultCase(EObject object) {
		    return Optional.empty();
		};
    };
    
    @SuppressWarnings("unchecked")
    private SeffSwitch<Optional<Consumer<ModelElementPassedEvent<? extends EObject>>>> SEFF_NOTIFICATOR_SELECTOR = new SeffSwitch<Optional<Consumer<ModelElementPassedEvent<? extends EObject>>>> () {
    	public Optional<Consumer<ModelElementPassedEvent<? extends EObject>>> caseExternalCallAction(ExternalCallAction object) {
    		return Optional.of(
		    		BEGIN_END_SWITCH.apply(
	    				((ModelElementPassedEvent<? extends EObject> ev) -> getEventDispatcher().beginExternalCallInterpretation((RDSEFFElementPassedEvent<ExternalCallAction>) ev)),
	    				((ModelElementPassedEvent<? extends EObject> ev) -> getEventDispatcher().endExternalCallInterpretation((RDSEFFElementPassedEvent<ExternalCallAction>) ev))
				    )
        		);		
    		};
    	
    	public Optional<Consumer<ModelElementPassedEvent<? extends EObject>>> defaultCase(EObject object) {
		    return Optional.empty();
		};
    };
    
    private Consumer<ModelElementPassedEvent<? extends EObject>> UNKNOWN_ELEMENT_NOTIFICATOR_SELECTOR =
		BEGIN_END_SWITCH.apply(
			((ModelElementPassedEvent<? extends EObject> ev) -> getEventDispatcher().beginUnknownElementInterpretation(ev)),
			((ModelElementPassedEvent<? extends EObject> ev) -> getEventDispatcher().endUnknownElementInterpretation(ev))
	    );
    

    private final List<Function<EObject, Optional<Consumer<ModelElementPassedEvent<? extends EObject>>>>> NOTIFICATOR_SELECTOR_SWITCHES = Arrays.asList(
		USAGE_MODEL_NOTIFICATOR_SELECTOR::doSwitch,
		REPOSITORY_NOTIFICATOR_SELECTOR::doSwitch,
		SEFF_NOTIFICATOR_SELECTOR::doSwitch);
    
    public <T extends EObject> void firePassedEvent(final ModelElementPassedEvent<T> event) {
		NOTIFICATOR_SELECTOR_SWITCHES.stream()
			.map(sw -> sw.apply(event.getModelElement()))
			.filter(Optional::isPresent).map(Optional::get).findFirst()
			.orElse(UNKNOWN_ELEMENT_NOTIFICATOR_SELECTOR)
			.accept(event);
    }
    
    public void removeAllListener() {
        this.removeAllObserver();
    }
}

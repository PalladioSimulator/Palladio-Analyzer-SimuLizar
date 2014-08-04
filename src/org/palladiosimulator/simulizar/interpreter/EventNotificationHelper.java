/**
 * 
 */
package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.ModelElementPassedEvent;
import org.palladiosimulator.simulizar.interpreter.listener.RDSEFFElementPassedEvent;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationEvent;

import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.seff.SeffPackage;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelPackage;

/**
 * @author snowball
 * 
 */
public class EventNotificationHelper extends AbstractObservable<IInterpreterListener> {

    public EventNotificationHelper() {
        super();
    }

    @SuppressWarnings("unchecked")
    <T extends EObject> void firePassedEvent(final ModelElementPassedEvent<T> event) {
        for (final IInterpreterListener singleListener : this.getObservers()) {
            switch (event.getModelElement().eClass().getClassifierID()) {
            case UsagemodelPackage.USAGE_SCENARIO:
                if (event.getEventType() == EventType.BEGIN) {
                    singleListener.beginUsageScenarioInterpretation((ModelElementPassedEvent<UsageScenario>) event);
                } else {
                    singleListener.endUsageScenarioInterpretation((ModelElementPassedEvent<UsageScenario>) event);
                }
                break;
            case UsagemodelPackage.ENTRY_LEVEL_SYSTEM_CALL:
                if (event.getEventType() == EventType.BEGIN) {
                    singleListener
                            .beginEntryLevelSystemCallInterpretation((ModelElementPassedEvent<EntryLevelSystemCall>) event);
                } else {
                    singleListener
                            .endEntryLevelSystemCallInterpretation((ModelElementPassedEvent<EntryLevelSystemCall>) event);
                }
                break;
            case SeffPackage.EXTERNAL_CALL_ACTION:
                if (event.getEventType() == EventType.BEGIN) {
                    singleListener
                            .beginExternalCallInterpretation((RDSEFFElementPassedEvent<ExternalCallAction>) event);
                } else {
                    singleListener.endExternalCallInterpretation((RDSEFFElementPassedEvent<ExternalCallAction>) event);
                }
                break;
            default:
                if (event.getEventType() == EventType.BEGIN) {
                    singleListener.beginUnknownElementInterpretation(event);
                } else {
                    singleListener.endUnknownElementInterpretation(event);
                }
                break;
            }
        }
    }

    public void fireReconfigurationEvent(final ReconfigurationEvent event) {
        for (final IInterpreterListener singleListener : this.getObservers()) {
            if (event.getEventType() == EventType.RECONFIGURATION) {
                singleListener.reconfigurationInterpretation(event);
            } else {
                throw new PCMModelInterpreterException("Tried to fire unknown event");
            }
        }
    }

    public void removeAllListener() {
        this.removeAllObserver();
    }
}

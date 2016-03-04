/**
 *
 */
package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.seff.SeffPackage;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.ModelElementPassedEvent;
import org.palladiosimulator.simulizar.interpreter.listener.RDSEFFElementPassedEvent;

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
                    singleListener.beginEntryLevelSystemCallInterpretation(
                            (ModelElementPassedEvent<EntryLevelSystemCall>) event);
                } else {
                    singleListener.endEntryLevelSystemCallInterpretation(
                            (ModelElementPassedEvent<EntryLevelSystemCall>) event);
                }
                break;
            case RepositoryPackage.OPERATION_SIGNATURE:
                if (event.getEventType() == EventType.BEGIN) {
                    singleListener.beginSystemOperationCallInterpretation(
                            (ModelElementPassedEvent<OperationSignature>) event);
                } else {
                    singleListener
                            .endSystemOperationCallInterpretation((ModelElementPassedEvent<OperationSignature>) event);
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

    public void removeAllListener() {
        this.removeAllObserver();
    }
}

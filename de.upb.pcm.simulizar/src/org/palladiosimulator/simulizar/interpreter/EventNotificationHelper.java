/**
 * 
 */
package org.palladiosimulator.simulizar.interpreter;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.interpreter.listener.IInterpreterListener;
import org.palladiosimulator.simulizar.interpreter.listener.ModelElementPassedEvent;
import org.palladiosimulator.simulizar.interpreter.listener.RDSEFFElementPassedEvent;

import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.seff.SeffPackage;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import de.uka.ipd.sdq.pcm.usagemodel.UsagemodelPackage;

/**
 * @author snowball
 * 
 */
public class EventNotificationHelper {

    final Collection<IInterpreterListener> listener;

    public EventNotificationHelper() {
        super();
        this.listener = new ArrayList<IInterpreterListener>();
    }

    public void addListener(final IInterpreterListener singleListener) {
        this.listener.add(singleListener);
    }

    public void removeListener(final IInterpreterListener singleListener) {
        this.listener.remove(singleListener);
    }

    @SuppressWarnings("unchecked")
    <T extends EObject> void firePassedEvent(final ModelElementPassedEvent<T> event) {
        for (final IInterpreterListener singleListener : this.listener) {
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
                throw new PCMModelInterpreterException("Tried to fire unknown event");
            }
        }
    }

    public void removeAllListener() {
        this.listener.clear();
    }
}

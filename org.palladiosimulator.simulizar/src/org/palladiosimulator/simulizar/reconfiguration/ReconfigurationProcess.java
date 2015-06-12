package org.palladiosimulator.simulizar.reconfiguration;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationEvent;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class ReconfigurationProcess extends SimuComSimProcess {

    private final EObject monitoredElement;
    private final IReconfigurationListener reconfigurationDispatcher;
    private final List<IReconfigurator> reconfigurators;

    protected ReconfigurationProcess(SimuComModel model, String name, List<IReconfigurator> reconfigurators,
            EObject monitoredElement, IReconfigurationListener reconfigurationDispatcher) {
        super(model, name);
        this.reconfigurators = reconfigurators;
        this.monitoredElement = monitoredElement;
        this.reconfigurationDispatcher = reconfigurationDispatcher;
    }

    @Override
    protected void internalLifeCycle() {
        for (IReconfigurator reconfigurator : reconfigurators) {
            double startReconfigurationTime = this.getModel().getSimulationControl().getCurrentSimulationTime();
            reconfigurationDispatcher.beginReconfigurationEvent(new ReconfigurationEvent(EventType.BEGIN,
                    startReconfigurationTime));
            if (reconfigurator.checkAndExecute(monitoredElement)) {
                LOGGER.debug("Successfully executed reconfiguration.");
                double endReconfigurationTime = this.getModel().getSimulationControl().getCurrentSimulationTime();
                reconfigurationDispatcher.endReconfigurationEvent(new ReconfigurationEvent(EventType.END,
                        endReconfigurationTime));
            }
        }
    }
}

package de.upb.pcm.simulizar.sdinterpreter;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import de.upb.pcm.simulizar.access.IModelAccessFactory;
import de.upb.pcm.simulizar.access.SDAccess;
import de.upb.pcm.simulizar.utils.InterpreterLogger;

public class SDReconfigurator implements IReconfigurator {

    final static Logger logger = Logger.getLogger(SDReconfigurator.class);

    private final SDAccess sdAccess;

    private final SDExecutor sdExecutor;

    public SDReconfigurator(final IModelAccessFactory modelAccessFactory) {
        super();
        this.sdAccess = modelAccessFactory.getSDAccess();
        this.sdExecutor = new SDExecutor(modelAccessFactory);
    }

    @Override
    public void runReconfiguration(final EObject monitoredElement) {
        if (this.sdAccess.sdModelsExist()) {
            InterpreterLogger.debug(logger, "Checking reconfiguration rules due to PRM change");
            final boolean result = this.sdExecutor.executeActivities(monitoredElement);
            InterpreterLogger.debug(logger, result ? "Reconfigured system by a matching rule"
                    : "No reconfiguration rule was executed, all conditions were false");
        }
    }
}

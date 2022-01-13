package org.palladiosimulator.simulizar.interpreter.legacy;

import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.pcm.reliability.ReliabilityPackage;
import org.palladiosimulator.pcm.seff.SeffPackage;
import org.palladiosimulator.pcm.seff.seff_reliability.SeffReliabilityPackage;
import org.palladiosimulator.pcm.seff.seff_reliability.util.SeffReliabilitySwitch;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory.RDSeffElementDispatcher;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;

/**
 * This Switch acts as legacy adapter to provide previous behaviour of allowing SimuLizar to silently ignore reliability model elements.
 * 
 * This NOP interpreter became necessary after modularizing the RDSEFF interpreter, in case reliability simulation is turned off.
 * 
 * @author Sebastian Krach
 *
 */
public class NOPReliabilityInterpreter extends Switch<InterpreterResult> {
    @AssistedFactory
    public static interface Factory extends RDSeffSwitchContributionFactory {
        @Override
        default Switch<InterpreterResult> createRDSeffSwitch(InterpreterDefaultContext context,
                RDSeffElementDispatcher parentSwitch) {
            return create(parentSwitch);
        }
        
        NOPReliabilityInterpreter create(RDSeffElementDispatcher parentSwitch);
    }
    
    private final static Set<String> SUPPORTED_NS_URIS = Set.of(
            SeffReliabilityPackage.eNS_URI,
            ReliabilityPackage.eNS_URI
    );
    private final RDSeffElementDispatcher parentSwitch;
    
    private final Switch<InterpreterResult> delegateSwitch = new SeffReliabilitySwitch<InterpreterResult>() {
        public InterpreterResult caseRecoveryAction(org.palladiosimulator.pcm.seff.seff_reliability.RecoveryAction object) {
            LOGGER.debug("Encountered reliability element (" + object.eClass().getName() +": " + object.getId() + "). Passing to first child.");
            return parentSwitch.doSwitch(SeffPackage.Literals.RESOURCE_DEMANDING_BEHAVIOUR, object.getPrimaryBehaviour__RecoveryAction());
        }
        @Override
        public InterpreterResult defaultCase(EObject eObject) {
            LOGGER.debug("Encountered element to be ignored: " + eObject.toString());
            return InterpreterResult.OK;
        }
    };
    
    @AssistedInject
    public NOPReliabilityInterpreter(@Assisted RDSeffElementDispatcher parentSwitch) {
        this.parentSwitch = parentSwitch;
    }
    
    private static final Logger LOGGER = Logger.getLogger(NOPReliabilityInterpreter.class);

    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return SUPPORTED_NS_URIS.contains(ePackage.getNsURI());
    }

    @Override
    protected InterpreterResult doSwitch(EClass eClass, EObject eObject) {
        if (isSwitchFor(eClass.getEPackage())) {
            return delegateSwitch.doSwitch(eObject);
        } else {
            return null;
        }
    }

}

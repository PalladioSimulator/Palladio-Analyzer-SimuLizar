package org.palladiosimulator.simulizar.interpreter;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.palladiosimulator.pcm.reliability.FailureType;
import org.palladiosimulator.pcm.seff.AbstractAction;
import org.palladiosimulator.pcm.seff.SeffPackage;
import org.palladiosimulator.pcm.seff.seff_reliability.RecoveryAction;
import org.palladiosimulator.pcm.seff.seff_reliability.RecoveryActionBehaviour;
import org.palladiosimulator.pcm.seff.seff_reliability.util.SeffReliabilitySwitch;
import org.palladiosimulator.pcm.seff.util.SeffSwitch;

import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

public class ReliabilitySwitch extends SeffReliabilitySwitch<Object>{

    private static final Boolean SUCCESS = true;
    private static final Logger LOGGER = Logger.getLogger(SeffReliabilitySwitch.class);

	private ExplicitDispatchComposedSwitch<Object> parentSwitch;
	private InterpreterDefaultContext context;

	public ReliabilitySwitch(InterpreterDefaultContext context, ExplicitDispatchComposedSwitch<Object> parentSwitch) {
		this.context = context;
		this.parentSwitch = parentSwitch;
	}

    @Override
    public SimulatedStackframe<Object> caseAbstractAction(final AbstractAction object) {
        throw new UnsupportedOperationException(
                "SEFF Interpreter tried to interpret unsupported action type: " + object.eClass().getName());
    }


	@Override
	public Object caseRecoveryAction(RecoveryAction action) {
		parentSwitch.doSwitch(action.getPrimaryBehaviour__RecoveryAction());
		return SUCCESS;
	}


	@Override
	public Object caseRecoveryActionBehaviour(RecoveryActionBehaviour behaviour) {
		if(context.hasFailureOccurred()) {
			//sanity check, this should not happen
			throw new RuntimeException("RecoverActionBehaviour cannot be executed when there are already failures!");
		}
		parentSwitch.doSwitch(SeffPackage.eINSTANCE.getResourceDemandingBehaviour(), behaviour);
		if(context.hasFailureOccurred()) {
			//if an exception occurred, find the matching alternative, if available
			if(context.hasFailureOccurred()) {
				FailureType failure = context.peekFailure().get().getType();
				for(RecoveryActionBehaviour alternative : behaviour.getFailureHandlingAlternatives__RecoveryActionBehaviour()) {
					List<FailureType> handledTypes = alternative.getFailureTypes_FailureHandlingEntity();
					if(handledTypes.contains(failure)) {
						context.popFailure();
						parentSwitch.doSwitch(alternative);
						break; //found an alternative, at most one exists
					}
				}
			}
		}
		return SUCCESS;
	}

}

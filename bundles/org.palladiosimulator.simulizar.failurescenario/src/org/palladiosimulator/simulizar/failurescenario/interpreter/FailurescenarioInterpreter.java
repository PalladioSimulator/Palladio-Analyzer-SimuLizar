package org.palladiosimulator.simulizar.failurescenario.interpreter;

import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.failuremodel.failurescenario.Occurence;
import org.palladiosimulator.failuremodel.failurescenario.util.FailurescenarioSwitch;
import org.palladiosimulator.failuremodel.failuretype.Failure;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import de.uka.ipd.sdq.simucomframework.ResourceRegistry;

public class FailurescenarioInterpreter extends FailurescenarioSwitch<InterpreterResult> {
	
	@AssistedFactory
    public static interface Factory extends RDSeffSwitchContributionFactory {
        @Override
        default Switch<InterpreterResult> createRDSeffSwitch(InterpreterDefaultContext context,
                RDSeffElementDispatcher parentSwitch) {
            return create(context);
        }
        
        FailurescenarioInterpreter create(InterpreterDefaultContext context);
    }
	
	private final InterpreterDefaultContext context;
	private final ResourceRegistry resourceRegistry;
	
	@AssistedInject
    public FailurescenarioInterpreter(@Assisted final InterpreterDefaultContext context, ResourceRegistry resourceRegistry) {
        this.context = context;
        this.resourceRegistry = resourceRegistry;
    }
	
	@Override
	public InterpreterResult caseOccurence(Occurence object) {
		Failure failure = object.getFailure();

		var result = InterpreterResult.OK;
		//result = InterpreterResult.of(new FailureOccurrence(object.getFailure()));
		
		return result;
	}
}

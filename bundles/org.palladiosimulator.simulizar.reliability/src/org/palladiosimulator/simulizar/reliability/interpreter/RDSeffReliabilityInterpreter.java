package org.palladiosimulator.simulizar.reliability.interpreter;

import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.pcm.reliability.InternalFailureOccurrenceDescription;
import org.palladiosimulator.pcm.reliability.util.ReliabilitySwitch;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import org.palladiosimulator.simulizar.reliability.interpreter.issue.FailureOccurredIssue;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import de.uka.ipd.sdq.probfunction.math.IRandomGenerator;
import de.uka.ipd.sdq.simucomframework.variables.exceptions.ValueNotInFrameException;

public class RDSeffReliabilityInterpreter extends ReliabilitySwitch<InterpreterResult> {
    
    @AssistedFactory
    public static interface Factory extends RDSeffSwitchContributionFactory {
        @Override
        default Switch<InterpreterResult> createRDSeffSwitch(InterpreterDefaultContext context,
                RDSeffElementDispatcher parentSwitch) {
            return create(context);
        }
        
        RDSeffReliabilityInterpreter create(InterpreterDefaultContext context);
    }
    
    public static final String FAILURE_PROBABILITY_RANDOM_DRAW = "__RDSEFF_INTERPRETER_INTERNAL_ACTION_FAILURE_PROBABILITY_RANDOM_DRAW";
    public static final String FAILURE_PROBABILITY_ACCUMULATOR = "__RDSEFF_INTERPRETER_INTERNAL_ACTION_FAILURE_PROBABILITY_ACCUMULATOR";

    
    private final IRandomGenerator randomNumberGenerator;
    private final InterpreterDefaultContext context;

    @AssistedInject
    public RDSeffReliabilityInterpreter(IRandomGenerator randomNumberGenerator,
            @Assisted final InterpreterDefaultContext context) {
        this.randomNumberGenerator = randomNumberGenerator;
        this.context = context;
    }
    
    @Override
    public InterpreterResult caseInternalFailureOccurrenceDescription(InternalFailureOccurrenceDescription object) {
        double randVar = 0.0;
        double accumulator = 0.0;
        double newAccumulator = 0.0;
        var result = InterpreterResult.OK;
        
        try {
            randVar = (Double) context.getStack().currentStackFrame().getValue(FAILURE_PROBABILITY_RANDOM_DRAW);
            accumulator = (Double) context.getStack().currentStackFrame().getValue(FAILURE_PROBABILITY_ACCUMULATOR);
            newAccumulator = accumulator;
        } catch (ValueNotInFrameException e) {
            randVar = randomNumberGenerator.random();
            context.getStack().currentStackFrame().addValue(FAILURE_PROBABILITY_RANDOM_DRAW, randVar);
        }
        
        newAccumulator += object.getFailureProbability(); 
        if (accumulator <= randVar && newAccumulator > randVar) {
            result = InterpreterResult.of(new FailureOccurredIssue(object.getSoftwareInducedFailureType__InternalFailureOccurrenceDescription()));
        }
        
        context.getStack().currentStackFrame().addValue(FAILURE_PROBABILITY_ACCUMULATOR, newAccumulator);
        
        return result;
    }

}

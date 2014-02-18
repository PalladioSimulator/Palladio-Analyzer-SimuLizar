package org.palladiosimulator.simulizar.utils;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;

import de.uka.ipd.sdq.pcm.core.PCMRandomVariable;
import de.uka.ipd.sdq.pcm.parameter.VariableCharacterisation;
import de.uka.ipd.sdq.pcm.parameter.VariableUsage;
import de.uka.ipd.sdq.pcm.stochasticexpressions.PCMStoExPrettyPrintVisitor;
import de.uka.ipd.sdq.simucomframework.variables.EvaluationProxy;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStack;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;
import de.uka.ipd.sdq.stoex.AbstractNamedReference;
import de.uka.ipd.sdq.stoex.NamespaceReference;

/**
 * A simulated stack for the pcm interpreter with some convenience methods.
 * 
 * @author Joachim Meyer
 * 
 */
public final class SimulatedStackHelper {
    private static final Logger logger = Logger.getLogger(SimulatedStackHelper.class);

    /**
     * Adds parameters to given stack frame.
     * 
     * @param parameter
     *            the parameter.
     * @param targetStackFrame
     *            the stack frame.
     */
    public static final void addParameterToStackFrame(final SimulatedStackframe<Object> contextStackFrame,
            final EList<VariableUsage> parameter, final SimulatedStackframe<Object> targetStackFrame) {
        for (final VariableUsage variableUsage : parameter) {
            for (final VariableCharacterisation variableCharacterisation : variableUsage
                    .getVariableCharacterisation_VariableUsage()) {

                final PCMRandomVariable randomVariable = variableCharacterisation
                        .getSpecification_VariableCharacterisation();

                final AbstractNamedReference namedReference = variableCharacterisation
                        .getVariableUsage_VariableCharacterisation().getNamedReference__VariableUsage();

                final String id = new PCMStoExPrettyPrintVisitor().prettyPrint(namedReference) + "."
                        + variableCharacterisation.getType().getLiteral();

                Object value;

                if (namedReference instanceof NamespaceReference) {
                    // we assume that this is now an INNER variable!
                    // assign top most stack frame as current state
                    value = new EvaluationProxy(randomVariable.getSpecification(), contextStackFrame);
                } else {
                    value = StackContext.evaluateStatic(randomVariable.getSpecification(), contextStackFrame);
                }
                targetStackFrame.addValue(id, value);

                if (logger.isDebugEnabled()) {
                    logger.debug("Added value " + value + " for id " + id + " to stackframe "
                        + targetStackFrame);
                }
            }
        }
    }

    /**
     * Convenience method creating new stack frame, adds it to stack and puts parameters into frame.
     * This method uses own stack for parameter evaluation.
     * 
     * @param parameter
     *            the parameter.
     * @return the created stack frame.
     */
    public static final SimulatedStackframe<Object> createAndPushNewStackFrame(final SimulatedStack<Object> stack,
            final EList<VariableUsage> parameter) {
        return createAndPushNewStackFrame(stack, parameter, null);
    }

    /**
     * Convenience method creating new stack frame with parent, adds it to stack and puts parameters
     * into frame. This method uses own stack for parameter evaluation.
     * 
     * @param parameter
     *            the parameter.
     * @param parent
     *            the parent, if null no parent will be set.
     * @return the created stack frame.
     */
    public static final SimulatedStackframe<Object> createAndPushNewStackFrame(final SimulatedStack<Object> stack,
            final EList<VariableUsage> parameter, final SimulatedStackframe<Object> parent) {
        SimulatedStackframe<Object> stackFrame;
        if (parent == null) {
            stackFrame = new SimulatedStackframe<Object>();
        } else {
            stackFrame = new SimulatedStackframe<Object>(parent);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Added new stack frame: " + stackFrame);
        }
        addParameterToStackFrame(stack.size() == 0 ? null : stack.currentStackFrame(), parameter, stackFrame);
        stack.pushStackFrame(stackFrame);
        return stackFrame;
    }
}

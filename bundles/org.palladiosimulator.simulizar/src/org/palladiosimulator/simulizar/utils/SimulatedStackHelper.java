package org.palladiosimulator.simulizar.utils;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.pcm.core.PCMRandomVariable;
import org.palladiosimulator.pcm.parameter.VariableCharacterisation;
import org.palladiosimulator.pcm.parameter.VariableUsage;

import de.uka.ipd.sdq.simucomframework.variables.EvaluationProxy;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.exceptions.ValueNotInFrameException;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStack;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;
import de.uka.ipd.sdq.stoex.AbstractNamedReference;
import de.uka.ipd.sdq.stoex.NamespaceReference;
import de.uka.ipd.sdq.stoex.VariableReference;
import de.uka.ipd.sdq.stoex.analyser.visitors.StoExPrettyPrintVisitor;
import de.uka.ipd.sdq.stoex.util.StoexSwitch;

/**
 * A simulated stack for the pcm interpreter with some convenience methods.
 *
 * @author Joachim Meyer, Christian Stier
 *
 */
public final class SimulatedStackHelper {

    private static final Logger LOGGER = Logger.getLogger(SimulatedStackHelper.class);

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

                final String id = new StoExPrettyPrintVisitor().doSwitch(namedReference).toString() + "."
                        + variableCharacterisation.getType().getLiteral();
                ;
                if (SimulatedStackHelper.isInnerReference(namedReference)) {
                    targetStackFrame.addValue(id,
                            new EvaluationProxy(randomVariable.getSpecification(), contextStackFrame.copyFrame()));
                } else {
                    targetStackFrame.addValue(id,
                            StackContext.evaluateStatic(randomVariable.getSpecification(), contextStackFrame));
                }

                if (LOGGER.isDebugEnabled()) {
                    try {
                        LOGGER.debug("Added value " + targetStackFrame.getValue(id) + " for id " + id
                                + " to stackframe " + targetStackFrame);
                    } catch (final ValueNotInFrameException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    /**
     * Returns whether the specified reference belongs to an INNER variable characterisation.
     *
     * @param reference
     *            the named reference associated with a variable characterisation
     * @return true, if the reference's name is "INNER"; false else.
     */
    public static boolean isInnerReference(final AbstractNamedReference reference) {
        return new StoexSwitch<Boolean>() {

            @Override
            public Boolean caseVariableReference(final VariableReference object) {
                return object.getReferenceName().equals("INNER");
            }

            @Override
            public Boolean caseNamespaceReference(final NamespaceReference object) {
                return object.getReferenceName().equals("INNER")
                        || this.doSwitch(object.getInnerReference_NamespaceReference());
            }

        }.doSwitch(reference);
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
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Added new stack frame: " + stackFrame);
        }
        addParameterToStackFrame(stack.size() == 0 ? null : stack.currentStackFrame(), parameter, stackFrame);
        stack.pushStackFrame(stackFrame);
        return stackFrame;
    }

    /**
     * Convenience method for creating a new stack frame that contains the elements in the given map.
     * 
     * Does not validate the input.
     * 
     * @param map the map of entries the new stack frame is to contain
     * @return the created stack frame
     */
    public static final SimulatedStackframe<Object> createFromMap(final Map<String, Object> map) {
    	SimulatedStackframe<Object> resultFrame = new SimulatedStackframe<Object>();
    	for (Entry<String, Object> entry : map.entrySet()) {
    		resultFrame.addValue(entry.getKey(), entry.getValue());
    	}
    	return resultFrame;
    }
}

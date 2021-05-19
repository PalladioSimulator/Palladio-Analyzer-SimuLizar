package org.palladiosimulator.simulizar.failurescenario.interpreter.behavior.preinterpretation;

import java.util.List;
import java.util.Map.Entry;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitch;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehavior;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.converter.NumberConverter;
import de.uka.ipd.sdq.simucomframework.variables.exceptions.ValueNotInFrameException;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

public class CorruptContentBehavior extends PreInterpretationBehavior {
	private String degreeOfCorruptionSpec;

	public CorruptContentBehavior(String degreeOfCorruptionSpec) {
		super(InterpreterResult.OK);
		this.degreeOfCorruptionSpec = degreeOfCorruptionSpec;
	}

	/**
	 * Manipulates the degreeOfCorruption of the simulations stackframe variables.
	 * 
	 * @return InterpreterResult.OK
	 */
	@Override
	public InterpreterResult execute(InterpreterDefaultContext context) {
		if (context != null) {
			setDegreeOfCorruptionCharacterisation(context);
		}
		return super.execute(context);
	}

	/**
	 * Sets DegreeOfCorruption to every parameter of the stackFrame
	 * 
	 * @param context The DefaultInterpreterContext
	 */
	private void setDegreeOfCorruptionCharacterisation(InterpreterDefaultContext context) {
		double corruptionSummand = NumberConverter.toDouble(StackContext.evaluateStatic(degreeOfCorruptionSpec));

		List<Entry<String, Object>> entries = context.getStack().currentStackFrame().getContents();
		List<Entry<String, Object>> entriesOfResult = context.getCurrentResultFrame().getContents();
		final SimulatedStackframe<Object> resultFrame = context.getCurrentResultFrame();
		try {
			resultFrame.getValue("parameter0." + RDSeffSwitch.DEGREE_OF_CORRUPTION);
		} catch (ValueNotInFrameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		entries.addAll(entriesOfResult);
		entries.forEach(e -> {
			String key = e.getKey();
			if (key.endsWith("." + RDSeffSwitch.DEGREE_OF_CORRUPTION)) {
				try {
					double newDegreeOfCorruption = Math.max(0.0, Math.min(1.0,
							(double) context.getStack().currentStackFrame().getValue(key) + corruptionSummand));
					context.getStack().currentStackFrame().addValue(key, newDegreeOfCorruption);
					resultFrame.addValue(key, newDegreeOfCorruption);
				} catch (ValueNotInFrameException exception) {
					// Not found
				}
			}
		});
	}
}

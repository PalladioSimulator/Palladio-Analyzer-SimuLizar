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
	 * @param context The DefaultInterpreterContext
	 */
	private void setDegreeOfCorruptionCharacterisation(InterpreterDefaultContext context) {
		List<Entry<String, Object>> entries = context.getStack().currentStackFrame().getContents();
		entries.forEach(e -> {
			String key = e.getKey();
			if (key.endsWith("." + RDSeffSwitch.DEGREE_OF_CORRUPTION)) {
				setDegreeOfCorruptionCharacterisation(context, e.getKey());
			}
		});
	}

	/**
	 * Sets DegreeOfCorruption to every parameter of the stackFrame
	 */
	private void setDegreeOfCorruptionCharacterisation(InterpreterDefaultContext context, String idOfParameter) {
		double corruptionSummand = NumberConverter.toDouble(StackContext.evaluateStatic(degreeOfCorruptionSpec)); //TODO hochziehen, nur einmal auswerten
		try {
			double newDegreeOfCorruption = Math.min(1.0,
					(double) context.getStack().currentStackFrame().getValue(idOfParameter) + corruptionSummand);
			context.getStack().currentStackFrame().addValue(idOfParameter, newDegreeOfCorruption);
		} catch (ValueNotInFrameException e) {
			// Not found
		}
	}
}

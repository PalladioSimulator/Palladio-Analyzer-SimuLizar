package de.upb.pcm.interpreter.simulation;

import java.util.EmptyStackException;

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
import de.uka.ipd.sdq.stoex.analyser.visitors.StoExPrettyPrintVisitor;
import de.upb.pcm.interpreter.utils.InterpreterLogger;

/**
 * A simulated stack for the pcm interpreter with some convenience methods.
 * 
 * @author Joachim Meyer
 * 
 */
public class InterpreterSimulatedStack extends SimulatedStack<Object>
{
   /**
    * 
    */
   private static final long serialVersionUID = 48089399746219161L;

   protected static final Logger logger = Logger.getLogger(InterpreterSimulatedStack.class.getName());

   /**
    * Constructor
    */
   public InterpreterSimulatedStack()
   {
      super();
   }

   /**
    * Creates an interpreter simulated stack with initial contents of the given simulated stack
    * (copies top most stack frame and all of its children)
    * 
    * @param simulatedStack the simulated stack from which the new one should be created.
    */
   public InterpreterSimulatedStack(final SimulatedStack<Object> simulatedStack)
   {
      super();
      this.pushStackFrame(simulatedStack.currentStackFrame().copyFrame());
   }

   /**
    * Adds parameters to given stack frame.
    * 
    * @param parameter the parameter.
    * @param stackFrame the stack frame.
    */
   public void addParameterToStackFrame(final EList<VariableUsage> parameter,
         final SimulatedStackframe<Object> stackFrame)
   {
      for (final VariableUsage variableUsage : parameter)
      {
         for (final VariableCharacterisation variableCharacterisation : variableUsage
               .getVariableCharacterisation_VariableUsage())
         {
            final StoExPrettyPrintVisitor stoexPrettyPrinter = new PCMStoExPrettyPrintVisitor();
            final String id = stoexPrettyPrinter.prettyPrint(variableCharacterisation
                  .getVariableUsage_VariableCharacterisation());

            final PCMRandomVariable randomVariable = variableCharacterisation
                  .getSpecification_VariableCharacterisation();

            final AbstractNamedReference namedReference = variableCharacterisation
                  .getVariableUsage_VariableCharacterisation().getNamedReference__VariableUsage();

            /*
             * for the first time the stack is empty, the current stackframe will be the which will
             * created now. This is a really ugly hack!
             */

            SimulatedStackframe<Object> currentStackFrame;
            Object value;
            try
            {
               currentStackFrame = this.currentStackFrame();
            }
            catch (final EmptyStackException e)
            {
               currentStackFrame = stackFrame;
            }

            if (namedReference instanceof NamespaceReference)
            {
               // we assume that this is now an INNER variable!
               // assign top most stack frame as current state
               value = new EvaluationProxy(randomVariable.getSpecification(), currentStackFrame);
            }
            else
            {
               value = StackContext.evaluateStatic(randomVariable.getSpecification(), currentStackFrame);
            }
            stackFrame.addValue(id, value);

            InterpreterLogger.debug(logger, "Added value " + value + " for id " + id + " to stackframe " + stackFrame);
         }
      }
   }


   /**
    * Convenience method creating new stack frame, adds it to stack and puts parameters into frame.
    * This method uses own stack for parameter evaluation.
    * 
    * @param parameter the parameter.
    * @return the created stack frame.
    */
   public SimulatedStackframe<Object> createAndPushNewStackFrame(final EList<VariableUsage> parameter)
   {
      return createAndPushNewStackFrame(parameter, null);
   }


   /**
    * Convenience method creating new stack frame with parent, adds it to stack and puts parameters
    * into frame. This method uses own stack for parameter evaluation.
    * 
    * @param parameter the parameter.
    * @param parent the parent, if null no parent will be set.
    * @return the created stack frame.
    */
   public SimulatedStackframe<Object> createAndPushNewStackFrame(final EList<VariableUsage> parameter,
         final SimulatedStackframe<Object> parent)
   {
      SimulatedStackframe<Object> stackFrame;
      if (parent == null)
      {
         stackFrame = new SimulatedStackframe<Object>();
      }
      else
      {
         stackFrame = new SimulatedStackframe<Object>(parent);
      }
      InterpreterLogger.debug(logger, "Added new stack frame: " + stackFrame);
      addParameterToStackFrame(parameter, stackFrame);
      this.pushStackFrame(stackFrame);
      return stackFrame;
   }
}

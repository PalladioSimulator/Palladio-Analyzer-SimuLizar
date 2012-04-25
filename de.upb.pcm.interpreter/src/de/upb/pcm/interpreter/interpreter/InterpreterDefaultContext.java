package de.upb.pcm.interpreter.interpreter;

import java.util.Stack;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.simucomframework.Context;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStack;


/**
 * Default context for the pcm interpreter.
 * 
 * @author Joachim Meyer
 * 
 */
public class InterpreterDefaultContext extends Context
{

   /**
    * 
    */
   private static final long serialVersionUID = -5027373777424401211L;

   protected static final Logger logger = Logger.getLogger(InterpreterDefaultContext.class.getName());
   
   private final Stack<AssemblyContext> assemblyContextStack = new Stack<AssemblyContext>();

   /**
    * Create interpreter default context from the given default context (model, sim process and
    * stack are set according to the given default context). The contents of the stack will be
    * copied.
    * 
    * @param defaultContext the default context from which the new default context should be
    *           created.
    */
   public InterpreterDefaultContext(final Context defaultContext)
   {
      super(defaultContext.getModel());
      this.setEvaluationMode(defaultContext.getEvaluationMode());
      this.setSimProcess(defaultContext.getThread());
      stack = new SimulatedStack<Object>();
      if (defaultContext.getStack().size() > 0) {
    	  stack.pushStackFrame(defaultContext.getStack().currentStackFrame().copyFrame());
      }
   }

   /**
    * Constrcutor
    * 
    * @param simuComModel the SimuCom model.
    * @param simProcess the sim process of this context, means the process in which this context is
    *           used
    */
   public InterpreterDefaultContext(final SimuComModel simuComModel, final SimuComSimProcess simProcess)
   {
      super(simuComModel);
      stack = new SimulatedStack<Object>();
      this.setSimProcess(simProcess);
   }

   public InterpreterDefaultContext(SimuComModel simuComModel) {
	      super(simuComModel);
	      stack = new SimulatedStack<Object>();
   }

   /**
    * @see de.upb.pcm.interpreter.interpreter.InterpreterDefaultContext#initialiseAssemblyContextLookup()
    */
   @Override
   protected void initialiseAssemblyContextLookup()
   { 
	   // Template method which is only needed in generative SimuCom
   }

   public Stack<AssemblyContext> getAssemblyContextStack()
   {
	   return this.assemblyContextStack;
   }
}

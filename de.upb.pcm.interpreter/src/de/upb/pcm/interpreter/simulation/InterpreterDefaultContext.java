package de.upb.pcm.interpreter.simulation;


import org.apache.log4j.Logger;

import de.uka.ipd.sdq.simucomframework.Context;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.SimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;


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
      stack = new InterpreterSimulatedStack(defaultContext.getStack());

   }


   /**
    * Constrcutor
    * 
    * @param simuComModel the SimuCom model.
    * @param simProcess the sim process of this context, means the process in which this context is
    *           used
    */
   public InterpreterDefaultContext(final SimuComModel simuComModel, final SimProcess simProcess)
   {
      super(simuComModel);
      stack = new InterpreterSimulatedStack();
      this.setSimProcess(simProcess);
   }


   /**
    * @see de.uka.ipd.sdq.simucomframework.variables.StackContext#getStack()
    */
   @Override
   public InterpreterSimulatedStack getStack()
   {

      return (InterpreterSimulatedStack) super.getStack();
   }


   /**
    * @see de.upb.pcm.interpreter.simulation.InterpreterDefaultContext#initialiseAssemblyContextLookup()
    */
   @Override
   protected void initialiseAssemblyContextLookup()
   {


   }


}
